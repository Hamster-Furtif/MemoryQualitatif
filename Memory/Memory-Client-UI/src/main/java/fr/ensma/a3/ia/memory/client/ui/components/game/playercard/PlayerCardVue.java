package fr.ensma.a3.ia.memory.client.ui.components.game.playercard;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PlayerCardVue extends GridPane implements IPlayerCardPresentation{
	
	private final static String PLAYER = "PLAYER";
	private final static String SCORE = "SCORE";
	private final static String ITEMS = "ITEMS";
	
	private Label playerLabel;
	private Label scoreLabel;
	private Label itemsLabel;
	
	public PlayerCardVue() {
		Label playerRowLabel = new Label(PLAYER);
		Label scoreRowLabel = new Label(SCORE);
		Label itemsRowLabel = new Label(ITEMS);
		
		playerLabel = new Label();
		scoreLabel = new Label();
		itemsLabel = new Label();
		
		add(playerRowLabel, 0, 0);
		add(scoreRowLabel, 0, 1);
		add(itemsRowLabel, 0, 2);
		
		add(playerLabel, 1, 0);
		add(scoreLabel, 1, 1);
		add(itemsLabel, 1, 2);
		
		setGridLinesVisible(true);
	}
	
	@Override
	public void setPlayer(String playerName) {
		playerLabel.setText(playerName);
	}
	
	@Override
	public void setScore(String score) {
		scoreLabel.setText(score);
	}
	
	@Override
	public void setItems(String items) {
		itemsLabel.setText(items);
	}

}