package fr.ensma.a3.ia.memory.table.card;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;

public class MelangeTout extends SpecialCard {
	
	public MelangeTout() {
		super("melangetout");
	}
	
	@Override
	public void specialAction(AbstractPlayer player) {
		player.getGame().getBoard().shuffleTiles();
		//TODO remove
		System.out.println("Mélange tout !");
	}
	
}
