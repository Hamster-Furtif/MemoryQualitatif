package fr.ensma.a3.ia.memory.player;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.item.Item;
import fr.ensma.a3.ia.memory.player.playerstate.IPlayerState;
import fr.ensma.a3.ia.memory.player.playerstate.Turned0;
import fr.ensma.a3.ia.memory.player.playerstate.Turned1;
import fr.ensma.a3.ia.memory.player.playerstate.Waiting;
import fr.ensma.a3.ia.memory.table.Tile;
import fr.ensma.a3.ia.memory.table.card.Card;

public class TestHumanPlayer {
	
	private AbstractPlayer cunegonde;
	final private Waiting stateWaiting = new Waiting(cunegonde);
	final private Turned0 stateTurned0 = new Turned0(cunegonde);
	final private Turned1 stateTurned1 = new Turned1(cunegonde);
	
	@Mocked
	private List<Card> thecards;
	private List<Item> theinventory;
	private Tile theturned_tile;
	protected Game game1;
	protected IPlayerState thecurrentState;
	
	@Before
	public void initTest() {
		cunegonde = new HumanPlayer("Cunégonde");
		cunegonde.setGame(game1);
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		Assert.assertTrue(cunegonde.getID() == "Cunégonde");
		Assert.assertEquals(stateWaiting, cunegonde.getState());
		Assert.assertNotNull(cunegonde.getCards());
		Assert.assertNotNull(cunegonde.getInventory());
		Assert.assertEquals(game1, cunegonde.getGame());
		
	}
}
