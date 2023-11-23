// Name: Betul Dincer
// USC NetID: 1816821532
// CSCI455 PA2
// Fall 2023


/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
*/
import java.util.*;
public class Bookshelf {

    /**
      Representation invariant:
      Books in the bookshelf should have positive height.
      Bookshelf cannot be null
   */
   
   // <add instance variables here>
      private ArrayList<Integer>books;

   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {  
      books = new ArrayList<>();
      assert isValidBookshelf();  // sample assert statement (you will be adding more of these calls)
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    * 
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      ArrayList<Integer> copy = pileOfBooks;
      books = new ArrayList<>(copy);
      assert isValidBookshelf(); 
   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      books.add(0,height);
      assert isValidBookshelf(); 
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      books.add(height);
      assert isValidBookshelf(); 
   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
      int value = books.get(0);
      books.remove(0);
      assert isValidBookshelf(); 
      return value;   // dummy code to get stub to compile
      
   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
      int size = books.size();
      int value = books.get(size-1);
      books.remove(size-1);
      assert isValidBookshelf(); 
      return value;   // dummy code to get stub to compile      
   }

   /*
    * Gets the height of the book at the given position.
    * 
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {  
      assert isValidBookshelf(); 
      return books.get(position);   // dummy code to get stub to compile
   }

   /**
    * Returns number of books on the this Bookshelf.
    */
   public int size() {
      assert isValidBookshelf(); 
      return books.size();   // dummy code to get stub to compile

   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {
      String ret ="[";
      int size = books.size(); 
      
      for(int i =0;i<size;i++){
         ret +=books.get(i);
         if(i!=size-1){
            ret+=", ";
         }
      }     
      ret = ret + "]";
      assert isValidBookshelf(); 
      return ret;   // dummy code to get stub to compile

   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      boolean val =true;
      int curr=0;
      int next=0;
      int size = books.size();
      for(int i=0;i<size-1;i++){
         curr = books.get(i);        
         next = books.get(i+1);  
         if(curr>next){
            val = false;
         }
      }
      assert isValidBookshelf(); 
      return val;  // dummy code to get stub to compile
   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
       If the height of books is positive
       If it is not null. 
    * (See representation invariant comment for more details.)
    */
   private boolean isValidBookshelf() {     
      int size = books.size();
      for(int i =0;i<size;i++){
         if(books.get(i)<0){
            return false;
         }
      }
      if(books==null){
         return false;
      }
      return true;  // dummy code to get stub to compile
   }

}
