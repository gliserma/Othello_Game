package othello.players;

/**
 * Maintains the current score for the game.
 * 
 * @author nicholasgliserman
 *
 */
public class Score {
	private Player black;
	private Player white;
	private int blackScore;
	private int whiteScore;
	
	/**
	 * Constructs a new score object at the start of the game.
	 * 
	 * @param black
	 * @param white
	 */
	public Score(Player black, Player white)
	{
		this.black = black;
		this.white = white;
		setInitialScore();
	}
	
	/**
	 * Resets the score to the initial state
	 * at a beginning of a new game.
	 * 
	 */
	public void setInitialScore()
	{
		this.blackScore = 2;
		this.whiteScore = 2;
	}

	/**
	 * Gets scores from each player each turn to
	 * refresh the score values.
	 */
	public void updateScore()
	{
		this.blackScore = black.getScore();
		this.whiteScore = white.getScore();
	}
	
	// GETTERS
	/**
	 * Returns a message to be displayed in the victory screen at the end of the game.
	 * 
	 * @return	the victory message 
	 */
	public String getVictoryMessage()
	{
		if (this.blackScore > this.whiteScore)
		{
			return "Black Wins";
		}
		if (this.whiteScore > this.blackScore)
		{
			return "White Wins";
		}
		return " Tie Game";
	}
	
	
	/**
	 * Returns the score message to be displayed in the GUI
	 * while the game is ongoing.
	 * 
	 * @param black 	boolean to determine which score message to send
	 * @return			the message containing the current score
	 */
	public String getScoreMessage(boolean black)
	{
		String message;
		if (black)
		{
			message = "BLACK: " + blackScore;
		}
		else
		{
			message = "WHITE: " + whiteScore;
		}
		return message;
	}
}
