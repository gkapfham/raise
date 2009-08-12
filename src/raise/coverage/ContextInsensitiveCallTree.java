/**
 * ContextInsensitiveCallTree.java
 * 
 * This class outlines a generic context insensitive call tree from the
 * information in the super class's infoNodes object.  The gprof tool collects
 * a context-insensitive call graph and therefore a cct or dct cannot be 
 * constructed.  
 * 
 *  TODO Use a context sensitive profiler and create classes to support 
 *  cct and dct construction.
 */

package raise.coverage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ContextInsensitiveCallTree extends CallTree
{
	
	public ContextInsensitiveCallTree(String fileName) throws FileNotFoundException
	{
		super(fileName);
		constructCallTree();
	}
	
	public ContextInsensitiveCallTree constructCallTree() throws FileNotFoundException
	{
		if(!infoConstructed)
		{
			System.out.println("You must specify a gprof output file or have"+
					" constructed the CallGraphInfo before constructing the "+
					"call tree.");
			System.exit(-1);
		}
		
		return constructCallTree(null);
	}
	
	public ContextInsensitiveCallTree constructCallTree(String file) throws FileNotFoundException
	{	
		// The name of the method to use as the root node
		String root = "main";
		
		if(!infoNodes.containsKey(root))
		{
			System.out.println("Root not found.");
			return null;
		}
		
		ArrayList<CallTreeNode> visitList = new ArrayList<CallTreeNode>();
		
		rootNode = new CallTreeNode(((CallInfoNode) infoNodes.get(root)).getIndex(), root, null);
					
		CallTreeNode currentNode = (CallTreeNode) rootNode;
		
		do
		{	
			boolean cycle = false;
			
			for(CallInfoNode ciNode : ((CallInfoNode) infoNodes.get(currentNode.getMethodName())).getChildren())
			{
				// This loop makes sure that we don't have a cycle.
				for(CallTreeNode searchNode = currentNode;!searchNode.getMethodName().equals("main");searchNode=searchNode.getParent())
				{
					if(ciNode.getMethodName().equals(searchNode.getMethodName()))
					{
						cycle = true;
						searchNode.setCycleCaller(currentNode);
					}
				}
				
				// if the ciNode is not a recursive/cyclic call then add it to the tree
				if(!cycle)
				{
					//if(ciNode.getMethodName().equals("check_live_switch"))
					//	System.out.println(ciNode.getMethodName());
					
					CallTreeNode newNode = new CallTreeNode(ciNode.getIndex(), ciNode.getMethodName(), currentNode);
					currentNode.addChild(newNode);
					visitList.add(newNode);
					numNodes++;
				}
				
				//TODO Handle cycles.  Adding this functionality will require me to change
				// the way that I traverse the tree later.
				else
				{
					
				}
				
				cycle = false;
			}
			
			if(!visitList.isEmpty())
				currentNode=visitList.remove(0);
			else
				break;
			
		}while(true);
		
		System.out.println("Finished Processing.");
		
		return this;
	}
}
