package de.unikoblenz.ptt.lord.ass02


object Main extends App {

  val scss =
    """
      |$test: 10px;
      |div {
      | width: 10px;
      | a:visited, a:focus, a:hover {
      |   color: black;
      |   i {
      |     display: block;
      |   }
      | }
      |}
    """.stripMargin

  val scssAst = Parser.parse(scss, Parser.parser)
  //println("AST", ast
  val cssAst = SCSSConverter.convert(scssAst)
  println("Pretty SCSS")
  println(PrettyPrinter.pretty(scssAst))
  println()
  println("Pretty CSS")
  println(PrettyPrinter.pretty(cssAst))

}