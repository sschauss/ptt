package de.unikoblenz.ptt.lord.ass02.ast.selector

import de.unikoblenz.ptt.lord.ass02.ast.Node


trait Combinator extends Node

case class DescendantCombinator() extends Combinator

case class ChildCombinator() extends Combinator

case class AdjacentCombinator() extends Combinator

case class GeneralSiblingCombinator() extends Combinator
