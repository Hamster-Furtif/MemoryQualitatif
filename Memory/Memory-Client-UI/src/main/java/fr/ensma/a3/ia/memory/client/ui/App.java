package fr.ensma.a3.ia.memory.client.ui;

import fr.ensma.a3.ia.memory.client.ui.components.game.tile.TilePresentation;
import fr.ensma.a3.ia.memory.client.ui.components.game.tile.TileVue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
	
    @Override
    public void start(Stage stage) {


        var label = new Label("Hello, JavaFX "  + ", running on Java "  + ".");
        JFXResourceLoader.loadResources();
    	
    	TileVue.setBackImage(JFXResourceLoader.getBackImage());
    	
    	TilePresentation presentation = new TilePresentation();
    	
    	TileVue vue = new TileVue(presentation, JFXResourceLoader.getBasicTileImage(5));
    	vue.setImageFromCardNumber(5);
    	
    	presentation.setVue(vue);

        var scene = new Scene(new StackPane(vue), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}