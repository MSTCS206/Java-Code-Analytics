package CodeAnalyzer;

import java.util.List;

import CodeAnalyzer.Parser.MetricsParser;
import CodeAnalyzer.Rules.*;
import CodeAnalyzer.Summary.SummaryItem;

public class PopulationGenerator 
{

	public PopulationGenerator() 
	{
		/*RulesGenerator gen = new RulesGenerator(2);
		Node root = gen.generateRules();
		
		MetricsParser p = new MetricsParser("C:\\Users\\RyanGamer\\Desktop\\CS206\\JLayer-Metrics.xml");
		List<SummaryItem> s = p.getFragments();
		*/
		
		RulesGenerator gen = new RulesGenerator(3);
		System.out.println(gen.returnRuleString(gen.generateRules()));
		
		FileChooser c = new FileChooser();
		c.setSize(500, 350);
		c.setVisible(true);
		
		
	
	}

	public static void main(String[] args) 
	{
		PopulationGenerator gen = new PopulationGenerator();
	}

}
