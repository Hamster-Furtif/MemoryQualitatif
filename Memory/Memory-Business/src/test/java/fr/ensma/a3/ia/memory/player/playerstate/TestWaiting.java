package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import junit.framework.Assert;

public class TestWaiting {
	private Waiting wait;
	
	@Mocked
	private AbstractPlayer aplayer;
	
	@Test
	public void T00_testConstructeurAccesseur() {
		wait = new Waiting(aplayer);
		Assert.assertEquals(aplayer, wait.getPlayer());
	}
}
