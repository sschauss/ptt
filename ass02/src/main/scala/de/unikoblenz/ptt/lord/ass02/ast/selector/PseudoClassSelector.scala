package de.unikoblenz.ptt.lord.ass02.ast.selector

import de.unikoblenz.ptt.lord.ass02.ast.pseudoclass.PseudoClass

trait PseudoClassSelector extends SimpleSelector

case class NegationPseudoClassSelector(simpleSelector: SimpleSelector) extends PseudoClassSelector

case class ElementPseudoClassSelector(elementSelector: ElementSelector, pseudoClass: PseudoClass) extends PseudoClassSelector
