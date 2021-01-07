package fr.ensma.a3.ia.memory.event;

public interface IEventManager<T extends Event>{

	/**
	 * Subscribes an event handler to this event manager
	 * @param handler The {@link IEventHandler} to subscribe to this manager
	 */
	public void subscribe(IEventHandler<T> handler);
	
	/**
	 * Triggers an event for event handlers subscribed to this manager by calling their {@link IEventHandler#onEvent(Event)} method.
	 * Every handler gets called unless one of them cancels the event.
	 * @param event The {@link Event} to trigger
	 */
	public void triggerEvent(T event);
	
}
