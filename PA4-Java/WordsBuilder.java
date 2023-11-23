// Name: Betul Dincer
// USC NetID:  1816821532
// CS 455 PA4
// Fall 2023

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;

/**
   This class is used to use create all the possible words from the given rack and checks from the Dictionary. It also prints the words with corresponding scores.
 */

public class WordsBuilder{
   
   private String word; 
   private ArrayList <ArrayList <String>> anagList;  
   private String filename; 
   private int counter; 
   private List<WordScore> wordScores ; 
   
   /*
      Representation invariant:
      -- word is given rack. It is non-empty, word.length()>0.
      -- anagList holds all the anagrams of each subset of the rack.
      -- filename is th dictionary name.
      -- counter is for how many words can be established.
      -- counter >= 0
      -- wordScores holds all the WordScore objects.
   */
   
   /**
      Initializes the variables. 
      Pre: word.length()>0
           filename is Dictionary name
    */ 
   public WordsBuilder(String word, String filename){
      this.word = word;
      this.filename = filename;
      counter = 0;
      wordScores = new ArrayList<WordScore>();
      anagList= new ArrayList<ArrayList <String>>() ;
   }
   /**
      This function creates all the word and score pairs with given rack and searching it from anagram dictionary.
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */ 
   public void buildWords() throws FileNotFoundException, IllegalDictionaryException{      
      Rack rack = new Rack(word);
      ArrayList<String> subsets = rack.subsetsRack();
      AnagramDictionary anagramDictionary = new AnagramDictionary(filename);     
      for(int i=0;i<subsets.size();i++){
         anagList.add(anagramDictionary.getAnagramsOf(subsets.get(i))); //For all the subsets of rack, get the anagrams and add to set.
      }
      Iterator<ArrayList<String>> value = anagList.iterator();
      while(value.hasNext()){
         ArrayList <String> elem = value.next();
         if(elem!=null){
            Iterator<String> listIterator = elem.iterator();
            while (listIterator.hasNext()) {
               String currentString = listIterator.next();
               counter ++;
               ScoreTable s = new ScoreTable(currentString) ;
               //Create the WordScore object from the subset of rack string and score
               WordScore wordScore  = new WordScore(currentString,s.getWordScore()); 
               wordScores.add(wordScore);
            }
         }
      }
      Collections.sort(wordScores);
      printWords();  
   }
   
   /**
      Prints each words and corresponding scores from the wordScore list.    
    */ 
   private void printWords(){
      System.out.println("We can make "+ counter+" words from \""+ word+ "\"" );
      if(counter != 0){
         System.out.println("All of the words with their scores (sorted by score):");
         for(WordScore wordScore : wordScores){
            System.out.println(wordScore.getScore()+": "+ wordScore.getWord());
         }      
      } 
   }
}


 
 

    



