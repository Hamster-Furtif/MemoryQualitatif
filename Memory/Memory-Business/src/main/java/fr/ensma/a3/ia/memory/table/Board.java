package fr.ensma.a3.ia.memory.table;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private int size;
	private List<Tile> tiles;
	
	public Board(int sz) {
		size = sz;
		tiles = new ArrayList<Tile>();
	}
	
}
