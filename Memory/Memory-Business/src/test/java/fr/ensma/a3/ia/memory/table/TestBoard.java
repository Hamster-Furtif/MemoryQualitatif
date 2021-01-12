package fr.ensma.a3.ia.memory.table;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.table.card.Card;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;
import mockit.Expectations;
import mockit.Mocked;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBoard {
	
	private Board small;
	
	@Mocked
	private Tile t1, t2, t3, t4, t5;
	private List<Tile> thetiles, othertiles, alltiles;
	private Game game;
	private Card card, acard;
	private SpecialCard scard;
	
	@Before
	public void initTest() {
		thetiles = new ArrayList<Tile>();
		thetiles.add(t1);
		thetiles.add(t2);
		othertiles = new ArrayList<Tile>();
		othertiles.add(t3);
		othertiles.add(t4);
		alltiles = new ArrayList<Tile>();
		alltiles.add(t1);
		alltiles.add(t2);
		alltiles.add(t3);
		alltiles.add(t4);
		scard.init();
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		new Expectations() {
			{
				Tile.generatePairsFromCards(Card.generate((4-2)/2));
				result = thetiles;
				times = 1;
				
				Tile.generateFromCards(SpecialCard.getRandomCards(2));
				result = othertiles;
				times = 1;
			}
		};
		small = new Board(game, 2, 2, 4);
		Assert.assertEquals(2, small.getXDim());
		Assert.assertEquals(2, small.getYDim());
		Assert.assertEquals(alltiles, small.getTiles());
	}
	
	@Test
	public void T04_testshuffleTiles() {
		small.shuffleTiles();
		Assert.assertNotSame(alltiles, small.getTiles());
	}
	
	@Test
	public void T01_testgetTile() {
		Assert.assertEquals(t1, small.getTile(0, 0));
		Assert.assertEquals(null, small.getTile(-1, 0));
		Assert.assertEquals(null, small.getTile(2, 0));
		Assert.assertEquals(null, small.getTile(0, -1));
		Assert.assertEquals(null, small.getTile(0, 2));
		Assert.assertEquals(null, small.getTile(0, -1));
	}
	
	@Test
	public void T02_testGetRandomTile() {
		t5 = small.getRandomTile();
		Assert.assertTrue((t5 == t1) || (t5 == t2) || (t5 == t3) || (t5 == t4));
	}
	
	@Test
	public void T03_testPopCard() {
		new Expectations() {
			{
				t5.getCard();
				result = card;
				times = 1;
				
			}
		};
		t5 = small.getTile(0, 0);
		acard = small.popCard(t5);
		Assert.assertEquals(acard, card);
		Assert.assertTrue(t5.isEmpty());
	}
	
	
	
}
