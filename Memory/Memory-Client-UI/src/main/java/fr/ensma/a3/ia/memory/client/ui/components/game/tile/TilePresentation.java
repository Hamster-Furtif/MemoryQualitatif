package fr.ensma.a3.ia.memory.client.ui.components.game.tile;

public class TilePresentation {
	
	private ITilePresentation vue;
	private TileModele modele;
	
	public TilePresentation() {
		modele = new TileModele();
	}
	
	public void setVue(ITilePresentation vue) {
		this.vue = vue;
	}


	public void onClick() {
		modele.setFlipped(!modele.isFlipped());
		vue.setFlipped(modele.isFlipped());
	}

}
