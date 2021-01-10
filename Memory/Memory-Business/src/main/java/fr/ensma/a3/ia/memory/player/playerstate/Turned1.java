package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.event.player.EndOfTurnEvent;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Board;
import fr.ensma.a3.ia.memory.table.Tile;

public class Turned1 extends AbstractPlayerState {

	public Turned1(AbstractPlayer player) {
		super(player);
	}
	
	@Override
	public void toTurned0() {
		player.setState(player.getStateTurned0());
		player.getTurnedTile().setFlipped(false);
	}
	
	public void toWaiting() {
		player.setState(player.getStateWaiting());;
		player.getTurnedTile().setFlipped(false);
	}
	
	@Override
	public void tileFlipped(Tile tile) {
		
		EndOfTurnEvent event;
		
		Tile firstTile = player.getTurnedTile();
		
		if(tile.pairs(firstTile)) {
			
			event = new EndOfTurnEvent(player, true);
			Board currentBoard = player.getGame().getBoard();
			
			// Adds the flipped card to the list of card pair the player has won, while removing the tile from the game's Board
			player.addMatchingPair(currentBoard.popCard(tile));
			// Removes the first tile that was flipped from the game's Board
			currentBoard.popCard(firstTile);
			// Clears the tile saved in the players memory. This should not be useful if everything else is working properly but who knows ¯\_(ツ)_/¯
			player.setTurnedTile(null);
		}
		else {
			event = new EndOfTurnEvent(player, false);
		}
		
		player.getGame().triggerEvent(event);
		
		if(event.isCancelled() || event.isSuccessful()) {
			toTurned1();
		}
		else {
			toWaiting();
		}
		
		/*       _______________________________________________
		 *  	|    event		| cancelled 	| not cancelled |
		 *   	|---------------|---------------|---------------|
		 *   	| successful 	| goto Turned0	| goto Turned0	|
		 *   	|not successful | goto Turned0	| goto Waiting	|
		 *       ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
		 */	
		
		tile.setFlipped(false);
		firstTile.setFlipped(false);
	}

}
