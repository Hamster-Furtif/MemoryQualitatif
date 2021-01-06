package fr.ensma.a3.ia.memory.event;

public interface IEventHandler<T extends Event> {

	public void onEvent(T event);
	
}
