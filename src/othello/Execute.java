package othello;

import othello.game_states.TurnManager;
import othello.players.Player;

public class Execute {

	public static void main(String[] args) {
		{
			System.out.println("Test");
			TurnManager game = new TurnManager("PlayerOne", "PlayerTwo");
			
			while (!game.isGameDone())
			{
				System.out.println("Take turn");
				game.takeTurn();
			}
			try {
				Player winner = game.getWinner();
				System.out.println("WINNER: " + winner.getUsername());
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
