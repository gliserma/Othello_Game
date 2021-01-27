package othello.windows;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import othello.board_components.Board;
import othello.board_components.Space;
import othello.game_states.TurnManager;

/**
 * Visualizes the contents of a Board in a GUI.
 * 
 * @author nicholasgliserman
 *
 */
public class BoardView {
	private TurnManager controller;
	private Board underlyingBoard;
	
	private Group spaceGroup = new Group();
	private Group diskGroup = new Group();
	
	private int spaceDimension;
	private int boardHeight;
	private int boardWidth;
	
	Pane root = new Pane();
	
	public BoardView(Board underlyingBoard, int spaceDimension, TurnManager controller) 
	{
		setTurnManager(controller);
		setBoard(underlyingBoard);
		setSpaceDimension(spaceDimension);
		this.setBoardDimensions();
		this.setRootDimension();
		refresh();
	}
	


	public void refresh()
	{
		root.getChildren().clear();
		this.spaceGroup.getChildren().clear();
		this.diskGroup.getChildren().clear();
		
		root.getChildren().addAll(this.spaceGroup, this.diskGroup);
		
		for (int y = 0; y < this.boardHeight; y++)
		{
			for (int x = 0; x < this.boardWidth; x++)
			{
				// ADD SPACE
				SpaceView tile = new SpaceView(this.spaceDimension);
				tile.setTranslateY(y * this.spaceDimension);
				tile.setTranslateX(x * this.spaceDimension);
				this.spaceGroup.getChildren().add(tile);
				
				// ADD DISK
				Space currentSpace = this.underlyingBoard.getSpace(y, x);

				DiskView disk = new DiskView(currentSpace, this.spaceDimension, x, y, this.controller);
				
				disk.setTranslateY(y * this.spaceDimension + spaceDimension / 2);
				disk.setTranslateX(x * this.spaceDimension + spaceDimension / 2);
				this.diskGroup.getChildren().add(disk);

			}
		}
	}
	
	public void gameOverView()
	{
		int totalWidth = this.boardWidth * this.spaceDimension;
		int totalHeight = this.boardHeight * this.spaceDimension;
		
		double messageWidth = (double) totalWidth * 0.75;
		double messageHeight = (double) totalHeight * 0.5;
		
		Rectangle rectangle = new Rectangle(messageWidth, messageHeight);
		rectangle.setTranslateX(totalWidth * .125);
		rectangle.setTranslateY(totalHeight * .25);
		Color transparentWhite = new Color(1.0, 1.0, 1.0, .8);
		rectangle.setFill(transparentWhite);
		
		diskGroup.getChildren().add(rectangle);
		
		Text winner = new Text((totalWidth * 0.25), (totalHeight * 0.375) , "GAME OVER");
		winner.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
		diskGroup.getChildren().add(winner);
	}
	
	// SETTERS
	private void setBoardDimensions()
	{
		this.boardHeight = this.underlyingBoard.getBoardHeight();
		this.boardWidth = this.underlyingBoard.getBoardWidth();
	}
	
	private void setRootDimension()
	{
		int height = this.boardHeight * this.spaceDimension;
		int width = this.boardWidth * this.spaceDimension;
		this.root.setPrefSize(width, height);
	}
	
	private void setSpaceDimension(int spaceDimension) {this.spaceDimension = spaceDimension;}
	private void setBoard(Board underlyingBoard) {this.underlyingBoard = underlyingBoard;}
	private void setTurnManager(TurnManager controller) {this.controller = controller;}
	
	// GETTERS
	public Pane getBoardViewPane() {return this.root;}

}
