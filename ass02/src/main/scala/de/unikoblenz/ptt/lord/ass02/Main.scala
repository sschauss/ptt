package de.unikoblenz.ptt.lord.ass02

import de.unikoblenz.ptt.lord.ass02.Ast._


/**
 * Created by simon on 19/05/14.
 */
object Main extends App {

  val parser = Parser

  val sass: Sass = parser.parse(
    """
      |nav,              div {
      |  box-shadow: 8px 8px 8px red,
      |               -8px 8px 8px green,
      |                8px -8px 8px blue,
      |               -8px -8px 8px yellow;
      |  ul, ol {
      |    margin: 0;
      |           padding: 0;
      |    list-style: none;
      |  }
      |
      |  li { display: inline-block; }
      |
      |  a {
      |    display: block;
      |    padding: 6px 12px;
      |    text-decoration: none;
      |  }
      |}
    """.stripMargin)

  println(PrettyPrinter.pretty(sass))


}
