package fr.ensma.a3.ia.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ensma.a3.ia.memory.event.Event;
import fr.ensma.a3.ia.memory.event.IEventObserver;
import fr.ensma.a3.ia.memory.event.IEventManager;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.player.BotPlayer;
import fr.ensma.a3.ia.memory.table.Board;

public class Game implements IEventManager{

	private Map<Class<?>, List<IEventObserver>> observersMap;
	
	
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
		
		observersMap = new HashMap<Class<?>, List<IEventObserver>>();
		
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
	 * Returns the total number of cards in this game, including special cards.
	 * @return the number of cards in this game
	 */
	public int getNbCards() {
		return nbCards;
	}

	@Override
	public void triggerEvent(Event event) {
		if(observersMap.get(event.getClass()) != null)
			for(IEventObserver handler : observersMap.get(event.getClass()))
				handler.handle(event);
	}


	@Override
	public void subscribe(Class<? extends Event> type, IEventObserver handler) {
		if(!observersMap.containsKey(type))
			observersMap.put(type, new ArrayList<IEventObserver>());
		observersMap.get(type).add((IEventObserver) handler);		
	}

	@Override
	public void unsubscribe(Class<? extends Event> type, IEventObserver handler) {
		if(observersMap.containsKey(type))
			observersMap.remove(type).add((IEventObserver) handler);	
	}
	
	
}
