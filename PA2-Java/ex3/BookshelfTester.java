   // Name: Betul Dincer
// USC NetID: 1816821532
// CSCI455 PA2
// Fall 2023
 
import java.util.*;
 public class BookshelfTester
{

   public static void main(String[] args)
   {
      String retValue="";
      Bookshelf bookshelf = new Bookshelf();
      retValue=bookshelf.toString();
      System.out.println(retValue);
      ArrayList<Integer> books = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
      bookshelf = new Bookshelf(books);
      retValue=bookshelf.toString();
      System.out.println(retValue);
      
      //Ex2
      bookshelf.addFront(13);
      retValue=bookshelf.toString();
      System.out.println(retValue);
      
      bookshelf.addLast(16);
      retValue=bookshelf.toString();
      System.out.println(retValue);
      
      int first = bookshelf.removeFront();
      System.out.println("First element: "+first);
      retValue=bookshelf.toString();
      System.out.println(retValue);
      
      int last = bookshelf.removeLast();
      System.out.println("Last element: "+last);
      retValue=bookshelf.toString();
      System.out.println(retValue);
      
      
      
   
   }
    
 }