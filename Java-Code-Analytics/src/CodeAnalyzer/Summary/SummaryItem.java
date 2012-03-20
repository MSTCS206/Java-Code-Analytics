package CodeAnalyzer.Summary;

import java.util.HashMap;

public class SummaryItem 
{
	public enum SummaryType
	{
		Class,
		Method
	}
	
	private String name;
	private String source;
	private SummaryType type;
	
	private HashMap<String, Double> metrics;
	
	public SummaryItem(String name, String source, SummaryType type)
	{
		this.name = name;
		this.source = source;
		this.type = type;
		
		metrics = new HashMap<String, Double>();	
	}
	
	public SummaryItem(String name, SummaryType type)
	{
		this.name = name;
		this.source = "";
		this.type = type;
		
		metrics = new HashMap<String, Double>();
	}
	
	public HashMap<String, Double> getMetrics()
	{
		return metrics;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSource()
	{
		return source;
	}
	
	public SummaryType getSummaryType()
	{
		return type;
	}
}
