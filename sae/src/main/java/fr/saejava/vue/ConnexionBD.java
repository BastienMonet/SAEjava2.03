package fr.saejava.vue;

import fr.saejava.controlleur.ControlleurConnexionBD;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConnexionBD {
    

    private Scene scene;

    TextField nomBD, login, motDePasse;

    public ConnexionBD(App app){

        Text nom = new Text("Nom BD:");
        Text log = new Text("Login:");
        Text mdp = new Text("Mot de passe:");
        nomBD = new TextField();
        login = new TextField();
        motDePasse = new TextField();

        GridPane infos = new GridPane();
        infos.add(nom, 0, 0);
        infos.add(nomBD, 1, 0);
        infos.add(log, 0, 1);
        infos.add(login, 1, 1);
        infos.add(mdp, 0, 2);
        infos.add(motDePasse, 1, 2);
        infos.setHgap(10);
        infos.setVgap(10);

        BorderPane root = new BorderPane();

        Text bienvenue = new Text("Bienvenue dans la base de données !");


        Button btnConnexion = new Button("Se connecter");

        btnConnexion.setOnAction(new ControlleurConnexionBD(app, this));
        Button btnQuitter = new Button("Quitter");

        VBox vbox = new VBox(bienvenue, infos, btnConnexion, btnQuitter);

        root.setCenter(vbox);

        Scene scene = new Scene(root, 400, 300);

        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }


    public String getNomBD(){
        return nomBD.getText();
    }

     public String getLogin(){
        return login.getText();
    }

     public String getMotDePasse(){
        return motDePasse.getText();
    }

}
