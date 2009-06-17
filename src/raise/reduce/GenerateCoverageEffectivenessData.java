package raise.reduce;

import java.util.ArrayList;
import java.util.Iterator;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/*
 * @author Gavilan T. Steinman 03/12/2008
 */
public class GenerateCoverageEffectivenessData {
       private SetCover s;
       private ArrayList<SingleTestSubset> testSubSets;
       private ArrayList<RequirementSubset> requirementSubSets;
       public GenerateCoverageEffectivenessData(SetCover s){
               this.s = s;
               this.testSubSets = new ArrayList<SingleTestSubset>();
               this.requirementSubSets = new ArrayList<RequirementSubset>();
               this.identifyTestSubsets();
               this.identifyRequirementSubsets();
       }
       private void identifyTestSubsets(){
               Iterator<SingleTestSubset> subsets = s.getTestSubsets().iterator();
               SingleTestSubset subset;
               while(subsets.hasNext()){
                       subset = subsets.next();
                       this.testSubSets.add(subset);
               }
       }
       private void identifyRequirementSubsets(){
               Iterator<RequirementSubset> subsets = s.getRequirementSubsetUniverse().iterator();
               RequirementSubset subset;
               while(subsets.hasNext()){
                       subset = subsets.next();
                       this.requirementSubSets.add(subset);
               }
       }
       public void saveMatrixData(String fileName){
               int[][] matrix = new int[this.testSubSets.size()+1][this.requirementSubSets.size()+1];
               PrintStream out;
               for(int i=0;i<this.testSubSets.size();i++){
                       for(int j=0;j<this.requirementSubSets.size();j++){
                               if(this.requirementSubSets.get(j)
                                               .containsSingleTest(this.testSubSets
                                                               .get(i).getTest())){
                                       matrix[i][j] = 1;
                                       matrix[i][matrix[0].length-1]++;
                                       matrix[matrix.length-1][j]++;
                               }
                       }
               }
               try {
                       out = new PrintStream(fileName);
                       for(int j=0;j<matrix[0].length;j++){
                               for(int i=0;i<matrix.length;i++){
                                       out.print(matrix[i][j]);
                                       if(i != matrix.length-1)out.print(" ");
                               }
                               out.println();
                       }
                       out.close();
               } catch (FileNotFoundException e) {
                       e.printStackTrace();
               }
       }
       public void saveTimingData(String fileName){
               PrintStream out;
               try {
                       out = new PrintStream(fileName);
                       out.println(
                                       "NameTTD"
                                       +"\t"+"Time"
                       );
                       for(int i=0;i<this.testSubSets.size();i++){
                               out.println(
                                               (this.testSubSets.get(i).getTest().getIndex())
                                               +"\t"+(this.testSubSets.get(i).getTest().getCost())
                               );
                       }
                       out.close();
               } catch (FileNotFoundException e) {
                       e.printStackTrace();
               }
       }
       
       /**
        * This method saves the setcover coverage data in the format
        * that is used by the R CE calculator.
        *
        * @author Adam M. Smith
        */
       
        public void saveCoverageData(String fileName){
               PrintStream out;
               try {
                        out = new PrintStream(fileName);
                        out.println("NameCD\tRequirements");
               			Iterator testSubsetsIterator = this.testSubSets.iterator();
               		  	while(testSubsetsIterator.hasNext())
               		  	{
               		  		SingleTestSubset currentSTS = (SingleTestSubset) testSubsetsIterator.next();
               		  		int currentSTSIndex = currentSTS.getTest().getIndex();
               		  		Iterator currentSTSRSIterator = currentSTS.getRequirementSubsetSet().iterator();
               		  		
               		  		while(currentSTSRSIterator.hasNext())
               		  		{
               		  			RequirementSubset currentRS = (RequirementSubset) currentSTSRSIterator.next();
               		  			int RSindex=currentRS.getIndex()+1;
               		  			out.println(currentSTSIndex +"\t"+RSindex);
               		  		}
               		  	}
               		   
               		   out.close();
               } catch (FileNotFoundException e) {
                       e.printStackTrace();
               }
       }
       
