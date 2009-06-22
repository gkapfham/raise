/*---------------------------------------------------------------------
 *  File: TestCover.java, 2008/01/22 13:36:17 smitha   
 *  Version:  $Revision: 1.26 $
 *
 *  Project: raise, Reduce And prIoritize SuitEs
 *
 *--------------------------------------------------------------------*/

package raise.reduce;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Vector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 *  Test suite for the SetCover class.
 *
 *  @author Adam M. Smith 1/22/2008
 */

public class TestSetCover extends TestCase
{
	SetCover cover;
	SetCover cover2;
	LinkedHashSet<SingleTest> covered;

	 /*
     *  Required constructor.
     *  
     *  @author Adam M. Smith 7/02/2007
     */
    public TestSetCover(String name)
    {
    	super(name);
	 }

   /**
     *  Construct the fresh instance of the SetCover.
     *  
     *  @author Adam M. Smith 7/02/2007
     */
    public void setUp()
    {
  		cover = new SetCover();
  		covered = new LinkedHashSet<SingleTest>();
  		cover2 = new SetCover();
	 }
    
	 public void testProperNumberOfRequirementsAndTests()
	 {
	 	cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/AdamCoverage.dat","data/raise/reduce/setCovers/AdamTime.dat");
	 	
	 	assertEquals(8,(cover.getRequirementSubsetUniverse()).size());
	 	assertEquals(6,(cover.getTestSubsets()).size());
	 }


