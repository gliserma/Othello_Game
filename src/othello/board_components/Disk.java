package othello.board_components;

import othello.players.Player;

/**
 * Piece placed on the Othello board, representing a player.
 * 
 * @author nicholasgliserman
 */
public class Disk {
	Player currentPlayer;
	int turnPlaced;
	
	/**
	 * Constructs a new disk object.
	 * 
	 * Passes in a Player to set the disk's
	 * initial state and a turn to record
	 * when in the game the disk was created.
	 * 
	 * @param player
	 * @param turn
	 */
	public Disk(Player player, int turn)
	{
		setPlayer(player);
		setTurnPlaced(turn);
		this.currentPlayer.incrementScore();
	}
	
	/**
	 * Switches the player currently associated 
	 * with the disk.
	 * 
	 * Decrements the score for the player who used to
	 * have the disk. Conversely, increments the score
	 * for the player who now has the disk.
	 */
	public void flip()
	{
		this.currentPlayer.decrementScore(); // -1 FOR SOON-TO-BE OLD currentPlayer
		this.currentPlayer = this.currentPlayer.getOpponent(); // FLIP currentPlayer
		this.currentPlayer.incrementScore(); // +1 FOR NEW currentPlayer
	}
	
	// SETTERS
	public void setPlayer(Player player) {this.currentPlayer = player;}
	public void setTurnPlaced(int turn) {if (turn > 0) {this.turnPlaced = turn;}}
	
	// GETTERS
	public Player getPlayer() {return this.currentPlayer;}
	public boolean isBlack() {return this.currentPlayer.isBlack();}
}
