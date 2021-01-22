package othello.board_components;

import java.util.ArrayList;

import othello.Player;

/**
 * Building block of the board, which can house a disk.
 * 
 * @author nicholasgliserman
 *
 */

public class Space 
{
	private Neighbors neighbors;
	private ArrayList<Row> rowsEndingHere = new ArrayList<>();
	private ArrayList<Row> rowsStartingHere = new ArrayList<>();
	private boolean playable = false;
	private Disk disk;
	
	public Space() {}
	
	/**
	 * A new turn resets whether this space is playable
	 */
	public void reset()
	{
		rowsEndingHere = new ArrayList<>();
		playable = false;
		for (int i = 0; i < this.rowsStartingHere.size(); i++)
		{
			this.rowsStartingHere.get(i).reset();
		}
	}
	
	/**
	 * If this space has a disk, reconstruct all of the rows
	 * starting in this space to discover if any of them
	 * lead to a playable space.
	 * 
	 * TODO: If we add a player parameter, we will only need to
	 * reconstruct rows starting on spaces with disks for 
	 * that player, thereby reducing the overall processing for the
	 * program each turn.
	 */
	public void reconstructRowsStartingHere()
	{
		if (!isEmpty())
		{
			for (int i = 0; i < this.rowsStartingHere.size(); i++)
			{
				this.rowsStartingHere.get(i).reconstruct();
			}
		}
	}
	
	/**
	 * flips the disk in this space.
	 */
	public void flipThisDisk()
	{
		if (this.disk != null) {this.disk.flip();}
	}
	
	/**
	 * flips all the "between" disks on all
	 * of the rows that end at this space.
	 * 
	 */
	private void flipDisks()
	{
		for (int i = 0; i < rowsEndingHere.size(); i++)
		{
			rowsEndingHere.get(i).flipDisks();
		}
	}
	
	/*
	 * TODO: Optional Method that would facilitate players
	 * to go back one turn. Only write this method IF we
	 * want to have this feature in the game.
	 *
	 *private void removeDisk()
	 *{
	 *	
	 *}
	*/
	
	private void initializeRowsStartingHere()
	{
		ArrayList<Direction> directions = this.neighbors.getDirections();
		for (int i = 0; i < directions.size(); i++)
		{
			this.rowsStartingHere.add(new Row(this, directions.get(i)));
		}
	}
	
	// SETTERS
	public void setNeighbors(Neighbors neighbors) 
	{
		this.neighbors = neighbors;
		initializeRowsStartingHere();
	}
	
	public void addRowEndingHere(Row terminus) {this.rowsEndingHere.add(terminus);}
	public void setPlayableTrue() {this.playable = true;}
	
	public void setDisk(Player player, int turn) throws Exception
	{
		if (playable && this.disk == null)
		{
			player.incrementScore();
			this.disk = new Disk(player, turn);
			flipDisks();
		}
		else throw new Exception("This space is not playable");
	}
	
	// GETTERS
	public boolean isEmpty() 
	{
		if (this.disk == null) {return true;}
		else {return false;}
	}
}
