package de.unikoblenz.ptt.lord.ass02.ast.value

import de.unikoblenz.ptt.lord.ass02.ast.Node

trait Value extends Node

case class ValueGroup(values: List[Value]) extends Value

case class Dimension(value: String, unit: String) extends Value

case class ZeroValue(value: String) extends Value

case class StringValue(value: String) extends Value