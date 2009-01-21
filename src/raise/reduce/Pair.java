/*---------------------------------------------------------------------
 *  File: $Id: Pair.java,v 1.9 2007/08/02 20:25:23 smitha Exp $   
 *  
 *  Project: DIATOMS, Database drIven Application Testing tOol ModuleS
 * 
 *   THIS FILE IS NO LONGER NEEDED AS OF 06/04/08
 *
 *--------------------------------------------------------------------*/
 
 package raise.reduce;
 
 import java.util.LinkedHashSet;
 import java.util.Iterator;
 
 public class Pair {
 	
 	LinkedHashSet reqsCovered;
 	SingleTestSubset test1;
 	SingleTestSubset test2;
 	int numReqsCovered;
 	double ratio;
 	double time;
 	String metric;
 
 	public Pair() {
 		test1=null;
 		test2=null;
 		numReqsCovered = 0;
 		ratio=0;
 		time=0;	
  	}
  	
  	public Pair(SingleTestSubset t1, SingleTestSubset t2, String metric) {
 		// Create the list of RequirementSubsets that are covered by the union of the two
 		// RequirementSubsetSets of the SingleTestSubsets.
 		reqsCovered = new LinkedHashSet();
 		
 		// Assign the tests to local references.
 		test1 = t1;
 		test2 = t2;
 		
 		time = (((test1.getTest()).getCost())+((test2.getTest()).getCost()));
 		
 		if(!metric.equals("time"))
 		{
	 		// Add all of the RequirementSubsets from test1 into the reqsCovered list
	 		reqsCovered.addAll(test1.getRequirementSubsetSet());
	 		
	 		//Extract an iterator over the RequirementSubsets covered 		
	 		Iterator test2ReqIterator = test2.getRequirementSubsetSet().iterator();

	 		// While the test2ReqIterator has more objects
	 		while (test2ReqIterator.hasNext()) {
	 		
	 			// Get the current RequirementSubset
				RequirementSubset currentReq = (RequirementSubset) test2ReqIterator.next();
			
				// If the current RequirementSubset is not in the reqsCovered list
	 			if (!reqsCovered.contains(currentReq)){
	 				// Add it
	 				reqsCovered.add(currentReq);
	 			}
	 		} 	
	  		numReqsCovered = reqsCovered.size();
	  		ratio = (numReqsCovered/time);
  		}
  		else
  		{
  			numReqsCovered = -1;
  			ratio = -1;
  		}
  		
  		
  	}
  	
  	
  	public int getNumReqsCovered()
  	{
  		return numReqsCovered;
  	}
  	
  	public double getRatio()
  	{
  		return ratio;
  	}
  	
  	public double getTime()
  	{
  		return time;
  	}
  	
  	public SingleTestSubset getTest1() 
  	{
  		return test1;
  	}
  	
  	public SingleTestSubset getTest2() 
  	{
  		return test2;
  	}
  	
  	public LinkedHashSet getTest1CoveredRequirements() 
  	{
  		return test1.getRequirementSubsetSet();
  	}
  	
  	public LinkedHashSet getTest2CoveredRequirements() 
  	{
  		return test2.getRequirementSubsetSet();
  	}
  	
  	public String toString()
  	{
  		return ("\nTest1: "+ test1+"\nTest2: "+test2+": \n\tCoverage: "+numReqsCovered+"\n\tTime: "+"\n\tRatio: "+ratio+"\n\n");
  	}
  	
}
