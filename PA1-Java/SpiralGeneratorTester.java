// Name:Betul Dincer
// USC NetID:bdincer
// CS 455 PA1
// Fall 2023

import java.awt.geom.Line2D;
import java.awt.Point;

   /*
      This class is unit testing the SpiralGenerator class. The data is hardcoded. 
      It returns error messenge if the lines are not horizontal or vertical, the segments are not connected or perpendicular.
   
   */

 public class SpiralGeneratorTester
{

   public static void main(String[] args)
   {
      Point p = new Point(200,300);
      int unitLength = 5;
      int segNum = 10; 
      System.out.println("Making a spiral starting from java.awt.Point[x="+p.x+",y="+p.y+"] with a unit length of "+unitLength+", and made up of "+ segNum+" segments: ");
      testSegment(p,unitLength,segNum );
   
      Point p1 = new Point(159,303);
      int unitLength1 = 7;
      int segNum1 = 13;
      
      System.out.println("Making a spiral starting from java.awt.Point[x="+p1.x+",y="+p1.y+"] with a unit length of "+unitLength1+", and made up of "+ segNum1+" segments: ");
      testSegment(p1,unitLength1,segNum1 );
      
      Point p2 = new Point(20,200);
      int unitLength2 = 2;
      int segNum2 = 13;
      
      System.out.println("Making a spiral starting from java.awt.Point[x="+p2.x+",y="+p2.y+"] with a unit length of "+unitLength2+", and made up of "+ segNum2+" segments: ");
      testSegment(p2,unitLength2,segNum2);
      
      // Boundary Tests
      // When the point is 0,0 - origin
      
      Point p3 = new Point(0,0);
      int unitLength3 = 21;
      int segNum3 = 13;
      
      System.out.println("Making a spiral starting from java.awt.Point[x="+p3.x+",y="+p3.y+"] with a unit length of "+unitLength3+", and made up of "+ segNum2+" segments: ");
      testSegment(p3,unitLength3,segNum3);
      
      // Min unit length
      
      Point p4 = new Point(30,100);
      int unitLength4 = 1;
      int segNum4 = 13;
      
      System.out.println("Making a spiral starting from java.awt.Point[x="+p4.x+",y="+p4.y+"] with a unit length of "+unitLength4+", and made up of "+ segNum4+" segments: ");
      testSegment(p4,unitLength4,segNum4);
      
     

   }
    
    /**
       This method is for creating segments according to starting point, unit length and segment number
       @param p point the spiral started from
       @param unitLength is the segment length
       @param segNum is the total segment number
    */
    private static void testSegment(Point p,int unitLength, int segNum){
         SpiralGenerator gen = new SpiralGenerator(p,unitLength );
         for(int i =0; i<segNum; i++ ){
         Line2D currSeg = gen.nextSegment();
         double x1 = currSeg.getX1();
         double x2 = currSeg.getX2();  
         double y1 = currSeg.getY1();
         double y2 = currSeg.getY2();
         boolean ver = false;   
         boolean horz = false; 
         System.out.println("Segment #" + (i+1)+": "+ currSeg.getP1() + " " + currSeg.getP2());
         
         // To check if the lines are vertical or horizontal
         if(((int)x1-x2==0) && ((int)y1 -y2!=0 )){
            ver= true;
         }
         if(((int)x1-x2!=0) && ((int)y1 -y2==0 )){
            horz= true;
         }
        
         if(ver == false && horz == false){
            System.out.println("FAILED: segment is not horizontal or vertical. Skipping pair tests");
         }else{   
             Line2D nextSeg = gen.nextSegment();
             double x1t = nextSeg.getX1();
             double x2t = nextSeg.getX2();  
             double y1t = nextSeg.getY1();
             double y2t = nextSeg.getY2();
             System.out.println("Segment #" + (i+2)+": "+ nextSeg.getP1() + " " + nextSeg.getP2());
          
             if(x1t != x2 || y1t != y2){
                System.out.println("FAILED: last two segments are not connected");
             }
           
             if(!checkPerp(x1,y1,x2,y2,x1t,x2t,y1t,y2t)){
                System.out.println("FAILED: last two segments are not perpendicular");
             }       
        }
         i++;

      }
    
    }
    
    
    
    
    
     /**
        Check if two lines are perpendicular to each other.
        @param x1 starting x point of first line
        @param x2 ending x point of first line
        @param y1 starting y point of first line
        @param y2 ending y point of first line
        @param x1t starting x point of second line
        @param x2t ending x point of second line
        @param y1t starting y point of second line
        @param y2t ending y point of second line
        @return returns true if the lines are perpendicular each other
     
     */
    
     private static boolean checkPerp(double x1, double y1,double x2, double y2,double x1t, double y1t,  double x2t, double y2t){
          
        boolean ret = true;
        // To check if the lines are parallel      
        if(x1-x2==0 && x1t - x2t==0){
           ret = false;
        }
        if(y1-y2==0 && y1t - y2t==0){
           ret = false;
        }
           
        double m1=0;
        double m2=0;
        // first slope 
        if((int)(y1-y2)!=0){
           m1 = (x1-x2)/(y1- y2);
        }
        // second slope
        if((int)(y1t-y2t)!=0){
           m2 = (x1t-x2t)/(y1t- y2t);
        }
        if(m1 * m2 == -1){
           ret = true;
        } 
        // first line is horizontal / second line is vertical
        if((int)(y1-y2) == 0 && (int)(x1t-x2t)==0){
           ret = true;
        }
        // first line is vertical / second line is horizantal
        if((int)(x1-x2) == 0 && (int)(y1t-y2t)==0){
           ret = true;
        }
        return ret;
          
     }
    
    
    
    
 }