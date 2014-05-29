package de.unikoblenz.ptt.lord.ass02.ast.selector

import de.unikoblenz.ptt.lord.ass02.ast.pseudoclass.PseudoClass
import de.unikoblenz.ptt.lord.ass02.ast.PseudoElement

trait PseudoSelector extends SimpleSelector

case class PseudoClassSelector(pseudoClass: PseudoClass) extends PseudoSelector

case class PseudoElementSelector(pseudoElement: PseudoElement) extends PseudoSelector
