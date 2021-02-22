package fr.ensma.a3.ia.memory.client.ui.components.game;

import fr.ensma.a3.ia.memory.client.ui.components.game.playercard.PlayerCardVue;
import fr.ensma.a3.ia.memory.client.ui.components.game.tile.TilePresentation;
import fr.ensma.a3.ia.memory.client.ui.components.game.tile.TileVue;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameVue extends HBox implements IGamePresentation {
	
	private GridPane tiles;
	private VBox playerList;
	
	public GameVue() {
		getChildren().add(tiles = new GridPane());
		getChildren().add(playerList = new VBox());
		
		//TODO dynamic game name
		playerList.getChildren().add(new Label("Game 161"));
	}
	
	@Override
	public void setImage(Image img, int x, int y) {
		TilePresentation tp = new TilePresentation();
		TileVue tv = new TileVue(tp, img);
		tp.setVue(tv);
		tiles.add(tv , x, y);
	}
	
	public void addPlayerCard(PlayerCardVue pc) {
		playerList.getChildren().add(pc);
	}

}
