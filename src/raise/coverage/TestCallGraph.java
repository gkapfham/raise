package raise.coverage;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import raise.reduce.SetCover;
import raise.reduce.SingleTest;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.framework.TestCase;


public class TestCallGraph extends TestCase 
{
	
	public TestCallGraph(String name)
	{
		super(name);
	}

	public void setUp()
	{
		
	}
	
	public void testFindMethodName()
	{
		String testString = "0.00    0.00       1/158         alloc_args [60]";
		assertEquals("alloc_args",CallGraph.findMethodName(testString));
		
		testString = "                                  59             do_spec_1 <cycle 1> [6]";
		assertEquals("do_spec_1",CallGraph.findMethodName(testString));
	}
	    /* 
	public void testHelloWorldExperiment()
	{
		String[] fileNames = {"/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/hello_world/cc1/hello_world_vanilla_gprof.txt",
					"/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/hello_world/cc1/hello_world_O1_gprof.txt",
					"/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/hello_world/cc1/hello_world_O2_gprof.txt",
					"/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/hello_world/cc1/hello_world_O3_gprof.txt",
					"/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/hello_world/cc1/hello_world_Os_gprof.txt"
					};
					
		CallTree dct;
		
		for(String name : fileNames)
		{
			
			try {
				dct = new CallTree(name);
				
				dct.writeDotFile(name+".calltree.dot");
				
				System.out.println(name+" numNodes: "+dct.getNumNodes());
				
				
				// I'm only leaving this so I remember that it is available
				//dct.writeGraphMLFile("calltree.graphml");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
//   */

 // /*
	public void testMallocExperiment()
	{
		String[] fileNames = {
			"/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/malloc/cc1/malloctest_vanilla_gprof.txt",
			"/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/malloc/cc1/malloctest_O1_gprof.txt",
			"/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/malloc/cc1/malloctest_O2_gprof.txt",
			"/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/malloc/cc1/malloctest_O3_gprof.txt",
			"/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/malloc/cc1/malloctest_Os_gprof.txt"};
		//CallTree dct;
		CallGraph dct;
		for(String name : fileNames)
		{
			
			try {
				//dct = new CallTree(name);
					dct = new CallGraph(name);
				//dct.writeDotFile(name+".calltree.dot");
				
				dct.writeCallGraphToDotFile(name+".callGraph.dot");
				
				//System.out.println(name+" numNodes: "+dct.getNumNodes());
				
				
				// I'm only leaving this so I remember that it is available
				//dct.writeGraphMLFile("calltree.graphml");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// */
	
	 /*
	
	public void testmyMallocExperiment()
	{
		try {
			CallTree dct = new CallTree("/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/malloc/malloc_gprof_out");
		
			dct.writeDotFile("/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/malloc/myMallocCalltree.dot");
			
			System.out.println("myMalloc numNodes: "+dct.getNumNodes());
			
			
			// I'm only leaving this so I remember that it is available
			//dct.writeGraphMLFile("calltree.graphml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// */
	 /*
	public void testAnotherMallocExperiment()
	{
			String file = "/Users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/malloc/gcc/malloctest_Os_gprof.txt";
			
			try {
				ContextInsensitiveCallTree dct = new ContextInsensitiveCallTree(file);
				
				//dct.writeDotFile(name+".calltree.dot");
				
				System.out.println("numNodes: "+dct.getNumNodes());
				
				
				// I'm only leaving this so I remember that it is available
				//dct.writeGraphMLFile("calltree.graphml");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	// */
	
	public void testMallocOutput()
	{
		String file = "/users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/malloc/malloc_gprof_out";
		
		try 
		{
			// Build the dct
			ContextInsensitiveCallTree dct = new ContextInsensitiveCallTree(file);
			
			CallGraph dctcg = (CallGraph) dct;
			
			dctcg.writeCallGraphToDotFile("/users/mr_smith22586/Documents/Regression Testing Research/raise/exampleData/malloc/malloc_gprof_callgraph.dot");
			/* 
			// Make the set Cover
			SetCover cover = dct.constructSetCover();
			System.out.println("# Reqs: "+ cover.getRequirementSubsetUniverse().size() +"\n# Tests: "+cover.getTestSubsets().size());
			
			
			// Iterator for the test set.
			Iterator it = cover.getSingleTests().iterator();
			
			System.out.println("Original Test Suite: ");
			// Print the test Set
			while(it.hasNext())
			{
				System.out.println("\t"+((SingleTest) it.next()).getName());
			}
			
			cover.reduceUsingGreedy("coverage");
			
			// Reset the iterator for the reduced set.
			it = cover.getCoverPickSets().iterator();

			System.out.println("Reduce Test Suite");
			while(it.hasNext())
			{
				System.out.println("\t"+((SingleTest) it.next()).getName());
			}
			// */
		} 
		
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}