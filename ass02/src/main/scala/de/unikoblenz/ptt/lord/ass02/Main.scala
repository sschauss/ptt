package de.unikoblenz.ptt.lord.ass02

/**
 * Created by simon on 19/05/14.
 */
object Main extends App {

  val parser = new Parser

  val result = parser.parse(
    """
      |.content {
      | height: 100px;
      | div {
      |   border: 1px solid black;
      | }
      |}
      |""".stripMargin)

  print(result)

}
