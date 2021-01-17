package fr.ensma.a3.ia.memory.table.card;

import java.util.ArrayList;
import java.util.List;

public class Card {

	protected static int nCards = 0;
	
	protected int id;
	
	/**
	 * Generate a new card with an unique protected ID
	 */
	public Card() {
		id = nCards++;
	}
	
	/**
	 * Compares two cards and returns whether or not they are the same card.
	 * @param card
	 * @return Whether or not the two cards are the same
	 */
	public boolean pairs(Card card) {
		return card == this;
	}
	
	/**
	 * Generates a list of unique cards
	 * @param n The size of the list to be generated
	 * @return A list of unique cards
	 */
	public static List<Card> generate(int n){
		ArrayList<Card> cards = new ArrayList<Card>();
		
		for (int i = 0; i < n; i++)
			cards.add(new Card());
		
		return cards;
	}
	
	/**
	 * Returns the ID of the card
	 * @return the ID of the card
	 */
	public int getId() {
		return id;
	}
	
}
