package fr.ensma.a3.ia.memory.event.board;

import fr.ensma.a3.ia.memory.event.Event;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Tile;

public class TileActivatedEvent extends Event {

	private Tile tile;
	private AbstractPlayer player;
	
	public TileActivatedEvent(AbstractPlayer player, Tile tile) {
		this.tile = tile;
		this.player = player;
	}

	public Tile getTile() {
		return tile;
	}

	public AbstractPlayer getPlayer() {
		return player;
	}
	
}
