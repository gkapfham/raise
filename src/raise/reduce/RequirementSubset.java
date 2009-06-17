/*---------------------------------------------------------------------
 *  File: $Id: RequirementSubset.java,v 1.10 2007/03/06 19:50:10 gkapfham Exp $   
 *  Version:  $Revision: 1.10 $
 *
 *  Project: DIATOMS, Database drIven Application Testing tOol ModuleS
 *
 *--------------------------------------------------------------------*/

package raise.reduce;

import java.util.Set;
import java.util.Iterator;
import java.util.LinkedHashSet;

import java.io.Serializable;

import java.lang.Cloneable;

/**
 *  This class represents the RequirementSubset.
 *
 *  @author Gregory M. Kapfhammer 9/17/2005
 */

public class RequirementSubset implements Cloneable, Serializable, Comparable<RequirementSubset>
{

    /** The name of the requirement subset; this is just the name
     of the requirement itself (i.e., a DUA, DIA, DB entity, ...) */
    private String name;

    /** The index of this requirement */
    private int index;

    /** The set of the SingleTests that cover this requirement */
    private LinkedHashSet coveringTestSet;

    /** The frequency of this requirement.  This is the number 
	of tests that actually cover this same requirement */
    private int frequency;

    /**
     *  Default constructor.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public RequirementSubset()
    {

	name = "RequirementSubset";
	index = 0;
	coveringTestSet = new LinkedHashSet();
	frequency = 0;

    }

    /**
     *  Constructor for the first two parameters.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public RequirementSubset(String name, int index)
    {

	this.name = name;
	this.index = index;
	coveringTestSet = new LinkedHashSet();
	frequency = 0;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public void setName(String name)
    {

	this.name = name;

    }

    /**
     *  Return the name of this RequirementSubset, i.e., the name of 
     *  the requirement that all of these tests covered.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public String getName()
    {

	return name;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public void setIndex(int index)
    {

	this.index = index;

    }

    /**
     *  Return the index of this RequirementSubset.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public int getIndex()
    {

	return index;

    }

    /**
     *  Put a new SingleTest into the set of tests that cover this
     *  given TestRequirement.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public void addCoveringTest(SingleTest test)
    {

	coveringTestSet.add(test);

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public void setCoveringTestSet(LinkedHashSet coveringSet)
    {

	coveringTestSet = coveringSet;

    }

    /**
     *  Clear the set of covering test cases.
     *  
     *  @author Gregory M. Kapfhammer 9/24/2005
     */
    public void clearCoveringTestSet()
    {

	coveringTestSet = new LinkedHashSet();

    }

	/**
    *  Remove a covering test
	 * 
    *  @author Adam M. Smith 07/17/2007
	 */
	public void removeCoveringTest(SingleTest coveringTest) {

		coveringTestSet.remove(coveringTest);
	}

    /**
     *  Return the list of the covering tests for this requirement. 
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public LinkedHashSet getCoveringTests()
    {

	return coveringTestSet;

    }    

    /**
     *  Does this RequirementSubset contain a given test?
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public boolean containsSingleTest(SingleTest test)
    {

	return coveringTestSet.contains(test);

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public void setRedundancyFactor(int freq)
    {

	frequency = freq;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public void incrementRedundancyFactor()
    {

	frequency++;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public int getRedundancyFactor()
    {

		return frequency;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public Object clone()
    {

		RequirementSubset clone = new RequirementSubset();

		// clone all of the easy instance variables
		clone.setName(this.getName());
		clone.setIndex(this.getIndex());
		clone.setRedundancyFactor(this.getRedundancyFactor());

		// NOTE: this is a shallow copy and it might be 
		// neccessary to perform a deep copy (i.e., we are 
		// not currently cloning the SingleTests inside of 
		// the LinkedHashSet)

		// use a clone of the coveringTestSet inside of the clone
	// 	clone.
	// 	    setCoveringTestSet( (LinkedHashSet)
	// 				(this.getCoveringTests().clone()) );

		// doing the deep copy
		LinkedHashSet deepSet = new LinkedHashSet();
		Iterator coveringIterator = this.getCoveringTests().iterator();
		while( coveringIterator.hasNext() )
	    {

			SingleTest currentTest = 
				(SingleTest) coveringIterator.next();

			deepSet.add( currentTest.clone() );

	    }

		clone.setCoveringTestSet( deepSet );

		return clone;

    }

    /**
     *  @author Gregory M. Kapfhammer 7/15/2005
     */ 
    public String longToString()
    {

	// do not print out the details about the covering test set
	// because this makes the debugging output very difficult
	// to read when we convert to hitting set instance to a
	// set cover instance

	return "RequirementSubset(" + name + ", " + index + ")" + "," +
	     coveringTestSet.toString() + ")";

    }

    /**
     *  @author Gregory M. Kapfhammer 7/15/2005
     */
    public String toString()
    {

	// do not print out the details about the covering test set
	// because this makes the debugging output very difficult
	// to read when we convert to hitting set instance to a
	// set cover instance
	
	return "RequirementSubset(" + name + ", " + index + ")"; //"," +
	//return "(" + index + ")";

	// coveringTestSet.toString() + ")";

    }

    /**
     *  Determines if two RequirementSubsets are equal if they have
     *  the same name and index.  This is useful when we are
     *  performing the prioritization of a test suite because we 
     *  can easily remove those requirements that are not any longer 
     *  in need of being covered.
     *  
     *  @author Gregory M. Kapfhammer 12/1/2005
     */
    public boolean equals(Object other)
    {

	// turn the object into an actually RequirementSubset
	RequirementSubset otherRequirementSubset = 
	    (RequirementSubset)other;

	// assume that they are not the same and prove otherwise
	boolean sameName = false;
	
	// they do indeed have the same name and index
	if( otherRequirementSubset.getName().
	    equals(this.getName()) && 
	    otherRequirementSubset.getIndex() == 
	    this.getIndex() )
	    {

		return true;

	    }

	return sameName;

    }
    
    public int compareTo(RequirementSubset in)
    {
    	if(this.index == in.index)
    		return 0;
    	else if(this.index < in.index)
    		return -1;
    	else
    		return 1;
    }

}
