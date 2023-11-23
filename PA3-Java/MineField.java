// Name:Betul Dincer
// USC NetID:1816821532
// CS 455 PA3
// Fall 2023


/** 
   MineField
      Class with locations of mines for a minesweeper game.
      This class is mutable, because we sometimes need to change it once it's created.
      Mutators: populateMineField, resetEmpty
      Includes convenience method to tell the number of mines adjacent to a location.
 */
import java.util.Random;

public class MineField {
   
   // <put instance variables here>
   
   private boolean [][] mineField;
   private int numMines; // initial num of mines
   private int numMinesPr; // Present number of mines
   
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in
      the array such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice
      versa.  numMines() for this minefield will corresponds to the number of 'true' values in 
      mineData.
      @param mineData  the data for the mines; must have at least one row and one col,
                       and must be rectangular (i.e., every row is the same length)
    */
   public MineField(boolean[][] mineData) {
       
      mineField = new boolean [mineData.length][mineData[0].length];
      for(int i=0;i<numRows();i++){
         for(int j =0;j<numCols();j++){
            mineField[i][j]=mineData[i][j];
            if(mineField[i][j]==true){
               this.numMines++;         
            }
         }
      }
      numMinesPr = this.numMines;
      
   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a 
      MineField, numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
      mineField = new boolean[numRows][numCols];   
      this.numMines = numMines;  
      
   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on
      the minefield, ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col) and numMines() < (1/3 * numRows() * numCols())
    */
   public void populateMineField(int row, int col) {      
      resetEmpty();
      numMinesPr=0;
      while(numMinesPr<numMines()){
         int []mines = rowColGenerator(row,col); // random row,col generator
         int r = mines[0];
         int c = mines[1];
         // To put mines where there wasn't mines before
         if (!mineField[r][c]) {
            mineField[r][c] = true;
            numMinesPr++;
        }   
      }
   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or
      numCols().  Thus, after this call, the actual number of mines in the minefield does not match
      numMines().  
      Note: This is the state a minefield created with the three-arg constructor is in at the 
      beginning of a game.
    */
   public void resetEmpty() {

      for(int i=0;i<numRows();i++){
         for(int j=0;j<numCols();j++){
            mineField[i][j]=false;
         }
      }
      numMinesPr = 0; // present mines are zero
   }

   
  /**
     Returns the number of mines adjacent to the specified location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
      int tot=0;
      for(int i = row-1;i<=row+1;i++){
         for(int j=col-1;j<=col+1;j++){
            if(0 <= i && i < numRows() && 0 <= j && j < numCols() && !(i==row && j==col)){
               if(mineField[i][j]==true){
                  tot++;
                  
                }
            }
         }    
      } 
      return tot;        
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
      if(row>=0&&row<=numRows()-1&& col <= numCols()-1&&col>=0){
         return true;
      }
      return false;      
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return mineField.length;        
   }
   
   
   /**
      Returns the number of columns in the field.
      @return number of columns in the field
   */    
   public int numCols() {
      return mineField[0].length;        
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
      if(mineField[row][col]==true){
         return true;
      }
      return false;        
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg
      constructor, some of the time this value does not match the actual number of mines currently
      on the field.  See doc for that constructor, resetEmpty, and populateMineField for more
      details.
      @return number of mines
    */
   public int numMines() {
      return this.numMines;        
   }
   
   /**
      Returns the mine matrix as well as initial mine number and remaining mine number
      @return mine matrix, number of initial mines, number of remaining mines 
   */
   
   public String toString() {
      
      String ret = "";
      for (int i = 0; i < numRows(); i++) {
        for (int j = 0; j < numCols(); j++) {
            if (mineField[i][j]) {
                ret = ret+ "true ";
            } else {
                ret = ret+ "false ";
            }
        }
        ret+="\n"; 
      }
      ret+= "Number of initial mines: "+ numMines+" number of current mines: "+ numMinesPr;
      return ret;
      
   }

   
   // <put private methods here>
   
   /**
      This method is a helper function for populateMineField. I generates random location. It prohibits to put mine at location (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
   */
   
   private int[] rowColGenerator(int row, int col){
      Random rand = new Random();
      int r = rand.nextInt(numRows());
      int c = rand.nextInt(numCols());
      while(r==row ){
         r = rand.nextInt(numRows());
      }
      while(c==col ){
         c = rand.nextInt(numCols());
      }
      int []ret = new int[2];
      ret[0]= r;
      ret[1]=c;
      return ret;   
   }
   
}

