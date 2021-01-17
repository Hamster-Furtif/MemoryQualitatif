package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;

public class Waiting extends AbstractPlayerState{

	/**
	 * Creates a new {@link Waiting} {@link IPlayerState} for a given player. This is the state the player is in when it is not its time to play.
	 * @param player The player to create the {@link Waiting} {@link IPlayerState} for.
	 */
	public Waiting(AbstractPlayer player) {
		super(player);
	}
	
}
