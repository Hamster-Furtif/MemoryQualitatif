package fr.ensma.a3.ia.memory.player.playerstate;

import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.table.Tile;

public interface IPlayerState {
	
	void toTurned0();
	void toTurned1();
	void toWaiting();
	void tileFlipped(Tile tile);
	AbstractPlayer getPlayer();
	
}
