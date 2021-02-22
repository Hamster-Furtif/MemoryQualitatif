package fr.ensma.a3.ia.memory.event.table;

import fr.ensma.a3.ia.memory.event.Event;
import fr.ensma.a3.ia.memory.table.Tile;

public class FlippedTileEvent extends Event{

	private Tile tile;
	private boolean previousState;
	
	public FlippedTileEvent(Tile tile, boolean previousState) {
		this.tile = tile;
		this.previousState = previousState;
	}
	
	public boolean isFlippedBeforeEvent() {
		return previousState;
	}
	
	public boolean isFaceUpBeforeEvent() {
		return previousState;
	}
	
	public boolean isFaceDownBeforeEvent() {
		return !previousState;
	}
	
	public boolean isFlippedAfterEvent() {
		return !previousState;
	}
	
	public boolean isFaceUpAfterEvent() {
		return !previousState;
	}
	
	public boolean isFaceDownAfterEvent() {
		return previousState;
	}
}
