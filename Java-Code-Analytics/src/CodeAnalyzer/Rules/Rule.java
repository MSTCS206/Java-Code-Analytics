package CodeAnalyzer.Rules;

public class Rule 
{
	private QualityMetric metric;
	private int value;
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
