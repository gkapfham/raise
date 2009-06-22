package raise.reduce;

import java.io.FileNotFoundException;
import java.io.PrintStream;

// I would really like to make a class that can be more versatile for
// running experiments, but for now this will do. 

public class ExperimentRunner 
{
	
	public static void main(String[] args)
	{
		String[] metrics = {"coverage","time","ratio"};
		String[] techniques = {"GRD","2OPT","HGS","DGR"};
		
		/*************************  ALGORITHMS *********************/

		/*	
		runAlgExperiment("data/raise/reduce/setCovers/ADMatrix.dat","data/raise/reduce/setCovers/ADTime.dat",
				"results/raise/reduce/IST/ADResults.dat",metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/LFMatrix.dat","data/raise/reduce/setCovers/LFTime.dat",
				"results/raise/reduce/IST/LFResults.dat",metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/RPMatrix.dat","data/raise/reduce/setCovers/RPTime.dat",
				"results/raise/reduce/IST/RPResults.dat",metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/DSMatrix.dat","data/raise/reduce/setCovers/DSTime.dat",
				"results/raise/reduce/IST/DSResults.dat",metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/GBMatrix.dat","data/raise/reduce/setCovers/GBTime.dat",
				"results/raise/reduce/IST/GBResults.dat",metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/JDMatrix.dat","data/raise/reduce/setCovers/JDTime.dat",
				"results/raise/reduce/IST/JDResults.dat",metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/RMMatrix.dat","data/raise/reduce/setCovers/RMTime.dat",
				"results/raise/reduce/IST/RMResults.dat",metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/SKMatrix.dat","data/raise/reduce/setCovers/SKTime.dat",
				"results/raise/reduce/IST/SKResults.dat",metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/TMMatrix.dat","data/raise/reduce/setCovers/TMTime.dat",
				"results/raise/reduce/IST/TMResults.dat",metrics, techniques);
		*/
		
		/*******************  RANDOM ************************/
		/*
		runRandomExperiment("data/raise/reduce/setCovers/ADMatrix.dat","data/raise/reduce/setCovers/ADTime.dat",
				"results/raise/reduce/IST/ADRandomResults.dat",50);
		runRandomExperiment("data/raise/reduce/setCovers/LFMatrix.dat","data/raise/reduce/setCovers/LFTime.dat",
				"results/raise/reduce/IST/LFRandomResults.dat",50);
		runRandomExperiment("data/raise/reduce/setCovers/RPMatrix.dat","data/raise/reduce/setCovers/RPTime.dat",
				"results/raise/reduce/IST/RPRandomResults.dat",50);
		runRandomExperiment("data/raise/reduce/setCovers/DSMatrix.dat","data/raise/reduce/setCovers/DSTime.dat",
				"results/raise/reduce/IST/DSRandomResults.dat",50);
		runRandomExperiment("data/raise/reduce/setCovers/GBMatrix.dat","data/raise/reduce/setCovers/GBTime.dat",
				"results/raise/reduce/IST/GBRandomResults.dat",50);
		runRandomExperiment("data/raise/reduce/setCovers/JDMatrix.dat","data/raise/reduce/setCovers/JDTime.dat",
				"results/raise/reduce/IST/JDRandomResults.dat",50);
		runRandomExperiment("data/raise/reduce/setCovers/RMMatrix.dat","data/raise/reduce/setCovers/RMTime.dat",
				"results/raise/reduce/IST/RMRandomResults.dat",50);
		runRandomExperiment("data/raise/reduce/setCovers/SKMatrix.dat","data/raise/reduce/setCovers/SKTime.dat",
				"results/raise/reduce/IST/SKRandomResults.dat",50);
		runRandomExperiment("data/raise/reduce/setCovers/TMMatrix.dat","data/raise/reduce/setCovers/TMTime.dat",
				"results/raise/reduce/IST/TMRandomResults.dat",50);
		*/
		printRedSet("data/raise/reduce/setCovers/ADCoverage.dat","data/raise/reduce/setCovers/ADTime.dat",
				"results/raise/reduce/IST/ADReducedSet.dat",metrics, techniques);
		printRedSet("data/raise/reduce/setCovers/LFCoverage.dat","data/raise/reduce/setCovers/LFTime.dat",
				"results/raise/reduce/IST/LFReducedSet.dat",metrics, techniques);
		printRedSet("data/raise/reduce/setCovers/RPCoverage.dat","data/raise/reduce/setCovers/RPTime.dat",
				"results/raise/reduce/IST/RPReducedSet.dat",metrics, techniques);
		printRedSet("data/raise/reduce/setCovers/DSCoverage.dat","data/raise/reduce/setCovers/DSTime.dat",
				"results/raise/reduce/IST/DSReducedSet.dat",metrics, techniques);
		printRedSet("data/raise/reduce/setCovers/GBCoverage.dat","data/raise/reduce/setCovers/GBTime.dat",
				"results/raise/reduce/IST/GBReducedSet.dat",metrics, techniques);
		printRedSet("data/raise/reduce/setCovers/JDCoverage.dat","data/raise/reduce/setCovers/JDTime.dat",
				"results/raise/reduce/IST/JDReducedSet.dat",metrics, techniques);
		printRedSet("data/raise/reduce/setCovers/RMCoverage.dat","data/raise/reduce/setCovers/RMTime.dat",
				"results/raise/reduce/IST/RMReducedSet.dat",metrics, techniques);
		printRedSet("data/raise/reduce/setCovers/SKCoverage.dat","data/raise/reduce/setCovers/SKTime.dat",
				"results/raise/reduce/IST/SKReducedSet.dat",metrics, techniques);
		printRedSet("data/raise/reduce/setCovers/TMCoverage.dat","data/raise/reduce/setCovers/TMTime.dat",
				"results/raise/reduce/IST/TMReducedSet.dat",metrics, techniques);
	
		System.out.println("Completed Experiments.");
	}		
	
