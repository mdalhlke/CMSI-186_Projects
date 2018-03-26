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

   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
   private Ball[] balls = null;
   private Ball pole = new Ball(0.0, 0.0, 0.0, 0.0, 1.0);
   //field is centered at (0.0) and is 1000ft by 1000ft
   private double fieldrx = 500.0;
   private double fieldry = 500.0;
   
   public SoccerSim() {
      super();
      this.pole.setRadius(3.0 / 12);
   }

   //counts the number of balls on the field
   public int ballCount(String[] args) {
      try {
         if (args.length % 4 == 1) {
            return ((args.length - 1) / 4);
         } else if (args.length % 4 == 0) {
    	    return (args.length / 4);
         } else {
         	throw new IllegalArgumentException("bad args count");
         }
      }
      catch (NumberFormatException nfe) {
         throw new NumberFormatException("invalid number");
      }
   }
   
   //getting the time slice
   public double getTimeSlice(String[] args) {
      double timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS; 
      try { 
         if (args.length % 4 == 1) {
            timeSlice = Double.parseDouble(args[args.length - 1]);
            if (timeSlice <= 0.0) {
            	throw new IllegalArgumentException("bad args count");
            }
         } else if (args.length % 4 != 0) {
         	throw new IllegalArgumentException("bad args count");
         }
      }
      catch (NumberFormatException nfe) {
         throw new NumberFormatException("invalid number");
      }
      return timeSlice;
   }

   //runs the program
   public void run(String[] args) {
      try {
      	 double timeSlice = this.getTimeSlice(args);
         Timer time = new Timer(timeSlice);
      	 System.out.println(time.toString());
      	 int count = this.ballCount(args);
         this.balls = new Ball[count];
         int j = 0;
         for (int i = 0; i < count * 4; i += 4) {
            this.balls[j] = new Ball(Double.parseDouble(args[i]), Double.parseDouble(args[i + 1]), Double.parseDouble(args[i + 2]), 
            	Double.parseDouble(args[i + 3]), timeSlice);
            System.out.println(this.balls[j].toString());
            j++;    
         }
         while (!this.checkIfGameOver()) {
         	time.tick();
         	System.out.println(); //for looks
         	System.out.println(time.toString());
         	for (int i = 0; i < count; i++) {
         	   this.balls[i].move();
         	   System.out.print((i + 1) + ": ");
         	   System.out.println(this.balls[i].toString());
         	}
         }
      }
      catch(NumberFormatException nfe) {
   	     throw new NumberFormatException("invalid number");
      }
   }

   public boolean checkIfGameOver() {     
      boolean over = false;
      if (this.balls.length == 0) {
         over = true;
      	 System.out.println("NO COLLISION, NO BALLS");
      } else if (this.ballsStopped()) {
      	 over = true;
      	 System.out.println("NO COLLISION POSSIBLE, ALL BALLS STOPPED");
      }  else if (this.outOfBounds()) {
      	 over = true;
      	 System.out.println("NO COLLISION POSSIBLE, ALL BALLS WENT OUT OF BOUNDS");
      }else if (this.ballCollision()) {
      	 over = true;
      	 System.out.println("COLLISION WITH BALL");
      } else if (this.poleCollision()){
      	 over = true;
      	 System.out.println("COLLISION WITH POLE");
      }
       return over;   
   }
   
   public double distance(Ball b1, Ball b2) {
      return (Math.sqrt(Math.pow(b1.getX() - b2.getX(),2) + Math.pow(b1.getY() - b2.getY(),2)));
   }

   public boolean ballCollision() {
      for (int i = 0; i < this.balls.length; i++) {
         for (int j = i + 1; j < this.balls.length; j++) {
            if (this.distance(this.balls[i], this.balls[j]) <= (balls[i].getRadius() + balls[j].getRadius())) {
               return true;
            }
         }
      }
      return false;
   }

   public boolean poleCollision() {
      for (int i = 0; i < this.balls.length; i++) {
         if (this.distance(this.balls[i], this.pole) <= (this.balls[i].getRadius() + this.pole.getRadius())) {
            return true;
         }
      }
      return false;
   }

   public boolean outOfBounds() {
      boolean out = true;
      for (int i = 0; i < this.balls.length; i++) {
         if ((Math.abs(this.balls[i].getX()) <= Math.abs(this.fieldrx)) && (Math.abs(this.balls[i].getY()) <= Math.abs(this.fieldry))) {
            out = false;
         } else {
         	this.balls[i].stop();
         }
      }
      return out;
   }

   public boolean ballsStopped() {
      for (int i = 0; i < this.balls.length; i++) {
         if (this.balls[i].isMoving()){
            return false;
         }
      }
      return true;
   }

   public static void main(String[] args) {
      SoccerSim soccersim = new SoccerSim();
      soccersim.run(args);
   }
}
