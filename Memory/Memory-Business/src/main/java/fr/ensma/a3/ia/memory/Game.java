package fr.ensma.a3.ia.memory;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.memory.event.IEventHandler;
import fr.ensma.a3.ia.memory.event.IEventManager;
import fr.ensma.a3.ia.memory.event.player.EndOfTurnEvent;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.player.BotPlayer;
import fr.ensma.a3.ia.memory.table.Board;

public class Game implements IEventManager<EndOfTurnEvent>{

	private final List<IEventHandler<EndOfTurnEvent>> endOfTurnHandlers = new ArrayList<IEventHandler<EndOfTurnEvent>>();
	
	private int nbCards;
	private Board board;
	private List<AbstractPlayer> players;
	
	/**
	 * Creates a new {@link Game}
	 * @param nbCards The number of cards this game is played with
	 * @param players The list of {@link AbstractPlayer}s who will play the game.
	 */
	public Game(int nbCards, List<AbstractPlayer> players) {
		this.nbCards = nbCards;
		this.players = players;
		
		if(players.size() == 1)
			players.add(new BotPlayer("RainMan"));
		
		for (AbstractPlayer player : players)
			player.setGame(this);
	}
	
	/**
	 * Returns the board used in this Game instance
	 * @return the {@link Board} used in this Game instance
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Returns the players participating in this game
	 * @return the list of {@link AbstractPlayer} participating in this game
	 */
	public List<AbstractPlayer> getPlayers(){
		return players;
	}
	
	/**
	 * Returns the number of cards in this game
	 * @return the number of cards in this game
	 */
	public int getNbCards() {
		return nbCards;
	}

	@Override
	public void triggerEvent(EndOfTurnEvent event) {
		for(IEventHandler<EndOfTurnEvent> handler : endOfTurnHandlers)
			if(event.isCancelled())
				break;
			else
				handler.onEvent(event);
	}

	@Override
	public void subscribe(IEventHandler<EndOfTurnEvent> handler) {
		if(!endOfTurnHandlers.contains(handler))
			endOfTurnHandlers.add(handler);
	}
	
}
