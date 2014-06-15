package de.unikoblenz.ptt.lord.ass02


import org.specs2.mutable._

import scala.io.Source
import scala.reflect.io.File

class SCSSTest extends Specification {


	val cssPath = "src/test/resources/fixtures/base.css"
	val scssPath = "src/test/resources/fixtures/base.scss"
	val cssString = Source.fromFile(cssPath).mkString
	val scssString = Source.fromFile(scssPath).mkString
	val scssAst =  Parser.parse(scssString, Parser.parser(File.apply(scssPath).toAbsolute.parent.path))
	val cssAst =  Parser.parse(cssString, Parser.parser(File.apply(cssPath).toAbsolute.parent.path))

	"The parsed and the prettyprinted AST" should {
		"be the same" in {
			val prettySCSSString = SCSSPrettyPrinter.pretty(scssAst)
			scssAst == Parser.parse(prettySCSSString, Parser.parser(File.apply(scssPath).toAbsolute.parent.path))
		}
	}

	"The transformed AST" should {
		"be css" in {
			cssAst == Transformer.transform(scssAst)
		}
	}


}
