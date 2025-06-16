package fr.saejava.vue;

import fr.saejava.modele.ConnexionMySQL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
   

    public App(ConnexionMySQL co){

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

    public void showConnexion(){
        ConnexionVue connexionVue = new ConnexionVue(this);
        this.stage.setScene(connexionVue.getSceneConnexion());
    }

    public static void main(String[] args) {
        App.launch(args);
    }
    
}