	 public void testIdenticalConstructionAdamExample()
	 {
	 
	  	SingleTest test0 = new SingleTest("SingleTest0",0,4.0);
		SingleTest test1 = new SingleTest("SingleTest1",1,1.0);
		SingleTest test2 = new SingleTest("SingleTest2",2,5.0);
		SingleTest test3 = new SingleTest("SingleTest3",3,2.0);
		SingleTest test4 = new SingleTest("SingleTest4",4,3.0);
		SingleTest test5 = new SingleTest("SingleTest5",5,2.0);

		RequirementSubset req0 = new RequirementSubset("RequirementSubset0",0);
		RequirementSubset req1 = new RequirementSubset("RequirementSubset1",1);
		RequirementSubset req2 = new RequirementSubset("RequirementSubset2",2);
		RequirementSubset req3 = new RequirementSubset("RequirementSubset3",3);
		RequirementSubset req4 = new RequirementSubset("RequirementSubset4",4);
		RequirementSubset req5 = new RequirementSubset("RequirementSubset5",5);
		RequirementSubset req6 = new RequirementSubset("RequirementSubset6",6);
		RequirementSubset req7 = new RequirementSubset("RequirementSubset7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover2.addRequirementSubset(req0);
		cover2.addRequirementSubset(req1);
		cover2.addRequirementSubset(req2);
		cover2.addRequirementSubset(req3);
		cover2.addRequirementSubset(req4);
		cover2.addRequirementSubset(req5);
		cover2.addRequirementSubset(req6);
		cover2.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover2.addSingleTestSubset(STS0);
		cover2.addSingleTestSubset(STS1);
		cover2.addSingleTestSubset(STS2);
		cover2.addSingleTestSubset(STS3);
		cover2.addSingleTestSubset(STS4);
		cover2.addSingleTestSubset(STS5);
	 
	 
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/AdamCoverage.dat","data/raise/reduce/setCovers/AdamTime.dat");
	 
	 	assertEquals(cover.toString(),cover2.toString());
	}	 
	
	public void testIdenticalConstructionWalcottExample()
	{
	
		SingleTest test0 = new SingleTest("SingleTest0",0,9.0);
		SingleTest test1 = new SingleTest("SingleTest1",1,1.0);
		SingleTest test2 = new SingleTest("SingleTest2",2,3.0);
		SingleTest test3 = new SingleTest("SingleTest3",3,4.0);
		SingleTest test4 = new SingleTest("SingleTest4",4,4.0);
		SingleTest test5 = new SingleTest("SingleTest5",5,4.0);

		RequirementSubset req0 = new RequirementSubset("RequirementSubset0",0);
		RequirementSubset req1 = new RequirementSubset("RequirementSubset1",1);
		RequirementSubset req2 = new RequirementSubset("RequirementSubset2",2);
		RequirementSubset req3 = new RequirementSubset("RequirementSubset3",3);
		RequirementSubset req4 = new RequirementSubset("RequirementSubset4",4);
		RequirementSubset req5 = new RequirementSubset("RequirementSubset5",5);
		RequirementSubset req6 = new RequirementSubset("RequirementSubset6",6);
		RequirementSubset req7 = new RequirementSubset("RequirementSubset7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req1.addCoveringTest(test0);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test3);		
		req3.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test4);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test0);		
		req6.addCoveringTest(test3);		
		req7.addCoveringTest(test0);		
		req7.addCoveringTest(test4);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req3);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS0.addRequirementSubset(req6);
		STS0.addRequirementSubset(req7);
		STS1.addRequirementSubset(req0);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req6);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req5);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
	
		cover2 = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/WalcottCoverage.dat","data/raise/reduce/setCovers/WalcottTime.dat");
		
		assertEquals(cover.toString(),cover2.toString());
	}
	
	public void testIdenticalConstructionTallamGuptaExample()
	{
	
	// make singletest
		SingleTest test0 = new SingleTest("SingleTest0",0,7.0);
		SingleTest test1 = new SingleTest("SingleTest1",1,4.0);
		SingleTest test2 = new SingleTest("SingleTest2",2,3.0);
		SingleTest test3 = new SingleTest("SingleTest3",3,9.0);
		SingleTest test4 = new SingleTest("SingleTest4",4,1.0);

		// make requirementsubsets
		RequirementSubset req0 = new RequirementSubset("RequirementSubset0",0);
		RequirementSubset req1 = new RequirementSubset("RequirementSubset1",1);
		RequirementSubset req2 = new RequirementSubset("RequirementSubset2",2);
		RequirementSubset req3 = new RequirementSubset("RequirementSubset3",3);
		RequirementSubset req4 = new RequirementSubset("RequirementSubset4",4);
		RequirementSubset req5 = new RequirementSubset("RequirementSubset5",5);

		// make singletestsubset with singletests
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1); 
		req1.addCoveringTest(test0);
		req1.addCoveringTest(test2);
		req2.addCoveringTest(test0);
		req2.addCoveringTest(test3);
		req3.addCoveringTest(test1);
		req4.addCoveringTest(test2);
		req4.addCoveringTest(test4);
		req5.addCoveringTest(test3);
	
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req3);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req4);

		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);

		// add SingleTestSubset to cover
		
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
	
		cover2 = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/TallamGuptaCoverage.dat","data/raise/reduce/setCovers/TallamGuptaTime.dat");
		
		assertEquals(cover.toString(),cover2.toString());
	}
	
	public void testIdenticalConstructionHGSExample()
	{
		SingleTest test0 = new SingleTest("SingleTest0",0,9.0);
		SingleTest test1 = new SingleTest("SingleTest1",1,3.0);
		SingleTest test2 = new SingleTest("SingleTest2",2,3.0);
		SingleTest test3 = new SingleTest("SingleTest3",3,4.0);
		SingleTest test4 = new SingleTest("SingleTest4",4,5.0);
		SingleTest test5 = new SingleTest("SingleTest5",5,3.0);
		SingleTest test6 = new SingleTest("SingleTest6",6,6.0);

		RequirementSubset req0 = new RequirementSubset("RequirementSubset0",0);
		RequirementSubset req1 = new RequirementSubset("RequirementSubset1",1);
		RequirementSubset req2 = new RequirementSubset("RequirementSubset2",2);
		RequirementSubset req3 = new RequirementSubset("RequirementSubset3",3);
		RequirementSubset req4 = new RequirementSubset("RequirementSubset4",4);
		RequirementSubset req5 = new RequirementSubset("RequirementSubset5",5);
		RequirementSubset req6 = new RequirementSubset("RequirementSubset6",6);
		RequirementSubset req7 = new RequirementSubset("RequirementSubset7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);
		SingleTestSubset STS6 = new SingleTestSubset(test6);
		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test4);		
		req1.addCoveringTest(test4);		
		req2.addCoveringTest(test0);		
		req2.addCoveringTest(test1);		
		req2.addCoveringTest(test2);		
		req3.addCoveringTest(test2);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test3);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test2);		
		req6.addCoveringTest(test3);		
		req6.addCoveringTest(test6);		
		req7.addCoveringTest(test1);		
		req7.addCoveringTest(test2);		
		req7.addCoveringTest(test3);		
		req7.addCoveringTest(test6);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS1.addRequirementSubset(req2);
		STS1.addRequirementSubset(req7);
		STS2.addRequirementSubset(req2);
		STS2.addRequirementSubset(req3);
		STS2.addRequirementSubset(req6);
		STS2.addRequirementSubset(req7);
		STS3.addRequirementSubset(req4);
		STS3.addRequirementSubset(req6);
		STS3.addRequirementSubset(req7);
		STS4.addRequirementSubset(req0);
		STS4.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		STS6.addRequirementSubset(req6);
		STS6.addRequirementSubset(req7);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		cover.addSingleTestSubset(STS6);
	
		cover2 = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/HGSCoverage.dat","data/raise/reduce/setCovers/HGSTime.dat");
		
		assertEquals(cover.toString(),cover2.toString());
	}
	
	/*// This is for a feature that has not been included yet.
	public void testRestorationAdamExample()
	{
		SingleTest test0 = new SingleTest("SingleTest0",0,4.0);
		SingleTest test1 = new SingleTest("SingleTest1",1,1.0);
		SingleTest test2 = new SingleTest("SingleTest2",2,5.0);
		SingleTest test3 = new SingleTest("SingleTest3",3,2.0);
		SingleTest test4 = new SingleTest("SingleTest4",4,3.0);
		SingleTest test5 = new SingleTest("SingleTest5",5,2.0);

		RequirementSubset req0 = new RequirementSubset("RequirementSubset0",0);
		RequirementSubset req1 = new RequirementSubset("RequirementSubset1",1);
		RequirementSubset req2 = new RequirementSubset("RequirementSubset2",2);
		RequirementSubset req3 = new RequirementSubset("RequirementSubset3",3);
		RequirementSubset req4 = new RequirementSubset("RequirementSubset4",4);
		RequirementSubset req5 = new RequirementSubset("RequirementSubset5",5);
		RequirementSubset req6 = new RequirementSubset("RequirementSubset6",6);
		RequirementSubset req7 = new RequirementSubset("RequirementSubset7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover2.addRequirementSubset(req0);
		cover2.addRequirementSubset(req1);
		cover2.addRequirementSubset(req2);
		cover2.addRequirementSubset(req3);
		cover2.addRequirementSubset(req4);
		cover2.addRequirementSubset(req5);
		cover2.addRequirementSubset(req6);
		cover2.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover2.addSingleTestSubset(STS0);
		cover2.addSingleTestSubset(STS1);
		cover2.addSingleTestSubset(STS2);
		cover2.addSingleTestSubset(STS3);
		cover2.addSingleTestSubset(STS4);
		cover2.addSingleTestSubset(STS5);
		
		cover2.reduceUsingGreedy("ratio");
		cover2.reduceUsingGreedy("ratio");
		
	}
	
	*/
	public void testCECalculatorAdamExample()
	{
		
		SingleTest test0 = new SingleTest("SingleTest0",0,4.0);
		SingleTest test1 = new SingleTest("SingleTest1",1,1.0);
		SingleTest test2 = new SingleTest("SingleTest2",2,5.0);
		SingleTest test3 = new SingleTest("SingleTest3",3,2.0);
		SingleTest test4 = new SingleTest("SingleTest4",4,3.0);
		SingleTest test5 = new SingleTest("SingleTest5",5,2.0);

		RequirementSubset req0 = new RequirementSubset("RequirementSubset0",0);
		RequirementSubset req1 = new RequirementSubset("RequirementSubset1",1);
		RequirementSubset req2 = new RequirementSubset("RequirementSubset2",2);
		RequirementSubset req3 = new RequirementSubset("RequirementSubset3",3);
		RequirementSubset req4 = new RequirementSubset("RequirementSubset4",4);
		RequirementSubset req5 = new RequirementSubset("RequirementSubset5",5);
		RequirementSubset req6 = new RequirementSubset("RequirementSubset6",6);
		RequirementSubset req7 = new RequirementSubset("RequirementSubset7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover2.addRequirementSubset(req0);
		cover2.addRequirementSubset(req1);
		cover2.addRequirementSubset(req2);
		cover2.addRequirementSubset(req3);
		cover2.addRequirementSubset(req4);
		cover2.addRequirementSubset(req5);
		cover2.addRequirementSubset(req6);
		cover2.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover2.addSingleTestSubset(STS0);
		cover2.addSingleTestSubset(STS1);
		cover2.addSingleTestSubset(STS2);
		cover2.addSingleTestSubset(STS3);
		cover2.addSingleTestSubset(STS4);
		cover2.addSingleTestSubset(STS5);
		
		int order[] = {0,1,2,3,4,5};
		
		//System.out.println("CE of "+printIntArray(order)+" is "+cover2.getCE(order));
		assertEquals(cover2.getCE(order),(float) 0.5);
		
		cover2.savePristeneCopyByteArray();
		cover2.reduceUsingDelayedGreedy("ratio");
		cover2.restoreSetCover();
		assertEquals(cover2.getCE(order),(float) 0.5);
		
	}
	
	public void testCECalculatorReverseAdamExample()
	{
		
		SingleTest test0 = new SingleTest("SingleTest0",0,4.0);
		SingleTest test1 = new SingleTest("SingleTest1",1,1.0);
		SingleTest test2 = new SingleTest("SingleTest2",2,5.0);
		SingleTest test3 = new SingleTest("SingleTest3",3,2.0);
		SingleTest test4 = new SingleTest("SingleTest4",4,3.0);
		SingleTest test5 = new SingleTest("SingleTest5",5,2.0);

		RequirementSubset req0 = new RequirementSubset("RequirementSubset0",0);
		RequirementSubset req1 = new RequirementSubset("RequirementSubset1",1);
		RequirementSubset req2 = new RequirementSubset("RequirementSubset2",2);
		RequirementSubset req3 = new RequirementSubset("RequirementSubset3",3);
		RequirementSubset req4 = new RequirementSubset("RequirementSubset4",4);
		RequirementSubset req5 = new RequirementSubset("RequirementSubset5",5);
		RequirementSubset req6 = new RequirementSubset("RequirementSubset6",6);
		RequirementSubset req7 = new RequirementSubset("RequirementSubset7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover2.addRequirementSubset(req0);
		cover2.addRequirementSubset(req1);
		cover2.addRequirementSubset(req2);
		cover2.addRequirementSubset(req3);
		cover2.addRequirementSubset(req4);
		cover2.addRequirementSubset(req5);
		cover2.addRequirementSubset(req6);
		cover2.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover2.addSingleTestSubset(STS0);
		cover2.addSingleTestSubset(STS1);
		cover2.addSingleTestSubset(STS2);
		cover2.addSingleTestSubset(STS3);
		cover2.addSingleTestSubset(STS4);
		cover2.addSingleTestSubset(STS5);
		
		int order[] = {5,4,3,2,1,0};
		
		//System.out.println("CE of "+printIntArray(order)+" is "+cover2.getCE(order));
		assertEquals(cover2.getCE(order),(float) 0.639705882);
	}
	
	
	// The clone method will return a SetCover with the same string 
	// representation, but it is not essenentially a true clone.
	// See the clone method in SetCover for more info.
	public void testCloneSameString()
	{
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/AdamCoverage.dat","data/raise/reduce/setCovers/AdamTime.dat");
		
		assertEquals(cover.toString(),(cover.clone()).toString());
	}  
	
	public void testPristeneCopySameString()
	{
		cover = SetCover.constructSetCoverFromMatrix(
		"data/raise/reduce/setCovers/AdamCoverage.dat",
		"data/raise/reduce/setCovers/AdamTime.dat");

		cover.savePristeneCopyByteArray();				
		String original = cover.toString();
	
		cover.reduceUsingDelayedGreedy("ratio");
		
		cover.restoreSetCover();
		
		assertEquals(cover.toString(),original);
	}
	/* Not done
	public void testPristeneCopySameResultsAdamExampleReduction()
	{
		cover = SetCover.constructSetCoverFromMatrix(
		"data/raise/reduce/setCovers/AdamCoverage.dat",
		"data/raise/reduce/setCovers/AdamTime.dat");

		cover.savePristeneCopyByteArray();				
		String original = cover.toString();
	
		cover.reduceUsingDelayedGreedy("ratio");
		
		cover.restoreSetCover();
		
		assertEquals(cover.toString(),original);
	}
	
	*/
	// Below here are reduction/prioritization tests
	
	/*************************************************************************
	 * Begin Greedy tests
	 ************************************************************************/
	public void testGreedyReduceCoverageAdamExample()
	{
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/AdamCoverage.dat","data/raise/reduce/setCovers/AdamTime.dat");
		
		cover.reduceUsingGreedy("coverage");
		covered = cover.getCoverPickSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
		assertEquals( answerIterator.next().getName(),"SingleTest1");
		assertEquals( answerIterator.next().getName(),"SingleTest4");	
		assertEquals( answerIterator.next().getName(),"SingleTest0");
	
	}
	
	public void testGreedyReduceTimeAdamExample()
	{
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/AdamCoverage.dat","data/raise/reduce/setCovers/AdamTime.dat");
		
		cover.reduceUsingGreedy("time");
		covered = cover.getCoverPickSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
		
		assertEquals( answerIterator.next().getName(),"SingleTest1");
		assertEquals( answerIterator.next().getName(),"SingleTest3");	
		assertEquals( answerIterator.next().getName(),"SingleTest5");
		assertEquals( answerIterator.next().getName(),"SingleTest4");
		assertEquals( answerIterator.next().getName(),"SingleTest0");	
}

	public void testGreedyReduceRatioAdamExample()
	{
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/AdamCoverage.dat","data/raise/reduce/setCovers/AdamTime.dat");
		
		
		cover.reduceUsingGreedy("ratio");
		covered = cover.getCoverPickSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
		
		assertEquals( answerIterator.next().getName(),"SingleTest1");
		assertEquals( answerIterator.next().getName(),"SingleTest4");	
		assertEquals( answerIterator.next().getName(),"SingleTest0");
	}
	
	///////////////////////////////////////
	 	public void testGreedyPrioritizeCoverageAdamExample()
	{
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/AdamCoverage.dat","data/raise/reduce/setCovers/AdamTime.dat");
		
		
		cover.prioritizeUsingGreedy("coverage");
		covered = cover.getPrioritizedSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
		
		assertEquals( answerIterator.next().getName(),"SingleTest1");
		assertEquals( answerIterator.next().getName(),"SingleTest4");	
		assertEquals( answerIterator.next().getName(),"SingleTest0");
		assertEquals( answerIterator.next().getName(),"SingleTest5");
		assertEquals( answerIterator.next().getName(),"SingleTest2");	
		assertEquals( answerIterator.next().getName(),"SingleTest3");	
	}

	public void testGreedyPrioritizeTimeAdamExample()
	{
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/AdamCoverage.dat","data/raise/reduce/setCovers/AdamTime.dat");
		
		
		cover.prioritizeUsingGreedy("time");
		covered = cover.getPrioritizedSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
		
		assertEquals( answerIterator.next().getName(),"SingleTest1");
		assertEquals( answerIterator.next().getName(),"SingleTest3");	
		assertEquals( answerIterator.next().getName(),"SingleTest5");
		assertEquals( answerIterator.next().getName(),"SingleTest4");
		assertEquals( answerIterator.next().getName(),"SingleTest0");	
		assertEquals( answerIterator.next().getName(),"SingleTest2");	
}
	public void testGreedyPrioritizeRatioAdamExample()
	{
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/AdamCoverage.dat","data/raise/reduce/setCovers/AdamTime.dat");
		
		
		cover.prioritizeUsingGreedy("ratio");
		covered = cover.getPrioritizedSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
		
		assertEquals( answerIterator.next().getName(),"SingleTest1");
		assertEquals( answerIterator.next().getName(),"SingleTest4");	
		assertEquals( answerIterator.next().getName(),"SingleTest0");
		assertEquals( answerIterator.next().getName(),"SingleTest5");
		assertEquals( answerIterator.next().getName(),"SingleTest3");	
		assertEquals( answerIterator.next().getName(),"SingleTest2");
	}
	
	
	
	/*************************************************************************
	* The following test Delayed Greedy
	*************************************************************************/
	/**
	 * This method tests the use of objects and references.  It basically served as proof
	 * of some of the things regarding java objects that I wasn't sure about.  
	 *
	 * Changed to use assert Dec
	 */
	 
	 public void testDelayedGreedySomeSetCoverStuffTallamGuptaExample() {
	 	assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		// make singletest
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);

		// make requirementsubsets
		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);

		// make singletestsubset with singletests
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1); 
		req1.addCoveringTest(test0);
		req1.addCoveringTest(test2);
		req2.addCoveringTest(test0);
		req2.addCoveringTest(test3);
		req3.addCoveringTest(test1);
		req4.addCoveringTest(test2);
		req4.addCoveringTest(test4);
		req5.addCoveringTest(test3);
	
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req3);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req4);

		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);

		// add SingleTestSubset to cover
		
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
	 
	 
	 	LinkedHashSet ct = STS4.getRequirementSubsetSet();
	 	
	 	Iterator r = ct.iterator();
	 	
	 	RequirementSubset rs = (RequirementSubset) r.next();
	 		 	
	 	Iterator t4cri = rs.getCoveringTests().iterator();
	 		 		 	
	 	//System.out.println("Here are the covering tests from the RS in the STS before removal");
	 	
	 	//while (t4cri.hasNext()) {
	 		//System.out.println(t4cri.next());
	 	//}
		
		assertEquals( ((SingleTest) t4cri.next()).getName(),"test2");	 	
		assertEquals( ((SingleTest) t4cri.next()).getName(),"test4");

	 	Iterator r4i = req4.getCoveringTests().iterator();

	 	//System.out.println("Here are the covering tests directly from the RS before removal");
	 	
	 	// while (r4i.hasNext()){
	 	//		System.out.println(r4i.next());
	 	// }	 
	 	
	 	assertEquals( ((SingleTest) r4i.next()).getName(),"test2");	 	
		assertEquals( ((SingleTest) r4i.next()).getName(),"test4");

	 	Iterator newit = cover.getTestSubsets().iterator();
	 	SingleTestSubset thisSTS = null;
	 	

		//The point of most of the rest of this code is to remove test2 
		// SingleTests from the cover and then see if they were removed in 
		// other places

		// This searches for the test "test4" and sets the SingleTestSubet that 
		// contains it to thisSTS.  It seems like there should be a better way to 
		// do this... maybe not.  Oh yeah, there is no get for LinkedHashSet. So... 
		// nope. 

	 	while (newit.hasNext()) {
	 		thisSTS = (SingleTestSubset) newit.next();
	 		if (thisSTS.getTest().getName().equals("test4"))
				break;
	 	}
	 	
		//assertFalse(thisSTS.getTest().getName().equals("test4"));
	 	
	 	Iterator newnewit = thisSTS.getRequirementSubsetSet().iterator();
	 	
	 	RequirementSubset thisreq = null;
	 	
	 	while (newnewit.hasNext()){
	 		thisreq = (RequirementSubset) newnewit.next();
	 		
	 		if (thisreq.getName().equals("req4"));
	 			break;
	 	}
	 	
	 	//remove
	 	thisreq.removeCoveringTest(test4);
	 	 
	  	ct = STS4.getRequirementSubsetSet();
	 	
	 	r = ct.iterator();
	 	
	 	rs = (RequirementSubset) r.next();
	 	
	 	t4cri = rs.getCoveringTests().iterator();
	 		 	
	 //	System.out.println("Here are the covering tests from the RS in the STS after removal");
	 	
	 //	while (t4cri.hasNext()) {
	 	//	System.out.println(t4cri.next());
	 	//}

		assertEquals(((SingleTest) t4cri.next()).getName(),"test2");
	 	
	 	r4i = req4.getCoveringTests().iterator();
	 //	System.out.println("Here are the covering tests directly from the RS after removal");
	 	
	 //	while (r4i.hasNext()){
	 //		System.out.println(r4i.next());
	 //	}
	 
	 assertEquals(((SingleTest) r4i.next()).getName(),"test2");
	 }
	 
	
	public void testDelayedGreedyReduceUsingTallamGuptaExample() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		// make singletest
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);

		// make requirementsubsets
		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);

		// make singletestsubset with singletests
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1); 
		req1.addCoveringTest(test0);
		req1.addCoveringTest(test2);
		req2.addCoveringTest(test0);
		req2.addCoveringTest(test3);
		req3.addCoveringTest(test1);
		req4.addCoveringTest(test2);
		req4.addCoveringTest(test4);
		req5.addCoveringTest(test3);
	
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req3);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req4);

		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);

		// add SingleTestSubset to cover
		
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		
		cover.reduceUsingDelayedGreedy("coverage");
		covered = cover.getCoverPickSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
	//	
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");

	assertEquals( answerIterator.next().getName(),"test1");
	assertEquals( answerIterator.next().getName(),"test2");	
	assertEquals( answerIterator.next().getName(),"test3");
	}	



	public void testDelayedGreedyReduceUsingHarroldGuptaSoffaExample() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
		
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);
		SingleTest test6 = new SingleTest("test6",6);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);
		SingleTestSubset STS6 = new SingleTestSubset(test6);
		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test4);		
		req1.addCoveringTest(test4);		
		req2.addCoveringTest(test0);		
		req2.addCoveringTest(test1);		
		req2.addCoveringTest(test2);		
		req3.addCoveringTest(test2);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test3);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test2);		
		req6.addCoveringTest(test3);		
		req6.addCoveringTest(test6);		
		req7.addCoveringTest(test1);		
		req7.addCoveringTest(test2);		
		req7.addCoveringTest(test3);		
		req7.addCoveringTest(test6);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS1.addRequirementSubset(req2);
		STS1.addRequirementSubset(req7);
		STS2.addRequirementSubset(req2);
		STS2.addRequirementSubset(req3);
		STS2.addRequirementSubset(req6);
		STS2.addRequirementSubset(req7);
		STS3.addRequirementSubset(req4);
		STS3.addRequirementSubset(req6);
		STS3.addRequirementSubset(req7);
		STS4.addRequirementSubset(req0);
		STS4.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		STS6.addRequirementSubset(req6);
		STS6.addRequirementSubset(req7);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		cover.addSingleTestSubset(STS6);
	
		cover.reduceUsingDelayedGreedy("coverage");
		covered = cover.getCoverPickSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();

		//System.out.println("*****\nCovering Set for HGS example:\n");
		//while ( answerIterator.hasNext()){
	
			//SingleTest currentTest = (SingleTest) answerIterator.next();

			//System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
		answerIterator = covered.iterator();
		
	assertEquals( answerIterator.next().getName(),"test4");
	assertEquals( answerIterator.next().getName(),"test0");
	assertEquals( answerIterator.next().getName(),"test2");

	} 



	public void testDelayedGreedyReduceUsingWalcottExample() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
	
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req1.addCoveringTest(test0);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test3);		
		req3.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test4);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test0);		
		req6.addCoveringTest(test3);		
		req7.addCoveringTest(test0);		
		req7.addCoveringTest(test4);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req3);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS0.addRequirementSubset(req6);
		STS0.addRequirementSubset(req7);
		STS1.addRequirementSubset(req0);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req6);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req5);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		cover.reduceUsingDelayedGreedy("coverage");
		covered = cover.getCoverPickSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Covering Tests For Walcott Reduction Example:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");

	assertEquals( answerIterator.next().getName(),"test0");
	assertEquals( answerIterator.next().getName(),"test3");
}

	public void testDelayedGreedyReduceUsingAdamSmithFirstExample() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		
		cover.reduceUsingDelayedGreedy("coverage");
		covered = cover.getCoverPickSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Covering Tests for reduceUsingAdamSmithFirstExample:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//}
   //  	System.out.println("*****\n\n");	
		assertEquals( answerIterator.next().getName(),"test0");	
		assertEquals( answerIterator.next().getName(),"test1");
		assertEquals( answerIterator.next().getName(),"test4");	
	
	}
	

	public void testDelayedGreedyPrioritizeUsingAdamSmithFirstExample() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		
		cover.prioritizeUsingDelayedGreedy("coverage");
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Prioritized Tests for prioritizeUsingAdamSmithFirstExample:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");

	assertEquals( answerIterator.next().getName(),"test0");
	assertEquals( answerIterator.next().getName(),"test1");	
	assertEquals( answerIterator.next().getName(),"test4");
	assertEquals( answerIterator.next().getName(),"test5");
	assertEquals( answerIterator.next().getName(),"test2");	
	assertEquals( answerIterator.next().getName(),"test3");
	}

