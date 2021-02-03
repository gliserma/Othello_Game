package othello;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import othello.gui_elements.ScoreController;

/**
 * Main class that executes the Othello program.
 * 
 * @author nicholasgliserman
 */
public class Execute extends Application {
	
	/**
	 * Launches the Othello application.
	 * 
	 * This creates a new TurnManager object, which
	 * manages the flow of the game. Additionally,
	 * it launches the GUI elements.
	 * 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		// TURN MANAGER Controls the flow of the game
		TurnManager game = new TurnManager();
		
		// Load & Add Score Area into a VBOX
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Execute.class.getResource("gui_elements/GameView.fxml"));
		VBox vbox = loader.load();
		
		// Create a Score Controller so the TurnManager can update score in GUI
		try
		{
			ScoreController sc = loader.<ScoreController>getController();
			game.setScoreController(sc);
		}
		catch (Exception e) {e.printStackTrace();}
		
		// CREATE & ADD MAIN BOARD AREA to VBOX
		Pane gamePane = game.getPane();
		vbox.getChildren().add(0, gamePane);
		vbox.setAlignment(Pos.CENTER);
		
		// PRIMARY STAGE Parameters
		primaryStage.setScene(new Scene(vbox));
		primaryStage.setTitle("OTHELLO");
		primaryStage.setWidth(600.0);
		primaryStage.setHeight(673.00);
		primaryStage.show();
	}

	public static void main(String[] args) {launch(args);}
}