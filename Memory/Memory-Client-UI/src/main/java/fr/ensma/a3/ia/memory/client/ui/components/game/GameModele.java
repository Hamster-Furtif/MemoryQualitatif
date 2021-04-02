package fr.ensma.a3.ia.memory.client.ui.components.game;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.client.ui.components.game.playercard.PlayerCardPresentation;
import fr.ensma.a3.ia.memory.client.ui.components.game.tile.TilePresentation;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Tile;

public class GameModele {
	
	private List<Tile> tiles;
	private List<TilePresentation> presentations;
	private List<PlayerCardPresentation> cardPresentations;
	private Game game;
	private int xDim, yDim;
	private AbstractPlayer self;
	
	public GameModele(Game game, AbstractPlayer self) {
		this.game  = game;
		this.self = self;
		this.tiles = game.getBoard().getTiles();
		this.xDim  = game.getBoard().getXDim();
		this.yDim  = game.getBoard().getYDim();
		
		cardPresentations = new ArrayList<PlayerCardPresentation>(game.getPlayers().size());
		presentations = new ArrayList<TilePresentation>(xDim*yDim);
		for(int i = 0; i < xDim*yDim; i++) {
			presentations.add(null);
		}
	}
	
	public Tile getTile(int x, int y) {
		return tiles.get(xDim*y+x);
	}
	
	public int getxDim() {
		return xDim;
	}

	public int getyDim() {
		return yDim;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public AbstractPlayer getSelf() {
		return self;
	}

	public List<PlayerCardPresentation> getCardPresentations() {
		return cardPresentations;
	}
	
	public void addPresentation(TilePresentation p, int x, int y) {
		presentations.add(x+y*xDim, p);
	}

	public List<TilePresentation> getPresentations() {
		return presentations;
	}

	
	public TilePresentation getPresentationFromTile(Tile tile) {
		for(TilePresentation tp : presentations)
			if(tp != null && tp.getTile() == tile)
				return tp;
		
		return null;
	}
}
