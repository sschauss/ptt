package de.unikoblenz.ptt.lord.ass02


object Main extends App {

  val scss =
    """
      |$test: 10px;
      |.menu {
      |  display: none;
      |  position: absolute;
      |  top: 100%;
      |  background: white;
      |  list-style: none;
      |  width: 15em;
      |  padding: 10px 0;
      |}
      |.menu-item[href='test'] {
      |  color: black;
      |  display: block;
      |  padding: 8px 20px;
      |  text-decoration: none;
      |
      |  div { background: #29a7f5; color: $test; }
      |}
    """.stripMargin

  val ast = Parser.parse(scss, Parser.parser)
  println(ast)
  println(PrettyPrinter.pretty(ast))

}