public void testDelayedGreedyPrioritizeUsingWalcottExample() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req1.addCoveringTest(test0);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test3);		
		req3.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test4);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test0);		
		req6.addCoveringTest(test3);		
		req7.addCoveringTest(test0);		
		req7.addCoveringTest(test4);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req3);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS0.addRequirementSubset(req6);
		STS0.addRequirementSubset(req7);
		STS1.addRequirementSubset(req0);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req6);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req5);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		
		cover.prioritizeUsingDelayedGreedy("coverage");
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Prioritized Tests for prioritizeUsingWalcottExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	
	//	System.out.println("*****\n\n");
	
	assertEquals( answerIterator.next().getName(),"test0");
	assertEquals( answerIterator.next().getName(),"test3");	
	assertEquals( answerIterator.next().getName(),"test2");
	assertEquals( answerIterator.next().getName(),"test5");
	assertEquals( answerIterator.next().getName(),"test4");	
	assertEquals( answerIterator.next().getName(),"test1");

	}

	public void testDelayedGreedyPrioritizeUsingTallamGuptaExample() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		// make singletest
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);

		// make requirementsubsets
		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);

		// make singletestsubset with singletests
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1); 
		req1.addCoveringTest(test0);
		req1.addCoveringTest(test2);
		req2.addCoveringTest(test0);
		req2.addCoveringTest(test3);
		req3.addCoveringTest(test1);
		req4.addCoveringTest(test2);
		req4.addCoveringTest(test4);
		req5.addCoveringTest(test3);
	
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req3);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req4);

		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);

		// add SingleTestSubset to cover
		
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		
		
		cover.prioritizeUsingDelayedGreedy("coverage");
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nPrioritized Set for prioritizeUsingTallumGuptaExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");

	assertEquals( answerIterator.next().getName(),"test1");	
	assertEquals( answerIterator.next().getName(),"test2");
	assertEquals( answerIterator.next().getName(),"test3");
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test4");

	}	



	public void testDelayedGreedyPrioritizeUsingHarroldGuptaSoffaExample() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
		
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);
		SingleTest test6 = new SingleTest("test6",6);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);
		SingleTestSubset STS6 = new SingleTestSubset(test6);
		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test4);		
		req1.addCoveringTest(test4);		
		req2.addCoveringTest(test0);		
		req2.addCoveringTest(test1);		
		req2.addCoveringTest(test2);		
		req3.addCoveringTest(test2);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test3);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test2);		
		req6.addCoveringTest(test3);		
		req6.addCoveringTest(test6);		
		req7.addCoveringTest(test1);		
		req7.addCoveringTest(test2);		
		req7.addCoveringTest(test3);		
		req7.addCoveringTest(test6);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS1.addRequirementSubset(req2);
		STS1.addRequirementSubset(req7);
		STS2.addRequirementSubset(req2);
		STS2.addRequirementSubset(req3);
		STS2.addRequirementSubset(req6);
		STS2.addRequirementSubset(req7);
		STS3.addRequirementSubset(req4);
		STS3.addRequirementSubset(req6);
		STS3.addRequirementSubset(req7);
		STS4.addRequirementSubset(req0);
		STS4.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		STS6.addRequirementSubset(req6);
		STS6.addRequirementSubset(req7);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		cover.addSingleTestSubset(STS6);
	
		
		cover.prioritizeUsingDelayedGreedy("coverage");
		covered = cover.getPrioritizedSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
	//	System.out.println("*****\nPrioritized Set for PrioritizeUsingHarroldGuptaSoffaExample:\n");
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
	assertEquals( answerIterator.next().getName(),"test4");
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test2");
	assertEquals( answerIterator.next().getName(),"test1");
	assertEquals( answerIterator.next().getName(),"test5");	
	assertEquals( answerIterator.next().getName(),"test3");
	assertEquals( answerIterator.next().getName(),"test6");
	}
/*******************************************************************************************************************
																			Time Tests
*******************************************************************************************************************
*/

public void testDelayedGreedyReduceUsingTallamGuptaExampleTime()
	{
		// this example never is without a test or requirement eliminiation 
		// so the answer will be the same as coverage
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/TallamGuptaCoverage.dat","data/raise/reduce/setCovers/TallamGuptaTime.dat");
			
		cover.reduceUsingDelayedGreedy("time");
		covered = cover.getCoverPickSets();
	
		Iterator<SingleTest> answerIterator = covered.iterator();

		//System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
			
		//	while ( answerIterator.hasNext()){
		
		//		SingleTest currentTest = (SingleTest) answerIterator.next();
	
		//		System.out.println(currentTest.toString());
		//	}
			
		//	System.out.println("*****\n\n");

		assertTrue( answerIterator.next().getName().equals("SingleTest1"));
		assertTrue( answerIterator.next().getName().equals("SingleTest2"));
		assertTrue( answerIterator.next().getName().equals("SingleTest3"));
	}
	
	public void testDelayedGreedyReduceUsingHarroldGuptaSoffaExampleTime() 
	{
	
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/HGSCoverage.dat","data/raise/reduce/setCovers/HGSTime.dat");
		
		cover.reduceUsingDelayedGreedy("time");
			covered = cover.getCoverPickSets();
	
			Iterator<SingleTest> answerIterator = covered.iterator();
/*
		System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
			
			while ( answerIterator.hasNext()){
		
				SingleTest currentTest = (SingleTest) answerIterator.next();
	
				System.out.println(currentTest.toString());
			}
			
			System.out.println("*****\n\n");
*/
		assertTrue( answerIterator.next().getName().equals("SingleTest4"));
		assertTrue( answerIterator.next().getName().equals("SingleTest2"));
		assertTrue( answerIterator.next().getName().equals("SingleTest5"));
		assertTrue( answerIterator.next().getName().equals("SingleTest3"));
	}
	
public void testDelayedGreedyPrioritizeUsingHarroldGuptaSoffaExampleTime() 
	{
	
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/HGSCoverage.dat","data/raise/reduce/setCovers/HGSTime.dat");
		
		cover.prioritizeUsingDelayedGreedy("time");
			covered = cover.getPrioritizedSets();
	
			Iterator<SingleTest> answerIterator = covered.iterator();

			//System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
			
		//	while ( answerIterator.hasNext()){
		
		//		SingleTest currentTest = (SingleTest) answerIterator.next();
	
		//		System.out.println(currentTest.toString());
		//	}
			
		//	System.out.println("*****\n\n");

		assertTrue( answerIterator.next().getName().equals("SingleTest4"));
		assertTrue( answerIterator.next().getName().equals("SingleTest2"));
		assertTrue( answerIterator.next().getName().equals("SingleTest5"));
		assertTrue( answerIterator.next().getName().equals("SingleTest3"));
		assertTrue( answerIterator.next().getName().equals("SingleTest0"));
		assertTrue( answerIterator.next().getName().equals("SingleTest6"));
		assertTrue( answerIterator.next().getName().equals("SingleTest1"));
	}
