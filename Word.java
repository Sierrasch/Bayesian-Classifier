class Word{
    String word; 
    int positiveCount; //stores number of positive documents which contain word 
    int negativeCount; //stores number of negative documents which contain word
    
    Word(String newWord, int posOrNeg){
	word = newWord;
	if(posOrNeg == 1){
	    positiveCount = 1;
	    negativeCount = 0;
	}else{
	    positiveCount = 0;
	    negativeCount = 1;
	}
    }

    Word(){
	word = "";
	positiveCount = 0;
	negativeCount = 0;
    }

    public String toString(){
	String result = "";
	result += "(" + word + "," + positiveCount + "," + negativeCount + ")";
	return result;
    }
}
