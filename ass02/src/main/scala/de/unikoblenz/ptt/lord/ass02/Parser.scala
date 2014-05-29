package de.unikoblenz.ptt.lord.ass02

import org.kiama.util.{WhitespacePositionedParserUtilities, PositionedParserUtilities}
import de.unikoblenz.ptt.lord.ass02.ast._
import de.unikoblenz.ptt.lord.ass02.ast.pseudoclass._
import de.unikoblenz.ptt.lord.ass02.ast.selector._
import de.unikoblenz.ptt.lord.ass02.ast.value._
import scala.util.matching.Regex


object Parser extends PositionedParserUtilities {

  def parse(input: String): Any = parse(input, parser)

  def parse[T](input: String, parser: Parser[T]): T = parseAll(parser, input) match {
    case Success(sass, _) => sass
    case Failure(message, _) => throw new Exception(message)
    case Error(message, _) => throw new Exception(message)
  }

  def parser: PackratParser[Any] = phrase(sass)

  def sass: PackratParser[Any] = rep(selectorsGroup ~ "{}")

  def ruleSet: PackratParser[RuleSet] = rep1sep(selector, ",") ~ ("{" ~> rep(ruleSet | rule) <~ "}") ^^ RuleSet

  def rule: PackratParser[Rule] = property ~ (":" ~> repsep(valueGroup, ",")) ~ (opt(important) <~ ";") ^^ Rule

  def important: Parser[String] = "!important"

  def valueGroup: PackratParser[ValueGroup] = rep1(value) ^^ ValueGroup

  def property: PackratParser[String] = name

  def value: PackratParser[Value] = color | quantity | stringValue



  //Selector Group
  def selectorsGroup: PackratParser[SelectorGroup] = rep1sep(selector, ",") ^^ SelectorGroup


  //Selector
  def selector: PackratParser[Selector] = simpleSelectorSequence ~ rep((combinator ~ simpleSelectorSequence) ^^ { tuple => (tuple._1, tuple._2)}) ^^ Selector


  //Combinator
  def combinator: PackratParser[Combinator] =
    descendantCombinator |
      childCombinator |
      adjacentCombinator |
      generalSiblingCombinator

  def descendantCombinator: PackratParser[DescendantCombinator] = rep(" ") ^^^ DescendantCombinator()

  def childCombinator: PackratParser[ChildCombinator] = ">" ^^^ ChildCombinator()

  def adjacentCombinator: PackratParser[AdjacentCombinator] = "+" ^^^ AdjacentCombinator()

  def generalSiblingCombinator: PackratParser[GeneralSiblingCombinator] = "~" ^^^ GeneralSiblingCombinator()


  //Simple Selector Sequence
  def simpleSelectorSequence: PackratParser[SimpleSelectorSequence] =
    (typeSelector | universalSelector) ~ rep(idSelector | classSelector | attributeSelector | pseudoSelector | negationSelector) ^^ {tuple => SimpleSelectorSequence(Some(tuple._1), tuple._2)} |
    rep1(idSelector | classSelector | attributeSelector | pseudoSelector | negationSelector) ^^ {SimpleSelectorSequence(None, _)}


  //Type Selector
  def typeSelector: PackratParser[TypeSelector] = (nameSpacePrefix ? ) ~ name ^^ TypeSelector


  //Namespace Prefix
  def nameSpacePrefix: PackratParser[NameSpacePrefix] = ((ident | "*") ?) <~ "|" ^^ NameSpacePrefix


  //Element Name
  def elementName: PackratParser[String] = ident


  //Universal Selector
  def universalSelector: PackratParser[UniversalSelector] = "*" ^^ UniversalSelector


  //Class Selector
  def classSelector: PackratParser[ClassSelector] = ("." ~> ident) ^^ ClassSelector


  //Attribute Selector
  def attributeSelector: PackratParser[AttributeSelector] = "[" ~> (nameSpacePrefix ?) ~ ident  ~ ((("~" | "^" | "$" | "*" | "|") ?) <~ "=") ~ (ident | string)  <~ "]" ^^ AttributeSelector


  //Pseudo Selector
  def pseudoSelector: PackratParser[PseudoSelector] = pseudoClassSelector | pseudoElementSelector


  //Pseudo Class Selector
  def pseudoClassSelector: PackratParser[PseudoClassSelector] = ":" ~> (structuralPseudoClass | linkPseudoClass | userActionPseudoClass | targetPseudoClass | langPseudoClass | uiElementStatePseudoClass) ^^ PseudoClassSelector

