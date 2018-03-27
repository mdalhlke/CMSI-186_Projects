/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  author        :  Maya Dahlke
 *  Date started  :  2018-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *                   for Homework 5. 
 *
 *  Notes         :  None 
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.text.DecimalFormat;

public class Ball {
    
   private double rx;
   private double ry;
   private double vx;
   private double vy;
   private double timeslice;
   private double radius = 4.45 / 12; //in feet

   //constructor 
   public Ball(double rxi, double ryi, double vxi, double vyi, double timeslice) {
      this.rx = rxi;
      this.ry = ryi;
      this.vx = vxi;
      this.vy = vyi;
      this.timeslice = timeslice;
   }

   //decreases the velocity due to the frictional force until the ball comes to rest
   public String velocityDueToFriction() {
      DecimalFormat df = new DecimalFormat("00.000");
      this.vx = this.vx * (Math.pow(0.99, this.timeslice));
   	  this.vy = this.vy * (Math.pow(0.99, this.timeslice));
   	  double vf = Math.sqrt(this.vx * this.vx + this.vy * this.vy); 
   	  if (vf <= (1.0 / 12.0)) {
   	      this.vx = 0.0;
   	      this.vy = 0.0;
   	  } 
   	  return ("< " + this.vx + ", " + this.vx + " >");
   }

   //moves the ball to another location according to the velocity  
   public void move() {
      String vel = this.velocityDueToFriction();
      this.rx = this.vx * this.timeslice + this.rx;
      this.ry = this.vy * this.timeslice + this.ry;
   }

   public void stop() {
      this.vx = 0.0;
      this.vy = 0.0;
   }

   public double getX() {
      return this.rx;
   }

   public double getY() {
      return this.ry;
   }

   public boolean isMoving() {
      return (this.vx != 0 || this.vy != 0);
   }

   public void setRadius(double radius) {
      this.radius = radius;
   }

   public double getRadius() {
      return this.radius;
   }

   //converts the results into a string 
   public String toString() {
     DecimalFormat df = new DecimalFormat("0.000000");
     return "position: <" + df.format(this.rx) + ", " + df.format(this.ry) + 
         ">  velocity: <"  + df.format(this.vx) + ", " + df.format(this.vy) + ">";
   }

   //testing
   public static void main(String[] args) {
      System.out.println( "\nBALL CLASS TESTER PROGRAM\n" + "--------------------------\n" );
  
      System.out.println("Testing methods:");
      System.out.println("Testing #1:");
      try { 
      	Ball ball = new Ball(1.0, 1.0, 1.0, 1.0, 1.0); 
      	ball.move(); 
      	System.out.println((1.99 == ball.getX()) ? " - got it" : " - no joy" ); 
      	System.out.println((1.99 == ball.getY()) ? " - got it" : " - no joy" ); } 
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("Testing #2:");
      try { Ball ball = new Ball(10.0, 1.0, 1.0, 1.0, 1.0); 
      	ball.move(); 
      	System.out.println((10.99 == ball.getX()) ? " - got it" : " - no joy" );
      	System.out.println((1.99 == ball.getY()) ? " - got it" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("Testing #3:");
      try { 
      	Ball ball = new Ball(0.20, 100.01, 1.0, 1.0, 1.0); 
      	ball.move();
      	System.out.println((1.19 == ball.getX()) ? " - got it" : " - no joy" );
      	System.out.println((101.0 == ball.getY()) ? " - got it" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("Testing #4:");
      try { 
      	Ball ball = new Ball(-1.0, 0.0, 1.0, 1.0, 1.0); 
      	ball.move(); 
      	System.out.println((-0.010000000000000009 == ball.getX()) ? " - got it" : " - no joy" ); 
      	System.out.println((0.99 == ball.getY()) ? " - got it" : " - no joy" );}
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
