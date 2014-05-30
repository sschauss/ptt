package de.unikoblenz.ptt.lord.ass02


object Main extends App {

  val css =
    """
      |div#container, div:not([*|href='test']) {
      |  color: red;
      |  span {
      |    color: green;
      |  }
      |}
      |
      |div p:nth-child(-2a+bas+3) {background: red green, 20px;}
      |
      |:nth-child(2), a[href="test"] {background: grey;}
    """.stripMargin



  println(css)
  val ast = Parser.parse(css, Parser.parser)
  println(ast)
  println(PrettyPrinter.pretty(ast))

}