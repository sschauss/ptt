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
    |test:nth-child(1){}
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

  val partialResult: Any = parser.parse(
    """
      |test test
    """.stripMargin, parser.combinator)

  println(partialResult)

  val result: Sass = parser.parse(test)

  println(result)

}
