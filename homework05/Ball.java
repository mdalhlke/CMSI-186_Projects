/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  author        :  Maya Dahlke
 *  Date started  :  2018-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *                   for Homework 5. 
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/


public class Ball {
    
   private int posX;
   private int posY;
   private int velX;
   private int velY;
   private double timeslice;

   //constructor 
   public Ball(int posX, int posY, int velX, int velY, double timeslice) {
      this.posX = posX;
      this.posY = posY;
      this.velX = velX;
      this.velY = velY;
      this.timeslice = timeslice;
   }

   //methods

   public double locationBall() {
      //detemines balls location
   }

   public double moveBall() {
   	//move to another location using velocity
   }

   public double friction() {
       //slows each ball until the ball comes to rest - friction is a force that continuously decreases ball's speed at 1%/sec until
   	  // the ball is traveling less than one inch per sec, at which point it comes to rest.
   }

  public String toString() {

  }


}
