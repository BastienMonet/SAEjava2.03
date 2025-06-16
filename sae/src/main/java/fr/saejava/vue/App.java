package fr.saejava.vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    private Stage primaryStage;

    public void showConnexion(){
        ConnexionVue connexionVue = new ConnexionVue(this);
        this.primaryStage.setScene(connexionVue.getSceneConnexion());
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        showConnexion();
        primaryStage.setTitle("SAE java");
        primaryStage.show();

    }

    public static void main(String[] args) {
        App.launch(args);
    }
    
}
