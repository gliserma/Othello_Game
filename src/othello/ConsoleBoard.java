package othello;

import othello.board_components.Board;

/**
 * Allows us to play Othello on the console, i.e.
 * before the GUI update.
 * 
 * @author nicholasgliserman
 *
 */
public class ConsoleBoard {
	//Board board;
	String[][] consoleBoard = new String[8][8];
	
	// [ ] - EMPTY
	// [P] - PLAYABLE
	// [X] - BLACK
	// [O] - WHITE
	
	
	public ConsoleBoard()
	{
		// INITIALIZE BOARD
		for (int y = 0; y < 8; y++)
		{
			for (int x = 0; x < 8; x++)
			{
				this.consoleBoard[y][x] = "[ ]";
			}
		}
		// INITIAL DISKS
		this.consoleBoard[3][3] = "[O]";
		this.consoleBoard[3][4] = "[X]";
		this.consoleBoard[4][4] = "[O]";
		this.consoleBoard[4][3] = "[X]";
	}
	
	public void printBoard()
	{
		for (int y = 0; y < 8; y++)
		{
			for (int x = 0; x < 8; x++)
			{
				printSpace(this.consoleBoard[y][x]);
			}
			System.out.print("\n");
		}
	}
	
	public void inputChoice(int y, int x)
	{
		
	}
	
	public void printSpace(String space)
	{
		System.out.print(space);
	}

}
