package othello.windows;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import othello.board_components.Space;
import othello.game_states.TurnManager;

public class DiskView extends Circle
{
	TurnManager controller;
	Space underlying;
	int x;
	int y;
	//Circle disk;
	
	public DiskView(Space underlying, int spaceLength, int x, int y, TurnManager controller)
	{
		setSpaceStatus(underlying);
		draw(spaceLength);
		setXY(x, y);
		setTurnManager(controller);
	}

	private void draw(int spaceLength) 
	{
		double radiusLength = (double) spaceLength * 0.3125;
		setRadius(radiusLength);
		setStrokeWidth(radiusLength / 10);
		
		if (this.underlying.isPlayable())
		{
			drawPlayable();
		}
		else if (this.underlying.getDisk() != null)
		{
			if (this.underlying.getDisk().isBlack())
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

	/**
	 * Draws the circle black
	 */
	public void drawBlack() 
	{
		setStroke(Color.BLACK);
		setFill(Color.BLACK);	
	}
	
	/**
	 * Draws the circle white
	 */
	public void drawWhite()
	{
		setStroke(Color.WHITESMOKE);
		setFill(Color.WHITESMOKE);
	}

	/**
	 * Draws a hollow circle with light gray edge
	 * and adds an event listener.
	 */
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
	
	public void addListener()
	{
		setOnMouseClicked(event-> {
			controller.finishTurn(this.x, this.y);
		});
		
	}
	
	// SETTERS
	
	private void setSpaceStatus(Space status) {this.underlying = status;}
	private void setTurnManager(TurnManager controller) {this.controller = controller;}
	
	private void setXY(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
}
