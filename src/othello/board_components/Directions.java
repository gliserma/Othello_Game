package othello.board_components;

import java.util.ArrayList;

/**
 * Contains a collection of the eight possible directions to move from
 * one space to all possible neighboring spaces in the game of Othello.
 * 
 * @author nicholasgliserman
 *
 */
public class Directions {

	/**
	 * Contains all eight cardinal and intermediate directions 
	 * to move between spaces on the Othello board.
	 * 
	 * @author nicholasgliserman
	 *
	 */
	ArrayList<Direction> directions = new ArrayList<>();
	
	
	/**
	 * Instantiates all eight possible directions, i.e.:
	 * 
	 * [-1,-1][0,-1][1,-1]
	 * [-1, 0]      [1, 0]
	 * [-1, 1][0, 1][1, 1]
	 * 
	 * 
	 * @author nicholasgliserman
	 *
	 */
	public Directions()
	{
		for (int i = -1; i < 2; i++)
		{
			for (int j = -1; j < 2; j++)
			{
				try 
				{
					Direction dir = new Direction(i, j);
					directions.add(dir);
				} 
				catch (Exception e) {}
			}
		}
	}
	
	// GETTERS
	public ArrayList<Direction> getDirections() {return this.directions;}
	
}
