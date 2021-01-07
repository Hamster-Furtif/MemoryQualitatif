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
	
	private int nb_cards;
	private Board board;
	private List<AbstractPlayer> players;
	
	public Game(int nb, List<AbstractPlayer> pl) {
		nb_cards = nb;
		players = pl;
		
		if(players.size() == 1)
			pl.add(new BotPlayer("RainMan"));
		
		for (AbstractPlayer player : players)
			player.setGame(this);
	}
	
	public Board getBoard() {
		return board;
	}
	
	public List<AbstractPlayer> getPlayers(){
		return players;
	}
	
	public int getNbCards() {
		return nb_cards;
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
