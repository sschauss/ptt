package de.unikoblenz.ptt.lord.ass02


object Main extends App {

//  val scss =
//    """
//      |$a: 60px;
//      |$test: 12px, $a;
//      |div {
//      |    $test: 13px;
//      |    width: 10px + 10px;
//      |    height: $test;
//      |    a:visited, a:focus, a:hover {
//      |        $test: 100px, $a;
//      |        color: 1px $test 12px $test, 10px $test $test;
//      |        i {
//      |            color: red;
//      |            i, a {
//      |                color: black;
//      |            }
//      |        }
//      |    }
//      |}
//    """.stripMargin
//
//  val scssAst = Parser.parse(scss, Parser.parser)
//  val cssAst = Transformer.transform(scssAst)
//  println(scssAst)
//  println("Pretty SCSS")
//  println(PrettyPrinter.pretty(scssAst))
//  println("Pretty CSS")
//  println(PrettyPrinter.pretty(cssAst))

  val op = "-(-(2px--3px)*7)/-2+4px-3px"
  val opAst = Parser.parse(op, Parser.expression)
  println(opAst)
  val calc = Transformer.calculateExpression(opAst)
  println(calc)
}