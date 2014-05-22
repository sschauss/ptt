package de.unikoblenz.ptt.lord.ass02

import org.kiama.util.PositionedParserUtilities

import de.unikoblenz.ptt.lord.ass02.Ast._

class Parser extends PositionedParserUtilities {

  def parse(input: String) = parseAll(parser, input) match {
    case Success(sass, _) => sass
    case Failure(message, _) => throw new Exception(message)
    case Error(message, _) => throw new Exception(message)
  }

  lazy val parser = phrase(root)

  lazy val root: Parser[Node] = selector

  lazy val selector: Parser[Selector] = tag ~ ("{" ~> rep(rule) <~ "}") ^^ Selector

  lazy val tag: Parser[String] = "(#|.)?[a-zA-Z0-9-_]*".r

  lazy val rule: Parser[Node] = attribute ~ (":" ~> value <~ ";") ^^ Rule | selector

  lazy val attribute: Parser[String] = "[a-zA-Z0-9]*".r

  lazy val value: Parser[String] = "[a-zA-Z0-9%,\\s]*".r

}
