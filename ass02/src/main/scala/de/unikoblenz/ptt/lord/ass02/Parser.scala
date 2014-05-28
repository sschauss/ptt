package de.unikoblenz.ptt.lord.ass02

import org.kiama.util.PositionedParserUtilities
import de.unikoblenz.ptt.lord.ass02.ast._
import de.unikoblenz.ptt.lord.ass02.ast.pseudoclass._
import de.unikoblenz.ptt.lord.ass02.ast.selector._
import de.unikoblenz.ptt.lord.ass02.ast.value._


object Parser extends PositionedParserUtilities {

  def parse(input: String): Sass = parse(input, parser)

  def parse[T](input: String, parser: Parser[T]): T = parseAll(parser, input) match {
    case Success(sass, _) => sass
    case Failure(message, _) => throw new Exception(message)
    case Error(message, _) => throw new Exception(message)
  }

  def parser: PackratParser[Sass] = phrase(sass)

  def sass: PackratParser[Sass] = rep(ruleSet) ^^ Sass

  def ruleSet: PackratParser[RuleSet] = rep1sep(selector, ",") ~ ("{" ~> rep(ruleSet | rule) <~ "}") ^^ RuleSet

  def rule: PackratParser[Rule] = property ~ (":" ~> rep1sep(valueGroup, ",") <~ ";") ^^ Rule

  def valueGroup: PackratParser[ValueGroup] = rep1(value) ^^ ValueGroup

  def property: PackratParser[String] = List("color", "opacity").reduce(_ | _)


  def value: PackratParser[Value] = color | dimension | stringValue | zeroValue


  def selector: PackratParser[Selector] =
    selectorCombinator |
      pseudoElementSelector |
      simpleSelector


  def simpleSelector: PackratParser[SimpleSelector] =
    pseudoClassSelector |
      attributeSelector |
      universalSelector |
      elementSelector |
      classSelector |
      idSelector


  def universalSelector: Parser[UniversalSelector] = "*" ^^ UniversalSelector


  def elementSelector: PackratParser[ElementSelector] = name ^^ ElementSelector


  def attributeSelector: PackratParser[AttributeSelector] = elementSelector ~ ("[" ~> attribute <~ "]") ^^ AttributeSelector

  def attribute: PackratParser[Attribute] = name ~ ((("~" | "^" | "$" | "*" | "|") ?) <~ "=") ~ ("\"" ~> name <~ "\"") ^^ Attribute


  def pseudoClassSelector: PackratParser[PseudoClassSelector] =
    elementSelector ~ (":" ~> pseudoClass) ^^ ElementPseudoClassSelector |
      negationPseudoClassSelector

  def pseudoClass: PackratParser[PseudoClass] =
    structuralPseudoClass |
      linkPseudoClass |
      userActionPseudoClass |
      targetPseudoClass |
      langPseudoClass |
      uiElementStatePseudoClass

  def structuralPseudoClass: PackratParser[StructuralPseudoClass] =
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

  def linkPseudoClass: Parser[LinkPseudoClass] = "link" ^^ LinkPseudoClass

  def userActionPseudoClass: Parser[UserActionPseudoClass] =
    "visited" ^^ UserActionPseudoClass |
      "active" ^^ UserActionPseudoClass |
      "hover" ^^ UserActionPseudoClass |
      "focus" ^^ UserActionPseudoClass

  def targetPseudoClass: Parser[TargetPseudoClass] = "target" ^^ TargetPseudoClass

  def langPseudoClass: PackratParser[LangPseudoClass] = "lang(" ~> name <~ ")" ^^ LangPseudoClass

  def uiElementStatePseudoClass: Parser[UiElementStatePseudoClass] =
    "enabled" ^^ UiElementStatePseudoClass |
      "disabled" ^^ UiElementStatePseudoClass |
      "checked" ^^ UiElementStatePseudoClass


  def pseudoElementSelector: PackratParser[PseudoElementSelector] = elementSelector ~ ("::" ~> pseudoElement) ^^ PseudoElementSelector

  def pseudoElement: PackratParser[PseudoElement] =
    firstLinePseudoElement |
      firstLetterPseudoElement |
      beforePseudoElement |
      afterPseudoElement

  def firstLinePseudoElement: Parser[PseudoElement] = "first-line" ^^ PseudoElement

