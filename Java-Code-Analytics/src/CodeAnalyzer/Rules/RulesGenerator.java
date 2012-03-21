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
			
			Rule rule = null;
			
			QualityMetric picked = metrics[random.nextInt(metrics.length)];
			Op op = ops[random.nextInt(ops.length)];
			
			switch(picked)
			{
				case NOM:
					rule = new Rule(picked, 1 + random.nextInt(50), op);//(1, 50)
					break;
			
				case NOF:
					rule = new Rule(picked, random.nextInt(21), op);
					break;
					
				case MLOC:
					rule = new Rule(picked, 5 + random.nextInt(96), op);//(5, 100)
					break;
					
				case DIT:
					rule = new Rule(picked, random.nextInt(5), op);//(0, 4);
					break;
					
				case PAR:
					rule = new Rule(picked, random.nextInt(13), op);//(0, 12);
					break;
					
				case LCOM:
					rule = new Rule(picked, random.nextDouble(), op);//(0, 1);
					break;
				
				default://should never here, just go with NOM values
					rule = new Rule(QualityMetric.NOM, 1 + random.nextInt(50), op);
					break;
			}
			
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
	
	public static String returnRuleString(Node root)
	{
		if(root == null)
			return "";
		
		if(root.getNodeType() == NodeType.Boolean)
		{
			if(root.getBooleanType() == BooleanType.And)
			{
				return "(" + returnRuleString(root.getLeftChild()) + " AND " + returnRuleString(root.getRightChild()) + ")";
			}
			else
			{
				return "(" + returnRuleString(root.getLeftChild()) + " OR " + returnRuleString(root.getRightChild()) + ")";
			}
		}
		else
		{
			return root.getRule().toString();
		}
	}
}
