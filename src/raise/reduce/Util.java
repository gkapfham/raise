/*---------------------------------------------------------------------
 *  File: $Id: Util.java,v 1.7 2005/12/04 01:07:23 gkapfham Exp $   
 *  Version:  $Revision: 1.7 $
 *
 *  Project: DIATOMS, Database drIven Application Testing tOol ModuleS
 *
 *--------------------------------------------------------------------*/

package raise.reduce;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.HashMap;

import java.util.regex.*;

import java.text.DecimalFormat;

/**
 *  This class provides additional functionality that is needed during
 *  test suite prioritization and reduction.
 *
 *  @author Gregory M. Kapfhammer 10/4/2005
 */

public class Util
{

    /** Pattern to detect a test case inside of a JUnit/Ant output */
    private static final String TESTCASE_PATTERN = "Testcase:(.)*";

    /** Pattern to detect a decimal or a zero.  Note that we have to have
     a zero here because of the fact that some tests can take "0 sec" and
     thus just looking for a decimal is not sufficient. */
    private static final String DECIMAL_PATTERN = 
	"([0-9]*\\.[0-9]*)|0";
    
    /** JUnit requires all test cases to adhere to a specific naming 
     pattern and thus it is safe to */
    private static final String TESTNAME_PATTERN = "test\\w+";

    /** The pattern for the "test suite" that is returned from
	Mathematica when we are reducting and prioritizing randomly */
    //private static final String TESTSUITE_PATTERN = "\\{[(\\w+]+ \\}";

    //private static final String TESTSUITE_PATTERN = "\\{[\\w+]\\}+";

    private static final String TESTSUITE_PATTERN = "\\w+\\}?+";

    /** This is the time of a test case that does not consume any
     amount of execution time according to a millisecond profiler */
    private static final double NO_TIME_TEST = 0.0;

    /** This is the reduction factor.  We want to make the tests that 
     have zero time actually */
    private static final double TEST_REDUCTION_FACTOR = .1;

    /**
     *  This method goes through the output from executing a test 
     *  suite inside of the Ant/JUnit/DBUnit/DIATOMS test suite 
     *  execution infrastructure and harvests the timings from the 
     *  test suite.
     *
     *  Note that this implementation assumes that there is at least
     *  one test case that has non-zero time within it.  This
     *  assumption might not hold for all test suites since some could
     *  all have short running tests.  I think that it will always
     *  hold for the types of tests that we will handle.  
     *
     *  The code could be modified to assign an arbitrary small time
     *  in the circumstance when all of the tests have zero time.
     *
     *  @author Gregory M. Kapfhammer 10/4/2005
     */
    public static HashMap readTestTimings(String fileName)
    {

	// use a linked hash map because it preserves the insertion
	// order and it allows us to store (test name, test time)
	// pairs and return this to the client
	LinkedHashMap testTimingsMap = new LinkedHashMap();

	// the currentText will store the current line that was read
	// in from the file; the fullText will be turned into a String
	// after all of the lines are appended
	String currentText = new String();

	try
	    {

		// read in the file on a buffered, line by line basis
		BufferedReader in = 
		    new BufferedReader( new FileReader( new File(fileName) ) );

		// there are still lines of the file left
		while( (currentText = in.readLine() ) != null )
		    {

			//System.out.println("current: " + currentText);

			// declare these variables so that once we
			// have extract all of the information it
			// can be stored here and then we can just
			// put the (testNameFinal, testCostFinal) 
			// pair inside of the HashMap
			String testNameFinal = null;
			Double testCostFinal = null;

			// create a regular expression pattern and try
			// to match it with a line that actually contains
			// a timing for a test case
			Pattern testCasePattern = 
			    Pattern.compile(TESTCASE_PATTERN);
			Matcher testCaseMatcher =  
			    testCasePattern.matcher(currentText);

			// this is indeed a test case that starts with
			// the correct prefix; we have to keep
			// analyzing it for timing information
			if( testCaseMatcher.find() )
			    {

				// extract the information about the
				// current test case
				String currentTest = 
				    testCaseMatcher.group();

				//System.out.println("yes, found");
				//System.out.println(currentTest);
				
				// create a regular expression pattern
				// and try to match it with the name
				// of the test case; if there is a test
				// case name, then we want to extract it
				Pattern testNamePattern = 
				    Pattern.compile(TESTNAME_PATTERN);
				Matcher testNameMatcher = 
				    testNamePattern.matcher(currentTest);

				// we were able to find the test name
				// and we need to convert it to a String
				// and then persist it so that we can
				// look for the test time overhead
				if( testNameMatcher.find() )
				    {

					testNameFinal = 
					    testNameMatcher.group();
					
					// System.out.println("testName: " + 
// 							   testNameFinal);

				    }

				// create a regular expression pattern
				// and try to match it with a specific
				// number ; if there is a number then
				// we want to extract it
				Pattern decimalPattern = 
				    Pattern.compile(DECIMAL_PATTERN);
				Matcher decimalMatcher = 
				    decimalPattern.matcher(currentTest);

				// we were able to find a decimal and 
				// we need to convert it properly and 
				// place it into the listing 
				if( decimalMatcher.find() )
				    {

					// extract the decimal that 
					// we have found
					String testCostDecimal = 
					    decimalMatcher.group();

// 					System.out.println("decimal");
// 					System.out.println(testCostDecimal);

					// construct a Double out of 
					// this decimal in String form
					testCostFinal = 
					    new Double(testCostDecimal);

				    }
				
				DecimalFormat decimalFormat = 
				    new DecimalFormat();

// 				System.out.println("formatted: " + 
// 						   decimalFormat.
// 						   format(testCostFinal));

				// we have assembled the time overhead
				// of the test and the name of the
				// test and we have to put these two
				// things into the hash table that
				// stores the test timings
				testTimingsMap.put(testNameFinal,
						   testCostFinal);
				

			    }

		    }

	    }

	catch(IOException e)
	    {

		e.printStackTrace();

	    }

	// store all of the test cases that have zero time; we need to
	// modify their times so that the SetCover instance can exist
	// in a proper state
        ArrayList zeroTestNameList = new ArrayList();

	// keep a listing of all of the tests that have a non-zero
	// time; it is from these tests that we must select the
	// minimum and use this as the baseline for the time of all of
	// the zero time test cases
	ArrayList nonZeroTestTimings = new ArrayList();

	// extract an iterator of all of the keys inside of the HashMap
	Iterator testTimingsIterator = testTimingsMap.keySet().iterator();
	while( testTimingsIterator.hasNext() )
	    {

		// extract the next key from the listing
		String currentTestName = (String) testTimingsIterator.next();
		
		// get the value for this current test
		Double currentTestTiming = 
		    (Double) testTimingsMap.get(currentTestName);

// 		System.out.println("Current test name: " + currentTestName);

		// this current test does have a zero time and thus we
		// have to put it into the HashMap of the
		// zeroTestNameList
		if( currentTestTiming.doubleValue() == 0.0 )
		    {

			zeroTestNameList.add(currentTestName);

		    }

		// this current test does not have a zero time and
		// thus we need to keep it around; we want the 
		// minimum of these to use to determine the new
		// value for all of the tests that have zero time
		else
		    {

			nonZeroTestTimings.add(currentTestTiming);

		    }

	    }

// 	System.out.println(testTimingsMap);

	// look in the list of the test timings that are not zero and
	// find the minimum; use this to calculate the new value for
	// the previously zero time test cases
	Double lowestNonZeroMin = 
	    (Double) Collections.min(nonZeroTestTimings);
	
// 	System.out.println("LNZM: " + lowestNonZeroMin);
	
	// create a new zero test time that is the previous minimum
	// time that is reduced by the pre-defined reduction factor
	double newZeroTime = 
	    lowestNonZeroMin.doubleValue() * TEST_REDUCTION_FACTOR;
	Double newZeroTimeFinal = new Double(newZeroTime);

// 	System.out.println("nZTF: " + newZeroTimeFinal);

	// extract an iterator of the zeroTestList and then go through
	// and add this many of the newZeroTimes into the testTimingsMap
	Iterator zeroIterator = zeroTestNameList.iterator();
	while( zeroIterator.hasNext() )
	    {

		// extract the name of the test that previously
		// had a zero time 
		String currentZeroTestName = 
		    (String) zeroIterator.next();

		DecimalFormat decimalFormat = 
		    new DecimalFormat("####.000000");

// 		System.out.println("prev zero formatted: " + 
// 				   decimalFormat.
// 				   format(newZeroTimeFinal));

		// we need to rehash this test name so that it
		// contains a new timing that we calculated to fall
		// below the previous minimum in a pre-defined fashion
		testTimingsMap.put(currentZeroTestName, 
				    newZeroTimeFinal);
		

	    }

	return testTimingsMap;

    }

