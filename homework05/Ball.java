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
    
   private double rx, ry;
   private double vx, vy;
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

   //methods
   public String[] locationBall() {
      //how to determine location
      String loc[] = new String[2];
      loc[0] = String.valueOf(this.rx);
      loc[1] = String.valueOf(this.ry);
      System.out.println(loc);
      return loc;
   }

   public void moveBall() {
      //move to another location using velocity
      if (Math.abs(this.rx + this.vx) + radius > 1.0) System.exit(1);
      if (Math.abs(this.ry + this.vy) + radius > 1.0) System.exit(1);
      double rxf, ryf;
      rxf = this.rx + this.vx;
      ryf = this.ry += this.vy;

   }

   public void friction() {
       //slows each ball until the ball comes to rest - friction is a force that continuously decreases ball's speed at 1%/sec until
   	   //the ball is traveling less than one inch per sec, at which point it comes to rest.
   	      double vxf = this.vx * (Math.pow(0.99, this.timeslice));
   	      double vyf = this.vy * (Math.pow(0.99, this.timeslice));
   	      if (this.rx == 0 && this.ry == 0) {
   	         System.out.println("Ball is at rest");
   	         System.exit(1);
   	      }
   }

   public String toString() {
      return "hi";
   }

   public static void main(String[] args) {
      System.out.println( "\nBALL CLASS TESTER PROGRAM\n" + "--------------------------\n" );
  
      System.out.println("Testing locationBall() method:");
      try { Ball ball = new Ball("3", "4", "5", "6", "0.01"); System.out.println(("3,4" == ball.locationBall()) ? " - got it" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}

