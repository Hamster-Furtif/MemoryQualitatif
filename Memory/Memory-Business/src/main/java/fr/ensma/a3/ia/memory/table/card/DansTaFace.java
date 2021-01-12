package fr.ensma.a3.ia.memory.table.card;

import java.util.List;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Board;
import fr.ensma.a3.ia.memory.table.Tile;

final class DansTaFace extends SpecialCard {

	public DansTaFace() {
		super();
	}
	
	@Override
	public void specialAction(AbstractPlayer player) {
		Board board = player.getGame().getBoard();
		List<Card> cards = player.popCards();
		List<Tile> toReturn = Tile.generatePairsFromCards(cards);
		for (Tile r : toReturn)
			for(Tile b : board.getTiles())
				if(b.isEmpty())
					board.getTiles().set(board.getTiles().indexOf(b), r);

		board.shuffleTiles();
		//TODO remove
		System.out.println("Dans ta face !");
	}
}
