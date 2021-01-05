package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.table.card.Card;

public interface IPlayerState {
	
	void toTurned0();
	void toTurned1();
	void toWaiting();
	void cardTurned(Card c);
	
}
