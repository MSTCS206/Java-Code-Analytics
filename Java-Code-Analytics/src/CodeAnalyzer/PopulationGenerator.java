package CodeAnalyzer;

import CodeAnalyzer.Rules.*;

public class PopulationGenerator 
{

	public PopulationGenerator() 
	{
		RulesGenerator gen = new RulesGenerator(2);
		Node root = gen.generateRules();
		
		FileChooser c = new FileChooser();
		c.setSize(200, 350);
		c.setVisible(true);
	
	}

	public static void main(String[] args) 
	{
		PopulationGenerator gen = new PopulationGenerator();
	}

}