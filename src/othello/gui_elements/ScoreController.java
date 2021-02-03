package othello.gui_elements;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import othello.players.Score;

/**
 * Updates the score display in the GUI.
 * 
 * @author nicholasgliserman
 *
 */
public class ScoreController {
	@FXML
	private Label blackScore;
	@FXML
	private Label whiteScore;
	@FXML
	private Label currentTurn;
	
	@FXML
	protected void initialize() {}
	
	/**
	 * Updates the score display each turn.
	 * 
	 * @param  score		the score object that will generate the score messages for each player
	 * @param  turnMessage	the message identifying the current player
	 */
	public void refresh(Score score, String turnMessage)
	{
		// UPDATE TEXT for SCORES
		blackScore.setText(score.getScoreMessage(true));
		whiteScore.setText(score.getScoreMessage(false));
		
		// UPDATE TEXT for CURRENT PLAYER	
		this.currentTurn.setText(turnMessage);
	}
}
