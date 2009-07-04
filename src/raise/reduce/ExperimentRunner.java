package raise.reduce;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

// I would really like to make a class that can be more versatile for
// running experiments, but for now this will do. 

public class ExperimentRunner 
{
	
	public static void main(String[] args) throws IOException
	{
		String[] metrics = {"coverage","time","ratio"};
		String[] techniques = {"GRD","2OPT","HGS","DGR"};
		
	
		//runAlgExperiment("data/raise/reduce/setCovers/ADMatrix.dat","data/raise/reduce/setCovers/ADTime.dat",
		//					"results/raise/reduce/IST/ADResults.dat", metrics, techniques);
		
		/*************************  ALGORITHMS *********************/
		/*
		runAlgExperiment("data/raise/reduce/setCovers/ADMatrix.dat","data/raise/reduce/setCovers/ADTime.dat",
				"results/raise/reduce/IST/ADResults.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/LFMatrix.dat","data/raise/reduce/setCovers/LFTime.dat",
				"results/raise/reduce/IST/LFResults.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/RPMatrix.dat","data/raise/reduce/setCovers/RPTime.dat",
				"results/raise/reduce/IST/RPResults.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/DSMatrix.dat","data/raise/reduce/setCovers/DSTime.dat",
				"results/raise/reduce/IST/DSResults.dat", metrics,techniques);
		runAlgExperiment("data/raise/reduce/setCovers/GBMatrix.dat","data/raise/reduce/setCovers/GBTime.dat",
				"results/raise/reduce/IST/GBResults.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/JDMatrix.dat","data/raise/reduce/setCovers/JDTime.dat",
				"results/raise/reduce/IST/JDResults.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/RMMatrix.dat","data/raise/reduce/setCovers/RMTime.dat",
				"results/raise/reduce/IST/RMResults.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/setCovers/SKMatrix.dat","data/raise/reduce/setCovers/SKTime.dat",
				"results/raise/reduce/IST/SKResults.dat", metrics, techniques);
				*/
		//runAlgExperiment("data/raise/reduce/setCovers/TMMatrix.dat","data/raise/reduce/setCovers/TMTime.dat",
		//		"results/raise/reduce/IST/TMResults.dat", metrics, techniques);
		
		// GREEDY FOOLING
		/* runAlgExperiment("data/raise/reduce/gf/GF_TRUE_10_Coverage.dat","data/raise/reduce/gf/GF_TRUE_10_Timing.dat",
				"results/raise/reduce/gf/GF_TRUE_10_results.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/gf/GF_FALSE_10_Coverage.dat","data/raise/reduce/gf/GF_FALSE_10_Timing.dat",
				"results/raise/reduce/gf/GF_FALSE_10_results.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/gf/GF_TRUE_20_Coverage.dat","data/raise/reduce/gf/GF_TRUE_20_Timing.dat",
				"results/raise/reduce/gf/GF_TRUE_20_results.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/gf/GF_FALSE_20_Coverage.dat","data/raise/reduce/gf/GF_FALSE_20_Timing.dat",
				"results/raise/reduce/gf/GF_FALSE_20_results.dat", metrics, techniques);
		*/
		runAlgExperiment("data/raise/reduce/gf/GF_TRUE_30_Coverage.dat","data/raise/reduce/gf/GF_TRUE_30_Timing.dat",
				"results/raise/reduce/gf/GF_TRUE_30_results.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/gf/GF_FALSE_30_Coverage.dat","data/raise/reduce/gf/GF_FALSE_30_Timing.dat",
				"results/raise/reduce/gf/GF_FALSE_30_results.dat", metrics, techniques);
		
		runAlgExperiment("data/raise/reduce/gf/GF_TRUE_40_Coverage.dat","data/raise/reduce/gf/GF_TRUE_40_Timing.dat",
				"results/raise/reduce/gf/GF_TRUE_40_results.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/gf/GF_FALSE_40_Coverage.dat","data/raise/reduce/gf/GF_FALSE_40_Timing.dat",
				"results/raise/reduce/gf/GF_FALSE_40_results.dat", metrics, techniques);
		
		runAlgExperiment("data/raise/reduce/gf/GF_TRUE_50_Coverage.dat","data/raise/reduce/gf/GF_TRUE_50_Timing.dat",
				"results/raise/reduce/gf/GF_TRUE_50_results.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/gf/GF_FALSE_50_Coverage.dat","data/raise/reduce/gf/GF_FALSE_50_Timing.dat",
				"results/raise/reduce/gf/GF_FALSE_50_results.dat", metrics, techniques);
		
		runAlgExperiment("data/raise/reduce/gf/GF_TRUE_60_Coverage.dat","data/raise/reduce/gf/GF_TRUE_60_Timing.dat",
				"results/raise/reduce/gf/GF_TRUE_60_results.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/gf/GF_FALSE_60_Coverage.dat","data/raise/reduce/gf/GF_FALSE_60_Timing.dat",
				"results/raise/reduce/gf/GF_FALSE_60_results.dat", metrics, techniques);
		
		runAlgExperiment("data/raise/reduce/gf/GF_TRUE_70_Coverage.dat","data/raise/reduce/gf/GF_TRUE_70_Timing.dat",
				"results/raise/reduce/gf/GF_TRUE_70_results.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/gf/GF_FALSE_70_Coverage.dat","data/raise/reduce/gf/GF_FALSE_70_Timing.dat",
				"results/raise/reduce/gf/GF_FALSE_70_results.dat", metrics, techniques);
		
		runAlgExperiment("data/raise/reduce/gf/GF_TRUE_80_Coverage.dat","data/raise/reduce/gf/GF_TRUE_80_Timing.dat",
				"results/raise/reduce/gf/GF_TRUE_80_results.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/gf/GF_FALSE_80_Coverage.dat","data/raise/reduce/gf/GF_FALSE_80_Timing.dat",
				"results/raise/reduce/gf/GF_FALSE_80_results.dat", metrics, techniques);
		/*
		runAlgExperiment("data/raise/reduce/gf/GF_TRUE_90_Coverage.dat","data/raise/reduce/gf/GF_TRUE_90_Timing.dat",
				"results/raise/reduce/gf/GF_TRUE_90_results.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/gf/GF_FALSE_90_Coverage.dat","data/raise/reduce/gf/GF_FALSE_90_Timing.dat",
				"results/raise/reduce/gf/GF_FALSE_90_results.dat", metrics, techniques);
		*/
		runAlgExperiment("data/raise/reduce/gf/GF_TRUE_100_Coverage.dat","data/raise/reduce/gf/GF_TRUE_100_Timing.dat",
				"results/raise/reduce/gf/GF_TRUE_100_results.dat", metrics, techniques);
		runAlgExperiment("data/raise/reduce/gf/GF_FALSE_100_Coverage.dat","data/raise/reduce/gf/GF_FALSE_100_Timing.dat",
				"results/raise/reduce/gf/GF_FALSE_100_results.dat", metrics, techniques);
		
		// *******************  RANDOM ************************
	
	/*
		runRandomExperiment("data/raise/reduce/setCovers/ADMatrix.dat","data/raise/reduce/setCovers/ADTime.dat",
				"results/raise/reduce/IST/AD5000RandomResults.dat",5000);
		runRandomExperiment("data/raise/reduce/setCovers/LFMatrix.dat","data/raise/reduce/setCovers/LFTime.dat",
				"results/raise/reduce/IST/LF5000RandomResults.dat",5000);
		runRandomExperiment("data/raise/reduce/setCovers/RPMatrix.dat","data/raise/reduce/setCovers/RPTime.dat",
				"results/raise/reduce/IST/RP5000RandomResults.dat",5000);
		runRandomExperiment("data/raise/reduce/setCovers/DSMatrix.dat","data/raise/reduce/setCovers/DSTime.dat",
				"results/raise/reduce/IST/DS5000RandomResults.dat",5000);
		runRandomExperiment("data/raise/reduce/setCovers/GBMatrix.dat","data/raise/reduce/setCovers/GBTime.dat",
				"results/raise/reduce/IST/GB5000RandomResults.dat",5000);
		runRandomExperiment("data/raise/reduce/setCovers/JDMatrix.dat","data/raise/reduce/setCovers/JDTime.dat",
				"results/raise/reduce/IST/JD5000RandomResults.dat",5000);
		runRandomExperiment("data/raise/reduce/setCovers/RMMatrix.dat","data/raise/reduce/setCovers/RMTime.dat",
				"results/raise/reduce/IST/RM5000RandomResults.dat",5000);
		runRandomExperiment("data/raise/reduce/setCovers/SKMatrix.dat","data/raise/reduce/setCovers/SKTime.dat",
				"results/raise/reduce/IST/SK5000RandomResults.dat",5000);
		runRandomExperiment("data/raise/reduce/setCovers/TMMatrix.dat","data/raise/reduce/setCovers/TMTime.dat",
				"results/raise/reduce/IST/TM5000RandomResults.dat",5000);
		
		System.out.println("Done: Random");
*/
		
		/*
		produceReduced("data/raise/reduce/setCovers/ADMatrix.dat","data/raise/reduce/setCovers/ADTime.dat",
				"results/raise/reduce/IST/ADReducedSet.dat", metrics, techniques);
		produceReduced("data/raise/reduce/setCovers/LFMatrix.dat","data/raise/reduce/setCovers/LFTime.dat",
				"results/raise/reduce/IST/LFReducedSet.dat", metrics, techniques);
		produceReduced("data/raise/reduce/setCovers/RPMatrix.dat","data/raise/reduce/setCovers/RPTime.dat",
				"results/raise/reduce/IST/RPReducedSet.dat", metrics, techniques);
		produceReduced("data/raise/reduce/setCovers/DSMatrix.dat","data/raise/reduce/setCovers/DSTime.dat",
				"results/raise/reduce/IST/DSReducedSet.dat",metrics,techniques);
		produceReduced("data/raise/reduce/setCovers/GBMatrix.dat","data/raise/reduce/setCovers/GBTime.dat",
				"results/raise/reduce/IST/GBReducedSet.dat", metrics, techniques);
		produceReduced("data/raise/reduce/setCovers/JDMatrix.dat","data/raise/reduce/setCovers/JDTime.dat",
				"results/raise/reduce/IST/JDReducedSet.dat", metrics, techniques);
		produceReduced("data/raise/reduce/setCovers/RMMatrix.dat","data/raise/reduce/setCovers/RMTime.dat",
				"results/raise/reduce/IST/RMReducedSet.dat", metrics, techniques);
		produceReduced("data/raise/reduce/setCovers/SKMatrix.dat","data/raise/reduce/setCovers/SKTime.dat",
				"results/raise/reduce/IST/SKReducedSet.dat", metrics, techniques);
		produceReduced("data/raise/reduce/setCovers/TMMatrix.dat","data/raise/reduce/setCovers/TMTime.dat",
				"results/raise/reduce/IST/TMReducedSet.dat", metrics, techniques);
		*/
		/*
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
	*/
	}		
	
