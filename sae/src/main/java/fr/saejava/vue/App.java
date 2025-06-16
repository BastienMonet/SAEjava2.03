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

    private Stage stage; 

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.stage = primaryStage;

        this.setSceneCreerCompte();

        stage.setTitle("SAE java");
        stage.show();

    }

    public void setSceneCreerCompte(){
        CompteClient vue = new CompteClient(this);
        stage.setScene(vue.getScene());
    }

    public static void main(String[] args) {
        App.launch(args);
    }
    
}
