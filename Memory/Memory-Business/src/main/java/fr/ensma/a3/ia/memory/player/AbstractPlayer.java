package fr.ensma.a3.ia.memory.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.item.Item;
import fr.ensma.a3.ia.memory.player.playerstate.IPlayerState;
import fr.ensma.a3.ia.memory.player.playerstate.Turned0;
import fr.ensma.a3.ia.memory.player.playerstate.Turned1;
import fr.ensma.a3.ia.memory.player.playerstate.Waiting;
import fr.ensma.a3.ia.memory.table.Tile;
import fr.ensma.a3.ia.memory.table.card.Card;

public abstract class AbstractPlayer {

	protected String id;
	
	protected IPlayerState currentState;
	
	final private Waiting stateWaiting = new Waiting(this);
	final private Turned0 stateTurned0 = new Turned0(this);
	final private Turned1 stateTurned1 = new Turned1(this);
	
	private List<Card> cards;

	private List<Item> inventory;
	
	private Tile turned_tile;

	protected Game game;
	
	/**
	 * Creates a new {@link AbstractPlayer} with a given ID
	 * @param id
	 */
	AbstractPlayer(String id) {
		this.id = id;
		currentState = stateWaiting;
		cards = new ArrayList<Card>();
		inventory = new ArrayList<Item>();

	}
	
	/**
	 * @return the game the player is participating in, or null if the player isn't participating in a game.
	 */
	public Game getGame() {
		return game;
	}
	
	/**
	 * Sets the instance of {@link Game} for the player to participate in
	 * @param The game for the player to join
	 */
	public void setGame(Game game) {
		this.game = game;
	}
	
	/**
	 * @return the id of the player
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Called when the player turns a {@link Tile}
	 * @param tile The tile to be flipped by the player
	 */
	public void tileFlipped(Tile tile) {
		currentState.tileFlipped(tile);
	}

	/**
	 * Returns the AbstractPlayer's final instance of the {@link Waiting} {@link IPlayerState}
	 */
	public Waiting getStateWaiting() {
		return stateWaiting;
	}

	/**
	 * Returns the AbstractPlayer's final instance of the {@link Turned0} {@link IPlayerState}
	 */
	public Turned0 getStateTurned0() {
		return stateTurned0;
	}

	/**
	 * Returns the AbstractPlayer's final instance of the {@link Turned1} {@link IPlayerState}
	 */
	public Turned1 getStateTurned1() {
		return stateTurned1;
	}

	/**
	 * Sets the AbstractPlayer's current state.
	 * @param state The state to be set, preferably fecthing the {@link IPlayerState} instance from this player by using {@link #getStateWaiting()}, {@link #getStateTurned0()} or {@link #getStateTurned1()}
	 */
	public void setState(IPlayerState state) {
		currentState = state;
	}
	
	/**
	 * Returns the current instance of {@link IPlayerState} of the player
	 * @return the current instance of {@link IPlayerState} of the player
	 */
	public IPlayerState getState() {
		return currentState;
	}

	/**
	 * Stores a {@link Card}
	 * @param tile
	 */
	public void setTurnedTile(Tile tile) {
		turned_tile = tile;
	}
	
	/**
	 * Returns the tile that has already been turned by the player.
	 * @return The tile that has already been turned by the player.
	 */
	public Tile getTurnedTile() {
		return turned_tile;
	}
	
	/**
	 * Adds a card to the list of cards won by the player. Please note that you do not need to add the same card twice, as a "pair" of cards is stored as an unique instance of a Card.
	 * @param card The Card to be added
	 */
	public void addMatchingPair(Card card) {
		cards.add(card);
	}
	
	/**
	 * Returns the player's inventory as a List of {@link Item}
	 * @return A list of {@link Item}
	 */
	public List<Item> getInventory(){
		return inventory;
	}
	
	/**
	 * Returns the {@link Card} the player has won as a List of {@link Card}
	 * @return A list of {@link Card}
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * Manually sets the List of {@link Card} won by the player
	 * @param cards The new List of {@link Card} won by the player
	 */
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	/**
	 * Removes all the {@link Card}s won by the player and returns them as a list. Each card is only present once (not as pairs)
	 * @return
	 */
	public List<Card>  popCards() {
		List<Card> lst = cards;
		cards = new ArrayList<Card>();
		return lst;
	}
	
	private static Scanner sc = new Scanner(System.in);

	
	/**
	 * Lets the user pick a {@link Tile} for this player
	 * @return The coordinate of the {@link Tile} picked by the user
	 */
	public int[] pickTile() {
		int[] arr = {0, 0};
		System.out.println("Player" + id + "\n x=");
		arr[0] = sc.nextInt();
		System.out.println(" y=");
		arr[1] = sc.nextInt();
		return arr;
	}
	
}
