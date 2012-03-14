package CodeAnalyzer.Rules;

import java.util.*;

import CodeAnalyzer.Rules.Node.BooleanType;
import CodeAnalyzer.Rules.Node.NodeType;

public class RulesGenerator 
{
	private Random random;
	
	//this is the depth of the tree
	//note, depth=1 makes root node + children
	private int depth;
	
	public RulesGenerator(int maxdepth)
	{
		depth = maxdepth;
		
		random = new Random();
	}
	
	public Node generateRules()
	{		
		return generateRules(0);
	}
	
	private Node generateRules(int currentLevel)
	{
		//if we have reached path the depth we want, then return back
		//NOTE, if the code below this is doing its job, this code will never be reached
		if(currentLevel > depth)
			return null;
		
		Node temp;
		
		//if we are at the bottom level, its going to be a metric rule
		if(currentLevel >= depth)
		{
			temp = new Node(NodeType.Metric);
			
			//create the random rule
			QualityMetric [] metrics = QualityMetric.values();
			Op [] ops = Op.values();
			
			Rule rule = new Rule(metrics[random.nextInt(metrics.length)], random.nextInt(5000), ops[random.nextInt(ops.length)]);
			
			temp.setRule(rule);
		}
		else
		{
			temp = new Node(NodeType.Boolean);
			
			//set the random boolean type
			BooleanType [] vals = BooleanType.values();
			temp.setBooleanType(vals[random.nextInt(vals.length)]);
			
			//setup children nodes
			temp.setLeftChild(generateRules(currentLevel + 1));
			temp.setRightChild(generateRules(currentLevel + 1));
		}
		
		return temp;
	}
}
