/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
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
//import java.text.DecimalFormat;

public class Timer {
   
   //class field definintions
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
   private static final String ABSENT = new String("");
   private static final double EPSILON = 0.1; 
   private double timeSlice;
   private double currentTime; //in seconds

   //constructor
   public Timer(double timeSlice) {
      this.currentTime = 0.0;
      this.timeSlice = timeSlice;      
   }

   //validates the timeslice
   public double validateTimeSlice(String argValue) {
      try {
         if(argValue == ABSENT) {
            this.timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
            return this.timeSlice;
         } else {
         	return this.timeSlice;
         }
      }
      catch (NumberFormatException nfe) {
         throw new NumberFormatException("invalid number");
      } 
   }
   
   //tells what the timeslice is
   public double timeSlice() {
   	 return this.timeSlice;
   }
   
   //how many seconds elapsed
   public double tick() {
      this.currentTime += this.timeSlice;
      return this.currentTime;
   }

   public String toString() {
      return("The time is: " + this.currentTime);
   }

   //testing
   public static void main(String[] args) {
      System.out.println( "\nTIMER CLASS TESTER PROGRAM\n" + "--------------------------\n" );
  
      Timer time = new Timer(1.0);

      System.out.println(time.toString());
      /*System.out.println("Testing locationBall() method:");
      try { Ball ball = new Ball("3", "4", "5", "6", "0.01"); System.out.println(("3,4" == ball.locationBall()) ? " - got it" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }*/
   }

}
