package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.card.Card;

public class Turned1 extends AbstractPlayerState {

	public Turned1(AbstractPlayer player) {
		super(player);
	}
	
	@Override
	public void cardTurned(Card c) {
		if(c == player.getTurnedCard()) {
			player.addMatchingPair(c);
			player.setState(player.getStateTurned0());
		}
		else {
			//TODO Utiliser des Tiles plutôt que des cartes ?
			//TODO Event fin de tour
			player.setState(player.getStateWaiting());
		}
	}

}
