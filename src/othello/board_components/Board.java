package othello.board_components;

import java.util.ArrayList;

import othello.players.Player;

/**
 * The 8x8 grid of spaces where the game of 
 * Othello is played
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

	public Board(Player black)
	{
		initializeBoard(black);
	}
	
	private void initializeBoard(Player black)
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
	 * 
	 * @param currentPlayer
	 */
	public int newTurn(Player currentPlayer)
	{
		resetPlayableSpaces();
		findPlayableSpaces(currentPlayer);
		return this.numCurrentPlayableSpaces;
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
	 * 
	 * @param x
	 * @param y
	 * @throws Exception 
	 */
	public void setNewDisk(int y, int x, Player currentPlayer, int turn) throws Exception
	{
		Space placeDiskHere = this.boardspaces[y][x];
		placeDiskHere.setDisk(currentPlayer, turn);
	}
	

	// GETTERS
	public Space getSpace(int y, int x) {return this.boardspaces[y][x];}
	
}
