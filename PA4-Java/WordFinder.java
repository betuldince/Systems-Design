// Name: Betul Dincer
// USC NetID:  1816821532
// CS 455 PA4
// Fall 2023

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.*;

/**
   This class has main function, so it runs the application. It takes the rack from the user and generates words and corresponding scores. To run      with default dictionary(sowpods.txt): java WordFinder. You can also input other dictionaries as arguments.
 */

public class WordFinder{
    
   private static final String DEFAULT_FILENAME = "sowpods.txt";
   private static boolean done = false;
   private static String filename;   
   
   /*
      Representation invariant:
      -- filename is inputed from command line or DEFAULT_FILENAME is selected.
      -- program terminates with done boolean
      -- when the program starts the done is false, when the program terminates done is true
   */

   public static void main(String[] args) { 
      Scanner sc = new Scanner(System.in);   
      String rack = "";    
      boolean isBeginning = true; 
      while(!done){         
         try{            
            chooseFile(args);            
            Dictionary d = new Dictionary(filename);
            d.isDuplicated();
            if(isBeginning == true){
               System.out.println("Type . to quit.");
               isBeginning = false;
            }
            System.out.print("Rack? ");
            rack = sc.next();
            if(rack.equals(".")){
               done = true;  
            }else{
               WordsBuilder wordsBuilder = new WordsBuilder(rack,filename);
               wordsBuilder.buildWords();
            }
         }catch (FileNotFoundException e) {
            fileException();  
         } catch(IllegalDictionaryException e){
            illegalException(e);            
         } 
      }
   }
   
   /**
      Prints the messages when the file doesn't exist. End the program.   
    */
   private static void fileException(){
      System.out.println("ERROR: Dictionary file "+ filename +" does not exist.");
      System.out.println("Exiting program.");
      done = true;     
   }
   /**
      Prints the messages when the file has duplicate words. End the program.     
    */ 
   private static void illegalException(IllegalDictionaryException e){
      System.out.println(e.getMessage());
      System.out.println("Exiting program.");
      done = true;      
   }
   /**
      If there is no argument, default dictionary is used. If there is argument, the directory is used for the dictionary. 
      PRE: args array hold strings from the command line arguments.
    */
   private static void chooseFile(String args[]){      
      if(args.length>0){
         filename = args[0];
      }else{
         filename = DEFAULT_FILENAME; 
      }
   }
  
 }