package de.unikoblenz.ptt.lord.ass02.ast.selector

import de.unikoblenz.ptt.lord.ass02.ast.{Node, PseudoElement}


trait Selector extends Node

case class PseudoElementSelector(elementSelector: SimpleSelector, pseudoElement: PseudoElement) extends Selector
