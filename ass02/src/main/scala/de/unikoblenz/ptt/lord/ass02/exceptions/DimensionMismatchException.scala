package de.unikoblenz.ptt.lord.ass02.exceptions

class DimensionMismatchException(dimensionLeft: Option[String], dimensionRight: Option[String], operator: String, message: String) extends Exception(message) {

	def this(dimensionLeft: Option[String], dimensionRight: Option[String], operator: String) =
		this(dimensionLeft, dimensionRight, operator, s"$dimensionLeft $operator $dimensionRight")

}
