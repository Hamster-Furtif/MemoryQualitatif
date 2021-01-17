package fr.ensma.a3.ia.board;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.Mode;
import fr.ensma.a3.ia.tile.IClicker;
import fr.ensma.a3.ia.tile.TilePresentation;

public class BoardPresentation implements IClickHandler{

	private IBoardPresentation vue;
	private BoardModele model;
	
	
	private List<TilePresentation> tiles;
	
	public BoardPresentation(IBoardPresentation vue) {
		this.vue = vue;
		model = new BoardModele();
		tiles = new ArrayList<TilePresentation>(9);
		
		for(int i = 0; i < 9; i++)
			tiles.add(null);
	}
	
	public TilePresentation getTile(int x, int y) {
		return tiles.get(y*3+x);
	}
	
	public void setTile(TilePresentation tile, int x, int y) {
		tiles.set(y*3+x, tile);
		tile.setHandler(this);
		tile.setCurrentPlaye(model.getPlayer());
	}
	

	private boolean gameIsWon() {
		return false;
	}

	public Mode getCurrentPlayer() {
		return model.getPlayer();
	}

	@Override
	public void handle(IClicker clicker) {
		if(tiles.contains(clicker)) {
			TilePresentation clickedOnTile = (TilePresentation)clicker;
			
			if(gameIsWon())
				System.out.println("GGWP");
			else {
				model.setPlayer(model.getPlayer() == Mode.CIRCLE ? Mode.CROSS : Mode.CIRCLE);
				clickedOnTile.setCurrentPlaye(model.getPlayer());
			}
		}
	}
	
	
	
}
