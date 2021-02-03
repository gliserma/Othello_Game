package othello.gui_elements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Creates the visual representation for each space.
 * 
 * This is drawn beneath a potential disk or playable
 * space icon.
 * 
 * @author nicholasgliserman
 *
 */
public class GUISpace extends Rectangle {

	public GUISpace(int dimension)
	{
		setWidth(dimension);
		setHeight(dimension);
		setStrokeWidth((double) dimension * .015);
		setStroke(Color.WHITE);
		setFill(Color.SEAGREEN);
	}
	
}