    /**
     *  Produces a "List of List" of test suites and test case names
     *  from the String that is returned from Mathematica.
     *  
     *  @author Gregory M. Kapfhammer 12/3/2005
     */
    public static ArrayList generateTestNames(String setsText)
    {

	// this is going to be an ArrayList of ArrayLists
	ArrayList testSuitesList = new ArrayList();

	// create a regular expression pattern and try
	// to match it with a line that actually contains
	// a timing for a test case
	Pattern testSuitePattern = 
	    Pattern.compile(TESTSUITE_PATTERN);
	Matcher testSuiteMatcher =  
	    testSuitePattern.matcher(setsText);

	// we are going to populate this with each of the 
	// individual test suites that were inside the 
	// outermost "{" and "}"
	ArrayList internalSuiteList = new ArrayList();

	int i = 1;

	// we found a test suite and now we have to pick apart
	// the individual test names that are inside of it
	while( testSuiteMatcher.find() )
	    {
		
		// extract the information about the
		// current test suite
		String currentTestCase = 
		    testSuiteMatcher.group();

		//System.out.println("yes, found");
		//System.out.println(i + " " + currentTestCase);
		
		// we have found a test case that is at the 
		// end of a suite and thus we need to create a 
		// new internalSuiteList and use it after 
		// adding in the current internalSuiteList
		if( currentTestCase.endsWith("}") )
		    {

			// replace the "}" that is at the end and 
			// then make sure that there is no extra 
			// white space inside of the entire string
			internalSuiteList.
			    add(currentTestCase.replace('}', ' ').trim());

			// we want to place this internalSuiteList
			// into the outer test suite listing because
			// we are done with it and need to move to
			// the next test suite in the String
			testSuitesList.add(internalSuiteList);
			
			// use a clone() because we do not want to
			// have the Java references problem
			internalSuiteList = 
			    (ArrayList)internalSuiteList.clone();
		
			// clear out the clone() so that we do not
			// accumulate the state from the previous
			// iterations of the while loop
			internalSuiteList.clear();
			
			//System.out.println(i + " new test suite");
			i++;
				
		    }

		// this is one of the internal test cases and so we 
		// just need to add it to the internal listing
		else
		    {

			internalSuiteList.add(currentTestCase);

		    }
		
	     }

	//System.out.println("Test suite?" + testSuitesList);
	return testSuitesList;

    }

}
