package de.unikoblenz.ptt.lord.ass02

import org.kiama.util.PositionedParserUtilities
import de.unikoblenz.ptt.lord.ass02.Ast._

object Parser extends PositionedParserUtilities {

  def parse(input: String): Any = parse(input, parser)

  def parse[T](input: String, parser: Parser[T]): T = parseAll(parser, input) match {
    case Success(sass, _) => sass
    case Failure(message, _) => throw new Exception(message)
    case Error(message, _) => throw new Exception(message)
  }

  lazy val parser: PackratParser[Any] = phrase(sass)


  lazy val sass: PackratParser[Any] = ruleSets



  lazy val ruleSets: PackratParser[Any] = rep(selector ~ rep("," ~ selector) ~ ("{" ~> rep(rule) <~ "}"))

  lazy val rule: PackratParser[Any] = property ~ (":" ~> rep1(value <~ (","?)) <~ ";")



  lazy val property: Parser[Any] = name



  lazy val value: PackratParser[Any] =   color | unit | time | name



  lazy val selector: PackratParser[Any] =
    selectorCombinator |
    pseudoElementSelector |
    simpleSelector



  lazy val simpleSelector: PackratParser[Any] =
    pseudoClassSelector |
    attributeSelector |
    universalSelector |
    elementSelector |
    classSelector |
    idSelector



  lazy val universalSelector: Parser[Any] = "*"



  lazy val elementSelector: PackratParser[Any] = name



  lazy val attributeSelector: PackratParser[Any] = elementSelector ~ ("[" ~> attribute <~ "]")

  lazy val attribute: PackratParser[Any] = name ~ ((("~" | "^" | "$" | "*" | "|")?) <~ "=") ~ ("\"" ~> name <~ "\"")



  lazy val pseudoClassSelector: PackratParser[Any] =
    elementSelector ~ (":" ~> pseudoClass) |
    negationPseudoClassSelector

  lazy val pseudoClass: PackratParser[Any] =
    structuralPseudoClass |
    linkPseudoClass |
    userActionPseudoClass |
    targetPseudoClass |
    langPseudoClass |
    uiElementStatePseudoClass

  lazy val structuralPseudoClass: PackratParser[Any] =
    "root" |
    "nth-child" ~ ("(" ~> nth <~ ")") |
    "nth-last-child" ~ ("(" ~> nth <~ ")") |
    "nth-of-type" ~ ("(" ~> nth <~ ")") |
    "nth-last-of-type" ~ ("(" ~> nth <~ ")") |
    "first-child" |
    "last-child" |
    "first-of-type" |
    "last-of-type" |
    "only-child" |
    "only-of-type" |
    "empty"

  lazy val nth: PackratParser[Any] = "odd" | "even" | signedInteger | signedInteger ~ "n" ~ signedInteger

  lazy val linkPseudoClass: Parser[Any] = "link"

  lazy val userActionPseudoClass: PackratParser[Any] =
    "visited" |
    "active" |
    "hover" |
    "focus"

  lazy val targetPseudoClass: Parser[Any] = "target"

  lazy val langPseudoClass: PackratParser[Any] = "lang(" ~> name <~ ")"

  lazy val uiElementStatePseudoClass: PackratParser[Any] = "enabled" | "disabled" | "checked"


  lazy val pseudoElementSelector: PackratParser[Any] = elementSelector ~ ("::" ~> pseudoElement)

  lazy val pseudoElement: PackratParser[Any] =
    firstLinePseudoElement |
    firstLetterPseudoElement |
    beforePseudoElement |
    afterPseudoElement

  lazy val firstLinePseudoElement: Parser[Any] = "first-line"

  lazy val firstLetterPseudoElement: Parser[Any] = "first-letter"

  lazy val beforePseudoElement: Parser[Any] = "before"

  lazy val afterPseudoElement:Parser[Any] = "after"



  lazy val classSelector: PackratParser[Any] = "." ~> name



  lazy val idSelector: PackratParser[Any] = "#" ~> name



  lazy val negationPseudoClassSelector: PackratParser[Any] = ":not(" ~> simpleSelector <~ ")"



  lazy val selectorCombinator: PackratParser[Any] =
    descendantCombinator |
    childCombinator |
    adjacentCombinator |
    generalSiblingCombinator

  lazy val descendantCombinator: PackratParser[Any] = selector ~ selector

  lazy val childCombinator: PackratParser[Any] = selector ~ (">" ~> selector)

  lazy val adjacentCombinator: PackratParser[Any] = selector ~ ("+" ~> selector)

  lazy val generalSiblingCombinator: PackratParser[Any] = selector ~ ("~" ~> selector)



  lazy val unit: Parser[Any] = (decimal ~ ("in" | "cm" | "mm" | "em" | "ex" | "pt" | "pc" | "px")) | percent

  lazy val name: Parser[Any] = "[a-zA-Z]+".r

  lazy val integer: Parser[Any] = "[0]|[1-9][0-9]*".r

  lazy val signedInteger: PackratParser[Any] = (("+" | "-") ?) ~ integer

  lazy val decimal: Parser[Any] = "0|-?[1-9][0-9]*(.[0-9])?".r

  lazy val time: Parser[Any] = decimal ~ "s|ms"

  lazy val color: Parser[Any] = name | "#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})".r

  lazy val percent: Parser[Any] = "(100|[1-9][0-9]?)%".r

}