public void testDelayedGreedyPrioritizeUsingTallamGuptaExampleTime()
	{
		// this example never is without a test or requirement eliminiation 
		// so the answer will be the same as coverage
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/TallamGuptaCoverage.dat","data/raise/reduce/setCovers/TallamGuptaTime.dat");
			
		cover.prioritizeUsingDelayedGreedy("time");
		covered = cover.getPrioritizedSets();
	
		Iterator<SingleTest> answerIterator = covered.iterator();
/*
		System.out.println("*****\nCovering Set for prioritizeUsingTallumGuptaExample:\n");
			
			while ( answerIterator.hasNext()){
		
				SingleTest currentTest = (SingleTest) answerIterator.next();
	
				System.out.println(currentTest.toString());
			}
			
			System.out.println("*****\n\n");
*/
		assertTrue( answerIterator.next().getName().equals("SingleTest1"));
		assertTrue( answerIterator.next().getName().equals("SingleTest2"));
		assertTrue( answerIterator.next().getName().equals("SingleTest3"));
		assertTrue( answerIterator.next().getName().equals("SingleTest0"));
		assertTrue( answerIterator.next().getName().equals("SingleTest4"));		
	}

	public void testLinkedHashSetSize0()
	{
		LinkedHashSet test = new LinkedHashSet();
		assertTrue(test.size()==0);
	}

	public void testNoNullsMatrixrlll5() 
	{
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/matrix-rlll5","data/raise/reduce/setCovers/time-rlll5");
		
		Iterator testSubsetsIterator = cover.getTestSubsets().iterator();
		
		while (testSubsetsIterator.hasNext())
		{
			assertTrue(!((SingleTestSubset)testSubsetsIterator.next()).equals(null));
		}
		
	}
	

	public void testDelayedGreedyreduceUsingDelayedGreedyHugeExampleCoverage() 
	{
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/matrix-rlll5","data/raise/reduce/setCovers/time-rlll5");

		cover.reduceUsingDelayedGreedy("coverage");
		
		covered = cover.getCoverPickSets();

		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/matrix-rlll5","data/raise/reduce/setCovers/time-rlll5");
		
		assertTrue(cover.coversRequirementSubsetUniverse(covered));
		
		//Iterator answerIterator = covered.iterator();

	//	System.out.println("*****\nCovering Set for reduceUsingHugeExample:\n");
		
		//while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

		//	System.out.println(currentTest.toString());
		//}
		
		//System.out.println("*****\n\n");
	}
	
	public void testDelayedGreedyreduceUsingDelayedGreedyHugeExampleTime() 
	{
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/matrix-rlll5","data/raise/reduce/setCovers/time-rlll5");

		cover.reduceUsingDelayedGreedy("time");
		
		covered = cover.getCoverPickSets();

		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/matrix-rlll5","data/raise/reduce/setCovers/time-rlll5");
		
		assertTrue(cover.coversRequirementSubsetUniverse(covered));
		
		//Iterator answerIterator = covered.iterator();

	//	System.out.println("*****\nCovering Set for reduceUsingHugeExample:\n");
		
		//while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

		//	System.out.println(currentTest.toString());
		//}
		
		//System.out.println("*****\n\n");
	}
	
	public void testDelayedGreedyReduceRatioAdamExample()
	{
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/AdamCoverage.dat","data/raise/reduce/setCovers/AdamTime.dat");
		
		cover.reduceUsingDelayedGreedy("coverage");
		covered = cover.getCoverPickSets();
		Iterator<SingleTest> answerIterator = covered.iterator();
		
		assertEquals( answerIterator.next().getName(),"SingleTest0");
		assertEquals( answerIterator.next().getName(),"SingleTest1");	
		assertEquals( answerIterator.next().getName(),"SingleTest4");
	}
	public void testDelayedGreedyreduceUsingDelayedGreedyHugeExampleRatio() 
	{
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/matrix-rlll5","data/raise/reduce/setCovers/time-rlll5");

		cover.reduceUsingDelayedGreedy("ratio");
		
		covered = cover.getCoverPickSets();

		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/matrix-rlll5","data/raise/reduce/setCovers/time-rlll5");
		
		assertTrue(cover.coversRequirementSubsetUniverse(covered));
		
		//Iterator answerIterator = covered.iterator();

	//	System.out.println("*****\nCovering Set for reduceUsingHugeExample:\n");
		
		//while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

		//	System.out.println(currentTest.toString());
		//}
		
		//System.out.println("*****\n\n");
	}
	
	
	/**********************************************************************
	* Begin HGS Tests
	**********************************************************************/
	
	// This method tests looksahead=0
	
	public void testHarroldGuptaSoffaReduceUsingTallamGuptaExampleLA0() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		// make singletest
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);

		// make requirementsubsets
		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);

		// make singletestsubset with singletests
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1); 
		req1.addCoveringTest(test0);
		req1.addCoveringTest(test2);
		req2.addCoveringTest(test0);
		req2.addCoveringTest(test3);
		req3.addCoveringTest(test1);
		req4.addCoveringTest(test2);
		req4.addCoveringTest(test4);
		req5.addCoveringTest(test3);
	
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req3);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req4);

		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);

		// add SingleTestSubset to cover
		
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		
		// pass coverage, ratio, or time, and the number of looks ahead
		cover.reduceUsingHarroldGuptaSoffa("coverage",0); 
		covered = cover.getCoverPickSets();

		assertTrue(covered.contains(test1));
		assertTrue(covered.contains(test2));
		assertTrue(covered.contains(test3));

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");

	assertEquals( answerIterator.next().getName(),"test1");
	assertEquals( answerIterator.next().getName(),"test3");	
	assertEquals( answerIterator.next().getName(),"test2");
	}	


// This method tests lookahead = 1

public void testHarroldGuptaSoffaReduceUsingTallamGuptaExampleLA1() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		// make singletest
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);

		// make requirementsubsets
		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);

		// make singletestsubset with singletests
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1); 
		req1.addCoveringTest(test0);
		req1.addCoveringTest(test2);
		req2.addCoveringTest(test0);
		req2.addCoveringTest(test3);
		req3.addCoveringTest(test1);
		req4.addCoveringTest(test2);
		req4.addCoveringTest(test4);
		req5.addCoveringTest(test3);
	
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req3);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req4);

		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);

		// add SingleTestSubset to cover
		
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		
		cover.reduceUsingHarroldGuptaSoffa("coverage",1);
		covered = cover.getCoverPickSets();

		assertTrue(covered.contains(test1));
		assertTrue(covered.contains(test2));
		assertTrue(covered.contains(test3));

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");

	assertEquals( answerIterator.next().getName(),"test1");
	assertEquals( answerIterator.next().getName(),"test3");	
	assertEquals( answerIterator.next().getName(),"test2");
	}	
	
	
	//looksahead=2
	
	public void testHarroldGuptaSoffaReduceUsingTallamGuptaExampleLA2() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		// make singletest
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);

		// make requirementsubsets
		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);

		// make singletestsubset with singletests
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1); 
		req1.addCoveringTest(test0);
		req1.addCoveringTest(test2);
		req2.addCoveringTest(test0);
		req2.addCoveringTest(test3);
		req3.addCoveringTest(test1);
		req4.addCoveringTest(test2);
		req4.addCoveringTest(test4);
		req5.addCoveringTest(test3);
	
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req3);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req4);

		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);

		// add SingleTestSubset to cover
		
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		
		
		cover.reduceUsingHarroldGuptaSoffa("coverage",2);
		covered = cover.getCoverPickSets();

		assertTrue(covered.contains(test1));
		assertTrue(covered.contains(test2));
		assertTrue(covered.contains(test3));

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");

	assertEquals( answerIterator.next().getName(),"test1");
	assertEquals( answerIterator.next().getName(),"test3");	
	assertEquals( answerIterator.next().getName(),"test2");
	}	


//****************************************************************************************

	public void testHarroldGuptaSoffaReduceUsingHarroldGuptaSoffaExampleLA0() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
		
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);
		SingleTest test6 = new SingleTest("test6",6);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);
		SingleTestSubset STS6 = new SingleTestSubset(test6);
		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test4);		
		req1.addCoveringTest(test4);		
		req2.addCoveringTest(test0);		
		req2.addCoveringTest(test1);		
		req2.addCoveringTest(test2);		
		req3.addCoveringTest(test2);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test3);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test2);		
		req6.addCoveringTest(test3);		
		req6.addCoveringTest(test6);		
		req7.addCoveringTest(test1);		
		req7.addCoveringTest(test2);		
		req7.addCoveringTest(test3);		
		req7.addCoveringTest(test6);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS1.addRequirementSubset(req2);
		STS1.addRequirementSubset(req7);
		STS2.addRequirementSubset(req2);
		STS2.addRequirementSubset(req3);
		STS2.addRequirementSubset(req6);
		STS2.addRequirementSubset(req7);
		STS3.addRequirementSubset(req4);
		STS3.addRequirementSubset(req6);
		STS3.addRequirementSubset(req7);
		STS4.addRequirementSubset(req0);
		STS4.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		STS6.addRequirementSubset(req6);
		STS6.addRequirementSubset(req7);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		cover.addSingleTestSubset(STS6);
	
		cover.reduceUsingHarroldGuptaSoffa("coverage",0);
		covered = cover.getCoverPickSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
	
	//	System.out.println("*****\nCovering Set for reduceUsingHarroldGuptaSoffa:\n");
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
	
		assertEquals( answerIterator.next().getName(),"test4");
		assertEquals( answerIterator.next().getName(),"test5");
		assertEquals( answerIterator.next().getName(),"test0");	
		assertEquals( answerIterator.next().getName(),"test2");
	
	} 
	
		public void testHarroldGuptaSoffaReduceUsingHarroldGuptaSoffaExampleLA1() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
		
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);
		SingleTest test6 = new SingleTest("test6",6);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);
		SingleTestSubset STS6 = new SingleTestSubset(test6);
		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test4);		
		req1.addCoveringTest(test4);		
		req2.addCoveringTest(test0);		
		req2.addCoveringTest(test1);		
		req2.addCoveringTest(test2);		
		req3.addCoveringTest(test2);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test3);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test2);		
		req6.addCoveringTest(test3);		
		req6.addCoveringTest(test6);		
		req7.addCoveringTest(test1);		
		req7.addCoveringTest(test2);		
		req7.addCoveringTest(test3);		
		req7.addCoveringTest(test6);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS1.addRequirementSubset(req2);
		STS1.addRequirementSubset(req7);
		STS2.addRequirementSubset(req2);
		STS2.addRequirementSubset(req3);
		STS2.addRequirementSubset(req6);
		STS2.addRequirementSubset(req7);
		STS3.addRequirementSubset(req4);
		STS3.addRequirementSubset(req6);
		STS3.addRequirementSubset(req7);
		STS4.addRequirementSubset(req0);
		STS4.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		STS6.addRequirementSubset(req6);
		STS6.addRequirementSubset(req7);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		cover.addSingleTestSubset(STS6);
	
		cover.reduceUsingHarroldGuptaSoffa("coverage",1);
		covered = cover.getCoverPickSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
	
	//	System.out.println("*****\nCovering Set for reduceUsingHarroldGuptaSoffa:\n");
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
	
	assertEquals( answerIterator.next().getName(),"test4");
	assertEquals( answerIterator.next().getName(),"test5");
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test2");
	
	}
	
		public void testHarroldGuptaSoffaReduceUsingHarroldGuptaSoffaExampleLA2() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
		
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);
		SingleTest test6 = new SingleTest("test6",6);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);
		SingleTestSubset STS6 = new SingleTestSubset(test6);
		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test4);		
		req1.addCoveringTest(test4);		
		req2.addCoveringTest(test0);		
		req2.addCoveringTest(test1);		
		req2.addCoveringTest(test2);		
		req3.addCoveringTest(test2);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test3);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test2);		
		req6.addCoveringTest(test3);		
		req6.addCoveringTest(test6);		
		req7.addCoveringTest(test1);		
		req7.addCoveringTest(test2);		
		req7.addCoveringTest(test3);		
		req7.addCoveringTest(test6);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS1.addRequirementSubset(req2);
		STS1.addRequirementSubset(req7);
		STS2.addRequirementSubset(req2);
		STS2.addRequirementSubset(req3);
		STS2.addRequirementSubset(req6);
		STS2.addRequirementSubset(req7);
		STS3.addRequirementSubset(req4);
		STS3.addRequirementSubset(req6);
		STS3.addRequirementSubset(req7);
		STS4.addRequirementSubset(req0);
		STS4.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		STS6.addRequirementSubset(req6);
		STS6.addRequirementSubset(req7);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		cover.addSingleTestSubset(STS6);
	
		cover.reduceUsingHarroldGuptaSoffa("coverage",2);
		covered = cover.getCoverPickSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
	
	//	System.out.println("*****\nCovering Set for reduceUsingHarroldGuptaSoffa:\n");
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
	
	assertEquals( answerIterator.next().getName(),"test4");
	assertEquals( answerIterator.next().getName(),"test5");
	assertEquals( answerIterator.next().getName(),"test3");	
	assertEquals( answerIterator.next().getName(),"test0");
	
	}

//****************************************************************************************

	public void testHarroldGuptaSoffaReduceUsingWalcottExampleLA0() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
	
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req1.addCoveringTest(test0);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test3);		
		req3.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test4);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test0);		
		req6.addCoveringTest(test3);		
		req7.addCoveringTest(test0);		
		req7.addCoveringTest(test4);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req3);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS0.addRequirementSubset(req6);
		STS0.addRequirementSubset(req7);
		STS1.addRequirementSubset(req0);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req6);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req5);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		cover.reduceUsingHarroldGuptaSoffa("coverage",0);
		covered = cover.getCoverPickSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Covering Tests For Walcott Reduction Example:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
		assertEquals( answerIterator.next().getName(),"test3");	
		assertEquals( answerIterator.next().getName(),"test0");
	
	}
	
	public void testHarroldGuptaSoffaReduceUsingWalcottExampleLA1() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
	
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req1.addCoveringTest(test0);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test3);		
		req3.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test4);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test0);		
		req6.addCoveringTest(test3);		
		req7.addCoveringTest(test0);		
		req7.addCoveringTest(test4);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req3);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS0.addRequirementSubset(req6);
		STS0.addRequirementSubset(req7);
		STS1.addRequirementSubset(req0);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req6);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req5);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);

		cover.reduceUsingHarroldGuptaSoffa("coverage",1);
		covered = cover.getCoverPickSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Covering Tests For Walcott Reduction Example:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
	assertEquals( answerIterator.next().getName(),"test3");	
	assertEquals( answerIterator.next().getName(),"test0");
	}

