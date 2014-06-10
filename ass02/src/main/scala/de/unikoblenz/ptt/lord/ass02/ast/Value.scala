package de.unikoblenz.ptt.lord.ass02.ast

case class ValueGroup(values: List[Node]) extends Node

case class Value(value: String) extends Node

case class VariableValue(name: String) extends Node


case class DimensionedValue(value: Double, dimension: Option[String]) extends Node

case class Expression(term: Term, operations: List[Node]) extends Node
case class Term(factor: Node, operations: List[Node]) extends Node

case class Multiplication(factor: Node) extends Node
case class Division(divisor: Node) extends Node
case class Addition(summand: Term) extends Node
case class Subtraction(subtrahend: Term) extends Node

case class NegativeExpression(expression: Expression) extends Node