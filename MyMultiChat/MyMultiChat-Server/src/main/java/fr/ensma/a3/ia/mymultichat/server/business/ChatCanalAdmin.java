package fr.ensma.a3.ia.mymultichat.server.business;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.mymultichat.api.canal.ChatCanalDesc;

public class ChatCanalAdmin {

	
	private static ChatCanalDesc privcanal = new ChatCanalDesc(1, "Canal de chat privé ... ");
	private static ChatCanalDesc profcanal = new ChatCanalDesc(2, "Canal de chat pro ...");
	private static ChatCanalDesc amicanal  = new ChatCanalDesc(3, "Canal de chat amis ...");
		
	
	private static List<ChatCanalDesc> listCans = new ArrayList<ChatCanalDesc>();
	
	static {
		listCans.add(privcanal);
		listCans.add(profcanal);
		listCans.add(amicanal);
	}
	
	public static synchronized List<ChatCanalDesc> getAllCanal() {
		return listCans;
	}
	
	public static synchronized void removeCanalFromID(int cid) {
		for(ChatCanalDesc canal : listCans) {
			if(canal.getCanalId() == cid)
				listCans.remove(canal);
		}
	}
}