import java.io.*;
import java.util.*;

class Classifier{
    public static void main(String[] args){
	
	Hashtable<String, Word> words = new Hashtable<String, Word>();
	
        String trainingFile = "input.txt"; //file which contains training data
        String line = null; //contains one line at a time
	
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
		for(String newWord: line.split(" ")){
		    if(words.containsKey(newWord)){ //word has already been added
			//TODO: fix this
			//this adds one every time... not once per review
			if(reviewType == 1){
			    words.get(newWord).positiveCount ++;
			}else{
			    words.get(newWord).negativeCount ++;
			}
		    }else{ // word has not yet been added to hashtable
			//creates new word object and adds it to words hashtable
			Word tmpWord = new Word(newWord, reviewType); 
			words.put(newWord, tmpWord);
		    }
		}
		
		System.out.println(words.toString());
	
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

    }
}
