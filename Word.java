class Word{
    String word; 
    int positiveCount; //stores number of positive documents which contain word 
    int negativeCount; //stores number of negative documents which contain word
    
    
    public String toString(){
	String result = "";
	result += "(" + word + "," + positiveCount + "," + negativeCount + ")";
	return result;
    }
}
