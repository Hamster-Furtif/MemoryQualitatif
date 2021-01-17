package fr.ensma.a3.ia.memory.event;

public interface IEventManager{


	
	/**
	 * Triggers an event for event handlers subscribed to this manager by calling their {@link IEventObserver#onEvent(Event)} method.
	 * Every handler gets called unless one of them cancels the event.
	 * @param event The {@link Event} to trigger
	 */
	public void triggerEvent(Event event);

	/**
	 * Subscribes an event handler to this event manager
	 * @param type The type of {@link Event} to subscribe to
	 * @param handler The {@link IEventObserver} to subscribe to this manager
	 */
	void subscribe(Class<? extends Event> type, IEventObserver handler);
	
	/**
	 * Unsubscribes an event handler from this event manager
	 * @param type The type of {@link Event} to unsubscribe from
	 * @param handler The {@link IEventObserver} to unsubscribe from this manager
	 */
	void unsubscribe(Class<? extends Event> type, IEventObserver handler);

	
}
