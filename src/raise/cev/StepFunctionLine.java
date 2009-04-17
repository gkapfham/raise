package raise.cev;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.Iterator;
import java.util.LinkedHashSet;

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
	
	boolean drawArea = false;
	
	Color color;
	
	Graphics2D g2d;
	
	float lineWidth;
	float sX,sY;
	
	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public StepFunctionLine(SetCover cover, int[] order, Color color, float width, int boxWidth, int boxHeight, int startX, int startY)
	{	
		sX = startX;
		sY = startY;

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
		
		System.out.println(numTests + ", "+execTime);
		
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
		
		if (drawArea)
			drawAreaBelow();

	}
	
	/**
	 * Draw the area below the line.
	 */
	public void drawAreaBelow()
	{
		GeneralPath area = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x.length); 
		area.moveTo(x[0], y[0]);
		for (int i = 1; i < x.length-1; i++) {
	        area.lineTo(x[i+1],y[i]);  
	        area.lineTo(x[i+1],y[i+1]);			
		}
		area.lineTo(x[x.length - 1], y[0]);
		area.closePath();

		Composite originalComposite = g2d.getComposite();
	    g2d.setComposite(makeComposite(.6F));
	    g2d.setPaint(color);
	    g2d.fill(area);
	    g2d.setComposite(originalComposite);
	    
	}
	private AlphaComposite makeComposite(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

	/**
	 * To draw the line in the highlight color or to draw some kind of glow around it.
	 */
	public void drawHighlight()
	{
		
	}

	/**
	 * This will return true if x and y denote a point close to the line.
	 */
	/*public boolean contains(int xClick, int yClick)
	{
		int proximity = (int)(lineWidth/2) + 3; //3 is arbitrary closeness
		
		for (int i = 0; i <= proximity; i++){
			for (int j = 0; j <= proximity; j++){
				if (xClick+i < x.length && yClick+j < y.length){
					if (containsSearch(xClick + i, yClick + j) )
						return true;
				}
				if (xClick-i > 0 && yClick-j  >= 0){
					if (containsSearch(xClick - i, yClick - j) )
						return true;
				}
				if (xClick+i < x.length && yClick-j >= 0){
					if (containsSearch(xClick + i, yClick - j) )
						return true;
				}
				if (xClick-i > 0 && yClick + j < y.length){
					if (containsSearch(xClick - i, yClick + j) )
						return true;
				}
			}
		}
		return false;
	}*/
	
	/*
	 * NOTE:  MAKE A FUNCTION THAT DOES THIS
	 * a popup rectangle that displays the CE.  It will really just be a method for the StepFunctionLine class that shows creates a rectangle and draws the ce on it and puts it by the mouse or something.  Just a couple of lines.
	 */
	
	
	
	public boolean contains(int posX, int posY){
		int p = 5; // proximity

		for(int i=0; i < x.length-1; i++){
			if (posX >= x[i]-p/2 && posX <= x[i+1] +p/2)
				if (posY >= y[i]-p && posY <= y[i] +p)
					return true;

				else if (posX >= x[i+1]-p && posX <= x[i+1] + p)
					if(posY >= y[i+1]-p/2 && posY <= y[i] + p/2)
						return true;
		}
		return false;
	}
	/**
	 * This will return true if x and y denote a point on the line.
	 */
	public boolean containsSearch(int posX, int posY){
		int arrLength = x.length;
		//see if click is in arrays, ie a line exists at that height
		int foundYDex = binarySearchOfReverseSorted(y,posY,0,arrLength);
		int foundXDex = binarySearchOfSorted(x,posX,0,arrLength);
		
		//found the point
		if ((foundYDex >= 0 || foundXDex >= 0) && foundYDex == foundXDex)
			return true;
		//check the horizontal 
		else if ( foundYDex >= 0){
			//look to left
			int dex = foundYDex;
			if (posX <= x[dex]){
				while (--dex >= 0 && dex < arrLength && y[dex] == posY ){ //look to the left
					if (x[dex] <= posX){
						System.out.println("HERE1");
						return true;
					}
				}
			}else{
				//look to the right
				while (++dex < arrLength && dex >= 0 && y[dex] == posY){ // look to the right
					if (x[dex] >= posX){
						System.out.println("HERE2");
						return true;
					}
				}
			}
		}
		//	check the vertical
		else if ( foundXDex >= 0){
			//look above
			int dex = foundXDex;
			if (posY <= y[dex]){
				while (++dex >= 0 && dex < arrLength && x[dex] == posX ){ //look to the left
					if (y[dex] <= posY){
						System.out.println("HERE3");
						return true;
					}
				}
			}else{
				//look below
				while (--dex < arrLength && dex >= 0  && x[dex] == posX){ // look to the right
					if (y[dex] >= posY){
						System.out.println("HERE4");
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private static int binarySearchOfReverseSorted(int[] arr, int searchValue, int left,
			int right) {
		if (right <= left) {
			return -1;
		}
		int mid = (left + right) >>> 1;
		if (searchValue < arr[mid]) {
			return binarySearchOfReverseSorted(arr, searchValue, mid + 1, right);
		} else if (searchValue > arr[mid]) {
			return binarySearchOfReverseSorted(arr, searchValue, left, mid - 1);
		} else {
			return mid;
		}
	}
	
	private static int binarySearchOfSorted(int[] arr, int searchValue, int left,
			int right) {
		if (right <= left) {
			return -1;
		}
		int mid = (left + right) >>> 1;
		if (searchValue > arr[mid]) {
			return binarySearchOfSorted(arr, searchValue, mid + 1, right);
		} else if (searchValue < arr[mid]) {
			return binarySearchOfSorted(arr, searchValue, left, mid - 1);
		} else {
			return mid;
		}
	}
}
