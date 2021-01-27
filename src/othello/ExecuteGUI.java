package othello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import othello.game_states.TurnManager;


public class ExecuteGUI extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		{
			TurnManager game = new TurnManager("PlayerOne", "PlayerTwo");
			game.startTurn();
			primaryStage.setScene(new Scene(game.getPane()));
			primaryStage.show();
		}
	}
	
	public static void main(String[] args) {launch(args);}
}