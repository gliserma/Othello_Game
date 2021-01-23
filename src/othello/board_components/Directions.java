package othello.board_components;

import java.util.ArrayList;

/**
 * The eight possible directions to move from
 * one space to all possible neighboring spaces.
 * 
 * @author nicholasgliserman
 *
 */
public class Directions {

	/**
	 * Contains all eight directions to move between spaces in the game of Othello.
	 * 
	 * @author nicholasgliserman
	 *
	 */
	ArrayList<Direction> directions = new ArrayList<>();
	
	
	/**
	 * Instantiates all eight possible directions.
	 * 
	 * [-1, -1][0, -1][1, -1]
	 * [-1,  0][  x  ][1,  0]
	 * [-1,  1][0,  1][1,  1]
	 * 
	 * [1] [2] [3]
	 * [4] [ ] [5]
	 * [6] [7] [8]
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
