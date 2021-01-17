package fr.ensma.a3.ia.tile;

import fr.ensma.a3.ia.Mode;

public class TileModele {

	private Mode mode = Mode.EMPTY;
	private Mode currentPlayer;
	
	private int x, y;
	
	TilePresentation presentation;

	public Mode getMode() {
		return mode;
	}
	
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public Mode getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Mode mode) {
		currentPlayer = mode;
	}
	
}
