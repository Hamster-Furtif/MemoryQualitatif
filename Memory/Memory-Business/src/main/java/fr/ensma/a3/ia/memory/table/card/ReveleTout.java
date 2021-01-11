package fr.ensma.a3.ia.memory.table.card;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;

final class ReveleTout extends SpecialCard {
	
	public ReveleTout() {
		super();
		System.out.println("ReveleTout");
	}
	
	@Override
	public void specialAction(AbstractPlayer player) {
		//TODO remove
		System.out.println("Revele tout !");
	}
	
}
