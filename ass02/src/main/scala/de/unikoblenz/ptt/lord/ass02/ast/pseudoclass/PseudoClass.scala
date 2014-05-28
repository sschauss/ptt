package de.unikoblenz.ptt.lord.ass02.ast.pseudoclass

import de.unikoblenz.ptt.lord.ass02.ast.Node

trait PseudoClass extends Node

case class LinkPseudoClass(name: String) extends PseudoClass

case class UserActionPseudoClass(name: String) extends PseudoClass

case class LangPseudoClass(lang: String) extends PseudoClass

case class TargetPseudoClass(name: String) extends PseudoClass

case class UiElementStatePseudoClass(state: String) extends PseudoClass

