package othello.players;

public class Player {
	String username;
	Player opponent;
	boolean black;
	int currentScore = 0;
	
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
	
	public void placeDisk()
	{
		
	}
	
	// OPTIONAL to IMPLEMENT
	// public void undoLastAction() {}
	
	public void incrementScore() {this.currentScore++;}
	public void decrementScore() {this.currentScore--;}
	
	// SETTERS
	public void setColor(boolean black) {this.black = black;}
	public void setUsername(String username) {this.username = username;}
	
	// GETTERS
	public Player getOpponent() {return this.opponent;}
	public String getUsername() {return this.username;}
	public int getScore() {return this.currentScore;}
	public boolean isBlack() {return this.black;}

}
