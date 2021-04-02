package fr.ensma.a3.ia.memory.client.ui.components.game.tile;

import fr.ensma.a3.ia.memory.client.ui.components.game.GamePresentation;
import fr.ensma.a3.ia.memory.event.table.FlippedTileEvent;
import fr.ensma.a3.ia.memory.event.table.IFlippedTileEventHandler;
import fr.ensma.a3.ia.memory.table.Tile;

public class TilePresentation implements IFlippedTileEventHandler{
	
	private ITilePresentation vue;
	private TileModele modele;
	private GamePresentation game;
	
	public TilePresentation(GamePresentation game, Tile tile) {
		this.game = game;
		modele = new TileModele(tile);
	}
	
	public void setVue(ITilePresentation vue) {
		this.vue = vue;
	}


	public void onClick() {
		game.onClick(modele.getTile());
	}

	@Override
	public void handle(FlippedTileEvent event) {
		if(! modele.getTile().isEmpty()) {
			modele.setFlipped(!modele.isFlipped());
			vue.setFlipped(modele.isFlipped());
		}
	}
	
	public void setEmpty() {
		vue.setEmpty();
	}

	public Tile getTile() {
		return modele.getTile();
	}

}
