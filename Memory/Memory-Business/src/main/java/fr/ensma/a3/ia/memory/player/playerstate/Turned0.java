package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.card.Card;

public class Turned0 extends AbstractPlayerState {
	
	public Turned0(AbstractPlayer player) {
		super(player);
	}
	
	@Override
	public void toTurned1() {
		player.setState(player.getStateTurned1());
	}
	
	@Override
	public void cardTurned(Card c) {
		player.setTurnedCard(c);;
		toTurned1();
	}

}
