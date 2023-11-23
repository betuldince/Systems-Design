// Name: Betul Dincer
// USC NetID: 1816821532
// CSCI455 PA2
// Fall 2023
 
/**
 * Class BookshelfKeeperProg 
 * BookshelfKeeperProg allows the user to perform a series of pickPos and putHeight operations on a bookshelf in    an interactive mode with user commands called pick and put. Also, program error checks entrered inputs and terminate program if needed.
 
 To run program: java -ea BookshelfProg
 
 */
import java.util.*;

public class BookshelfKeeperProg{
    
   
   
   private static boolean ex=false;
   private static String[] opParts;
   private static BookshelfKeeper bookshelfKeeper;
    
   public static void main(String[] args)
   {
      
      System.out.println("Please enter initial arrangement of books followed by newline:");
      Scanner in = new Scanner(System.in);  
      String inputStr = in.nextLine();   
      ArrayList<Integer> input = putHeights(inputStr); 
      checkHeight(input);
      Bookshelf bookshelf = new Bookshelf(input);
      checkOrder(bookshelf);
      bookshelfKeeper = new BookshelfKeeper(bookshelf);   
      System.out.println(bookshelfKeeper.toString()+ " 0 0");
      System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");

      while(!ex){
         String opInputs = in.nextLine();
         opParts = opInputs.trim().split("\\s+");
         String op = opParts[0];
         checkCommand(op);
         checkEnd(op);   
         putElem(op);
         pickElem(op);
      }
      
   }
    
   /**
    * To pick books, when user enters pick operation with invalid number, the  
    * program gives error message and terminates
    * @param op the operation user entered, should be pick
    */
    
    private static void pickElem(String op){
       if(op.equals("pick")){
          int inValue = Integer.parseInt(opParts[1]);
          if(inValue>=bookshelfKeeper.getNumBooks()){     
             System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
             System.out.println("Exiting Program.");
             System.exit(0);
          }
          int k =  bookshelfKeeper.pickPos(inValue);
          System.out.println(bookshelfKeeper.toString());
       }
    }
    
   /**
    * To put books, when user enters put operation with negative height, the  
    * program gives error message and terminates
    * @param op the operation user entered, should be put
    */
    
    private static void putElem(String op){
       if(op.equals("put")){
          int inValue = Integer.parseInt(opParts[1]);
          if(inValue<0){
             posHeightErr();
          }
          int k =  bookshelfKeeper.putHeight(inValue);
          System.out.println(bookshelfKeeper.toString());
         }
    }
    
   /**
    * To check if the end command came and terminate 
    * @param op the operation user entered, should be end
    */
    
    private static void checkEnd(String op){
       if(op.equals( "end")){
          System.out.println("Exiting Program.");
          ex = true;
       }   
    }
    
   /**
    * Terminate program if the entered command is not valid, a.k.a, other than pick,put and end
    * @param op the operation user entered, should be valid
    */
    
    private static void checkCommand(String op){
       if(!validInput(op)){
          System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
          System.out.println("Exiting Program.");
          System.exit(0);
       }   
    }
    
   /**
    * Check if the ArrayList contains all positive heights.
    * @param input is the entered ArrayList to check the elements' value
    */    
    
    private static void checkHeight(ArrayList<Integer> input){
      if(!validHeight(input)){
         posHeightErr();
      }
    }
    
   /**
    * Terminating and writing error messege for inalid height.
    * 
    */  
    private static void posHeightErr(){
       System.out.println("ERROR: Height of a book must be positive.");
       System.out.println("Exiting Program.");
       System.exit(0);
    }
    
   /**
    * Terminating and writing error messege if the bookshelf does not have non-decreasing order
    * @param bookshelf to check the order
    */ 
    private static void checkOrder(Bookshelf bookshelf){          
      if(!validOrder(bookshelf)){
         System.out.println("ERROR: Heights must be specified in non-decreasing order.");
         System.out.println("Exiting Program.");
         System.exit(0);
      }
    }
    
   /**
    * This method reads the entered numbers and returns ArrayList to create bookshelf
    * @param inputStr the numbers user entered
    */ 
    
    private static ArrayList<Integer> putHeights(String inputStr){
       ArrayList<Integer> input = new ArrayList<Integer>(); 
       String[] inputParts = inputStr.trim().split("\\s+");
       for(int i =0;i<inputParts.length;i++){
          if(inputStr.length()>0){
          int book = Integer.parseInt(inputParts[i]);
          input.add(book);
          }
       }
       return input;   
    }
    
   /**
    * This method validates the height of books, return false if it has negative value
    * @param books to check height
    */ 
    
    private static boolean validHeight(ArrayList<Integer> books){   
       int size = books.size();
       for(int i =0;i<size;i++){
          if(books.get(i)<0){
             return false;
          }
       }
       return true;   
    }
    
   /**
    * This method validates the order of bookshelf which should be ordered
    * @param bookshelf to check order
    */ 
    
   private static boolean validOrder(Bookshelf bookshelf) {
      if(bookshelf.isSorted()==false){
         return false;
      }
      return true;  
   }
    
   /**
    * This method validates if the entered method is valid, should be one od pick, put or end  
    * @param input that user entered
    */ 
    
   private static boolean validInput(String input) {
      if(input.equals("pick") ||input.equals("put")||input.equals("end") ){
         return true;
      }
      return false;   
   }
    
 }