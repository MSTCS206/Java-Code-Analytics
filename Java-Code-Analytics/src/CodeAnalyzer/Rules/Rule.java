package CodeAnalyzer.Rules;

public class Rule 
{
	//quality metric, LOC, SLOC, etc
	private QualityMetric metric;
	
	//numberical value
	private double value;
	
	//Operator that is used to compare, like >=, <=, etc
	private Op compare;
	
	public Rule(QualityMetric metric, double value, Op compare)
	{
		this.metric = metric;
		this.value = value;
		this.compare = compare;
	}
	
	public QualityMetric getMetric()
	{
		return metric;
	}
	
	public double getValue()
	{
		return value;
	}
	
	public Op getCompare()
	{
		return compare;
	}
	
	public String toString()
	{
		String op = "";
		switch(compare)
		{
			case LT:
				op = "<";
				break;
			case LTE:
				op = "<=";
				break;
			case GT:
				op = ">";
				break;
			case GTE:
				op = ">=";
				break;
		}
		
		String met = "";
		switch(metric)
		{
			case NOF:
				met = "NumberOfAttributes";
				break;
			case NOM:
				met = "NumberOfMethods";
				break;
			case MLOC:
				met = "MethodLinesOfCode";
				break;
			case DIT:
				met = "DepthOfInheritanceTree";
				break;
			case PAR:
				met = "NumberOfParameters";
				break;
			case LCOM:
				met = "LackOfCohesionOfMethods";
				break;
		}
		
		return met + " " + op + " " + value;
	}
}
