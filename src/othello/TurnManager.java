package othello;

import othello.board_components.Board;

/**
 * Controls the flow of the game while
 * in the play state
 * 
 * @author nicholasgliserman
 *
 */
public class TurnManager {
	private int currentTurn = 0;
	private int numTurnsSkipped = 0;
	private Player currentPlayer;
	private Score score;
	private Board board;
	
	public TurnManager()
	{
		
	}
	
	public void initializeGame(String usernameBlack, String usernameWhite)
	{
		initializePlayers(usernameBlack, usernameWhite);
		initializeScore();
		initializeBoard();
	}
	
	// GAME INITIALIZATION METHODS
	private void initializePlayers(String usernameBlack, String usernameWhite)
	{
		Player black = new Player(true);
		Player white = black.getOpponent();
		black.setUsername(usernameBlack);
		white.setUsername(usernameWhite);
		setPlayer(black);
	}
	
	private void initializeScore()
	{
		this.score = new Score(this.currentPlayer, this.currentPlayer.getOpponent());
	}
	
	private void initializeBoard() {this.board = new Board();}
	
	public void takeTurn()
	{
		// FIND PLAYABLE BOARD SPACES
			// IF NO PLAYABLE SPACES: 
				// incrementSkippedTurns();
				// if (isGameDone) {} FIND THE WINNER, GO TO VICTORY SCREEN
				// else {setNextPlayer();}
			// ELSE: resetSkippedTurns();
		
		// PLAYER CHOOSES SPACE
		// BOARD IS UPDATED
		// SCORE IS UPDATED
		
		this.score.updateScore();
		setNextPlayer();
		
	}
	
	// OPTIONAL METHOD: TODO
	// public void goBackOneTurn() {}
	
	// HELPER METHODS
	private void updateScore(){this.score.updateScore();}
	private void incrementTurn() {this.currentTurn++;}
	private void incrementSkippedTurns() {this.numTurnsSkipped++;}
	private void resetSkippedTurns() {this.numTurnsSkipped = 0;}
	
	private boolean isGameDone() 
	{
		if (this.numTurnsSkipped == 2) {return true;}
		return false;
	}
	
	// SETTERS
	public void setNextPlayer() {this.currentPlayer = this.currentPlayer.getOpponent();}
	public void setPlayer(Player player) {this.currentPlayer = player;}
	
	// GETTERS
	public Player getWinner() throws Exception {return score.determineWinner();}
}
