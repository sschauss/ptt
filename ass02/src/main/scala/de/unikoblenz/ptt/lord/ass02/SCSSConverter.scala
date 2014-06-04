package de.unikoblenz.ptt.lord.ass02

import de.unikoblenz.ptt.lord.ass02.ast._

object SCSSConverter {


  def convert(scss: SCSS) = replaceSubRules(scss)

  def replaceSubRules(scss: SCSS): SCSS = SCSS(scss.nodes map {node => replaceSubRules(node)} reduce (_:::_))

  def replaceSubRules[T >: Node](node: T, parent: Option[RuleSet] = None): List[T] = node match {
    case node: RuleSet => parent match {
      case Some(p) => RuleSet(concatSelectorSequence(p.selectorGroup, node.selectorGroup), node.rules) :: (node.rules map {rule => replaceSubRules(rule, Some(node))} reduce (_:::_))
      case None => node :: (node.rules map {rule => replaceSubRules(rule, Some(node))} reduce (_:::_))
    }
    case _ => List(node)
  }



  def concatSelectorSequence(r: SelectorGroup, l: SelectorGroup): SelectorGroup = SelectorGroup(r.selectorSequences map { r => l.selectorSequences map { l => concatSelectorSequence(r, l)}} reduce (_ ::: _))

  def concatSelectorSequence(r: SelectorSequence, l: SelectorSequence): SelectorSequence = r match {
    case SelectorSequence(selector, None) => SelectorSequence(selector, Some(SelectorCombination(" ", l)))
    case SelectorSequence(selector, Some(selectorCombination)) => SelectorSequence(selector, Some(SelectorCombination(selectorCombination.operator, concatSelectorSequence(selectorCombination.selectorSequence, l))))
  }

  def replaceVariables[T >: Node](node: T, variables: Set[Variable]): T = node match {
    case SCSS(nodes) => SCSS(nodes.zipWithIndex collect {
      case (n, i) if !n.isInstanceOf[Variable] => replaceVariables(n, buildVariableSet(nodes.take(i + 1), variables))
    })
    case RuleSet(selectorGroup, rules) => RuleSet(selectorGroup, rules.zipWithIndex collect {
      case (n, i) if !n.isInstanceOf[Variable] => replaceVariables(n, buildVariableSet(rules.take(i + 1), variables))
    })
    case Declaration(property, valueGroups) => Declaration(property, valueGroups map {
      replaceVariables(_, variables)
    })
    case _ => node
  }

  def buildVariableSet(nodes: List[Node], variables: Set[Variable]): Set[Variable] = {
    val vs: List[Variable] = variables.toList ++ (nodes collect { case v: Variable => v})
    (for (v <- vs) yield (vs collect { case Variable(v.name, value) => Variable(v.name, value)}).last).toSet
  }


}
