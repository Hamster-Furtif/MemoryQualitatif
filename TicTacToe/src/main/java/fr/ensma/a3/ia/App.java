package fr.ensma.a3.ia;

import fr.ensma.a3.ia.board.BoardVue;
import fr.ensma.a3.ia.tile.TileVue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
    	
        var vue = new BoardVue();
        var scene = new Scene(new StackPane(vue), 640, 480);
        
       
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}