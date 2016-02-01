import java.io.*;
import java.util.*;

class Classifier{
    public static void main(String[] args){
	
	Hashtable<String, Word> words = new Hashtable<String, Word>();
	
        String trainingFile = "input2.txt"; //file which contains training data
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
			if(reviewType == 1){
			    //add one to positive review count for word 
			}else{
			    //add one to negative review count for word
			}
		    }else{ // word has not yet been added
			//add object to hashTable with 1 instance of positive or negative review
		    }
		}

		System.out.println(reviewType);
		System.out.println(review);
		//TODO: Deal with each review
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
