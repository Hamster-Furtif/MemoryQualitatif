package fr.ensma.a3.ia.memory.event.player;

public interface IEndOfTurnEventManager {

	public void triggerEvent(EndOfTurnEvent event);
	
	void subscribe(IEndOfTurnEventHandler handler);
	
	void unsubscribe(IEndOfTurnEventHandler handler);
	
}
