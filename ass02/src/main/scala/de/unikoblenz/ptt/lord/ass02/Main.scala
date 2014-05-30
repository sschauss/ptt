package de.unikoblenz.ptt.lord.ass02


object Main extends App {


  val result = Parser.parse("""div#container span, div:not([*|href="test"]) span{}""", Parser.parser)


  println(result)
  println(PrettyPrinter.pretty(result))

}
