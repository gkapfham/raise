package raise.cev;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;

import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import raise.reduce.SetCover;

import java.awt.event.ActionEvent;

import java.awt.Font;

import java.awt.geom.AffineTransform;

import java.awt.AlphaComposite;

import java.lang.Math;

import java.awt.RenderingHints;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
		
	private static final long serialVersionUID = 1L;

	// For the size of the window
    Dimension size;

    // This timer handles all transition animations.
    Timer timer;

    // Required for J2D
    Graphics2D g2d;
    
    ArrayList<StepFunctionLine> lines = new ArrayList<StepFunctionLine>();

    public CoverageEffectivenessVisualization()
    {
    	// A mouse listener is added to receive clicks
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
    	SetCover sc = new SetCover();
    	sc = SetCover.constructSetCoverFromMatrix("C:/Users/Adam/Documents/raise/data/raise/reduce/setCovers/RPMatrix.dat", "C:/Users/Adam/Documents/raise/data/raise/reduce/setCovers/RPTime.dat");
    	
    	int[] order = new int[sc.getTestSubsets().size()];
    	for(int i = 0; i < order.length; i++)
    		order[i] = i;
    	int[] originalOrder = order.clone();
    	
    	for(int i = 0; i < 20; i++)
    	{
    		shuffle(order);
    		lines.add(new StepFunctionLine(sc, order , new Color(100,100,100),2));
    	}
    	
   	  	lines.add(new StepFunctionLine(sc,originalOrder,new Color(0,0,255),4));    	
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
	    
	    // Draw the plot info
	    
	    
	    for(StepFunctionLine l : lines)
    	{
    		l.setGraphics(g2d);
    		l.drawStep();	    	
    	}
	    
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
	public void mouseClicked(MouseEvent e) {
	
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
