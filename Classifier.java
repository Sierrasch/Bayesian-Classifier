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
		System.out.println(line.charAt(0));
		System.out.println(line.charAt(2));
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
