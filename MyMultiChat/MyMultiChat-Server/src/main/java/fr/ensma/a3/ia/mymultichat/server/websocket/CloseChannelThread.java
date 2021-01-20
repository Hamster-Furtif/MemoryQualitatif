package fr.ensma.a3.ia.mymultichat.server.websocket;

import java.io.IOException;
import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import fr.ensma.a3.ia.mymultichat.api.messages.client.ClientMessage;
import fr.ensma.a3.ia.mymultichat.server.business.ChatCanalAdmin;

public class CloseChannelThread extends Thread{

	
	private List<Session> inCanal;
	private int cid;
	
	public CloseChannelThread(final List<Session> inCanal, int cid) {
		this.inCanal = inCanal;
		this.cid = cid;
	}

	@Override
	public void run() {
		try {
			for(int i = 0; i < 5; i++) {
				
				ClientMessage messClose = new ClientMessage();
				messClose.setCanalId(cid);
				messClose.setLePseudo("LeServer");
				
				for(Session player : inCanal) {
					
					messClose.setLeContenu("Le canal fermera dans " + (5-i) + " secondes...");
					
					try {
						player.getBasicRemote().sendObject(messClose);
					} catch (IOException | EncodeException e) {
						e.printStackTrace();
					}
					
				}
				
				sleep(1000);
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(Session player : inCanal)
				MultiChatServerEndPoint.kickPlayer(player);


		ChatCanalAdmin.removeCanalFromID(cid);
		
	}
	
}
