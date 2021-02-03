package othello.board_components;

import java.util.ArrayList;

/**
 * List of adjacent spaces in a single direction.
 * 
 * This class contains the logic to determine
 * the playable spaces on the Othello Board. 
 * 
 * Each space contains between three (corners) and eight
 * rows (inside), representing all the possible lines
 * that can be formed FROM that space. This space will
 * always be the starting space for this row.
 * 
 * Each row contains a starting space, an end space,
 * and (potentially) a list of "between" spaces. It
 * also contains a single direction, which determines 
 * from the starting space, which spaces will be added
 * to the row. Every turn, each row reconstructs itself
 * to determine whether the end space is "playable" by
 * the current player.
 * 
 * If the row ends with a "playable" space, it attaches
 * itself to that space. Spaces with rows so attached
 * appear to the player as "playable". If the player then
 * chooses that space, the "between" spaces in the row are
 * flipped.
 * 
 * @author nicholasgliserman
 *
 */

public class Row {
	Space start;
	Direction direction;
	ArrayList<Space> between = new ArrayList<>();
	Space end;

	/**
	 * Constructs a row object that begins at an origin space
	 * and travels in a constant direction.
	 * 
	 * @param  start      the space where the row begins
	 * @param  direction  the constant direction the row travels while constructing itself
	 */
	public Row(Space start, Direction direction)
	{
		setStart(start);
		setDirection(direction);
	}
	
	/**
	 * Rebuilds the row object in a new turn.
	 * 
	 * In a given turn, if the space has a disk of the color matching
	 * the current player, the row will rebuild itself from the origin space.
	 * Travelling neighbor by neighbor in a constant direction, the row
	 * class will add spaces to the "between" array if they contain disks
	 * of the color opposite to the starting space. If any of these disks is the
	 * same color or the very first neighboring space is empty, this process
	 * will stop as their is no playable space at the end of the row. If, however,
	 * the row ultimately finds an empty space where a new disk could be placed,
	 * the row will attach itself to this terminal space -- so that the space
	 * knows it is playable in that turn and, if it is played, which disks
	 * to flip.
	 * 
	 * 
	 * @param startingDiskBlack		boolean value indicating the color of the first disk in the row
	 */
	public void reconstruct(boolean startingDiskBlack)
	{
		try 
		{
			// GET FIRST NEIGHBORING SPACE (IF EXISTS)
			Space firstNeighbor = this.start.getNeighboringSpace(this.direction);
			// CONTINUE IF FIRST NEIGHBOR HAS DISK
			if (!firstNeighbor.isEmpty())
			{
				// CONTINUE IF FIRST NEIGHBOR's DISK IS DIFFERENT COLOR
				if (firstNeighbor.getDisk().isBlack() != startingDiskBlack)
				{
					// ADD TO BETWEEN LIST
					this.between.add(firstNeighbor);
					Space nextNeighbor = firstNeighbor;
					boolean next = true;
					while (next)
					{
						//FIND NEXT NEIGHBOR WHILE WE REMAIN ON BOARD
						nextNeighbor = nextNeighbor.getNeighboringSpace(this.direction);
						
						// IF SPACE IS EMPTY, IT IS PLAYABLE
						if (nextNeighbor.isEmpty())
						{
							setEnd(nextNeighbor);
							this.end.addRowEndingHere(this);
							this.end.setPlayableTrue();
							next = false;
						}
						// IF DISK BELONGS TO SAME PLAYER AS STARTING DISK, STOP
						else if (nextNeighbor.getDisk().isBlack() == startingDiskBlack) 
						{
							next = false;
						}
						// DISK BELONGS TO OPPOSITE PLAYER AS STARTING DISK, CONTINUE
						else 
						{
							this.between.add(nextNeighbor);
						}
					}
				}
			}
		} 
		catch (Exception e) {} // CASES WHERE WE HIT THE EDGE OF THE BOARD
	}
	
	/**
	 * Switches the color of all the disks in the "between" ArrayList.
	 * 
	 * This method is activated by the space where this row terminates 
	 * if a player places a disk in that space.
	 * 
	 */
	public void flipDisks()
	{
		for (int i = 0; i < between.size(); i++)
		{
			between.get(i).flipThisDisk();
		}
		
	}
	
	/**
	 * Restores the row to its original settings.
	 * 
	 * The row keeps its original starting space and
	 * direction while dropping the collection of between
	 * spaces as well as the terminal space. This allows
	 * the row to reconstruct itself in future turns to
	 * discover playable spaces with a new configuration
	 * of disks on the board. 
	 * 
	 */
	public void reset()
	{
		this.between = new ArrayList<>();
		this.end = null;
	}
	
	// SETTERS
	private void setStart(Space start) {this.start = start;}
	private void setEnd(Space end) {this.end = end;}
	private void setDirection(Direction direction) {this.direction = direction;}
	
	// GETTERS
}
