/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  To learn about dynamic programming.
 * @author    :  Maya Dahlke
 * Date       :  April 24 2018
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework07.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DynamicChangeMaker {
   /**
   *  Constructor takes a string and converts it into integer Tuple format, also check for 
   *     valid arguments
   *  @param  value  String value to make into an int Tuple
   */
   public DynamicChangeMaker(String value) {
      String[] d = value.split(",");
      int[] denomiations = new int[d.length];
      for (int i = 0; i < denomiations.length; i++) {
         denomiations[i] = Integer.parseInt(d[i]);
      }
      this.validateArgs(denomiations);


   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate the arguments passed in 
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException 
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateArgs(int[] value) {
      for (int i = 0; i < value.length; i++) {
         for (int j = 0; j < value.length; i++) {
            if (value[i] == value[j]) {
               throw new IllegalArgumentException("BAD DATA: arguments contain copies of a coin.");
            }
         }
         if (value.length <= 1) {
            throw new IllegalArgumentException("BAD DATA: not enough arguments.");
         }
         if (value[i] < 0) {
            throw new IllegalArgumentException("BAD DATA: negative argument not possible.");
         }
         if (value[i] == 0) {
            throw new IllegalArgumentException("BAD DATA: arguments contain nonsensical denomination of zero.");
         }
      }
      return true;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method that makes change using dynamic programming 
   *  @params  int[]    array of denominations 
   *           int      target amount of cents
   *  @return  boolean  true if all digits are good
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public makeChangeWithDynamicProgramming(int[] denom, int target) {
   	
   }



}















