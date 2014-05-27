package de.unikoblenz.ptt.lord.ass02

import org.kiama.output.PrettyPrinter
import de.unikoblenz.ptt.lord.ass02.Ast._

/**
 * Created by simon on 27/05/14.
 */
object PrettyPrinter extends PrettyPrinter {

  def pretty(node: Node): String = super.pretty(show(node))

  def show(node: Node): Doc = node match {
    case Sass(ruleSets) => ssep(ruleSets map show, line)
    case RuleSet(selector, rules) => ssep(selector map show, ", ") <+> "{" <> nest(line <> vsep(rules map show)) <> line <> "}"
    case Rule(property, value) => property <> ": " <> ssep(value map show, " ") <> ";"
    case UniversalSelector(value) => value
    case AttributeSelector(elementSelector, attribute) => show(elementSelector) <> "[" <> show(attribute) <> "]"
    case ElementSelector(name) => name
    case ClassSelector(name) => name
    case IdSelector(name) => name
    case ElementPseudoClassSelector(elementSelector, pseudoClass) => show(elementSelector) <> ":" <> show(pseudoClass)
    case NegationPseudoClassSelector(simpleSelector: SimpleSelector) => ":not(" <> show(simpleSelector) <> ")"
    case SimpleStructuralPseudoClass(name) => name
    case ComplexStructuralPseudoClass(name, value) => name <> "(" <> value <> ")"
    case LinkPseudoClass(name) => name
    case UserActionPseudoClass(name) => name
    case TargetPseudoClass(name) => name
    case LangPseudoClass(lang) => "lang(" <> lang <> ")"
    case UiElementStatePseudoClass(state) => state
    case Attribute(name, None, value) => name <> "=" <> value
    case Attribute(name, Some(operator), value) => name <> operator <> value
    case dimension @ Dimension(value, unit) => value <> unit <>  (dimension.delimiter match {
      case None => ""
      case Some(delimiter: String) => "" <> delimiter
    })
    case color @ Color(value) => value <> (color.delimiter match {
      case None => ""
      case Some(delimiter: String) => "" <> delimiter
    })
    case stringValue @ StringValue(value) => value <>  (stringValue.delimiter match {
      case None => ""
      case Some(delimiter: String) => "" <> delimiter
    })
    case zeroValue @ ZeroValue(value) => value <>  (zeroValue.delimiter match {
      case None => ""
      case Some(delimiter: String) => "" <> delimiter
    })
    case PseudoElementSelector(elementSelector, pseudoElement) => show(elementSelector) <> "::" <> show(pseudoElement)
    case PseudoElement(name) => name
    case DescendantCombinator(selectorLeft, selectorRight) => show(selectorLeft) <> " " <> show(selectorRight)
    case ChildCombinator(selectorLeft, selectorRight) => show(selectorLeft) <> ">" <> show(selectorRight)
    case AdjacentCombinator(selectorLeft, selectorRight) => show(selectorLeft) <> "+" <> show(selectorRight)
    case GeneralSiblingCombinator(selectorLeft, selectorRight) => show(selectorLeft) <> "~" <> show(selectorRight)
  }


}
