/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  Simultaneously, on a perfectly flat playground, at time 00:00:00.0 (hrs:mins:secs), an arbitrary number of soccer balls are kicked, 
 *                   all at different speeds and directions. We wish to find out, via a discrete simulation of the system, whether a collision will ever 
 *                   take place, and, if so, where and when. Each ball has a radius of 4.45 inches and weighs one pound. The center of the playground is
 *                   presumed to be the point (x,y) = (0.0,0.0).
 *  author        :  Maya Dahlke
 *  Date started  :  2018-03-13
 *  Description   :  This class provides a bunch of methods for SoccerSim.class
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class SoccerSim {

   //pole
   private static final double polePosx   = 100.0;
   private static final double polePosy   = 4.0;
   private static final double poleRadius = 3.0;
   //field
   private static final double[] trc = {  250,  250 };
   private static final double[] tlc = { -250,  250 };
   private static final double[] brc = {  250, -250 };
   private static final double[] blc = { -250, -250 };

   public SoccerSim() {
      super();

   }

  //creates the pole
   public void pole() {
      double radius = 4.45;
      double rx = 2.0;
      double ry = 3.0;  
   }

   //creates the field
   public void field() {
      double toprightconer = (250,-250);
      double topleftconer
      toprightconer
   }

   //counts the number of balls on the field
   public void ballCount(String args[]) {
      if(args.length % 4 == 1) {
         (args.length - 1) / 4;
      } else if (args.length % 4 == 0) {
    	 args.length / 4;
      }
      else {
         System.out.print("invalid number of args");
         System.exit(1);  
      }
   }

   //loops through to see if their was a collision
   public void GameOver( String args[] ) {
      //converts all the args in the String array to Double's
      double[] doubleArr = new double[args.length];
      for (int i = 0; i < doubleArr.length; i++) {
         doubleArr[i] = Double.parseDouble(args[i]);
      }
      for(int i = 0; i < doubleArr.length; i++) {   
         for(int j = 0; i < doubleArr.length; i++)
			//ball and ball collide
			if(Math.sqrt((j - i) * (x2 - x1) + (y2 - y1) * (y2 - y1)) <= 8.9) {
			System.out.println("Two balls collided " + " at " + " at time " + "seconds"); //Two balls collided at (2,3) at time 5 secs
			System.exit(0); 
			}
			//ball and pole collide
			if(ball and pole collide) {
			System.out.println("A ball collided with the pole " + " at " + " at time " + "seconds"); //... collided at (2,3) at time 5 secs
			System.exit(0);
			}
			//all the balls went out of the field
			if(ball and pole collide) {
			System.out.println("All the balls went out of the field " + " at time " + "seconds"); //...field at time 5 secs
			System.exit(0);
		   }
		   //all the balls are at rest 
		}
      }
   }
}
