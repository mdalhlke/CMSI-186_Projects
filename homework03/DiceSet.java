/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-09
 *  Author        :  Maya Dahlke
 *  Date          :  2017-02-15
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count; 
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
      if(count < 1 && sides < 4) {
        throw new IllegalArgumentException("doesn't make sense.");
      }

      this.ds = new Die[ count ];
      
      for(int i = 0; i < count; i++) {
          this.ds[i] = new Die(sides);
      }
      this.sides = sides;
      this.count = count;
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
      int add = 0;
      for(Die d : this.ds) {
         add += d.getValue();
      }
      return add;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
      for(Die d : this.ds) {
         d.roll();
      }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @throws IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
      if(0 > dieIndex || dieIndex > count - 1) {
        throw new IllegalArgumentException("index is out of range");
      }
      return this.ds[dieIndex].roll();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @throws IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      if(0 > dieIndex || dieIndex > count - 1) {
        throw new IllegalArgumentException("index is out of range");
      }
      return this.ds[dieIndex].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
      StringBuffer buffer = new StringBuffer();
      for(Die d : this.ds) {
        buffer.append(d.toString());
      }
      return buffer.toString();
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
      return ds.toString();
   }

  /**
   * @return  true if this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds ) {
      return (this.count == ds.count && this.sides == ds.sides);
   }
  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      //testing sum() method
      System.out.println( "Testing sum() method:" );

      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(6, 4); System.out.println( d.sum() <= 24 ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
      
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(5, 8); System.out.println( d.sum() <= 40 ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(5, 4); System.out.println( d.sum() <= 20 ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(8, 6); System.out.println( d.sum() <= 48 ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
      
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(7, 5); System.out.println( d.sum() <= 35 ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
      
     
      //testing rollIndividual() method
      System.out.println( "Testing rollIndividual() method:" ); 

      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(6, 4); System.out.println( d.rollIndividual(3) <= d.sides ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(5, 4); System.out.println( d.rollIndividual(2) <= d.sides ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(7, 8); System.out.println( d.rollIndividual(6) <= d.sides ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(5, 9); System.out.println( d.rollIndividual(4) <= d.sides ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(4, 6); System.out.println( d.rollIndividual(3) <= d.sides ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
     
      //testing getIndividual() method
      System.out.println( "Testing getIndividual() method:" ); 

      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(6, 4); System.out.println( d.getIndividual(3) <= d.sides ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(5, 4); System.out.println( d.getIndividual(2) <= d.sides ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
      
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(7, 8); System.out.println( d.getIndividual(6) <= d.sides ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
      
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(9, 5); System.out.println( d.getIndividual(7) <= d.sides ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(5, 7); System.out.println( d.getIndividual(4) <= d.sides ? "true" : "false" );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
     
       
      //testing toString() method
      System.out.println( "testing toString() method:" ); 

      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(6, 4); System.out.println( d.toString());}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(5, 7); System.out.println( d.toString());}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
      
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(9, 5); System.out.println( d.toString());}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(4, 5); System.out.println( d.toString());}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
      
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(8, 6); System.out.println( d.toString());}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
    
      
      //testing toString( DiceSet ds )
      System.out.println( "Testing toString( DiceSet ds ) method:" ); 

      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(6, 4); System.out.println( DiceSet.toString(d) );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(5, 7); System.out.println( DiceSet.toString(d) );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(9, 5); System.out.println( DiceSet.toString(d) );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(4, 5); System.out.println( DiceSet.toString(d) );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
     
      System.out.print( "should get true:" ); 
      try { DiceSet d = new DiceSet(8, 6); System.out.println( DiceSet.toString(d) );}                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }
      
     
      //testing isIdentical()
      System.out.println( "Testing isIdentical() method:" ); 

      System.out.print( "should get true:" ); 
      try { 
         DiceSet d1 = new DiceSet(6, 4);
         DiceSet d2 = new DiceSet(5, 7); 
         System.out.println( d1.isIdentical(d2) == false ? "true" : "false" );
      }                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }

      System.out.print( "should get true:" ); 
      try { 
         DiceSet d1 = new DiceSet(6, 4);
         DiceSet d2 = new DiceSet(6, 4); 
         System.out.println( d1.isIdentical(d2) == true ? "true" : "false" );
      }                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }

      System.out.print( "should get true:" ); 
      try { 
         DiceSet d1 = new DiceSet(8, 8);
         DiceSet d2 = new DiceSet(5, 8); 
         System.out.println( d1.isIdentical(d2) == false ? "true" : "false" );
      }                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }

      System.out.print( "should get true:" ); 
      try { 
         DiceSet d1 = new DiceSet(5, 7);
         DiceSet d2 = new DiceSet(5, 7); 
         System.out.println( d1.isIdentical(d2) == true ? "true" : "false" );
      }                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }

      System.out.print( "should get true:" ); 
      try { 
         DiceSet d1 = new DiceSet(5, 7);
         DiceSet d2 = new DiceSet(7, 5); 
         System.out.println( d1.isIdentical(d2) == false ? "true" : "false" );
      }                                  
      catch( Exception e ) { System.out.println ( " number can't be used" ); }


   }

}
