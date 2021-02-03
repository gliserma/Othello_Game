package othello.board_components;

import java.util.ArrayList;

/**
 * Represents all the Neighbor objects
 * for a given origin space.
 * 
 * The board assigns these neighbor objects
 * to this neighbors collection as part of the
 * initialization process. Passing a direction
 * into this class will return a specific neighbor
 * (or throw an exception if there is no neighbor in 
 * that direction). This facilitates the Row objects
 * as each one works to construct itself by traveling
 * from one space to another in a constant direction.
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
	public Neighbors() {}
	
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
	/**
	 * Returns the space reached from the current space by travelling in a particular direction.
	 * 
	 * @param   direction    The direction to find an adjacent space.
	 * @return	Space        The adjacent space reached by traveling in the inputted direction
	 * @throws  Exception    Cases where there are no spaces in a particular direction (specifically edges and corners)
	 */
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

	/**
	 * Returns all the directions it is possible to travel from a given space.
	 * 
	 * Spaces in the middle of the board will allow travel in all eight directions
	 * but this is not the case for spaces along edges or in corners. Therefore,
	 * the list of all directions also informs how many rows should emanate from
	 * a given space.
	 * 
	 * @return  The complete set of directions it is possible to travel from this space
	 */
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

