package de.unikoblenz.ptt.lord.ass02.ast.selector

trait SelectorCombinator extends Selector

case class DescendantCombinator(selectorLeft: SimpleSelector, selectorRight: SimpleSelector) extends SelectorCombinator

case class ChildCombinator(selectorLeft: SimpleSelector, selectorRight: SimpleSelector) extends SelectorCombinator

case class AdjacentCombinator(selectorLeft: SimpleSelector, selectorRight: SimpleSelector) extends SelectorCombinator

case class GeneralSiblingCombinator(selectorLeft: SimpleSelector, selectorRight: SimpleSelector) extends SelectorCombinator