  def structuralPseudoClass: PackratParser[StructuralPseudoClass] =
    ("nth-child" ~ ("(" ~> nth <~ ")") | "nth-last-child" ~ ("(" ~> nth <~ ")") | "nth-of-type" ~ ("(" ~> nth <~ ")") | "nth-last-of-type" ~ ("(" ~> nth <~ ")")) ^^ ComplexStructuralPseudoClass |
      ("root" | "first-child" | "last-child" | "first-of-type" | "last-of-type" | "only-child" | "only-of-type" | "empty") ^^ SimpleStructuralPseudoClass

  def linkPseudoClass: Parser[LinkPseudoClass] = "link" ^^ LinkPseudoClass

  def userActionPseudoClass: Parser[UserActionPseudoClass] = ("visited" | "active" | "hover" | "focus") ^^ UserActionPseudoClass

  def targetPseudoClass: Parser[TargetPseudoClass] = "target" ^^ TargetPseudoClass

  def langPseudoClass: PackratParser[LangPseudoClass] = "lang(" ~> name <~ ")" ^^ LangPseudoClass

  def uiElementStatePseudoClass: Parser[UiElementStatePseudoClass] = ("enabled" | "disabled" | "checked") ^^ UiElementStatePseudoClass


  //Pseudo Element Selector
  def pseudoElementSelector: PackratParser[PseudoElementSelector] = "::" ~> pseudoElement ^^ PseudoElementSelector

  def pseudoElement: PackratParser[PseudoElement] = firstLinePseudoElement | firstLetterPseudoElement | beforePseudoElement | afterPseudoElement

  def firstLinePseudoElement: Parser[PseudoElement] = "first-line" ^^ PseudoElement

  def firstLetterPseudoElement: Parser[PseudoElement] = "first-letter" ^^ PseudoElement

  def beforePseudoElement: Parser[PseudoElement] = "before" ^^ PseudoElement

  def afterPseudoElement: Parser[PseudoElement] = "after" ^^ PseudoElement


  //Id Selector
  def idSelector: PackratParser[IdSelector] = ("#" ~> ident) ^^ IdSelector


  //Negation Selector
  def negationSelector: PackratParser[NegationSelector] = ":not(" ~> (typeSelector | universalSelector | idSelector | classSelector | attributeSelector | pseudoSelector) <~  ")" ^^ NegationSelector


  def ident = ("-" ?) ~ (nmstart ~ rep(nmchar) ^^ { x => x._1 + x._2.mkString}) ^^ { x => x._1.getOrElse("") + x._2}

  def name: Parser[String] = rep1(nmchar) ^^ {
    _.mkString
  }

  def nmstart: Parser[String] = "[_a-z]".r | nonascii | escape

  def nonascii: Parser[String] = "[^\0-\177]".r

  def unicode: Parser[String] = "\\[0-9a-f]{1,6}(\r\n|[\n\r\t\f])?".r

  def escape: PackratParser[String] = unicode | "\\[^\n\r\f0-9a-f]"

  def nmchar: Parser[String] = "[_a-z0-9-]".r | nonascii | escape

  def num: Parser[String] = "[0-9]+|[0-9]*\\.[0-9]+".r

  def string: Parser[String] = ("\"" | "'") ~ (rep(not("[^\n\r\f\"]".r) ~> (nonascii | escape))) ~ ("\"" | "'") ^^ { x => x._1._1 + x._1._2.mkString + x._2}

  def nl: Parser[String] = "\n|\r\n|\r|\f".r

  def s: Parser[String] = "[\t\r\n\f]+".r

  def ss: Parser[List[String]] = rep(s)














  def quantity: PackratParser[Quantity] =
    percent |
      in |
      cm |
      mm |
      em |
      ex |
      pt |
      pc |
      px |
      zero

  def percent: PackratParser[Percent] = num <~ "%" ^^ { r => Percent(r.toFloat)}

  def in: PackratParser[In] = num <~ "in" ^^ { r => In(r.toFloat)}

  def cm: PackratParser[Cm] = num <~ "cm" ^^ { r => Cm(r.toFloat)}

  def mm: PackratParser[Mm] = num <~ "mm" ^^ { r => Mm(r.toFloat)}

  def em: PackratParser[Em] = num <~ "em" ^^ { r => Em(r.toFloat)}

