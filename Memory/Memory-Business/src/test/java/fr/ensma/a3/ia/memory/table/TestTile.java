package fr.ensma.a3.ia.memory.table;

import fr.ensma.a3.ia.memory.item.Item;
import fr.ensma.a3.ia.memory.item.PrecieuxItem;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.card.Card;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import mockit.Expectations;
import mockit.Mocked;


public class TestTile {
	
	private Tile tile;
	
	@Mocked
	private Card card;
	private Item anitem, nullitem;
	private AbstractPlayer player1;
	
	@Before
	public void initTest() {
		anitem = new PrecieuxItem("Precieux");
		nullitem = null;
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		tile.generateFromCard(card);
		Assert.assertEquals(card, tile.getCard());
		Assert.assertNotNull(tile.getItem());
		tile.setEmpty(true);
		Assert.assertTrue(tile.isEmpty());
		tile.setItem(anitem);
		Assert.assertEquals(anitem, tile.getItem());
		tile.setFlipped(true);
		Assert.assertTrue(tile.isFlipped());
		
	}
	
	@Test
	public void test_PopItemToInventory() {
		tile.setItem(anitem);
		tile.popItemToInventory(player1);
		Assert.assertEquals(null, anitem);
		tile.setItem(nullitem);
		tile.popItemToInventory(player1);
	}
}
