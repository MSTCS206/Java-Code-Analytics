package CodeAnalyzer.Summary;

import java.util.List;

import CodeAnalyzer.Rules.Node;

public class Individual 
{
	private Node rules;
	private List<SummaryItem> summary;
	
	public Individual()
	{
		
	}
	
	public void setRules(Node rules)
	{
		this.rules = rules;
	}
	
	public Node getRules()
	{
		return rules;
	}
	
	public void setSummary(List<SummaryItem> summary)
	{
		this.summary = summary;
	}
	
	public List<SummaryItem> getSummary()
	{
		return summary;
	}
	
}