  def ex: PackratParser[Ex] = num <~ "ex" ^^ { r => Ex(r.toFloat)}

  def pt: PackratParser[Pt] = num <~ "pt" ^^ { r => Pt(r.toFloat)}

  def pc: PackratParser[Pc] = num <~ "pc" ^^ { r => Pc(r.toFloat)}

  def px: PackratParser[Px] = num <~ "px" ^^ { r => Px(r.toFloat)}

  def zero: PackratParser[Zero] = "0" ^^ { _ => Zero()}


  def stringValue: Parser[StringValue] = "[-a-zA-Z]+".r ^^ StringValue


  def integer: Parser[String] = "[0]|[1-9][0-9]*".r

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

  def namedColor: Parser[NamedColor] = "(?i)aliceblue|antiquewhite|aqua|aquamarine|azure|beige|bisque|black|blanchedalmond|blue|blueviolet|brown|burlywood|cadetblue|chartreuse|chocolate|coral|cornflowerblue|cornsilk|crimson|cyan|darkblue|darkcyan|darkgoldenrod|darkgray|darkgreen|darkgrey|darkkhaki|darkmagenta|darkolivegreen|darkorange|darkorchid|darkred|darksalmon|darkseagreen|darkslateblue|darkslategray|darkslategrey|darkturquoise|darkviolet|deeppink|deepskyblue|dimgray|dimgrey|dodgerblue|firebrick|floralwhite|forestgreen|fuchsia|gainsboro|ghostwhite|gold|goldenrod|gray|green|greenyellow|grey|honeydew|hotpink|indianred|indigo|ivory|khaki|lavender|lavenderblush|lawngreen|lemonchiffon|lightblue|lightcoral|lightcyan|lightgoldenrodyellow|lightgray|lightgreen|lightgrey|lightpink|lightsalmon|lightseagreen|lightskyblue|lightslategray|lightslategrey|lightsteelblue|lightyellow|lime|limegreen|linen|magenta|maroon|mediumaquamarine|mediumblue|mediumorchid|mediumpurple|mediumseagreen|mediumslateblue|mediumspringgreen|mediumturquoise|mediumvioletred|midnightblue|mintcream|mistyrose|moccasin|navajowhite|navy|oldlace|olive|olivedrab|orange|orangered|orchid|palegoldenrod|palegreen|paleturquoise|palevioletred|papayawhip|peachpuff|peru|pink|plum|powderblue|purple|red|rosybrown|royalblue|saddlebrown|salmon|sandybrown|seagreen|seashell|sienna|silver|skyblue|slateblue|slategray|slategrey|snow|springgreen|steelblue|tan|teal|thistle|tomato|turquoise|violet|wheat|white|whitesmoke|yellow|yellowgreen".r ^^ NamedColor

  def hexColor: Parser[HexColor] = "#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})".r ^^ HexColor

  def rgbColor: Parser[RgbColor] = ("rgb(" ~> rgbColorValue <~ ",") ~ rgbColorValue ~ ("," ~> rgbColorValue <~ ")") ^^ RgbColor

  def rgbaColor: Parser[RgbaColor] = ("rgba(" ~> rgbColorValue <~ ",") ~ rgbColorValue ~ ("," ~> rgbColorValue <~ ",") ~ opacity <~ ")" ^^ RgbaColor

  def hslColor: Parser[HslColor] = ("hsl(" ~> degree <~ ",") ~ colorPercent ~ ("," ~> colorPercent <~ ")") ^^ HslColor

  def hslaColor: Parser[HslaColor] = ("hsla(" ~> degree <~ ",") ~ colorPercent ~ ("," ~> colorPercent <~ ",") ~ opacity <~ ")" ^^ HslaColor

  def rgbColorValue: Parser[String] = "25[0-5]|2[0-4][0-9]|1?[0-9]?[0-9]".r

  def opacity: Parser[String] = "1|0\\.[0-9]+".r

  def degree: Parser[String] = "360|3[0-5][0-9]|[1-2]?[0-9]?[0-9]".r

  def colorPercent: Parser[String] = "100%|[1-9][0-9]?%".r

  def initial = "initial" ^^^ Initial()

  def inherit = "inherit" ^^^ Inherit()

  case class Initial() extends Color

  case class Inherit() extends Color

  case class ColorRule(value: Color) extends RuleNode

  def colorRule = "color:" ~ color ~ ";"

}
