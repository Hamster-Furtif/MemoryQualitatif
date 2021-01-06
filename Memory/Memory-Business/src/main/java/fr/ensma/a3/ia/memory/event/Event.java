package fr.ensma.a3.ia.memory.event;

public abstract class Event {

	protected boolean isCancelled = false;
	
	protected void setCancelled(boolean b) {
		isCancelled = b;
	}
	
	protected boolean isCancelled() {
		return isCancelled;
	}
}
