package fr.ensma.a3.ia.memory.table.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;

public abstract class SpecialCard extends Card{

	private static ArrayList<SpecialCard> specialCards = new ArrayList<SpecialCard>();
	
	private String name;
	
	static {
		init();
		System.out.println("Special cards generated !");
	}
	
	protected SpecialCard(String name) {
		specialCards.add(this);
		this.name = name;
	}
	
	public void specialAction(AbstractPlayer player) {}
	
	/**
	 * Returns the unique instance of a special card class
	 * @param <T> the type of Special Card to be returned
	 * @param cl the class you want to instantiate
	 * @return the unique instance of the desired class, or null if it has not been registered.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends SpecialCard> T getInstanceOf(Class<T> cl) {
		for(SpecialCard c : specialCards)
			if(cl.isInstance(c))
				return (T)c;
		return null;
	}
	
	/**
	 * Returns a list of random, non unique special cards, picked from the {@link SpecialCard} registry
	 * @param n The number of cards to be randomly picked
	 * @return A list of n Special Cards
	 */
	public static List<Card> getRandomCards(int n){
		List<Card> lst = new ArrayList<Card>();
		Random rd = new Random();
		for(int i = 0; i < n; i++)
			lst.add(specialCards.get(rd.nextInt(specialCards.size())));		
		return lst;
	}
	
	public String getName() {
		return name;
	}
	
	private static void init() {
		new DansTaFace();
		new MelangeTout();
		new ReveleTout();
	}
	
	public static List<SpecialCard> getSpecialCards(){
		return specialCards;
	}
		
}
