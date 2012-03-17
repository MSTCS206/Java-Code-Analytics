package CodeAnalyzer.Summary;

import java.util.HashMap;

public class SummaryItem 
{
	public enum SummaryType
	{
		Class,
		Method
	}
	
	private String className;
	private String methodName;
	private SummaryType type;
	
	private HashMap<String, Double> metrics;
	
	public SummaryItem(String clas, String method, SummaryType type)
	{
		this.className = clas;
		this.methodName = method;
		this.type = type;
		
		metrics = new HashMap<String, Double>();	
	}
	
	public SummaryItem(String clas, SummaryType type)
	{
		this.className = clas;
		this.methodName = "";
		this.type = type;
		
		metrics = new HashMap<String, Double>();
	}
	
	public HashMap<String, Double> getMetrics()
	{
		return metrics;
	}
	
	public String getClassName()
	{
		return className;
	}
	
	public String getMethodName()
	{
		return methodName;
	}
	
	public SummaryType getSummaryType()
	{
		return type;
	}
}
