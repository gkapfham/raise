/**
 * CallTree.java
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

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;

import raise.reduce.SetCover;
import raise.reduce.SingleTest;
import raise.reduce.SingleTestSubset;
import raise.reduce.RequirementSubset;

public class CallTree extends CallGraph
{
	private final String rootName = "main";
	protected int numNodes;
	CallTreeNode rootNode;
	
	public CallTree(String fileName) throws FileNotFoundException
	{
		super(fileName);
		numNodes = 0;
	}
	
	/**
	 * EnumerateCallPaths
	 * 
	 * Performs a depth first search of the tree and stores all of the call paths
	 * to be used as requirements.
	 * 
	 * ASSUMES: - No duplicate nodes called by main.
	 * 			- All nodes directly under main are tests.
	 * 
	 */
	public SetCover constructSetCover()
	{
		// Hashtables to hold the RequirementSubset and SingleTestSubset Objects
		Hashtable<String, SingleTestSubset> singleTestSubsets = new Hashtable<String, SingleTestSubset>();
		Hashtable<String, RequirementSubset> requirementSubsets = new Hashtable<String, RequirementSubset>();
		
		// The setCover to return
		SetCover cover = new SetCover();
		
		// Reference to current node.
		CallTreeNode currentNode;
		
		// The visitor list for search the tree
		ArrayList<CallTreeNode> visitList = new ArrayList<CallTreeNode>();
		
		// References to the current SingleTestSubset object and the current
		// RequirementSubset object.
		SingleTestSubset currentSingleTestSubset = null;
		RequirementSubset currentRequirementSubset = null;
		
		// Add the rootNode to the visitList to start the search
		visitList.add(rootNode);
		
		// A new index is assigned to each RequirementSubset object that is 
		// generated.
		int reqIndex = 0;
		
		// Keeping track of how many node are searched.
		int visited = 0;
		
		// While there are objects left in the visitList.
		while(visitList.size() > 0)
		{
			
			// Get the last node on the list to facilitate depth first search.
			currentNode = visitList.remove(visitList.size()-1);
			// Update the number of visited nodes.
			visited++;
			
			// If the current node is not main and is called by main
			// then it is considered a test method.
			if(currentNode.getParent() != null && currentNode.getParent().getMethodName().equals(rootName))
			{	
				// Create a singleTest object for 
				currentSingleTestSubset = new SingleTestSubset(new SingleTest(currentNode.getMethodName(),currentNode.getIndex()));
			
				// Add the SingleTest object to the Hashtable.
				singleTestSubsets.put(currentNode.getMethodName(), currentSingleTestSubset);
			}
			
			// If the currentNode is a leaf node
			if(currentNode.getChildren().size() == 0)
			{
				if(!currentNode.getParent().equals(rootNode))
				{	
					// Create and initialize the path variable.
					String path = "";
					
					// Finish the path variable by combining the names of all of 
					// the parents up to one below main.
					for(CallTreeNode searchNode = currentNode; 
					!searchNode.getMethodName().equals(currentSingleTestSubset.getTest().getName()) && !searchNode.getMethodName().equals(rootName);
					searchNode = searchNode.getParent())
					{
						path += searchNode.getMethodName();
					}
				
					// If there is a RequirementSubset object for the leaf node 
					// then set the reference to the object from the hashtable.
					if(requirementSubsets.containsKey(path))
						currentRequirementSubset = requirementSubsets.get(path);
					
					// If there is not an existing RequirementSubset object then
					// create the requirement and add it to the SetCover object.
					else
					{
						// Create the RequirementSubset object.
						currentRequirementSubset = new RequirementSubset(path,reqIndex);
						// Update the index.
						reqIndex++;
						// Add the RequirementSubset object to the hashtable.
						requirementSubsets.put(path,currentRequirementSubset);
					}
					
					// If the SingleTestSubset object does not already have the
					// RequirementSubset object then add it.
					if(!currentSingleTestSubset.getRequirementSubsetSet().contains(currentRequirementSubset))
						currentSingleTestSubset.addRequirementSubset(currentRequirementSubset);
					
					// If the RequirementSubset object does not already have the
					// SingleTestSubset object then add it.
					if(!currentRequirementSubset.getCoveringTests().contains(currentSingleTestSubset.getTest()))
						currentRequirementSubset.addCoveringTest(currentSingleTestSubset.getTest());
				}
			}
			// If the current node is not a test node or a leaf node 
			else
			{
				//do nothing...?
			}
			
			// Add the children of the current node to the visitList
			visitList.addAll(currentNode.getChildren());
		}
		
		
		
		// Create LinkedHashSet objects for the RequirementSubset and 
		// SingleTestSubset objects.
		LinkedHashSet<RequirementSubset> reqs = new LinkedHashSet<RequirementSubset>();
		LinkedHashSet<SingleTestSubset> sets = new LinkedHashSet<SingleTestSubset>();
		
		// Create iterators to move the items in the Hashtable objects to the LinkedHashSet objects
		Iterator<RequirementSubset> requirementSubsetIterator = requirementSubsets.values().iterator();
		Iterator<SingleTestSubset> singleTestSubsetsIterator =  singleTestSubsets.values().iterator(); 
		
		
		// Add the objects from the Hashtable objects to the LinkedHashSet objects.
		while(requirementSubsetIterator.hasNext())
		{
			reqs.add((RequirementSubset) requirementSubsetIterator.next());
		}
		while(singleTestSubsetsIterator.hasNext())
		{
			sets.add((SingleTestSubset) singleTestSubsetsIterator.next());
		}
		
		// Add the LinkedHashSet objects to the SetCover object.
		cover.addRequirementSubsets(reqs);
		cover.addSingleTestSubsets(sets);
		
		// return the SetCover object.
		return cover;
	}
	
	/**
	 * Writes out a dot file for the tree.
	 */
	public void writeDotFile(String fileName) throws IOException
	{
		// includes node names
		String firstPart = "digraph CallTree {\noverlap=scale\n";
		
		// includes the directed edges.
		String secondPart = "";
		
		CallTreeNode currentNode;
		
		ArrayList<CallTreeNode> visitList = new ArrayList<CallTreeNode>();
		
		visitList.add(rootNode);
		
		while(visitList.size() > 0)
		{
			currentNode = visitList.remove(0);
			
			System.out.println("here");
			System.out.println(currentNode== null);
			
			firstPart += currentNode.getParentString() +" [label=\""+currentNode.getMethodName()+ "\"]\n";
			
			if(!currentNode.getMethodName().equals("main"))
				secondPart += currentNode.getParent().getParentString() + " -> " + currentNode.getParentString() +"\n";
			
			visitList.addAll(currentNode.getChildren());
		}
		
		secondPart += "}\n";
		
		// Create file 
	    FileWriter fstream = new FileWriter(fileName);
	        BufferedWriter out = new BufferedWriter(fstream);
	    out.write(firstPart + secondPart);
	    //Close the output stream
	    out.close();
		
	}
	
	/**
	 * Writes out a dot file for the tree.
	 */
	public void writeGraphMLFile(String fileName) throws IOException
	{
		// includes node names
		String firstPart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
		"<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\"\n"+
		"   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"+
		"   xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns\n"+
		"   http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd\">"+
		"\n\t<graph id=\"G\" edgedefault=\"directed\">\n";
		
		// includes the directed edges.
		String secondPart = "";
		
		CallTreeNode currentNode;
		
		ArrayList<CallTreeNode> visitList = new ArrayList<CallTreeNode>();
		
		visitList.add(rootNode);
		
		while(visitList.size() > 0)
		{
			currentNode = visitList.remove(0);
			
			firstPart += "\t\t<node id=\""+currentNode.getParentString() +"\"/>\n";
			
			if(!currentNode.getMethodName().equals("main"))
				secondPart += "\t\t<edge source=\""+currentNode.getParent().getParentString() + "\" target=\"" + currentNode.getParentString() +"\"/>\n";
			
			visitList.addAll(currentNode.getChildren());
		}
		
		secondPart += "\t</graph>\n</graphml>";
		
		// Create file 
	    FileWriter fstream = new FileWriter(fileName);
	        BufferedWriter out = new BufferedWriter(fstream);
	    out.write(firstPart + secondPart);
	    //Close the output stream
	    out.close();
		
	}
	
	public int getNumNodes()
	{
		return numNodes;
	}
}
