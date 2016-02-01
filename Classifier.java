import java.io.*;

class Classifier{
    public static void main(String[] args){
  
        String trainingFile = "input.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(trainingFile);

            // wraps FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
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
