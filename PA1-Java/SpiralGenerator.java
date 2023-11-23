// Name:Betul Dincer
// USC NetID:bdincer
// CS 455 PA1
// Fall 2023

 

import java.awt.geom.Line2D;
import java.awt.Point;

/**
   class SpiralGenerator
   
   Generates a "rectangular" spiral, using Java display coordinate system, moving 
   outward from the center of the spiral in a counter-clockwise direction.
   That is, it will head rightward on screen, then, upward, then left, then down, etc.
  
   Length of initial line is unitLength.
   Padding between "layers" of the spiral is also unitLength.
   
   NOTE: we have provided the public interface for this class.  Do not change
   the public interface.  You can add private instance variables, constants, 
   and private methods to the class.  You will also be completing the 
   implementation of the methods given. 
   
 */
public class SpiralGenerator {



   /**
      Creates a SpiralGenerator starting at startPosition, with length of first segnment and 
      distance between "layers" both given as unitLength.  Both values are assuming the Java 
      graphics coordinate system.
      @param startPosition starting point of the first segment in the spiral
      @param unitLength in pixels, must be > 0
   */
   
   private int unitLength;
   private int segLength;
   private int counter;
   private int i=0;
   private Point currPoint;
   final int DIRECTIONS =4;
   
   public SpiralGenerator(Point startPosition, int unitLength) {
       
      currPoint = startPosition;
      this.unitLength = unitLength;
      segLength = unitLength;

   }

   /**
      Return the coordinates of the next line segment in the spiral.
      
    */
   public Line2D nextSegment() {
   
      int x = currPoint.x;
      int y = currPoint.y;
      
      int x2=0;
      int y2=0;

      switch(i){
      // Forward
         case 0 : 
         x2 = x+segLength;
         y2 = y;
         counter++;
         break;
      // Up      
         case 1:
         x2 = x;
         y2 = y-segLength;
         counter++;
         break;
      // Left     
         case 2: 
         x2 = x-segLength;
         y2 = y;
         counter++;
         break;
      // Down     
         case 3:     
         x2 = x;
         y2 = y+segLength;
         counter++;
         break;
            
         default:
         break;
      
      }
      
      // each unit length is used for 2 times, then it increases with unit length from inner circle to outer
      if(counter==2){
         segLength=segLength+unitLength;
         counter=0;
      }  
      
      Point nextPoint = new Point (x2,y2);
      currPoint= nextPoint;     
      
      // there are only 4 directions so we need to do modular operation. 
      i++;
      i = i % DIRECTIONS; 
        
      Line2D.Double ret = new Line2D.Double(x,y,x2,y2);
      
      return ret;
 
   }

}
