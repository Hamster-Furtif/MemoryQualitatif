package fr.ensma.a3.ia.memory.event.table;

public interface IFlippedTileEventManager {

	public void triggerEvent(FlippedTileEvent event);
	
	void subscribe(IFlippedTileEventHandler handler);
	
	void unsubscribe(IFlippedTileEventHandler handler);
	
}
