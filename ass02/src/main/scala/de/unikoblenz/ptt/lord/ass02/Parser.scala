package de.unikoblenz.ptt.lord.ass02

import org.kiama.util.PositionedParserUtilities
import de.unikoblenz.ptt.lord.ass02.Ast._

object Parser extends PositionedParserUtilities {

  def parse(input: String): Sass = parse(input, parser)

  def parse[T](input: String, parser: Parser[T]): T = parseAll(parser, input) match {
    case Success(sass, _) => sass
    case Failure(message, _) => throw new Exception(message)
    case Error(message, _) => throw new Exception(message)
  }

  lazy val parser: PackratParser[Sass] = phrase(sass)

  lazy val sass: PackratParser[Sass] = rep(ruleSet) ^^ Sass

  lazy val ruleSet: PackratParser[RuleSet] = repsep(selector, ",") ~ ("{" ~> rep(ruleSet | rule) <~ "}") ^^ RuleSet

  lazy val rule: PackratParser[Rule] = property ~ (":" ~> rep1(value <~ ("," ?)) <~ ";") ^^ Rule


  lazy val property: PackratParser[String] = name


  lazy val value: PackratParser[Value] = color | dimension | stringValue | zeroValue


  lazy val selector: PackratParser[Selector] =
    selectorCombinator |
      pseudoElementSelector |
      simpleSelector


  lazy val simpleSelector: PackratParser[SimpleSelector] =
    pseudoClassSelector |
      attributeSelector |
      universalSelector |
      elementSelector |
      classSelector |
      idSelector


  lazy val universalSelector: Parser[UniversalSelector] = "*" ^^ UniversalSelector


  lazy val elementSelector: PackratParser[ElementSelector] = name ^^ ElementSelector


  lazy val attributeSelector: PackratParser[AttributeSelector] = elementSelector ~ ("[" ~> attribute <~ "]") ^^ AttributeSelector

  lazy val attribute: PackratParser[Attribute] = name ~ ((("~" | "^" | "$" | "*" | "|") ?) <~ "=") ~ ("\"" ~> name <~ "\"") ^^ Attribute


  lazy val pseudoClassSelector: PackratParser[PseudoClassSelector] =
    elementSelector ~ (":" ~> pseudoClass) ^^ ElementPseudoClassSelector |
      negationPseudoClassSelector

  lazy val pseudoClass: PackratParser[PseudoClass] =
    structuralPseudoClass |
      linkPseudoClass |
      userActionPseudoClass |
      targetPseudoClass |
      langPseudoClass |
      uiElementStatePseudoClass

  lazy val structuralPseudoClass: PackratParser[StructuralPseudoClass] =
    "root" ^^ SimpleStructuralPseudoClass |
      "nth-child" ~ ("(" ~> nth <~ ")") ^^ ComplexStructuralPseudoClass |
      "nth-last-child" ~ ("(" ~> nth <~ ")") ^^ ComplexStructuralPseudoClass |
      "nth-of-type" ~ ("(" ~> nth <~ ")") ^^ ComplexStructuralPseudoClass |
      "nth-last-of-type" ~ ("(" ~> nth <~ ")") ^^ ComplexStructuralPseudoClass |
      "first-child" ^^ SimpleStructuralPseudoClass |
      "last-child" ^^ SimpleStructuralPseudoClass |
      "first-of-type" ^^ SimpleStructuralPseudoClass |
      "last-of-type" ^^ SimpleStructuralPseudoClass |
      "only-child" ^^ SimpleStructuralPseudoClass |
      "only-of-type" ^^ SimpleStructuralPseudoClass |
      "empty" ^^ SimpleStructuralPseudoClass

  lazy val linkPseudoClass: Parser[LinkPseudoClass] = "link" ^^ LinkPseudoClass

  lazy val userActionPseudoClass: Parser[UserActionPseudoClass] =
    "visited" ^^ UserActionPseudoClass |
      "active" ^^ UserActionPseudoClass |
      "hover" ^^ UserActionPseudoClass |
      "focus" ^^ UserActionPseudoClass

  lazy val targetPseudoClass: Parser[TargetPseudoClass] = "target" ^^ TargetPseudoClass

  lazy val langPseudoClass: PackratParser[LangPseudoClass] = "lang(" ~> name <~ ")" ^^ LangPseudoClass

  lazy val uiElementStatePseudoClass: Parser[UiElementStatePseudoClass] =
    "enabled" ^^ UiElementStatePseudoClass |
      "disabled" ^^ UiElementStatePseudoClass |
      "checked" ^^ UiElementStatePseudoClass


  lazy val pseudoElementSelector: PackratParser[PseudoElementSelector] = elementSelector ~ ("::" ~> pseudoElement) ^^ PseudoElementSelector

  lazy val pseudoElement: PackratParser[PseudoElement] =
    firstLinePseudoElement |
      firstLetterPseudoElement |
      beforePseudoElement |
      afterPseudoElement

  lazy val firstLinePseudoElement: Parser[PseudoElement] = "first-line" ^^ PseudoElement

  lazy val firstLetterPseudoElement: Parser[PseudoElement] = "first-letter" ^^ PseudoElement

  lazy val beforePseudoElement: Parser[PseudoElement] = "before" ^^ PseudoElement

  lazy val afterPseudoElement: Parser[PseudoElement] = "after" ^^ PseudoElement


  lazy val classSelector: PackratParser[ClassSelector] = "." ~> name ^^ ClassSelector


  lazy val idSelector: PackratParser[IdSelector] = "#" ~> name ^^ IdSelector


  lazy val negationPseudoClassSelector: PackratParser[NegationPseudoClassSelector] = ":not(" ~> simpleSelector <~ ")" ^^ NegationPseudoClassSelector


  lazy val selectorCombinator: PackratParser[SelectorCombinator] =
    descendantCombinator |
      childCombinator |
      adjacentCombinator |
      generalSiblingCombinator

  lazy val descendantCombinator: PackratParser[DescendantCombinator] = selector ~ selector ^^ DescendantCombinator

  lazy val childCombinator: PackratParser[ChildCombinator] = selector ~ (">" ~> selector) ^^ ChildCombinator

  lazy val adjacentCombinator: PackratParser[AdjacentCombinator] = selector ~ ("+" ~> selector) ^^ AdjacentCombinator

  lazy val generalSiblingCombinator: PackratParser[GeneralSiblingCombinator] = selector ~ ("~" ~> selector) ^^ GeneralSiblingCombinator


  lazy val dimension: Parser[Dimension] = (decimal ~ ("in" | "cm" | "mm" | "em" | "ex" | "pt" | "pc" | "px")) ^^ Dimension | percent

  lazy val percent: Parser[Dimension] = "(100|[1-9][0-9]?)".r ~ "%" ^^ Dimension

  lazy val name: Parser[String] = string

  lazy val stringValue: Parser[StringValue] = "[-a-zA-Z]+".r ^^ StringValue

  lazy val string: Parser[String] = "[-a-zA-Z]+".r

  lazy val zeroValue: Parser[ZeroValue] = "0" ^^ ZeroValue

  lazy val integer: Parser[String] = "[0]|[1-9][0-9]*".r

  lazy val decimal: Parser[String] = "0|-?[1-9][0-9]*(.[0-9])?".r

  lazy val color: Parser[Color] = "#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})".r ^^ Color

  lazy val nth: Parser[String] = "even" | "odd" | "(\\+|-)?([0]|[1-9][0-9]*)(n(\\+|-)([0]|[1-9][0-9]*))?".r

}
