package de.unikoblenz.ptt.lord.ass02

package object Ast {

  case class Sass(ruleSets: List[RuleSet])

  case class RuleSet(selector: List[Selector], rules: List[Any])

  case class Rule(property: String, value: List[Value])

  trait Selector extends Serializable

  trait SelectorCombinator extends Selector

  trait SimpleSelector extends Selector

  trait PseudoClassSelector extends SimpleSelector

  trait PseudoClass

  trait Value

  trait StructuralPseudoClass extends PseudoClass

  case class UniversalSelector(name: String) extends SimpleSelector

  case class AttributeSelector(elementSelector: ElementSelector, attribute: Attribute) extends SimpleSelector

  case class ElementSelector(name: String) extends SimpleSelector

  case class ClassSelector(name: String) extends SimpleSelector

  case class IdSelector(name: String) extends SimpleSelector

  case class ElementPseudoClassSelector(elementSelector: ElementSelector, pseudoClass: PseudoClass) extends PseudoClassSelector

  case class NegationPseudoClassSelector(simpleSelector: SimpleSelector) extends PseudoClassSelector

  case class SimpleStructuralPseudoClass(name: String) extends StructuralPseudoClass

  case class ComplexStructuralPseudoClass(name: String, value: String) extends StructuralPseudoClass

  case class LinkPseudoClass(name: String) extends PseudoClass

  case class UserActionPseudoClass(name: String) extends PseudoClass

  case class TargetPseudoClass(name: String) extends PseudoClass

  case class LangPseudoClass(lang: String) extends PseudoClass

  case class UiElementStatePseudoClass(state: String) extends PseudoClass

  case class Attribute(name: String, operator: Option[String], value: String)

  case class Dimension(value: String, unit: String) extends Value

  case class Color(value: String) extends Value

  case class StringValue(value: String) extends Value

  case class PseudoElementSelector(elementSelector: SimpleSelector, pseudoElement: PseudoElement) extends Selector

  case class PseudoElement(name: String)

  case class DescendantCombinator(selectorLeft: Selector, selectorRight: Selector) extends SelectorCombinator

  case class ChildCombinator(selectorLeft: Selector, selectorRight: Selector) extends SelectorCombinator

  case class AdjacentCombinator(selectorLeft: Selector, selectorRight: Selector) extends SelectorCombinator

  case class GeneralSiblingCombinator(selectorLeft: Selector, selectorRight: Selector) extends SelectorCombinator

}
