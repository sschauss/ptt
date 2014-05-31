package de.unikoblenz.ptt.lord.ass02.ast


case class Mixin(name: String, parameters: Option[List[Parameter]],rules: List[Rule]) extends Node