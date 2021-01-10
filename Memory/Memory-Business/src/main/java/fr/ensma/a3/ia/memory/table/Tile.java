package fr.ensma.a3.ia.memory.table;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.memory.item.Item;
import fr.ensma.a3.ia.memory.table.card.Card;

public class Tile {

	private Card card;
	private List<Item> items;
	private boolean flipped;
	
	protected Tile(Card card) {
		this.card = card;
		items = new ArrayList<Item>();
		setFlipped(false);
	}
	
	public Card getCard() {
		return card;
	}

	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public boolean containsItem(Item item) {
		return items.contains(item);
	}

	public List<Item> getItems() {
		return items;
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
	
	public static Tile generateFromCard(Card card) {
		return new Tile(card);
	}
	
	public static List<Tile> generateFromCards(List<Card> list){
		ArrayList<Tile> lst = new ArrayList<Tile>();
		
		for(Card card : list)
			lst.add(generateFromCard(card));
		
		return lst;
	} 
	
	public static List<Tile> generatePairsFromCards(List<Card> list){
		
		List<Tile> lst = generateFromCards(list);
		lst.addAll(generateFromCards(list));
		
		return lst;
	}
}
