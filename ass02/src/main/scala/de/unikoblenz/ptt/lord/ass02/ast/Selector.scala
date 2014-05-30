package de.unikoblenz.ptt.lord.ass02.ast

trait Selector extends Node

case class SelectorGroup(selectorSequences: List[SelectorSequence]) extends Node
case class SelectorSequence(selector: Selector, selectorsCombinations: List[SelectorCombination]) extends Node
case class SelectorCombination(operator: String, selectorSequence: SelectorSequence) extends Node
case class ClassSelector(className: String) extends Selector
case class IdSelector(idName: String) extends Selector
case class TypeSelector(nameSpacePrefix: Option[NameSpacePrefix], typeName: String) extends Selector
case class NameSpacePrefix(nameSpaceName: Option[String]) extends Node
case class UniversalSelector(nameSpacePrefix: Option[NameSpacePrefix]) extends Selector
case class AttributeSelector(nameSpacePrefix: Option[NameSpacePrefix], attributeName: String, attribute: Option[Attribute]) extends Selector
case class Attribute(attributeOperator: String, attributeValue: String) extends Node
case class PseudoClassSelector(pseudoClassName: String, pseudoClassExpression: Option[String]) extends Selector
case class PseudoElementSelector(pseudoElementName: String) extends Selector
case class NotSelector(selector: Selector) extends Selector