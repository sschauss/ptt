package de.unikoblenz.ptt.lord.ass02

import org.kiama.output.PrettyPrinter
import de.unikoblenz.ptt.lord.ass02.ast._
import de.unikoblenz.ptt.lord.ass02.ast.pseudoclass._
import de.unikoblenz.ptt.lord.ass02.ast.selector._
import de.unikoblenz.ptt.lord.ass02.ast.value._

object PrettyPrinter extends PrettyPrinter {

  def pretty(node: Node): String = super.pretty(show(node))

  def show(node: Node): Doc = node match {
    case Sass(ruleSets) => ssep(ruleSets map show, line)
    case RuleSet(selector, rules) => ssep(selector map show, ", ") <+> "{" <> nest(line <> vsep(rules map show)) <> line <> "}"
    case Rule(property, valueGroup) => property <> ": " <> ssep(valueGroup map show, ", ") <> ";"
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
    case ValueGroup(values) => ssep(values map show, " ")
    case Dimension(value, unit) => value <> unit
    case RgbColor(r, g, b) => "rgb(" <> r <> ", " <> g <> ", " <> b <> ")"
    case RgbaColor(r, g, b, a) => "rgba(" <> r <> ", " <> g <> "," <> b <> ", " <> a <> ")"
    case HslColor(h, s, l) => "hsl(" <> h <> ", " <> s <> ", " <> l <> ")"
    case HslaColor(h, s, l, a) => "hsla(" <> h <> ", " <> s <> ", " <> l <> ", " <> a <> ")"
    case HexColor(value) => value
    case NamedColor(value) => value
    case StringValue(value) => value
    case ZeroValue(value) => value
    case PseudoElementSelector(elementSelector, pseudoElement) => show(elementSelector) <> "::" <> show(pseudoElement)
    case PseudoElement(name) => name
    case DescendantCombinator(selectorLeft, selectorRight) => show(selectorLeft) <> " " <> show(selectorRight)
    case ChildCombinator(selectorLeft, selectorRight) => show(selectorLeft) <> ">" <> show(selectorRight)
    case AdjacentCombinator(selectorLeft, selectorRight) => show(selectorLeft) <> "+" <> show(selectorRight)
    case GeneralSiblingCombinator(selectorLeft, selectorRight) => show(selectorLeft) <> "~" <> show(selectorRight)
  }


}
