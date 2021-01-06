package fr.ensma.a3.ia.memory.table;

import java.util.Collections;
import java.util.List;

public class Board {

	private List<Tile> tiles;
	
	private int xDim, yDim;
	
	public Board(int xDim, int yDim, List<Tile> tiles) {
		this.xDim = xDim;
		this.yDim = yDim;
		this.tiles = tiles;
	}
	
	public void shuffleTiles() {
		Collections.shuffle(tiles);
	}
	
	public Tile getTile(int x, int y) {
		if( 0  <= x && x < xDim && 0 <= y && y < yDim)
			return tiles.get(x+y*xDim);
		return null;
	}
	
}
