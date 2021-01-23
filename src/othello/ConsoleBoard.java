package othello;

import othello.board_components.Board;
import othello.board_components.Space;

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
	
	public void getBoardData(Board currentBoard)
	{
		for (int height = 0; height < this.consoleBoard.length; height++)
		{
			for (int width = 0; width < this.consoleBoard[height].length; width++)
			{
				Space currentSpace = currentBoard.getSpace(height, width);
				
				if (currentSpace.isPlayable())
				{
					this.consoleBoard[height][width] = "[P]";
				}
				else if (currentSpace.isEmpty())
				{
					this.consoleBoard[height][width] = "[ ]";
				}
				else if(currentSpace.getDisk().isBlack())
				{
					this.consoleBoard[height][width] = "[X]";
				}
				else
				{
					this.consoleBoard[height][width] = "[O]";
				}
			}
		}
		
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
