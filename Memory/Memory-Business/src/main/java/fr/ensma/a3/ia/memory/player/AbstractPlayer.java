package fr.ensma.a3.ia.memory.player;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.memory.player.playerstate.IPlayerState;
import fr.ensma.a3.ia.memory.player.playerstate.Turned0;
import fr.ensma.a3.ia.memory.player.playerstate.Turned1;
import fr.ensma.a3.ia.memory.player.playerstate.Turned2;
import fr.ensma.a3.ia.memory.player.playerstate.Waiting;
import fr.ensma.a3.ia.memory.table.card.Card;

public abstract class AbstractPlayer {

	protected String id;
	
	public IPlayerState currentState;
	
	public Waiting stateWaiting;
	public Turned0 stateTurned0;
	public Turned1 stateTurned1;
	public Turned2 stateTurned2;
	
	private List<Card> cartes;

	
	public AbstractPlayer(String id) {
		this.id = id;
		
		stateWaiting = new Waiting(this);
		stateTurned0 = new Turned0(this);
		stateTurned1 = new Turned1(this);
		stateTurned2 = new Turned2(this);
		
		cartes = new ArrayList<Card>();

	}
	
}
