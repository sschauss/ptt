package de.unikoblenz.ptt.lord.ass02

import org.kiama.util.PositionedParserUtilities
import de.unikoblenz.ptt.lord.ass02.ast._


object Parser extends PositionedParserUtilities {

  override def skipWhitespace = false

  val s = "[ \t\r\n\f]+".r

  def parse[T](input: String, parser: Parser[T]): T = parseAll(parser, input) match {
    case Success(result, _) => result
    case Failure(msg, next) => println(msg); println(next.offset); throw new Exception()
    case Error(msg, next) => throw new Exception(msg + next)
  }

  def is[T](parser: Parser[T]) = (rep(s) ~> parser <~ rep(s))

  lazy val parser = rep(ruleSet) ^^ CSS

  lazy val ruleSet: PackratParser[RuleSet] = is(selectorGroup) ~ (is("{") ~> rep(rule) <~ is("}")) ^^ RuleSet

  lazy val rule: PackratParser[Rule] = property | ruleSet

  lazy val property = propertyName ~ (is(":") ~> rep1sep(valueGroup, is(",")) <~ is(";")) ^^ Property

  lazy val valueGroup = rep1sep(propertyValue, s) ^^ ValueGroup

  lazy val propertyName = "[-a-zA-Z_]+".r
  lazy val propertyValue = "(#|\\.|%|\\(|\\)|[-a-zA-Z0-9])+".r ^^ Value


  lazy val selectorGroup = rep1sep(selectorSequence, is(",")) ^^ SelectorGroup
  lazy val selectorSequence: PackratParser[SelectorSequence] = selector ~ rep(selectorCombination) ^^ SelectorSequence
  lazy val selectorCombination: PackratParser[SelectorCombination] = (">" | "+" | "~" | rep1(s) ^^^ " " | "") ~ selectorSequence ^^ SelectorCombination
  lazy val selector = classSelector | idSelector | typeSelector | universalSelector | notSelector | attributeSelector | pseudoClassSelector | pseudoElementSelector
  lazy val classSelector = "." ~> className ^^ ClassSelector
  lazy val className = "[_a-zA-Z-]+".r
  lazy val idSelector = "#" ~> idName ^^ IdSelector
  lazy val idName = "[-a-zA-Z]+".r
  lazy val typeSelector = (nameSpacePrefix ?) ~ typeName ^^ TypeSelector
  lazy val typeName = "[-a-zA-Z]+".r
  lazy val nameSpacePrefix = nameSpaceName <~ "|" ^^ NameSpacePrefix
  lazy val nameSpaceName = ("[a-zA-Z]+".r | "*") ?
  lazy val universalSelector = (nameSpacePrefix ?) <~ "*" ^^ UniversalSelector
  lazy val attributeSelector: PackratParser[AttributeSelector] = "[" ~> (nameSpacePrefix ?) ~ attributeName ~ (attribute ?) <~ "]" ^^ AttributeSelector
  lazy val attributeName = "[a-zA-Z]+".r
  lazy val attribute = attributeOperator ~ attributeValue ^^ Attribute
  lazy val attributeValue = """(\"|')?[a-zA-Z]+(\"|')?|((\"{2}|'{2}))""".r
  lazy val attributeOperator = "=" | "|=" | "$=" | "^=" | "~=" | "*="
  lazy val pseudoClassSelector: PackratParser[PseudoClassSelector] = ":" ~> pseudoClassName ~ (("(" ~> pseudoClassExpression <~ ")") ?) ^^ PseudoClassSelector
  lazy val pseudoClassName = "[-a-zA-Z]+".r
  lazy val pseudoClassExpression = """(\+|-|[a-zA-Z0-9])*""".r
  lazy val pseudoElementSelector: PackratParser[PseudoElementSelector] = "::" ~> pseudoElementName ^^ PseudoElementSelector
  lazy val pseudoElementName = "[-a-zA-Z]+".r
  lazy val notSelector = ":not(" ~> (typeSelector | universalSelector | idSelector | classSelector | attributeSelector | pseudoClassSelector | pseudoElementSelector) <~ ")" ^^ NotSelector
}
