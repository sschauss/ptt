package de.unikoblenz.ptt.lord.ass02

import de.unikoblenz.ptt.lord.ass02.ast._

object Transformer {

  def transform(scss: SCSS) = {
    SCSS(transformVariables(scss).nodes map {
      case ruleSet: RuleSet => transformRuleSet(ruleSet)
      case node => List(node)
    } reduce (_ ::: _))
  }


  def transformVariables(nodes: List[Node], variables: Set[Variable]): List[Node] = nodes.zipWithIndex collect {
    case (node, i) if !node.isInstanceOf[Variable] => transformVariables(node, buildVariableSet(nodes.take(i), variables))
  }

  def transformVariables[T](node: T, variables: Set[Variable] = Set()): T = (node match {
    case SCSS(nodes) => SCSS(transformVariables(nodes, variables))
    case RuleSet(selectorGroup, rules) => RuleSet(selectorGroup, transformVariables(rules, variables))
    case Declaration(string, valueGroups) => Declaration(string, valueGroups map { case v: ValueGroup => replaceVariable(v, variables)} reduce (_ ::: _))
    case _ => node
  }).asInstanceOf[T]

  def buildVariableSet(nodes: List[Node], variables: Set[Variable]): Set[Variable] =
    (nodes collect {
      case v: Variable => v
    }).foldLeft(variables.toList)((list, e) => list.filter(_.name != e.name) :+ Variable(e.name, e.valueGroups map {
      v => replaceVariable(v, list.toSet)
    } reduce (_ ::: _))).toSet

  def replaceVariable(valueGroup: ValueGroup, variables: Set[Variable]): List[ValueGroup] = valueGroup.values.zipWithIndex collectFirst {
    case (v: VariableValue, i) => (v, i)
  } match {
    case None => List(valueGroup)
    case Some((variableValue, i)) => variables.find(_.name == variableValue.name) match {
      case Some(Variable(_, valueGroups)) => valueGroups match {
        case Nil => List(valueGroup)
        case head :: Nil => List(ValueGroup(valueGroup.values.take(i) ::: head.values ::: valueGroup.values.drop(i + 1)))
        case head :: tail => ValueGroup(valueGroup.values.take(i) ::: head.values) :: (tail.slice(1, tail.size - 1) :+ ValueGroup(tail.last.values ::: valueGroup.values.drop(i + 1)) map {
          v => replaceVariable(v, variables)
        } reduce (_ ::: _))
      }
    }
  }

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
      case SelectorSequence(selector, None) =>
        SelectorSequence(selector, Some(SelectorCombination(" ", r)))
      case SelectorSequence(selector, Some(selectorCombination)) =>
        SelectorSequence(selector, Some(SelectorCombination(selectorCombination.operator, appendSelectorSequence(selectorCombination.selectorSequence, r))))
    }




  def calculateExpression(expression: Expression): DimensionedValue = {
    var result = calculateTerm(expression.term)
    result = expression.operations.foldLeft(result){
      case (r, c:Addition) => addWithDimension(r, calculateTerm(c.summand))
      case (r, c:Subtraction) => subtractWithDimension(r, calculateTerm(c.subtrahend))
    }
    result
  }

  def calculateTerm(term: Term): DimensionedValue = {
    var result = calculateFactor(term.factor)
    result = term.operations.foldLeft(result){
      case (r, c: Multiplication) => multiplyWithDimension(r, calculateFactor(c.factor))
      case (r, c: Division) => divideWithDimension(r, calculateFactor(c.divisor))
    }
    result
  }

  def calculateFactor(factor: Node): DimensionedValue = {
    factor match {
      case f: DimensionedValue => f
      case f: Expression => calculateExpression(f)
      case f: NegativeExpression => multiplyWithDimension(DimensionedValue(-1, None), calculateExpression(f.expression))
    }
  }

  def addWithDimension(a: DimensionedValue, b: DimensionedValue): DimensionedValue = {
    if (a.dimension != b.dimension) throw new Exception("Dimension mismatch: " + a.dimension + "+" + b.dimension)
    DimensionedValue(a.value + b.value, a.dimension)
  }

  def subtractWithDimension(a: DimensionedValue, b: DimensionedValue): DimensionedValue = {
    if (a.dimension != b.dimension) throw new Exception("Dimension mismatch: " + a.dimension + "-" + b.dimension)
    DimensionedValue(a.value - b.value, a.dimension)
  }

  def multiplyWithDimension(a: DimensionedValue, b: DimensionedValue): DimensionedValue = {
    (a.dimension, b.dimension) match {
      case (Some(_), Some(_)) => throw new Exception("Dimension mismatch: " + a.dimension + "*" + b.dimension)
      case (Some(_), None) => DimensionedValue(a.value * b.value, a.dimension)
      case (None, Some(_)) => DimensionedValue(a.value * b.value, b.dimension)
      case (None, None) => DimensionedValue(a.value * b.value, None)
    }
  }

  def divideWithDimension(a: DimensionedValue, b: DimensionedValue): DimensionedValue = {
    (a.dimension, b.dimension) match {
      case (Some(_), Some(_)) if a.dimension != b.dimension => throw new Exception("Dimension mismatch: " + a.dimension + "/" + b.dimension)
      case (Some(_), Some(_)) if a.dimension == b.dimension => DimensionedValue(a.value / b.value, None)
      case (Some(_), None) => DimensionedValue(a.value / b.value, a.dimension)
      case (None, Some(_)) => DimensionedValue(a.value / b.value, b.dimension)
      case (None, None) => DimensionedValue(a.value / b.value, None)
    }
  }
}
