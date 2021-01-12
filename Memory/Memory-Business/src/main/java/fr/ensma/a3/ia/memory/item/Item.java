package fr.ensma.a3.ia.memory.item;

public abstract class Item {

	protected String id;
	
	/**
	 * Creates an {@link Item} with a given ID
	 * @param id The ID of the {@link Item} to be created
	 */
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
