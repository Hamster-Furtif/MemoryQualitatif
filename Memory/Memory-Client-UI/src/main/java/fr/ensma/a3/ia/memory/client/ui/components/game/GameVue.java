package fr.ensma.a3.ia.memory.client.ui.components.game;

import java.util.List;

import fr.ensma.a3.ia.memory.client.ui.JFXResourceLoader;
import fr.ensma.a3.ia.memory.client.ui.components.game.tile.TilePresentation;
import fr.ensma.a3.ia.memory.client.ui.components.game.tile.TileVue;
import fr.ensma.a3.ia.memory.table.Tile;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class GameVue extends HBox {
	
	private int xDim, yDim;
	private GridPane tiles;
	private VBox playerList;
	
	public GameVue(int xDim, int yDim) {
		
		this.setMaxHeight(Double.MAX_VALUE);
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

		this.xDim = xDim;
		this.yDim = yDim;
		getChildren().add(tiles = new GridPane());
		//getChildren().add(playerList = new VBox());

		tiles.setMaxHeight(Double.MAX_VALUE);
		tiles.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		tiles.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

	}
	
	public void setTiles(List<Tile> tileList) {
		for(int i = 0; i < tileList.size(); i++) {
			
			Tile tile = tileList.get(i);
			if(!tile.isEmpty()) {
				int x = i%yDim;
				int y = (i-x)/yDim;
				
				TilePresentation tp = new TilePresentation();
				
				Image img;
				
				if(tile.getCard() instanceof SpecialCard)
					img = JFXResourceLoader.getSpecialTileImage(((SpecialCard)tile.getCard()).getName());
				else
					img = JFXResourceLoader.getBasicTileImage(tile.getCard().getId());
				
				TileVue tv = new TileVue(tp, img);
				tp.setVue(tv);
				tiles.add(tv , x, y);
			}
		}
	}

}
