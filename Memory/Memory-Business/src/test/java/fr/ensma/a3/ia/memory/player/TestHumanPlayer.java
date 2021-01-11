package fr.ensma.a3.ia.memory.player;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.item.Item;
import fr.ensma.a3.ia.memory.player.playerstate.IPlayerState;
import fr.ensma.a3.ia.memory.player.playerstate.Turned0;
import fr.ensma.a3.ia.memory.player.playerstate.Turned1;
import fr.ensma.a3.ia.memory.player.playerstate.Waiting;
import fr.ensma.a3.ia.memory.table.Tile;
import fr.ensma.a3.ia.memory.table.card.Card;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestHumanPlayer {
	
	private AbstractPlayer cunegonde;
	final private Waiting stateWaiting = new Waiting(cunegonde);
	final private Turned0 stateTurned0 = new Turned0(cunegonde);
	final private Turned1 stateTurned1 = new Turned1(cunegonde);
	
	@Mocked
	private List<Card> thecards, emptycards;
	private List<Item> theinventory;
	private Tile theturned_tile;
	protected Game game1;
	protected IPlayerState thecurrentState;
	private Turned0 state0;
	private Turned1 state1;
	private Waiting statewait;
	private Card thecard;
	
	@Before
	public void initTest() {
		cunegonde = new HumanPlayer("Cunégonde");
		cunegonde.setGame(game1);
		state0 = new Turned0(cunegonde);
		state1 = new Turned1(cunegonde);
		statewait = new Waiting(cunegonde);
		thecards.add(thecard);
		emptycards = new ArrayList<Card>();
		theinventory = new ArrayList<Item>();
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		Assert.assertTrue(cunegonde.getID() == "Cunégonde");
		Assert.assertEquals(stateWaiting, cunegonde.getState());
		Assert.assertNotNull(cunegonde.getCards());
		Assert.assertNotNull(cunegonde.getInventory());
		Assert.assertEquals(game1, cunegonde.getGame());
		Assert.assertEquals(theinventory, cunegonde.getInventory());
		
	}
	
	@Test
	public void T01_testChangeStateToTurned1() {
		new Expectations() {
			{
				thecurrentState.tileFlipped(theturned_tile);
				result = cunegonde.setTurnedTile(theturned_tile);
				result = cunegonde.setState(cunegonde.getStateTurned1());
				times = 1;
			}
		};
		cunegonde.setState(cunegonde.getStateTurned0());
		Assert.assertEquals(stateTurned0, cunegonde.getState());
		cunegonde.tileFlipped(theturned_tile);
		Assert.assertEquals(stateTurned1, cunegonde.getState());
		
	}
	
	@Test
	public void T02_testChangeStateToWaiting() {
		new Expectations() {
			{
				thecurrentState.tileFlipped(theturned_tile);
				result = cunegonde.addMatchingPair(thecard);;
				result = cunegonde.setState(cunegonde.getStateWaiting());
				times = 1;
			}
		};
		cunegonde.tileFlipped(theturned_tile);
		Assert.assertEquals(stateWaiting, cunegonde.getState());
		
	}
	
	@Test
	public void T03_testAddMatchingPair() {
		cunegonde.addMatchingPair(thecard);
		Assert.assertEquals(thecards, cunegonde.getCards());
	}
	
	@Test
	public void T04_testPopCards() {
		cunegonde.popCards();
		Assert.assertEquals(emptycards, cunegonde.getCards());
	}
	
	
}
