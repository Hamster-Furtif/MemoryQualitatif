package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.event.player.EndOfTurnEvent;
import fr.ensma.a3.ia.memory.item.Item;
import fr.ensma.a3.ia.memory.item.PrecieuxItem;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Tile;
import fr.ensma.a3.ia.memory.table.card.Card;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;
import junit.framework.Assert;

public class TestTurned0 {
	
	private Turned0 turn0to1, turn0towait;
	
	@Mocked
	private AbstractPlayer player1, player2;
	private Card acard;
	private Item anitem;
	private SpecialCard aspecialcard;
	private EndOfTurnEvent endevent;
	private Tile atile;
	
	@Before
	public void initTest() {
		anitem = new PrecieuxItem("Precieux");
		atile.setItem(anitem);
		turn0to1 = new Turned0(player1);
		turn0towait = new Turned0(player2);
	}
	
	@Test
	public void T00_testConstructeurAccesseur() {
		Assert.assertEquals(player1, turn0to1.getPlayer());
	}
	
	@Test
	public void T01_test
	
	
	
}
