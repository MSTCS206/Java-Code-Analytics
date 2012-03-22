package CodeAnalyzer.RulesEvaluator;

import java.util.Iterator;
import java.util.List;

import CodeAnalyzer.Rules.Node;
import CodeAnalyzer.Rules.Node.BooleanType;
import CodeAnalyzer.Rules.Node.NodeType;
import CodeAnalyzer.Rules.Rule;
import CodeAnalyzer.Summary.Individual;
import CodeAnalyzer.Summary.SummaryItem;

public class Evaluator {
	
	private static final NodeType Boolean = null;
	private static final BooleanType And = null;
	private List<SummaryItem> items;       //every summary item up for consideration
	
	public Evaluator(List<SummaryItem> items)    // pass the constructor the entire list of summary items 
	{
		this.items = items;
	}

	public void Evaluate(Individual Bob)         // call this for each element
	{
		Iterator<SummaryItem> itr = this.items.iterator();
		while(itr.hasNext())
		{
			SummaryItem sumItem = (SummaryItem) itr.next();
			if( evalTree(Bob.getRules(), sumItem))
			{
				Bob.getSummary().add(sumItem);
			}
		}
	}

	private boolean evalTree(Node rulenode, SummaryItem sumItem)   // Uses recursion to evaluate the expression tree
	{
		if(rulenode.getNodeType() == NodeType.Boolean)
		{
			if(rulenode.getBooleanType() == BooleanType.And)
			{
				return( evalTree(rulenode.getLeftChild(), sumItem) && evalTree(rulenode.getRightChild(), sumItem));  //recursive call 
			} 
			else
			{
				return( evalTree(rulenode.getLeftChild(), sumItem) || evalTree(rulenode.getRightChild(), sumItem));
			}
		}
		
		else
		{
			return( evalRule(rulenode.getRule(), sumItem));  //
		}
	}
	
	private boolean evalRule(Rule rule, SummaryItem sumItem)
	{
		if(sumItem.getMetrics().get(rule.getMetric()) == null)
			return false;
		
		switch( rule.getCompare() )
		{
			case GT:
				return ( sumItem.getMetrics().get(rule.getMetric()) > rule.getValue());
				
			case GTE:
				return (sumItem.getMetrics().get(rule.getMetric()) >= rule.getValue());
				
			case LTE: 
				return (sumItem.getMetrics().get(rule.getMetric()) <= rule.getValue());
				
			case LT:
				return (sumItem.getMetrics().get(rule.getMetric()) < rule.getValue());
				
			default:
				return false;
		}
	}
}
