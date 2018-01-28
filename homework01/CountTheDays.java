/**
 *  File name     :  CountTheDays.java
 *  Purpose       :  which outputs the absolute number of days between two given dates. 
 *  Author        :  Maya Dahlke
 *  Date          :  Jan 23 2018
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * 
 */

public class CountTheDays {
  public static void main(String[] args) {
    long month1 = Long.parseLong(args[0]);
    long day1 = Long.parseLong(args[1]);
    long year1 = Long.parseLong(args[2]);
    long month2 = Long.parseLong(args[3]);
    long day2 = Long.parseLong(args[4]);
    long year2 = Long.parseLong(args[5]);
    long result = CalendarStuff.daysBetween(month1, day1, year1, month2, day2, year2); 
    System.out.println(result);
  }
}
