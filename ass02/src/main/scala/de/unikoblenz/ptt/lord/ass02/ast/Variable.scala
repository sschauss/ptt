package de.unikoblenz.ptt.lord.ass02.ast


case class Variable(variableName: VariableName, valueGroups: List[ValueGroup]) extends Node

case class VariableName(name: String) extends Node