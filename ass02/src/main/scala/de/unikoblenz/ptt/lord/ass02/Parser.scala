package de.unikoblenz.ptt.lord.ass02

import org.kiama.util.PositionedParserUtilities

class Parser extends PositionedParserUtilities {

  def parse(input: String): Any = parse(input, parser)

  def parse(input: String, parser: Parser[Any]): Any = parseAll(parser, input) match {
    case Success(sass, _) => sass
    case Failure(message, _) => throw new Exception(message)
    case Error(message, _) => throw new Exception(message)
  }

  lazy val parser: PackratParser[Any] = phrase(sass)


  lazy val sass: PackratParser[Any] = ruleSets



  lazy val ruleSets: PackratParser[Any] = rep(selector <~ "{}")



  lazy val selector: PackratParser[Any] =
    combinator |
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
    "nth-child(" ~ integer ~ ")" |
    "nth-last-child(" ~ integer ~ ")" |
    "nth-of-type(" ~ integer ~ ")" |
    "nth-last-of-type(" ~ integer ~ ")" |
    "first-child" |
    "last-child" |
    "first-of-type" |
    "last-of-type" |
    "only-child" |
    "only-of-type" |
    "empty"

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



  lazy val combinator: PackratParser[Any] =
    descendantCombinator |
    childCombinator |
    adjacentCombinator |
    generalSiblingCombinator

  lazy val descendantCombinator: PackratParser[Any] = selector ~ selector

  lazy val childCombinator: PackratParser[Any] = selector ~ (">" ~> selector)

  lazy val adjacentCombinator: PackratParser[Any] = selector ~ ("+" ~> selector)

  lazy val generalSiblingCombinator: PackratParser[Any] = selector ~ ("~" ~> selector)



  lazy val name: Parser[Any] = "[a-zA-Z]+".r

  lazy val integer: Parser[Any] = "[0]|[1-9][0-9]*".r

}
