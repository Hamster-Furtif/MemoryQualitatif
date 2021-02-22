package fr.ensma.a3.ia.memory.client.ui.components.game;

import java.util.List;

import fr.ensma.a3.ia.memory.client.ui.components.game.playercard.PlayerCardPresentation;
import fr.ensma.a3.ia.memory.table.Tile;

public class GameModele {
	
	private List<Tile> tiles;
	private List<PlayerCardPresentation> cardPresentations;
	private int xDim, yDim;
	
	public GameModele(List<Tile> tiles, int xDim, int yDim) {
		this.tiles = tiles;
		this.xDim = xDim;
		this.yDim = yDim;
	}
	
	public Tile getTile(int x, int y) {
		return tiles.get(xDim*y+x);
	}
	
	public int getxDim() {
		return xDim;
	}

	public int getyDim() {
		return yDim;
	}

}
