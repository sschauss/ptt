package de.unikoblenz.ptt.lord.ass02

import org.kiama.attribution.Attributable


package object Ast {


  trait Node extends Attributable

  case class Selector(tag: String, rules: List[Node]) extends Node

  case class Rule(attribute: String, value: String) extends Node


}
