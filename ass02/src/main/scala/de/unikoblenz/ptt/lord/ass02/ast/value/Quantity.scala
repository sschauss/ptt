package de.unikoblenz.ptt.lord.ass02.ast.value

trait Quantity extends Value

case class Percent(value: Float) extends Quantity

case class In(value: Float) extends Quantity

case class Cm(value: Float) extends Quantity

case class Mm(value: Float) extends Quantity

case class Em(value: Float) extends Quantity

case class Ex(value: Float) extends Quantity

case class Pt(value: Float) extends Quantity

case class Pc(value: Float) extends Quantity

case class Px(value: Float) extends Quantity

case class Zero() extends Quantity



