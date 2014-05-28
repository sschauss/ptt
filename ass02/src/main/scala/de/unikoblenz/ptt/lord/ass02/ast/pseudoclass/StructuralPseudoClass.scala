package de.unikoblenz.ptt.lord.ass02.ast.pseudoclass

trait StructuralPseudoClass extends PseudoClass

case class SimpleStructuralPseudoClass(name: String) extends StructuralPseudoClass

case class ComplexStructuralPseudoClass(name: String, value: String) extends StructuralPseudoClass