public void testHarroldGuptaSoffaReduceUsingWalcottExampleLA2() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
	
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req1.addCoveringTest(test0);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test3);		
		req3.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test4);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test0);		
		req6.addCoveringTest(test3);		
		req7.addCoveringTest(test0);		
		req7.addCoveringTest(test4);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req3);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS0.addRequirementSubset(req6);
		STS0.addRequirementSubset(req7);
		STS1.addRequirementSubset(req0);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req6);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req5);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);

		cover.reduceUsingHarroldGuptaSoffa("coverage",0);
		covered = cover.getCoverPickSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Covering Tests For Walcott Reduction Example:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
	assertEquals( answerIterator.next().getName(),"test3");	
	assertEquals( answerIterator.next().getName(),"test0");
	
	}

//****************************************************************************************

	public void testHarroldGuptaSoffaReduceUsingAdamSmithFirstExampleLA0() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);

		cover.reduceUsingHarroldGuptaSoffa("coverage",0);
		covered = cover.getCoverPickSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Covering Tests for reduceUsingAdamSmithFirstExample:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");
		
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test4");	
	assertEquals( answerIterator.next().getName(),"test1");
	
	}
	
	public void testHarroldGuptaSoffaReduceUsingAdamSmithFirstExampleLA1() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);

		cover.reduceUsingHarroldGuptaSoffa("coverage",1);
		covered = cover.getCoverPickSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

		//	System.out.println("*****\nSet of Covering Tests for reduceUsingAdamSmithFirstExample:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");
		
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test4");	
	assertEquals( answerIterator.next().getName(),"test1");
	
	}
	
	public void testHarroldGuptaSoffaReduceUsingAdamSmithFirstExampleLA2() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);

		cover.reduceUsingHarroldGuptaSoffa("coverage",2);
		covered = cover.getCoverPickSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Covering Tests for reduceUsingAdamSmithFirstExample:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");
		
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test4");	
	assertEquals( answerIterator.next().getName(),"test1");
	
}
	//****************************************************************************************


	// Begin prioritization section

	public void testHarroldGuptaSoffaPrioritizeUsingAdamSmithFirstExampleLA0() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		cover.prioritizeUsingHarroldGuptaSoffa("coverage",0);
		
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Prioritized Tests for prioritizeUsingAdamSmithFirstExample:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");
	
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test4");	
	assertEquals( answerIterator.next().getName(),"test1");
	assertEquals( answerIterator.next().getName(),"test5");	
	assertEquals( answerIterator.next().getName(),"test2");
	assertEquals( answerIterator.next().getName(),"test3");	

	}


	public void testHarroldGuptaSoffaPrioritizeUsingAdamSmithFirstExampleLA1() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		cover.prioritizeUsingHarroldGuptaSoffa("coverage",1);
		
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Prioritized Tests for prioritizeUsingAdamSmithFirstExample:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");
	
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test4");	
	assertEquals( answerIterator.next().getName(),"test1");
	assertEquals( answerIterator.next().getName(),"test5");	
	assertEquals( answerIterator.next().getName(),"test2");
	assertEquals( answerIterator.next().getName(),"test3");
		
	}

	public void testHarroldGuptaSoffaPrioritizeUsingAdamSmithFirstExampleLA2() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		cover.prioritizeUsingHarroldGuptaSoffa("coverage",2);
		
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Prioritized Tests for prioritizeUsingAdamSmithFirstExample:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");
	
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test4");	
	assertEquals( answerIterator.next().getName(),"test1");
	assertEquals( answerIterator.next().getName(),"test5");	
	assertEquals( answerIterator.next().getName(),"test2");
	assertEquals( answerIterator.next().getName(),"test3");

	}

//****************************************************************************************


public void testHarroldGuptaSoffaPrioritizeUsingWalcottExampleLA0() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req1.addCoveringTest(test0);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test3);		
		req3.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test4);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test0);		
		req6.addCoveringTest(test3);		
		req7.addCoveringTest(test0);		
		req7.addCoveringTest(test4);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req3);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS0.addRequirementSubset(req6);
		STS0.addRequirementSubset(req7);
		STS1.addRequirementSubset(req0);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req6);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req5);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		cover.prioritizeUsingHarroldGuptaSoffa("coverage",2);
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Prioritized Tests for prioritizeUsingWalcottExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	
	//	System.out.println("*****\n\n");

	assertEquals( answerIterator.next().getName(),"test3");	
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test5");
	assertEquals( answerIterator.next().getName(),"test2");	
	assertEquals( answerIterator.next().getName(),"test4");
	assertEquals( answerIterator.next().getName(),"test1");

	}

public void testHarroldGuptaSoffaPrioritizeUsingWalcottExampleLA1() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req1.addCoveringTest(test0);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test3);		
		req3.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test4);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test0);		
		req6.addCoveringTest(test3);		
		req7.addCoveringTest(test0);		
		req7.addCoveringTest(test4);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req3);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS0.addRequirementSubset(req6);
		STS0.addRequirementSubset(req7);
		STS1.addRequirementSubset(req0);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req6);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req5);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		cover.prioritizeUsingHarroldGuptaSoffa("coverage",1);
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Prioritized Tests for prioritizeUsingWalcottExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	
	//	System.out.println("*****\n\n");

	assertEquals( answerIterator.next().getName(),"test3");	
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test5");
	assertEquals( answerIterator.next().getName(),"test2");	
	assertEquals( answerIterator.next().getName(),"test4");
	assertEquals( answerIterator.next().getName(),"test1");

	}
	
	public void testHarroldGuptaSoffaPrioritizeUsingWalcottExampleLA2() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req1.addCoveringTest(test0);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test3);		
		req3.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test4);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test0);		
		req6.addCoveringTest(test3);		
		req7.addCoveringTest(test0);		
		req7.addCoveringTest(test4);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req3);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS0.addRequirementSubset(req6);
		STS0.addRequirementSubset(req7);
		STS1.addRequirementSubset(req0);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req6);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req5);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);

		
		cover.prioritizeUsingHarroldGuptaSoffa("coverage",2);
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Prioritized Tests for prioritizeUsingWalcottExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	
	//	System.out.println("*****\n\n");

	assertEquals( answerIterator.next().getName(),"test3");	
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test5");
	assertEquals( answerIterator.next().getName(),"test2");	
	assertEquals( answerIterator.next().getName(),"test4");
	assertEquals( answerIterator.next().getName(),"test1");

	}

//****************************************************************************************

	public void testHarroldGuptaSoffaPrioritizeUsingTallamGuptaExampleLA0() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		// make singletest
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);

		// make requirementsubsets
		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);

		// make singletestsubset with singletests
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1); 
		req1.addCoveringTest(test0);
		req1.addCoveringTest(test2);
		req2.addCoveringTest(test0);
		req2.addCoveringTest(test3);
		req3.addCoveringTest(test1);
		req4.addCoveringTest(test2);
		req4.addCoveringTest(test4);
		req5.addCoveringTest(test3);
	
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req3);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req4);

		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);

		// add SingleTestSubset to cover
		
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);

		cover.prioritizeUsingHarroldGuptaSoffa("coverage",0);
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nPrioritized Set for prioritizeUsingTallumGuptaExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");
	
	
	assertEquals( answerIterator.next().getName(),"test1");	
	assertEquals( answerIterator.next().getName(),"test3");
	assertEquals( answerIterator.next().getName(),"test2");	
	assertEquals( answerIterator.next().getName(),"test0");
	assertEquals( answerIterator.next().getName(),"test4");
	
	}	
	
		public void testHarroldGuptaSoffaPrioritizeUsingTallamGuptaExampleLA1() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		// make singletest
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);

		// make requirementsubsets
		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);

		// make singletestsubset with singletests
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1); 
		req1.addCoveringTest(test0);
		req1.addCoveringTest(test2);
		req2.addCoveringTest(test0);
		req2.addCoveringTest(test3);
		req3.addCoveringTest(test1);
		req4.addCoveringTest(test2);
		req4.addCoveringTest(test4);
		req5.addCoveringTest(test3);
	
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req3);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req4);

		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);

		// add SingleTestSubset to cover
		
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);

		cover.prioritizeUsingHarroldGuptaSoffa("coverage",1);
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nPrioritized Set for prioritizeUsingTallumGuptaExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");
	
	
	assertEquals( answerIterator.next().getName(),"test1");	
	assertEquals( answerIterator.next().getName(),"test3");
	assertEquals( answerIterator.next().getName(),"test2");	
	assertEquals( answerIterator.next().getName(),"test0");
	assertEquals( answerIterator.next().getName(),"test4");
	
	}	
		public void testHarroldGuptaSoffaPrioritizeUsingTallamGuptaExampleLA2() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		// make singletest
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);

		// make requirementsubsets
		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);

		// make singletestsubset with singletests
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1); 
		req1.addCoveringTest(test0);
		req1.addCoveringTest(test2);
		req2.addCoveringTest(test0);
		req2.addCoveringTest(test3);
		req3.addCoveringTest(test1);
		req4.addCoveringTest(test2);
		req4.addCoveringTest(test4);
		req5.addCoveringTest(test3);
	
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req3);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req4);

		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);

		// add SingleTestSubset to cover
		
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		
		cover.prioritizeUsingHarroldGuptaSoffa("coverage",2);
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nPrioritized Set for prioritizeUsingTallumGuptaExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");
	
	
	assertEquals( answerIterator.next().getName(),"test1");	
	assertEquals( answerIterator.next().getName(),"test3");
	assertEquals( answerIterator.next().getName(),"test2");	
	assertEquals( answerIterator.next().getName(),"test0");
	assertEquals( answerIterator.next().getName(),"test4");
	
	}	

