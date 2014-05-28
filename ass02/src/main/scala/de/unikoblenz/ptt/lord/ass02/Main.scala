package de.unikoblenz.ptt.lord.ass02

import de.unikoblenz.ptt.lord.ass02.ast.Sass


/**
 * Created by simon on 19/05/14.
 */
object Main extends App {

  val parser = Parser

  val sass: Sass = parser.parse(
    """
      |nav,              div {
      |  box-shadow: 8px 8px 8px red,
      |               -8px 8px 8px hsla(12,100%,4%,0.4),
      |                8px -8px 8px blue,
      |               -8px -8px 8px #23FF00;
      |  ul, ol {
      |    margin: 0;
      |           padding: 0;
      |    list-style: none;
      |  }
      |
      |  li { display: inline-block; }
      |
      |  a {
      |    color: rgba(0,128,255,0.3);
      |    background-color: yelLxow;
      |    display: block;
      |    padding: 6px 12px;
      |    text-decoration: none;
      |  }
      |}
    """.stripMargin)

  println(sass)

  println(PrettyPrinter.pretty(sass))


}
