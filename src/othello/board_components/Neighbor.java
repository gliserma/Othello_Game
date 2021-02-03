package othello.board_components;

/**
 * Represents the space next to an origin space, reached by moving in
 * a given direction.
 * 
 * While spaces in the middle of the board will have eight
 * neighbors (i.e. one in each direction), spaces on the edge will
 * have no more than five (three in the case of corner spaces).
 * The neighbors belonging to a particular space are initially set
 * by the Board object at the start of the game.
 * 
 * @author nicholasgliserman
 *
 */
public class Neighbor 
{
	private Space destination;
	private Direction direction;
	
	/**
	 * Creates neighbor for a destination space on the board
	 * 
	 * @param  direction    The vector of movement to reach the destination from the origin space
	 * @param  destination  The adjacent space reached by travelling in particular direction
	 */
	public Neighbor(Direction direction, Space destination)
	{
		setDirection(direction);
		setDestination(destination);
	}
	
	
	// GETTERS
	public Space getSpace() {return this.destination;}
	public Direction getDirection() {return this.direction;}

	// SETTERS
	public void setDirection(Direction direction) {this.direction = direction;}
	public void setDestination(Space destination) {this.destination = destination;}
}
