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
		
	
		//runAlgExperiment("data/raise/reduce/setCovers/ADMatrix.dat","data/raise/reduce/setCovers/ADTime.dat",
		//					"results/raise/reduce/IST/ADResults.dat", metrics, techniques);
		
		/*************************  ALGORITHMS *********************/
		/*
		runAlgExperiment("data/raise/reduce/setCovers/ADMatrix.dat","data/raise/reduce/setCovers/ADTime.dat",
				"results/raise/reduce/IST/ADResults.dat",50);
		runAlgExperiment("data/raise/reduce/setCovers/LFMatrix.dat","data/raise/reduce/setCovers/LFTime.dat",
				"results/raise/reduce/IST/LFResults.dat",50);
		runAlgExperiment("data/raise/reduce/setCovers/RPMatrix.dat","data/raise/reduce/setCovers/RPTime.dat",
				"results/raise/reduce/IST/RPResults.dat",50);
		runAlgExperiment("data/raise/reduce/setCovers/DSMatrix.dat","data/raise/reduce/setCovers/DSTime.dat",
				"results/raise/reduce/IST/DSResults.dat",metrics,techniques);
		runAlgExperiment("data/raise/reduce/setCovers/GBMatrix.dat","data/raise/reduce/setCovers/GBTime.dat",
				"results/raise/reduce/IST/GBResults.dat",50);
		runAlgExperiment("data/raise/reduce/setCovers/JDMatrix.dat","data/raise/reduce/setCovers/JDTime.dat",
				"results/raise/reduce/IST/JDResults.dat",50);
		runAlgExperiment("data/raise/reduce/setCovers/RMMatrix.dat","data/raise/reduce/setCovers/RMTime.dat",
				"results/raise/reduce/IST/RMResults.dat",50);
		runAlgExperiment("data/raise/reduce/setCovers/SKMatrix.dat","data/raise/reduce/setCovers/SKTime.dat",
				"results/raise/reduce/IST/SKResults.dat",50);
		runAlgExperiment("data/raise/reduce/setCovers/TMMatrix.dat","data/raise/reduce/setCovers/TMTime.dat",
				"results/raise/reduce/IST/TMResults.dat",50);
		*/
		
		// *******************  RANDOM ************************
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
		
		SetCover cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/ADCoverage.dat",
				"data/raise/reduce/setCovers/ADTime.dat",false);
		System.out.println("AD\n"+cover.toString());
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/LFCoverage.dat",
			"data/raise/reduce/setCovers/LFTime.dat",false);
		System.out.println("LF\n"+cover.toString());
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/DSCoverage.dat",
			"data/raise/reduce/setCovers/DSTime.dat",false);
		System.out.println("DS\n"+cover.toString());
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/GBCoverage.dat",
			"data/raise/reduce/setCovers/GBTime.dat",false);
		System.out.println("GB\n"+cover.toString());
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/SKCoverage.dat",
			"data/raise/reduce/setCovers/SKTime.dat",false);
		System.out.println("SK\n"+cover.toString());
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/RPCoverage.dat",
			"data/raise/reduce/setCovers/RPTime.dat",false);
		System.out.println("RP\n"+cover.toString());
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/JDCoverage.dat",
			"data/raise/reduce/setCovers/JDTime.dat",false);
		System.out.println("JD\n"+cover.toString());
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/RMCoverage.dat",
		"data/raise/reduce/setCovers/RMTime.dat",false);
		System.out.println("RM\n"+cover.toString());
		
		cover = SetCover.constructSetCoverFromCoverageAndTime("data/raise/reduce/setCovers/TMCoverage.dat",
		"data/raise/reduce/setCovers/TMTime.dat",false);
		System.out.println("TM\n"+cover.toString());
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
		
		//"alg" "metric" "reduceTime" "priorTime" "CE" "RFFS" "RFFT" "origExecTime" "redExecTime" "totalSize" "redSize" "app"
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