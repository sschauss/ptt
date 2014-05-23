package de.unikoblenz.ptt.lord.ass02

import org.kiama.util.PositionedParserUtilities

import de.unikoblenz.ptt.lord.ass02.Ast._

class Parser extends PositionedParserUtilities {

  def parse(input: String) = parseAll(parser, input) match {
    case Success(sass, _) => sass
    case Failure(message, next) => {
      print(next.source)
      print(message)
    }
    case Error(message, next) => {
      print(next.source)
      print(message)
    }
  }

  lazy val parser = phrase(nodes)

  lazy val nodes: Parser[List[Node]] = rep(node)

  lazy val node: Parser[Node] = variable | selector | comment | mixin

  lazy val comment: Parser[Comment] = "//" ~>  "[\\S \t]*".r <~ "[\\s]*".r  ^^ Comment

  lazy val mixin: Parser[Mixin] = "@mixin" ~> mixinName ~ mixinParameters ~ mixinBody  ^^ Mixin

  lazy val mixinBody: Parser[List[Node]] = "{" ~> rep(rule) <~ "}"

  lazy val mixinParameters: Parser[List[String]] = "(" ~> rep(variableName) <~ ")"

  lazy val mixinName: Parser[String] = "[-_a-zA-Z0-9]+".r

  lazy val selector: Parser[Selector] = selectorName ~ ("{" ~> rep(rule) <~ "}") ^^ Selector

  lazy val variable: Parser[Variable] = variableName ~ (":" ~> value <~ ";") ^^ Variable

  lazy val rule: Parser[Node] = ruleName ~ (":" ~> value <~ ";") ^^ Rule

  lazy val selectorName: Parser[String] = "(#|.)?[-_a-zA-Z]+".r

  lazy val variableName: Parser[String] = "$" ~> "[-_a-zA-Z]+".r

  lazy val ruleName: Parser[String] = "[-_a-zA-Z0-9]+".r

  lazy val value: Parser[String] = "[$a-zA-Z0-9\\s]+".r

}
