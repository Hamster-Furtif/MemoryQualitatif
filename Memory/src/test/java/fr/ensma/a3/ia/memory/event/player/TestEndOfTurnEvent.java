package fr.ensma.a3.ia.memory.event.player;

import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import mockit.Expectations;
import mockit.Mocked;

public class TestEndOfTurnEvent {
	
	private Boolean success = true;
	private EndOfTurnEvent eote;
	
	@Mocked
	private AbstractPlayer player1;
	private Game game1;
	
	@Test
	public void T00_testConstructeurAccesseur() {
		new Expectations() {
			{
				player1.getGame();
				result = game1;
				times = 1;
			}
		};
		eote = new EndOfTurnEvent(player1, success);
		Assert.assertEquals(player1, eote.getPlayer());
		Assert.assertEquals(game1, eote.getGame());
		Assert.assertTrue(eote.isSuccessful());
	}
	
}
