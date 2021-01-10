package fr.ensma.a3.ia.memory.table.card;

import java.util.ArrayList;
import java.util.List;

public class Card {

	protected static int nCards = 0;
	
	protected int id;
	
	public Card() {
		id = nCards++;
	}
	
	public boolean pairs(Card card) {
		return card == this;
	}
	
	public static List<Card> generate(int n){
		ArrayList<Card> cards = new ArrayList<Card>();
		
		for (int i = 0; i < n; i++)
			cards.add(new Card());
		
		return cards;
	}
	
}
