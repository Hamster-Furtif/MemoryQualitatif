package fr.ensma.a3.ia.memory;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.ensma.a3.ia.memory.event.Event;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.player.BotPlayer;
import fr.ensma.a3.ia.memory.table.Board;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import mockit.Mocked;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGame {
	
	private Game game1, game2;
	
	@Mocked
	private AbstractPlayer Humain1, Humain2, Humain3;
	private List<AbstractPlayer> players1, players2, players1bis;
	private Board small;
	private BotPlayer RainMan;
	private Event cancelled, notcancelled;
	//private IEventObserver handler;

	@Before
	public void initTest() {
		RainMan = new BotPlayer("RainMan");
		players1 = new ArrayList<AbstractPlayer>();
		players2 = new ArrayList<AbstractPlayer>();
		players1bis = new ArrayList<AbstractPlayer>();
		players1.add(Humain1);
		players2.add(Humain2);
		players2.add(Humain3);
		players1bis.add(Humain1);
		players1bis.add(RainMan);
		game1 = new Game(46, players1);
		game2 = new Game(82, players2);
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		Assert.assertEquals(players2, game2.getPlayers());
		
		Assert.assertEquals(46, game1.getNbCards());
		
		//Assert.assertNotNull(game1.getObserversMap());
		Assert.assertEquals(players1bis, game1.getPlayers());
		Assert.assertEquals(game1, Humain1.getGame());
		
	}
	
	@Test
	public void T01_testSubscribe() {
		
	}
	
	@Test
	public void T02_testTriggerEvent() {
		
	}
	
	@Test
	public void T03_testUnsubscribe() {
		
	}
	
}
