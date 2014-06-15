package de.unikoblenz.ptt.lord.ass02

import scala.io.Source
import scala.reflect.io.File

object Application extends App {

	if (args.length < 2) printUsage

	val scss = Source.fromFile(args(0)).mkString
	val scssAst = Parser.parse(scss, Parser.parser(File.apply(args(0)).toAbsolute.parent.path))


	args(1) match {
		case "--pretty" =>
			val scssOutputFilename = if (args.length > 2) args(2) else "pretty.scss"
			File.apply(scssOutputFilename).writeAll(SCSSPrettyPrinter.pretty(scssAst))
		case "--css"    =>
			val cssOutputFilename = if (args.length > 2) args(2) else "pretty.css"
			val cssAst = Transformer.transform(scssAst)
			File.apply(cssOutputFilename).writeAll(SCSSPrettyPrinter.pretty(cssAst))
		case _          => printUsage
	}

	def printUsage() = println(
		"""
			|Usage: scssFile [--pretty scssOutputFilename] [--css cssOutputFilename]
			|
			|		scssFile:
			| 		relative or absolute path to scss file
			|
			| 	scssOutputFilename:
			| 		relative or absolute path for prettyprinted scss file
			|  		default: ./pretty.scss
			|
			| 	cssOutputFilename:
			| 		relative or absolute path for prettyprinted css file
			|  		default: ./pretty.css
		""".stripMargin)

}

