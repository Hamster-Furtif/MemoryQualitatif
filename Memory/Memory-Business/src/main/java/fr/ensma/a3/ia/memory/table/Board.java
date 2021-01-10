package fr.ensma.a3.ia.memory.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.event.Event;
import fr.ensma.a3.ia.memory.event.IEventManager;
import fr.ensma.a3.ia.memory.event.IEventObserver;
import fr.ensma.a3.ia.memory.event.board.TileActivatedEvent;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.card.Card;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;

public class Board implements IEventManager{

	private Map<Class<?>, List<IEventObserver>> observersMap;
	
	private List<Tile> tiles;
	
	private int xDim, yDim;
	
	/**
	 * @deprecated Please use {@link #Board(int, int, int)} instead
	 */
	public Board(int xDim, int yDim, List<Tile> tiles) {
		this.xDim = xDim;
		this.yDim = yDim;
		this.tiles = tiles;
	}
	
	public Board(Game game, int xDim, int yDim, int nbCards) {
		this.xDim = xDim;
		this.yDim = yDim;
		this.tiles = new ArrayList<Tile>();
		int nSpecialCards = Board.getSpecialCardNumber(nbCards);
		tiles.addAll(Tile.generatePairsFromCards(Card.generate(nbCards-nSpecialCards)));
		tiles.addAll(Tile.generateFromCards(SpecialCard.getRandomCards(nSpecialCards)));
	}
	
	public void shuffleTiles() {
		Collections.shuffle(tiles);
	}
	
	public Tile getTile(int x, int y) {
		if( 0  <= x && x < xDim && 0 <= y && y < yDim)
			return tiles.get(x+y*xDim);
		return null;
	}

	public int getXDim() {
		return xDim;
	}
	
	public int getYDim() {
		return xDim;
	}

	public List<Tile> getTiles() {
		return tiles;
	}
	
	public Tile getRandomTile() {
		Random rd = new Random();
		return tiles.get(rd.nextInt(tiles.size()));
	}
	
	public Card popCard(Tile tile) {
		Card c = tile.getCard();
		tiles.remove(tile);
		
		return c;
	}
	
	private static int getSpecialCardNumber(int nbCards) {
		if(nbCards < 10)
			return 2;
		else if(nbCards < 20)
			return 4;
		else
			return 6;
	}
	
	public void activateTile(AbstractPlayer player, int x, int y) {
		triggerEvent(new TileActivatedEvent(player, getTile(x, y)));
	}
	
	@Override
	public void triggerEvent(Event event) {
		if(observersMap.get(event.getClass()) != null)
			for(IEventObserver handler : observersMap.get(event.getClass()))
				handler.handle(event);
	}


	@Override
	public void subscribe(Class<? extends Event> type, IEventObserver handler) {
		if(!observersMap.containsKey(type))
			observersMap.put(type, new ArrayList<IEventObserver>());
		observersMap.get(type).add((IEventObserver) handler);		
	}

	@Override
	public void unsubscribe(Class<? extends Event> type, IEventObserver handler) {
		if(observersMap.containsKey(type))
			observersMap.remove(type).add((IEventObserver) handler);	
	}
	
}
