/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Maya Dahlke
 *  Date          :  Jan 21 2018
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 */
public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY     = 0;
   private static final int MONDAY     = SUNDAY    + 1;
   private static final int TUESDAY    = MONDAY    + 1;
   private static final int WEDNESDAY  = TUESDAY   + 1;
   private static final int THURSDAY   = WEDNESDAY + 1;
   private static final int FRIDAY     = THURSDAY  + 1;
   private static final int SATURDAY   = FRIDAY    + 1;
   
  
  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = FEBRUARY  + 1;
   private static final int APRIL      = MARCH     + 1;
   private static final int MAY        = APRIL     + 1;
   private static final int JUNE       = MAY       + 1;
   private static final int JULY       = JUNE      + 1;
   private static final int AUGUST     = JULY      + 1;
   private static final int SEPTEMBER  = AUGUST    + 1;
   private static final int OCTOBER    = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER  + 1;
  
  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   
   /**
   * An array containing the names of the months
   *  
   */
   private static String[] months      = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

   /**
   * An array containing the names of the days
   *  
   */
   private static String[] dayOfWeek   = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor called..." );  // replace this with the actual code
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      return year > 1582 && year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);  
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
      // find the months that have fixed 31 days 
      if( month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ) return 31;
      // find the months that have fixed 30 days
      else if( month == 4 || month == 6 || month == 9 || month == 11 ) return 30;
      // on leap year feb. has 29 days on leap year and 28 normally, so we have to calculate which  
      else if( isLeapYear( year ) && month == 2 ) return 29;
      else if( month == 2 ) return 28;
      // if none then return 0
      else return 0; 
   }
 

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     return day1 == day2 && month1 == month2 && year1 == year2; 
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     if( year1 > year2 ) return 1;        // if first year greater than second year
     else if( year1 < year2 ) return -1;  // if first year less than second year 
     else {                                // years are the same
      if( month1 > month2 ) return 1;
        else if( month1 < month2) return -1;
        else {
          if( day1 > day2 ) return 1;
            else if( day1 < day2 ) return -1;
             else return 0;               //the dates are the same
        }
     } 
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
      boolean monthValid = 1 <= month && month <= 12;
      boolean dayValid = 1 <= day && day <= daysInMonth(month, year);
      boolean yearValid = 1582 <= year && year <= 9999; // 1582 was the year that leap year began on the Gregorian calendar
      return monthValid && dayValid && yearValid; 
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
      switch( month - 1 ) {
         case 0: 
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
           return months[month - 1]; 
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      switch( day - 1 ) {
         case 0: 
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
           return dayOfWeek[day - 1];
         default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }

 /**
   * A method to count the number of days in a year (used in dayBetween method)
   */
  public static long daysInYear( long day1, long month1, long day2, long month2, long year ) {
    long dayCount = 0;
    if( month1 == month2 ) {
     dayCount = day2 - day1;
     } else {
     // month2 > month1
        for( long m = month1 + 1; m < month2; m++ ) {
        dayCount += daysInMonth( m, year );
        }
        dayCount += daysInMonth( month1, year ) - day1;
        dayCount +=  day2; // from month2
     }
     return dayCount;
  } 

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      long dayCount = 0; 
      //if the dates are switched
      if( compareDate( month1, day1, year1, month2, day2, year2 ) == 1 ) {
        dayCount = daysBetween( month2, day2, year2, month1, day1, year1 );
        return dayCount;
      }

      if( year1 == year2 ) {
        dayCount = daysInYear( day1, month1, day2, month2, year1 );
      } else {
      // year2 > year1
        for( long y = year1 + 1; y < year2; y++ ) {
          dayCount += isLeapYear( y ) ? 366 : 365; // all full years
        }
        dayCount += daysInYear( day1, month1, 31, 12, year1 );
        dayCount += daysInYear( 0, 1, day2, month2, year2 );
      } 
      return dayCount;
   } 

}
