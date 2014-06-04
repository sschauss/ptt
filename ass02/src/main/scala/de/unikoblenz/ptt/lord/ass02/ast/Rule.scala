package de.unikoblenz.ptt.lord.ass02.ast

trait Rule extends Node

case class RuleSet(selectorGroup: SelectorGroup, rules: List[Node]) extends Rule

case class Declaration(property: String, valueGroups: List[Node]) extends Rule

case class Include(name: String, parameters: Option[List[Value]]) extends Rule

case class Extend(selector: Selector) extends Rule
