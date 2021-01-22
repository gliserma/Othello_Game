package othello.board_components;

import java.util.ArrayList;

import othello.Player;

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

	public Board()
	{
		initializeBoard();
	}
	
	private void initializeBoard()
	{
		initializeSpaces();
		// TODO: Add four disks to center spaces
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
	public void newTurn(Player currentPlayer)
	{
		resetPlayableSpaces();
		findPlayableSpaces(currentPlayer);
	}
	
	private void resetPlayableSpaces()
	{
		// ITERATE OVER BOARD & REST EACH SPACE 
	}
	
	private void findPlayableSpaces(Player currentPlayer)
	{
		// ITERATE OVER BOARD to RECONSTRUCT ROWS

		// ITERATE OVER BOARD to FIND PLAYABLE SPACES
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @throws Exception 
	 */
	public void setNewDisk(int x, int y, Player currentPlayer, int turn) throws Exception
	{
		Space placeDiskHere = this.boardspaces[y][x];
		placeDiskHere.setDisk(currentPlayer, turn);
	}
	
	
}
