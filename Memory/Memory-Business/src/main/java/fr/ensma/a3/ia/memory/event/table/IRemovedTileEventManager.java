
package fr.ensma.a3.ia.memory.event.table;

public interface IRemovedTileEventManager {
	
	public void triggerEvent(RemovedTileEvent event);
	
	void subscribe(IRemovedTileEventHandler handler);
	
	void unsubscribe(IRemovedTileEventHandler handler);

}
