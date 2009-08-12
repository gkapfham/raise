/**
 * CallInfo.java
 * 
 * This class defines a Node object for a method call in a call tree.
 * 
 * @author Adam M. Smith
 */

package raise.coverage;

import java.util.ArrayList; 

public class CallInfoNode extends CallNode
{
	private ArrayList<CallInfoNode> parents;
	private ArrayList<CallInfoNode> children;
	
	public CallInfoNode(String methodName)
	{
		super(methodName);
		this.children = new ArrayList<CallInfoNode>();
		this.parents = new ArrayList<CallInfoNode>();
	}
	
	public CallInfoNode(int index, String methodName)
	{
		super(index, methodName);
		this.children = new ArrayList<CallInfoNode>();
		this.parents = new ArrayList<CallInfoNode>();
	}
	
	public CallInfoNode(int index, String methodName, ArrayList<CallInfoNode> parents)
	{
		super(index, methodName);
		this.parents = parents;
		this.children = new ArrayList<CallInfoNode>();
	}
	
	public CallInfoNode(int index, String methodName, ArrayList<CallInfoNode> parents, float executionTime)
	{
		super(index, methodName, executionTime);
		this.parents = parents;
		this.children = new ArrayList<CallInfoNode>();
	}
	
	public CallInfoNode(int index, String methodName, ArrayList<CallInfoNode> parents, ArrayList<CallInfoNode> children, float executionTime)
	{
		super(index, methodName, executionTime);
		this.parents = parents;
		this.children = children;
	}
	
	/*
	 * Access methods
	 */
	
	public void addParents(ArrayList<CallInfoNode> parents)
	{
		if(parents != null)
			this.parents.addAll(parents);
	}
	
	public void addChild(CallInfoNode child)
	{
		this.children.add(child);
	}
	
	public void addChildren(ArrayList<CallInfoNode> children)
	{
		if(parents != null)
			this.children.addAll(children);
	}
	
	
	/*
	 * Mutate methods
	 */
	
	public ArrayList<CallInfoNode> getParents()
	{
		return parents;
	}
	
	public ArrayList<CallInfoNode> getChildren()
	{
		return this.children;
	}
	

	
	/*
	 * toString
	 */

	public String toString()
	{
		String toReturn="Method: "+this.getMethodName()+"\nIndex: "+
			this.getIndex()+"\nParents:\n";
		
		for(CallInfoNode parent : this.parents)
			toReturn += "\t"+parent.getMethodName()+"\n";
		
		toReturn+="Children:\n";
		
		for(CallInfoNode child : this.children)
			toReturn += "\t"+child.getMethodName()+"\n";
		
		return toReturn;
	}
}