//****************************************************************************************

	public void testHarroldGuptaSoffaPrioritizeUsingHarroldGuptaSoffaExampleLA0() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
		
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);		SingleTest test6 = new SingleTest("test6",6);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);
		SingleTestSubset STS6 = new SingleTestSubset(test6);
		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test4);		
		req1.addCoveringTest(test4);		
		req2.addCoveringTest(test0);		
		req2.addCoveringTest(test1);		
		req2.addCoveringTest(test2);		
		req3.addCoveringTest(test2);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test3);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test2);		
		req6.addCoveringTest(test3);		
		req6.addCoveringTest(test6);		
		req7.addCoveringTest(test1);		
		req7.addCoveringTest(test2);		
		req7.addCoveringTest(test3);		
		req7.addCoveringTest(test6);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS1.addRequirementSubset(req2);
		STS1.addRequirementSubset(req7);
		STS2.addRequirementSubset(req2);
		STS2.addRequirementSubset(req3);
		STS2.addRequirementSubset(req6);
		STS2.addRequirementSubset(req7);
		STS3.addRequirementSubset(req4);
		STS3.addRequirementSubset(req6);
		STS3.addRequirementSubset(req7);
		STS4.addRequirementSubset(req0);
		STS4.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		STS6.addRequirementSubset(req6);
		STS6.addRequirementSubset(req7);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		cover.addSingleTestSubset(STS6);

		cover.prioritizeUsingHarroldGuptaSoffa("coverage",0);
		covered = cover.getPrioritizedSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
	//	System.out.println("*****\nPrioritized Set for PrioritizeUsingHarroldGuptaSoffaExample:\n");
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
	
	assertEquals( answerIterator.next().getName(),"test4");	
	assertEquals( answerIterator.next().getName(),"test5");
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test2");
	assertEquals( answerIterator.next().getName(),"test1");
	assertEquals( answerIterator.next().getName(),"test3");
	assertEquals( answerIterator.next().getName(),"test6");
	
	}
	
		public void testHarroldGuptaSoffaPrioritizeUsingHarroldGuptaSoffaExampleLA1() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
		
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);		SingleTest test6 = new SingleTest("test6",6);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);
		SingleTestSubset STS6 = new SingleTestSubset(test6);
		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test4);		
		req1.addCoveringTest(test4);		
		req2.addCoveringTest(test0);		
		req2.addCoveringTest(test1);		
		req2.addCoveringTest(test2);		
		req3.addCoveringTest(test2);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test3);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test2);		
		req6.addCoveringTest(test3);		
		req6.addCoveringTest(test6);		
		req7.addCoveringTest(test1);		
		req7.addCoveringTest(test2);		
		req7.addCoveringTest(test3);		
		req7.addCoveringTest(test6);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS1.addRequirementSubset(req2);
		STS1.addRequirementSubset(req7);
		STS2.addRequirementSubset(req2);
		STS2.addRequirementSubset(req3);
		STS2.addRequirementSubset(req6);
		STS2.addRequirementSubset(req7);
		STS3.addRequirementSubset(req4);
		STS3.addRequirementSubset(req6);
		STS3.addRequirementSubset(req7);
		STS4.addRequirementSubset(req0);
		STS4.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		STS6.addRequirementSubset(req6);
		STS6.addRequirementSubset(req7);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		cover.addSingleTestSubset(STS6);

		cover.prioritizeUsingHarroldGuptaSoffa("coverage",1);
		covered = cover.getPrioritizedSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
	//	System.out.println("*****\nPrioritized Set for PrioritizeUsingHarroldGuptaSoffaExample:\n");
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
	
	assertEquals( answerIterator.next().getName(),"test4");	
	assertEquals( answerIterator.next().getName(),"test5");
	assertEquals( answerIterator.next().getName(),"test0");	
	assertEquals( answerIterator.next().getName(),"test2");
	assertEquals( answerIterator.next().getName(),"test1");
	assertEquals( answerIterator.next().getName(),"test3");
	assertEquals( answerIterator.next().getName(),"test6");
	
	}
	
		public void testHarroldGuptaSoffaPrioritizeUsingHarroldGuptaSoffaExampleLA2() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
		
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);		SingleTest test6 = new SingleTest("test6",6);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);
		SingleTestSubset STS6 = new SingleTestSubset(test6);
		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test4);		
		req1.addCoveringTest(test4);		
		req2.addCoveringTest(test0);		
		req2.addCoveringTest(test1);		
		req2.addCoveringTest(test2);		
		req3.addCoveringTest(test2);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test3);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test2);		
		req6.addCoveringTest(test3);		
		req6.addCoveringTest(test6);		
		req7.addCoveringTest(test1);		
		req7.addCoveringTest(test2);		
		req7.addCoveringTest(test3);		
		req7.addCoveringTest(test6);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS1.addRequirementSubset(req2);
		STS1.addRequirementSubset(req7);
		STS2.addRequirementSubset(req2);
		STS2.addRequirementSubset(req3);
		STS2.addRequirementSubset(req6);
		STS2.addRequirementSubset(req7);
		STS3.addRequirementSubset(req4);
		STS3.addRequirementSubset(req6);
		STS3.addRequirementSubset(req7);
		STS4.addRequirementSubset(req0);
		STS4.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		STS6.addRequirementSubset(req6);
		STS6.addRequirementSubset(req7);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		cover.addSingleTestSubset(STS6);

		cover.prioritizeUsingHarroldGuptaSoffa("coverage",2);
		covered = cover.getPrioritizedSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
	//	System.out.println("*****\nPrioritized Set for PrioritizeUsingHarroldGuptaSoffaExample:\n");
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
	
	assertEquals( answerIterator.next().getName(),"test4");	
	assertEquals( answerIterator.next().getName(),"test5");
	assertEquals( answerIterator.next().getName(),"test3");	
	assertEquals( answerIterator.next().getName(),"test0");
	assertEquals( answerIterator.next().getName(),"test2");
	assertEquals( answerIterator.next().getName(),"test1");
	assertEquals( answerIterator.next().getName(),"test6");
	
	}
	
/**********************************************************************************************************************
***********************************************************************************************************************

						BEGIN THE TIME METRIC TESTS
					***********************************************************************************************************************
**********************************************************************************************************************/

public void testHarroldGuptaSoffaReduceUsingTallamGuptaExampleTimeLA0() {
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/TallamGuptaCoverage.dat","data/raise/reduce/setCovers/TallamGuptaTime.dat");
	
		cover.reduceUsingHarroldGuptaSoffa("time",0);
		covered = cover.getCoverPickSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");

	assertEquals( answerIterator.next().getName(),"SingleTest1");
	assertEquals( answerIterator.next().getName(),"SingleTest3");	
	assertEquals( answerIterator.next().getName(),"SingleTest2");
	}	

public void testHarroldGuptaSoffaReduceUsingHarroldGuptaSoffaExampleTimeLA0() {
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/HGSCoverage.dat","data/raise/reduce/setCovers/HGSTime.dat");
	
		cover.reduceUsingHarroldGuptaSoffa("time",0);
		covered = cover.getCoverPickSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

		//System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
		//	SingleTest currentTest = (SingleTest) answerIterator.next();

		//	System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");

	assertEquals( answerIterator.next().getName(),"SingleTest4");
	assertEquals( answerIterator.next().getName(),"SingleTest2");	
	assertEquals( answerIterator.next().getName(),"SingleTest3");	
	assertEquals( answerIterator.next().getName(),"SingleTest5");
	}		
	
	public void testHarroldGuptaSoffaPrioritizeUsingHarroldGuptaSoffaExampleTimeLA0() {
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/HGSCoverage.dat","data/raise/reduce/setCovers/HGSTime.dat");

		cover.prioritizeUsingHarroldGuptaSoffa("time",0);
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//		System.out.println("*****\nCovering Set for prioritizeUsingHarroldGuptaSoffaExample:\n");
		
	//		while (answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//		}
		
	//		System.out.println("*****\n\n");
	
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/HGSCoverage.dat","data/raise/reduce/setCovers/HGSTime.dat");
	
		assertTrue(cover.coversRequirementSubsetUniverse(covered));

		assertEquals( answerIterator.next().getName(),"SingleTest4");
		assertEquals( answerIterator.next().getName(),"SingleTest2");	
		assertEquals( answerIterator.next().getName(),"SingleTest3");	
		assertEquals( answerIterator.next().getName(),"SingleTest5");
		assertEquals( answerIterator.next().getName(),"SingleTest0");
		assertEquals( answerIterator.next().getName(),"SingleTest6");	
		assertEquals( answerIterator.next().getName(),"SingleTest1");
	}				
	
public void testHarroldGuptaSoffaReduceUsingHugeExampleRatioLA0() {
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/matrix-rlll5","data/raise/reduce/setCovers/time-rlll5");

		cover.reduceUsingHarroldGuptaSoffa("ratio",0);
		covered = cover.getCoverPickSets();

		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/matrix-rlll5","data/raise/reduce/setCovers/time-rlll5");
		
		assertTrue(cover.coversRequirementSubsetUniverse(covered));
		
		Iterator<SingleTest> answerIterator = covered.iterator();

	/*	System.out.println("*****\nCovering Set for reduceUsingHugeExample:\n");
		
		while ( answerIterator.hasNext()){
	
			SingleTest currentTest = (SingleTest) answerIterator.next();

			System.out.println(currentTest.toString());
		}
		
		System.out.println("*****\n\n");
		*/

	}	
	
	public static String printIntArray(int array[])
	{
		String returnString = "";
		returnString+="[";
		for(int i = 0;i<array.length-1;i++)
			returnString+= array[i]+",";	
			
		returnString+= array[array.length-1]+"]";
		
		return returnString;
	}
	
	/********************************************************************
	* Begin 2-Optimal Tests
	********************************************************************/
	public void test2OptimalReduceUsingTallamGuptaExampleCoverage() 
	{
		// make singletest
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);

		// make requirementsubsets
		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);

		// make singletestsubset with singletests
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1); 
		req1.addCoveringTest(test0);
		req1.addCoveringTest(test2);
		req2.addCoveringTest(test0);
		req2.addCoveringTest(test3);
		req3.addCoveringTest(test1);
		req4.addCoveringTest(test2);
		req4.addCoveringTest(test4);
		req5.addCoveringTest(test3);
	
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req3);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req4);

		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);

		// add SingleTestSubset to cover
		
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		
		
		
		cover.reduceUsing2Optimal("coverage");
		covered = cover.getCoverPickSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");

	assertTrue( answerIterator.next().getName().equals("test0"));
	assertTrue( answerIterator.next().getName().equals("test1"));
	assertTrue( answerIterator.next().getName().equals("test2"));
	assertTrue( answerIterator.next().getName().equals("test3"));
	}	



	public void test2OptimalReduceUsingHarroldGuptaSoffaExampleCoverage() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
		
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);
		SingleTest test6 = new SingleTest("test6",6);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);
		SingleTestSubset STS6 = new SingleTestSubset(test6);
		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test4);		
		req1.addCoveringTest(test4);		
		req2.addCoveringTest(test0);		
		req2.addCoveringTest(test1);		
		req2.addCoveringTest(test2);		
		req3.addCoveringTest(test2);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test3);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test2);		
		req6.addCoveringTest(test3);		
		req6.addCoveringTest(test6);		
		req7.addCoveringTest(test1);		
		req7.addCoveringTest(test2);		
		req7.addCoveringTest(test3);		
		req7.addCoveringTest(test6);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS1.addRequirementSubset(req2);
		STS1.addRequirementSubset(req7);
		STS2.addRequirementSubset(req2);
		STS2.addRequirementSubset(req3);
		STS2.addRequirementSubset(req6);
		STS2.addRequirementSubset(req7);
		STS3.addRequirementSubset(req4);
		STS3.addRequirementSubset(req6);
		STS3.addRequirementSubset(req7);
		STS4.addRequirementSubset(req0);
		STS4.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		STS6.addRequirementSubset(req6);
		STS6.addRequirementSubset(req7);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		cover.addSingleTestSubset(STS6);
	
		
		
		cover.reduceUsing2Optimal("coverage");
		covered = cover.getCoverPickSets();

		Iterator<SingleTest> answerIterator = covered.iterator();
	//	System.out.println("*****\nCovering Set for reduceUsingHarroldGuptaSoffa:\n");
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
		assertTrue( answerIterator.next().getName().equals("test0"));
		assertTrue( answerIterator.next().getName().equals("test2"));
		assertTrue( answerIterator.next().getName().equals("test1"));
		assertTrue( answerIterator.next().getName().equals("test4"));
	} 
	
	public void test2OptimalReduceUsingWalcottExampleCoverage() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
	
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req1.addCoveringTest(test0);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test3);		
		req3.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test4);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test0);		
		req6.addCoveringTest(test3);		
		req7.addCoveringTest(test0);		
		req7.addCoveringTest(test4);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req3);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS0.addRequirementSubset(req6);
		STS0.addRequirementSubset(req7);
		STS1.addRequirementSubset(req0);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req6);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req5);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		
		
		cover.reduceUsing2Optimal("coverage");
		covered = cover.getCoverPickSets();

		
		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Covering Tests For Walcott Reduction Example:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
	assertTrue( answerIterator.next().getName().equals("test0"));
	assertTrue( answerIterator.next().getName().equals("test3"));

	}

	public void test2OptimalReduceUsingAdamSmithFirstExampleCoverage() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		
		
		cover.reduceUsing2Optimal("coverage");
		covered = cover.getCoverPickSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Covering Tests for reduceUsingAdamSmithFirstExample:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");
	
	assertTrue( answerIterator.next().getName().equals("test1"));
	assertTrue( answerIterator.next().getName().equals("test4"));
	assertTrue( answerIterator.next().getName().equals("test0"));
	assertTrue( answerIterator.next().getName().equals("test2"));
	}
	

	public void test2OptimalPrioritizeUsingAdamSmithFirstExampleCoverage() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req0.addCoveringTest(test5);		
		req1.addCoveringTest(test1);		
		req1.addCoveringTest(test2);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test1);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test1);		
		req5.addCoveringTest(test3);		
		req6.addCoveringTest(test4);		
		req6.addCoveringTest(test5);		
		req7.addCoveringTest(test4);		
						
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req1);
		STS1.addRequirementSubset(req4);
		STS1.addRequirementSubset(req5);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req6);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req0);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req6);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		
		cover.prioritizeUsing2Optimal("coverage");
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Prioritized Tests for prioritizeUsingAdamSmithFirstExample:\n");

	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");

	assertTrue( answerIterator.next().getName().equals("test1"));
	assertTrue( answerIterator.next().getName().equals("test4"));
	assertTrue( answerIterator.next().getName().equals("test0"));
	assertTrue( answerIterator.next().getName().equals("test2"));
	assertTrue( answerIterator.next().getName().equals("test3"));
	assertTrue( answerIterator.next().getName().equals("test5"));

	}

