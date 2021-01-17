package fr.ensma.a3.ia.memory.event;

public interface IEventObserver{
	
	/**
	 * Handles an incoming {@link Event}
	 * @param event The {@link Event} to be handled
	 */
	public void handle(Event event);
		
}
