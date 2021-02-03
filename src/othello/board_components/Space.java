package othello.board_components;

import java.util.ArrayList;

import othello.players.Player;

/**
 * Composes the board as its core building block and can house a disk.
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
	 * populated with neighbors only after the board
	 * has instantiated all of the other spaces.
	 */
	public Space() {}
	
	
	/**
	 * Determines the playability of a space in a given turn.
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
	 * Builds all the rows emanating from this space to
	 * discover if any lead to a playable space.
	 * 
	 * This process should only occur if:
	 *  - the space has a disk placed on it
	 *  - the disk belongs to the player who is currently
	 *  	taking their turn.
	 * 
	 * @param  current  the player whose turn it currently is
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
	
	
	private void initializeRowsStartingHere()
	{
		ArrayList<Direction> directions = this.neighbors.getDirections();
		for (int i = 0; i < directions.size(); i++)
		{
			this.rowsStartingHere.add(new Row(this, directions.get(i)));
		}
	}
	
	
	/**
	 * Flips the specific disk in this space.
	 */
	public void flipThisDisk()
	{
		if (this.disk != null) {this.disk.flip();}
	}
	
	
	/**
	 * Flips all the "between" disks on all
	 * of the rows that end at this space.
	 */
	private void flipDisks()
	{
		for (int i = 0; i < rowsEndingHere.size(); i++)
		{
			rowsEndingHere.get(i).flipDisks();
		}
	}
	

	// SETTERS
	/**
	 * allows the board class, which creates the neighbors
	 * object for each space, to set that neighbors object
	 * in this space.
	 * 
	 * @param neighbors  the collection of adjacent spaces
	 */
	public void setNeighbors(Neighbors neighbors) 
	{
		this.neighbors = neighbors;
		initializeRowsStartingHere();
	}
	
	/**
	 * Adds a row object to the collection of rows that terminate
	 * in this space.
	 * 
	 * As the row object constructs itself, it must determine if a given
	 * space is the end of that row, and if so, if that space is then playable.
	 * If it is indeed playable, it uses this method to attach itself to the space.
	 * This serves two purposes:
	 * 	- Reveals the playable spaces on the board
	 *  - Tracks the rows with disks to flip if the space is played.
	 * 
	 * @param terminus  the row that terminates in this space
	 */
	public void addRowEndingHere(Row terminus) {this.rowsEndingHere.add(terminus);}
	
	/**
	 * Switches the state of the space to indicate it can receive
	 * a disk in given turn.
	 * 
	 */
	public void setPlayableTrue() {this.playable = true;}
	
	
	/**
	 * Lets the player add a new disk to an empty and playable
	 * space on the board.
	 * 
	 * @param player	the player who places the disk on the board
	 * @param turn		the turn in which the disk was added to the board
	 */
	public void setDisk(Player player, int turn)
	{
		this.disk = new Disk(player, turn);
		flipDisks();
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
