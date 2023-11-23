// Name: Betul Dincer
// USC NetID:  1816821532
// CS 455 PA4
// Fall 2023


/**
   The word and its score. Implements comparable interface for sorting.
 */
public class WordScore implements Comparable<WordScore>{
    private String word;
    private int score;

    /*
      Representation invariant:
      -- word is non-empty, word.length()>0
      -- word represents all the subsets of the rack that is in dictionary
      -- score is non-zero value.
   */
    /**
      Initialize the WordScore with given score and word.
      PRE: word.length() > 0
           score > 0
    */
    public WordScore(String word, int score) {
        this.word = word;
        this.score = score;
    }
    /**
      Getter for the word.
      @return the word.
    */
    public String getWord() {
        return word;
    }
    /**
      Getter for the score.
      @return the score.
    */
    public int getScore() {
        return score;
    }
    /**
      Implements the sorting according to decreasing score. If the scores are equal, it uses natural ordering of string.
      @return the comparison.
    */  
    public int compareTo(WordScore other) {
         
        int scoreDiff = other.getScore() - this.getScore();
        if(scoreDiff!=0){
           return scoreDiff;
        }
        return  word.compareTo(other.getWord());
    }
}