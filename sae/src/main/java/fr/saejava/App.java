package fr.saejava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("SAE java");
        primaryStage.show();

    }

    public static void main(String[] args) {
        App.launch(args);
    }
    
}
