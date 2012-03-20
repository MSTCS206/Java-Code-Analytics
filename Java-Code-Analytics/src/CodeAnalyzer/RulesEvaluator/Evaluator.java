package CodeAnalyzer.RulesEvaluator;

import CodeAnalyzer.Rules.Rule;

public class Evaluator {
	
	private List<SummaryItem> items;
	
	public Evaluator(List<SummaryItem> items)    // pass the constructor the entire list of summary items 
	{
		this.items = items;
	}

	public Evaluate(Individual Bob)
	{
		Iterator itr = this.items.iterator();
		while(itr.hasNext())
		{
			SummaryItem sumItem = itr.next();
			if( evalTree(Bob.rules, sumItem))
			{
				Bob.summary.add(sumItem);
			}
		}
	}

	private boolean evalTree(Node rulenode, SummaryItem sumItem)
	{
		if(rulenode.NodeType == Boolean)
		{
			if(rulenode.BooleanType == And)
			{
				return( evalTree(rulenode.leftChild, sumItem) && evalTree(rulenode.rightChild, sumItem));  //recursively goes down the tree
			} 
			else
			{
				return( evalTree(rulenode.leftChild, sumItem) || evalTree(rulenode.rightChild, sumItem));
			}
		}
		
		else
		{
			return( evalRule(rulenode.rule, sumItem));
		}
	}
	
	private boolean evalRule(Rule rule, SummaryItem sumItem)
	{
		switch( rule.getCompare() )
		{
			case GT:
				return sumItem.getMetrics() > rule.getValue();
				break;
			case GTE:
				return sumItem.getMetrics()>= rule.getValue();
				break;
			case LTE: 
				return sumItem.getMetrics() <= rule.getValue();
				break;
			case LT:
				return sumItem.getMetrics() < rule.getValue();
				break;
			default:
				break;
		}
	}
}
