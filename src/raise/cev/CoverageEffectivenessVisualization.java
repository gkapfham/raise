package raise.cev;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import raise.reduce.SetCover;

import java.awt.Font;

import java.awt.RenderingHints;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *	This class implements a visualization of many prioritizations of a test suite. 
 *
 */

public class CoverageEffectivenessVisualization extends JPanel implements MouseListener, MouseMotionListener, ChangeListener {
	
	private static String matrixFile; 
	private static String timeFile; 
	
	private final int windowWidth = 971; //971
	private final int windowHeight = 600;
	private final int infoWidth = 290; //270
	private final int axesWidth = 100;
	private final int edgeBuffer = 30;
	private final int tickLength = 5;
	private final int numTicks = 5;
	
	private final int randLineWidth = 1;
	private final int techniqueLineWidth = 2;
	private final int MAX_RAND = 30;
	private final int MIN_RAND = 0;
	
	
	private final int originalGridStartY = 150;
	private final int originalGridStartX = 115;
	private final int originalLabelsOffsetY = 20;
	private final int originalLabelsOffsetX = 60;
	
	private final String[] oTechniques = {"Original","Reverse"};
	
	private final int gridStartY = 270;
	private final int gridStartX = 45;
	private final int gridSpacingY = 40;
	private final int gridSpacingX = 70;
	private final int gcmLabelsOffsetX = 8;
	private final int gcmLabelsOffsetY = 5;
	private final int techniqueLabelsOffsetX = 40;
	private final int techniqueLabelsOffsetY = 25;
	
	private final int buttonInsetX = 10;
	private final int buttonInsetY = 10;
	
	private final String[] techniques = {"GRD","2OPT","DGR", "HGS"};
	private final String[] gcm = {" cost","coverage", "   ratio"};
	private final Color[] colors = {Color.BLUE,Color.cyan,Color.green,Color.ORANGE};
	
	private final int scrollBarStartX = 10;
	private final int scrollBarStartY = 500;
	private final int scrollBarLength = 260;
		
	private static final long serialVersionUID = 1L;

	private int numRand;
	private static float runningAveRandCE = 0;
	private static float aveRandCE = 0;
	private static float runningRandSTDev = 0;
	private static float randSTDev = 0;
	private static int numRandGenerated = 0;
	private static float runningSumOfSquares = 0;
	
	double execTime;
	int numTests;
	int numReqs;
	
	int[] order;
	SetCover sc;
	
	// For the size of the window
    private Dimension size;

    // Required for J2D
    Graphics2D g2d;
    
    ArrayList<StepFunctionLine> lines;
    ArrayList<StepFunctionLine> randLines;
    
    boolean[] show;

    JSlider slider;
    
    JPanel sliderPanel; 
    
    int scrollBoxX;
    int scrollBoxY;
    
    boolean scrolling;
    
    public CoverageEffectivenessVisualization()
    {
    	scrolling=false;
    	
    	// A mouse listener 
    	addMouseListener(this);
    	addMouseMotionListener(this);

    	//	Instantiate the size.
    	size = new Dimension();

    	// Set the size based on the background image.
	    size.width = windowWidth;
	    size.height = windowHeight;
	    
	    setPreferredSize(size);
	
	    // initialize the program
	    initState();
    }

