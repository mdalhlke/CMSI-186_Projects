/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * @author    :  Maya Dahlke
 * Date       :  2018-03-27
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   private String internalValue = "";        // internal String representation of this BrobInt
   private int   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string 
   
   //private byte[] b   = null;
   private int[] digits = null;
   int length = 0;


  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) { 
      internalValue = value;

      //handles the sign character
      if( value.charAt(0) == '-' ) {
         sign = 1;
         value = value.substring(1);
      } else if( value.charAt(0) == '+' || value.charAt(0) == ' ' ) {
         value = value.substring(1);
      }
    
      this.validateDigits(value);

      //assigns string to the internal storage 
      length = value.length();
      digits = new int[length];

      try {
         for( int i = 0; i < length; i++ ) {
            digits[i] = Character.getNumericValue(value.charAt(( length - 1 ) - i ));
        }
      } catch (NumberFormatException nfe) {
         throw new NumberFormatException("invalid number");
      }

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits(String value) {
        for( int i = 0; i < value.length(); i++ ) {
           char c = value.charAt(i);
           if( c < 48 || c > 57 ) {
              throw new IllegalArgumentException("invalid");
           }
        }
        return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static int[] reverser( int[] b2 ) {
      for (int i = 0; i < b2.length / 2; i++) {
         int temp = b2[i];
         b2[i] = b2[b2.length - i - 1];
         b2[b2.length - i - 1] = temp;
      }
      return b2; 
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return sign
   *  @param none
   *  @return int
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int sign() {
      return sign;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return the correct representation of BrobInt 
   *  @param  gint         make the BrobInt 
   *  @return BrobInt that joins the array, reverses the number and returns a BrobInt 
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   private BrobInt makeBrobInt(String[] b, int sign) {
      StringBuffer buffer = new StringBuffer(String.join("", b)); //makes the result array into a string
      if (b.length == 0) {
        throw new IllegalArgumentException("illegal arg");  
      }
      if (sign == 1){
         buffer.append("-");
      }
      buffer = buffer.reverse();

      //getting rid of the zeros in the front
      String buffStr = buffer.toString();
      for (int i = 0, length = buffStr.length() - 1; i < length; i++) {
         if (buffStr.charAt(i) != '0') {
            buffStr = buffStr.substring(i);
            break;
         } 
      }
      BrobInt brob = new BrobInt(buffStr);
      return brob;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return the correct representation of BrobInt 
   *  @param  gint         make the BrobInt 
   *  @return BrobInt that joins the array, reverses the number and returns a BrobInt 
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt makePositiveBrobInt() {
      if (internalValue.indexOf("-") == 0) {
         BrobInt temp = new BrobInt(internalValue.substring(1));
         return temp;
      } else {
        return this;
      }
   }
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return the correct representation of BrobInt 
   *  @param  gint         make the BrobInt 
   *  @return BrobInt that joins the array, reverses the number and returns a BrobInt 
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt makeBrobIntDivide(int[] b, int sign) {
      if (b.length == 0) {
        throw new IllegalArgumentException("illegal arg");  
      }
      String[] result = new String[b.length];
      for (int i = 0; i < b.length; i++) {
         result[i] = String.valueOf(b[i]);
      }

      StringBuffer buffer = new StringBuffer(String.join("", result)); //makes the result array into a string
      if (sign == 1){
         buffer.append("-");
      }
      BrobInt brob = new BrobInt(buffer.toString());
      return brob;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return the correct representation of BrobInt 
   *  @param  gint         make the BrobInt 
   *  @return BrobInt that joins the array, reverses the number and returns a BrobInt 
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt makeNegativeBrobInt() {
      if (internalValue.indexOf("-") == 0) {
         return this;
      } else {
        BrobInt temp = new BrobInt("-" + internalValue);
        return temp;
      }

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt b2 ) {
      int[] digits2 = b2.toArray();
      int carryOver = 0;
      int sum = 0, d1 = 0, d2 = 0;
      int len1 = digits.length; 
      int len2 = digits2.length;
      int length = Math.max(len2, len1) + 1;
      String[] result = new String[length];
      int sign2 = b2.sign();

      if (sign == sign2) {
         for (int i = 0; i < length; i++) {
            d1 = i < len1 ? digits[i] : 0;
            d2 = i < len2 ? digits2[i] : 0;
            sum = d1 + d2 + carryOver;
            if (sum > 9) {
               carryOver = 1;
               sum -= 10;
            } else {
                 carryOver = 0;
            }
            result[i] = Integer.toString(sum);
         } 
         return makeBrobInt(result, sign);
      } else {
         return subtract(b2);
      } 
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   private int compareAbsValue( BrobInt b ) {
      BrobInt b1 = this.makePositiveBrobInt();
      BrobInt b2 = b.makePositiveBrobInt();
      return b1.compareTo(b2); 
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt b2 ) {
      int[] digitsBig;
      int[] digitsSmall;
      int borrow = 0, diff = 0, d1 = 0, d2 = 0;
      int len1, len2;
      String[] result = null;
      int sign2 = b2.sign();
      int resultSign;
      
      //this.compareAbsValue(b2);
      if (sign == sign2) {
        if (sign == 0) {
           if (this.compareTo(b2) < 0) {
              digitsBig = b2.toArray();
              digitsSmall = digits;
              resultSign = 1;
           } else {
              digitsBig = digits;
              digitsSmall = b2.toArray();
              resultSign = 0;
           }
        } else {
          if (this.compareAbsValue(b2) < 0) {
              digitsBig = b2.toArray();
              digitsSmall = digits;
              resultSign = 0;
           } else {
              digitsBig = digits;
              digitsSmall = b2.toArray();
              resultSign = 1;
           }
        }
        len1 = digitsBig.length;
        len2 = digitsSmall.length;
        result = new String[len1];

        for (int i = 0; i < len1; i++) {
           d1 = digitsBig[i];
           d2 = i < len2 ? digitsSmall[i] : 0; 
           diff = d1 - d2;
           if ((d1 - borrow) - d2 < 0) {
              diff = (10 + d1 - borrow) - d2;
              borrow = 1;
           } else {
              diff = (d1 - borrow) - d2;
              borrow = 0;    
           }
           result[i] = Integer.toString(diff);
        }
        return makeBrobInt(result, resultSign);
      } else if (sign == 0 && sign2 == 1) {
           b2 = b2.makePositiveBrobInt();
           return this.add(b2);
      } else if (sign == 1 && sign2 == 0) {
           b2 = b2.makeNegativeBrobInt();
           return this.add(b2);
      }
      return this;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt b2 ) {
      int[] digits2 = b2.toArray();
      int[] longer = null;
      int[] shorter = null;
      int carry = 0;
      int k = 0;
      int len1 = digits.length; 
      int len2 = digits2.length;
      int length = Math.max(len2, len1);
      int length1 = Math.min(len2, len1);
      int[] product = new int[len1 + len2 + 1];
      int sign2 = b2.sign();

      if (len1 > len2) {
        longer = digits;
        shorter = digits2; 
      } else if (len1 == len2) {
         if (this.compareTo(b2) < 0) {
            longer = digits2;
            shorter = digits; 
         } else {
            longer = digits;
            shorter = digits2; 
         }
      } else {
         longer = digits2;
         shorter = digits; 
      }

      for (int i = 0; i < length1; i++) {                    //smaller number 12
         k = i;
         carry = 0;
         for (int j = 0; j < length; j++) {                  //bigger number  123
            product[k] += (longer[j] * shorter[i]) + carry;
            if (product[k] > 9) {
               carry = product[k] / 10;
               if (carry == 0) {
                  carry = 1;
               }
               product[k] = product[k] % 10;
            } else {
               carry = 0;
            }
            k++;
         }
         product[k] = carry;
      }
      if (carry >= 0) {
        product[k] = carry;
      }
      String[] result = new String[k + 1];
      for (int a = 0; a <= k; a++) {
         result[a] = String.valueOf(product[a]);
      }
      if (sign != sign2) {
         sign = 1;
      } else {
        sign = 0;
      }
      return makeBrobInt(result, sign);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  b2         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt dvs ) {
      int[] quotient = new int[digits.length];  
      int[] divisor = BrobInt.reverser(dvs.toArray());   //arg passed in  (d1) 
      int[] dividend = BrobInt.reverser(digits);         //this           (d2)
      int[] d3 = new int[digits.length + 1];   
      String[] result = null;
      int subVal;
      int n;
      int q;
      int idx = 0;
      int resultSign = 0;
      int[] temp;

      if (sign != dvs.sign()) {
         resultSign = 1;
      }
      System.out.println("yo" + this.compareTo(dvs));
      if (divisor.toString() == "0" ) {
         throw new IllegalArgumentException("Are you trying to break the code??");
      } else if (this.compareTo(dvs) == 0) {
         result = new String[] {"1"};
      } else if (this.compareTo(dvs) < 0) {   //d1>d2
         result = new String[] {"0"}; 
      } else {
         n = divisor.length;
         System.out.println("n " +n);
         d3 = Arrays.copyOfRange(dividend, 0, n); //extract that many characters from the front of d2 and put into d3
         System.out.println(d3.length);
         if (dvs.compareTo(makeBrobIntDivide(d3, 0)) > 0) { //d1>d3
            n += 1;
            d3 = Arrays.copyOfRange(dividend, 0, n); 
            System.out.println("d3 " + d3[0]);
            System.out.println("d3 " + d3[1]);
          }
          while (n <= dividend.toString().length()) {
             q = 0;
             BrobInt d3brob = new BrobInt(d3.toString());
             while (makeBrobIntDivide(d3, 0).compareTo(dvs) > 0) {  //d3>d1  
                String pc = makeBrobIntDivide(d3, 0).subtract(dvs).toString();
                System.out.println("y.     " + pc);
                int placehold = Integer.valueOf(pc);
                //System.out.println("y.     " + pc);
                int i = 0;
                d3 = new int[digits.length + 1];
                d3[i] = placehold;
                System.out.println("d.     " + d3[i]);
                i++;
                System.out.println("p" + placehold);
                System.out.println("%%%" + d3[i]);
                //d3 = new int[] {Integer.parseInt(placehold)};
                q++;
                if (placehold <= 0) {
                  break;
                }
             }
             quotient[idx] = q;
             System.out.println(quotient[0]);
             idx++;
             System.out.println(d3.length);
             d3[d3.length - 1] = 0; //same as multiplying by 10
             quotient[idx++] = 0;
             if (n++ == divisor.toString().length()) {
                break;
             }   
             temp = Arrays.copyOfRange(dividend, n - 1, n);//add current value of d3 to extracted digit [e.g., get "7" from d2, concat to d3 to make "197"]
             d3[d3.length - 1] += temp[0];
             break;
          } 
         return makeBrobIntDivide(quotient, resultSign);
      }
      return makeBrobInt(result, resultSign);
   }

// for implementation, in this discussion/comment/steps:
     //  0. divisor [passed in as ARGUMENT] is "d1"
     //     dividend [ME] is "d2"
     //     current dividend being handled is "d3"
     //     quotient is "q" and string length count is "n"
     //        for example, 56789 divided by 37: d1 == 37 and d2 == 56789
     //                     d3 starts with 56 and goes on adding single digits with each iteration
     //                     "q" starts at zero, and "n" starts at 2

     //  1. is d1 == 0?  if so, throw IllegalArgumentException
     //  IF ARGUMENT is equal to BrobInt.ZERO
     //     throw new IllegalArgumentException

     //  2. is d1 == d2? if so, return BrobInt.ONE

     //  3. is d1 > d2?  if so, return BrobInt.ZERO  [INTEGER ARITHMETIC!!!]

     //  4. otherwise, get length of d1 and put into "n"

     //  5. extract that many characters from the front of d2 and put into d3

     //  6. is d1 > d3?  if so, add one to "n" and re-extract characters from d2 into d3

     //  7. while "n" <= d2.toString().length()

        // a. while d3 > d1:
        //     i. subtract d1 from d3 [ gint.subtract( this ) ]
        //    ii. add one to quotient [ q.add( BrobInt.ONE ) ]

        // b. d3 now has any remainder [e.g., 56 - 37 = 19, "q" is one and d3 is 19]

        // c. if "n++" is equal to d1.toString().length() then we are done -- break

        // d. multiply d3 by 10 using d3.multiply( BrobInt.TEN )

        // e. multiply "q" by 10 using q.multiply( BrobInt.TEN )

        // f. extract next digit from d2 using d2.toString().substring( n-1, n )
        // g. add current value of d3 to extracted digit [e.g., get "7" from d2, concat to d3 to make "197"]

     //  END WHILE

     //  8. return "q" value which is already a BrobInt


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
   *        It takes into account the length of the two numbers, and if that isn't enough it does a
   *        character by character comparison to determine
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {

     // handle the signs here
      if( 1 == sign && 0 == gint.sign ) {
         return -1;
      } else if( 0 == sign && 1 == gint.sign ) {
         return 1;
      }

     // the signs are the same at this point
     // check the length and return the appropriate value
     //   1 means this is longer than gint, hence larger
     //  -1 means gint is longer than this, hence larger
      if( internalValue.length() > gint.internalValue.length() ) {
         return 1;
      } else if( internalValue.length() < gint.internalValue.length() ) {
         //System.out.println("k" +internalValue.length());
         return (-1);

     // otherwise, they are the same length, so compare absolute values
      } else {
         for( int i = 0; i < internalValue.length(); i++ ) {
            Character a = Character.valueOf( internalValue.charAt(i) );
            Character b = Character.valueOf( gint.internalValue.charAt(i) );
            if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
               return 1;
            } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
               return (-1);
            }
         }
      }
      return 0;
   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( new Long( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      /*String byteVersionOutput = "";
      for( int i = 0; i < byteVersion.length; i++ ) {
         byteVersionOutput = byteVersionOutput.concat( Byte.toString( byteVersion[i] ) );
      }
      byteVersionOutput = new String( new StringBuffer( byteVersionOutput ).reverse() );
      return internalValue;*/
      return internalValue;

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int[] toArray( ) {
      return digits;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      //System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      //System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
      BrobInt b1 = new BrobInt(args[0]);
      BrobInt b2 = new BrobInt(args[1]);
      BrobInt b3;
      b1.toArray();
      /*b3 = b1.add(b2);
      System.out.println(b3.toString());*/
      b3 = b1.divide(b2);
    //b3 = b1.reverser(b2);
      System.out.println(b3.toString());

      System.exit( 0 );
   }
}



