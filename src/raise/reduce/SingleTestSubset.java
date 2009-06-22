/*---------------------------------------------------------------------
 *  File: $Id: SingleTestSubset.java,v 1.12 2007/03/08 02:25:06 gkapfham Exp $   
 *  Version:  $Revision: 1.12 $
 *
 *  Project: DIATOMS, Database drIven Application Testing tOol ModuleS
 *
 *--------------------------------------------------------------------*/

package raise.reduce;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.io.Serializable;
/**
 *  The SingleTestSubset is a SingleTest and then the RequirementSubsets
 *  to which it belongs.  Intuitively, this corresponds to the test case
 *  and the test requirements that it is able to cover.
 *
 *  @author Gregory M. Kapfhammer 9/17/2005
 */

public class SingleTestSubset implements Cloneable, Serializable
{

    /**
	 * For Serializable
	 */
	private static final long serialVersionUID = 1L;

	/** The test that is part of the SingleTestSubset */
    private SingleTest test;

    /** The set of RequirementSubsets (in this case we are only really
	interested in the names of the sets and not their contents, but
	we are going to keep the entire contents around) */
    private LinkedHashSet<RequirementSubset> requirementSubsetsSet;

    /** Maximum redundancy for a test requirement */

    /**
     *  Default constructor.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public SingleTestSubset(SingleTest test)
    {

	this.test = test;
	requirementSubsetsSet = new LinkedHashSet<RequirementSubset>();

    }

    /**
     *  Adds a RequirementSubset to the current set.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public void addRequirementSubset(RequirementSubset rs)
    {

	requirementSubsetsSet.add(rs);

    }


	/**
    * Removes a RequirementSubset object
    *
	 */
	 
	 public void removeRequirementSubset(RequirementSubset reqToRemove){
	 
	 	requirementSubsetsSet.remove(reqToRemove);
	 
	 }
	 
    /**
     *  The first sentence is a description of the method.  The remaining
     *  sentences are more detailed information relating to the description.
     *  
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public void clearRequirementSubset()
    {

	requirementSubsetsSet.
	    removeAll(requirementSubsetsSet);  //= new LinkedHashSet();

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public void setRequirementSubsetSet(LinkedHashSet<RequirementSubset> set)
    {

	requirementSubsetsSet = set;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public SingleTest getTest()
    {

	return test;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public void setTest(SingleTest test)
    {

	this.test = test;

    }

    /**
     *  Return the Set of RequirementSubsets.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public LinkedHashSet<RequirementSubset> getRequirementSubsetSet()
    {

	return requirementSubsetsSet;

    }

    /**
     *  Determines whether the difference of this current
     *  SingleTestSubset and the provided cover is equal to the empty
     *  set.  If this method does return true then this indicates that
     *  this is a set that does not need to be considered further and
     *  can be removed from the computation.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public boolean containsElements(Set cover)
    {

	// clone the set and then perform a set different operation
	// so that we can determine how many items are left inside
	// of this specific SingleTestSubset
	Set requirementSubsetsSetClone = 
	    (Set)requirementSubsetsSet.clone();
	boolean found = requirementSubsetsSetClone.removeAll(cover);

// 	System.out.println("found in cost? = " + found);

	// we have found a SingleTestSubset for which all of the
	// test requirements have already been covered and thus 
	// we can return false; this is a sign to the calling method
	// that it can stop considering this SingleTestSubset during 
	// the current and all future iterations of the algorithm
	if( requirementSubsetsSetClone.size() == 0 )
	    {

		return false;

	    }

	// there are still some elements left inside of this set and 
	// thus we should keep using it (the client program might, for
	// exaample, then consider calculating the cost of this current
	// instance of SingleTestSubset)
	return true;

    }

    /**
     *  Calculate the cost for the given SingleTestSubset.
     *  Part of the cost calculation requires that we know the
     *  cost associated with executing the SingleTest that 
     *  will cover all of the test requirements inside of this
     *  SingleTestSubset.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public double costEffectiveness(Set cover)
    {

	// create a clone for this requirementSubset because we 
	// are going to perform a set difference and we do not 
	// want to actually modify the underlying test case
	Set requirementSubsetsSetClone = 
	    (Set)requirementSubsetsSet.clone();

	// perform the set difference on the clone
	boolean found = requirementSubsetsSetClone.removeAll(cover);

	// debugging output for now
// 	System.out.println("found in cost? = " + found);
// 	System.out.println("test cost = " + test.getCost());
// 	System.out.println("rs size = " + requirementSubsetsSetClone.size());
// 	System.out.println( (double) test.getCost() / 
// 	    (double) requirementSubsetsSetClone.size() );

	// calculate the cost; note that we are assuming that each
	// test case has a defined cost (if no cost is set then it is
	// required that we use the default cost, which is just one)
	// note that we also use in the denominator the size of the 
	// differenced set which does not factor in the requirements
	// that have already been covered
 	double finalCost = (double) test.getCost() / 
	    (double) requirementSubsetsSetClone.size();

 	return finalCost;

	// no longer just looking at the size(), which is really a
	// benefit and not a cost ; code is currently written to be a
	// cost

//	return requirementSubsetsSetClone.size();

    }

	public double coverageValue(Set cover)
    {

	// create a clone for this requirementSubset because we 
	// are going to perform a set difference and we do not 
	// want to actually modify the underlying test case
	Set requirementSubsetsSetClone = 
	    (Set)requirementSubsetsSet.clone();

	// perform the set difference on the clone
	boolean found = requirementSubsetsSetClone.removeAll(cover);

	// debugging output for now
// 	System.out.println("found in cost? = " + found);
// 	System.out.println("test cost = " + test.getCost());
// 	System.out.println("rs size = " + requirementSubsetsSetClone.size());
// 	System.out.println( (double) test.getCost() / 
// 	    (double) requirementSubsetsSetClone.size() );

	// calculate the cost; note that we are assuming that each
	// test case has a defined cost (if no cost is set then it is
	// required that we use the default cost, which is just one)
	// note that we also use in the denominator the size of the 
	// differenced set which does not factor in the requirements
	// that have already been covered
 	double finalCost = (double) requirementSubsetsSetClone.size();

 	return finalCost;

	// no longer just looking at the size(), which is really a
	// benefit and not a cost ; code is currently written to be a
	// cost

//	return requirementSubsetsSetClone.size();

    }


    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public Object clone()
    {

	SingleTestSubset clone = 
	    new SingleTestSubset( (SingleTest)this.getTest().clone() );

	// note that this code might need to be changed in the future
	// this just does a shallow copy, not enough?

// 	clone.
// 	    setRequirementSubsetSet( (LinkedHashSet) 
// 				     this.getRequirementSubsetSet().
// 				     clone() );

	LinkedHashSet newRSS = new LinkedHashSet();

	Iterator reqIterator = this.getRequirementSubsetSet().iterator();
	while( reqIterator.hasNext() )
	    {

		RequirementSubset requireSubset = 
		    (RequirementSubset) reqIterator.next();

		newRSS.add(requireSubset.clone());

	    }

	clone.setRequirementSubsetSet( newRSS );

	return clone;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public String toString()
    {

//    	return "SingleTestSubset(" + test + ", " + 
//    	    requirementSubsetsSet + ");"

    	return "SingleTestSubset(" + test + ", " + 
    	    requirementSubsetsSet.size() + ")";

//	return "SingleTestSubset(" + test; //+ //", " + 
	//requirementSubsetsSet + ")";

    }

}
