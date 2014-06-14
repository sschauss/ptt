package de.unikoblenz.ptt.lord.ass02

import de.unikoblenz.ptt.lord.ass02.ast._

object Transformer {

	def transform(scss: SCSS): SCSS = transform(scss, List(transformIncludes, transformVariables, transformRuleSet, transformExpressions))

	def transform(scss: SCSS, functions: List[SCSS => SCSS]): SCSS = functions match {
		case Nil     => scss
		case f :: fs => transform(f(scss), fs)
	}

	def transformIncludes(scss: SCSS): SCSS = SCSS(transformIncludes(scss.nodes, Set[Mixin]()))

	def transformIncludes(nodes: List[Node], mixins: Set[Mixin]): List[Node] = nodes.zipWithIndex collect {
		case (node, i) if !node.isInstanceOf[Mixin] => transformIncludes(node, buildMixinSet(nodes.take(i), mixins))
	} reduce (_ ::: _)

	def transformIncludes(node: Node, mixins: Set[Mixin]): List[Node] = node match {
		case RuleSet(selectorGroup, rules) => List(RuleSet(selectorGroup, transformIncludes(rules, mixins)))
		case Include(name, None)           => mixins find {
			_.name == name
		} match {
			case Some(Mixin(_, None, rules)) => rules
		}
		case Include(name, Some(nodes))    => mixins find {
			_.name == name
		} match {
			case Some(Mixin(_, Some(parameters), rules)) => ((parameters, nodes).zipped map { (parameter, node) => Variable(parameter.name, List(ValueGroup(List(node))))}) ::: rules
		}
		case _                             => List(node)
	}

	def buildMixinSet(nodes: List[Node], mixins: Set[Mixin]): Set[Mixin] =
		(nodes collect {
			case m: Mixin => m
		}).foldLeft(mixins.toList)((list, e) => list.filter(_.name != e.name) :+ e).toSet


	def transformVariables(scss: SCSS): SCSS = transformVariables(scss, Set[Variable]()).asInstanceOf[SCSS]

	def transformVariables(nodes: List[Node], variables: Set[Variable]): List[Node] = nodes.zipWithIndex collect {
		case (node, i) if !node.isInstanceOf[Variable] => transformVariables(node, buildVariableSet(nodes.take(i), variables))
	}

	def transformVariables(node: Node, variables: Set[Variable]): Node = node match {
		case SCSS(nodes)                      => SCSS(transformVariables(nodes, variables))
		case RuleSet(selectorGroup, rules)    => RuleSet(selectorGroup, transformVariables(rules, variables))
		case Declaration(string, valueGroups) => Declaration(string, valueGroups map { case v: ValueGroup => replaceVariable(v, variables)} reduce (_ ::: _))
		case Expression(term, operations)     => Expression(transformVariables(term, variables).asInstanceOf[Term], operations map { node => transformVariables(node, variables)})
		case Term(factor, operations)         => Term(transformVariables(factor, variables), operations map { node => transformVariables(node, variables)})
		case Multiplication(factor)           => Multiplication(transformVariables(factor, variables))
		case Addition(summand)                => Addition(transformVariables(summand, variables).asInstanceOf[Term])
		case Division(divisor)                => Division(transformVariables(divisor, variables))
		case Subtraction(subtrahend)          => Subtraction(transformVariables(subtrahend, variables).asInstanceOf[Term])
		case VariableValue(name) => variables.find(_.name == name) match {
			case Some(variable) => variable.valueGroups.head.values.head //catch illegal expressions
		}
		case _                                => node
	}

	def buildVariableSet(nodes: List[Node], variables: Set[Variable]): Set[Variable] =
		(nodes collect {
			case v: Variable => v
		}).foldLeft(variables.toList)((list, e) => list.filter(_.name != e.name) :+ Variable(e.name, e.valueGroups map {
			v => replaceVariable(v, list.toSet)
		} reduce (_ ::: _))).toSet

	def replaceVariable(valueGroup: ValueGroup, variables: Set[Variable]): List[ValueGroup] = valueGroup.values.zipWithIndex collectFirst {
		case (v: VariableValue, i) => (v, i)
	} match {
		case None                     => List(ValueGroup(valueGroup.values map { node => transformVariables(node, variables)}))
		case Some((variableValue, i)) => variables.find(_.name == variableValue.name) match {
			case Some(Variable(_, valueGroups)) => valueGroups match {
				case Nil          => List(valueGroup)
				case head :: Nil  => List(ValueGroup(valueGroup.values.take(i) ::: head.values ::: valueGroup.values.drop(i + 1)))
				case head :: tail => ValueGroup(valueGroup.values.take(i) ::: head.values) :: (tail.slice(1, tail.size - 1) :+ ValueGroup(tail.last.values ::: valueGroup.values.drop(i + 1)) map {
					v => replaceVariable(v, variables)
				} reduce (_ ::: _))
			}
		}
	}

	def transformRuleSet(scss: SCSS): SCSS = SCSS(scss.nodes map {
		case ruleSet: RuleSet => transformRuleSet(ruleSet)
		case node             => List(node)
	} reduce (_ ::: _))


