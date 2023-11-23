// Name: Betul Dincer
// USC NetID:  1816821532
// CS 455 PA4
// Fall 2023

import java.util.ArrayList;
import java.util.*;

/**
   A Rack of Scrabble tiles
 */

public class Rack {
   
   private static String rack;
   
   /*
      Representation invariant:
      -- rack represents the word taken from the user as input.
      -- rack should not contain letters other than english alphabet letters.
      -- rack.length()>0
   */
   
   
   /**
      Initializes the rack and creates HashSet to hold subsets of rack.
      PRE: rack is non-empty word
      @param rack a string of rack
    */
   
   public Rack(String rack){
      this.rack = rack; 
      
   }

   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   /**
      Finds all the subsets of given rack and add it to ArrayList of string.
      @return all the subsets for given rack
    */
   public static ArrayList<String> subsetsRack() {
      ArrayList<String> ret = new ArrayList<String>();
      String sortedRack = sortedString(rack);   
      int [] mult = new int[26];
      String unique = "";
      int ind = -1;
      for(int i =0 ; i< sortedRack.length();i++ ){
         char c = sortedRack.charAt(i);
         if(Character.isUpperCase(c)){
            ind = c-'A'; // For the words that includes upper characters
         }else{
            ind = c-'a'; // For the words that includes lower characters
         }        
         mult[ind]+= 1;
         if(mult[ind]==1){
            unique += c;
         }
      }     
      ret = allSubsets(unique, nonZeroArr(mult), 0);   
      return ret;
   }
   
   /**
      Converts the multiplicity array to the array of non-zero values. So, it only contains multiplities of characters which have non-zero value.
      PRE: arr.length must be the length of all letters in the alphabet     
      @param arr the array of multiplicities of all the characters 
      @return the non-zero valued array
    */
   private static int[] nonZeroArr(int[] arr){
      int arrLen = 0;
      for (int i = 0; i < arr.length; i++) {
         if(arr[i] != 0) {
            arrLen++;
         }
      }
      int[] ret = new int[arrLen];
      int index = 0;
      for (int i = 0; i < arr.length; i++) {
         if (arr[i] != 0) {
            ret[index] = arr[i];
            index++;
         }
      }
      return ret;  
   }
   
   /**
      Sort the given string.
      @param s string to process
      @return a sorted version of s
    */
   private static String sortedString(String s){
      char[] arr = s.toCharArray();
      Arrays.sort(arr);
      String ret = new String(arr);
      return ret;
   }

   
}
