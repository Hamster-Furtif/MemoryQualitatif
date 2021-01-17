package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Tile;

public interface IPlayerState {
	
	/**
	 * Called to go from this state to a {@link Turned0} state.
	 */
	void toTurned0();
	
	/**
	 * Called to go from this state to a {@link Turned1} state.
	 */
	void toTurned1();
	
	/**
	 * Called to go from this state to a {@link Waiting} state.
	 */
	void toWaiting();
	
	/**
	 * Called when the player running this state flips a {@link Tile}
	 */
	void tileFlipped(Tile tile);
	
	/**
	 * Returns a player stored in this state.
	 */
	AbstractPlayer getPlayer();
	
}
