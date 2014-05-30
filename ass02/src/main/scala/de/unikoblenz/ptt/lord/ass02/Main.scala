package de.unikoblenz.ptt.lord.ass02


object Main extends App {


  val result = Parser.parse(
    """
      | div#container, div:not([*|href='test']) {
      |
      | }
      | *|div, a {
      |   color: green;
      |}
    """.stripMargin, Parser.parser)


  println(result)
  println(PrettyPrinter.pretty(result))

}