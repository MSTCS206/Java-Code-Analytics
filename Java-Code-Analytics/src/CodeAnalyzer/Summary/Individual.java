package CodeAnalyzer.Summary;

import java.util.LinkedList;
import java.util.List;

import CodeAnalyzer.Rules.Node;
import CodeAnalyzer.Rules.RulesGenerator;

public class Individual 
{
	private Node rules;
	private List<SummaryItem> summary;
	
	public Individual()
	{
		summary = new LinkedList<SummaryItem>();
	}
	
	public void setRules(Node rules)
	{
		this.rules = rules;
	}
	
	public Node getRules()
	{
		return rules;
	}
	
	public List<SummaryItem> getSummary()
	{
		return summary;
	}
	
	public String getRuleString()
	{
		return RulesGenerator.returnRuleString(rules);
	}
}
