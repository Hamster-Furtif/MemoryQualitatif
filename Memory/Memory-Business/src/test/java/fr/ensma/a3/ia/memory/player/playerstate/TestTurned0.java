package fr.ensma.a3.ia.memory.player.playerstate;

import org.junit.Before;
import org.junit.Test;

import fr.ensma.a3.ia.memory.event.player.EndOfTurnEvent;
import fr.ensma.a3.ia.memory.item.Item;
import fr.ensma.a3.ia.memory.item.PrecieuxItem;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Tile;
import fr.ensma.a3.ia.memory.table.card.Card;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;
import junit.framework.Assert;
import mockit.Expectations;
import mockit.Mocked;

public class TestTurned0 {
	
	private Turned0 turn0to1;
	
	@Mocked
	private AbstractPlayer player1, player2, player3, waiter, atturn0, atturn1;
	private Card acard;
	private Item anitem;
	private SpecialCard aspecialcard;
	private EndOfTurnEvent event;
	private Tile specialTile, normalTile;
	final private Waiting stateWaiting = new Waiting(waiter);
	final private Turned0 stateTurned0 = new Turned0(atturn0);
	final private Turned1 stateTurned1 = new Turned1(atturn1);
	
	@Before
	public void initTest() {
		anitem = new PrecieuxItem("Precieux");
		normalTile.setItem(anitem);
		turn0to1 = new Turned0(player1);
		player3.setTurnedTile(normalTile);
		specialTile.generateFromCard(aspecialcard);
		normalTile.generateFromCard(acard);
		
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		Assert.assertEquals(player1, turn0to1.getPlayer());
	}
	
	@Test
	public void T01_testTileFlipped() {
		new Expectations() {
			{
				specialTile.popItemToInventory(player1);
				result : anitem = null;
				times = 1;
				
				specialTile.popItemToInventory(player2);
				result : anitem = null;
				times = 1;
				
				normalTile.popItemToInventory(player3);
				result : anitem = null;
				result : player2.getInventory().add(anitem);
				times = 1;
				
				event.isCancelled();
				result = false;
				times = 1;
				
				event.isCancelled();
				result = true;
				times = 1;
				
				player3.setTurnedTile(normalTile);
				result : player3.getTurnedTile();
			}
		};
		
		player1.tileFlipped(normalTile);
		player2.tileFlipped(specialTile);
		player3.tileFlipped(specialTile);
		
		Assert.assertEquals(waiter.getStateWaiting(), player2.getState());
		Assert.assertEquals(atturn0.getStateTurned1(), player3.getState());
		
		Assert.assertEquals(atturn1.getStateTurned1(), player1.getState());
		
	}
	
	
	
}
