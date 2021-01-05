package fr.ensma.a3.ia.memory.player;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.memory.player.playerstate.IPlayerState;
import fr.ensma.a3.ia.memory.player.playerstate.Turned0;
import fr.ensma.a3.ia.memory.player.playerstate.Turned1;
import fr.ensma.a3.ia.memory.player.playerstate.Waiting;
import fr.ensma.a3.ia.memory.table.card.Card;

public abstract class AbstractPlayer {

	protected String id;
	
	protected IPlayerState currentState;
	
	final private Waiting stateWaiting = new Waiting(this);
	final private Turned0 stateTurned0 = new Turned0(this);
	final private Turned1 stateTurned1 = new Turned1(this);
	
	private List<Card> cards;
	
	private Card turned_card;

	
	public AbstractPlayer(String id) {
		this.id = id;
		currentState = stateWaiting;
		cards = new ArrayList<Card>();

	}
	
	public void cardTurned(Card c) {
		currentState.cardTurned(c);
	}

	public Waiting getStateWaiting() {
		return stateWaiting;
	}

	public Turned0 getStateTurned0() {
		return stateTurned0;
	}


	public Turned1 getStateTurned1() {
		return stateTurned1;
	}

	
	public void setState(IPlayerState state) {
		currentState = state;
	}


	public void setTurnedCard(Card c) {
		turned_card = c;
	}
	
	public Card getTurnedCard() {
		return turned_card;
	}
	
	public void addMatchingPair(Card c) {
		cards.add(c);
	}
	
}