public void test2OptimalPrioritizeUsingWalcottExampleCoverage() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);

		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1);		
		req0.addCoveringTest(test2);		
		req1.addCoveringTest(test0);		
		req1.addCoveringTest(test3);		
		req1.addCoveringTest(test5);		
		req2.addCoveringTest(test3);		
		req3.addCoveringTest(test0);		
		req3.addCoveringTest(test4);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test2);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test4);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test0);		
		req6.addCoveringTest(test3);		
		req7.addCoveringTest(test0);		
		req7.addCoveringTest(test4);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req3);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS0.addRequirementSubset(req6);
		STS0.addRequirementSubset(req7);
		STS1.addRequirementSubset(req0);
		STS2.addRequirementSubset(req0);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req1);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req6);
		STS4.addRequirementSubset(req3);
		STS4.addRequirementSubset(req5);
		STS4.addRequirementSubset(req7);
		STS5.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		
		
		cover.prioritizeUsing2Optimal("coverage");
		covered = cover.getPrioritizedSets();


		Iterator<SingleTest> answerIterator = covered.iterator();

	//	System.out.println("*****\nSet of Prioritized Tests for prioritizeUsingWalcottExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	
	//	System.out.println("*****\n\n");

		assertTrue( answerIterator.next().getName().equals("test0"));
		assertTrue( answerIterator.next().getName().equals("test3"));
		assertTrue( answerIterator.next().getName().equals("test2"));
		assertTrue( answerIterator.next().getName().equals("test4"));
		assertTrue( answerIterator.next().getName().equals("test1"));
		assertTrue( answerIterator.next().getName().equals("test5"));

	}

	public void test2OptimalPrioritizeUsingTallamGuptaExampleCoverage() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());

		// make singletest
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);

		// make requirementsubsets
		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);

		// make singletestsubset with singletests
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);

		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test1); 
		req1.addCoveringTest(test0);
		req1.addCoveringTest(test2);
		req2.addCoveringTest(test0);
		req2.addCoveringTest(test3);
		req3.addCoveringTest(test1);
		req4.addCoveringTest(test2);
		req4.addCoveringTest(test4);
		req5.addCoveringTest(test3);
	
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req1);
		STS0.addRequirementSubset(req2);
		STS1.addRequirementSubset(req0);
		STS1.addRequirementSubset(req3);
		STS2.addRequirementSubset(req1);
		STS2.addRequirementSubset(req4);
		STS3.addRequirementSubset(req2);
		STS3.addRequirementSubset(req5);
		STS4.addRequirementSubset(req4);

		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);

		// add SingleTestSubset to cover
		
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		
		
		cover.prioritizeUsing2Optimal("coverage");
		covered = cover.getPrioritizedSets();

		Iterator<SingleTest> answerIterator = covered.iterator();

		//System.out.println("*****\nPrioritized Set for prioritizeUsingTallumGuptaExample:\n");
		
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
		
	//	System.out.println("*****\n\n");


	assertTrue( answerIterator.next().getName().equals("test0"));
	assertTrue( answerIterator.next().getName().equals("test1"));
	assertTrue( answerIterator.next().getName().equals("test2"));
	assertTrue( answerIterator.next().getName().equals("test3"));
	assertTrue( answerIterator.next().getName().equals("test4"));

	}	

	public void test2OptimalPrioritizeUsingHarroldGuptaSoffaExampleCoverage() {
		assertEquals(0, cover.getTestSubsets().size());
		assertEquals(0, cover.getRequirementSubsetUniverse().size());
		
		SingleTest test0 = new SingleTest("test0",0);
		SingleTest test1 = new SingleTest("test1",1);
		SingleTest test2 = new SingleTest("test2",2);
		SingleTest test3 = new SingleTest("test3",3);
		SingleTest test4 = new SingleTest("test4",4);
		SingleTest test5 = new SingleTest("test5",5);
		SingleTest test6 = new SingleTest("test6",6);

		RequirementSubset req0 = new RequirementSubset("req0",0);
		RequirementSubset req1 = new RequirementSubset("req1",1);
		RequirementSubset req2 = new RequirementSubset("req2",2);
		RequirementSubset req3 = new RequirementSubset("req3",3);
		RequirementSubset req4 = new RequirementSubset("req4",4);
		RequirementSubset req5 = new RequirementSubset("req5",5);
		RequirementSubset req6 = new RequirementSubset("req6",6);
		RequirementSubset req7 = new RequirementSubset("req7",7);
	
		SingleTestSubset STS0 = new SingleTestSubset(test0);
		SingleTestSubset STS1 = new SingleTestSubset(test1);
		SingleTestSubset STS2 = new SingleTestSubset(test2);
		SingleTestSubset STS3 = new SingleTestSubset(test3);
		SingleTestSubset STS4 = new SingleTestSubset(test4);
		SingleTestSubset STS5 = new SingleTestSubset(test5);
		SingleTestSubset STS6 = new SingleTestSubset(test6);
		
		// add SingleTests to requirementSubsets
		req0.addCoveringTest(test0);		
		req0.addCoveringTest(test4);		
		req1.addCoveringTest(test4);		
		req2.addCoveringTest(test0);		
		req2.addCoveringTest(test1);		
		req2.addCoveringTest(test2);		
		req3.addCoveringTest(test2);		
		req3.addCoveringTest(test5);		
		req4.addCoveringTest(test0);		
		req4.addCoveringTest(test3);		
		req5.addCoveringTest(test0);		
		req5.addCoveringTest(test5);		
		req6.addCoveringTest(test2);		
		req6.addCoveringTest(test3);		
		req6.addCoveringTest(test6);		
		req7.addCoveringTest(test1);		
		req7.addCoveringTest(test2);		
		req7.addCoveringTest(test3);		
		req7.addCoveringTest(test6);		
		
		
		// add requirementsubsets to STSs
		STS0.addRequirementSubset(req0);
		STS0.addRequirementSubset(req2);
		STS0.addRequirementSubset(req4);
		STS0.addRequirementSubset(req5);
		STS1.addRequirementSubset(req2);
		STS1.addRequirementSubset(req7);
		STS2.addRequirementSubset(req2);
		STS2.addRequirementSubset(req3);
		STS2.addRequirementSubset(req6);
		STS2.addRequirementSubset(req7);
		STS3.addRequirementSubset(req4);
		STS3.addRequirementSubset(req6);
		STS3.addRequirementSubset(req7);
		STS4.addRequirementSubset(req0);
		STS4.addRequirementSubset(req1);
		STS5.addRequirementSubset(req3);
		STS5.addRequirementSubset(req5);
		STS6.addRequirementSubset(req6);
		STS6.addRequirementSubset(req7);
		
		// add requirementSubsets to the cover
		cover.addRequirementSubset(req0);
		cover.addRequirementSubset(req1);
		cover.addRequirementSubset(req2);
		cover.addRequirementSubset(req3);
		cover.addRequirementSubset(req4);
		cover.addRequirementSubset(req5);
		cover.addRequirementSubset(req6);
		cover.addRequirementSubset(req7);
	
	
		// add SingleTestSubset to cover
		cover.addSingleTestSubset(STS0);
		cover.addSingleTestSubset(STS1);
		cover.addSingleTestSubset(STS2);
		cover.addSingleTestSubset(STS3);
		cover.addSingleTestSubset(STS4);
		cover.addSingleTestSubset(STS5);
		cover.addSingleTestSubset(STS6);
	
		
		cover.prioritizeUsing2Optimal("coverage");
		covered = cover.getPrioritizedSets();
		
		Iterator<SingleTest> answerIterator = covered.iterator();
	//	System.out.println("*****\nPrioritized Set for PrioritizeUsingHarroldGuptaSoffaExample:\n");
	//	while ( answerIterator.hasNext()){
	
	//		SingleTest currentTest = (SingleTest) answerIterator.next();

	//		System.out.println(currentTest.toString());
	//	}
	//	System.out.println("*****\n\n");
	
		assertTrue( answerIterator.next().getName().equals("test0"));
		assertTrue( answerIterator.next().getName().equals("test2"));
		assertTrue( answerIterator.next().getName().equals("test1"));
		assertTrue( answerIterator.next().getName().equals("test4"));
		assertTrue( answerIterator.next().getName().equals("test3"));
		assertTrue( answerIterator.next().getName().equals("test5"));
		assertTrue( answerIterator.next().getName().equals("test6"));
	}
/////////////////////////////////////END COVERAGE TESTS///////////////////////////////////////////////////
	public void test2OptimalReduceUsingTallamGuptaExampleRatio()
		{
	
			cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/TallamGuptaCoverage.dat","data/raise/reduce/setCovers/TallamGuptaTime.dat");
			
			cover.reduceUsing2Optimal("ratio");
			covered = cover.getCoverPickSets();
	
			Iterator<SingleTest> answerIterator = covered.iterator();

		//	System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
			
		//	while ( answerIterator.hasNext()){
		
		//		SingleTest currentTest = (SingleTest) answerIterator.next();
	
		//		System.out.println(currentTest.toString());
		//	}
			
		//	System.out.println("*****\n\n");

		assertTrue( answerIterator.next().getName().equals("SingleTest1"));
		assertTrue( answerIterator.next().getName().equals("SingleTest4"));
		assertTrue( answerIterator.next().getName().equals("SingleTest2"));
		assertTrue( answerIterator.next().getName().equals("SingleTest3"));
	}
	
	public void test2OptimalReduceUsingHarroldGuptaSoffaExampleRatio() 
	{
	
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/HGSCoverage.dat","data/raise/reduce/setCovers/HGSTime.dat");
		
		cover.reduceUsing2Optimal("ratio");
			covered = cover.getCoverPickSets();
	
			Iterator<SingleTest> answerIterator = covered.iterator();

			//System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
			
		//	while ( answerIterator.hasNext()){
		
		//		SingleTest currentTest = (SingleTest) answerIterator.next();
	
		//		System.out.println(currentTest.toString());
		//	}
			
		//	System.out.println("*****\n\n");

		assertTrue( answerIterator.next().getName().equals("SingleTest2"));
		assertTrue( answerIterator.next().getName().equals("SingleTest5"));
		assertTrue( answerIterator.next().getName().equals("SingleTest3"));
		assertTrue( answerIterator.next().getName().equals("SingleTest4"));
	}

/////////////////////////////////////////////END OF RATIO TESTS////////////////////////////////////////////////
	public void test2OptimalReduceUsingTallamGuptaExampleTime()
	{
	
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/TallamGuptaCoverage.dat","data/raise/reduce/setCovers/TallamGuptaTime.dat");
			
		cover.reduceUsing2Optimal("time");
		covered = cover.getCoverPickSets();
	
		Iterator<SingleTest> answerIterator = covered.iterator();

		//System.out.println("*****\nCovering Set for reduceUsingTallumGuptaExample:\n");
			
		//	while ( answerIterator.hasNext()){
		
		//		SingleTest currentTest = (SingleTest) answerIterator.next();
	
		//		System.out.println(currentTest.toString());
		//	}
			
		//	System.out.println("*****\n\n");

		assertTrue( answerIterator.next().getName().equals("SingleTest2"));
		assertTrue( answerIterator.next().getName().equals("SingleTest4"));
		assertTrue( answerIterator.next().getName().equals("SingleTest0"));
		assertTrue( answerIterator.next().getName().equals("SingleTest1"));
		assertTrue( answerIterator.next().getName().equals("SingleTest3"));
	}
	
