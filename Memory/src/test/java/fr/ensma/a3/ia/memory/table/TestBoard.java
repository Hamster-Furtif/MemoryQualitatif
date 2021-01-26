package fr.ensma.a3.ia.memory.table;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.ensma.a3.ia.memory.Game;
import mockit.Mocked;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBoard {
	
	private static Game game;
	private static Board small = new Board(game, 4);
	private Board theBoard;
	private static List<Tile> alltiles;
	
	@Mocked
	private static Tile t1, t2, t3, t4, t5;
	
	@BeforeClass
	public static void initTest() {
		alltiles = new ArrayList<Tile>();
		alltiles = small.getTiles();
		t5 = small.getRandomTile();
		t1 = alltiles.get(0);
		t2 = alltiles.get(1);
		t3 = alltiles.get(2);
		t4 = alltiles.get(3);
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		Assert.assertEquals(2, small.getXDim());
		Assert.assertEquals(2, small.getYDim());
		
	}
	
	
	@Test
	public void T01_testgetTile() {
		Assert.assertEquals(null, small.getTile(-1, 0));
		Assert.assertEquals(null, small.getTile(2, 0));
		Assert.assertEquals(null, small.getTile(0, -1));
		Assert.assertEquals(null, small.getTile(0, 2));
		Assert.assertEquals(null, small.getTile(0, -1));
	}
	
	
	@Test
	public void T02_testGetRandomTile() {
		Assert.assertTrue((t5 == t1) || (t5 == t2) || (t5 == t3) || (t5 == t4));
	}
	
	@Test
	public void T03_testPopCard() {
		theBoard = small;
		theBoard.getTile(0, 0).setEmpty(false);
		theBoard.popCard(theBoard.getTile(0, 0));
		Assert.assertTrue(theBoard.getTile(0, 0).isEmpty());
	}
	
	
}
