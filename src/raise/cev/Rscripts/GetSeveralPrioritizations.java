package raise.cev;

import raise.reduce.*;

import java.util.LinkedHashSet;
import java.util.Iterator;

public class GetSeveralPrioritizations
{
	public static void main(String[] args)
	{
		//Setup
		LinkedHashSet prioritizedSets;
		String matrix = "C:/Users/Adam/Documents/raise/data/raise/reduce/setCovers/RPMatrix.dat";
		String time = "C:/Users/Adam/Documents/raise/data/raise/reduce/setCovers/RPTime.dat";
		String outputBegin = "C:/Users/Adam/Documents/raise/results/raise/cev/";
		SetCover sc = new SetCover();
		
		/*************************************************************************************/
		// construct
		sc = SetCover.constructSetCoverFromMatrix(matrix, time);
		
		// get original order
		LinkedHashSet originalOrder = new LinkedHashSet();
		Iterator stsIterator = sc.getTestSubsets().iterator();
		
		while(stsIterator.hasNext())
			originalOrder.add((SingleTest) ((SingleTestSubset)stsIterator.next()).getTest());
				
		sc.saveNewInfo(originalOrder,(outputBegin+"RPOriginalMatrix.dat"), (outputBegin+"RPOriginalTime.dat"));
		
		GenerateCoverageEffectivenessData g = new GenerateCoverageEffectivenessData(sc);
		
		g.saveCoverageData(outputBegin+"RPRCoverage.dat");
		
		// prioritize
	//	sc.prioritizeUsingGreedy("ratio");
		//prioritizedSets = sc.getPrioritizedSets();
		//reconstruct
		//sc = SetCover.constructSetCoverFromMatrix(matrix, time);
		// save results
		//sc.saveNewInfo(prioritizedSets, (outputBegin+"AdamGRDRatioMatrix.dat"), (outputBegin+"AdamGRDRatioTime.dat"));
	
		///////////////////////////////////////////////////////////////////////////////////	
		/*
////////////////////////////////////////////////////////////////////////////////////
		// construct
		sc = SetCover.constructSetCoverFromMatrix(matrix, time);
		// prioritize
		sc.prioritizeUsingGreedy("time");
		prioritizedSets = sc.getPrioritizedSets();
		//reconstruct
		sc = SetCover.constructSetCoverFromMatrix(matrix, time);
		// save results
		sc.saveNewInfo(prioritizedSets, (outputBegin+"AdamGRDCostMatrix.dat"), 
										(outputBegin+"AdamGRDCostTime.dat"));
		
		///////////////////////////////////////////////////////////////////////////////////		
		
		///////////////////////////////////////////////////////////////////////////////////
		
		// construct
		sc = SetCover.constructSetCoverFromMatrix(matrix, time);
		// prioritize
		sc.prioritizeUsingGreedy("coverage");
		prioritizedSets = sc.getPrioritizedSets();
		//reconstruct
		sc = SetCover.constructSetCoverFromMatrix(matrix, time);
		// save results
		sc.saveNewInfo(prioritizedSets, (outputBegin+"AdamGRDCoverageMatrix.dat"), 
										(outputBegin+"AdamGRDCoverageTime.dat"));
		///////////////////////////////////////////////////////////////////////////////////		
		///////////////////////////////////////////////////////////////////////////////////		// construct
		sc = SetCover.constructSetCoverFromMatrix(matrix, time);
		// prioritize
		sc.prioritizeUsing2Optimal("ratio");
		prioritizedSets = sc.getPrioritizedSets();
		//reconstruct
		sc = SetCover.constructSetCoverFromMatrix(matrix, time);
		// save results
		sc.saveNewInfo(prioritizedSets, (outputBegin+"Adam2OPTRatioMatrix.dat"), 
										(outputBegin+"Adam2OPTRatioTime.dat"));
		///////////////////////////////////////////////////////////////////////////////////		
		///////////////////////////////////////////////////////////////////////////////////		// construct
		sc = SetCover.constructSetCoverFromMatrix(matrix, time);
		// prioritize
		sc.prioritizeUsing2Optimal("time");
		prioritizedSets = sc.getPrioritizedSets();
		//reconstruct
		sc = SetCover.constructSetCoverFromMatrix(matrix, time);
		// save results
		sc.saveNewInfo(prioritizedSets, (outputBegin+"Adam2OPTCostMatrix.dat"), 
										(outputBegin+"Adam2OPTCostTime.dat"));
		///////////////////////////////////////////////////////////////////////////////////		
		///////////////////////////////////////////////////////////////////////////////////		// construct
		sc = SetCover.constructSetCoverFromMatrix(matrix, time);
		// prioritize
		sc.prioritizeUsing2Optimal("coverage");
		prioritizedSets = sc.getPrioritizedSets();
		//reconstruct
		sc = SetCover.constructSetCoverFromMatrix(matrix, time);
		// save results
		sc.saveNewInfo(prioritizedSets, (outputBegin+"Adam2OTPCoverageMatrix.dat"), 
										(outputBegin+"Adam2OPTCoverageTime.dat"));
		///////////////////////////////////////////////////////////////////////////////////
	*/	
	}
}

