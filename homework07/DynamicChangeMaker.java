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
      this.validateArgDenoms(denoms);
      this.validateArgTarget(target);
      this.denoms = denoms;
      this.target = target; 
      //Tuple denoms = new Tuple(denomiations);
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate the arguments passed in of the denominations 
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException 
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateArgDenoms(int[] value) {
      for (int i = 0; i < value.length; i++) {
         for (int j = 0; j < value.length; j++) {
            if (value[i] == value[j] && i != j) {
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
   *  Method to validate the arguments passed in of the target value 
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException 
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateArgTarget(int value) {
      return true;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method that makes change using dynamic programming - this where all the action happens :) 
   *  @params  none
   *  @return  int    that gives the optimal solution  of cents (smallest coin count)
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static Tuple makeChangeWithDynamicProgramming(int[] denoms, int target) {
      DynamicChangeMaker dcm = new DynamicChangeMaker(denoms, target);
      return dcm.makeChange();
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method that makes change using dynamic programming - this where all the action happens :) 
   *  @params  none
   *  @return  int    that gives the optimal solution  of cents (smallest coin count)
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public Tuple makeChange() {
      int rowCount = this.denoms.length;
      int columnCount = this.target + 1; 
      Tuple ttable[][] = new Tuple[rowCount][columnCount];
      for (int i = 0; i < rowCount; i++) {       //i = 0
         for (int j = 0; j < columnCount; j++) { //j = 3
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
               //System.out.println("lb:" + lookBack);
               //System.out.println("i:" + i);
               if (lookBack < 0){
                  //t2 = Tuple.IMPOSSIBLE;
               } else {
               	   //System.out.println(ttable[i][lookBack]);
                  t2 = ttable[i][lookBack]; 
               } 
               //System.out.println(t1);
               //System.out.println(t2);
               tempResult = t1.add(t2);
               //check if ripple down is needed
               if ((i - 1) >= 0 && !ttable[i - 1][j].isImpossible()){
                  if (tempResult.isImpossible() || tempResult.total() > ttable[i - 1][j].total()) { 
                     tempResult = ttable[i - 1][j];
                  }                
               }
            }
            ttable[i][j] = tempResult; 
           // System.out.println("i: " + i + " j: " + j + " tt: " + ttable[i][j]);
         }
      }
      int answer = ttable[0][columnCount - 1].total(); 
      Tuple answerTuple = ttable[0][columnCount - 1]; 
      //System.out.println(answer);
      for (int i = 1; i < rowCount; i++){
         if (answer > ttable[i][columnCount - 1].total() || answer == 0) { 
            answer = ttable[i][columnCount - 1].total(); 
            answerTuple = ttable[i][columnCount - 1];   
         } 
         //System.out.println(ttable[i][columnCount - 1]);
         //System.out.println(i);
         //System.out.println(columnCount - 1);

      }
      System.out.println(answerTuple);
      return answerTuple;
   }
            
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {  // "1,4,2 7"
      String[] d = args[0].split(",");
      int[] denominations = new int[d.length];
      for (int i = 0; i < denominations.length; i++) {
         denominations[i] = Integer.parseInt(d[i]);
      }
      int target = Integer.parseInt(args[1]);
      Tuple result = DynamicChangeMaker.makeChangeWithDynamicProgramming(denominations, target);
      System.out.println(result);
      //Tuple[][] t1 = new Tuple [args(0).length][args(1).length + 1];
      System.exit( 0 );
   }

}


/**************************************
            if( j == 0 ) {
               // Otherwise, this is NOT column zero
            } else {
               // if we can't take one of the denominations out of the value of "j"
               //  impossible, at least temporarily
               if( some_check_to_see_if_we_can_take_ONE_thing_out_of_the_current_value ) {

                 // look backward to see if there is a valid/impossible solution
                 //  if there is, copy it over and add/replace the one that is there
                  if( some_check_to_see_if_we_are_ABLE_to_look_backwards ) {

                    // if the cell looking backward is NOT an "IMPOSSIBLE", add it
                    if (c != "IMPOSSIBLE") {

                  }

                 // if this is NOT row zero we need to look above to see if there is
                 //  a better/non-impossible solution; if so, copy it down
                  if( i != 0 ) {

                    // if the cell above is impossible, basically do nothing since
                    //  this the current cell is already IMPOSSIBLE

                    // else if the cell above has a total that is less than the current
                    //  cell, copy it down
                     }
                  }

              // ELSE -- we *CAN* take one current denomination out
               } else {

                 // make a new tuple with a one in the current demonimation index


                 // look backward to see if there is a valid/impossible solution
                  if( (j - denominations[i]) >= 0 ) {

                    // if it's IMPOSSIBLE, mark the current cell IMPOSSIBLE, too

                    // else, add the previous cell to the current cell
                  }

                 // if this is NOT row zero we need to look above to see if there is
                 //  a better/non-impossible solution; if so, copy it down
                  if( i != 0 ) {

                    // if the cell above is impossible, basically do nothing since
                    //  this the current cell is already IMPOSSIBLE

                    // else if the cell above has a total that is less than the current
                    //  cell, copy it down
                     }
                  }
               }
            }
         }
****/