	def transformRuleSet(ruleSet: RuleSet): List[RuleSet] =
		(List(RuleSet(ruleSet.selectorGroup, ruleSet.rules collect {
			case node if !node.isInstanceOf[RuleSet] => node
		})) /: (ruleSet.rules collect {
			case r: RuleSet => transformRuleSet(RuleSet(appendSelector(ruleSet.selectorGroup, r.selectorGroup), r.rules))
		})) {
			_ ::: _
		}

	def appendSelector(l: SelectorGroup, r: SelectorGroup): SelectorGroup =
		SelectorGroup(l.selectorSequences map { ls => r.selectorSequences map { rs => appendSelectorSequence(ls, rs)}} reduce (_ ::: _))

	def appendSelectorSequence(l: SelectorSequence, r: SelectorSequence): SelectorSequence =
		l match {
			case SelectorSequence(selector, None)                      =>
				SelectorSequence(selector, Some(SelectorCombination(" ", r)))
			case SelectorSequence(selector, Some(selectorCombination)) =>
				SelectorSequence(selector, Some(SelectorCombination(selectorCombination.operator, appendSelectorSequence(selectorCombination.selectorSequence, r))))
		}

	def transformExpressions(scss: SCSS): SCSS = SCSS(scss.nodes map transformExpressions)

	def transformExpressions(node: Node): Node = node match {
		case RuleSet(selectorGroup, rules)      => RuleSet(selectorGroup, rules map transformExpressions)
		case Mixin(name, parameters, rules)     => Mixin(name, parameters, rules map transformExpressions)
		case Declaration(property, valueGroups) => Declaration(property, valueGroups map transformExpressions)
		case ValueGroup(values)                 => ValueGroup(values map transformExpressions)
		case expression: Expression             => calculateExpression(expression)
		case _                                  => node
	}

	def calculateExpression(expression: Expression): DimensionedValue =
		expression.operations.foldLeft(calculateTerm(expression.term)) {
			case (r, c: Addition)    => addWithDimension(r, calculateTerm(c.summand))
			case (r, c: Subtraction) => subtractWithDimension(r, calculateTerm(c.subtrahend))
		}

	def calculateTerm(term: Term): DimensionedValue =
		term.operations.foldLeft(calculateFactor(term.factor)) {
			case (r, c: Multiplication) => multiplyWithDimension(r, calculateFactor(c.factor))
			case (r, c: Division)       => divideWithDimension(r, calculateFactor(c.divisor))
		}


	def calculateFactor(factor: Node): DimensionedValue =
		factor match {
			case f: DimensionedValue   => f
			case f: Expression         => calculateExpression(f)
			case f: NegativeExpression => multiplyWithDimension(DimensionedValue(-1, None), calculateExpression(f.expression))
		}


	def addWithDimension(a: DimensionedValue, b: DimensionedValue): DimensionedValue = (a.dimension, b.dimension) match {
		case (None, None)       => DimensionedValue(a.value + b.value, None)
		case (None, Some(_))    => DimensionedValue(a.value + b.value, b.dimension)
		case (Some(_), None)    => DimensionedValue(a.value + b.value, a.dimension)
		case (Some(_), Some(_)) => if (a.dimension == b.dimension) {
			DimensionedValue(a.value + b.value, b.dimension)
		} else {
			throw new Exception("Dimension mismatch: " + a.dimension + "+" + b.dimension)
		}
	}


	def subtractWithDimension(a: DimensionedValue, b: DimensionedValue): DimensionedValue = (a.dimension, b.dimension) match {
		case (None, None)       => DimensionedValue(a.value - b.value, None)
		case (None, Some(_))    => DimensionedValue(a.value - b.value, b.dimension)
		case (Some(_), None)    => DimensionedValue(a.value - b.value, a.dimension)
		case (Some(_), Some(_)) => if (a.dimension == b.dimension) {
			DimensionedValue(a.value - b.value, b.dimension)
		} else {
			throw new Exception("Dimension mismatch: " + a.dimension + "-" + b.dimension)
		}
	}


	def multiplyWithDimension(a: DimensionedValue, b: DimensionedValue): DimensionedValue = (a.dimension, b.dimension) match {
		case (Some(_), Some(_)) => throw new Exception("Dimension mismatch: " + a.dimension + "*" + b.dimension)
		case (Some(_), None)    => DimensionedValue(a.value * b.value, a.dimension)
		case (None, Some(_))    => DimensionedValue(a.value * b.value, b.dimension)
		case (None, None)       => DimensionedValue(a.value * b.value, None)
	}


	def divideWithDimension(a: DimensionedValue, b: DimensionedValue): DimensionedValue =
		(a.dimension, b.dimension) match {
			case (Some(_), Some(_)) if a.dimension != b.dimension => throw new Exception("Dimension mismatch: " + a.dimension + "/" + b.dimension)
			case (Some(_), Some(_)) | (None, None)                => DimensionedValue(a.value / b.value, None)
			case (Some(_), None)                                  => DimensionedValue(a.value / b.value, a.dimension)
			case (None, Some(_))                                  => DimensionedValue(a.value / b.value, b.dimension)
		}

}
