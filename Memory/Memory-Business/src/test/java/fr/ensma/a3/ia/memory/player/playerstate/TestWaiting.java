package fr.ensma.a3.ia.memory.player.playerstate;

import org.junit.Test;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import junit.framework.Assert;
import mockit.Mocked;

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
