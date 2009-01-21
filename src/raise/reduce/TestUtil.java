/*---------------------------------------------------------------------
 *  File: $Id: TestUtil.java,v 1.7 2005/12/03 20:55:49 gkapfham Exp $   
 *  Version:  $Revision: 1.7 $
 *
 *  Project: DIATOMS, Database drIven Application Testing tOol ModuleS
 *
 *--------------------------------------------------------------------*/

package raise.reduce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 *  This class tests the Util class.
 *
 *  @author Gregory M. Kapfhammer 10/4/2005
 */

public class TestUtil extends TestCase
{
    
    /**
     *  Required constructor.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public TestUtil(String name)
    {

	super(name);
    
    }

    /**
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public void setUp()
    {

    }

    /**
     *  Tests to make sure that a simple file can be read and just 
     *  one timing can be extracted.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public void testReadTestTimingsFromSimpleFile()
    {

	// note that these files are stored inside of the directory
	// where all of the diatoms.reduce source code is located

	String fileName = "src/diatoms/reduce/oneTiming";

	Double expected = new Double(.003);
	String expectedName = new String("testCloneSetCover");

	HashMap testTimings = Util.readTestTimings(fileName);
	assertEquals(1, testTimings.size());

	// extract the set of values and turn that into an ArrayList
	// for comparison (we want to make sure that correct data
	// exists inside of the HashMap)
	Collection valuesSet = testTimings.values();
	ArrayList valuesList = new ArrayList(valuesSet);

	assertTrue(valuesList.get(0) instanceof Double);
	assertEquals(expected.doubleValue(), 
		     ( (Double) valuesList.get(0) ).doubleValue(), 0);

	// extract the set of keys, turn it into an ArrayList
	Collection keySet = testTimings.keySet();
	ArrayList keyList = new ArrayList(keySet);

	// make sure that the name of the test is correct
	assertTrue(keyList.get(0) instanceof String);
	assertEquals(expectedName, (String) keyList.get(0));
	
    }

    /**
     *  Tests to make sure that a simple file can be read and multiple
     *  timings can be extracted.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public void testReadMultipleTestTimingsFromFile()
    {

	String fileName = "src/diatoms/reduce/multipleTimings";

	Double expectedZero = new Double(.003);
	Double expectedOne = new Double(.103);
	Double expectedTwo = new Double(1111.003);

	HashMap testTimings = Util.readTestTimings(fileName);
	assertEquals(3, testTimings.size());
	
	// extract the set of values, turn it into an array, and 
	// turn that into an ArrayList for comparison
	Collection valueSet = testTimings.values();
	ArrayList valueList = new ArrayList(valueSet);
	
	// sort so that it is easy to determine which values
	// are inside of the valueList in a pre-defined order
	Collections.sort(valueList);

	assertTrue(valueList.get(0) instanceof Double);

	assertEquals(expectedZero.doubleValue(), 
		     ( (Double) valueList.get(0) ).doubleValue(), 0);

	assertEquals(expectedOne.doubleValue(), 
		     ( (Double) valueList.get(1) ).doubleValue(), 0);

	assertEquals(expectedTwo.doubleValue(), 
		     ( (Double) valueList.get(2) ).doubleValue(), 0);

    }

    /**
     *  Tests to make sure that a simple file can be read and multiple
     *  timings can be extracted.  Also, checks to make sure that the 
     *  zero valued timing tests are handled properly.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public void testReadMultipleTestTimingsAndZeroTimingFromFile()
    {

	String fileName = "src/diatoms/reduce/multipleAndZeroTimings";

	Double expectedZero = new Double(.003);
	Double expectedOne = new Double(.103);
	Double expectedTwo = new Double(1111.003);
	Double expectedThree = new Double(.0003);

	HashMap testTimingsMap = Util.readTestTimings(fileName);
	assertEquals(4, testTimingsMap.keySet().size());

	// extract the set of values, turn it into an array, and 
	// turn that into an ArrayList for comparison
	Collection valueSet = testTimingsMap.values();
	ArrayList valueList = new ArrayList(valueSet);
	
	// sort so that it is easy to determine which values
	// are inside of the valueList in a pre-defined order
	Collections.sort(valueList);

	assertTrue(valueList.get(0) instanceof Double);

	assertEquals(expectedThree.doubleValue(), 
		     ( (Double) valueList.get(0) ).doubleValue(), .0001);

	assertEquals(expectedZero.doubleValue(), 
		     ( (Double) valueList.get(1) ).doubleValue(), 0);

	assertEquals(expectedOne.doubleValue(), 
		     ( (Double) valueList.get(2) ).doubleValue(), 0);

	assertEquals(expectedTwo.doubleValue(), 
		     ( (Double) valueList.get(3) ).doubleValue(), 0.0001);
	
    }    

    /**
     *  Tests to make sure that a simple file can be read and multiple
     *  timings can be extracted.  Also, checks to make sure that the 
     *  zero valued timing tests are handled properly.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public void testReadMultipleTestTimingsAndMultipleZeroTimingsFromFile()
    {

	String fileName = "src/diatoms/reduce/multipleAndMultipleZeroTimings";

	Double expectedThirdAndFourth = new Double(.003);
	Double expectedSecondToLast = new Double(.103);
	Double expectedLast = new Double(1111.003);
	Double expectedFromZero = new Double(.0003);

	// i think that the code under test is right, but test is wrong
	// too tired to keep working

	HashMap testTimings = Util.readTestTimings(fileName);
	assertEquals(6, testTimings.size());
	
	System.out.println("timings: " + testTimings);

	// extract the set of values, turn it into an array, and 
	// turn that into an ArrayList for comparison
	Collection valueSet = testTimings.values();
	ArrayList valueList = new ArrayList(valueSet);
	
	assertTrue(valueList.get(0) instanceof Double);
	
	// sort so that it is easy to determine which values
	// are inside of the valueList in a pre-defined order
	Collections.sort(valueList);

	// this will not work because the order is different ; I think
	// that it is acceptable for the order to change from the
	// input in the file to the output that is returned by the
	// method under test

	assertEquals(expectedFromZero.doubleValue(), 
		     ( (Double) valueList.get(0) ).doubleValue(), .0001);

	assertEquals(expectedFromZero.doubleValue(), 
		     ( (Double) valueList.get(1) ).doubleValue(), .0001);

	assertEquals(expectedThirdAndFourth.doubleValue(), 
		     ( (Double) valueList.get(2) ).doubleValue(), 0);

	assertEquals(expectedThirdAndFourth.doubleValue(), 
		     ( (Double) valueList.get(3) ).doubleValue(), 0);

	assertEquals(expectedSecondToLast.doubleValue(), 
		     ( (Double) valueList.get(4) ).doubleValue(), 0.0001);

	assertEquals(expectedLast.doubleValue(), 
		     ( (Double) valueList.get(5) ).doubleValue(), 0.0001);
	
	// extract the min value from the listing ; it should be 
	// the smallest one that is equal to the ones that were 
	// listed as 0.0
	Double smallestFromList = (Double) Collections.min(valueList);
	
	assertEquals(expectedFromZero.doubleValue(), 
		     smallestFromList.doubleValue(), .001);

    }    

    /**
     *  There was a problem with the HotSpot JVM crashing when the
     *  test ran a large example that had zero time tests in it for
     *  which the time was listed as "0 sec".  This test should pass
     *  since the parser is now fixed.
     *  
     *  @author Gregory M. Kapfhammer 10/12/2005
     */
    public void testReadFromOtherBigFile()
    {

	String fileName = "src/diatoms/reduce/multipleFromTestSuite";

	HashMap testTimings = Util.readTestTimings(fileName);
	
    }

