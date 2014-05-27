package de.unikoblenz.ptt.lord.ass02


package object Ast {

  case class Sass(ruleSets: List[RuleSet])

  case class RuleSet(selector: Selector, rules: List[Rule])

  case class Rule(property: String, value: List[String])

  trait Selector

  trait SelectorCombinator extends Selector

  trait PseudoElementSelector extends Selector

  trait SimpleSelector extends Selector

  trait PseudoClassSelector extends SimpleSelector

  trait PseudoClass

  trait StructuralPseudoClass extends PseudoClass

  trait Nth

  case class UniversalSelector(name: String) extends SimpleSelector

  case class AttributeSelector(name: String, attribute: Attribute) extends SimpleSelector

  case class ElementSelector(name: String) extends SimpleSelector

  case class ClassSelector(name: String) extends SimpleSelector

  case class IdSelector(name: String) extends SimpleSelector

  case class SimpleStructuralPseudoClass(name: String) extends StructuralPseudoClass

  case class ComplexStructuralPseudoClass(name: String, value: String) extends StructuralPseudoClass

  case class NthFinal()

  case class Attribute(name: String, operator: Option[String], value: String)

}
