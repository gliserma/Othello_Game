package othello;

import javafx.scene.layout.Pane;
import othello.board_components.Board;
import othello.gui_elements.GUIBoard;
import othello.gui_elements.ScoreController;
import othello.players.Player;
import othello.players.Score;

/**
 * Controls the flow of the game.
 * 
 * @author nicholasgliserman
 *
 */
public class TurnManager {
	private int currentTurn = 0;
	private int numTurnsSkipped = 0;
	private Player currentPlayer;
	private Score score;
	private ScoreController scoreController;
	private Board board;
	private GUIBoard viewBoard;
	private boolean firstGame = true;
	
	/**
	 * Creates a TurnManager, which starts a new game.
	 * 
	 * Initializes the players, initializes the score,
	 * initializes the board, and creates a graphic
	 * representation of that board.
	 * 
	 */
	public TurnManager() 
	{
		initializeGame();
	}
	
	private void initializeGame()
	{
		initializePlayers();
		initializeScore();
		initializeBoard(currentPlayer);
		this.viewBoard = new GUIBoard(this.board, 75, this);
	}
	
	/**
	 * Starts a new game when the TurnManager has already 
	 * been initialized.
	 * 
	 * Resets the game stats, reinitializes the board to its
	 * start state, and launches the first turn. This method
	 * allows for a new game to start after an existing game
	 * concludes.
	 * 
	 */
	public void newGame()
	{
		// REST STATS
		this.currentTurn = 0;
		this.numTurnsSkipped = 0;
		
		// RESET THE SCORE
		this.currentPlayer.resetScore();
		this.currentPlayer.getOpponent().resetScore();
		this.score.setInitialScore();
		
		// SET BLACK as CURRENT PLAYER
		if (!this.currentPlayer.isBlack()) {setNextPlayer();}
		
		// REINITIALIZE BOARD
		this.board.initializeBoard(this.currentPlayer);
		this.scoreController.refresh(score, "BLACK");
		this.viewBoard.refresh();
		
		// START NEW GAME
		startTurn();
	}
	
	// GAME INITIALIZATION METHODS
	private void initializePlayers()
	{
		Player black = new Player(true);
		Player white = black.getOpponent();
		setCurrentPlayer(black);
	}
	
	private void initializeScore()
	{
		this.score = new Score(this.currentPlayer, this.currentPlayer.getOpponent());
	}
	
	private void initializeBoard(Player black) {this.board = new Board(black);}
	
	/**
	 * Initiates the events in the beginning of a turn.
	 * 
	 * Contains the logic for the events which unfold
	 * in the first part of a turn before the player inputs
	 * the board space they wish to play. Tests to determine
	 * whether the current player has any moves to make and if
	 * not, whether there are legal moves left in the game. 
	 * If not, this class triggers the end of the game and transition
	 * into the Game Over view.
	 * 
	 */
	public void startTurn()
	{
		incrementTurn();
		this.board.newTurn(this.currentPlayer);
		
		// Launches the Title Screen if the Application has just
		// begun and the first game has not yet started.
		if (this.firstGame)
		{
			this.viewBoard.titleView(" OTHELLO!");
			this.firstGame = false;
		}
		else
		{
			int playableSpaces = this.board.getPlayableSpaces();
			if (playableSpaces == 0) // IF NO PLAYABLE SPACES: 
			{
				incrementSkippedTurns();
				this.viewBoard.refresh();
				// IF NO PLAYABLE SPACES for EITHER PLAYER: GAME OVER
				if (this.numTurnsSkipped == 2) 
				{
					this.viewBoard.titleView(this.score.getVictoryMessage());
				}
				else {startTurn();}
			}
			else // IF PLAYABLE SPACES EXIST
			{
				// RESET SKIPPED TURNS to ZERO
				resetSkippedTurns();
				this.viewBoard.refresh();
			}
		}
	}
	
	/**
	 * Starts the second part of a turn from when a player has input where
	 * they wish to place their disk on the board.
	 * 
	 * @param x int representing horizontal piece placement on the board
	 * @param y int representing vertical piece placement on the board
	 */
	public void finishTurn(int x, int y)
	{
		try {
			// PLACE DISK
			this.board.setNewDisk(y, x, this.currentPlayer, this.currentTurn);
			
			updateScore();
			setNextPlayer();
			
			// GENERATE Score Stats for GUI
			String turnMessage;
			if (this.currentPlayer.isBlack()) {turnMessage = "BLACK";}
			else {turnMessage = "WHITE";}
			this.scoreController.refresh(this.score, turnMessage);
			
			// LAUNCH Next Turn
			startTurn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// HELPER METHODS
	private void updateScore(){this.score.updateScore();}
	private void incrementTurn() {this.currentTurn++;}
	private void incrementSkippedTurns() {this.numTurnsSkipped++;}
	private void resetSkippedTurns() {this.numTurnsSkipped = 0;}
	
	/**
	 * Tests if the game is finished.
	 * 
	 * @return boolean true if the game is over.
	 */
	public boolean isGameDone() 
	{
		if (this.numTurnsSkipped == 2) {return true;}
		return false;
	}
	
	// SETTERS
	/**
	 * Sets the game's next player in the turn by reaching into the current
	 * player and getting their opponent.
	 */
	public void setNextPlayer() {this.currentPlayer = this.currentPlayer.getOpponent();}
	
	/**
	 * Sets the player whose turn it now is.
	 * 
	 * @param Player currentPlayer.
	 */
	public void setCurrentPlayer(Player currentPlayer) {this.currentPlayer = currentPlayer;}
	
	/**
	 * Provides the TurnManager with a ScoreController so that the turn manager can
	 * reflect the current score in the GUI.
	 * 
	 * @param scoreController
	 */
	public void setScoreController(ScoreController scoreController) 
	{
		this.scoreController = scoreController;
		this.scoreController.refresh(this.score, "BLACK");
		startTurn();
	}
	
	// GETTERS
	public Pane getPane() {return this.viewBoard.getBoardViewPane();}
	public Score getScore() {return this.score;}
}