       public static void main(String args[]){
       
       //  String setCoverFile = args[0];
       //  String coverageFile = args[1];
       //  String timingFile = args[2];
         
    	// AD
           String coverageFile = "data/raise/reduce/setCovers/ADCoverage.dat";
           String timingFile = "data/raise/reduce/setCovers/ADTime.dat";
           String matrixCoverage = "data/raise/reduce/setCovers/ADMatrix.dat";
           
           SetCover sc;
           
           sc = SetCover.constructSetCoverFromCoverageAndTime(coverageFile, timingFile,false); 
           GenerateCoverageEffectivenessData g = new GenerateCoverageEffectivenessData(sc);
           //g.saveTimingData(timingFile);
          // g.saveCoverageData(coverageFile);
           g.saveMatrixData(matrixCoverage);
           
    	   
    	   /*
         // RP
         String setCoverFile = "data/diatoms/reduce/xmlSetCovers/RP-setCover.xml";
         //String coverageFile = "data/diatoms/reduce/xmlSetCovers/RPCoverage.dat";
         //String timingFile = "data/diatoms/reduce/xmlSetCovers/RPTime.dat";
         String matrixCoverage = "data/diatoms/reduce/xmlSetCovers/RPMatrix.dat";
         
         SetCover sc;
         
         sc = SetCover.constructSetCoverFromXML(setCoverFile);
         
         GenerateCoverageEffectivenessData g = new GenerateCoverageEffectivenessData(sc);
         //g.saveTimingData(timingFile);
        // g.saveCoverageData(coverageFile);
         g.saveMatrixData(matrixCoverage);
         
         // LF
         setCoverFile = "data/diatoms/reduce/xmlSetCovers/LF-setCover.xml";
         //String coverageFile = "data/diatoms/reduce/xmlSetCovers/RPCoverage.dat";
         //String timingFile = "data/diatoms/reduce/xmlSetCovers/RPTime.dat";
         matrixCoverage = "data/diatoms/reduce/xmlSetCovers/LFMatrix.dat";
      
         
         sc = SetCover.constructSetCoverFromXML(setCoverFile);
         
         g = new GenerateCoverageEffectivenessData(sc);
         //g.saveTimingData(timingFile);
        // g.saveCoverageData(coverageFile);
         g.saveMatrixData(matrixCoverage);
         
         // RM
         setCoverFile = "data/diatoms/reduce/xmlSetCovers/RM-setCover.xml";
         //String coverageFile = "data/diatoms/reduce/xmlSetCovers/RPCoverage.dat";
         //String timingFile = "data/diatoms/reduce/xmlSetCovers/RPTime.dat";
         matrixCoverage = "data/diatoms/reduce/xmlSetCovers/RMMatrix.dat";
           
         sc = SetCover.constructSetCoverFromXML(setCoverFile);
         
         g = new GenerateCoverageEffectivenessData(sc);
         //g.saveTimingData(timingFile);
        // g.saveCoverageData(coverageFile);
         g.saveMatrixData(matrixCoverage);
         
         // SK
         setCoverFile = "data/diatoms/reduce/xmlSetCovers/SK-setCover.xml";
         //String coverageFile = "data/diatoms/reduce/xmlSetCovers/RPCoverage.dat";
         //String timingFile = "data/diatoms/reduce/xmlSetCovers/RPTime.dat";
         matrixCoverage = "data/diatoms/reduce/xmlSetCovers/SKMatrix.dat";
         
         sc = SetCover.constructSetCoverFromXML(setCoverFile);
         
         g = new GenerateCoverageEffectivenessData(sc);
         //g.saveTimingData(timingFile);
        // g.saveCoverageData(coverageFile);
         g.saveMatrixData(matrixCoverage);
         
         // TM
         setCoverFile = "data/diatoms/reduce/xmlSetCovers/TM-setCover.xml";
         //String coverageFile = "data/diatoms/reduce/xmlSetCovers/RPCoverage.dat";
         //String timingFile = "data/diatoms/reduce/xmlSetCovers/RPTime.dat";
         matrixCoverage = "data/diatoms/reduce/xmlSetCovers/TMMatrix.dat";
         
         sc = SetCover.constructSetCoverFromXML(setCoverFile);
         
         g = new GenerateCoverageEffectivenessData(sc);
         //g.saveTimingData(timingFile);
        // g.saveCoverageData(coverageFile);
         g.saveMatrixData(matrixCoverage);
         
         // DS
         setCoverFile = "data/diatoms/reduce/xmlSetCovers/DS-setCover.xml";
         //String coverageFile = "data/diatoms/reduce/xmlSetCovers/RPCoverage.dat";
         //String timingFile = "data/diatoms/reduce/xmlSetCovers/RPTime.dat";
         matrixCoverage = "data/diatoms/reduce/xmlSetCovers/DSMatrix.dat";
         
         sc = SetCover.constructSetCoverFromXML(setCoverFile);
         
         g = new GenerateCoverageEffectivenessData(sc);
         //g.saveTimingData(timingFile);
        // g.saveCoverageData(coverageFile);
         g.saveMatrixData(matrixCoverage);
        
        
        // GB
         setCoverFile = "data/diatoms/reduce/xmlSetCovers/GB-setCover.xml";
         //String coverageFile = "data/diatoms/reduce/xmlSetCovers/RPCoverage.dat";
         //String timingFile = "data/diatoms/reduce/xmlSetCovers/RPTime.dat";
         matrixCoverage = "data/diatoms/reduce/xmlSetCovers/GBMatrix.dat";
         
         sc = SetCover.constructSetCoverFromXML(setCoverFile);
         
         g = new GenerateCoverageEffectivenessData(sc);
         //g.saveTimingData(timingFile);
        // g.saveCoverageData(coverageFile);
         g.saveMatrixData(matrixCoverage);
         
         // JD
         setCoverFile = "data/diatoms/reduce/xmlSetCovers/JD-setCover.xml";
         //String coverageFile = "data/diatoms/reduce/xmlSetCovers/RPCoverage.dat";
         //String timingFile = "data/diatoms/reduce/xmlSetCovers/RPTime.dat";
         matrixCoverage = "data/diatoms/reduce/xmlSetCovers/JDMatrix.dat";
                 
         sc = SetCover.constructSetCoverFromXML(setCoverFile);
         
         g = new GenerateCoverageEffectivenessData(sc);
         //g.saveTimingData(timingFile);
        // g.saveCoverageData(coverageFile);
         g.saveMatrixData(matrixCoverage);
         */
		}
}

