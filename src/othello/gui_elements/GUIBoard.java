package othello.gui_elements;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import othello.TurnManager;
import othello.board_components.Board;
import othello.board_components.Space;

/**
 * Visualizes the contents of a Board in a GUI.
 * 
 * @author nicholasgliserman
 *
 */
public class GUIBoard {
	private TurnManager turnManager;
	private Board underlyingBoard;
	
	private Group spaceGroup = new Group();
	private Group diskGroup = new Group();
	
	private int spaceDimension;
	private int boardHeight;
	private int boardWidth;
	
	Pane root = new Pane();
	
	/**
	 * Constructs a new GUIBoard, typically at the start of a game.
	 * 
	 * 
	 * @param underlyingBoard	the board object responsible for managing the spaces
	 * @param spaceDimension	the length and width of each space in pixels
	 * @param turnManager		manages the flow of the game, connected to this class for players to input their space choices
	 */
	public GUIBoard(Board underlyingBoard, int spaceDimension, TurnManager turnManager) 
	{
		setTurnManager(turnManager);
		setBoard(underlyingBoard);
		setSpaceDimension(spaceDimension);
		this.setBoardDimensions();
		this.setRootDimension();
		refresh();
	}

	/**
	 * Visualizes the current state of the board class
	 * 
	 */
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
				GUISpace tile = new GUISpace(this.spaceDimension);
				tile.setTranslateY(y * this.spaceDimension);
				tile.setTranslateX(x * this.spaceDimension);
				this.spaceGroup.getChildren().add(tile);
				
				// ADD DISK
				Space currentSpace = this.underlyingBoard.getSpace(y, x);

				GUIDisk disk = new GUIDisk(currentSpace, this.spaceDimension, x, y, this.turnManager);
				
				disk.setTranslateY(y * this.spaceDimension + spaceDimension / 2);
				disk.setTranslateX(x * this.spaceDimension + spaceDimension / 2);
				this.diskGroup.getChildren().add(disk);
			}
		}
	}
	
	/**
	 * Shows the title screen with a message.
	 * 
	 * The specific message depends on whether it is being shown when the application
	 * is first launched ("Othello) or a victory message at the end of a game. Note
	 * the message should ideally be about 8 - 10 characters long to fill the alloted
	 * space without overflowing.
	 * 
	 * @param  titleMessage		the message to be relayed to the players in the title screen
	 */
	public void titleView(String titleMessage)
	{
		int totalWidth = this.boardWidth * this.spaceDimension;
		int totalHeight = this.boardHeight * this.spaceDimension;
		double messageWidth = (double) totalWidth;
		double messageHeight = (double) totalHeight;
		
		// PLACE OTHELLO LETTERS ON BOARD
		othelloTitle(totalWidth, totalHeight);
		
		// COVER GAME BOARD w/ TRANSPARENT WHITE
		Rectangle rectangle = new Rectangle(messageWidth, messageHeight);
		Color transparentWhite = new Color(1.0, 1.0, 1.0, .8);
		rectangle.setFill(transparentWhite);
		diskGroup.getChildren().add(rectangle);
		
		// WINNER or TITLE AREA
		Rectangle titleRectangle = new Rectangle();
		titleRectangle.setWidth(totalWidth * 0.5);
		titleRectangle.setHeight(totalHeight * 0.125);
		titleRectangle.setFill(Color.WHITE);
		titleRectangle.setTranslateX(totalWidth * .25);
		titleRectangle.setTranslateY(totalHeight * .375);
		diskGroup.getChildren().add(titleRectangle);
		
		// WINNER or TITLE TEXT
		Text winnerOrTitle = new Text((totalWidth * 0.27), (totalHeight * 0.47) , titleMessage);
		winnerOrTitle.setFont(Font.font("Courier", FontWeight.BOLD, 45));
		diskGroup.getChildren().add(winnerOrTitle);
		
		// NEW GAME Button
		Rectangle newGame = new Rectangle();
		newGame.setWidth(totalWidth * 0.5);
		newGame.setHeight(totalHeight * 0.125);
		newGame.setFill(Color.SEAGREEN);
		newGameEventListener(newGame);
		newGame.setTranslateX(totalWidth * .25);
		newGame.setTranslateY(totalHeight * .5);
		diskGroup.getChildren().add(newGame);
		
		// NEW GAME BUTTON TEXT
		Text newGameMessage = new Text("<NEW GAME>");
		newGameMessage.setTranslateX(totalWidth * .27);
		newGameMessage.setTranslateY(totalHeight * .585);
		newGameMessage.setFont(Font.font("Courier", FontWeight.BOLD, 45));
		newGameEventListener(newGameMessage);
		diskGroup.getChildren().add(newGameMessage);
	}
	
	private void othelloTitle(int totalWidth, int totalHeight)
	{
		String[] othello = {"O", "T", "H", "E", "L", "L", "O", "!"};
		for (int i = 0; i < othello.length; i++)
		{
			for (int j = 0; j < othello.length; j++)
			{
				int remainder = (i + j) % othello.length;
				
				Text letter = new Text(othello[remainder]);
				letter.setFont(Font.font("System Bold", FontWeight.BOLD, 45));
				letter.setTranslateX((this.spaceDimension * i) + (this.spaceDimension / 4) + 3);
				letter.setTranslateY((this.spaceDimension * j) + (this.spaceDimension * 0.75));
				diskGroup.getChildren().add(letter);
			}
		}
	}

	
	private void newGameEventListener(Shape shape)
	{
		shape.setOnMouseClicked(event-> {
			this.turnManager.newGame();
			refresh();
		});
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
	private void setTurnManager(TurnManager controller) {this.turnManager = controller;}
	
	// GETTERS
	public Pane getBoardViewPane() {return this.root;}

}
