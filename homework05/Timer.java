/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
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
      if(timeSlice <= 0.0){
      	this.timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
      } else {
           this.timeSlice = timeSlice;     
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
      int hour = (int) Math.floor(this.currentTime / 3600);
      double secLeft = this.currentTime % 3600.0;
      int min = (int) Math.floor(secLeft / 60);  
      double sec =  secLeft % 60.0;
      DecimalFormat df = new DecimalFormat("00.000");
      StringBuffer time = new StringBuffer();
      time.append(hour).append (":").append(min).append(":").append(df.format(sec));
      return("The time is: " + time.toString());
   }

   //testing
   public static void main(String[] args) {
      System.out.println( "\nTIMER CLASS TESTER PROGRAM\n" + "--------------------------\n" );

      System.out.println("Testing timeSlice() method:");
      System.out.println("Testing 1.0:");
      try { Timer time = new Timer(1.0); System.out.println((1.0 == time.timeSlice()) ? " - got it" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("Testing 10.0:");
      try { Timer time = new Timer(10.0); System.out.println((10.0 == time.timeSlice()) ? " - got it" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("Testing 0.001:");
      try { Timer time = new Timer(0.001); System.out.println((0.001 == time.timeSlice()) ? " - got it" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("Testing 0.0:");
      try { Timer time = new Timer(0.0); System.out.println((1.0 == time.timeSlice()) ? " - got it" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("Testing -1.0:");
      try { Timer time = new Timer(-1.0); System.out.println((1.0 == time.timeSlice()) ? " - got it" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      
   }
}
