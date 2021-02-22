package fr.ensma.a3.ia.memory.client.ui.components.game.tile;

import fr.ensma.a3.ia.memory.table.Tile;

public class TileModele {
	
	private boolean isFlipped;
	private Tile tile;

	public TileModele(Tile tile) {
		this.tile = tile;
	}

	public boolean isFlipped() {
		return isFlipped;
	}

	public void setFlipped(boolean isFlipped) {
		this.isFlipped = isFlipped;
	}

	public Tile getTile() {
		return tile;
	}
	

}
