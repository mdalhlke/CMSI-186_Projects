/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  author        :  Maya Dahlke
 *  Date started  :  2018-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *                   for Homework 5. 
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class Ball {
    
   private double rx;
   private double ry;
   private double vx;
   private double vy;
   private double timeslice;
   private final double radius = 4.50; //in inches
   //private final double weight = 1; //in pounds

   //constructor 
   public Ball(String rxi, String ryi, String vxi, String vyi, String timeslice) {
      this.rx = Double.parseDouble(rxi);
      this.ry = Double.parseDouble(ryi);
      this.vx = Double.parseDouble(vxi);
      this.vy = Double.parseDouble(vyi);
      this.timeslice = Double.parseDouble(timeslice);
   }

   //gets the location in the x direction
   public double getLocationX() {
      return this.rx; 
   }

   //gets the location in the y direction
   public double getLocationY() {
      return this.ry;
   }

   //decreases the velocity due to the frictional force until the ball comes to rest
   public void velocityDueToFriction() {
      this.vx = this.vx * (Math.pow(0.99, this.timeslice));
   	  this.vy = this.vy * (Math.pow(0.99, this.timeslice));
   	  double vf = Math.sqrt(this.vx * this.vx + this.vy * this.vy); 
   	  if (vf <= 1/12) {
   	      this.vx = 0;
   	      this.vy = 0;
   	  } 
   }

   //moves the ball to another location according to the velocity  
   public void move() {
       this.velocityDueToFriction();
       this.rx = this.vx * this.timeslice + this.rx;
       this.ry = this.vy * this.timeslice + this.ry;


   //converts the results into a string 
   public String toString() {
      //locationBall();
     StringBuffer buffer = new StringBuffer();
     return buffer.append(this.rx).append("\n").append(this.ry).append("\n").append(this.vx).append("\n").append(this.vy).append("\n").toString();
   }

   //testing
   public static void main(String[] args) {
      System.out.println( "\nBALL CLASS TESTER PROGRAM\n" + "--------------------------\n" );
  
      Ball ball = new Ball("0", "0", "1", "0", "1");
      ball.move();
      System.out.println(ball.toString());
      /*System.out.println("Testing locationBall() method:");
      try { Ball ball = new Ball("3", "4", "5", "6", "0.01"); System.out.println(("3,4" == ball.locationBall()) ? " - got it" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }*/
   }
}