    /*
     * 	This method sets up the starting variables for the program.
     * @author Adam M. Smith
     */
    public void initState()
    {
    	scrollBoxX = scrollBarStartX;
    	scrollBoxY = scrollBarStartY;
    	
    	slider = new JSlider(JSlider.HORIZONTAL,
                MIN_RAND, MAX_RAND, MIN_RAND);
    	
    	slider.addChangeListener(this);
    	slider.setMajorTickSpacing(10);
    	slider.setMinorTickSpacing(1);
    	slider.setPaintLabels(true);
    	
    	//add(slider);
    	    	    	
       	show = new boolean[14];
    	
    	show[0] = true;
    	
    	for(int i = 1; i<14; i++)
    		show[i]=false;
    	
    	lines = new ArrayList<StepFunctionLine>();
    	randLines = new ArrayList<StepFunctionLine>();
    	// Default to 20 and then set later via user interaction.
    	
    	numRand = 0;
    	
    	
    	sc = new SetCover();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	
    	numTests = sc.getTestSubsets().size();
    	numReqs = sc.getRequirementSubsetUniverse().size();
    	execTime = SetCover.getExecutionTimeSingleTestSubsetList(sc.getTestSubsets());

    	
    	order = new int[sc.getTestSubsets().size()];
    	for(int i = 0; i < order.length; i++)
    		order[i] = i;
    	    	  
    	// add the original
    	lines.add(new StepFunctionLine(sc,order,new Color(0,0,0),techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Original","N/A"));  	
     	
    	for(int i = 0; i < order.length; i++)
    		order[order.length-1-i] = i;
    	// add the reverse
    	lines.add(new StepFunctionLine(sc,order,new Color(0,0,0),techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Reverse","N/A"));
   	 	
    	// make the random
    	for(int i = 0; i < numRand; i++)
    	{
    		shuffle(order);
    		randLines.add(new StepFunctionLine(sc, order , new Color(200,200,200),randLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Random","N/A"));
    	}
    	
    	
    	//Get the prioritizations
    	//grd cost
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingGreedy("time");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,colors[0],techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Greedy","Runtime"));
    	
    	//grd cov
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingGreedy("coverage");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,colors[0],techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Greedy","Coverage"));
    	
    	//grd ratio
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingGreedy("ratio");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,colors[0],techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Greedy","Ratio"));
    	
    	//2opt cost
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsing2Optimal("time");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,colors[1],techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"2Optimal","Runtime"));
    	
    	//2opt cov
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsing2Optimal("coverage");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,colors[1],techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"2Optimal","Coverage"));
    	
    	//2opt ratio
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsing2Optimal("ratio");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,colors[1],techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"2Optimal","Ratio"));
    	
    	//dgr cost
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingDelayedGreedy("time");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,colors[2],techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Delayed Greedy","RunTime"));
    	
    	//dgr cov
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingDelayedGreedy("coverage");
    	order = sc.getPrioritizedOrderArray();    	
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,colors[2],techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Delayed Greedy","Coverage"));
    	
    	//dgr ratio
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingDelayedGreedy("ratio");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,colors[2],techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Delayed Greedy","Ratio"));
    	
    	//hgs cost
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingHarroldGuptaSoffa("time",3);
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,colors[3],techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Harrold Gupta Soffa","Runtime"));
    	
    	//hgs cov
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingHarroldGuptaSoffa("coverage",3);
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,colors[3],techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Harrold Gupta Soffa","Coverage"));
    	
    	//hgs ratio
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingHarroldGuptaSoffa("ratio",3);
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,colors[3],techniqueLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Harrold Gupta Soffa","Ratio"));
    	
    }
	
    /*
	 * The paint method is called to show the screen.
	 *
	 * @author Adam M. Smith
	 */
	
