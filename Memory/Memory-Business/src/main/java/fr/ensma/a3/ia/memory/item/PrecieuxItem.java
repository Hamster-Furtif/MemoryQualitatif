package fr.ensma.a3.ia.memory.item;

import fr.ensma.a3.ia.memory.event.IEventHandler;
import fr.ensma.a3.ia.memory.event.player.EndOfTurnEvent;

public class PrecieuxItem extends Item implements IEventHandler<EndOfTurnEvent>{

	private int remaining_uses = 5;
	
	/**
	 * Creates a new {@link PrecieuxItem}
	 * @param id The id of the {@link PrecieuxItem}
	 */
	public PrecieuxItem(String id) {
		super(id);
	}

	@Override
	public void onEvent(EndOfTurnEvent event) {
		if(!event.isSuccessful() && event.getPlayer().getInventory().contains(this)) {
			event.setCancelled(true);
			if(--remaining_uses == 0) {
				event.getPlayer().getInventory().remove(this);
				event.getGame().getBoard().getRandomTile().addItem(this);
			}
			
		}
	}

}
