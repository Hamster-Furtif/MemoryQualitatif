package fr.ensma.a3.ia.memory.event;

import fr.ensma.a3.ia.memory.event.player.EndOfTurnEvent;

public interface IEventManager<T extends Event>{

	public void subscribe(IEventHandler<T> handler);
	public void triggerEvent(T event);
	
}
