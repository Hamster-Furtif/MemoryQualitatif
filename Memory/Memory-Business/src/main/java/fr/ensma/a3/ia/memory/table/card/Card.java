package fr.ensma.a3.ia.memory.table.card;

public class Card {

	protected static int nCards = 0;
	
	protected int id;
	
	public Card() {
		id = nCards++;
	}
	
}
