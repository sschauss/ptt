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

  def iw[T](parser: Parser[T]) = (rep(s) ~> parser <~ rep(s)) //ignore whitespaces

  lazy val parser = iw(rep(ruleSet | mixin | variable)) ^^ SCSS

  lazy val extend = "@extend" ~> selector ^^ Extend

  lazy val mixin = iw("@mixin") ~> mixinName ~ ((iw("(") ~> rep1(parameter) <~ iw(")")) ?) ~ declarationBlock ^^ Mixin
  lazy val mixinName = "[-a-zA-Z]+".r
  lazy val parameter = "$" ~> parameterName ^^ Parameter
  lazy val parameterName = "[-a-zA-Z]+".r

  lazy val variable = variableName ~ (iw(":") ~> rep1sep(valueGroup, iw(",")) <~ iw(";")) ^^ Variable
  lazy val variableName = "$" ~> "[-a-zA-Z]+".r ^^ VariableName


  lazy val ruleSet: PackratParser[RuleSet] = iw(selectorGroup) ~ declarationBlock ^^ RuleSet
  lazy val declarationBlock = (iw("{") ~> rep(rule | extend | include) <~ iw("}"))
  lazy val rule: PackratParser[Rule] = declaration | ruleSet
  lazy val declaration = property ~ (iw(":") ~> rep1sep(valueGroup, iw(",")) <~ iw(";")) ^^ Declaration

  lazy val valueGroup = rep1sep(value, s) ^^ ValueGroup
  lazy val value = stringValue | variableValue
  lazy val variableValue = "$" ~> "[-a-zA-Z]+".r ^^ VariableValue


  lazy val property = "[-a-zA-Z_]+".r
  lazy val stringValue = "(#|\\.|%|[-a-zA-Z0-9])+".r ^^ StringValue


  lazy val include = iw("@include") ~> mixinName ~ ((iw("(") ~> rep1(value) <~ iw(")")) ?) <~ iw(";") ^^ Include


  lazy val selectorGroup = rep1sep(selectorSequence, iw(",")) ^^ SelectorGroup
  lazy val selectorSequence: PackratParser[SelectorSequence] = selector ~ rep(selectorCombination) ^^ SelectorSequence
  lazy val selectorCombination: PackratParser[SelectorCombination] = (">" | "+" | "~" | rep1(s) ^^^ " " | "") ~ selectorSequence ^^ SelectorCombination
  lazy val selector = classSelector | idSelector | typeSelector | universalSelector | notSelector | attributeSelector | pseudoClassSelector | pseudoElementSelector
  lazy val classSelector = "." ~> className ^^ ClassSelector
  lazy val className = ident
  lazy val idSelector = "#" ~> name ^^ IdSelector
  lazy val typeSelector = (nameSpacePrefix ?) ~ typeName ^^ TypeSelector
  lazy val typeName = ident
  lazy val nameSpacePrefix = nameSpaceName <~ "|" ^^ NameSpacePrefix
  lazy val nameSpaceName = (ident | "*").?
  lazy val universalSelector = (nameSpacePrefix ?) <~ "*" ^^ UniversalSelector
  lazy val attributeSelector: PackratParser[AttributeSelector] = "[" ~> (nameSpacePrefix ?) ~ attributeName ~ (attribute ?) <~ "]" ^^ AttributeSelector
  lazy val attributeName = ident
  lazy val attribute = attributeOperator ~ attributeValue ^^ Attribute
  lazy val attributeValue = ident | string
  lazy val attributeOperator = "=" | "|=" | "$=" | "^=" | "~=" | "*="
  lazy val pseudoClassSelector: PackratParser[PseudoClassSelector] = ":" ~> pseudoClassName ~ (("(" ~> pseudoClassExpression <~ ")") ?) ^^ PseudoClassSelector
  lazy val pseudoClassName = ident
  lazy val pseudoClassExpression = rep1("+" | "-" | dimension | num | string | ident) ^^ {
    _.mkString
  } // concat list to avoid regex :D
  lazy val pseudoElementSelector: PackratParser[PseudoElementSelector] = "::" ~> pseudoElementName ^^ PseudoElementSelector
  lazy val pseudoElementName = ident
  lazy val notSelector = ":not(" ~> (typeSelector | universalSelector | idSelector | classSelector | attributeSelector | pseudoClassSelector | pseudoElementSelector) <~ ")" ^^ NotSelector


  lazy val nmstart = s"[_a-z]|$nonascii|$escape".r
  lazy val nonascii = "[^\0-\177]".r
  lazy val unicode = "\\[0-9a-f]{1,6}(\r\n|[ \n\r\t\f])?".r
  lazy val escape = s"$unicode|\\[^\n\r\f0-9a-f]".r
  lazy val nmchar = s"[_a-z0-9-]|$nonascii|$escape".r
  lazy val nl = "\n|\r\n|\r|\f".r

  lazy val ident = s"[-]?($nmstart)($nmchar)*".r
  lazy val name = s"($nmchar)*".r
  lazy val string = {
    val value = "([^\n\r\f\"]|\\(" + nl + "|" + nonascii + "|" + escape + ")*"
    ("\"" + value + "\"").r | ("\'" + value + "\'").r
  }
  lazy val num = "[0-9]+|[0-9]*\\.[0-9]+".r
  lazy val dimension = num ~ ident


}
