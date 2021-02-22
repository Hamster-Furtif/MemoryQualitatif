package fr.ensma.a3.ia.memory.client.ui.components.game.playercard;

public class PlayerCardPresentation {

	private IPlayerCardPresentation vue;
	private PlayerCardModele modele;
	
	public PlayerCardPresentation(IPlayerCardPresentation vue) {
		this.vue = vue;
		modele = new PlayerCardModele();
	}
	
	public void setPlayer(String player) {
		modele.setPlayerName(player);
		vue.setPlayer(modele.getPlayerName());
	}
	
	public void setScore(String score) {
		modele.setScore(score);
		vue.setScore(modele.getScore());
	}
	
	public void setItems(String items) {
		modele.setItems(items);
		vue.setItems(modele.getItems());
	}
	
	public void init(String player) {
		setPlayer(player);
		setScore("0");
		setItems("-");
	}
	
}
