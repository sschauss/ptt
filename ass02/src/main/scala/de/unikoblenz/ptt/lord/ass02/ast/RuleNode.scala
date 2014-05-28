package de.unikoblenz.ptt.lord.ass02.ast

import de.unikoblenz.ptt.lord.ass02.ast.selector.Selector
import de.unikoblenz.ptt.lord.ass02.ast.value.ValueGroup

trait RuleNode extends Node

case class RuleSet(selector: List[Selector], rules: List[RuleNode]) extends RuleNode

case class Rule(property: String, value: List[ValueGroup], important: Option[String]) extends RuleNode