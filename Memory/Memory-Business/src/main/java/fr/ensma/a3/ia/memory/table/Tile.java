package fr.ensma.a3.ia.memory.table;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.memory.item.Item;
import fr.ensma.a3.ia.memory.table.card.Card;

public class Tile {

	private Card card;
	private List<Item> items;
	
	protected Tile(Card card) {
		this.card = card;
		items = new ArrayList<Item>();
	}
	
	public Card getCard() {
		return card;
	}
	
	public List<Item> geItems(){
		return items;
	}
	
}
