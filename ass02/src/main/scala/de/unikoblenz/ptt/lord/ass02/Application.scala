package de.unikoblenz.ptt.lord.ass02

object Application extends App {

  val scss =
    """
			|@mixin mix($width) {
			|    width: 10 * $width + 10px / 2px - 5px, 10 * $width + 10px / 2px -5px;
			|}
			|$width: 10px / 10px;
			|div {
			|    @include mix($width);
			|    width: $width;
			|    a {
			|        @include mix(20px);
			|    }
			|}
    """.stripMargin

  val scssAst = Parser.parse(scss, Parser.parser)
	println(scssAst)
  println("\nPretty SCSS")
  println(PrettyPrinter.pretty(scssAst))
  println("\nPretty CSS")
	val cssAst = Transformer.transform(scssAst)
  println(PrettyPrinter.pretty(cssAst))
}