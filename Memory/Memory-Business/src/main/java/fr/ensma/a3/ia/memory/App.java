package fr.ensma.a3.ia.memory;

import java.util.ArrayList;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.player.HumanPlayer;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;

public class App {

	public static void main(String[] args) {
		
		AbstractPlayer p1 = new HumanPlayer("Magnus");
		AbstractPlayer p2 = new HumanPlayer("Hikaru");
		
		ArrayList<AbstractPlayer> players = new ArrayList<AbstractPlayer>();
		players.add(p1);
		players.add(p2);
		
		SpecialCard.init();
		
		Game game = new Game(4, players);
		game.run();
	}
	
}
