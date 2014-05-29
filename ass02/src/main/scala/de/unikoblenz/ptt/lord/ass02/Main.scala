package de.unikoblenz.ptt.lord.ass02


object Main extends App {

  val parser = Parser

  val parse = """  div[a|foo=asd] div{}""".stripMargin

  println(parse)

  val sass = parser.parse(parse, parser.parser)

  println(sass)


}
