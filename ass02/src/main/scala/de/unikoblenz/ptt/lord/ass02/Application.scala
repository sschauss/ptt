package de.unikoblenz.ptt.lord.ass02

import scala.io.Source
import scala.reflect.io.File

object Application extends App {

	if (args.length == 0) println(
		"""
			|Usage: scssFile [scssOutputFilename] [cssOutputFilename]
			|
			|	scssFile:
			| 	relative or absolute path to scss file
			|
			| scssOutputFilename:
			| 	relative or absolute path for prettyprinted scss file
			|
			| cssOutputFilename:
			| 	relative or absolute path for prettyprinted css file
		""".stripMargin)

	val scssOutputFilename = if(args.length >= 2) args(1) else "pretty.scss"
	val cssOutputFilename = if(args.length >= 3) args(2) else "pretty.css"

	val scss = Source.fromFile(args(0)).mkString

	val scssAst = Parser.parse(scss, Parser.parser)
	File.apply(scssOutputFilename).writeAll(PrettyPrinter.pretty(scssAst))
	val cssAst = Transformer.transform(scssAst)
	File.apply(cssOutputFilename).writeAll(PrettyPrinter.pretty(cssAst))

}
