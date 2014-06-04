package de.unikoblenz.ptt.lord.ass02.ast

case class ValueGroup(values: List[Node]) extends Node

case class Value(value: String) extends Node

case class VariableValue(name: String) extends Node