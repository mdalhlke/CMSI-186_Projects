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
   private static final double EPSILON = 0.1; 
   private double timeSlice;
   private double currentTime; //in seconds

   //constructor
   public Timer(double timeSlice) {
   	  
      this.currentTime = 0.0;
      this.timeSlice = timeSlice;
      
   }

   //tells what the timeslice is
   public double timeSlice() {
   	 return this.timeSlice;
   }
   
   public double tick() {
   	//how many seconds elapsed
      this.currentTime += this.timeSlice;
      return this.currentTime;
   }

   public String toString() {

   }

}