	public void paint(Graphics g)
	{
	    g2d = (Graphics2D) g;

	    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
	   
	    rh.put(RenderingHints.KEY_RENDERING,
	               RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        // Set the font for displaying the text information on the screen.
	    g2d.setFont(new Font("Purisa", Font.PLAIN, 14));
	    
	    //Set to gray and fill info window
	    g2d.setColor(new Color(240,240,240));
	    g2d.fillRect(0, 0, infoWidth, windowHeight);
	    
	    // Set to white and fill the plot window
	    g2d.setColor(new Color(255, 255, 255));
	    g2d.fillRect(infoWidth, 0, windowWidth, windowHeight);
	    
	    //  Draw the axes
	    g2d.setColor(new Color(0,0,0));
	    g2d.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
	    //y axis
	    g2d.drawLine(infoWidth+(axesWidth*3/4)-5, windowHeight - axesWidth*3/4,infoWidth+(axesWidth*3/4)-5 , edgeBuffer);
	    //x axis
	    g2d.drawLine(infoWidth+(axesWidth*3/4), windowHeight - axesWidth*3/4,windowWidth-edgeBuffer , windowHeight - axesWidth*3/4);
	    
	    // Draw the ticks
	    g2d.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
	    for(int i = 0; i < numTicks; i ++)
	    {
	    	// Horizontal ticks on y axis
	    	int vertTickPoint = i*((windowHeight - axesWidth*3/4) - edgeBuffer)/(numTicks-1);
	    	g2d.drawLine(infoWidth+(axesWidth*3/4)-tickLength-5, edgeBuffer + vertTickPoint, infoWidth+(axesWidth*3/4)+tickLength-5, edgeBuffer + vertTickPoint);
	    	g2d.drawString((float)(numTicks-1-i)*((float)numReqs/(float)(numTicks-1))+"",infoWidth+(axesWidth*3/4)-60,edgeBuffer + vertTickPoint+5);
	    	
	    	//Vertical ticks on x axis
	    	int horTickPoint = i*( (windowWidth-edgeBuffer) -(infoWidth+(axesWidth*3/4)))/(numTicks-1);
	    	g2d.drawLine(horTickPoint+(infoWidth+(axesWidth*3/4)),windowHeight - axesWidth*3/4-tickLength, horTickPoint+(infoWidth+(axesWidth*3/4)), windowHeight - axesWidth*3/4+tickLength);
	    	g2d.drawString((float)(i)*((float)execTime/(float)(numTicks-1))+"",horTickPoint+(infoWidth+(axesWidth*3/4))-22, windowHeight - axesWidth*3/4+tickLength+15);
	    }
	    
	    
	    // Draw the stepfunctionlines
	    for(StepFunctionLine l : randLines)
    	{
    		l.setGraphics(g2d);
    		l.drawStep();    	
    	}
	    
	    for(int i = 0; i<lines.size();i++)
	    {
	    	if(show[i]==true)
		    {
		    	lines.get(i).setGraphics(g2d);
	    		lines.get(i).drawStep();	
		    } 
	    }
	    
	    // Draw the plot info
	    g2d.setColor(new Color(0,0,0));
	    g2d.drawString("Coverage: RPMatrix.dat", 17, 30);
	    g2d.drawString("Timing: RPTime.dat", 17, 50);
	    g2d.drawString("Test Cases: "+lines.get(0).getNumTests(), 17, 70);
	    // g2d.drawString("Execution Time: " + lines.get(0).getExecutionTime()+ " ms", 17, 70);
	    
	    //Draw a separator box
	    g2d.drawRect(0, 0, infoWidth -1 , 85);
	    
	    ////////////////// Original and Reverse
	    
	    g2d.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));	
	  //Draw vert lines between to delimit original/reverse buttons
	    for(int i = 0; i <= 1;i++)
	    {
	    	g2d.drawLine(originalGridStartX+i*gridSpacingX ,originalGridStartY,originalGridStartX+i*gridSpacingX,originalGridStartY+oTechniques.length*gridSpacingY);
	    }
	    
	    //Draw hor lines between to delimit original/reverse button grids
	    for(int i = 0; i <= oTechniques.length;i++)
	    {
	    	g2d.drawLine(originalGridStartX ,originalGridStartY+i*gridSpacingY,originalGridStartX +gridSpacingX ,originalGridStartY +i*gridSpacingY);
	    }
	    

	    //Draw the text labels for the technique buttons
	    for(int i =0; i < oTechniques.length;i++)
	    {
	    	g2d.drawString(oTechniques[i],originalGridStartX-originalLabelsOffsetX ,originalGridStartY+originalLabelsOffsetY+i*gridSpacingY );
	    }
	    
	 // The buttons
	    for(int i = 0; i < 2; i++)
	    {
	    	if(show[i]==true)
	    	{
	    		int row = i / 1;
	    		int col = i % 1;
	    			    		
	    		g2d.fillRect(originalGridStartX+col*gridSpacingX+buttonInsetX/2,originalGridStartY+row*gridSpacingY+buttonInsetY/2,gridSpacingX-buttonInsetX,gridSpacingY - buttonInsetY);
	       	}
	    	
	    }
	    
