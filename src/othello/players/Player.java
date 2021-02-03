package othello.players;

/**
 * Represents a user playing the game.
 * 
 * @author nicholasgliserman
 *
 */
public class Player {
	Player opponent;
	boolean black;
	int currentScore = 0;
	
	/**
	 * Creates a new player object, with a boolean
	 * representing whether they are black or not
	 * (i.e. white).
	 * 
	 * Essentially, this class creates both players at the same time.
	 * When the first player is created with the boolean set to true,
	 * the logic creates an opponent player with the boolean set to false.
	 * 
	 * @param  black	boolean value representing whether the player is black or not
	 */
	public Player(boolean black)
	{
		if (black)
		{
			setColor(black);
			this.opponent = new Player(!this.black);
			this.opponent.opponent = this;
		}
		else
		{
			setColor(black);
		}

	}
	
	/**
	 * Adds one to the player's score.
	 * 
	 * Allows outside objects, especially disks, to increase
	 * the player's score when they are placed or
	 * flipped from the opponent's color.
	 */
	public void incrementScore() {this.currentScore++;}
	
	/**
	 * Subtracts one from the player's score.
	 * 
	 * Allows outside objects, especially disks, to decrease
	 * the player's score when they are flipped to the
	 * opponent's color.
	 */
	public void decrementScore() {this.currentScore--;}
	
	/**
	 * Returns the player's score to zero.
	 * 
	 * Allows a new game to start with the old
	 * scores erased.
	 */
	public void resetScore() {this.currentScore = 0;}
	
	// SETTERS
	public void setColor(boolean black) {this.black = black;}
	
	// GETTERS
	public Player getOpponent() {return this.opponent;}
	public int getScore() {return this.currentScore;}
	public boolean isBlack() {return this.black;}

}
