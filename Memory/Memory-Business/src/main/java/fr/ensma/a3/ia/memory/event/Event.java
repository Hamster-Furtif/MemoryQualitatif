package fr.ensma.a3.ia.memory.event;

public abstract class Event {

	protected boolean isCancelled = false;
	
	public void setCancelled(boolean b) {
		isCancelled = b;
	}
	
	public boolean isCancelled() {
		return isCancelled;
	}
}
