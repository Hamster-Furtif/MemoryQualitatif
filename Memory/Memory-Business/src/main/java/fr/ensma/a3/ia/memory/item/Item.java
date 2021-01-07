package fr.ensma.a3.ia.memory.item;

public abstract class Item {

	protected String id;
	
	public Item(String id) {
		this.id = id;
	}
		
	/**
	 * Returns the id of the {@link Item}
	 * @return The id of the {@link Item}
	 */
	public String getID() {
		return id;
	}
}
