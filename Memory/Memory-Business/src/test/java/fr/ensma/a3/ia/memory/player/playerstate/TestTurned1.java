package fr.ensma.a3.ia.memory.player.playerstate;

import org.junit.Before;
import org.junit.Test;

import fr.ensma.a3.ia.memory.event.player.EndOfTurnEvent;
import fr.ensma.a3.ia.memory.item.Item;
import fr.ensma.a3.ia.memory.item.PrecieuxItem;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Board;
import fr.ensma.a3.ia.memory.table.Tile;
import fr.ensma.a3.ia.memory.table.card.Card;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;
import junit.framework.Assert;
import mockit.Expectations;
import mockit.Mocked;

public class TestTurned1 {

private Turned1 turn1;
	
	@Mocked
	private AbstractPlayer player1, waiter, atturn0;
	private Card acard;
	private Item anitem;
	private SpecialCard aspecialcard;
	private EndOfTurnEvent event;
	private Tile specialTile, normalTile, normalTile2;
	final private Waiting stateWaiting = new Waiting(waiter);
	final private Turned0 stateTurned0 = new Turned0(atturn0);
	private Board theboard;
	
	@Before
	public void initTest() {
		anitem = new PrecieuxItem("Precieux");
		normalTile.setItem(anitem);
		turn1 = new Turned1(player1);
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		Assert.assertEquals(player1, turn1.getPlayer());
	}
	
	@Test
	public void T01_testTileFlipped() {
		new Expectations() {
			{
				specialTile.popItemToInventory(player1);
				result : player1.getInventory().add(anitem);
				result : anitem = null;
				times = 1;
				
				normalTile.popItemToInventory(player1);
				result : anitem = null;
				times = 3;
				
				player1.getTurnedTile();
				result = normalTile2;
				times = 3;
				
				normalTile.pairs(normalTile2);
				result = false;
				times = 2;
				
				normalTile.pairs(normalTile2);
				result = true;
				times = 1;
				
				player1.getGame().getBoard();
				result = theboard;
				times = 1;
				
				player1.addMatchingPair(theboard.popCard(normalTile2));
				result : player1.getCards().add(acard);
				times = 1;
				
				event.isCancelled();
				result = true;
				times = 1;
				
				event.isSuccessful();
				result = true;
				times = 1;
				
				event.isSuccessful();
				result = false;
				times = 1;
				
				event.isCancelled();
				result = false;
				times = 1;
				
				event.isSuccessful();
				result = true;
				times = 1;
				
				event.isSuccessful();
				result = false;
				times = 1;
			}
		};
		
		
		player1.tileFlipped(normalTile);
		Assert.assertEquals(stateTurned0, player1.getState());
		player1.tileFlipped(normalTile);
		Assert.assertEquals(stateTurned0, player1.getState());
		player1.tileFlipped(normalTile2);
		Assert.assertEquals(stateTurned0, player1.getState());
		player1.tileFlipped(normalTile);
		Assert.assertEquals(stateWaiting, player1.getState());
		
		
		
	}
	
}
