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
    case RuleSet(selectorGroup, rules: List[Rule]) => show(selectorGroup) <+> "{" <> nest(line <> vsep(rules map show)) <> line <> "}"
    case Declaration(property, valueGroups) => property <> ":" <+> ssep(valueGroups map show, ", ") <> ";"
    case ValueGroup(values) => ssep(values map show, " ")
    case StringValue(value) => value
    case VariableName(name) => "$" <> name
    case SCSS(ruleSets) => ssep(ruleSets map show, line)
    case Extend(selector) => show(selector)
    case Import(name) => "@import" <+> name
    case Include(name, None) => "@include" <+> name <> ";"
    case Include(name, Some(parameters)) => "@include" <+> name <> "(" <> ssep(parameters map show, ", ") <> ");"
    case Mixin(name, None, rules) => "@mixin" <+> name <+> "{" <> nest(line <> vsep(rules map show)) <> line <> "}"
    case Mixin(name, Some(parameters), rules) => "@mixin" <+> name <> "(" <> ssep(parameters map show, ", ") <> ")" <+> "{" <> nest(line <> vsep(rules map show)) <> line <> "}"
    case Parameter(name) => "$" <> name
    case Variable(variableName, valueGroups) => show(variableName) <> ":" <+> ssep(valueGroups map show, ", ")
    case VariableValue(value) => "$" <> value
  }

}
