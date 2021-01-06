package fr.ensma.a3.ia.memory;

import java.util.List;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.player.BotPlayer;
import fr.ensma.a3.ia.memory.table.Board;

public class Game {

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
}
