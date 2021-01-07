package fr.ensma.a3.ia.memory.event;

public abstract class Event {

	protected boolean isCancelled = false;
	
	/**
	 * Sets whether or not the event is cancelled
	 * @param isCancelled The new state of the event
	 */
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	
	/**
	 * Returns whether or not the event is cancelled
	 * @return the current state of the event
	 */
	public boolean isCancelled() {
		return isCancelled;
	}
}
