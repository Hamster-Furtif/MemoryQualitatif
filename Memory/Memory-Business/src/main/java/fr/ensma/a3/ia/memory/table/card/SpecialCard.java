package fr.ensma.a3.ia.memory.table.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class SpecialCard extends Card{

	private static ArrayList<SpecialCard> specialCards = new ArrayList<SpecialCard>();
		
	protected SpecialCard() {
		specialCards.add(this);
	}
	
	public static SpecialCard getInstanceOf(Class<SpecialCard> cl) {
		for(SpecialCard c : specialCards)
			if(cl.isInstance(c))
				return c;
		return null;
	}
	
	public static List<SpecialCard> getRandomCards(int n){
		List<SpecialCard> lst = new ArrayList<SpecialCard>();
		Random rd = new Random();
		for(int i = 0; i < n; i++)
			lst.add(specialCards.get(rd.nextInt(specialCards.size())));
		return lst;
	}
}
