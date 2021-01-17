package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.event.player.EndOfTurnEvent;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Board;
import fr.ensma.a3.ia.memory.table.Tile;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;

public class Turned1 extends AbstractPlayerState {

	
	/**
	 * Creates a new {@link Turned1} {@link IPlayerState} for a given player. 
	 * This is the state the player is in when it has already flipped 1 {@link Tile} and must flip a second one.
	 * @param player The player to create the {@link Turned1} {@link IPlayerState} for.
	 */
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
		
		tile.popItemToInventory(player);
		
		if(tile.getCard() instanceof SpecialCard) {
			
			((SpecialCard)tile.getCard()).specialAction(player);
			event = new EndOfTurnEvent(player, false);
		}
		else {
			Tile firstTile = player.getTurnedTile();
			tile.setFlipped(true);
			if(tile.pairs(firstTile)) {
				
				event = new EndOfTurnEvent(player, true);
				Board currentBoard = player.getGame().getBoard();
				
				// Adds the flipped card to the list of card pair the player has won, while removing the tile from the game's Board
				player.addMatchingPair(currentBoard.popCard(tile));
				// Removes the first tile that was flipped from the game's Board
				currentBoard.popCard(firstTile);

			}
			else {
				event = new EndOfTurnEvent(player, false);
			}
		}
		
		player.getGame().getBoard().print();

		player.getGame().triggerEvent(event);
		
		if(event.isCancelled() || event.isSuccessful()) {
			
			toTurned0();
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
	}

}
