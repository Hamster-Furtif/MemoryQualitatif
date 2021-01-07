package fr.ensma.a3.ia.memory.table.card;

import junit.framework.Assert;

public class TestCard {
	
	private Card card;
	
	@Before
	public void initTest() {
		card = new Card();
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		Assert.assertEquals(1, card.id);
	}
	
}
