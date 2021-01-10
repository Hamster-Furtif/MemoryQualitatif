package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.event.player.EndOfTurnEvent;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Tile;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;

public class Turned0 extends AbstractPlayerState {
	
	public Turned0(AbstractPlayer player) {
		super(player);
	}
	
	@Override
	public void toWaiting() {
		player.setState(player.getStateWaiting());
	}
	
	@Override
	public void toTurned1() {
		player.setState(player.getStateTurned1());
	}
	
	@Override
	public void tileFlipped(Tile tile) {
		
		tile.popItemToInventory(player);
		
		if(tile.getCard() instanceof SpecialCard) {
			
			((SpecialCard)tile.getCard()).specialAction(player);
			
			EndOfTurnEvent event = new EndOfTurnEvent(player, false);
			player.getGame().triggerEvent(event);
			
			if(!event.isCancelled())
				toWaiting();

		}
		else {
			player.setTurnedTile(tile);
			toTurned1();
		}
	}

}
