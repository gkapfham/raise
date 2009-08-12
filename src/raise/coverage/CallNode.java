/**
 * CallNode.java
 * 
 * This class defines a Node object for a method call in a call tree.
 * 
 * @author Adam M. Smith
 */

package raise.coverage;

public class CallNode 
{
	private String methodName;
	private float executionTime;
	private int index;
	
	public CallNode(String methodName)
	{
		this.methodName = methodName;
	}
	
	public CallNode(int index)
	{
		this.index = index;
	}
	
	public CallNode(int index, String methodName)
	{
		this.index = index;
		this.methodName = methodName;
	}
	
	public CallNode(int index, String methodName, float executionTime)
	{
		this.index = index;
		this.methodName = methodName;
		this.executionTime = executionTime;
	}
	
	
	/*
	 * Access methods
	 */
	
	public String getMethodName()
	{
		return methodName;
	}
	
	public int getIndex()
	{
		return this.index;
	}
	public float getExecutionTime()
	{
		return executionTime;
	}
	
	/*
	 * Mutate methods
	 */
	public void setMethodName(String methodName)
	{
		this.methodName = methodName; 
	}
	
	public void setExecutionTime(float executionTime)
	{
		this.executionTime = executionTime;
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
}
