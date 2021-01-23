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
	
	/**
	 * Creates a neighbors object, which is initially empty.
	 * 
	 */
	public Neighbors()
	{
		
	}
	
	/**
	 * Populates the neighbors object by adding indvidual
	 * neighbor objects.
	 * 
	 * @param neighbor
	 */
	public void addNeighboringSpace(Neighbor neighbor)
	{
		neighbors.add(neighbor);
	}
	
	
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

