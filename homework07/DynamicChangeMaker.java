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

   private int[] denoms = null;
   private int target = 0; 
   /**
   *  Constructor takes a int array and an int, check for valid args 
   *     and saves the values
   *  @param  value  String value to make into an int Tuple
   */
   public DynamicChangeMaker(int[] denoms, int target) {
      this.validateArgs(denoms, target);
      this.denoms = denoms;
      this.target = target; 
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate the arguments passed in of the denominations 
   *  @return  boolean  impossible tuple
   *  @throws  IllegalArgumentException 
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateArgs(int[] values, int value) {
      int doesNotPass = 0;
      for (int i = 0; i < values.length; i++) {
         
         for (int k = i + 1; k < values.length; k++) {
            if (values[i] + values[k] > value) {
               doesNotPass++;
            } else {
               break;
            }
         }
         if (doesNotPass == ((values.length - 1) * ((values.length - 1) + 1)) / 2) {
            throw new IllegalArgumentException("IMPOSSIBLE");
         }

         for (int j = 0; j < values.length; j++) {
            if (values[i] == values[j] && i != j) {
               throw new IllegalArgumentException("BAD DATA: arguments contain copies of a coin.");
            }
         }

         if (values.length <= 1) {
            throw new IllegalArgumentException("BAD DATA: not enough arguments.");
         }
         if (values[i] < 0 || value < 0) {
            throw new IllegalArgumentException("BAD DATA: negative argument not possible.");
         }
         if (values[i] == 0) {
            throw new IllegalArgumentException("BAD DATA: arguments contain nonsensical denomination of zero.");
         }

      }
      return true;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method that makes change using dynamic programming  
   *  @params  int[]  denoms
   *           int    target
   *  @return  Tuple    that gives the optimal solution  of cents (smallest coin count)
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static Tuple makeChangeWithDynamicProgramming(int[] denoms, int target) {
      DynamicChangeMaker dcm = new DynamicChangeMaker(denoms, target);
      return dcm.makeChange();
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method that makes change using dynamic programming - this where all the action happens :) 
   *  @params  none
   *  @return  Tuple    that gives the optimal solution  of cents (smallest coin count)
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public Tuple makeChange() {
      int rowCount = this.denoms.length;
      int columnCount = this.target + 1; 
      Tuple ttable[][] = new Tuple[rowCount][columnCount];
      for (int i = 0; i < rowCount; i++) {       
         for (int j = 0; j < columnCount; j++) { 
         	Tuple t1 = new Tuple(rowCount);
         	Tuple t2 = new Tuple(rowCount);
         	Tuple tempResult = new Tuple(rowCount); 
            if (j > 0) {
               int q = j/denoms[i];
               if (q == 0) {
                  t1 = Tuple.IMPOSSIBLE;
               } else {
                  t1.setElement(i, 1); 
               }
               int lookBack = j - denoms[i];
               if (lookBack < 0){
                  //do nothing
               } else {
                  t2 = ttable[i][lookBack]; 
               } 
               tempResult = t1.add(t2);
               //check if ripple down is needed
               if ((i - 1) >= 0 && !ttable[i - 1][j].isImpossible()){
                  if (tempResult.isImpossible() || tempResult.total() > ttable[i - 1][j].total()) { 
                     tempResult = ttable[i - 1][j];
                  }                
               }
            }
            ttable[i][j] = tempResult; 
         }
      }
      Tuple answerTuple = ttable[rowCount - 1][columnCount - 1]; 
      return answerTuple;
   }
            
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {  
      String[] d = args[0].split(",");
      int[] denominations = new int[d.length];
      for (int i = 0; i < denominations.length; i++) {
         denominations[i] = Integer.parseInt(d[i]);
      }
      int target = Integer.parseInt(args[1]);
      Tuple result = DynamicChangeMaker.makeChangeWithDynamicProgramming(denominations, target);
      System.out.println(result);
      System.exit( 0 );
   }

}
