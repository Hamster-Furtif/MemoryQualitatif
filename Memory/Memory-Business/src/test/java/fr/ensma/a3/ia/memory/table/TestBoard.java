package fr.ensma.a3.ia.memory.table;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import mockit.Mocked;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBoard {
	
	private Board small;
	
	@Mocked
	private Tile t1, t2, t3, t4;
	private List<Tile> thetiles, othertiles;
	
	@Before
	public void initTest() {
		thetiles = new ArrayList<Tile>();
		thetiles.add(t1);
		thetiles.add(t2);
		thetiles.add(t3);
		thetiles.add(t4);
		othertiles = new ArrayList<Tile>();
		othertiles.add(t1);
		othertiles.add(t2);
		othertiles.add(t3);
		othertiles.add(t4);
		small = new Board(2,2,thetiles);
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		Assert.assertEquals(2, small.getxDim());
		Assert.assertEquals(2, small.getyDim());
		Assert.assertEquals(thetiles, small.gettiles());
	}
	
	@Test
	public void T02_testshuffleTiles() {
		small.shuffleTiles();
		Assert.assertNotSame(othertiles, small.gettiles());
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
	
	
}