    /**
     *  Tests to see if the sets can be created for a single
     *  test.
     *  
     *  @author Gregory M. Kapfhammer 12/1/2005
     */
    public void testSetOfSetsCreatorSingleTest()
    {

	// {{testThree, testOne, testFour}, {testFour, testOne, testFive}, {testFour, testOne, testTwo}}

	// public Set generateSetOfSets(String setsText, HashMap testNames)

	String sets = "{{testTwo, testOne, testFive}}";

	ArrayList finalResult = Util.generateTestNames(sets);
	assertEquals(1, finalResult.size());

    }

    /**
     *  Tests to see if the sets can be created for a single
     *  test.
     *  
     *  @author Gregory M. Kapfhammer 12/1/2005
     */
    public void testSetOfSetsCreatorThreeTests()
    {

	// public Set generateSetOfSets(String setsText, HashMap testNames)

	String sets = "{{testThree, testOne, testFour}, {testFour, testOne, testFive}, {testFour, testOne, testTwo}}";

	ArrayList finalResult = Util.generateTestNames(sets);
	assertEquals(3, finalResult.size());

	Iterator outerIterator = finalResult.iterator();
	while( outerIterator.hasNext() )
	    {

		boolean veryFirst = true;

		ArrayList innerList = 
		    (ArrayList)outerIterator.next();

		// could have some fancy assertions here, print
		// statements seem to indicate it works

	    }

	String expectedList = "[[testThree, testOne, testFour], [testFour, testOne, testFive], [testFour, testOne, testTwo]]";

	assertEquals(expectedList, finalResult.toString());

    }

}
