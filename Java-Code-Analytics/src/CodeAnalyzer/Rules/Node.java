package CodeAnalyzer.Rules;


public class Node 
{
	public enum NodeType
	{
		Boolean,
		Metric
	}
	
	public enum BooleanType
	{
		And,
		Or
	}
	
	private NodeType type;
	
	//if type is Boolean, then logical is filled
	private BooleanType booltype;
	
	//if type is Metric, it has a rule
	private Rule rule;
	
	private Node leftChild, rightChild;
	
	
	public Node()
	{
		//default to a 'leaf' node, ie NodeType.Metric
		type = NodeType.Metric;
	}
	
	public Node(NodeType type)
	{
		this.type = type;
	}
	
	public NodeType getNodeType()
	{
		return type;
	}
	
	public BooleanType getBooleanType()
	{
		return booltype;
	}
	
	public void setBooleanType(BooleanType booltype)
	{
		this.booltype = booltype;
	}
	
	public Rule getRule()
	{
		return rule;
	}
	
	public void setRule(Rule rule)
	{
		this.rule = rule;
	}
	
	public Node getLeftChild()
	{
		return leftChild;
	}
	
	public Node getRightChild()
	{
		return rightChild;
	}
	
	public void setLeftChild(Node child)
	{
		leftChild = child;
	}
	
	public void setRightChild(Node child)
	{
		rightChild = child;
	}
}
