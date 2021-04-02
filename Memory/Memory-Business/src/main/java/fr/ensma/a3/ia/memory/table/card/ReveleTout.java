package fr.ensma.a3.ia.memory.table.card;

import fr.ensma.a3.ia.memory.event.table.ReveleToutEvent;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;

final class ReveleTout extends SpecialCard {
	
	public ReveleTout() {
		super("reveletout", 'R');
	}
	
	@Override
	public void specialAction(AbstractPlayer player) {
		player.getGame().triggerEvent(new ReveleToutEvent());
	}
	
}
