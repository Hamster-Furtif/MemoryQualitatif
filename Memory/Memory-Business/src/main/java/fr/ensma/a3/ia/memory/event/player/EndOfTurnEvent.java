package fr.ensma.a3.ia.memory.event.player;


import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.event.Event;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;

public class EndOfTurnEvent extends Event{

	private AbstractPlayer player;
	private Game game;
	private boolean successful;
	
	/**
	 * Creates a new EndOfTurn event
	 * @param player The player whose turn just ended
	 * @param successful Whether or not the player drew a matching pair
	 */
	public EndOfTurnEvent(AbstractPlayer player, boolean successful) {
		this.player = player;
		game = player.getGame();
	}
	
	/**
	 * Returns the player whose turn ended
	 * @return the AbstractPlayer whose turn ended
	 */
	public AbstractPlayer getPlayer() {
		return player;
	}
	
	/**
	 * Returns the Game instance of the player
	 * @return the Game instance of the player
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Returns whether or not the player drew a matching pair
	 * @return whether or not the player drew a matching pair
	 */
	public boolean isSuccessful() {
		return successful;
	}
	
}
