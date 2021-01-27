package othello.windows;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SpaceView extends Rectangle {

	public SpaceView(int dimension)
	{
		setWidth(dimension);
		setHeight(dimension);
		setStrokeWidth((double) dimension * .015);
		setStroke(Color.WHITE);
		setFill(Color.SEAGREEN);
	}
	
}


