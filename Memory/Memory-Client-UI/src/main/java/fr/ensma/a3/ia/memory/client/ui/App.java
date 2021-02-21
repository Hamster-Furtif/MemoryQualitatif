package fr.ensma.a3.ia.memory.client.ui;

import java.util.ArrayList;

import fr.ensma.a3.ia.memory.Game;
import fr.ensma.a3.ia.memory.client.ui.components.game.GameVue;
import fr.ensma.a3.ia.memory.client.ui.components.game.tile.TilePresentation;
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
		
		ArrayList<AbstractPlayer> players = new ArrayList<AbstractPlayer>();
		players.add(p1);
		players.add(p2);
				
		Game game = new Game(20, players);
    	
    	    	
    	
    	GameVue vue = new GameVue(game.getBoard().getXDim(), game.getBoard().getYDim());
    	vue.setTiles(game.getBoard().getTiles());
    	
        var scene = new Scene(new StackPane(vue), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}