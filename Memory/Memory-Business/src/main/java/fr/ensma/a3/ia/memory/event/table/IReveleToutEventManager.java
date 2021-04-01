package fr.ensma.a3.ia.memory.event.table;

public interface IReveleToutEventManager {

	public void triggerEvent(ReveleToutEvent event);
	
	void subscribe(IReveleToutEventHandler handler);
	
	void unsubscribe(IReveleToutEventHandler handler);
	
}
