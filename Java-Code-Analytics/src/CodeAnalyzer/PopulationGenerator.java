package CodeAnalyzer;

import java.util.List;

import CodeAnalyzer.Parser.MetricsParser;
import CodeAnalyzer.Rules.*;
import CodeAnalyzer.Summary.SummaryItem;

public class PopulationGenerator 
{

	public PopulationGenerator() 
	{
		FileChooser c = new FileChooser();
		c.setSize(500, 350);
		c.setVisible(true);
		
		
	
	}

	public static void main(String[] args) 
	{
		PopulationGenerator gen = new PopulationGenerator();
	}

}
