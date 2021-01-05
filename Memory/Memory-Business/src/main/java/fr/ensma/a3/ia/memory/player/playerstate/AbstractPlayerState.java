package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;

public class AbstractPlayerState implements IPlayerState {

	private AbstractPlayer player;
	
	protected AbstractPlayerState(AbstractPlayer player) {
		this.player = player;
	}
	
	@Override
	public void toTurned0() {}

	@Override
	public void toTurned1() {}

	@Override
	public void toTurned2() {}

	@Override
	public void toWaiting() {}

}
