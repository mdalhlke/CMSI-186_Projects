/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  author        :  Maya Dahlke
 *  Date started  :  2018-02-22
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.\J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private static final double EPSILON = 0.1;
   private static final String ABSENT = new String("");
   private double angle;
   private double timeSlice;
   private double currentTime; //in seconds

  /**
   *  Constructor goes here
   */
   public Clock() {
      this.currentTime = 0.0;
      this.timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
   }
  
  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      this.currentTime += this.timeSlice;
      return this.currentTime;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
      //must correctly handle non-negative reals that are less than 360.0 degrees
      double value = 0.0;
      try {
         value = Double.parseDouble(argValue);
         if(value < MAXIMUM_DEGREE_VALUE && value >= 0.0) {
            this.angle = value % 360.0;
            return value;
         } else {
              return INVALID_ARGUMENT_VALUE;
         }
       }
       catch(NumberFormatException nfe) {
          throw new NumberFormatException("invalid number");
       }
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) {
      //must correctly handle positive reals that are less than 1800.0 seconds. 
      //If absent, your program should use a default time slice of 60.0 seconds.
     if(argValue == ABSENT) {
        this.timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
        return this.timeSlice;
     }
     double value = Double.parseDouble(argValue);
     if(value < 1800.0 && value > 0.0) {
        this.timeSlice = value;
        return value;
     } else {
          return INVALID_ARGUMENT_VALUE;
     }
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      return ((this.currentTime * HOUR_HAND_DEGREES_PER_SECOND) % MAXIMUM_DEGREE_VALUE);
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      return ((this.currentTime * MINUTE_HAND_DEGREES_PER_SECOND) % MAXIMUM_DEGREE_VALUE);
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      double hourAng = this.getHourHandAngle();
      double minAng = this.getMinuteHandAngle();
      DecimalFormat df = new DecimalFormat("000.000"); 
      String df1 = df.format(Math.abs(hourAng - minAng));
      double dble = Double.parseDouble(df1);
      return Math.round(dble) % MAXIMUM_DEGREE_VALUE;
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return this.currentTime;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      int hour = (int) Math.floor(this.currentTime / 3600);
      double secLeft = this.currentTime % 3600;
      int min = (int) Math.floor(secLeft / 60);  
      double sec =  secLeft % 60; 
      DecimalFormat df = new DecimalFormat("00.000");
      StringBuffer time = new StringBuffer();
      time.append(hour).append (":").append(min).append(":").append(df.format(sec));
      return time.toString();
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "    New clock created: " + clock.toString() );
      
     //testing validateAngleArg
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees',    expecting double value 0.0       " );
      try { System.out.println((0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "      sending '  360 degrees',  expecting INVALID_ARGUMENT_VALUE " );
      try{ System.out.println((INVALID_ARGUMENT_VALUE == clock.validateAngleArg("360.0")) ? " - got -1.0" : " - no joy"); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "      sending '  879 degrees',  expecting INVALID_ARGUMENT_VALUE " );
      try{ System.out.println((INVALID_ARGUMENT_VALUE == clock.validateAngleArg("879.0")) ? " - got -1.0" : " - no joy"); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "      sending '  -2.0 degrees', expecting INVALID_ARGUMENT_VALUE " );
      try{ System.out.println((INVALID_ARGUMENT_VALUE == clock.validateAngleArg("-2.0")) ? " - got -1.0" : " - no joy"); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "      sending '  180 degrees',  expecting double value 180.0     " );
      try{ System.out.println((180.0 == clock.validateAngleArg("180.0")) ? " - got 180.0" : " - no joy"); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      
     //testing validateTimeSliceArg
      System.out.println( "    Testing validateTimeSliceArg....");
      System.out.print( "      sending '  -2.0 seconds',   expecting INVALID_ARGUMENT_VALUE" );
      try{ System.out.println((INVALID_ARGUMENT_VALUE == clock.validateTimeSliceArg("-2.0")) ? " - got -1.0" : " - no joy"); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "      sending '  1800.0 seconds', expecting INVALID_ARGUMENT_VALUE" );
      try{ System.out.println((INVALID_ARGUMENT_VALUE == clock.validateTimeSliceArg("1800.0")) ? " - got -1.0" : " - no joy"); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "      sending '  1900.0 seconds', expecting INVALID_ARGUMENT_VALUE" );
      try{ System.out.println((INVALID_ARGUMENT_VALUE == clock.validateTimeSliceArg("1900.0")) ? " - got -1.0" : " - no joy"); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "      sending '  nothing ',       expecting 60.0                  " );
      try{ System.out.println((DEFAULT_TIME_SLICE_IN_SECONDS == clock.validateTimeSliceArg(ABSENT)) ? " - got 60.0" : " - no joy"); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "      sending '  .01 seconds',    expecting .01                   " );
      try{ System.out.println((0.01 == clock.validateTimeSliceArg(".01")) ? " - got .01" : " - no joy"); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
