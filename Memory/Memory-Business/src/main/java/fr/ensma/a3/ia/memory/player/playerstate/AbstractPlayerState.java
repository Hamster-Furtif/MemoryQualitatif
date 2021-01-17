package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Tile;

public class AbstractPlayerState implements IPlayerState {

	protected AbstractPlayer player;
	
	protected AbstractPlayerState(AbstractPlayer player) {
		this.player = player;
	}
	
	@Override
	public void toTurned0() {}

	@Override
	public void toTurned1() {}

	@Override
	public void toWaiting() {}

	@Override
	public void tileFlipped(Tile tile) {}

	@Override
	public AbstractPlayer getPlayer() {
		return player;
	}
	
}
