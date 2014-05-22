package de.unikoblenz.ptt.lord.ass02

/**
 * Created by simon on 19/05/14.
 */
object Main extends App {

  val parser = new Parser

  val result = parser.parse(
    """
      |$icon-font-path: 123;
      |
      |
      |body {
      |  padding-top: 70px;
      |}
      |
      |.btn {
      |  outline: none;
      |}
      |
      |//Dies ist ein Kommentar
      |
      |.hide-overflow {
      |  overflow: hidden;
      |  white-space: nowrap;
      |  text-overflow: ellipsis;
      |}
      |
      |.navbar-brand {
      |  border: solid 1px black;
      |  background-size: 60px;
      |  width: 90px;
      |}
      |""".stripMargin)

  print(result)

}