	    //Labels over the controls
	    g2d.drawString("Prioritization Techniques", originalGridStartX-46, originalGridStartY-30);
	    g2d.drawString("Random Prioritizations", scrollBarStartX+65, scrollBarStartY-30);
	    
	    ///////////////////////////  Big Button grid
	    
	    //Draw vert lines between to delimit buttons
	    for(int i = 0; i <= gcm.length;i++)
	    {
	    	g2d.drawLine(gridStartX+i*gridSpacingX ,gridStartY,gridStartX+i*gridSpacingX,gridStartY+techniques.length*gridSpacingY);
	    }
	    
	    //Draw hor lines between to delimit grids
	    for(int i = 0; i <= techniques.length;i++)
	    {
	    	g2d.drawLine(gridStartX ,gridStartY+i*gridSpacingY,gridStartX +gcm.length*gridSpacingX ,gridStartY +i*gridSpacingY);
	    }
	    
	    
	    //Draw the text labels for the technique buttons
	    for(int i =0; i < techniques.length;i++)
	    {
	    	g2d.setColor(colors[i]);
	    	g2d.drawString(techniques[i],gridStartX-techniqueLabelsOffsetX ,gridStartY+techniqueLabelsOffsetY+i*gridSpacingY );
	    }
	    
	    g2d.setColor(Color.BLACK);
	    //Draw the gcm labels for the technique buttons
	    for(int i = 0; i <gcm.length;i++)
	    {
	    	g2d.drawString(gcm[i],gridStartX+gcmLabelsOffsetX+i*gridSpacingX ,gridStartY-gcmLabelsOffsetY);
	    }
	   	    
	    // The buttons
	    for(int i = 2; i < lines.size(); i++)
	    {
	    	g2d.setColor(colors[(i-2)/gcm.length]);
	    	if(show[i]==true)
	    	{
	    		int row = (i-2) / gcm.length;
	    		int col = (i-2) % gcm.length;
	    			    		
	    		g2d.fillRect(gridStartX+col*gridSpacingX+buttonInsetX/2,gridStartY+row*gridSpacingY+buttonInsetY/2,gridSpacingX-buttonInsetX,gridSpacingY - buttonInsetY);
	       	}	
	    }
	    
	    
	    // Scroll Bar
	   // g2d.drawLine(scrollBarStartX, scrollBarStartY, scrollBarStartX+scrollBarLength, scrollBarStartY);
	    g2d.setColor(Color.WHITE);
	    g2d.fillRect(scrollBarStartX, scrollBarStartY-((gridSpacingY-buttonInsetY)/2), scrollBarLength,gridSpacingY-buttonInsetY);
	    g2d.setColor(new Color(200,200,200));
	    g2d.fillRect(scrollBoxX, scrollBoxY-((gridSpacingY-buttonInsetY)/2), gridSpacingX-buttonInsetX,gridSpacingY-buttonInsetY);
	    g2d.setColor(Color.BLACK);
	    g2d.setFont(new Font("Purisa", Font.PLAIN, 18));
	    g2d.drawString(numRand+"", scrollBoxX+26, scrollBoxY+7);
	    g2d.setFont(new Font("Purisa", Font.PLAIN, 14));
	    
	    // Stats for random
	    g2d.drawString("Avg CE: " + aveRandCE,scrollBarStartX-5, scrollBarStartY+29);
	    g2d.drawString("Running Avg CE: "+ runningAveRandCE,scrollBarStartX-5, scrollBarStartY+49);
	    g2d.drawString("St. Dev.: " + randSTDev,scrollBarStartX-5, scrollBarStartY+69);
	    g2d.drawString("Running St. Dev. " + runningRandSTDev,scrollBarStartX-5, scrollBarStartY+89);
	    
	    // axis labels
	    g2d.setColor(Color.black);
	    g2d.drawString("Execution Time (ms)", (windowWidth-infoWidth)/2+infoWidth - 35 ,windowHeight - axesWidth*3/4 + 45);
	        
