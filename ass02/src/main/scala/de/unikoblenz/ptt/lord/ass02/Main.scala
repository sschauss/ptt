package de.unikoblenz.ptt.lord.ass02


object Main extends App {

  val scss =
    """
      |div {
      |    width: 10px;
      |    a:visited, a:focus, a:hover {
      |        color: black;
      |        i {
      |            color: red;
      |            i, a {
      |                color: black;
      |            }
      |        }
      |    }
      |}
    """.stripMargin

  val scssAst = Parser.parse(scss, Parser.parser)
  val cssAst = Transformer.transform(scssAst)
  println(scssAst)
  println("Pretty SCSS")
  println(PrettyPrinter.pretty(scssAst))
  println("Pretty CSS")
  println(PrettyPrinter.pretty(cssAst))
}