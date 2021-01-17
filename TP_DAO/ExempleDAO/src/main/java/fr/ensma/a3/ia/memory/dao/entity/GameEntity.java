package fr.ensma.a3.ia.memory.dao.entity;

public class GameEntity {


	private int nCards;
	private int nPlayers;
	private int playerIDs[], playerScores[];

	public int getnCards() {
		return nCards;
	}

	public void setnCards(int nCards) {
		this.nCards = nCards;
	}

	public int getnPlayers() {
		return nPlayers;
	}

	public void setnPlayers(int nPlayers) {
		this.nPlayers = nPlayers;
	}

	public int[] getPlayerIDs() {
		return playerIDs;
	}

	public void setPlayerIDs(int playerIDs[]) {
		this.playerIDs = playerIDs;
	}

	public int[] getPlayerScores() {
		return playerScores;
	}

	public void setPlayerScores(int[] playerScores) {
		this.playerScores = playerScores;
	}

	@Override
	public String toString() {
		String s = "GameEntity - nPlayers=" + nPlayers + " - player/score=[";
		
		for(int i = 0; i < nPlayers; i++)
			s += playerIDs[i] + playerScores[i] + (i < nPlayers-1 ? "," : "");
		
		return s + "]";
	}
	
	
	
}
