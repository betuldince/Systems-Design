// Name: Betul Dincer
// USC NetID: 1816821532
// CSCI455 PA2
// Fall 2023
 
 
import java.util.*;
 public class TestAssert
{

   public static void main(String[] args)
   {
      ArrayList<Integer> booksValid = new ArrayList<Integer>(Arrays.asList(1, 2, 5, 4));
      Bookshelf bookshelf = new Bookshelf(booksValid);
      ArrayList<Integer> booksInvalid = new ArrayList<Integer>(Arrays.asList(1, -2, 5, 4));
      Bookshelf bookshelf2 = new Bookshelf(booksInvalid);
      System.out.println(bookshelf);
      System.out.println(bookshelf2);
   }
    
 }