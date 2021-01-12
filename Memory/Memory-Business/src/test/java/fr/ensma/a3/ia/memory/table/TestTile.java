package fr.ensma.a3.ia.memory.table;

import fr.ensma.a3.ia.memory.item.Item;
import fr.ensma.a3.ia.memory.item.PrecieuxItem;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.card.Card;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mockit.Expectations;
import mockit.Mocked;


public class TestTile {
	
	private Tile tile;
	private List<Tile> tiles;
	
	@Mocked
	private Card card;
	private Item anitem, nullitem, item;
	private AbstractPlayer player;
	private List<Card> cards;
	
	@Before
	public void initTest() {
		anitem = new PrecieuxItem("Precieux");
		item = new PrecieuxItem("LePrecieux");
		nullitem = null;
		card = new Card();
		System.out.println("id card = " + card.getId());
		cards = new ArrayList<Card>();
		cards.add(card);
		tiles = new ArrayList<Tile>();
		tiles = Tile.generateFromCards(cards);
		tile = tiles.get(0);
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		Assert.assertEquals(card, tile.getCard());
		Assert.assertNull(tile.getItem());
		tile.setEmpty(true);
		Assert.assertTrue(tile.isEmpty());
		tile.setItem(anitem);
		Assert.assertEquals(anitem, tile.getItem());
		tile.setFlipped(true);
		Assert.assertTrue(tile.isFlipped());
		
	}
	
	@Test
	public void T01_testPopItemToInventory() {
		new Expectations() {
			{
				player.getInventory().add(item);
				result = null;
				times = 1;
			}
		};
		tile.setItem(anitem);
		tile.popItemToInventory(player);
		Assert.assertEquals(null, anitem);
		tile.setItem(nullitem);
		tile.popItemToInventory(player);
	}
}
