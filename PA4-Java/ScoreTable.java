// Name: Betul Dincer
// USC NetID:  1816821532
// CS 455 PA4
// Fall 2023


/**
   The scores of the each words.
 */

public class ScoreTable{
    
   private String word;
   private int [] values;
   
   /*
      Representation invariant:
      -- word is non-empty, word.length()>0
      -- word represents all the subsets of the rack that is in dictionary
      -- values array hold the values of the individual letters.
      -- values contain non-zero ints.
   */
   
   /**
      Initializes the score table with given word. Convert to characters to lowercase for calculations.
      PRE: word must only consist of letters in the alphabet 
      @param word given string to calculate the score
    */ 
   public ScoreTable(String word){
      this.word = word;
      this.word = this.word.toLowerCase();
      values =  new int [26];
      setLetterScore();
   }
   
   /**
      Returning the score of the word according to its letters.
      @return the score of the word.
    */ 
   public int getWordScore(){        
      char[] c = word.toCharArray();
      int score = 0;
      for(int i=0; i<c.length; i++){
         score += getScoreLetter(c[i]);         
      }
      return score;        
   }
   /**
      Returning the score of the letter.
      PRE: c must be a letter in the english alphabet .
      @return the score of the letter.
    */  
   private int getScoreLetter(char c){       
      int ind = c - 'a';
      return values[ind];
   }
   /**
      Set the table of scores for each letter
    */  
   private void setLetterScore(){      
      values[0] = 1;   // A
      values[1] = 3;   // B
      values[2] = 3;   // C
      values[3] = 2;   // D
      values[4] = 1;   // E
      values[5] = 4;   // F
      values[6] = 2;   // G
      values[7] = 4;   // H
      values[8] = 1;   // I
      values[9] = 8;   // J
      values[10] = 5;  // K
      values[11] = 1;  // L
      values[12] = 3;  // M
      values[13] = 1;  // N
      values[14] = 1;  // O
      values[15] = 3;  // P
      values[16] = 10; // Q
      values[17] = 1;  // R
      values[18] = 1;  // S
      values[19] = 1;  // T
      values[20] = 1;  // U
      values[21] = 4;  // V
      values[22] = 4;  // W
      values[23] = 8;  // X
      values[24] = 4;  // Y
      values[25] = 10; // Z      
    }
    
    
 }