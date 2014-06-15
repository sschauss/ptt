package de.unikoblenz.ptt.lord.ass02

import org.kiama.util.PositionedParserUtilities
import de.unikoblenz.ptt.lord.ass02.ast._


object Parser extends PositionedParserUtilities {

	override def skipWhitespace = false

	val s = "[ \t\r\n\f]+".r

	def parse[T](input: String, parser: Parser[T]): T = parseAll(parser, input) match {
		case Success(result, _) => result
		case Failure(msg, next) => println(msg); println(next.offset); throw new Exception()
		case Error(msg, next)   => throw new Exception(msg + next)
	}

	def iw[T](parser: Parser[T]) = rep(s) ~> parser <~ rep(s) //ignore whitespaces

	lazy val parser = iw(rep(ruleSet | mixin | variable)) ^^ SCSS

	lazy val mixin: PackratParser[Mixin] = iw("@mixin") ~> mixinName ~ ((iw("(") ~> rep1(parameter) <~ iw(")")) ?) ~ declarationBlock ^^ Mixin
	lazy val mixinName = "[-a-zA-Z]+".r
	lazy val parameter = "$" ~> parameterName ^^ Parameter
	lazy val parameterName = "[-a-zA-Z]+".r

	lazy val variable = "$" ~> "[-a-zA-Z]+".r ~ (iw(":") ~> rep1sep(valueGroup, iw(",")) <~ iw(";")) ^^ Variable


	lazy val ruleSet: PackratParser[RuleSet] = iw(selectorGroup) ~ declarationBlock ^^ RuleSet
	lazy val declarationBlock = iw("{") ~> rep(rule) <~ iw("}")
	lazy val rule: PackratParser[Node] = declaration | ruleSet | variable | include | mixin
	lazy val declaration = property ~ (iw(":") ~> rep1sep(valueGroup, iw(",")) <~ iw(";")) ^^ Declaration

	lazy val valueGroup = rep1sep(value, s) ^^ ValueGroup
	lazy val value = variableValue | expression | stringValue
	lazy val variableValue = "$" ~> "[-a-zA-Z]+".r ^^ VariableValue
	lazy val property = ident
	lazy val stringValue = "(#|[a-zA-Z0-9])[-a-zA-Z0-9]*".r ^^ Value

	lazy val expression: Parser[Expression] = term ~ rep(addition | subtraction) ^^ Expression
	lazy val addition: Parser[Addition] = iw("+") ~> term ^^ Addition
	lazy val subtraction: Parser[Subtraction] = rep(s) ~> "-" ~> s ~> term ^^ Subtraction
	lazy val term: Parser[Term] = factor ~ rep(multiplication | division) ^^ Term
	lazy val multiplication: Parser[Multiplication] = iw("*") ~> factor ^^ Multiplication
	lazy val division: Parser[Division] = iw("/") ~> factor ^^ Division
	lazy val factor = iw("(") ~> expression <~ iw(")") | dimensionedValue | negativeExpression | variableValue
	lazy val negativeExpression = iw("-") ~> (iw("(") ~> expression <~ iw(")")) ^^ NegativeExpression

	lazy val dimensionedValue = number ~ opt(dimension) ^^ DimensionedValue
	lazy val dimension = "[a-zA-Z]+".r
	lazy val number = "0|(-)?[1-9][0-9]*(\\.[0-9])?".r ^^ {
		_.toDouble
	}

	lazy val include = iw("@include") ~> mixinName ~ ((iw("(") ~> rep1(value) <~ iw(")")) ?) <~ iw(";") ^^ Include

	lazy val selectorGroup = rep1sep(selectorSequence, iw(",")) ^^ SelectorGroup
	lazy val selectorSequence: PackratParser[SelectorSequence] = selector ~ (selectorCombination ?) ^^ SelectorSequence
	lazy val selectorCombination: PackratParser[SelectorCombination] = (iw(">" | "+" | "~") | rep1(s) ^^^ " " | "") ~ selectorSequence ^^ SelectorCombination
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
	lazy val pseudoClassExpression = rep1("+" | "-" | pseudoclassDimension | num | string | ident) ^^ {
		_.mkString
	}
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
	lazy val string = doubleQuotedString | singleQuotedString
	lazy val doubleQuotedString = ("\"" + "([^\n\r\f\"]|\\(" + nl + "|" + nonascii + "|" + escape + ")*" + "\"").r
	lazy val singleQuotedString = ("\'" + "([^\n\r\f\"]|\\(" + nl + "|" + nonascii + "|" + escape + ")*" + "\'").r
	lazy val num = "[0-9]+|[0-9]*\\.[0-9]+".r
	lazy val pseudoclassDimension = num ~ ident


}