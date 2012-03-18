package CodeAnalyzer;

import java.util.List;

import CodeAnalyzer.Parser.XMLParser;
import CodeAnalyzer.Rules.*;
import CodeAnalyzer.Summary.SummaryItem;

public class PopulationGenerator 
{

	public PopulationGenerator() 
	{
		RulesGenerator gen = new RulesGenerator(2);
		Node root = gen.generateRules();
		
		XMLParser p = new XMLParser("C:\\Users\\RyanGamer\\Desktop\\CS206\\JLayer-Metrics.xml");
		List<SummaryItem> s = p.getFragments();
		
		FileChooser c = new FileChooser();
		c.setSize(200, 350);
		c.setVisible(true);
	
	}

	public static void main(String[] args) 
	{
		PopulationGenerator gen = new PopulationGenerator();
	}

}
