package fr.ensma.a3.ia.memory.player;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

public class TestBotPlayer {
	
	private BotPlayer walle;
	
	@Before
	public void initTest() {
		walle = new BotPlayer("Wall.e");
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		Assert.assertTrue(walle.getID() == "Wall.e");
	}
}
