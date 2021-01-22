package othello.board_components;

import java.util.ArrayList;

import othello.Player;

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
	Player startDiskPlayer;
	Direction direction;
	ArrayList<Space> between = new ArrayList<>();
	Space end;
	boolean valid = false;
	
	/*
	 * TODO: boolean permanentlyInvalid
	 * 
	 * Having such a field to stop the row from resetting
	 * and reconstructing itself would make it difficult
	 * to ever go back a turn.
	 */
	
	public Row(Space start, Direction direction)
	{
		
	}
	
	public void reconstruct()
	{
		// IS THERE A DISK ON THIS SPACE?
			// IF NO: A row should not start here. STOP
			// IF YES: CONTINUE
			// GET FIRST NEIGHBOR
			// IS THERE A SPACE HERE?
				// NO: This row is not playable. STOP.
				// YES: Is this space empty?
					// IF EMPTY, it is not playable. STOP
					// IF NOT EMPTY:
						// Does the disk belong to the same player as starting disk?
							// If yes, STOP
							// If no, FIND NEXT NEIGHBOR
				// WHILE WE REMAIN ON BOARD
					// GET NEIGHBOR
					// IS THERE A SPACE HERE?
						// NO: This row is not playable. STOP
						// YES: We can continue
					// IS THIS SPACE EMPTY?
						// YES: This is a playable space
							// Assign this space to this.end attribute
							// Attach this row to the this.end space vis-a-vis
							// this.end.addRowEndingHere(this);
							// STOP
						// NO: Does the disk here belong to the same player as starting disk?
							// YES: This row is not playable. STOP
							// NO: Repeat Loop, i.e. GET NEIGHBOR
	}
	
	public void flipDisks()
	{
		for (int i = 0; i < between.size(); i++)
		{
			between.get(i).flipThisDisk();
		}
		
	}
	
	public void reset()
	{
		
	}
	

}
