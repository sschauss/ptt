package de.unikoblenz.ptt.lord.ass02

import de.unikoblenz.ptt.lord.ass02.ast._

object Transformer {

  def transform(scss: SCSS) = SCSS(scss.nodes collect {
    case ruleSet: RuleSet => ruleSet
  } map transformRuleSet reduce (_ ::: _))

  def transformRuleSet(ruleSet: RuleSet): List[RuleSet] =
    (List(RuleSet(ruleSet.selectorGroup, ruleSet.rules collect {
      case node if !node.isInstanceOf[RuleSet] => node
    })) /: (ruleSet.rules collect {
      case r: RuleSet => transformRuleSet(RuleSet(appendSelector(ruleSet.selectorGroup, r.selectorGroup), r.rules))
    })) {
      _ ::: _
    }

  def appendSelector(l: SelectorGroup, r: SelectorGroup): SelectorGroup =
    SelectorGroup(l.selectorSequences map { ls => r.selectorSequences map { rs => appendSelectorSequence(ls, rs)}} reduce (_ ::: _))

  def appendSelectorSequence(l: SelectorSequence, r: SelectorSequence): SelectorSequence =
    l match {
      case SelectorSequence(selector, None) =>
        SelectorSequence(selector, Some(SelectorCombination(" ", r)))
      case SelectorSequence(selector, Some(selectorCombination)) =>
        SelectorSequence(selector, Some(SelectorCombination(selectorCombination.operator, appendSelectorSequence(selectorCombination.selectorSequence, r))))
    }

}
