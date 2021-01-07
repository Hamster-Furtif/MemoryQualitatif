package fr.ensma.a3.ia.memory.table;

import fr.ensma.a3.ia.memory.table.card.Card;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import mockit.Mocked;


public class TestTile {
	
	private Tile tile;
	
	@Mocked
	private Card card;
	
	@Before
	public void initTest() {
		tile = new Tile(card);
	}
	
	@Test
	public void T01_testgetTile() {
		Assert.assertEquals(card, tile.getCard());
		Assert.assertNotNull(tile.getItems());
	}
	
}
