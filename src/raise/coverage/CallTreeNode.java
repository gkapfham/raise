/**
 * CallTreeNode.java
 * 
 * This class defines a Node object for a method call in a call tree.
 * 
 * @author Adam M. Smith
 */

package raise.coverage;

import java.util.ArrayList; 

public class CallTreeNode extends CallNode implements Cloneable
{
	private CallTreeNode parent;
	private ArrayList<CallTreeNode> children;
	private float executionTime;
	private CallTreeNode cycleCaller; 
	
	private String parentString;
	private boolean constructedParentString;
	
	/*
	 * Constructors
	 */
	public CallTreeNode(int index, String methodName, CallTreeNode parent)
	{
		super(index, methodName);
		this.parent = parent;
		children = new ArrayList<CallTreeNode>();
	}
	
	public CallTreeNode(int index, String methodName, CallTreeNode parent, float executionTime)
	{
		super(index, methodName);
		this.parent = parent;
		children = new ArrayList<CallTreeNode>();
	}
	
	/*
	 *  Creates and returns a copy of this object. 
	 *  
	 *  The general intent is that, for any object x, the expression:
	 *  
	 *  x.clone() != x
	 *  
	 *  will be true, and that the expression:
	 *  
	 *  x.clone().getClass() == x.getClass()
	 *  
	 *  will be true, but these are not absolute requirements. While it is 
	 *  typically the case that:
	 *  
	 *  x.clone().equals(x)
	 *  
	 *  will be true, this is not an absolute requirement.  By convention, 
	 *  the returned object should be obtained by calling super.clone. 
	 *  If a class and all of its superclasses (except Object) obey this 
	 *  convention, it will be the case that 
	 *  
	 *  x.clone().getClass() == x.getClass().
	 *  m
	 *  By convention, the object returned by this method should be 
	 *  independent of this object (which is being cloned). To achieve 
	 *  this independence, it may be necessary to modify one or more 
	 *  fields of the object returned by super.clone before returning it. 
	 *  Typically, this means copying any mutable objects that comprise the 
	 *  internal "deep structure" of the object being cloned and replacing 
	 *  the references to these objects with references to the copies. 
	 *  If a class contains only primitive fields or references to immutable 
	 *  objects, then it is usually the case that no fields in the object 
	 *  returned by super.clone need to be modified.
	 */
	
	public CallTreeNode clone()
	{
		CallTreeNode copy = new CallTreeNode(this.getIndex(), this.getMethodName(), null);
		
		return copy;
	}

	/*
	 * Mutate methods
	 */
	
	public void setParent(CallTreeNode parent)
	{
		this.parent = parent;
	}
	
	public void addChild(CallTreeNode child)
	{
		children.add(child);
	}
	
	public void addChildren(ArrayList<CallTreeNode> children)
	{
		this.children.addAll(children);
	}
	
	public void setExecutionTime(float executionTime)
	{
		this.executionTime = executionTime;
	}
	
	public void setCycleCaller(CallTreeNode cycleCaller)
	{
		this.cycleCaller = cycleCaller;
	}

	/*
	 * Access methods
	 */

	public CallTreeNode getParent()
	{
		return parent;
	}
	
	public ArrayList<CallTreeNode> getChildren()
	{
		return children;
	}
	
	public String getParentString()
	{
		if(constructedParentString)
			return parentString;
	
		else if(this.getMethodName().equals("main"))
			return this.getMethodName();
			
		String sep = "";
		parentString = "";
		
		for(CallTreeNode currentNode = this; currentNode.getParent() != null; currentNode = currentNode.getParent())
			parentString += currentNode.getMethodName() + sep; 
		
		return parentString;
	}
	
	public float getExecutionTime()
	{
		return executionTime;
	}
	
	
}
