package othello.board_components;

import java.util.ArrayList;

import othello.players.Player;

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
	
	/**
	 * Creates an empty space object, to be
	 * populated with neighbors after the board
	 * creates all of the space objects.
	 * 
	 */
	public Space() {}
	
	/**
	 * A new turn resets whether this space is playable
	 */
	public void reset()
	{
		this.rowsEndingHere = new ArrayList<>();
		this.playable = false;
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
	 */
	public void reconstructRowsStartingHere(Player current)
	{
		if (!isEmpty() && this.disk.currentPlayer.isBlack() == current.isBlack())
		{
			for (int i = 0; i < this.rowsStartingHere.size(); i++)
			{
				this.rowsStartingHere.get(i).reconstruct(current.isBlack());
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
		if (playable && isEmpty())
		{
			this.disk = new Disk(player, turn);
			flipDisks();
		}
		else throw new Exception("This space is not playable");
	}
	
	// GETTERS
	public boolean isPlayable() {return this.playable;}
	
	public boolean isEmpty() 
	{
		if (this.disk == null) {return true;}
		else {return false;}
	}
	
	public Space getNeighboringSpace(Direction direction) throws Exception
	{
		return this.neighbors.getNeighboringSpace(direction);
	}
	
	public Disk getDisk() {return this.disk;}
}
