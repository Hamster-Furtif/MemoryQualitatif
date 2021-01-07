package fr.ensma.a3.ia.memory.player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestHumanPlayer {
	
	private HumanPlayer cunegonde;
	
	@Before
	public void initTest() {
		cunegonde = new HumanPlayer("Cunégonde");
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		Assert.assertTrue(cunegonde.getID() == "Cunégonde");
	}
}
