// Name: Betul Dincer
// USC NetID:  1816821532
// CS 455 PA4
// Fall 2023

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Set; 
import java.util.HashSet;  
import java.util.Scanner;

/**
   This class checks if the dictionary has duplicated words. If so, throws an exception.
 */

public class Dictionary{
    
   private String filename;
   private Set <String> words;
   
   /*
      Representation invariant:
      -- filename represents the Dictionary name inputed the system.
      -- words set hold the words from the Dictionary
      -- words cannot contain multiple values from dictionary
   */
   
   
   /**
      Creates HashSet to hold the words in the dictionary.
      PRE: filename should be the Dictionary file name.
      @param filename  the input dictionary
    */
   public Dictionary(String filename){
      this.filename = filename; 
      words = new HashSet <String>();
   }   
    
   
   /**
      Checks the duplicates in the given dictionary and throws exception.  
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public void isDuplicated() throws FileNotFoundException, IllegalDictionaryException{        
      File file = new File(filename);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNext()) {
         String word = scanner.next();
         if(words.contains(word)){             
            throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word: "+ word );
         }
         words.add(word);
      }   
   }
       
 }