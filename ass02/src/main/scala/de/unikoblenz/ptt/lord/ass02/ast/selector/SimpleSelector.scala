package de.unikoblenz.ptt.lord.ass02.ast.selector

import de.unikoblenz.ptt.lord.ass02.ast.{NameSpacePrefix, Node}


trait SimpleSelector extends Node

case class ClassSelector(ident: String) extends SimpleSelector

case class NegationSelector(simpleSelector: SimpleSelector) extends SimpleSelector

case class AttributeSelector(nameSpacePrefix: Option[NameSpacePrefix], name: String, operator: Option[String], value: String) extends SimpleSelector

case class IdSelector(name: String) extends SimpleSelector

case class TypeSelector(nameSpacePrefix: Option[NameSpacePrefix], name: String) extends SimpleSelector

case class UniversalSelector(name: String) extends SimpleSelector
