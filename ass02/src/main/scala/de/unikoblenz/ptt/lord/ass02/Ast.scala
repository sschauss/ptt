package de.unikoblenz.ptt.lord.ass02

import org.kiama.attribution.Attributable


package object Ast {


  trait Node extends Attributable

  case class Variable(name: String, value: String) extends Node

  case class Selector(name: String, rules: List[Node]) extends Node

  case class Rule(name: String, value: String) extends Node


}
