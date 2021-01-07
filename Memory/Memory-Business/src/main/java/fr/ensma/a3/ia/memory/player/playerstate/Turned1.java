package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.event.player.EndOfTurnEvent;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Tile;
import fr.ensma.a3.ia.memory.table.card.Card;

public class Turned1 extends AbstractPlayerState {

	public Turned1(AbstractPlayer player) {
		super(player);
	}
	
	@Override
	public void cardTurned(Tile t) {
		Card c = t.getCard();
		if(c == player.getTurnedCard()) {
			player.addMatchingPair(c);
			player.setState(player.getStateTurned0());
		}
		else {
			EndOfTurnEvent event = new EndOfTurnEvent(player);
			player.getGame().triggerEvent(event);
			if(event.isCancelled())
				player.setState(player.getStateTurned0());
			else
				player.setState(player.getStateWaiting());
		}
	}

}
