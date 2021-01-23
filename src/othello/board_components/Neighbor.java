package othello.board_components;

/**
 * Represents the space next to an
 * origin space, reached by moving in
 * a given direction.
 * 
 * @author nicholasgliserman
 *
 */
public class Neighbor 
{
	private Space destination;
	private Direction direction;
	
	/**
	 * Creates neighbor for a destination space ON the board
	 * 
	 * @param direction
	 * @param destination
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
