package fr.ensma.a3.ia.memory.table;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.memory.item.Item;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.card.Card;

public class Tile {

	private Card card;
	private Item item;
	private boolean flipped = false;
	private boolean empty = false;
	
	public Tile(Card card) {
		this.card = card;
	}
	
	private Tile() {
		empty = true;
	}
	
	public Card getCard() {
		return card;
	}

	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean pairs(Tile tile) {
		return card.pairs(tile.getCard());
	}

	/**
	 * @return true if the card is visible, false if not.
	 */
	public boolean isFlipped() {
		return flipped;
	}

	/**
	 * Sets if the 'flipped' state of the tile, i.e. whether or not the {@link Card} is visible. Please never do something like tile.setFlipped(!tile.isFlipped()), for you should always know exactly what you're doing.
	 * @param flipped Whether or not the card is flipped.
	 */
	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}
	
	public static List<Tile> generateFromCards(List<Card> list){
		ArrayList<Tile> lst = new ArrayList<Tile>();
		
		for(Card card : list) {
			lst.add(new Tile(card));
		}
		
		return lst;
	} 
	
	public static List<Tile> generatePairsFromCards(List<Card> list){
		
		List<Tile> lst = generateFromCards(list);
		lst.addAll(generateFromCards(list));
		
		return lst;
	}
	
	public void popItemToInventory(AbstractPlayer player) {
		if(item != null) {
			player.getInventory().add(item);
			item = null;
		}
	}
	
	public static List<Tile> generateEmpty(int n) {
		List<Tile> lst = new ArrayList<Tile>();
		
		for(int i = 0; i < n; i++)
			lst.add(new Tile());
		
		return lst;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	
}