public void test2OptimalReduceUsing2OptimalayHugeExampleRatio() {
		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/matrix-rlll5","data/raise/reduce/setCovers/time-rlll5");

		cover.reduceUsing2Optimal("ratio");
		
		covered = cover.getCoverPickSets();

		cover = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/matrix-rlll5","data/raise/reduce/setCovers/time-rlll5");
		
		assertTrue(cover.coversRequirementSubsetUniverse(covered));
		
		/*Iterator answerIterator = covered.iterator();

		System.out.println("*****\nCovering Set for reduceUsingHugeExample:\n");
		
		while ( answerIterator.hasNext()){
	
			SingleTest currentTest = (SingleTest) answerIterator.next();

			System.out.println(currentTest.toString());
		}
		
		System.out.println("*****\n\n");
		*/
	}	

	public void testConstructFromCoverageAndTimeSameNumberOfTestsAndRequirements()
	{

		// This will only work if includeNonCoveringTests == false at the beginning o constructSetCoverFromCoverageAndTime
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/DSCoverage.dat","data/raise/reduce/setCovers/DSTime.dat",false);
		cover2 = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/DSMatrix.dat","data/raise/reduce/setCovers/DSTime.dat");
	
		// Using Matrix files creates different indexes than Coverage files so I can't compare
		// the resulting SetCovers with toString. 
		
		//  I check to see if they each have the same number of tests and requirements
		
		assertEquals(cover.getRequirementSubsetUniverse().size(), cover2.getRequirementSubsetUniverse().size());
		assertEquals(cover.getTestSubsets().size(), cover2.getTestSubsets().size());
	}
	
	public void testConstructFromCoverageAndTimeTestsCoverSameNumberOfRequirements()
	{
		// This will only work if includeNonCoveringTests == false at the beginning o constructSetCoverFromCoverageAndTime
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/DSCoverage.dat","data/raise/reduce/setCovers/DSTime.dat", false);
		cover2 = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/DSMatrix.dat","data/raise/reduce/setCovers/DSTime.dat");
	
		Iterator testIt1 = cover.getTestSubsets().iterator();
		Iterator testIt2 = cover2.getTestSubsets().iterator();
		
		// Here I see if every test covers the same number of requirements.
		// This assumes that the test orders are the same, which they should be after they are built.
		while(testIt1.hasNext())
		{
			SingleTestSubset firstTest = (SingleTestSubset) testIt1.next(); 
			SingleTestSubset secondTest = (SingleTestSubset) testIt2.next();
			
			assertEquals(firstTest.getRequirementSubsetSet().size(),secondTest.getRequirementSubsetSet().size());
		}
		
	}
	
	public void testConstructFromCoverageAndTimeSameNumberOfRequirements()
	{
		// This will only work if includeNonCoveringTests == false at the beginning o constructSetCoverFromCoverageAndTime
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/DSCoverage.dat","data/raise/reduce/setCovers/DSTime.dat", false);
		cover2 = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/DSMatrix.dat","data/raise/reduce/setCovers/DSTime.dat");
	
		 
		assertEquals(cover.getRequirementSubsetUniverse().size(),cover2.getRequirementSubsetUniverse().size());
		 
		
	}
	
	public void testConstructFromCoverageAndTimeRequirementsCoveredBySameNumberOfTests()
	{
		
		// This will only work if includeNonCoveringTests == false at the beginning o constructSetCoverFromCoverageAndTime
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/ADCoverage.dat","data/raise/reduce/setCovers/ADTime.dat", false);
		cover2 = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/ADMatrix.dat","data/raise/reduce/setCovers/ADTime.dat");
	
		RequirementSubset[] one = (RequirementSubset[]) (cover.getRequirementSubsetUniverse()).toArray(new RequirementSubset[0]); 
		RequirementSubset[] two = (RequirementSubset[]) (cover2.getRequirementSubsetUniverse()).toArray(new RequirementSubset[0]);
		
		Arrays.sort(one);
		Arrays.sort(two);
		
		for(int i = 0;i<one.length;i++)
		{
			assertEquals(one[i].getIndex(), two[i].getIndex()+1);
		}
	}
	
	public void testConstructFromCoverageAndTimeTestsCoverAllRequirements()
	{
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/ADCoverage.dat","data/raise/reduce/setCovers/ADTime.dat", false);
	
		assertTrue(cover.coversRequirementSubsetUniverse(cover.getSingleTests()));
			
	}
	
	public void testConstructFromCoverageAndTimeIdenticalTimeFileOutput()
	{
		String tempTime1Name = "./tempTime1";
		String tempTime2Name = "./tempTime2";
		
		File tempTime1 = new File(tempTime1Name);
		File tempTime2 = new File(tempTime2Name);
	
		if(tempTime1.exists())
			tempTime1.delete();
		if(tempTime2.exists())
			tempTime2.delete();
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/DSCoverage.dat","data/raise/reduce/setCovers/DSTime.dat", false);
		cover2 = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/DSMatrix.dat","data/raise/reduce/setCovers/DSTime.dat");

		GenerateCoverageEffectivenessData g1 = new GenerateCoverageEffectivenessData(cover);
		GenerateCoverageEffectivenessData g2 = new GenerateCoverageEffectivenessData(cover2);
	
		g1.saveTimingData(tempTime1Name);
		g2.saveTimingData(tempTime2Name);
		
		Scanner inTime1 = null;
		Scanner inTime2 = null;
		try
		{
			inTime1 = new Scanner(tempTime1);
			inTime2 = new Scanner(tempTime2);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

		int flip = 1;
		
		// Skip the labels
		inTime1.nextLine();
		inTime2.nextLine();		
		
		while(inTime1.hasNext())
		{
			
			if(flip > 0)
			{
				inTime1.nextInt();
				inTime2.nextInt();
			}
			else
				assertEquals(inTime1.nextDouble(), inTime2.nextDouble());
			
			flip *= -1;
		}
		
		tempTime1.delete();
		tempTime2.delete();	
	}
	
	/*
	  This doesn't work because the requirements are not in any particular order.
	public void testConstructFromCoverageAndTimeIdenticalMatrixOutput()
	{
		String tempCov1Name = "./tempCov1";
		String tempCov2Name = "./tempCov2";
		
		File tempCov1 = new File(tempCov1Name);
		File tempCov2 = new File(tempCov2Name);
		
		if(tempCov1.exists())
			tempCov1.delete();
		if(tempCov2.exists())
			tempCov2.delete();
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/DSCoverage.dat","data/raise/reduce/setCovers/DSTime.dat", false);
		cover2 = SetCover.constructSetCoverFromMatrix("data/raise/reduce/setCovers/DSMatrix.dat","data/raise/reduce/setCovers/DSTime.dat");

		RequirementSubset[] reqs = (RequirementSubset[]) cover.getRequirementSubsetUniverse().toArray(new RequirementSubset[0]);
		
		LinkedHashSet reqHS = cover.getRequirementSubsetUniverse();
		reqHS.clear();
		System.out.println("REQS: "+cover.getRequirementSubsetUniverse().size());
		
		Arrays.sort(reqs);
		
		for(int i = 0; i < reqs.length; i++)
		{
			cover.addRequirementSubset(reqs[i]);
		}
		
		GenerateCoverageEffectivenessData g1 = new GenerateCoverageEffectivenessData(cover);
		GenerateCoverageEffectivenessData g2 = new GenerateCoverageEffectivenessData(cover2);
		
		g1.saveMatrixData(tempCov1Name);
		g2.saveMatrixData(tempCov2Name);
		
		Scanner inCov1 = null;
		Scanner inCov2 = null;
		try
		{
			inCov1 = new Scanner(tempCov1);
			inCov2 = new Scanner(tempCov2);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

		int flip = 1;
		
		
		while(inCov1.hasNext())
		{
			assertEquals(inCov1.next(), inCov2.next());
		}
		
		
		tempCov1.delete();
		tempCov2.delete();
	}
	*/
	
	public void testConstructFromCoverageAndTimeFileCompleteCoveringTestReference()
	{
		boolean completeCoveringTestReference = true;
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/ADCoverage.dat","data/raise/reduce/setCovers/ADTime.dat", false);
		
		Iterator<RequirementSubset> reqIt = cover.getRequirementSubsetUniverse().iterator();
		
		while(reqIt.hasNext())  
		{
			RequirementSubset currentReq = reqIt.next(); 
			
			Iterator<SingleTest> testIt = currentReq.getCoveringTests().iterator();		
			while(testIt.hasNext())
			{
				SingleTest currentTest = testIt.next();
	
				Iterator<SingleTestSubset> STSIT = cover.getTestSubsets().iterator();
				boolean singleTestSubsetExists = false;
				
				while(STSIT.hasNext())
				{
					SingleTestSubset currentSTS = STSIT.next();
					
					if(currentSTS.getTest().getIndex() == currentTest.getIndex())
					{
						singleTestSubsetExists = true;
						
						boolean foundRequirement = false;
						Iterator<RequirementSubset> coveredReqsIt = currentSTS.getRequirementSubsetSet().iterator();
						
						while(coveredReqsIt.hasNext())
						{
							if(coveredReqsIt.next().getIndex() == currentReq.getIndex())
							{
								foundRequirement = true;
								break;
							}							
						}

						if(foundRequirement == false)
							completeCoveringTestReference = false;
					}
					
				}
				assertTrue(singleTestSubsetExists);
			}
		}
		
		assertTrue(completeCoveringTestReference);
		
	}
	
	public void testConstructFromCoverageAndTimeFileCompleteCoveredRequirementReferences()
	{
		boolean completeCoveredRequirementReference = true;
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/ADCoverage.dat","data/raise/reduce/setCovers/ADTime.dat", false);
		
		Iterator<SingleTestSubset> testIt = cover.getTestSubsets().iterator();
		
		while(testIt.hasNext())  
		{
			SingleTestSubset currentTest = testIt.next(); 
			
			Iterator<RequirementSubset> coveredReqIt = currentTest.getRequirementSubsetSet().iterator();		
			while(coveredReqIt.hasNext())
			{
				RequirementSubset currentCoveredReq = coveredReqIt.next();
	
				Iterator<RequirementSubset> reqIT = cover.getRequirementSubsetUniverse().iterator();
				boolean requirementSubsetExists = false;
				
				while(reqIT.hasNext())
				{
					RequirementSubset currentReq = reqIT.next();
					
					if(currentReq.getIndex() == currentCoveredReq.getIndex())
					{
						requirementSubsetExists = true;
						
						boolean foundTest = false;
						Iterator<SingleTest> coveringSingleTestIt = currentReq.getCoveringTests().iterator();
						
						while(coveringSingleTestIt.hasNext())
						{
							if(coveringSingleTestIt.next().getIndex() == currentTest.getTest().getIndex())
							{
								foundTest = true;
								break;
							}							
						}

						if(foundTest == false)
							completeCoveredRequirementReference = false;
					}
					
				}
				assertTrue(requirementSubsetExists);
			}
		}
		
		assertTrue(completeCoveredRequirementReference);	
	}

	/**
	 * This tests whether the coveringTests list of a RequirementSubset in a SingleTestSubset's 
	 * requirementSubsetSet contains the SingleTest corresponding to that SingleTestSubset.
	 * 
	 */
	
	public void testConstructFromCoverageAndTimeFileCompleteCoveredRequirementInternalReferences()
	{
		boolean completeCoveredRequirementReference = true;
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/ADCoverage.dat","data/raise/reduce/setCovers/ADTime.dat", false);
		
		Iterator<SingleTestSubset> testIt = cover.getTestSubsets().iterator();
		
		while(testIt.hasNext())  
		{
			SingleTestSubset currentTest = testIt.next(); 
			
			Iterator<RequirementSubset> coveredReqIt = currentTest.getRequirementSubsetSet().iterator();		
			while(coveredReqIt.hasNext())
			{
				RequirementSubset currentCoveredReq = coveredReqIt.next();

				boolean foundTest = false;
				Iterator<SingleTest> coveringSingleTestIt = currentCoveredReq.getCoveringTests().iterator();
				
				while(coveringSingleTestIt.hasNext())
				{
					if(coveringSingleTestIt.next().getIndex() == currentTest.getTest().getIndex())
					{
						foundTest = true;
						break;
					}							
				}

				if(foundTest == false)
					completeCoveredRequirementReference = false;
			}
		}
		
		assertTrue(completeCoveredRequirementReference);	
	}

	
	/**
	 *  This tests whether or not the RequirementSubsets in the RequirementSubsetUniverse that
	 *  have the same index as RequirementSubsets in the covered requirement lists in the 
	 *  SingleTestSubset objects are the same object.
	 */
	public void testConstructSetCoverFromCoverageAndTimeFileRequirementSubsetsSameObject()
	{		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/ADCoverage.dat","data/raise/reduce/setCovers/ADTime.dat", false);
		
		Iterator<SingleTestSubset> testIt = cover.getTestSubsets().iterator();
		
		while(testIt.hasNext())  
		{
			SingleTestSubset currentTest = testIt.next(); 
			
			Iterator<RequirementSubset> coveredReqIt = currentTest.getRequirementSubsetSet().iterator();		
			while(coveredReqIt.hasNext())
			{
				RequirementSubset currentCoveredReq = coveredReqIt.next();
				Iterator<RequirementSubset> reqIT = cover.getRequirementSubsetUniverse().iterator();
				
				while(reqIT.hasNext())
				{
					RequirementSubset currentReq = reqIT.next();
					
					if(currentReq.getIndex() == currentCoveredReq.getIndex())
					{
						assertTrue(currentReq == currentCoveredReq);
					}
				}
			}
		}	
	}
}

