package fr.ensma.a3.ia.memory.event;

public interface IEventHandler<T extends Event> {

	/**
	 * Handles an incoming event
	 * @param event The incoming event
	 */
	public void onEvent(T event);
	
}
