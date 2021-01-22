package othello.board_components;

import java.util.ArrayList;

/**
 * Represents all the Neighbor objects
 * for a given origin space.
 * 
 * @author nicholasgliserman
 *
 */
public class Neighbors 
{
	ArrayList<Neighbor> neighbors = new ArrayList<>();
	
	
	public Neighbors()
	{
		
	}
	
	public void addNeighboringSpace(Neighbor neighbor)
	{
		//TODO: Add logic to weed out a neighbor already in the set
		
		neighbors.add(neighbor);
	}
	
	/*
	 * TODO: Which option is better?
	 * 
	 * A) if a space does not have a neighbor in a given direction, simply do not
	 * create a neighbor in that direction?
	 * 
	 * B) create the neighbor but rely on the boolean onBoard to return the neighbor.
	 * 
	 * Probably option B...worth talking over.
	 * 
	 */
	
	// GETTERS
	public Space getNeighboringSpace(Direction direction) throws Exception
	{
		for (int i = 0; i < this.neighbors.size(); i++)
		{
			if (direction.isSame(this.neighbors.get(i).getDirection()))
			{
				return this.neighbors.get(i).getSpace();
			}
		}
		throw new Exception("No space in this direction");
	}
	
	public ArrayList<Direction> getDirections()
	{
		ArrayList<Direction> directions = new ArrayList<>();
		
		for (int i = 0; i < this.neighbors.size(); i++)
		{
			directions.add(this.neighbors.get(i).getDirection());
		}
		
		return directions;
	}
}

