package fr.ensma.a3.ia.memory.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.table.card.Card;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;

public class Board {

	
	private List<Tile> tiles;
	
	private int xDim, yDim;
	
	/**
	 * @deprecated Please use {@link #Board(int, int, int)} instead
	 */
	public Board(int xDim, int yDim, List<Tile> tiles) {
		this.xDim = xDim;
		this.yDim = yDim;
		this.tiles = tiles;
	}
	
	public Board(Game game, int xDim, int yDim, int nbCards) {
		this.xDim = xDim;
		this.yDim = yDim;
		this.tiles = new ArrayList<Tile>();
		int nSpecialCards = Board.getSpecialCardNumber(nbCards);
		tiles.addAll(Tile.generatePairsFromCards(Card.generate((nbCards-nSpecialCards)/2)));
		tiles.addAll(Tile.generateFromCards(SpecialCard.getRandomCards(nSpecialCards)));
	}
	
	public Board(Game game, int nbCards) {
		int[] arr = Board.getDimFromCardNumber(nbCards);
		this.xDim = arr[0];
		this.yDim = arr[1];
		this.tiles = new ArrayList<Tile>();
		int nSpecialCards = Board.getSpecialCardNumber(nbCards);
		tiles.addAll(Tile.generatePairsFromCards(Card.generate((nbCards-nSpecialCards)/2)));
		tiles.addAll(Tile.generateFromCards(SpecialCard.getRandomCards(nSpecialCards)));
		tiles.addAll(Tile.generateEmpty(xDim*yDim-nbCards));
	}
	
	public void shuffleTiles() {
		Collections.shuffle(tiles);
	}
	
	public Tile getTile(int x, int y) {
		if( 0  <= x && x < xDim && 0 <= y && y < yDim)
			return tiles.get(x+y*xDim);
		return null;
	}

	public int getXDim() {
		return xDim;
	}
	
	public int getYDim() {
		return yDim;
	}

	public List<Tile> getTiles() {
		return tiles;
	}
	
	public Tile getRandomTile() {
		Random rd = new Random();
		return tiles.get(rd.nextInt(tiles.size()));
	}
	
	public Card popCard(Tile tile) {
		tile.setEmpty(true);
		return tile.getCard();
	}
	
	private static int getSpecialCardNumber(int nbCards) {
		if(nbCards < 10)
			return 2;
		else if(nbCards < 20)
			return 4;
		else
			return 6;
	}
	
	private static int[] getDimFromCardNumber(int nbCard) {
		
		int yDim = 1;
		while(yDim*yDim < nbCard && yDim < 10)
			yDim++;
		
		int xDim = yDim;
		while(yDim*(xDim-1) >= nbCard)
			xDim--;
		
		int[] arr = {xDim,yDim};
		return arr;
	}
	
}
