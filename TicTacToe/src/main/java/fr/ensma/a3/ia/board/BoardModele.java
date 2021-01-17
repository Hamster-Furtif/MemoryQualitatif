package fr.ensma.a3.ia.board;

import fr.ensma.a3.ia.Mode;

public class BoardModele {

	private Mode player;
	
	public BoardModele() {
		setPlayer(Mode.CIRCLE);
	}

	public Mode getPlayer() {
		return player;
	}

	public void setPlayer(Mode player) {
		this.player = player;
	}
	
}
