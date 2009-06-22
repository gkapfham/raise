/*---------------------------------------------------------------------
 *  File: $Id: SingleTest.java,v 1.9 2007/03/06 20:25:23 gkapfham Exp $   
 *  Version:  $Revision: 1.9 $
 *
 *  Project: DIATOMS, Database drIven Application Testing tOol ModuleS
 *
 *--------------------------------------------------------------------*/

package raise.reduce;

import java.io.Serializable;
import java.lang.Cloneable;

/**
 *  This class represents a single test inside of a test suite.  It
 *  is used to provide a layer of abstraction from all of the different
 *  test suite and automation frameworks.
 *
 *  @author Gregory M. Kapfhammer 9/17/2005
 */

public class SingleTest implements Cloneable, Serializable
{

    /** This is the default cost for all SingleTests */
    private static final double DEFAULT_COST = 1.0;

    /** This is the default purchase price for the SingleTests; note
     that this would mean that a test was not selected at all. */
    private static final double DEFAULT_PRICE = 0.0;

    /** The default extra cost ; normally we would not have an extra
	cost.  We only have to include this when we derive a new test
	suite from an input test suite that is supposed to exhibit 
	worst-case performance */
    private static final double DEFAULT_EXTRA_COST = 0.0;

    /** The name of the test */
    private String name;

    /** The index of the test */
    private int index;

    /** The purchase price for this SingleTest */
    private double purchasePrice;

    /** The cost for running this SingleTest ; this could correspond,
	in practice, to the time or space overhead required to 
	execute an individual test case */
    private double cost;

    /** The extra cost that is associated with running a SingleTest if
	it is the LAST test case in the worst-case test suite that was
	derived from any input test suite */
    private double extraCost;

    /** The desired effectiveness; mostly just use to build up the 
	correct string when it is sent along to Mathematica */
    private double desiredEffectiveness;

    /**
     *  Construct a default instance.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public SingleTest()
    {

	name = "Test";
	index = 0;

	// note that we assume that the cost is going 
	// to be the default
	cost = DEFAULT_COST;
	extraCost = DEFAULT_EXTRA_COST;
	purchasePrice = DEFAULT_PRICE;

    }

    /**
     *  Construct an instance with provide parameters.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public SingleTest(String name, int index)
    {

	this.name = name;
	this.index = index;

	// note that we assume that the cost is going 
	// to be the default
	cost = DEFAULT_COST;
	extraCost = DEFAULT_EXTRA_COST;
	purchasePrice = DEFAULT_PRICE;

    }

    /**
     *  Construct an instance with provide parameters.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public SingleTest(String name, int index, double cost)
    {

	this.name = name;
	this.index = index;
	this.cost = cost;

    }    

    /**
     *  Set the name.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public void setName(String newName)
    {

	name = newName;

    }

    /**
     *  Return the name.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public String getName()
    {

	return name;

    }

    /**
     *  Set the index.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public void setIndex(int newIndex)
    {

	index = newIndex;

    }

    /**
     *  Return the index.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public int getIndex()
    {

	return index;

    }    

    /**
     *  Returns the cost.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public double getCost()
    {

	return cost + extraCost;

    }

    /**
     *  Sets the cost.
     *  
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public void setCost(double cost)
    {

	this.cost = cost;

    }

    /**
     *  Sets the extra cost.
     *  
     *  @author Gregory M. Kapfhammer 9/24/2005
     */
    public void setExtraCost(double extra)
    {

	this.extraCost = extra;

    }

    /**
     *  Gets the extra cost.
     *  
     *  @author Gregory M. Kapfhammer 9/24/2005
     */
    public double getExtraCost()
    {

	return extraCost;

    }    

    /**
     *  Sets the desired effectiveness.  This is useful when we are 
     *  building up the constraint system that we must solve.
     *  
     *  @author Gregory M. Kapfhammer 9/24/2005
     */
    public void setDesiredEffectiveness(double effective)
    {


	desiredEffectiveness = effective;

    }

    /**
     *  Returns the desired effectiveness.
     *  
     *  @author Gregory M. Kapfhammer 9/24/2005
     */
    public double getDesiredEffectiveness()
    {

	return desiredEffectiveness;

    }

    /**
     *  Purchase the SingleTest for a given cost.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public void purchase(double price)
    {

	purchasePrice = price;

    }

    /**
     *  Returns the purchase price of the SingleTest.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public double getPurchasePrice()
    {

	return purchasePrice;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public String toString()
    {

 	return "SingleTest(" + name + ", " + index + ", " 
 	    + this.getCost() + ", " + purchasePrice + ")";

//	return "SingleTest(" + name + ", " + index + ")";

    }    

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public Object clone()
    {

		SingleTest clone = new SingleTest();

		// name of the SingleTest
		clone.setName(this.getName());
	
		// index of the SingleTest
		clone.setIndex(this.getIndex());

		// purchase price of the SingleTest
		clone.purchase(this.getPurchasePrice());

		// cost for the SingleTest
		clone.setCost(this.getCost());

		return clone;

    }

}
