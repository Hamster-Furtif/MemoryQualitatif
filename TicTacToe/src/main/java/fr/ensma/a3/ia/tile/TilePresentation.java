package fr.ensma.a3.ia.tile;

import fr.ensma.a3.ia.Mode;
import fr.ensma.a3.ia.board.IClickHandler;

public class TilePresentation implements IClicker{

	private ITilePresentation vue;
	private TileModele modele;
	private IClickHandler papa;
	
	public TilePresentation(ITilePresentation vue, int x, int y) {
		this.vue = vue;
		modele = new TileModele();
		modele.setCoord(x, y);
	}
	
	public void onClick() {	
		if(modele.getMode() == Mode.EMPTY) {
			papa.handle(this);
			modele.setMode(modele.getCurrentPlayer());
			vue.setMode(modele.getCurrentPlayer());
		}
	}

	@Override
	public void setHandler(IClickHandler handler) {
		papa = handler;
	}
	
	public void setCurrentPlaye(Mode mode) {
		modele.setCurrentPlayer(mode);
	}
}
