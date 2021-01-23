package othello.players;

public class Score {
	private Player black;
	private Player white;
	private int blackScore;
	private int whiteScore;
	
	public Score(Player black, Player white)
	{
		this.black = black;
		this.white = white;
		updateScore();
	}

	public void updateScore()
	{
		this.blackScore = black.getScore();
		this.whiteScore = white.getScore();
	}
	
	public void displayScores()
	{
		System.out.println("BLACK: " + this.blackScore + " vs. WHITE: " + this.whiteScore);
	}
	
	public Player determineWinner() throws Exception
	{
		if (this.blackScore > this.whiteScore)
		{
			return black;
		}
		if (this.whiteScore > this.blackScore)
		{
			return white;
		}
		throw new Exception("Tie Game");
	}
}
