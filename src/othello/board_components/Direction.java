package othello.board_components;


/**
 * Contains information to move from one space to a
 * neighboring space.
 * 
 * @author nicholasgliserman
 *
 */

public class Direction {
	public int x;
	public int y;
	
	/**
	 * Encodes the x and y values of the direction.
	 * 
	 * x and y values (x, y) range between -1 and 1. (0, 0) is
	 * not allowed as a possible pair (the constructor throws
	 * and exception in this case) because that would represent
	 * the position of the current space. (-1, -1) would represent
	 * a space to the top left while (0, 1) would represent a 
	 * space directly beneath.
	 * 
	 * @param x
	 * @param y
	 */
	public Direction(int x, int y) throws Exception
	{
		// CHECK VALUES BETWEEN -1 and 1
		if (x < -1 || x > 1 || y < -1 || y > 1)
		{
			throw new Exception("Invalid direction (x and y must be between -1 and 1)");
		}

		// CHECK VALUE is NOT (0, 0)
		if (x != 0 && y != 0)
		{
			setX(x);
			setY(y);
		}
		else throw new Exception("0, 0 is not a valid direction");
	}
	
	/**
	 * Determine if an input direction is the same
	 * as this direction object.
	 * 
	 * @param comparison
	 * @return
	 */
	public boolean isSame(Direction comparison)
	{
		if (comparison.getX() == this.x && comparison.getY() == this.y) 
		{
			return true;
		}
		return false;
	}
	
	// Setters
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	
	// Getters
	public int getX() {return this.x;}
	public int getY() {return this.y;}

}
