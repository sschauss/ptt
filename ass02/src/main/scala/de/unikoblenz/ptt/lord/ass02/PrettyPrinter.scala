package de.unikoblenz.ptt.lord.ass02

import de.unikoblenz.ptt.lord.ass02.ast._


import org.kiama.output.PrettyPrinter

object PrettyPrinter extends PrettyPrinter {

  def pretty(node: Node): String = super.pretty(show(node))

  def show(node: Node): Doc = node match {
    case SelectorGroup(selectorSequences) => ssep(selectorSequences map show, ", ")
    case SelectorSequence(selector, selectorCombinations) => show(selector) <> sep(selectorCombinations map show)
    case SelectorCombination(operator, selectorSequence) => operator <> show(selectorSequence)
    case ClassSelector(className) => "." <> className
    case IdSelector(idName) => "#" <> idName
    case TypeSelector(Some(nameSpacePrefix), typeName) => show(nameSpacePrefix) <> typeName
    case TypeSelector(None, typeName) => typeName
    case NameSpacePrefix(Some(nameSpaceName)) => nameSpaceName <> "|"
    case NameSpacePrefix(None) => "|"
    case UniversalSelector(Some(nameSpacePrefix)) => show(nameSpacePrefix) <> "*"
    case UniversalSelector(None) => "*"
    case AttributeSelector(None, attributeName, None) => "[" <> attributeName <> "]"
    case AttributeSelector(None, attributeName, Some(attribute)) => "[" <> attributeName <> show(attribute) <> "]"
    case AttributeSelector(Some(nameSpacePrefix), attributeName, None) => "[" <> show(nameSpacePrefix) <> attributeName <> "]"
    case AttributeSelector(Some(nameSpacePrefix), attributeName, Some(attribute)) => "[" <> show(nameSpacePrefix) <> attributeName <> show(attribute) <> "]"
    case Attribute(attributeOperator, attributeValue) => attributeOperator <> attributeValue
    case PseudoClassSelector(pseudoClassName, None) => ":" <> pseudoClassName
    case PseudoClassSelector(pseudoClassName, Some(pseudoClassExpression)) => ":" <> pseudoClassName <> "(" <> pseudoClassExpression <> ")"
    case PseudoElementSelector(pseudoElementName) => "::" <> pseudoElementName
    case NotSelector(selector: Selector) => ":not(" <> show(selector) <> ")"
  }

}
