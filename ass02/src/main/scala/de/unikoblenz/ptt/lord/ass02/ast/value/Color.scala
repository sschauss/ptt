package de.unikoblenz.ptt.lord.ass02.ast.value


trait Color extends Value

case class NamedColor(value: String) extends Color

case class HexColor(value: String) extends Color

case class RgbColor(r: String, g:String, b:String) extends Color

case class RgbaColor(r: String, g:String, b:String, a:String) extends Color

case class HslColor(h: String, s:String, l:String) extends Color

case class HslaColor(h: String, s:String, l:String, a:String) extends Color