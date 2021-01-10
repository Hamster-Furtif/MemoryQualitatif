package fr.ensma.a3.ia.memory.item;

import fr.ensma.a3.ia.memory.event.Event;
import fr.ensma.a3.ia.memory.event.IEventObserver;
import fr.ensma.a3.ia.memory.event.player.EndOfTurnEvent;

public class PrecieuxItem extends Item implements IEventObserver{

	private int remaining_uses = 5;
	
	/**
	 * Creates a new {@link PrecieuxItem}
	 * @param id The id of the {@link PrecieuxItem}
	 */
	public PrecieuxItem(String id) {
		super(id);
	}

	@Override
	public void handle(Event event) {
		if(event instanceof EndOfTurnEvent) {
			
		EndOfTurnEvent eotEvent = (EndOfTurnEvent)event;
			
			if(!eotEvent.isSuccessful() && eotEvent.getPlayer().getInventory().contains(this)) {
				eotEvent.setCancelled(true);
				if(--remaining_uses == 0) {
					eotEvent.getPlayer().getInventory().remove(this);
					eotEvent.getGame().getBoard().getRandomTile().addItem(this);
				}
				
			}
		}
	}
}
