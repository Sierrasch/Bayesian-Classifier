import java.io.*;
import java.util.*;

class DecisionTreeClassifier{

    public static void main(String[] args){
	

	Hashtable<String, Word> words = new Hashtable<String, Word>();
	

	String trainingFile = "input.txt";
	String testingFile = "testing.txt";
	if(args.length >= 0)
	    trainingFile = args[0]; //file which contains training data
	if(args.length >= 1)    
	    testingFile = args[1]; // file which contains testing data
	

        String line = null; //contains one line at a time
	int positiveReviewCount = 0;
	int negativeReviewCount = 0;

	final long startTraining = System.currentTimeMillis();
	
	/* CREATE HASHTABLE OF ALL WORDS */
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(trainingFile);

            // wraps FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
	    
	    //handles each review one by one
            while((line = bufferedReader.readLine()) != null) {
		int reviewType = Integer.parseInt("" + line.charAt(0)); //contains sentiment of review
		String review = line.substring(2); //contains text of the review
		review = review.toLowerCase();
		review = review.replace("<br />", " <br/> ");
		review = review.replaceAll("[,.;!?*/:\"()\\]\\[<>&]", "");

		HashSet<String> uniqueWords = new HashSet<String>(); //used to check if word has already been added

		for(String newWord: review.split(" ")){
		    if(uniqueWords.add(newWord)){ //word has not been added this review
			if(words.containsKey(newWord)){ //word has already been added

			    if(reviewType == 1){
				words.get(newWord).positiveCount ++;
			    }else{
				words.get(newWord).negativeCount ++;
			    }
			}else{ // word has not yet been added to hashtable
			    //creates new word object and adds it to words hashtable
			    Word tmpWord = new Word(reviewType); 
			    words.put(newWord, tmpWord);
			}
		    }
		}
		if (reviewType == 0){
		    negativeReviewCount++;
		}else{
		    positiveReviewCount++;
		}
	       
            }   
	    bufferedReader.close();

        }
        catch(FileNotFoundException ex) {
            System.out.println(
			       "Unable to open file '" + 
			       trainingFile + "'");                
        }
        catch(IOException ex) {
            System.out.println(
			       "Error reading file '" 
			       + trainingFile + "'");
        }
	
	/*Populate hashtable with probability values*/

	Set<String> keys = words.keySet();
	for(String key: keys){
	    words.get(key).positiveProb = (double) words.get(key).positiveCount / positiveReviewCount;
	    words.get(key).negativeProb = (double) words.get(key).negativeCount / negativeReviewCount;
	}

	final long endTraining = System.currentTimeMillis();

	/*Classify the testing set*/

	double trainingResults = classifyData(trainingFile, words, false);
	double testingResults = classifyData(testingFile, words, true);

	final long endTesting = System.currentTimeMillis();

	int trainingTime = (int)((endTraining - startTraining)/ 1000);
	int testingTime = (int)((endTesting - endTraining) / 1000);

	System.out.println("" + trainingTime + " seconds (training)");
	System.out.println("" + testingTime + " seconds (labeling)");
	System.out.println("" + trainingResults + " (training)");
	System.out.println("" + testingResults + " (testing)");
    }

    public static double classifyData(String file, Hashtable<String, Word> words, boolean outputEachClassification){
	int correct = 0;
	int incorrect = 0;
	String line = null;
	double percentageCorrect = 0.0;

	try {
	    FileReader fileReader2 = 
		new FileReader(file);

	    BufferedReader bufferedReader2 = 
		new BufferedReader(fileReader2);
	    
	    //classifies each review one by one
	    while((line = bufferedReader2.readLine()) != null) {
		int reviewType = Integer.parseInt("" + line.charAt(0)); //contains sentiment of review
		String review = line.substring(2); //contains text of the review
		review = review.toLowerCase();
		review = review.replace("<br />", " <br/> ");
		review = review.replaceAll("[,.;!?*/:\"()\\]\\[<>&]", "");

		double positiveProbability = 0.0;
		double negativeProbability = 0.0;
		int myClassification = 0;

		HashSet<String> uniqueWords = new HashSet<String>(); //used to check if word has already been added

		for(String currentWord: review.split(" ")){
		    if(uniqueWords.add(currentWord)){ //word has not already been calculated for this review
			if(words.containsKey(currentWord)){
			    Word current = words.get(currentWord);
			    if(current.positiveProb != 0){
				positiveProbability += (double)Math.log(current.positiveProb);
			    }else{
				positiveProbability += -7;
			    }
			    if(current.negativeProb != 0){
				negativeProbability += (double)Math.log(current.negativeProb);
			    }else{
				negativeProbability += -7;
			    }
			}
		    }
		}

		if(positiveProbability > negativeProbability){
		    myClassification = 1;
		}else{
		    myClassification = 0;
		}
		if(outputEachClassification == true){
		    System.out.println("" + myClassification);
		}

		if(myClassification == reviewType){
		    correct++;
		}else{
		    incorrect++;
		}
		
	
	    }   
	    bufferedReader2.close();
	  	    
	    percentageCorrect = ((double)(correct) / (double)(incorrect + correct));
	    
	}
	catch(FileNotFoundException ex) {
	    System.out.println(
			       "Unable to open file '" + 
			       file + "'");                
	}
	catch(IOException ex) {
	    System.out.println(
			       "Error reading file '" 
			       + file + "'");
	}
	return percentageCorrect;

    }

}
