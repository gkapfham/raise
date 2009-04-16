package raise.cev;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

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

public class CoverageEffectivenessVisualization extends JPanel implements ActionListener, MouseListener {
	
	// Define the size of the screen.
	private final int windowWidth = 971;
	private final int windowHeight = 600;
	private final int infoWidth = 300;
	private final int axesWidth = 40;
	private final int edgeBuffer = 20;
	private final int tickLength = 5;
	private final int numTicks = 5;
	private static final long serialVersionUID = 1L;

	private int numRand;
	
	private int[] order;
	SetCover sc;
	
	// For the size of the window
    Dimension size;

    // This timer handles all transition animations.
    Timer timer;

    // Required for J2D
    Graphics2D g2d;
    
    ArrayList<StepFunctionLine> lines;
    ArrayList<StepFunctionLine> randLines;

    public CoverageEffectivenessVisualization()
    {
    	lines = new ArrayList<StepFunctionLine>();
    	randLines = new ArrayList<StepFunctionLine>();
    	// Default to 20 and then set later via user interaction.
    	numRand = 20;
    	
    	// A mouse listener 
    	addMouseListener(this);

    	// Instantiate and start the timer
    	timer = new Timer(10, this);
    	timer.start();

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
    	sc = new SetCover();
    	sc = SetCover.constructSetCoverFromMatrix("C:/Users/Adam/Documents/raise/data/raise/reduce/setCovers/RPMatrix.dat", "C:/Users/Adam/Documents/raise/data/raise/reduce/setCovers/RPTime.dat");
    	
    	order = new int[sc.getTestSubsets().size()];
    	for(int i = 0; i < order.length; i++)
    		order[i] = i;
    	int[] originalOrder = order.clone();
    	
    	for(int i = 0; i < numRand; i++)
    	{
    		shuffle(order);
    		randLines.add(new StepFunctionLine(sc, order , new Color(100,100,100),1,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	}
    	
   	  	lines.add(new StepFunctionLine(sc,originalOrder,new Color(0,0,255),4,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));  	
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
	    for(StepFunctionLine l : lines)
    	{
    		l.setGraphics(g2d);
    		l.drawStep();	    	
    	}
	    
	    for(StepFunctionLine l : randLines)
	    {
	    	l.setGraphics(g2d);
    		l.drawStep();		    	
	    }
	    
	    // Draw the plot info
	    g2d.setColor(new Color(0,0,0));
	    g2d.drawString("Test Suite: RPMatrix.dat and RPTime.dat", 17,30);
	    g2d.drawString("Test Cases: "+lines.get(0).getNumTests(), 17, 50);
	    g2d.drawString("Execution Time: " + lines.get(0).getExecutionTime()+ " ms", 17, 70);
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
	    
	}
	
	/*
	 * This method is called every time a mouse event is triggered.
	 * This program will only handle mouse clicks.  
	 */
	public void mouseClicked(MouseEvent e) 
	{
		// Increase the number of random lines
		this.numRand  = (this.numRand +10)%50;
		
		// Get rid of the old lines
		this.randLines.clear();
		
		// add the new random lines to the randLines arrayList
		for(int i = 0; i < numRand; i++)
    	{
    		shuffle(this.order);
    		this.randLines.add(new StepFunctionLine(this.sc, this.order , new Color(100,100,100),1,windowWidth-(infoWidth + axesWidth+edgeBuffer),windowHeight - axesWidth-edgeBuffer,infoWidth+axesWidth,windowHeight-axesWidth));
    	}
		
		repaint();
	}
	
	public void mousePressed(MouseEvent e) {
	//     throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public void mouseReleased(MouseEvent e) {
	//    throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public void mouseEntered(MouseEvent e) {
	
	    //   throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public void mouseExited(MouseEvent e) {
	 //   throw new UnsupportedOperationException("Not supported yet.");
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

}