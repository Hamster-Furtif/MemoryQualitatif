package fr.ensma.a3.ia.memory.client.ui;

import java.util.ArrayList;

import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.client.ui.components.game.GamePresentation;
import fr.ensma.a3.ia.memory.client.ui.components.game.GameVue;
import fr.ensma.a3.ia.memory.client.ui.components.game.playercard.PlayerCardPresentation;
import fr.ensma.a3.ia.memory.client.ui.components.game.playercard.PlayerCardVue;
import fr.ensma.a3.ia.memory.player.AbstractPlayer;
import fr.ensma.a3.ia.memory.player.HumanPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
	
    @Override
    public void start(Stage stage) {

        JFXResourceLoader.loadResources();
    	JFXResourceLoader.init();

    	AbstractPlayer p1 = new HumanPlayer("Magnus");
		AbstractPlayer p2 = new HumanPlayer("Hikaru");
		
		AbstractPlayer self = p1;
		
		ArrayList<AbstractPlayer> players = new ArrayList<AbstractPlayer>();
		players.add(p1);
		players.add(p2);
				
		Game game = new Game(20, players);
    	game.init();

    	
    	GameVue vue = new GameVue();
    	GamePresentation presentation = new GamePresentation(self);
    	presentation.setVue(vue);
    	
    	for(AbstractPlayer player : players)
    		addPlayerToGame(player, vue, presentation);
    	
        var scene = new Scene(new StackPane(vue), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public void addPlayerToGame(AbstractPlayer player, GameVue vue, GamePresentation presentation) {
    	PlayerCardVue pVue= new PlayerCardVue();
    	PlayerCardPresentation pPresentation = new PlayerCardPresentation(pVue);
    	
    	pPresentation.init(player.getID());
    	vue.addPlayerCard(pVue);

    }

}