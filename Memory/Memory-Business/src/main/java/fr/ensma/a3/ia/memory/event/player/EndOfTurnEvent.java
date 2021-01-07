package fr.ensma.a3.ia.memory.event.player;


import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.event.Event;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;

public class EndOfTurnEvent extends Event{

	private AbstractPlayer player;
	private Game game;
	
	public EndOfTurnEvent(AbstractPlayer player) {
		this.player = player;
		game = player.getGame();
	}
	
	public AbstractPlayer getPlayer() {
		return player;
	}
	
	public Game getGame() {
		return game;
	}
	
}
