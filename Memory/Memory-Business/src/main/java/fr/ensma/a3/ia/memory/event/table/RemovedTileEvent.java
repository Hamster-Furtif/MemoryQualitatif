package fr.ensma.a3.ia.memory.event.table;

import fr.ensma.a3.ia.memory.event.Event;
import fr.ensma.a3.ia.memory.table.Tile;

public class RemovedTileEvent extends Event {

	private Tile tile;
	
	public RemovedTileEvent(Tile tile) {
		this.tile = tile;
	}
	
	public Tile getTile() {
		return tile;
	}
}
