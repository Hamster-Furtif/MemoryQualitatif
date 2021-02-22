package fr.ensma.a3.ia.memory.client.ui.components.game;

import java.util.List;

import fr.ensma.a3.ia.memory.client.ui.JFXResourceLoader;
import fr.ensma.a3.ia.memory.table.Tile;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;
import javafx.scene.image.Image;

public class GamePresentation {
	
	private IGamePresentation vue;
	private GameModele modele;
	
	public GamePresentation(List<Tile> tiles, int xDim, int yDim) {
		modele = new GameModele(tiles, xDim, yDim);
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
		
			vue.setImage(img, x, y);
		}
	}
	
	public void pushImagesToVue() {
		for(int x = 0; x < modele.getxDim(); x++)
			for(int y = 0; y < modele.getyDim(); y++)
				pushImageToVue(x, y);
	}
	
}
