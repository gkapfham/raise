
/**
 * CallGraph.java
 * 
 * This class defines a DynamicCallTree (DCT) object.
 * A DCT includes a node for each method call, preserving 
 * full execution context at the expense of having unbounded 
 * depth and breadth. 
 * 
 * @author Adam M. Smith
 */

package raise.coverage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

public class CallGraph 
{
	protected Hashtable<String, CallInfoNode> infoNodes;
	
	boolean infoConstructed;
	/*
	 * Default Constructor
	 */
	public CallGraph()
	{
		infoNodes = new Hashtable<String, CallInfoNode>();
		infoConstructed = false;
	}

	/*
	 * This constructor allows for the inclusion of the gprof output file
	 * to use to construct the dct.
	 */
	public CallGraph(String gprofOutputFileName) throws FileNotFoundException 
	{	
		infoNodes = new Hashtable<String, CallInfoNode>();
		parseGProfCallTables(gprofOutputFileName);
		infoConstructed=true;
	}
	
	/**
	 * This method scans the given file for a gprof call graph.
	 * It adds the parsed information to the infoNodes Hashtable.
	 * 
	 * I should make exceptions to be thrown if the read in data
	 * is not as expected.
	 *  
	 * @param gprofOutputFileName
	 * @throws FileNotFoundException
	 */
	public void parseGProfCallTables(String gprofOutputFileName) 
		throws FileNotFoundException
	{	
		// File object for the input file.
		File inputFile = new File(gprofOutputFileName);
		
		// Scanner object to read in the input file.
		Scanner fileScanner = new Scanner(inputFile);
		
		// Scanner object to scan individual lines
		Scanner lineScanner;
		
		// String to hold the lines.
		String line;
		
		// Arraylist to hold the caller nodes in each table before I set up
		// the edges.
		ArrayList<CallInfoNode> callers = new ArrayList<CallInfoNode>();
		
		// Arraylist to hold the called functions in each table before I set 
		// up the edges
		ArrayList<CallInfoNode> callees = new ArrayList<CallInfoNode>();
				
		CallInfoNode tableMain = null;
		
		String[] splitLine = null;
		
		// Skip all of the lines before the call graph tables
		do
		{
			line = fileScanner.nextLine();
			lineScanner = new Scanner(line);
		}while(lineScanner.hasNext() && !lineScanner.next().equals("index") ||
				!lineScanner.hasNext() && fileScanner.hasNext());

		// Catch the case where the call graph is not found.
		if(!fileScanner.hasNext())
		{
			System.out.println("No call tree graph detected."+ 
					"\nExiting program.");
			System.exit(-1);
		}
				
		// Read the first line of the table 
		line = fileScanner.nextLine();
	
		// Start the analysis of the call graph
		do
		{
			boolean callerLoop = true;
			
			// This try-catch block attempts to read <.*> from the line.
			// If "<" and ">" are not present in the string, then the 
			// exception is caught and nothing is skipped.  
			// If such a substring exists, then the length of it is
			// used to see if the table represents a cycle summary
			// table.  If it is, then it is skipped.  If it isn't
			// then the code continues processing the table.
			try
			{
				// This will throw an exception if there are no "<" or ">" characters.
				String cycleText = line.substring(line.indexOf("<")+1, line.indexOf(">"));
				
				if(cycleText.split("\\s+").length == 5)
				{
					// Pop the lines of the cycle table
					while(fileScanner.hasNextLine() && !fileScanner.nextLine().equals(
							"-----------------------------------------------")){}
					line=fileScanner.nextLine();
					// Skip the remaining loop and start at the new table.
					continue;
				}
			}
			catch(StringIndexOutOfBoundsException e)
			{
				// do nothing.
			}
					
			// Before the main function call for the table.  At this point 
			// "line" is set as the first line of the next table
			do
			{
				// Skip if the line is for locore or hicore.  
				// As far as I understand, these are only sentinal values
				// of some kind.  Ignoring them will only cause a problem 
				// if they "call" something.  I don't think this is 
				// possible, but if it ever happens then the code will
				// break.
				if(line.contains("<locore>") || line.contains("<hicore>"))
				{
					//System.out.println("Skipped: " + line);
					//if(fileScanner.hasNextLine())
						line = fileScanner.nextLine();
					
					continue;
				}
				
				splitLine = line.split("\\s+");
				
				// if the first element is of the form [.*] then this is the 
				// main entry for the table.
				//if(splitLine[0].matches("\\[.*]"))
				if(splitLine[0].contains("["))
				{
					//System.out.println("FOUND: "+line);
					// Get the index of the function
					int index = Integer.parseInt(splitLine[splitLine.length-1].split("\\[|\\]")[1]);
					
					// Set the callerLoop boolean to false so the program
					// will handle the remaining table entries as 
					// callee functions
					callerLoop = false;
					
					// Create the main table CallInfoNode object.
					tableMain = new CallInfoNode(index,findMethodName(line));
					
					// Add the parent CallInfoNode objects
					tableMain.addParents(callers);
					
					// Prepare the line variable 
					line = fileScanner.nextLine();
				}
				else // It came before and is a caller
				{	
					//System.out.println("BEFORE: "+line);

					// Get the index of the function.
					int index = Integer.parseInt(splitLine[splitLine.length-1].split("\\[|\\]")[1]);
					
					// Create caller object
					callers.add(new CallInfoNode(index, findMethodName(line)));
					
					// Prepare the line variable
					line = fileScanner.nextLine();
				}
			}while(callerLoop); // Continue as long as the main table entry has
								// not been found.
			
			// Once the main table entry has been found.
			while(!callerLoop)
			{
				// If this line of dashes is found then the code has processed
				// the entire table.
				if(line.equals("-----------------------------------------------"))
				{
					// Setting callerLoop to true will allow the program to 
					// move on to the next table.
					callerLoop = true;
				}
				// If there is more to process in this table.
				else
				{

					//System.out.println("AFTER: "+line);

					// Get the index of the function.
					int index = Integer.parseInt(splitLine[splitLine.length-1].split("\\[|\\]")[1]);
					
					// Create callee CallInfoNode object and add it to the 
					// ArrayList of callee objects that will be added
					// to the main table entry node later.
					callees.add(new CallInfoNode(index,findMethodName(line)));
					
					// Prepare the line variable for the next loop iteration.
					line = fileScanner.nextLine();
				}
			}
			
			
			// Add the children to the tableMain CallInfoNode object
			tableMain.addChildren(callees);
			
			// Clear the callers and callees ArrayLists
			callers.clear();
			callees.clear();
			
			// Add the tableMain CallInfoNode object to infoNodes Hashtable.
			this.infoNodes.put(tableMain.getMethodName(), tableMain);
			
			//System.out.println("INFONODES: "+infoNodes.size());
			
			// Set the tableMain CallInfoNode object to null so that it must
			// be reset during the next iteration of the processing loop.
			tableMain = null;
			
			// Set up the line variable for the next iteration if there are 
			// more tables to be processed.
			if(fileScanner.hasNextLine())
				line = fileScanner.nextLine();
			
		// Continue as long as there are more tables to be processed.
		}while(fileScanner.hasNextLine() && !line.equals(""));
		
		// DEBUG block for printing the contents of the Hashtable.
	/*
		{
			Iterator noderator = infoNodes.values().iterator();
		
			while(noderator.hasNext())
			{
				System.out.println(( (CallInfoNode) noderator.next()).toString()+"\n");
			}
		}
		//*/
	
		/*
		 * It looks like there is no entry for main in the callgraph table, so 
		 * I need to manually make it.  This is going to require way to much
		 * searching...
		 */
		CallInfoNode mainNode = new CallInfoNode("main");
		Iterator<CallInfoNode> searchInfoIt = infoNodes.values().iterator();
		boolean firstInstance = true;
		while(searchInfoIt.hasNext())
		{
			CallInfoNode currentSearchNode = (CallInfoNode) searchInfoIt.next();
			for(CallInfoNode parent : currentSearchNode.getParents())
			{
				if(parent.getMethodName().equals("main"))
				{
					if(firstInstance)
					{
						mainNode.setIndex(parent.getIndex());
						firstInstance = false;
					}
					
					mainNode.addChild(currentSearchNode);
				}
			}
		}
		if(mainNode.getChildren().size() != 0)
			infoNodes.put(mainNode.getMethodName(), mainNode);
	}
	
