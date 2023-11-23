// Name: Betul Dincer
// USC NetID: 1816821532
// CSCI455 PA2
// Fall 2023


/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

    /**
      Representation invariant:
      Bookshelf should be always sorted.
   */
   
   // <add instance variables here>
   private Bookshelf bookshelf;
   private int totOp=0;
   private int holder =0;
   private int holder2 = 0;
   private int HALF_ARRAY=2;
   private int ARRAY_START_POS=2;
   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {  
      bookshelf = new Bookshelf();
      assert isValidBookshelfKeeper();
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    * @param sortedBookshelf is sorted bookshelf
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      bookshelf=sortedBookshelf;
      assert isValidBookshelfKeeper();
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted 
    * after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * @param position the position of book we try to pick
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {       
      Bookshelf dummy = new Bookshelf();
      int size = bookshelf.size();
      // if the position we pick later than half of array, we operate removing and adding from last of bookshelf
      if (position>=size/HALF_ARRAY){         
         for(int i =0 ; i< (size - position);i++){
            dummy.addFront(bookshelf.removeLast());
            incrPickPos(); 
         }
         for(int i =1; i<dummy.size();i++){        
            bookshelf.addLast(dummy.getHeight(i));
            incrPickPos();
         }
      // if the position we pick before than half of array, we operate removing and adding from front of bookshelf 
      }else{         
         for(int i =0 ; i< position+1;i++){
            dummy.addLast(bookshelf.removeFront());
            incrPickPos();
         }
         for(int i =dummy.size()- ARRAY_START_POS; i>=0;i--){        
            bookshelf.addFront(dummy.getHeight(i));
            incrPickPos();
         }
      }
      assert isValidBookshelfKeeper();
      return holder;   // dummy code to get stub to compile
   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted 
    * after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * @ param height the height of the book that we are putting on Bookshelf
    * PRE: height > 0
    */
   public int putHeight(int height) {
      int index =0;
      int size = bookshelf.size();
      Bookshelf dummy = new Bookshelf();
      //To detecting the position, we want to put book
      for(int i =0;i<size;i++){
         if(height<=bookshelf.getHeight(i)){
            index =i;
            break;
         }else if(height>=bookshelf.getHeight(size-1)){
            index =bookshelf.size();
         }
      }
      //if the position greater than middle, we operate removing and adding from last of bookshelf
      if(index >=size/HALF_ARRAY){  
         for(int i =0 ; i< (size - index);i++){
            dummy.addFront(bookshelf.removeLast());
            incrPutHeight();
         }
         dummy.addFront(height); 
         for(int i =0; i<dummy.size();i++){        
            bookshelf.addLast(dummy.getHeight(i));
            incrPutHeight();
         }
      //if the position greater than middle, we operate removing and adding from front of bookshelf   
      }else{
         for(int i =0 ; i< index;i++){
            dummy.addLast(bookshelf.removeFront());
            incrPutHeight();
         }
         dummy.addLast(height);
         for(int i =dummy.size()-1; i>=0;i--){        
            bookshelf.addFront(dummy.getHeight(i));
            incrPutHeight();
         }
      }
      assert isValidBookshelfKeeper();
      return holder2;   // dummy code to get stub to compile
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
       assert isValidBookshelfKeeper();
       return totOp;   // dummy code to get stub to compile
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
       assert isValidBookshelfKeeper();
       return bookshelf.size();   // dummy code to get stub to compile
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed 
    * by the number of bookshelf mutator calls made to perform the last pick or put operation, 
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {
      String ret = bookshelf.toString();
      //If PickPos method updated at last, use holder
      if(holder>0){
         ret = ret + " " + holder+ " " + getTotalOperations();
         holder=0;
      }
      //If PutHeight method updated at last, use holder2
      if(holder2>0){
         ret = ret + " " + holder2+ " " + getTotalOperations();
         holder2=0;
      }

      assert isValidBookshelfKeeper();
      return ret;   // dummy code to get stub to compile
      
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {
      if(bookshelf.isSorted()==false){
         return false;
      }
      return true;  // dummy code to get stub to compile
   }
   
   /**
    *  Updating the counters for PickPos method
    *  
    */
   private void incrPickPos(){
      totOp++;
      holder++;      
   }
   
   /**
    *  Updating the counters for PutHeight method
    *  
    */
   private void incrPutHeight(){
      totOp++;
      holder2++;      
   }

}
