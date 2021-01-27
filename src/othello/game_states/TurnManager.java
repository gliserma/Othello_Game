package othello.game_states;

import java.util.Scanner;

import javafx.scene.layout.Pane;
import othello.ConsoleBoard;
import othello.board_components.Board;
import othello.players.Player;
import othello.players.Score;
import othello.windows.BoardView;

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
	private BoardView viewBoard;
	private ConsoleBoard visualBoard = new ConsoleBoard();
	private Scanner input = new Scanner(System.in);
	private boolean turnDone = false;
	
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
		this.viewBoard = new BoardView(this.board, 75, this);
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
	
	
	public void startTurn()
	{
		this.turnDone = false;
		incrementTurn();
		
		// PRINT STATEMENTS
		if (this.currentPlayer.isBlack())
		{
			System.out.print(this.currentTurn + ". BLACK's TURN...");
		}
		else
		{
			System.out.print(this.currentTurn + ". WHITE's TURN...");
		}
		
		this.board.newTurn(this.currentPlayer);
		int playableSpaces = this.board.getPlayableSpaces();
		
		System.out.print("PLAYABLE SPACES: " + playableSpaces + " ... ");
		
		if (playableSpaces == 0) // IF NO PLAYABLE SPACES: 
		{
			incrementSkippedTurns();
			this.viewBoard.refresh();
			System.out.println("ABOUT TO SKIP TURN");
			
			if (this.numTurnsSkipped == 2) {
				this.viewBoard.gameOverView();
				System.out.println("GAME OVER");}
			else {startTurn();}
		}
		else
		{
			// RESET SKIPPED TURNS to ZERO
			resetSkippedTurns();
			this.viewBoard.refresh();
		}
		
	}
	
	public void finishTurn(int x, int y)
	{
		try {
			this.board.setNewDisk(y, x, this.currentPlayer, this.currentTurn);
			updateScore();
			this.score.updateScore();
			this.score.displayScores();
			this.turnDone = true;
			setNextPlayer();
			startTurn();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
//	public void takeTurn()
//	{
//		incrementTurn();
//		
//		// BOARD IS VISUALIZED
//		
//		if (this.board.newTurn(this.currentPlayer) == 0) // IF NO PLAYABLE SPACES: 
//		{
//			incrementSkippedTurns();
//
//		}
//		else
//		{
//			// RESET SKIPPED TURNS to ZERO
//			resetSkippedTurns();
//			this.viewBoard.refresh();
//			//this.visualBoard.getBoardData(this.board);
//			//this.visualBoard.printBoard();
//
//			// PLAYER INPUT
//			// HOW to DO THIS VIA THE GUI BOARD?
//			
//			//while (!enterSpace()) {}
//			updateScore();
//			this.score.updateScore();
//			this.score.displayScores();
//		}
//		// FIND PLAYABLE BOARD SPACES
//
//		setNextPlayer();
//	}
	
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
	public Pane getPane() {return this.viewBoard.getBoardViewPane();}
	public boolean isTurnDone() {return this.turnDone;}
}
