package de.unikoblenz.ptt.lord.ass02

import de.unikoblenz.ptt.lord.ass02.ast._


import org.kiama.output.PrettyPrinter

object SCSSPrettyPrinter extends PrettyPrinter {

	implicit def doubleToString(value: Double): Doc = text(value.toString.replaceAll("\\.0*$|(?<=\\.[0-9]{0,2147483646})0*$", ""))

	def pretty(node: Node): String = super.pretty(show(node))

	def show(node: Node): Doc = node match {
		case SCSS(nodes, _)                                                           => ssep(nodes map show, line <> line)
		case Import(name)                                                             => "@import" <+> name
		case Include(name, None)                                                      => "@include" <+> name <> ";"
		case Include(name, Some(parameters))                                          => "@include" <+> name <> "(" <> ssep(parameters map show, ", ") <> ");"
		case Mixin(name, None, rules)                                                 => "@mixin" <+> name <+> "{" <> nest(line <> vsep(rules map show)) <> line <> "}"
		case Mixin(name, Some(parameters), rules)                                     => "@mixin" <+> name <> "(" <> ssep(parameters map show, ", ") <> ")" <+> "{" <> nest(line <> vsep(rules map show)) <> line <> "}"
		case Parameter(name)                                                          => "$" <> name
		case RuleSet(selectorGroup, rules)                                            => show(selectorGroup) <+> "{" <> nest(line <> vsep(rules map show)) <> line <> "}"
		case Variable(name, valueGroups)                                              => "$" <> name <> ":" <+> ssep(valueGroups map show, ", ") <> ";"
		case SelectorGroup(selectorSequences)                                         => ssep(selectorSequences map show, ", ")
		case SelectorSequence(selector, None)                                         => show(selector)
		case SelectorSequence(selector, Some(selectorCombination))                    => show(selector) <> show(selectorCombination)
		case SelectorCombination("", selectorSequence)                                => "" <> show(selectorSequence)
		case SelectorCombination(" ", selectorSequence)                               => " " <> show(selectorSequence)
		case SelectorCombination(operator, selectorSequence)                          => " " <> operator <+> show(selectorSequence)
		case ClassSelector(className)                                                 => "." <> className
		case IdSelector(idName)                                                       => "#" <> idName
		case TypeSelector(Some(nameSpacePrefix), typeName)                            => show(nameSpacePrefix) <> typeName
		case TypeSelector(None, typeName)                                             => typeName
		case NameSpacePrefix(Some(nameSpaceName))                                     => nameSpaceName <> "|"
		case NameSpacePrefix(None)                                                    => "|"
		case UniversalSelector(Some(nameSpacePrefix))                                 => show(nameSpacePrefix) <> "*"
		case UniversalSelector(None)                                                  => "*"
		case AttributeSelector(None, attributeName, None)                             => "[" <> attributeName <> "]"
		case AttributeSelector(None, attributeName, Some(attribute))                  => "[" <> attributeName <> show(attribute) <> "]"
		case AttributeSelector(Some(nameSpacePrefix), attributeName, None)            => "[" <> show(nameSpacePrefix) <> attributeName <> "]"
		case AttributeSelector(Some(nameSpacePrefix), attributeName, Some(attribute)) => "[" <> show(nameSpacePrefix) <> attributeName <> show(attribute) <> "]"
		case Attribute(attributeOperator, attributeValue)                             => attributeOperator <> attributeValue
		case PseudoClassSelector(pseudoClassName, None)                               => ":" <> pseudoClassName
		case PseudoClassSelector(pseudoClassName, Some(pseudoClassExpression))        => ":" <> pseudoClassName <> "(" <> pseudoClassExpression <> ")"
		case PseudoElementSelector(pseudoElementName)                                 => "::" <> pseudoElementName
		case NotSelector(selector: Node)                                              => ":not(" <> show(selector) <> ")"
		case Declaration(property, valueGroups)                                       => property <> ":" <+> ssep(valueGroups map show, ", ") <> ";"
		case ValueGroup(values)                                                       => ssep(values map show, " ")
		case Value(value)                                                             => value
		case VariableValue(value)                                                     => "$" <> value
		case Expression(term, operations)                                             => show(term) <> sep(operations map show)
		case Term(factor, operations)                                                 => show(factor) <> sep(operations map show)
		case NegativeExpression(expression)                                           => "-(" <> show(expression) <> ")"
		case Addition(term)                                                           => " + " <> show(term)
		case Subtraction(subtrahend)                                                  => " - " <> show(subtrahend)
		case Multiplication(factor)                                                   => " * " <> show(factor)
		case Division(divisor)                                                        => " / " <> show(divisor)
		case DimensionedValue(value, None)                                            => value
		case DimensionedValue(value, Some(dimension))                                 => value <> dimension
	}

}
