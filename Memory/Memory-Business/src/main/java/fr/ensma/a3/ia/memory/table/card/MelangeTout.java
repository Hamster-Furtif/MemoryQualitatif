package fr.ensma.a3.ia.memory.table.card;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;

public class MelangeTout extends SpecialCard {
	
	static {
		new MelangeTout();
	}
	
	@Override
	public void specialAction(AbstractPlayer player) {
		player.getGame().getBoard().shuffleTiles();
	}
	
}
