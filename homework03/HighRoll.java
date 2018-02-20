/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  high roll game using die set
 *  Author        :  Maya Dahlke
 *  Date          :  2017-02-15
 *  Description   :  Implement a Textual User Interface (TUI) on the command line. This will display
 *                   a list of options to the user, and will prompt for input. Based on that input your 
 *                   program will do what the user selected, then will display the results, a blank
 *                   line or two, and then re-display the options.
 *                   Option 1 in the list must be: ROLL ALL THE DICE
 *                   Option 2 in the list must be: ROLL A SINGLE DIE
 *                   Option 3 in the list must be: CALCULATE THE SCORE FOR THIS SET
 *                   Option 4 in the list must be: SAVE THIS SCORE AS HIGH SCORE
 *                   Option 5 in the list must be: DISPLAY THE HIGH SCORE
 *                   Option 6 in the list must be: ENTER 'Q' TO QUIT THE PROGRAM
 *
 *  Warnings      :  None
 *  Exceptions    :  
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

   public static void main(String args[]) {
      System.out.println("Welcome to the HighRoll Game!");
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      
      try {
         System.out.println("How many dice do you want?: ");
         System.out.print( ">>" );
         int count = Integer.parseInt(input.readLine());
         System.out.println("You have " + count + " dice.");

         System.out.println("How many sides do you want the dice to have? (must be higher than 3): ");
         System.out.print( ">>" );
         int sides = Integer.parseInt(input.readLine());
         System.out.println("The dice have " + sides + " sides.");

         DiceSet d = new DiceSet(count, sides); 

         int highscore = 0;
        
         while(true) {
      	    System.out.println("Press '1' to roll the dice. ");
            System.out.println("Press '2' to roll a single dice.");
            System.out.println("Press '3' to calculate the score for this set.");
            System.out.println("Press '4' to save this score as a high score.");
            System.out.println("Press '5' to display the high score.");
            System.out.println("Press 'q' to quit the program.");

            System.out.print(">>");
            String inputLine = null;
            
            try {  

               inputLine = input.readLine();
               
               if(0 == inputLine.length()) {                    //if the user enters something other than the options above
                  System.out.println("Enter a number 1-5:");
                  System.out.println("You typed: " + inputLine);
               }
               
               if('1' == inputLine.charAt(0)) {
                  d.roll();
                  System.out.println("The dice have been rolled." );
                  System.out.println("You rolled:" + DiceSet.toString(d));
               }
               
               if('2' == inputLine.charAt(0)) {
                  System.out.println("Which dice do you want to roll?: ");
               	  int index = Integer.parseInt(input.readLine());
               	  d.rollIndividual(index - 1);
               	  System.out.println("Dice " + index + " has been rolled.");
               	  System.out.println("You rolled a: " + d.getIndividual(index - 1));
               	  System.out.println("Now you have:" + DiceSet.toString(d));
               }
               
               if('3' == inputLine.charAt(0)) {
            	   int score = d.sum();
            	   System.out.println("Your score is: " + score);
               }
               
               if('4' == inputLine.charAt(0)) {
                   highscore = d.sum();
                   System.out.println(highscore + " score has been saved as the high score.");
               }
               
               if('5' == inputLine.charAt(0)) {
            	  System.out.println("Your high score is: " + highscore);

               }
              
               if('q' == inputLine.charAt(0)) {
                  break;
               }
            } 
            catch(IOException ioe) {
               System.out.println("Caught IOException");
            }
         }
      }
      catch(IOException ioe) {
            System.out.println("Caught IOException");
      }
   }
}
