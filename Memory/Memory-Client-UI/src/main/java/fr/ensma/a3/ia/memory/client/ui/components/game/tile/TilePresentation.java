package fr.ensma.a3.ia.memory.client.ui.components.game.tile;

public class TilePresentation {
	
	private ITilePresentation vue;
	private TileModele modele;
	
	public void setVue(ITilePresentation vue) {
		this.vue = vue;
		modele = new TileModele();
	}


	public void onClick() {
		modele.setFlipped(!modele.isFlipped());
		vue.setFlipped(modele.isFlipped());
	}

}