	/**
	 * Write the graph to a file in the dot file format.
	 */
	
	public void writeCallGraphToDotFile(String fileName) throws IOException
	{
		// includes node names
		String firstPart = "digraph CallGraph {\noverlap=scale\n";
		
		// includes the directed edges.
		String secondPart = "";
		
		CallInfoNode currentNode;
		
		Iterator<CallInfoNode> infoNodeIterator = infoNodes.values().iterator();
		
		while(infoNodeIterator.hasNext())
		{
			currentNode = infoNodeIterator.next();
			
			firstPart += currentNode.getMethodName() +" [label=\""+currentNode.getMethodName()+ "\"]\n";
			
			for(CallInfoNode parent : currentNode.getParents())
				secondPart += parent.getMethodName() + " -> " + currentNode.getMethodName() +"\n";
			
			for(CallInfoNode child : currentNode.getChildren())
				secondPart += currentNode.getMethodName() + " -> " + child.getMethodName() +"\n";
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
	 * Finds the method name for a given entry line in the 
	 * gprof output callgraph tables.
	 * 
	 * @author Adam M. Smith
	 * @param String line
	 * @return String methodName
	 */
	public static String findMethodName(String line)
	{
		String[] splitLine = line.split("\\s+");
		
		if(line.contains("<"))
			return splitLine[splitLine.length-4];
		
		return splitLine[splitLine.length-2];
	}
	
	/**
	 * A debug method for printing the contents of an array.
	 * 
	 * @author Adam M. Smith
	 * @param array
	 */
	private void printArray(String[] array)
	{
		for(String a : array)
			System.out.print(a+",");
		System.out.println();
	}
}
