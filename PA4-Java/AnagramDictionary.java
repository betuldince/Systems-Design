// Name: Betul Dincer
// USC NetID:  1816821532
// CS 455 PA4
// Fall 2023

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;  
import java.util.Map;  
import java.util.Scanner;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
   
 */
public class AnagramDictionary {
   
   private Map<String, ArrayList<String>> anagMap;
   
   /*
      Representation invariant:
      -- anagMap contains all the words in the dictionary and their anagrams.
      -- anagMap stores words(keys) as sorted versions of them.
      -- 0 <= anagMap.size <= words in dictionary  
      -- Size of the values equal to dictionary size
   */

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName. 
      PRE: if filename exists, the file should contain words seperated by new lines
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,IllegalDictionaryException {
      anagMap = new HashMap<String, ArrayList<String>>();
      File f = new File(fileName);
      Scanner s = new Scanner(f);
      while(s.hasNext()){
         String word = s.next();
         addAnagMap(word);
      }                                                   

   }
   
   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {   
      String sortedW = sortedString(string);
      if(anagMap.containsKey(sortedW)){
         return anagMap.get(sortedW);
      }
      return null;
   }
   /**
      Sort the given string.
      @param s string to process
      @return a sorted version of s
    */   
   private String sortedString(String s){
      char[] arr = s.toCharArray();
      Arrays.sort(arr);
      String ret = new String(arr);
      return ret;
   }
   
   /**
      Adding all the anagrams of the given word to map.
      @param word string to find anagrams and add to map.
    */       
   private void addAnagMap(String word){
      String sortedWord = sortedString(word);
      if(anagMap.containsKey(sortedWord)){
         anagMap.get(sortedWord).add(word);         
      }else{
         ArrayList<String> anagrams = new  ArrayList<String> ();
         anagrams.add(word);
         anagMap.put(sortedWord, anagrams);         
      }     
   }
   
   
}
