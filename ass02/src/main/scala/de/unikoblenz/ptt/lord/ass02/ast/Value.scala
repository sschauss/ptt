package de.unikoblenz.ptt.lord.ass02.ast

trait Value extends Node

case class ValueGroup(values: List[Value]) extends Value

case class StringValue(value: String) extends Value

case class VariableValue(value: String) extends Value