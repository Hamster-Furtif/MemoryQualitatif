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
	
	private int nbCards, xDim, yDim;
	
	/**
	 * @deprecated Please use {@link #Board(Game, int)}instead
	 */
	public Board(int xDim, int yDim, List<Tile> tiles) {
		this.xDim = xDim;
		this.yDim = yDim;
		this.tiles = tiles;
	}
	
	/**
	 * @deprecated Please use {@link #Board(Game, int)}instead
	 */
	public Board(Game game, int xDim, int yDim, int nbCards) {
		this.xDim = xDim;
		this.yDim = yDim;
		this.nbCards = nbCards;
		
		int nSpecialCards = Board.getSpecialCardNumber(nbCards);

		tiles = new ArrayList<Tile>();
		tiles.addAll(Tile.generatePairsFromCards(Card.generate((nbCards-nSpecialCards)/2)));
		tiles.addAll(Tile.generateFromCards(SpecialCard.getRandomCards(nSpecialCards)));
	}
	
	/**
	 * Instanciates a Board with the appropriate dimensions and number of special cards
	 * @param game The {@link Game} this board is being used in
	 * @param nbCards The number of cards initially on the board. Must be even.
	 */
	public Board(Game game, int nbCards) {
		int[] arr = Board.getDimFromCardNumber(nbCards);
		xDim = arr[0];
		yDim = arr[1];
		
		this.nbCards = nbCards;
		int nSpecialCards = Board.getSpecialCardNumber(nbCards);
		
		tiles = new ArrayList<Tile>();
		tiles.addAll(Tile.generatePairsFromCards(Card.generate((nbCards-nSpecialCards)/2)));
		tiles.addAll(Tile.generateFromCards(SpecialCard.getRandomCards(nSpecialCards)));
		shuffleTiles();
		tiles.addAll(Tile.generateEmpty(xDim*yDim-nbCards));
		
	}
	
	/**
	 * Shuffles the tiles on the board
	 */
	public void shuffleTiles() {
		Collections.shuffle(tiles);
	}
	
	/**
	 * Returns a tile at a specific position on the board
	 * @param x The x coordinate (0 < x < {@link #getXDim()} )
	 * @param y The Y coordinate (0 < y < {@link #getYDim()} )
	 * @return The tile at the position (x,y)
	 */
	public Tile getTile(int x, int y) {
		if( 0  <= x && x < xDim && 0 <= y && y < yDim)
			return tiles.get(x+y*xDim);
		return null;
	}

	/**
	 * Returns the width of the board
	 * @return the width of the board
	 */
	public int getXDim() {
		return xDim;
	}
	
	/**
	 * Returns the height of the board
	 * @return the height of the board
	 */
	public int getYDim() {
		return yDim;
	}

	/**
	 * Returns the list of the tiles on the board
	 * @return the list of the tiles on the board
	 */
	public List<Tile> getTiles() {
		return tiles;
	}
	
	/**
	 * Returns a random non-empty card from the board
	 * @return a random non-empty card from the board
	 */
	public Tile getRandomTile() {
		Random rd = new Random();
		return tiles.get(rd.nextInt(nbCards));
	}
	
	/**
	 * Removes a card from one of the tiles of the board and returns it. Marks the tile as empty.
	 * @param tile The tile to get the card from
	 * @return The card removed from the tile
	 */
	public Card popCard(Tile tile) {
		tile.setEmpty(true);
		return tile.getCard();
	}
	
	/**
	 * Displays the board
	 */
	public void print() {
		
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		System.out.print("  /");
		for(int x = 0; x < getXDim(); x++)
			System.out.print(" " + (x+1));
		System.out.print("\n");
		for(int x = 0; x < getXDim(); x++)
			System.out.print("--");
		System.out.print("\n");
		for(int y = 0; y < getYDim(); y++) {
			System.out.print((y+1) + " |");
			for(int x = 0; x < getXDim(); x++) {
				Tile tile = getTile(x, y);
				System.out.print(" " + (tile.isEmpty() ? " " :(tile.isFlipped() ? chars.charAt(tile.getCard().getId()) : ".")));
			}
			System.out.print("\n");
		}
			
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
