package de.unikoblenz.ptt.lord.ass02.ast

case class RuleSet(selectorGroup: SelectorGroup, rules: List[Node]) extends Node

case class Declaration(property: String, valueGroups: List[Node]) extends Node

case class Include(name: String, parameters: Option[List[Node]]) extends Node


