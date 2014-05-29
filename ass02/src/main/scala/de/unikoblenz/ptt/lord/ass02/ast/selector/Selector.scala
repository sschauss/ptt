package de.unikoblenz.ptt.lord.ass02.ast.selector

import de.unikoblenz.ptt.lord.ass02.ast.{Node, PseudoElement}


case class SimpleSelectorSequence(selector: Option[SimpleSelector], selectors: List[SimpleSelector]) extends Node

case class Selector(simpleSelectorSequence: SimpleSelectorSequence, simpleSelectorSequences: List[(Combinator, SimpleSelectorSequence)]) extends Node
