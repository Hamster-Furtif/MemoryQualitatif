package fr.ensma.a3.ia.memory;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.memory.event.player.EndOfTurnEvent;
import fr.ensma.a3.ia.memory.event.player.IEndOfTurnEventHandler;
import fr.ensma.a3.ia.memory.event.player.IEndOfTurnEventManager;
import fr.ensma.a3.ia.memory.event.table.FlippedTileEvent;
import fr.ensma.a3.ia.memory.event.table.IFlippedTileEventHandler;
import fr.ensma.a3.ia.memory.event.table.IFlippedTileEventManager;
import fr.ensma.a3.ia.memory.event.table.IRemovedTileEventHandler;
import fr.ensma.a3.ia.memory.event.table.IRemovedTileEventManager;
import fr.ensma.a3.ia.memory.event.table.IReveleToutEventHandler;
import fr.ensma.a3.ia.memory.event.table.IReveleToutEventManager;
import fr.ensma.a3.ia.memory.event.table.RemovedTileEvent;
import fr.ensma.a3.ia.memory.event.table.ReveleToutEvent;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.player.BotPlayer;
import fr.ensma.a3.ia.memory.table.Board;
import fr.ensma.a3.ia.memory.table.Tile;

public class Game implements IEndOfTurnEventManager, IReveleToutEventManager {

	private List<IEndOfTurnEventHandler> endOfTurnEventHandlers;
	private List<IReveleToutEventHandler> reveleToutEventHandlers;
	private int nbCards;
	private Board board;
	private List<AbstractPlayer> players;
	private AbstractPlayer currentPlayer;
	
	/**
	 * Creates a new {@link Game}
	 * @param nbCards The number of cards this game is played with
	 * @param players The list of {@link AbstractPlayer}s who will play the game.
	 */
	public Game(int nbCards, List<AbstractPlayer> players) {
		this.nbCards = nbCards;
		this.players = players;
		
		endOfTurnEventHandlers = new ArrayList<IEndOfTurnEventHandler>();
		reveleToutEventHandlers = new ArrayList<IReveleToutEventHandler>();
		
		board = new Board(this, nbCards);
		
		for (AbstractPlayer player : players)
			player.setGame(this);
	}
	
	/**
	 * Returns the board used in this Game instance
	 * @return the {@link Board} used in this Game instance
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Returns the players participating in this game
	 * @return the list of {@link AbstractPlayer} participating in this game
	 */
	public List<AbstractPlayer> getPlayers(){
		return players;
	}
	
	/**
	 * Returns the total number of cards in this game, including special cards.
	 * @return the number of cards in this game
	 */
	public int getNbCards() {
		return nbCards;
	}

	@Override
	public void triggerEvent(EndOfTurnEvent event) {
		for(IEndOfTurnEventHandler handler : endOfTurnEventHandlers)
			handler.handle(event);
	}


	@Override
	public void subscribe(IEndOfTurnEventHandler handler) {
		if(!endOfTurnEventHandlers.contains(handler))
			endOfTurnEventHandlers.add(handler);
	}

	@Override
	public void unsubscribe(IEndOfTurnEventHandler handler) {
		if(endOfTurnEventHandlers.contains(handler))
			endOfTurnEventHandlers.remove(handler);	
	}
	
	@Override
	public void triggerEvent(ReveleToutEvent event) {
		
		for(IReveleToutEventHandler handler : reveleToutEventHandlers)
			handler.handle(event);
	}

	@Override
	public void subscribe(IReveleToutEventHandler handler) {
		if(!reveleToutEventHandlers.contains(handler))
			reveleToutEventHandlers.add(handler);		
	}

	@Override
	public void unsubscribe(IReveleToutEventHandler handler) {
		if(reveleToutEventHandlers.contains(handler))
			reveleToutEventHandlers.remove(handler);			
	}
	

	/**
	 * Makes a player flip a tile at a specific coordinate. Does nothing is the tile is null or empty.
	 * @param player The player who flips the tile
	 * @param tile The tile
	 */
	private void activateTile(AbstractPlayer player, Tile tile) {
		if(tile != null && !tile.isEmpty())
			player.tileFlipped(tile);			
	}
	
	
	/**
	 * Makes the game run.
	 */
	public void run() {
		while(true)
			for(AbstractPlayer player : players) {
				
				player.setState(player.getStateTurned0());
				while(player.getState() !=  player.getStateWaiting()) {
					board.print();
					int[] arr = player.pickTile();
					activateTile(player, board.getTile(arr[1]-1, arr[0]-1));
				}
				
			}
	}
	
	public void init() {
		for(Tile t : board.getTiles())
			t.setFlipped(Tile.FACE_UP);
		
		board.print();
		
		for(Tile t : board.getTiles())
			t.setFlipped(Tile.FACE_DOWN);
		
		currentPlayer = players.get(0);
		currentPlayer.setState(currentPlayer.getStateTurned0());
	}
	
	public void play(AbstractPlayer player, Tile tile) {
		if(player.equals(currentPlayer) && player.getState() != player.getStateWaiting()) {
			activateTile(player, tile);
			if(player.getState() == player.getStateWaiting())
				cyclePlayers();
		}		
	}
	
	private void cyclePlayers() {
		
		int i = players.indexOf(currentPlayer);
		
		if(i < players.size() -1)
			currentPlayer = players.get(i+1);
		else
			currentPlayer = players.get(0);
		
	}

	
}
