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
   
   //constructor 
   public Ball() {

   }

   //methods
   public double validateTimeSlice() {
      // check if args.length % 1 is true
   	  // if it is than you are good to go
   	  // if false, then default the timeslice to 1 sec

      try {
         if(args.length % 1){

         }
      catch (NumberFormatException nfe) {
          throw new NumberFormatException("invalid number");
      }
   }

   public double validateArgs(String argValue) throws NumberFormatException {
      //1 ball has 4 args x and y position and x and y speeds
   }

   public void friction() {
      //slows each ball until the ball comes to rest - friction is a force that continuously decreases ball's speed at 1%/sec until
   	  // the ball is traveling less than one inch per sec, at which point it comes to rest.
   }

   public double velocity() {
      //returns the velocity of each ball
   }

   public double position() {
   	  //returns the position of each ball
   }


}