  def firstLetterPseudoElement: Parser[PseudoElement] = "first-letter" ^^ PseudoElement

  def beforePseudoElement: Parser[PseudoElement] = "before" ^^ PseudoElement

  def afterPseudoElement: Parser[PseudoElement] = "after" ^^ PseudoElement


  def classSelector: PackratParser[ClassSelector] = "." ~> name ^^ ClassSelector


  def idSelector: PackratParser[IdSelector] = "#" ~> name ^^ IdSelector


  def negationPseudoClassSelector: PackratParser[NegationPseudoClassSelector] = ":not(" ~> simpleSelector <~ ")" ^^ NegationPseudoClassSelector


  def selectorCombinator: PackratParser[SelectorCombinator] =
    descendantCombinator |
      childCombinator |
      adjacentCombinator |
      generalSiblingCombinator

  def descendantCombinator: PackratParser[DescendantCombinator] = simpleSelector ~ simpleSelector ^^ DescendantCombinator

  def childCombinator: PackratParser[ChildCombinator] = simpleSelector ~ (">" ~> simpleSelector) ^^ ChildCombinator

  def adjacentCombinator: PackratParser[AdjacentCombinator] = simpleSelector ~ ("+" ~> simpleSelector) ^^ AdjacentCombinator

  def generalSiblingCombinator: PackratParser[GeneralSiblingCombinator] = simpleSelector ~ ("~" ~> simpleSelector) ^^ GeneralSiblingCombinator


  def dimension: Parser[Dimension] = (decimal ~ ("in" | "cm" | "mm" | "em" | "ex" | "pt" | "pc" | "px")) ^^ Dimension | percent

  def percent: Parser[Dimension] = "(100|[1-9][0-9]?)".r ~ "%" ^^ Dimension

  def name: Parser[String] = string

  def stringValue: Parser[StringValue] = "[-a-zA-Z]+".r ^^ StringValue

  def string: Parser[String] = "[-a-zA-Z]+".r

  def zeroValue: Parser[ZeroValue] = "0" ^^ ZeroValue

  def integer: Parser[String] = "[0]|[1-9][0-9]*".r

  def decimal: Parser[String] = "0|-?[1-9][0-9]*(.[0-9])?".r

  def nth: Parser[String] = "even" | "odd" | "(\\+|-)?([0]|[1-9][0-9]*)(n(\\+|-)([0]|[1-9][0-9]*))?".r

  def color: Parser[Color] =
    rgbaColor |
      hslaColor |
      rgbColor |
      hslColor |
      hexColor |
      namedColor |
      initial |
      inherit

  def namedColor: Parser[NamedColor] = name ^^ NamedColor

  def hexColor: Parser[HexColor] = "#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})".r ^^ HexColor

  def rgbColor: Parser[RgbColor] = ("rgb(" ~> rgbColorValue <~ ",") ~ rgbColorValue ~ ("," ~> rgbColorValue <~ ")") ^^ RgbColor

  def rgbaColor: Parser[RgbaColor] = ("rgba(" ~> rgbColorValue <~ ",") ~ rgbColorValue ~ ("," ~> rgbColorValue <~ ",") ~ opacity <~ ")" ^^ RgbaColor

  def hslColor: Parser[HslColor] = ("hsl(" ~> degree <~ ",") ~ colorPercent ~ ("," ~> colorPercent <~ ")") ^^ HslColor

  def hslaColor: Parser[HslaColor] = ("hsla(" ~> degree <~ ",") ~ colorPercent ~ ("," ~> colorPercent <~ ",") ~ opacity <~ ")" ^^ HslaColor

  def rgbColorValue: Parser[String] = "25[0-5]|2[0-4][0-9]|1?[0-9]?[0-9]".r

  def opacity: Parser[String] = "1|0\\.[0-9]+".r

  def degree: Parser[String] = "360|3[0-5][0-9]|[1-2]?[0-9]?[0-9]".r

  def colorPercent: Parser[String] = "100%|[0-9]?[0-9]%".r

  def initial = "initial" ^^ Initial

  def inherit = "inherit" ^^ Inherit

  case class Initial(value: String) extends Color

  case class Inherit(value: String) extends Color

  case class ColorRule(value: Color) extends RuleNode

  def colorRule = "color:" ~ color ~ ";"



}
