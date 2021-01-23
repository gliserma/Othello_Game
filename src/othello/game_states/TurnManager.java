package othello.game_states;

import othello.board_components.Board;
import othello.players.Player;
import othello.players.Score;

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
	
	/**
	 * Starts the play state of the game.
	 * 
	 * @param usernameBlack
	 * @param usernameWhite
	 */
	public TurnManager(String usernameBlack, String usernameWhite) 
	{
		initializeGame(usernameBlack, usernameWhite);
	}
	
	public void initializeGame(String usernameBlack, String usernameWhite)
	{
		initializePlayers(usernameBlack, usernameWhite);
		initializeScore();
		initializeBoard(currentPlayer);
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
	
	private void initializeBoard(Player black) {this.board = new Board(black);}
	
	public void takeTurn()
	{
		if (this.board.newTurn(this.currentPlayer) == 0) // IF NO PLAYABLE SPACES: 
		{
			
			// incrementSkippedTurns();
			// if (isGameDone) {} FIND THE WINNER, GO TO VICTORY SCREEN
			// else {setNextPlayer();}
		}
		// FIND PLAYABLE BOARD SPACES

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
