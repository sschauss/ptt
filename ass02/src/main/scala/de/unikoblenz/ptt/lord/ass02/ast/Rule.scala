package de.unikoblenz.ptt.lord.ass02.ast

trait Rule extends Node

case class RuleSet(selectorGroup: SelectorGroup, rules: List[Rule]) extends Rule

case class Property(property: String, valueGroups: List[ValueGroup]) extends Rule