// Name:Betul Dincer
// USC NetID:bdincer
// CS 455 PA1
// Fall 2023

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.geom.Line2D;
import java.awt.Point;

   /**
      This classes for creating Spiral component from segment length and segment number.
       
   
   */

public class SpiralComponent extends JComponent
{  
   private int segLen;
   private int segNum;
 

   public SpiralComponent(int segLen, int segNum) {
      this.segLen = segLen;
      this.segNum = segNum;
   }
   
   /**
      Draw spiral figure on JFrame. Start in the middle, from inner to outer, counter-clockwise 
      @param g graphics to be used  
   
   */
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;
      
      int x = getWidth()/2;
      int y = getHeight()/2;
      
      Point middle = new Point(x,y);

      SpiralGenerator spiral = new SpiralGenerator(middle,segLen);

      for(int i =0; i<segNum;i++){
         Line2D k = spiral.nextSegment(); 
         g2.draw(k);
      }
     
   }
}