	public static void runRandomExperiment(String coverageFile, String timeFile, String resultsFile, int samples)
	{
		PrintStream out = null;
		try
		{
			out = new PrintStream(resultsFile);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		out.println("alg\tsamples\tpriorTime\tCE\tapp");
	
		SetCover cover;
	
		double start = -1;
		double stop = -1;
	
		double priorTime = -1;
		double totalCE = 0;

		System.out.println("Test Suite: " + coverageFile + "\nRandom Prioritization");
		
		//cover = SetCover.constructSetCoverFromCoverageAndTime(coverageFile, timeFile,false);
		cover = SetCover.constructSetCoverFromMatrix(coverageFile, timeFile);
		
		for(int i = 1; i <= samples; i++)
		{				
			System.out.println(i+"/"+samples);
			
			start = System.currentTimeMillis();
			cover.prioritizeUsingRandom();
			stop = System.currentTimeMillis();
			
			priorTime += stop-start;
			
			int[] order = cover.getPrioritizedOrderArray();
			
			totalCE += cover.getCE(order);
		}
		
		out.println("RND\t"+samples+"\t"+priorTime + "\t"+totalCE/samples+"\t"+ coverageFile);
	}
	
	public static void printRedSet(String coverageFile, String timeFile, String resultsFile, 
			String[] metrics, String[] techniques)
	{
		PrintStream out=null;
			
		try
		{
			out = new PrintStream(resultsFile);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		out.println("alg\tmetric\tapp\tredSet");
		
		SetCover cover;
		
		for(String technique : techniques)
		{
			for(String metric : metrics)
			{	
				System.out.println("Test Suite: " + coverageFile + "\ntechnique: "+technique
						+"\nmetric: "+metric);
				
				cover = SetCover.constructSetCoverFromCoverageAndTime(coverageFile, timeFile,false);
				
				System.out.println("reducing...");
				
				if(technique.equals("2OPT"))
					cover.reduceUsing2Optimal(metric);
				else if(technique.equals("GRD"))					
					cover.reduceUsingGreedy(metric);
				else if(technique.equals("DGR"))
					cover.reduceUsingDelayedGreedy(metric);
				else if(technique.equals("HGS"))
					cover.reduceUsingHarroldGuptaSoffa(metric);
				
				System.out.println("writing to file...");
				String redSet = cover.getCoveringTestSetStringNoAlter(" ");
				out.println(technique+"\t"+metric+"\t"+coverageFile+"\t"+redSet);
				System.out.println("completed current iteration.");
			}
		}	
	}
	
	public static void runAlgExperiment(String coverageFile, String timeFile, String resultsFile, 
			String[] metrics, String[] techniques)
	{
		PrintStream out=null;
			
		try
		{
			out = new PrintStream(resultsFile);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		//"alg" "metric" "reduceTime" "priorTime" "CE" "RFFS" "RFFT" "origExecTime" "redExecTime" "totalSize" "redSize" "app"
		out.println("alg\tmetric\treduceTime\tpriorTime\tCE\tRFFS\tRFFT\torigExecTime\tredExecTime\ttotalSize\tredSize\tapp");
		
		SetCover cover;
		
		double start = -1;
		double stop = -1;
		
		double redTime, priorTime, CE, RFFS, RFFT, origExec, redExec;
		double totalSize, redSize;
		
		for(String technique : techniques)
		{
			for(String metric : metrics)
			{	
				
				System.out.println("Test Suite: " + coverageFile + "\ntechnique: "+technique
						+"\nmetric: "+metric);
				
				System.out.println("constructing object...");
				//cover = SetCover.constructSetCoverFromCoverageAndTime(coverageFile, timeFile,false);
				cover = SetCover.constructSetCoverFromMatrix(coverageFile, timeFile);
				System.out.println("calculating original execution...");
				origExec = SetCover.getExecutionTimeSingleTestSubsetList(cover.getTestSubsets());
				System.out.println("calculating original size...");
				totalSize = cover.getTestSubsets().size();
				
				System.out.println("reducing...");
				if(technique.equals("2OPT"))
				{
					start = System.currentTimeMillis();
					cover.reduceUsing2Optimal(metric);
					stop = System.currentTimeMillis();
				}
				else if(technique.equals("GRD"))
				{
					start = System.currentTimeMillis();
					cover.reduceUsingGreedy(metric);
					stop = System.currentTimeMillis();
				}
				else if(technique.equals("DGR"))
				{
					start = System.currentTimeMillis();
					cover.reduceUsingDelayedGreedy(metric);
					stop = System.currentTimeMillis();
				}
				else if(technique.equals("HGS"))
				{
					start = System.currentTimeMillis();
					cover.reduceUsingHarroldGuptaSoffa(metric);
					stop = System.currentTimeMillis();
				}
				System.out.println("calculating runtime...");
				redTime = stop-start;
				
				System.out.println("calculating size of reduced suite...");
				redSize = cover.getCoverPickSets().size();
				System.out.println("calculating execution time of reduce suite...");
				redExec = SetCover.getExecutionTimeSingleTestList(cover.getCoverPickSets());
				
				System.out.println("calculating RFFS and RFFT...");
				RFFS = (totalSize-redSize)/totalSize;
				RFFT = (origExec-redExec)/origExec;
				
				System.out.println("reconstructing object...");
				
				//cover = SetCover.constructSetCoverFromCoverageAndTime(coverageFile, timeFile,false);
				cover = SetCover.constructSetCoverFromMatrix(coverageFile, timeFile);
				
				System.out.println("prioritizing...");
				if(technique.equals("2OPT"))
				{
					start = System.currentTimeMillis();
					cover.prioritizeUsing2Optimal(metric);
					stop = System.currentTimeMillis();
				}
				else if(technique.equals("GRD"))
				{
					start = System.currentTimeMillis();
					cover.prioritizeUsingGreedy(metric);
					stop = System.currentTimeMillis();
				}
				else if(technique.equals("DGR"))
				{
					start = System.currentTimeMillis();
					cover.prioritizeUsingDelayedGreedy(metric);
					stop = System.currentTimeMillis();
				}
				else if(technique.equals("HGS"))
				{
					start = System.currentTimeMillis();
					cover.prioritizeUsingHarroldGuptaSoffa(metric);
					stop = System.currentTimeMillis();
				}
				else
				{
					System.out.println("Illegal technique");
					System.exit(0);
				}
				
				System.out.println("calculating prioritization time");
				priorTime = stop-start;
				
				int[] order = cover.getPrioritizedOrderArray();
				
				System.out.println("reconstructing object...");
				//cover = SetCover.constructSetCoverFromCoverageAndTime(coverageFile,timeFile,false);
				cover = SetCover.constructSetCoverFromMatrix(coverageFile, timeFile);

				System.out.println("calculating coverage effectiveness");
				CE = cover.getCE(order);
			
				System.out.println("writing to file...");
				out.println(technique+"\t"+metric+"\t"+redTime+"\t"+priorTime + "\t"+CE+"\t"+RFFS+"\t"+RFFT+"\t"+origExec+"\t"+redExec+"\t"+totalSize+"\t"+redSize+"\t"+ coverageFile);
				System.out.println("completed current iteration.");
			}
		}
	}
}