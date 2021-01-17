package fr.ensma.a3.ia.board;

import fr.ensma.a3.ia.tile.TileVue;
import javafx.scene.layout.GridPane;

public class BoardVue extends GridPane implements IBoardPresentation{

	private BoardPresentation presentation;
	
	public BoardVue() {
		
		presentation = new BoardPresentation(this);
		
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++) {
				TileVue vue = new TileVue(x, y);
				add(vue, x, y);
				presentation.setTile(vue.getPresentation(), x, y);
			}
		
		System.out.println(getChildren().size());

	}
}
