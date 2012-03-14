package CodeAnalyzer.Rules;

public class Rule 
{
	//quality metric, LOC, SLOC, etc
	private QualityMetric metric;
	
	//numberical value
	private int value;
	
	//Operator that is used to compare, like >=, <=, etc
	private Op compare;
	
	public Rule(QualityMetric metric, int value, Op compare)
	{
		this.metric = metric;
		this.value = value;
		this.compare = compare;
	}
	
	public QualityMetric getMetric()
	{
		return metric;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public Op getCompare()
	{
		return compare;
	}
}
