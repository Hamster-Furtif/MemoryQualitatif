package fr.ensma.a3.ia.memory.client.ui.components.game;

import fr.ensma.a3.ia.memory.client.ui.components.game.tile.TilePresentation;
import javafx.scene.image.Image;

public interface IGamePresentation {

	public void setImage(Image img, int x, int y, TilePresentation tp);
	
	
}
