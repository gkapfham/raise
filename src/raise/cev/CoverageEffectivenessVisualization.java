package raise.cev;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import raise.reduce.SetCover;

import java.awt.event.ActionEvent;

import java.awt.Font;

import java.awt.AlphaComposite;

import java.awt.RenderingHints;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 *	This class implements a visualization of many prioritizations of a test suite. 
 *
 */

public class CoverageEffectivenessVisualization extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	
	// Define the size of the screen.
	private final int windowWidth = 971;
	private final int windowHeight = 600;
	private final int infoWidth = 300;
	private final int axesWidth = 40;
	private final int edgeBuffer = 20;
	private final int tickLength = 5;
	private final int numTicks = 5;
	private final String matrixFile = "C:/Users/Adam/Documents/raise/data/raise/reduce/setCovers/RPMatrix.dat";
	private final String timeFile = "C:/Users/Adam/Documents/raise/data/raise/reduce/setCovers/RPTime.dat";
	//private final String matrixFile = "/home/geiger/Documents/school/pitt/cs2620/raise/data/raise/reduce/setCovers/RPMatrix.dat";
	//private final String timeFile = "/home/geiger/Documents/school/pitt/cs2620/raise/data/raise/reduce/setCovers/RPTime.dat";
	private final int randLineWidth = 1;
	private final int techniqueLineWidth = 2;
	
	private static final long serialVersionUID = 1L;

	private int numRand;
	
	double execTime;
	int numTests;
	
	int[] order;
	SetCover sc;
	
	// For the size of the window
    private Dimension size;

    // Required for J2D
    Graphics2D g2d;
    
    ArrayList<StepFunctionLine> lines;
    ArrayList<StepFunctionLine> randLines;
    
    boolean[] show;

    public CoverageEffectivenessVisualization()
    {
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
    	show = new boolean[13];
    	
    	show[0] = true;
    	
    	for(int i = 1; i<13; i++)
    		show[i]=true;
    	
    	lines = new ArrayList<StepFunctionLine>();
    	randLines = new ArrayList<StepFunctionLine>();
    	// Default to 20 and then set later via user interaction.
    	
    	numRand = 20;
    	
    	
    	sc = new SetCover();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, matrixFile);
    	
    	numTests = sc.getTestSubsets().size();
    	execTime = SetCover.getExecutionTimeSingleTestSubsetList(sc.getTestSubsets());
    	
    	
    	System.out.println(numTests+","+ execTime+",");
    	
    	
    	order = new int[sc.getTestSubsets().size()];
    	for(int i = 0; i < order.length; i++)
    		order[i] = i;
    	    	  
    	// add the original
    	System.out.println("original");
   	 	lines.add(new StepFunctionLine(sc,order,new Color(0,255,0),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));  	
     	
    	// make the random
    	for(int i = 0; i < numRand; i++)
    	{
    		System.out.println("Random");
    		shuffle(order);
    		randLines.add(new StepFunctionLine(sc, order , new Color(200,200,200),randLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	}
    	
    	//Get the prioritizations
    	  System.out.println("Several techniques");
    	//grd cost
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingGreedy("time");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,new Color(255,0,255),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	
    	//grd cov
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingGreedy("coverage");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,new Color(0,0,255),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	
    	//grd ratio
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingGreedy("ratio");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,new Color(0,0,255),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	
    	//2opt cost
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsing2Optimal("time");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,new Color(255,0,255),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	
    	//2opt cov
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsing2Optimal("coverage");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,new Color(255,0,255),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	
    	//2opt ratio
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsing2Optimal("ratio");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,new Color(255,0,255),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	
    	//dgr cost
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingDelayedGreedy("time");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,new Color(0,255,255),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	
    	//dgr cov
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingDelayedGreedy("coverage");
    	order = sc.getPrioritizedOrderArray();    	
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,new Color(0,255,255),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	
    	//dgr ratio
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingDelayedGreedy("ratio");
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,new Color(0,255,255),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	
    	//hgs cost
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingHarroldGuptaSoffa("time",3);
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,new Color(255,255,0),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	
    	//hgs cov
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingHarroldGuptaSoffa("coverage",3);
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,new Color(255,255,0),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	
    	//hgs ratio
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	sc.prioritizeUsingHarroldGuptaSoffa("ratio",3);
    	order = sc.getPrioritizedOrderArray();
    	sc = SetCover.constructSetCoverFromMatrix(matrixFile, timeFile);
    	lines.add(new StepFunctionLine(sc,order,new Color(255,255,0),techniqueLineWidth,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	
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
	    g2d.setFont(new Font("Purisa", Font.PLAIN, 12));
	    
	    // Set to white and fill the plot window
	    g2d.setColor(new Color(255, 255, 255));
	    g2d.fillRect(0, 0, windowWidth, windowHeight);
	    
	    
	    // Set the gray and fill the info window
	    g2d.setColor(new Color(200, 200, 200));
	    g2d.fillRect(0, 0, infoWidth, windowHeight);
	    
	    //  Draw the axes
	    g2d.setColor(new Color(0,0,0));
	    g2d.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
	    //y axis
	    g2d.drawLine(infoWidth+(axesWidth*3/4), windowHeight - axesWidth*3/4,infoWidth+(axesWidth*3/4) , edgeBuffer);
	    //x axis
	    g2d.drawLine(infoWidth+(axesWidth*3/4), windowHeight - axesWidth*3/4,windowWidth-edgeBuffer , windowHeight - axesWidth*3/4);
	    
	    // Draw the ticks
	    g2d.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
	    for(int i = 0; i < numTicks; i ++)
	    {
	    	// Horizontal ticks on y axis
	    	int vertTickPoint = i*((windowHeight - axesWidth*3/4) - edgeBuffer)/(numTicks-1);
	    	g2d.drawLine(infoWidth+(axesWidth*3/4)-tickLength, edgeBuffer + vertTickPoint, infoWidth+(axesWidth*3/4)+tickLength, edgeBuffer + vertTickPoint);
	    	
	    	
	    	//Vertical ticks on x axis
	    	int horTickPoint = i*( (windowWidth-edgeBuffer) -(infoWidth+(axesWidth*3/4)))/(numTicks-1);
	    	g2d.drawLine(horTickPoint+(infoWidth+(axesWidth*3/4)),windowHeight - axesWidth*3/4-tickLength, horTickPoint+(infoWidth+(axesWidth*3/4)), windowHeight - axesWidth*3/4+tickLength);
	    }
	    
	    //labels
	    g2d.drawString("Execution Time (ms)", (windowWidth-infoWidth)/2+infoWidth - 50 ,windowHeight - axesWidth*3/4 + 25);
	    
	    // Draw the lines
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
	    g2d.drawString("Test Suite: RPMatrix.dat and RPTime.dat", 17,30);
	    g2d.drawString("Test Cases: "+lines.get(0).getNumTests(), 17, 50);
	    g2d.drawString("Execution Time: " + lines.get(0).getExecutionTime()+ " ms", 17, 70);
	    
	    
	    g2d.setColor(new Color(0,0,255));
	    g2d.drawString("GRD", 17, 110);
	    g2d.setColor(new Color(255,0,255));
	    g2d.drawString("2OPT", 17, 130);
	    g2d.setColor(new Color(0,255,255));
	    g2d.drawString("DRG", 17, 150);
	    g2d.setColor(new Color(255,255,0));
	    g2d.drawString("HGS", 17, 170);
	}
	
	public static void main(String[] args)
	{
	    JFrame frame = new JFrame("Coverage Effectiveness");
	    frame.add(new CoverageEffectivenessVisualization());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	/*
	 * This method is called every time the timer sends out
	 * an action.
	 */
	public void actionPerformed(ActionEvent e)
	{
	    System.out.println("Action!");
	}
	
	/*
	 * This method is called every time a mouse event is triggered.
	 * This program will only handle mouse clicks.  
	 */
	public void mouseMoved(MouseEvent e) 
	{
		for(StepFunctionLine l : lines)
		{
			if(l.contains(e.getX(), e.getY()))
			{
				l.color=(new Color(255,0,0));
			}
			else
			{
				l.color=(new Color(0,255,0));
			}
				
		}
		
		//if(!hit)
		//	System.out.println("MISS!");
		
		System.out.println("Mouse Moved! ("+e.getX()+", "+e.getY()+")");
		repaint();
	}
	
	public void mouseClicked(MouseEvent e)
	{
		System.out.println("Mouse Clicked! ("+e.getX()+", "+e.getY()+"(");
		// Increase the number of random lines
		this.numRand  = (this.numRand +10)%60;
		
		// Get rid of the old lines
		this.randLines.clear();
		
		// add the new random lines to the randLines arrayList
		for(int i = 0; i < numRand; i++)
    	{
    		shuffle(this.order);
    		this.randLines.add(new StepFunctionLine(this.sc, this.order , new Color(200,200,200),1,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	}
		
		repaint();
	}
	
	
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse Pressed! ("+e.getX()+", "+e.getY()+"(");	}
	
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse Released! ("+e.getX()+", "+e.getY()+"(");	}
	
	public void mouseEntered(MouseEvent e) {
	
		System.out.println("Mouse Entered! ("+e.getX()+", "+e.getY()+"(");	}
	
	public void mouseExited(MouseEvent e) {
		System.out.println("Mouse Exited! ("+e.getX()+", "+e.getY()+"(");	}
	
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

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		System.out.println("Mouse Dragged! ("+e.getX()+", "+e.getY()+"(");	
	}
}