	    g2d.translate((infoWidth+ 15), (windowHeight/2));
	    g2d.rotate(-Math.PI/2);
	    g2d.drawString("Covered Requirements", -55 ,-2);
	    g2d.rotate(Math.PI/2);
	    
	}
	
	
	/*
	 * This method is called every time a mouse event is triggered.
	 * This program will only handle mouse clicks.  
	 */
	public void mouseMoved(MouseEvent e) 
	{
		if(e.getX() > infoWidth)
		{
		for(StepFunctionLine l : lines)
		{
			if(l.contains(e.getX(), e.getY()))
			{
				//l.color=(new Color(255,0,0));
				l.drawArea = true;
				l.highlight = true;
				l.displayInfo(e.getX(),e.getY());
				
			}
			else
			{
				l.highlight = false;
				//l.color=l.defaultColor;
				l.drawArea = false;
			}
		}		
		
		}
		
		repaint();	
	}
	
	public void mouseClicked(MouseEvent e)
	{
		
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (mouseX < infoWidth)
		{
			// techniques
			for(int i = 2; i < lines.size(); i++)
		    {
		       	int row = (i-2) / gcm.length;
		    	int col = (i-2) % gcm.length;
		    			  
		    	if(mouseX >= gridStartX+col*gridSpacingX+buttonInsetX/2 && mouseX <= (gridStartX+col*gridSpacingX+buttonInsetX/2)+gridSpacingX-buttonInsetX )
		    	{
		    		if(mouseY >=gridStartY+row*gridSpacingY+buttonInsetY/2 && mouseY <=(gridStartY+row*gridSpacingY+buttonInsetY/2)+gridSpacingY - buttonInsetY)
		    		{
		    			if(show[i]== true)
		    				show[i] = false;
		    			else
		    				show[i] = true;
		    			
		    			repaint();
		    			break;
		    		}
		    	}		    	
		    }

			// original and reverse
			for(int i = 0; i < oTechniques.length;i++)
			{
				int row = i / 1;
		    	int col = i % 1;
				if(mouseX >= originalGridStartX+col*gridSpacingX+buttonInsetX/2 && mouseX <= (originalGridStartX+col*gridSpacingX+buttonInsetX/2)+gridSpacingX-buttonInsetX)
				{
					if(mouseY >=originalGridStartY+row*gridSpacingY+buttonInsetY/2 && mouseY <=(originalGridStartY+row*gridSpacingY+buttonInsetY/2)+gridSpacingY - buttonInsetY)
					{
						if(show[i]== true)
		    				show[i] = false;
		    			else
		    				show[i] = true;
		    			
						repaint();
						break;
					}
				}		
			}
		}
	}
	
	
	public void mousePressed(MouseEvent e) 
	{
		int mouseX = e.getX();
		int mouseY = e.getY();		
		
		if(mouseX < infoWidth)
		{
			if(mouseX >= scrollBoxX && mouseX <= scrollBoxX + gridSpacingX - buttonInsetX)
			{
				if(mouseY >= scrollBoxY-((gridSpacingY-buttonInsetY)/2) && mouseY <= (scrollBoxY + gridSpacingY - buttonInsetY)-((gridSpacingY-buttonInsetY)/2))
				{
					scrolling = true;
					randLines.clear();
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) 
	{
		if(scrolling)
		{
			scrolling = false;
			randLines.clear();		
			
			//Clear current average CE and standard deviation
			aveRandCE = 0;
			randSTDev = 0;
			
			if(numRand !=0 )
			{
				for(int i = 0; i < numRand; i++)
				{
			   		shuffle(order);
		    		StepFunctionLine randLine = new StepFunctionLine(sc, order , new Color(200,200,200),randLineWidth,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth-edgeBuffer,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Random","N/A");
			   		
		    		// Update CE to be divided later
		    		aveRandCE += randLine.getCE();
		    		
		    		/*
						private static float runningRandSTDev = 0;
						private static float randSTDev = 0; */
		    		randLines.add(randLine);
				}
				
				// Running average update: aveRandCE hasn't been divided by the numRand yet
				// so we can just add it to the running total CE and divide later.
				runningAveRandCE = runningAveRandCE*numRandGenerated + aveRandCE;
				
				// Update the total number of random generated and update the running average
				numRandGenerated += numRand;
				runningAveRandCE = runningAveRandCE/numRandGenerated;
							
				// Current average CE calculate
				aveRandCE = aveRandCE/numRand;
				
				//Calculate Standard deviation
				float sumOfSquares = 0;
				Iterator randIt = randLines.iterator();
				while(randIt.hasNext())
				{
					StepFunctionLine currentRandLine = (StepFunctionLine) randIt.next();
					sumOfSquares += (currentRandLine.getCE()-aveRandCE)*(currentRandLine.getCE()-aveRandCE);
				}
				
				randSTDev = (float) Math.sqrt((sumOfSquares/((float)numRand-1)) );
				runningSumOfSquares += sumOfSquares;
				runningRandSTDev = (float) Math.sqrt(runningSumOfSquares/((float)numRandGenerated-1));
				
				System.out.println("Average CE: " + aveRandCE +"\nRunning Average CE: " 
									+ runningAveRandCE +"\nStandard Deviation: " 
									+ randSTDev+"\nRunning Standard Deviation"
									+ runningRandSTDev);
			}
			repaint();
		}
	}
	
	public void mouseEntered(MouseEvent e) 
	{
	
//		System.out.println("Mouse Entered! ("+e.getX()+", "+e.getY()+"(");	
		}
	
	public void mouseExited(MouseEvent e) 
	{
//		System.out.println("Mouse Exited! ("+e.getX()+", "+e.getY()+"(");	
	}
	
	 public static void shuffle (int[] array) 
    {
        Random rng = new Random();   // i.e., java.util.Random.
        int n = array.length;        // The number of items left to shuffle (loop invariant).
        while (n > 1) 
        {
            int k = rng.nextInt(n);  // 0 <= k < n.
            n--;                     // n is now the last pertinent index;
            int temp = array[n];     // swap array[n] with array[k] (does nothing if k == n).
            array[n] = array[k];
            array[k] = temp;
        }
    }


	public void mouseDragged(MouseEvent e) 
	{
		int mouseX = e.getX();
		
		if(scrolling)
		{
			scrollBoxX = mouseX;
			if(scrollBoxX > (scrollBarStartX+scrollBarLength) - (gridSpacingX-buttonInsetX))
				scrollBoxX = (scrollBarStartX+scrollBarLength) - (gridSpacingX-buttonInsetX);
	
			else if(scrollBoxX < scrollBarStartX)
				scrollBoxX = scrollBarStartX;
		
			numRand =(int) ((((float)(scrollBoxX - scrollBarStartX))/((float)(((scrollBarStartX+scrollBarLength) - (gridSpacingX-buttonInsetX))-scrollBarStartX))  )*((float)MAX_RAND));
			repaint();
		}
		
	}


	public void stateChanged(ChangeEvent e) 
	{
		JSlider source = (JSlider)e.getSource();
	    if (!source.getValueIsAdjusting()) 
	        numRand = (int)source.getValue();
	    
	 // Get rid of the old lines
		this.randLines.clear();
		
		// add the new random lines to the randLines arrayList
		for(int i = 0; i < numRand; i++)
    	{
    		shuffle(this.order);
    		this.randLines.add(new StepFunctionLine(this.sc, this.order , new Color(200,200,200),1,windowWidth-(infoWidth + axesWidth),windowHeight - axesWidth-edgeBuffer,infoWidth+(axesWidth*3/4),windowHeight - axesWidth*3/4,"Random", "N/A"));
    	}
	    
	    repaint();
	}
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args)
	{
	    JFrame frame = new JFrame("Coverage Effectiveness");
	    
	    matrixFile = JOptionPane.showInputDialog("Enter the path to the matrix file.");
	    timeFile = JOptionPane.showInputDialog("Enter the path to the time file.");
	    
	    if(matrixFile.equals("") || timeFile.equals("") )
	    {
	    	matrixFile = "data/raise/reduce/setCovers/RPMatrix.dat";
	    	timeFile = "data/raise/reduce/setCovers/RPTime.dat";
	    }
	    
	    frame.setLayout(new GridLayout());
	    
	    frame.add(new CoverageEffectivenessVisualization());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
}