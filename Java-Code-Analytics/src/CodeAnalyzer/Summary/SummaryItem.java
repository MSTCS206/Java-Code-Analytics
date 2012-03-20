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
	private String source;
	private SummaryType type;
	
	private HashMap<String, Double> metrics;
	
	public SummaryItem(String clas, String method, String source, SummaryType type)
	{
		this.className = clas;
		this.methodName = method;
		this.source = source;
		this.type = type;
		
		metrics = new HashMap<String, Double>();	
	}
	
	public SummaryItem(String clas, String source, SummaryType type)
	{
		this.className = clas;
		this.methodName = "";
		this.source = source;
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
