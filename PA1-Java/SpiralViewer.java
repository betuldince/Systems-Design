// Name:Betul Dincer
// USC NetID:bdincer
// CS 455 PA1
// Fall 2023

import javax.swing.JFrame;
import java.util.Scanner;

   /**
      This class uses SpiralComponent to draw spiral. It gets the input from the user and return error messege if it is lower than 0.
      It draws on 300x400 frame. User enter number of segments and length of the initial segment. And program generate the spiral.
 
   */
public class SpiralViewer
{

   public static void main(String[] args)
   {
       
      Scanner in = new Scanner(System.in);
      System.out.println("Enter length of initial segment: ");
      int segLen = in.nextInt();
      
      while(segLen<=0){
         System.out.println("ERROR: value must be > 0");
         System.out.println("Enter length of initial segment: ");
         segLen = in.nextInt();
      }
      
      System.out.println("Enter number of line segments: ");
      int segNum = in.nextInt();
      
      while(segLen<=0){
         System.out.println("ERROR: value must be > 0");
         System.out.println("Enter number of line segments: ");
         segNum = in.nextInt();
      }
      
      JFrame frame = new JFrame();
      frame.setSize(300, 400);
      frame.setTitle("Spiral");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      SpiralComponent component = new SpiralComponent(segLen, segNum);
      frame.add(component);
      frame.setVisible(true);

}}