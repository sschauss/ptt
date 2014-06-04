package de.unikoblenz.ptt.lord.ass02.ast

trait Value extends Node

case class ValueGroup(values: List[Node]) extends Value

case class StringValue(value: String) extends Value

case class VariableValue(name: String) extends Value