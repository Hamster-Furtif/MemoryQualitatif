package fr.ensma.a3.ia.memory.client.ui.components.game;

import java.util.List;

import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.client.ui.components.game.playercard.PlayerCardPresentation;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Tile;

public class GameModele {
	
	private List<Tile> tiles;
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

}
