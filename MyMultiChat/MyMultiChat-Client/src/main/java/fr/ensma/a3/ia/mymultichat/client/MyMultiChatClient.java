package fr.ensma.a3.ia.mymultichat.client;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Scanner;

import javax.websocket.DeploymentException;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

import com.google.gson.Gson;

import fr.ensma.a3.ia.mymultichat.api.canal.ChatCanalDesc;
import fr.ensma.a3.ia.mymultichat.api.messages.client.ClientMessage;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class MyMultiChatClient {
	
	private static final String SERVER = "ws://localhost:8080/ws/multichat";
	private static final String REST_URI = "http://localhost:8080/services/multichat/";

	private static Gson gson = new Gson();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		//Appel du service pour recupérer un canal depuis un ID
		Client restclient = ClientBuilder.newClient();

		ChatCanalDesc rep = restclient.target(REST_URI).path(String.valueOf(1)).request(MediaType.APPLICATION_JSON).get(ChatCanalDesc.class);
		System.out.println(rep);

		ClientManager client = ClientManager.createClient();
		String blabla;
		
		List<ChatCanalDesc> channelLst = restclient.target(REST_URI).request(MediaType.APPLICATION_JSON).get(new GenericType<List<ChatCanalDesc>>() {});
		System.out.println("Canaux disponibles: ");
		
		for(ChatCanalDesc dsc : channelLst)
			System.out.println("   " + dsc.toString());
		
		int choice;
		
		do {
			
			System.out.println("Sur quel canal veux-tu te connecter ?");
			choice = Integer.valueOf(scan.nextLine());
			
		} while (choice <= 0 || choice > channelLst.size());

		//Connexion au serveur :
		System.out.println("Bienvenu sur MultiChat - Canal " + choice + " !");
		System.out.println("Donne ton pseudo : ");
		String pseudo = scan.nextLine();
		try {
			Session sess = client.connectToServer(MultiChatClientEndPoint.class, URI.create(SERVER+"/" + choice + ":"+pseudo));
			sess.getUserProperties().put("Pseudo", pseudo);
			do {
				blabla = scan.nextLine();
				sess.getBasicRemote().sendText(formatMessage(choice, pseudo, blabla));
			} while(!blabla.equalsIgnoreCase("quit"));
			
		} catch (DeploymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			scan.close();
		}	
	}
	
	private static String formatMessage(int cid, String pseu, String bla) {
		ClientMessage m = new ClientMessage();
        m.setCanalId(cid);
        m.setLePseudo(pseu);
        m.setLeContenu(bla);
        return gson.toJson(m);
    }
	

}
