package othello.game_states;

import java.util.Scanner;

import othello.ConsoleBoard;
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
	private ConsoleBoard visualBoard = new ConsoleBoard();
	private Scanner input = new Scanner(System.in);
	
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
		this.visualBoard.getBoardData(this.board);
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
		incrementTurn();
		
		// BOARD IS VISUALIZED
		
		if (this.board.newTurn(this.currentPlayer) == 0) // IF NO PLAYABLE SPACES: 
		{
			incrementSkippedTurns();

		}
		else
		{
			// RESET SKIPPED TURNS to ZERO
			resetSkippedTurns();
			
			this.visualBoard.getBoardData(this.board);
			this.visualBoard.printBoard();

			// PLAYER INPUT
			while (!enterSpace()) {}
			updateScore();
			this.score.updateScore();
			this.score.displayScores();
		}
		// FIND PLAYABLE BOARD SPACES

		setNextPlayer();
		
	}
	
	private boolean enterSpace()
	{
		System.out.println("ENTER NEW SPACE");
		System.out.println("Player " + this.currentPlayer.getUsername() + "'s Turn");

		// PLAYER CHOOSES SPACE
		int y = enterNumber("Y");
		int x = enterNumber("X");
		
		try {
			this.board.setNewDisk(y, x, this.currentPlayer, this.currentTurn);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	private int enterNumber(String value)
	{
		System.out.print("Enter new " + value + " value: ");
		int valueInputted = Integer.parseInt(this.input.nextLine());
		return valueInputted;
	}
	
	// OPTIONAL METHOD: TODO
	// public void goBackOneTurn() {}
	
	// HELPER METHODS
	private void updateScore(){this.score.updateScore();}
	private void incrementTurn() {this.currentTurn++;}
	private void incrementSkippedTurns() {this.numTurnsSkipped++;}
	private void resetSkippedTurns() {this.numTurnsSkipped = 0;}
	
	public boolean isGameDone() 
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
