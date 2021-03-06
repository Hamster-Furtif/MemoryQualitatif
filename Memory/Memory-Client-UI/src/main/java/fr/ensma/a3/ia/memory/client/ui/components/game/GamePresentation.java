package fr.ensma.a3.ia.memory.client.ui.components.game;

import fr.ensma.a3.ia.memory.client.ui.JFXResourceLoader;
import fr.ensma.a3.ia.memory.client.ui.components.game.tile.TilePresentation;
import fr.ensma.a3.ia.memory.event.table.FlippedTileEvent;
import fr.ensma.a3.ia.memory.event.table.IRemovedTileEventHandler;
import fr.ensma.a3.ia.memory.event.table.IReveleToutEventHandler;
import fr.ensma.a3.ia.memory.event.table.RemovedTileEvent;
import fr.ensma.a3.ia.memory.event.table.ReveleToutEvent;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Tile;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;
import javafx.scene.image.Image;

public class GamePresentation implements IReveleToutEventHandler, IRemovedTileEventHandler {
	
	private IGamePresentation vue;
	private GameModele modele;
	
	public GamePresentation(AbstractPlayer player) {
		modele = new GameModele(player.getGame(), player);
		modele.getGame().subscribe(this);
		modele.getGame().getBoard().subscribe(this);
	}
	
	public void setVue(IGamePresentation vue) {
		this.vue = vue;
		pushImagesToVue();
	}
	
	public void pushImageToVue(int x, int y) {
		Tile tile = modele.getTile(x, y);
		Image img;
		
		if(!tile.isEmpty()) {
			
			if(tile.getCard() instanceof SpecialCard)
				img = JFXResourceLoader.getSpecialTileImage(((SpecialCard)tile.getCard()).getName());
			else
				img = JFXResourceLoader.getBasicTileImage(tile.getCard().getId());
		
			TilePresentation tp = new TilePresentation(this, tile);
			tile.subscribe(tp);
			modele.addPresentation(tp, x, y);
			vue.setImage(img, x, y, tp);
		}
	}
	
	public void pushImagesToVue() {
		for(int x = 0; x < modele.getxDim(); x++)
			for(int y = 0; y < modele.getyDim(); y++)
				pushImageToVue(x, y);
	}
	
	public void onClick(Tile tile) {
		modele.getGame().play(modele.getSelf(), tile);
	}

	@Override
	public void handle(ReveleToutEvent event) {

	}

	@Override
	public void handle(RemovedTileEvent event) {
		modele.getPresentationFromTile(event.getTile()).setEmpty();
	}
	
}
