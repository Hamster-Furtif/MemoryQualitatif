package fr.ensma.a3.ia.memory.table;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.memory.event.table.FlippedTileEvent;
import fr.ensma.a3.ia.memory.event.table.IFlippedTileEventHandler;
import fr.ensma.a3.ia.memory.event.table.IFlippedTileEventManager;
import fr.ensma.a3.ia.memory.item.Item;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.card.Card;

public class Tile implements IFlippedTileEventManager {

	public static final boolean FACE_UP = true;
	public static final boolean FACE_DOWN = false;
	
	private Card card;
	private Item item;
	private boolean flipped = false;
	private boolean empty = false;
	
	private List<IFlippedTileEventHandler> flippedTileEventHandlers;
	
	/**
	 * Returns a Tile with a specific card
	 * @param card
	 */
	public Tile(Card card) {
		this.card = card;
		flippedTileEventHandlers = new ArrayList<IFlippedTileEventHandler>();
	}
	
	private Tile() {
		empty = true;
	}
	
	/**
	 * Returns the {@link Card} on this tile. Can be null.
	 * @return
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * Returns the {@link Item} on this tile. Can be null.
	 * @return the {@link Item} on this tile
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Sets the {@link Item} on this tile. Overwrites any {@link Item} already on this card.
	 * @param item The {@link Item} to put on this tile
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Compares the {@link Card} on this tile with the {@link Card} on another tile. Returns null if any of the {@link Card}s is null.
	 * @param tile The tile to compare this tile with
	 * @return Whether or not the two tiles have the same {@link Card}
	 */
	public boolean pairs(Tile tile) {
		return card != null && card.pairs(tile.getCard());
	}

	/**
	 * @return true if the card is visible, false if not.
	 */
	public boolean isFlipped() {
		return flipped;
	}
	
	public boolean isFaceUp() {
		return flipped;
	}
	
	public boolean isFaceDown() {
		return !flipped;
	}

	private void flip() {
		FlippedTileEvent event = new FlippedTileEvent(this, flipped);
		triggerEvent(event);
		if(!event.isCancelled())
			flipped = !flipped;
	
	}
	
	/**
	 * Sets if the 'flipped' state of the tile, i.e. whether or not the {@link Card} is visible. Please never do something like tile.setFlipped(!tile.isFlipped()), for you should always know exactly what you're doing.
	 * @param flipped Whether or not the card is flipped.
	 */
	public void setFlipped(boolean flipped) {
		if(this.flipped != flipped)
			flip();
	}
	
	/**
	 * Generates a list of {@link Tile}, each containing one card from the list.
	 * @param list A list of {@link Card} (can be empty)
	 * @return a list of {@link Tile} of the same size as the input list
	 */
	public static List<Tile> generateFromCards(List<Card> list){
		ArrayList<Tile> lst = new ArrayList<Tile>();
		
		for(Card card : list) {
			lst.add(new Tile(card));
		}
		
		return lst;
	} 
	
	/**
	 * Generates a list of {@link Tile} from a list of {@link Card}. For each card, two distinct tiles will be generated.	
	 * @param list A list of {@link Card} (can be empty)
	 * @return a list of {@link Tile} twice the size as the input list
	 */
	public static List<Tile> generatePairsFromCards(List<Card> list){
		
		List<Tile> lst = generateFromCards(list);
		lst.addAll(generateFromCards(list));
		
		return lst;
	}
	
	/**
	 * Removes the {@link Item} from this card and adds it to a player's inventory. Does nothing if there is no item on this card.
	 * @param player The {@link AbstractPlayer} that must receive the Item
	 */
	public void popItemToInventory(AbstractPlayer player) {
		if(item != null) {
			player.getInventory().add(item);
			item = null;
		}
	}
	
	/**
	 * Generates a list of distinct empty tiles
	 * @param n The number of tiles to generate
	 * @return A list of empty tiles
	 */
	public static List<Tile> generateEmpty(int n) {
		List<Tile> lst = new ArrayList<Tile>();
		
		for(int i = 0; i < n; i++)
			lst.add(new Tile());
		
		return lst;
	}

	/**
	 * Returns whether or not this tile is marked as empty. This does not guarantees the is no card stored in this tile.
	 * @return whether or not this tile is marked as empty.
	 */
	public boolean isEmpty() {
		return empty;
	}

	/**
	 * Sets whether or not this tile is marked is empty. This does not affect the card on this tile.
	 * @param empty
	 */
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	@Override
	public void triggerEvent(FlippedTileEvent event) {
		for(IFlippedTileEventHandler handler : flippedTileEventHandlers)
			handler.handle(event);
	}


	@Override
	public void subscribe(IFlippedTileEventHandler handler) {
		if(!flippedTileEventHandlers.contains(handler))
			flippedTileEventHandlers.add(handler);
	}

	@Override
	public void unsubscribe(IFlippedTileEventHandler handler) {
		if(flippedTileEventHandlers.contains(handler))
			flippedTileEventHandlers.remove(handler);	
	}
	
}
