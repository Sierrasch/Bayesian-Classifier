class Word{
    int positiveCount; //stores number of positive documents which contain word 
    int negativeCount; //stores number of negative documents which contain word
    double positiveProb; //stores P(word occurs | review is positive)
    double negativeProb; //stores P(word occurs | review is negative)

    
    Word(int posOrNeg){
	if(posOrNeg == 1){
	    positiveCount = 1;
	    negativeCount = 0;
	}else{
	    positiveCount = 0;
	    negativeCount = 1;
	}
	positiveProb = -1;
	negativeProb = -1;
    }

    Word(){
	positiveCount = 0;
	negativeCount = 0;
	positiveProb = -1;
	negativeProb = -1;
}

    public String toString(){
	String result = "";
	result += "(" + positiveCount + "," + negativeCount + "," + positiveProb + "," + negativeProb + ")";
	return result;
    }
}
