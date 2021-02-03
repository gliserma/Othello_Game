package othello.gui_elements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import othello.TurnManager;
import othello.board_components.Space;

/**
 * The visual representation of a disk or playable icon.
 * 
 * The disk or playable icon is drawn over the GUISpace.
 * The disks are visualized with a white or black fill,
 * depending on their color. The playable spaces are drawn
 * with a white strock and a transparent white fill.
 * 
 * @author nicholasgliserman
 *
 */
public class GUIDisk extends Circle
{
	TurnManager turnManager;
	Space underlyingSpace;
	int x;
	int y;
	
	/**
	 * Constructs a new GUIDisk.
	 * 
	 * If the GUIDisk is a playable icon, it adds a event listener
	 * so that if it is clicked, it will input the x and y coordinates
	 * of the space to the turn manager.
	 * 
	 * @param  underlyingSpace	the space object with the information about its underlying status
	 * @param  spaceLength		the dimensions of the GUISpace, used to calculate the size of the circle
	 * @param  x				the x coordinate of the space in the board grid
	 * @param  y				the y coordinate of the space in the board grid
	 * @param  turnManager		the TurnManager, which will be used to input the player's choice
	 */
	public GUIDisk(Space underlyingSpace, int spaceLength, int x, int y, TurnManager turnManager)
	{
		setSpaceStatus(underlyingSpace);
		draw(spaceLength);
		setXY(x, y);
		setTurnManager(turnManager);
	}

	
	private void draw(int spaceLength) 
	{
		double radiusLength = (double) spaceLength * 0.3125;
		setRadius(radiusLength);
		setStrokeWidth(radiusLength / 10);
		
		if (this.underlyingSpace.isPlayable())
		{
			drawPlayable();
		}
		else if (this.underlyingSpace.getDisk() != null)
		{
			if (this.underlyingSpace.getDisk().isBlack())
			{
				drawBlack();
			}
			else
			{
				drawWhite();
			}
		}
		else
		{
			drawEmpty();
		}
	}


	private void drawBlack() 
	{
		setStroke(Color.BLACK);
		setFill(Color.BLACK);	
	}
	

	private void drawWhite()
	{
		setStroke(Color.WHITESMOKE);
		setFill(Color.WHITESMOKE);
	}


	private void drawPlayable() 
	{
		Color transparentWhite = new Color(1, 1, 1, .2);
		setStroke(Color.WHITE);
		setFill(transparentWhite);
		addListener();
	}
	
	
	private void drawEmpty()
	{
		setStroke(null);
		setFill(null);
	}
	
	
	private void addListener()
	{
		setOnMouseClicked(event-> {
			turnManager.finishTurn(this.x, this.y);
		});
	}
	
	
	// SETTERS
	private void setSpaceStatus(Space status) {this.underlyingSpace = status;}
	private void setTurnManager(TurnManager turnManager) {this.turnManager = turnManager;}
	
	private void setXY(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
}
