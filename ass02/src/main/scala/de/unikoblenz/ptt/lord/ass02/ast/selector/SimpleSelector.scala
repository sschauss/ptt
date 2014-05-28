package de.unikoblenz.ptt.lord.ass02.ast.selector

import de.unikoblenz.ptt.lord.ass02.ast.Attribute


trait SimpleSelector extends Selector

case class ClassSelector(name: String) extends SimpleSelector

case class AttributeSelector(elementSelector: ElementSelector, attribute: Attribute) extends SimpleSelector

case class IdSelector(name: String) extends SimpleSelector

case class ElementSelector(name: String) extends SimpleSelector

case class UniversalSelector(name: String) extends SimpleSelector
