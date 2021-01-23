package othello.board_components;

import java.util.ArrayList;

import othello.players.Player;

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

	public Row(Space start, Direction direction)
	{
		setStart(start);
		setDirection(direction);
	}
	
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
	 * direction. 
	 * 
	 * TODO: FINISH WRITING DESCRIPTION
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
