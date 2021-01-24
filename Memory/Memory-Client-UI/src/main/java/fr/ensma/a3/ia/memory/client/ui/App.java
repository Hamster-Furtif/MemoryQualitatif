package fr.ensma.a3.ia.memory.client.ui;

import fr.ensma.a3.ia.memory.table.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

	Board b = new Board(null, 0);
	
    @Override
    public void start(Stage stage) {


        var label = new Label("Hello, JavaFX "  + ", running on Java "  + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}