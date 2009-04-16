package raise.cev;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Arrays;

import raise.reduce.RequirementSubset;
import raise.reduce.SetCover;
import raise.reduce.SingleTest;
import raise.reduce.SingleTestSubset;

import java.awt.Graphics2D;

public class StepFunctionLine {
	
	int[] x;
	int[] y;
	
	float ce;
	int numTests;
	double executionTime;
	
	Color color;
	
	Graphics2D g2d;
	
	float lineWidth;
	
	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public StepFunctionLine(SetCover cover, int[] order, Color color, float width, int boxWidth, int boxHeight, int startX, int startY)
	{		
		int height = 0;
		int sum = 0;
		int totalTime = 0;
		int[] covered = new int[cover.getRequirementSubsetUniverse().size()];
		
		x = new int[cover.getTestSubsets().size()+1];
		y = new int[cover.getTestSubsets().size()+1];
		
		x[0] = startX;
		y[0] = startY;
		
		LinkedHashSet<SingleTest> tempTestList = new LinkedHashSet();
		
		Iterator stsIt = cover.getTestSubsets().iterator();
		
		while(stsIt.hasNext())
			tempTestList.add(((SingleTestSubset)stsIt.next()).getTest());
		
		double execTime = SetCover.getExecutionTimeSingleTestList(tempTestList); 
		int numReqs = cover.getRequirementSubsetUniverse().size();
		
		for(int i = 0; i < order.length; i++)
		{
			Iterator testSubsetsIterator = cover.getTestSubsets().iterator();
			
			while(testSubsetsIterator.hasNext())
			{
				SingleTestSubset thisTestSubset = 
					((SingleTestSubset)testSubsetsIterator.next());
					
				// if the test is to be executed next
				if( (thisTestSubset.getTest()).getIndex() == order[i])
				{
					// Move forward and sum
					totalTime += thisTestSubset.getTest().getCost();
					
					x[i+1] = startX + (int) ((((float)totalTime - 0)/((float)execTime - 0))*boxWidth);
										
					// Update height.  
					Iterator<RequirementSubset> requirementsIterator = 
						thisTestSubset.getRequirementSubsetSet().iterator();
						
					while(requirementsIterator.hasNext())
					{
						int reqIndex = 	((RequirementSubset) 
							requirementsIterator.next()).getIndex();
						
						if( covered[reqIndex] == 0 )
						{
							height+=1;
							covered[reqIndex] = 1;
						}
					}
					y[i+1] = startY - (int) ((((float)height - 0 )/((float)numReqs - 0))*boxHeight);
					break;
				}
			}	
		}
		
		ce = ((float)sum)/((float)height*(float)totalTime);
		
		this.numTests = x.length-1;
		
		this.color = color;
		this.lineWidth = width;
		
		this.executionTime = SetCover.getExecutionTimeSingleTestSubsetList(cover.getTestSubsets());
		
	}

	public double getExecutionTime()
	{
		return executionTime;
	}
	
	public int getNumTests()
	{
		return numTests;
	}
	
	public float getCE()
	{
		return ce;
	}
	
	/**
	 * Set the graphics2d object. 
	 * @param g2d
	 */
	public void setGraphics(Graphics2D g2d)
	{
		this.g2d = g2d;
	}
	
	/**
	 * This will draw the step function. 
	 */
	public void drawStep()
	{
		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(lineWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));	
		
		for(int i = 0; i < x.length-1; i++)
		{
			g2d.drawLine(x[i],y[i],x[i+1],y[i]);
			g2d.drawLine(x[i+1],y[i],x[i+1],y[i+1]);
		}
	}
	
	/**
	 * Draw the area below the line.
	 */
	public void drawAreaBelow()
	{
		
	}
		
	/**
	 * To draw the line in the highlight color or to draw some kind of glow around it.
	 */
	public void drawHighlight()
	{
		
	}
	

	/**
	 * This will return true if x and y denote a point on the line.
	 */
	public boolean contains(int xClick, int yClick)
	{
		//see if click is in arrays, ie a line exists at that height
		int foundYDex = Arrays.binarySearch(y, yClick);
		int foundXDex = Arrays.binarySearch(x, xClick);
		
		//found the point
		if (x[foundYDex] == xClick  || y[foundXDex] == yClick)
			return true;		
		//check the horizontal if clickY found
		else if ( foundYDex >= 0){
			//look to left
			int dex = foundYDex;
			while (y[--dex] == yClick ){ //look to the left
				if (x[dex] <= xClick)
					return true;
			}
			//look to the right
			dex = foundYDex;
			while (y[++dex] == yClick){ // look to the right
				if (x[dex] >= xClick)
					return true;
			}
		}
		//	check the vertical
		else if ( foundXDex >= 0){
			//look below
			int dex = foundXDex;
			while (x[--dex] == xClick ){ //look to the left
				if (y[dex] <= yClick)
					return true;
			}
			//look above
			dex = foundXDex;
			while (x[++dex] == xClick){ // look to the right
				if (y[dex] >= yClick)
					return true;
			}
		}
		return false;
	}

}
