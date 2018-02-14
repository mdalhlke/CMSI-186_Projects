/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  B.J. Johnson
 *  Date          :  2017-02-06
 *  @author       :  Maya Dahlke
 *  Date          :  2017-02-06
 *  Description   :  This class provides the data fields and methods to describe a single game die.  A
 *                   die can have "N" sides.  Sides are randomly assigned sequential pip values, from 1
 *                   to N, with no repeating numbers.  A "normal" die would thus has six sides, with the
 *                   pip values [spots] ranging in value from one to six.  Includes the following:
 *                   public Die( int nSides );                  // Constructor for a single die with "N" sides
 *                   public int roll();                         // Roll the die and return the result
 *                   public int getValue()                      // get the value of this die
 *                   public void setSides()                     // change the configuration and return the new number of sides
 *                   public String toString()                   // Instance method that returns a String representation
 *                   public static String toString()            // Class-wide method that returns a String representation
 *                   public static void main( String args[] );  // main for testing porpoises
 *
 *  Notes         :  Restrictions: no such thing as a "two-sided die" which would be a coin, actually.
 *                   Also, no such thing as a "three-sided die" which is a physical impossibility without
 *                   having it be a hollow triangular prism shape, presenting an argument as to whether
 *                   the inner faces are faces which then should be numbered.  Just start at four for
 *                   minimum number of faces.  However, be aware that a four-sided die dosn't have a top
 *                   face to provide a value, since it's a tetrahedron [pyramid] so you'll have to figure
 *                   out a way to get the value, since it won't end up on its point.
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-06  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-02-17  B.J. Johnson  Filled in method code
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Random;

public class Die {

  /**
   * private instance data
   */
   private int sides;
   private int pips;
   private final int MINIMUM_SIDES = 4;

   // public constructor:
  /**
   * constructor
   * @param nSides int value containing the number of sides to build on THIS Die
   * @throws       IllegalArgumentException
   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
   */
   public Die( int nSides ) {
      if(nSides < MINIMUM_SIDES) {
          throw new IllegalArgumentException("Number is invalid");
      } 
      this.sides = nSides;
      this.pips = this.sides; //side 1 has 1 pip, side 2 has 2 pips, ... , side n has n pips   
   }

  /**
   * Roll THIS die and return the result
   * @return  integer value of the result of the roll, randomly selected
   */
   public int roll() {   //rolls and returns the value 
      this.pips = (int) (Math.random() * this.sides + 1);
      return this.pips;
   }

  /**
   * Get the value of THIS die to return to the caller; note that the way
   *  the count is determined is left as a design decision to the programmer
   *  For example, what about a four-sided die - which face is considered its
   *  "value"?
   * @return the pip count of THIS die instance
   */
   public int getValue() {
      return 0;
   }

  /**
   * @param  int  the number of sides to set/reset for this Die instance
   * @return      The new number of sides, in case anyone is looking
   * @throws      IllegalArgumentException
   */
   public void setSides( int sides ) {
   }

  /**
   * Public Instance method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public String toString() {
      return "";
   }

  /**
   * Class-wide method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public static String toString( Die d ) {
      return "";
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      System.out.println( "Hello world from the Die class..." );
      
     //testing the constructor   
      System.out.println( "Tests for the constructor: " );
    
      System.out.print( "Testing 1 should return 'number can't be used':" ); 
      try { Die d = new Die(1);  System.out.println(" yeah");}                                   
      catch( Exception e ) { System.out.println ( "number can't be used" ); }
      
      System.out.print( "Testing 2 should return 'number can't be used':" );
      try { Die d = new Die(2); System.out.println("yeah");}                                     
      catch( Exception e ) { System.out.println ( "number can't be used" ); }

      System.out.print( "Testing 3 should return 'number can't be used':" );
      try { Die d = new Die(3); System.out.println("yeah");}                                     
      catch( Exception e ) { System.out.println ( "number can't be used" ); }
     
      System.out.print( "Testing 4 should return 'yeah':" ); 
      try { Die d = new Die(4); System.out.println("yeah"); }                                    
      catch( Exception e ) { System.out.println ( "number can't be used" ); }
      
      System.out.print( "Testing 7 should return 'yeah':" ); 
      try { Die d = new Die(6); System.out.println("yeah");}                                  
      catch( Exception e ) { System.out.println ( "number can't be used" ); }
     
     
     //testing roll() method
      System.out.println( "Tests for roll(): " ); //rolling n times should give you n values each between 1 and this.sides
      
      System.out.print( "Rolling dice w/ 4 sides 5 times should return 5 nums 1-4:  " );
      try { 
          Die d = new Die(4); 
          for (int i = 0; i < 5; i++) { //roll is 5 times
              if (0 < d.roll() && d.roll() <= 4) { //checking if the number is between 1 and 4
                  System.out.print( d.roll()+" " );
              }
          } System.out.print( "\n" );
      }   
      catch( Exception e ) { System.out.println ( "nope" ); }

      System.out.print( "Rolling dice w/ 6 sides 3 times should return 3 nums 1-6:  " );
      try { 
          Die d = new Die(6); 
          for (int i = 0; i < 3; i++) { //roll is 3 times
              if (0 < d.roll() && d.roll() <= 6) { //checking if the number is between 1 and 6
                  System.out.print( d.roll()+" " );
              }
          } System.out.print( "\n" );
      }   
      catch( Exception e ) { System.out.println ( "nope" ); }

      System.out.print( "Rolling dice w/ 7 sides 9 times should return 9 nums 1-7:  " );
      try { 
          Die d = new Die(7); 
          for (int i = 0; i < 9; i++) { //roll is 9 times
              if (0 < d.roll() && d.roll() <= 7) { //checking if the number is between 1 and 7
                  System.out.print( d.roll()+" " );
              }
          } System.out.print( "\n" );
      }   
      catch( Exception e ) { System.out.println ( "nope" ); }

      System.out.print( "Rolling dice w/ 5 sides 2 times should return 2 nums 1-5:  " );
      try { 
          Die d = new Die(5); 
          for (int i = 0; i < 2; i++) { //roll is 2 times
              if (0 < d.roll() && d.roll() <= 5) { //checking if the number is between 1 and 5
                  System.out.print( d.roll()+" " );
              }
          } System.out.print( "\n" );
      }   
      catch( Exception e ) { System.out.println ( "nope" ); }   
   }

}
