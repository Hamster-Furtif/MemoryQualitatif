package fr.ensma.a3.ia.mymultichat.server.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import fr.ensma.a3.ia.mymultichat.api.messages.client.ClientMessage;
import fr.ensma.a3.ia.mymultichat.api.messages.client.ClientMessageDecoder;
import fr.ensma.a3.ia.mymultichat.api.messages.client.ClientMessageEncoder;


@ServerEndpoint(value = "/ws/multichat/{canalandpseudo}", encoders = ClientMessageEncoder.class, decoders = ClientMessageDecoder.class)
public class MultiChatServerEndPoint {

	static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	static Map<Integer, Game> gamesMap = new HashMap<Integer, Game>();
	
	@OnOpen
	public void onOpen(@PathParam("canalandpseudo") String canalandpseudo, Session sess, EndpointConfig endpointConfig) {
		System.out.println(sess.getId() + " vient de se connecter au canal " + canalandpseudo);
		String[] params = canalandpseudo.split(":");
		String channel = params[0];
		String pseudo = params[1];
		sess.getUserProperties().put("canal", channel);
		sess.getUserProperties().put("pseudo", pseudo);
		clients.add(sess);
		int cid = Integer.valueOf(channel);
		if(!gamesMap.containsKey(cid)) {
			Game game = new Game(sess);
			gamesMap.put(cid, game);
			System.out.println("Pour le canal " + cid + ", le nombre secret est " + game.getNombre());
			ClientMessage mess = new ClientMessage();
			mess.setLePseudo("LeServer");
			mess.setLeContenu("Bonjour " + pseudo + ", devine un nombre entre 1 et 100");
			mess.setCanalId(cid);
			try {
				sess.getBasicRemote().sendObject(mess);
			} catch (IOException | EncodeException e) {
				e.printStackTrace();
			}
		}
	}

	//Réaction du serveur à la réception du message
	@OnMessage
	public void onMessage(ClientMessage mess, Session sess) throws IOException, EncodeException {
		
		int cid = mess.getCanalId();
		Game game = gamesMap.get(cid);
		
		if(game.isPlayersTurn(sess)) {
			
			List<Session> inCanal = new ArrayList<Session>();
			
			for(Session player : clients) {
				  if(player.getUserProperties().get("canal").equals(String.valueOf(cid)))
					  inCanal.add(player);
			 }
			
			
			ClientMessage messServ = new ClientMessage();
			messServ.setLePseudo("LeServer");
			messServ.setCanalId(mess.getCanalId());
			
			int guess = Integer.valueOf(mess.getLeContenu());
			
			boolean win = false;
			
			if(guess != game.getNombre()) {
				if(guess > game.getNombre())
					messServ.setLeContenu("Trop grand !");
				else
					messServ.setLeContenu("Trop petit !");
			}
			else {
				messServ.setLeContenu("Ah oui oui oui oui oui ! " + mess.getLePseudo() + " a gagné !");
				win = true;
				CloseChannelThread myThread = new CloseChannelThread(inCanal, cid);
				myThread.start();
			}
			
			
			for(Session player : inCanal) {
				
				// Retransmet le message envoyé par le joueur à tous les autres joueurs.
				if(!player.getId().equals(sess.getId()))
					player.getBasicRemote().sendObject(mess);

				// Envoie les message annonçant si le resultat est trop grand, trop petit, où juste.
				player.getBasicRemote().sendObject(messServ);

			}
			if(!win) {
				Session nextPlayer = getNextPlayer(cid, sess);
				game.setPlayer(nextPlayer);
				messServ.setLeContenu("A toi de jouer !");
				nextPlayer.getBasicRemote().sendObject(messServ);
			}
			else {
				//TODO Fermer le canal au bout de 5s
			}
			
		}
		
	}
	
	@OnClose
	public void onClose(Session sess) {
		System.out.println(sess.getUserProperties().get("pseudo") + " vient de se déconnecter ...");
		clients.remove(sess);
		ClientMessage mess = new ClientMessage();
		for (Session client : clients) {
			if(sess.getUserProperties().get("canal").equals(client.getUserProperties().get("canal"))) {
				mess.setLePseudo("LeServer");
				mess.setLeContenu((String) sess.getUserProperties().get("pseudo") + " nous a quitté ... (sniff)");
				try {
					client.getBasicRemote().sendObject(mess);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EncodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	  @OnError
	  public void onError(Session session, Throwable t) {
	    t.printStackTrace();
	  }
	  
	  private static class Game {
		  private int nombre;
		  private Session joueur;
		  
		  private static final int MAX = 100;
		  
		  public Game(Session session) {
			  this.joueur = session;
			  Random rd = new Random();
			  nombre = rd.nextInt(MAX)+1;
		  }
		  
		  public boolean isPlayersTurn(Session s) {
			  return joueur.getId().equals(s.getId());
		  }
		  
		  public void setPlayer(Session s) {
			  joueur = s;
		  }
		  
		  public int getNombre() {
			  return nombre;
		  }

	  }
	  
	  private static Session getNextPlayer(int channel, Session currentPlayer) {
		  
		  List<Session> inCanal = new ArrayList<Session>();
		  
		  for(Session player : clients) {
			  if(player.getUserProperties().get("canal").equals(String.valueOf(channel)))
				  inCanal.add(player);
		  }
		  
		  int curIndex = inCanal.indexOf(currentPlayer);
		  
		  if(curIndex == inCanal.size()-1)
			  return inCanal.get(0);
		  else
			  return inCanal.get(curIndex+1);
	  }
	  
	  public static void kickPlayer(Session player) {
			try {
				player.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Partie gagnée, canal fermé."));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clients.remove(player);

	  }
	  
	  
}
