package de.unikoblenz.ptt.lord.ass02

import de.unikoblenz.ptt.lord.ass02.Ast.Sass


/**
 * Created by simon on 19/05/14.
 */
object Main extends App {

  val parser = Parser


  val test = """
    |*{}
    |test{}
    |test[foo="bar"]{}
    |test[foo~="bar"]{}
    |test[foo="bar"]{}
    |test[foo$="bar"]{}
    |test[foo*="bar"]{}
    |test[foo|="bar"]{}
    |test:root{}
    |test:nth-child(even){}
    |test:nth-child(odd){}
    |test:nth-child(2){}
    |test:nth-child(2n+3){}
    |test:nth-child(2n-3){}
    |test:nth-child(-2n+3){}
    |test:nth-child(-2n-3){}
    |test:nth-last-child(1){}
    |test:nth-of-type(1){}
    |test:nth-last-of-type(1){}
    |test:first-child{}
    |test:last-child{}
    |test:first-of-type{}
    |test:last-of-type{}
    |test:only-child{}
    |test:only-of-type{}
    |test:empty{}
    |test:link{}
    |test:visited{}
    |test:active{}
    |test:hover{}
    |test:focus{}
    |test:target{}
    |test:lang(de){}
    |test:enabled{}
    |test:disabled{}
    |test:checked{}
    |test::first-line{}
    |test::first-letter{}
    |test::before{}
    |test::after{}
    |.test{}
    |#test{}
    |:not(test){}
    |test test{}
    |test > test{}
    |test + test{}
    |test ~ test{}
  """.stripMargin

  val result: Sass = parser.parse(
    """
      |nav {
      |  ul {
      |    margin: 0;
      |    padding: 0;
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

  printList(result.ruleSets)

  def printList(list: List[Any]): Unit  = list match {
    case (x::xs) => x match {
      case x: List[Any] => printList(x)
      case x: Any => println(x)
    }
  }



}
