package de.unikoblenz.ptt.lord.ass02.ast


case class RuleSet(selectorGroup: SelectorGroup, rules: List[Rule]) extends Node

case class Rule(property: String, valueGroups: List[ValueGroup]) extends Node