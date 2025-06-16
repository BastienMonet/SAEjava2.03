package fr.saejava.vue;

import fr.saejava.modele.ConnexionMySQL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    private Stage stage; 

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        this.showStatistiqueVue();

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

    public void showStatistiqueVue(){
        StatistiqueVue vueStatistique = new StatistiqueVue(this);
        this.stage.setScene(vueStatistique.getSceneStatistique());
    }

    public static void main(String[] args) {
        App.launch(args);
    }
    
}
