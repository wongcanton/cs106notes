import java.applet.*;
import acm.graphics.*;
import acm.util.*;

public class BreakoutBall extends GOval {
	
	/* Constructor */
	public BreakoutBall(int radius) {
		super(radius * 2, radius * 2);
		this.setFilled(true);
	}

	
	/* Instance variables */
	private double vx, vy;
	public int ballDiam = Breakout.BALL_RADIUS * 2;
	/* Instance variable for audio clip */
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	
	
	/**
	 * Method: setXVelocity
	 * @param vx double vx: velocity to set
	 * @return void
	 */
	public void setXVelocity(double vx) {
		this.vx = vx;
	}
	
	
	/**
	 * Method: setYVelocity
	 * @param vy double vy: velocity to set
	 * @return void
	 */
	public void setYVelocity(double vy) {
		this.vy = vy;
	}
	
	
	/**
	 * Method: getXVelocity
	 * @return vx float vx: velocity
	 */
	public double getXVelocity() {
		return this.vx;
	}
	
	
	/**
	 * Method: getYVelocity
	 * @return vx float vx: velocity
	 */
	public double getYVelocity() {
		return this.vy;
	}
	
	
	/**
	 * Method: isAtLeftWall
	 * Returns if the ball is at the left wall.
	 * @return boolean
	 */
	public boolean isAtLeftWall() {
		return (this.getX() <= 0);
	}
	
	
	/**
	 * Method: isAtRightWall
	 * Returns if the ball is at the right wall.
	 * @return boolean
	 */
	public boolean isAtRightWall() {
		return (this.getX() >= Breakout.WIDTH - ballDiam);
	}
	
	
	/**
	 * Method: isAtTopWall
	 * Returns if the ball is at the top wall.
	 * @return boolean
	 */
	public boolean isAtTopWall() {
		return (this.getY() <= 0);
	}
	
	
	/**
	 * Method: isAtBottomWall
	 * Returns if the ball is at the bottom wall.
	 * @return boolean
	 */
	public boolean isAtBottomWall() {
		return (this.getY() >= (Breakout.HEIGHT - ballDiam));
	}
	
	
	/**
	 * Method: isAtPaddle
	 * Returns if the ball is at the paddle.
	 * @param GRect GRect paddle: GRect object for the paddle
	 * @return boolean
	 */
	public boolean isAtPaddle(GRect paddle) {
		boolean atPaddleTop = (
			(this.getY() + ballDiam) <= (paddle.getY() + paddle.getHeight()) &&
			(this.getY() + ballDiam) >= paddle.getY()
		);
		boolean withinPaddleLength = (
			(this.getX() >= paddle.getX()) &&
			(this.getX() + ballDiam) <= (paddle.getX() + paddle.getWidth())
		);
		return (atPaddleTop && withinPaddleLength);
	}
	
	
	/**
	 * Method: moveBall
	 * Moves the ball according to vx and vy.
	 * @param GRect GRect paddle: GRect object for the paddle
	 * @return void
	 */
	public void moveBall(GRect paddle) {
		// Check for wall bounces off wall
		if (isAtLeftWall() || isAtRightWall()) {
			bounceClip.play();
			this.vx = -this.vx; 
		}
		if(isAtTopWall()) {
			bounceClip.play();
			vy = -vy;
		}
		// Check if ball is at paddle
		if(isAtPaddle(paddle)) {
			//Reversing x is redundant and makes the game too predictable
			bounceClip.play();
			this.vy = -this.vy;
		}
		this.move(this.vx, this.vy);
		pause(Breakout.BALL_PAUSE);
	}
}
