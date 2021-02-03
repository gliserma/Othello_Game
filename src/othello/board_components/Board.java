package othello.board_components;

import java.util.ArrayList;

import othello.players.Player;

/**
 * Manages the spaces throughout the Othello game.
 * 
 * When the board is initialized as an 8x8 grid
 * (i.e. 2d array) of spaces, it creates
 * the graph structure to connect neighboring
 * spaces. During the game, it iterates through
 * the spaces to find the ones are playable in
 * a given player's turn. When a player chooses
 * a space to place a disk, the board passes that
 * information to the space.
 * 
 * @author nicholasgliserman
 *
 */
public class Board {
	private int numCurrentPlayableSpaces;
	public Directions allPossibleDirections = new Directions();
	private int height = 8;
	private int width = 8;
	public Space[][] boardspaces = new Space[this.height][this.width];

	/**
	 * Constructs a new Board object by calling the
	 * initializeBoard method, which handles the core logic.
	 * 
	 * @param black   the player whose disk color is black and goes first
	 * 
	 */
	public Board(Player black)
	{
		initializeBoard(black);
	}
	
	/**
	 * Sets the board to its beginning state with two disks
	 * of each color in the center of the board.
	 * 
	 * 
	 * @param black   the player whose disk color is black and goes first
	 */
	public void initializeBoard(Player black)
	{
		initializeSpaces();
		// ADD CENTER DISKS to BOARD
		try {
			// SET BLACK PIECES
			this.boardspaces[3][4].setPlayableTrue();
			this.boardspaces[3][4].setDisk(black, 0);		
			this.boardspaces[4][3].setPlayableTrue();
			this.boardspaces[4][3].setDisk(black, 0);
			
			// SET WHITE PIECES
			this.boardspaces[3][3].setPlayableTrue();
			this.boardspaces[3][3].setDisk(black.getOpponent(), 0);		
			this.boardspaces[4][4].setPlayableTrue();
			this.boardspaces[4][4].setDisk(black.getOpponent(), 0);
			
			resetPlayableSpaces();
			
		} 
		catch (Exception e) {e.printStackTrace();}
	}
	
	private void initializeSpaces()
	{
		// CREATE SPACES
		for (int y = 0; y < this.height; y++)
		{
			for (int x = 0; x < this.width; x++)
			{
				boardspaces[y][x] = new Space();
			}
		}
		
		// FOR EACH SPACE, ADD NEIGHBORS
		for (int y = 0; y < this.height; y++)
		{
			for (int x = 0; x < this.width; x++)
			{
				Neighbors neighbors = createNeighbors(y, x);
				boardspaces[y][x].setNeighbors(neighbors);
			}
		}
	}
	
	private Neighbors createNeighbors(int originY, int originX)
	{
		Neighbors neighbors = new Neighbors();
		ArrayList<Direction> directions = this.allPossibleDirections.getDirections();
		for (int i = 0; i < directions.size(); i++)
		{
			int tempY = originY + directions.get(i).getY();
			int tempX = originX + directions.get(i).getX();		
			
			// IS THE NEIGHBOR ON THE BOARD?
			if (tempY < this.height && tempY >= 0)
			{
				if (tempX < this.width && tempX >= 0)
				{
					Neighbor temp = new Neighbor(directions.get(i), boardspaces[tempY][tempX]);
					neighbors.addNeighboringSpace(temp);
				}
			}
		}
		return neighbors;
	}
	
	/**
	 * Determines the playable spaces at the start
	 * of a turn for the current player.
	 * 
	 * @param currentPlayer   the player whose turn it currently is
	 */
	public void newTurn(Player currentPlayer)
	{
		resetPlayableSpaces();
		findPlayableSpaces(currentPlayer);
	}
	
	private void resetPlayableSpaces()
	{
		// RESET NUMBER OF PLAYABLE SPACES to ZERO
		this.numCurrentPlayableSpaces = 0;
		// ITERATE OVER BOARD & RESET EACH SPACE 
		for (int y = 0; y < this.height; y++)
		{
			for (int x = 0; x < this.width; x++)
			{
				boardspaces[y][x].reset();
			}
		}
	}
	
	private void findPlayableSpaces(Player currentPlayer)
	{
		// ITERATE OVER BOARD to RECONSTRUCT ROWS
		for (int y = 0; y < this.height; y++)
		{
			for (int x = 0; x < this.width; x++)
			{
				boardspaces[y][x].reconstructRowsStartingHere(currentPlayer);
			}
		}

		// ITERATE OVER BOARD to FIND PLAYABLE SPACES
		for (int y = 0; y < this.height; y++)
		{
			for (int x = 0; x < this.width; x++)
			{
				// IS THIS SPACE PLAYABLE?
				if (boardspaces[y][x].isPlayable()) {this.numCurrentPlayableSpaces++;}
			}
		}
	}
	
	// SETTERS
	/**
	 * Places a new disk on the board in the appropriate space.
	 * 
	 * @param x  the horizontal coordinate of the space on the board where the disk is to be placed
	 * @param y  the vertical coordinate of the space on the board where the disk is to be placed
	 * @throws Exception 
	 */
	public void setNewDisk(int y, int x, Player currentPlayer, int turn) throws Exception
	{
		Space placeDiskHere = this.boardspaces[y][x];
		placeDiskHere.setDisk(currentPlayer, turn);
	}
	

	// GETTERS
	public int getPlayableSpaces() {return this.numCurrentPlayableSpaces;}
	public Space getSpace(int y, int x) {return this.boardspaces[y][x];}
	public int getBoardHeight() {return this.height;}
	public int getBoardWidth() {return this.width;}
	
}
