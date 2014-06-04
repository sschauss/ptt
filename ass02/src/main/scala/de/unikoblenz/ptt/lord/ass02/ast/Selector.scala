package de.unikoblenz.ptt.lord.ass02.ast

case class SelectorGroup(selectorSequences: List[SelectorSequence]) extends Node
case class SelectorSequence(selector: Node, selectorsCombination: Option[SelectorCombination]) extends Node
case class SelectorCombination(operator: String, selectorSequence: SelectorSequence) extends Node
case class ClassSelector(className: String) extends Node
case class IdSelector(idName: String) extends Node
case class TypeSelector(nameSpacePrefix: Option[NameSpacePrefix], typeName: String) extends Node
case class NameSpacePrefix(nameSpaceName: Option[String]) extends Node
case class UniversalSelector(nameSpacePrefix: Option[NameSpacePrefix]) extends Node
case class AttributeSelector(nameSpacePrefix: Option[NameSpacePrefix], attributeName: String, attribute: Option[Attribute]) extends Node
case class Attribute(attributeOperator: String, attributeValue: String) extends Node
case class PseudoClassSelector(pseudoClassName: String, pseudoClassExpression: Option[String]) extends Node
case class PseudoElementSelector(pseudoElementName: String) extends Node
case class NotSelector(selector: Node) extends Node