	public static void produceReduced(String coverageFile, String timeFile, String resultsFile, 
			String[] metrics, String[] techniques)
	{
		
		Scanner fScanner = new Scanner(resultsFile).useDelimiter("/");
		String fileName = "";
		while(fScanner.hasNext())
			fileName = fScanner.next();
		
		String app = fileName.substring(0, 2);
	 
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
		out.println("alg\tmetric\tapp\torder");
		
		SetCover cover;
		
		for(String technique : techniques)
		{
			for(String metric : metrics)
			{	
				
				System.out.println("Test Suite: " + coverageFile + "\ntechnique: "+technique
						+"\nmetric: "+metric);
				
				System.out.println("constructing object...");
				//cover = SetCover.constructSetCoverFromCoverageAndTime(coverageFile, timeFile,false);
				cover = SetCover.constructSetCoverFromMatrix(coverageFile, timeFile);
				
				System.out.println("reducing...");
				if(technique.equals("2OPT"))
				{
					cover.reduceUsing2Optimal(metric);
				}
				else if(technique.equals("GRD"))
				{
					cover.reduceUsingGreedy(metric);
				}
				else if(technique.equals("DGR"))
				{
					cover.reduceUsingDelayedGreedy(metric);
				}
				else if(technique.equals("HGS"))
				{
					cover.reduceUsingHarroldGuptaSoffa(metric);
				}
				
				out.println(technique+"\t"+metric+"\t"+ app+"\t"+cover.getCoveringTestSetStringNoAlter(","));
				System.out.println("completed current iteration.");
			}
		}
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
	
		Scanner fScanner = new Scanner(resultsFile).useDelimiter("/");
		String fileName = "";
		while(fScanner.hasNext())
			fileName = fScanner.next();
		
		String app = fileName.substring(0, 2);
	
		//"alg" "metric" "reduceTime" "priorTime" "CE" "RFFS" "RFFT" "origExecTime" "redExecTime" "totalSize" "redSize" "app"
		out.println("alg\tpriorTime\tCE\tapp");
	
		SetCover cover;
		
		double start = -1;
		double stop = -1;
	
		double priorTime = 0;
		double CE = -1;

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
			CE = cover.getCE(order);
			
			out.println("RND"+"\t"+priorTime + "\t"+CE+"\t"+ app);
		}
	}
	
	public static void runAlgExperiment(String coverageFile, String timeFile, String resultsFile, 
			String[] metrics, String[] techniques) throws IOException
	{
	
		String aggregateResultsFile = "results/raise/reduce/IST/AggregateAlgResults.dat";
		
		PrintStream out=null;
		FileWriter aggOut = null;
		
		try
		{
			out = new PrintStream(resultsFile);
			aggOut = new FileWriter(aggregateResultsFile, true);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		/*
		Scanner fScanner = new Scanner(resultsFile).useDelimiter("/");
		String fileName = "";
		while(fScanner.hasNext())
			fileName = fScanner.next();
		
		String app = fileName.substring(0, 2);
		*
		*/
		
		String app = resultsFile;
		
		//"alg" "metric" "reduceTime" "priorTime" "CE" "RFFS" "RFFT" "origExecTime" "redExecTime" "totalSize" "redSize" "app"
		//out.println("alg\tmetric\treduceTime\tpriorTime\tCE\tRFFS\tRFFT\torigExecTime\tredExecTime\ttotalSize\tredSize\tapp");
		out.println("alg\tmetric\treduceTime\tpriorTime\tCE\tRFFS\tRFFT\torigExecTime\tredExecTime\ttotalSize\tredSize\tapp\tprioritizedSet, reducedSet\n");
		SetCover cover;
		
		double start = -1;
		double stop = -1;
		
		double redTime, priorTime, CE, RFFS, RFFT, origExec, redExec;
		double totalSize, redSize;
		
		String reducedSet;
		String prioritizedSet;
		
		for(String technique : techniques)
		{
			for(String metric : metrics)
			{	
				
				//System.out.println("Test Suite: " + coverageFile + "\ntechnique: "+technique
				//		+"\nmetric: "+metric);
				
				//System.out.println("constructing object...");
				cover = SetCover.constructSetCoverFromCoverageAndTime(coverageFile, timeFile,false);
				//cover = SetCover.constructSetCoverFromMatrix(coverageFile, timeFile);
				//System.out.println("calculating original execution...");
				origExec = SetCover.getExecutionTimeSingleTestSubsetList(cover.getTestSubsets());
				//System.out.println("calculating original size...");
				totalSize = cover.getTestSubsets().size();
				
				//System.out.println("reducing...");
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
				//System.out.println("calculating runtime...");
				redTime = stop-start;
		
				reducedSet = cover.getCoveringTestSetStringNoAlter(",");
				
				//System.out.println("calculating size of reduced suite...");
				redSize = cover.getCoverPickSets().size();
				//System.out.println("calculating execution time of reduce suite...");
				redExec = SetCover.getExecutionTimeSingleTestList(cover.getCoverPickSets());
				
				//System.out.println("calculating RFFS and RFFT...");
				RFFS = (totalSize-redSize)/totalSize;
				RFFT = (origExec-redExec)/origExec;
				
				//System.out.println("reconstructing object...");
				
				cover = SetCover.constructSetCoverFromCoverageAndTime(coverageFile, timeFile,false);
				//cover = SetCover.constructSetCoverFromMatrix(coverageFile, timeFile);
				
				//System.out.println("prioritizing...");
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
				
				//System.out.println("calculating prioritization time");
				priorTime = stop-start;
				
				prioritizedSet = cover.getPrioritizedSetStringNoAlter(",");				
				int[] order = cover.getPrioritizedOrderArray();
				
				//System.out.println("reconstructing object...");
				cover = SetCover.constructSetCoverFromCoverageAndTime(coverageFile,timeFile,false);
				//cover = SetCover.constructSetCoverFromMatrix(coverageFile, timeFile);

				//System.out.println("calculating coverage effectiveness");
				CE = cover.getCE(order);
			
		
				//System.out.println("writing to file...");
				//out.println(technique+"\t"+metric+"\t"+redTime+"\t"+priorTime + "\t"+CE+"\t"+RFFS+"\t"+RFFT+"\t"+origExec+"\t"+redExec+"\t"+totalSize+"\t"+redSize+"\t"+ app);
				out.println(technique+"\t"+metric+"\t"+redTime+"\t"+priorTime + "\t"+CE+"\t"+RFFS+"\t"+RFFT+"\t"+origExec+"\t"+redExec+"\t"+totalSize+"\t"+redSize+"\t"+ app + "\t" + prioritizedSet +"\t"+reducedSet+"\n");
				//aggOut.write(technique+"\t"+metric+"\t"+redTime+"\t"+priorTime + "\t"+CE+"\t"+RFFS+"\t"+RFFT+"\t"+origExec+"\t"+redExec+"\t"+totalSize+"\t"+redSize+"\t"+ app + "\t" + prioritizedSet +"\t"+reducedSet+"\n");
				System.out.println("completed current iteration.");
			}
		}
	}
}