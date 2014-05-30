package de.unikoblenz.ptt.lord.ass02.ast

case class Value(value: String) extends Node

case class ValueGroup(values: List[Value]) extends Node