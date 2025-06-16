package fr.saejava.vue;

import fr.saejava.controlleur.ControlleurConnexionBD;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConnexionBD {
    

    private Scene scene;

    TextField nomBD, login, motDePasse;

    public ConnexionBD(App app){

        nomBD = new TextField();
        login = new TextField();
        motDePasse = new TextField();

        BorderPane root = new BorderPane();

        Text bienvenue = new Text("Bienvenue dans la base de données !");


        Button btnConnexion = new Button("Se connecter");

        btnConnexion.setOnAction(new ControlleurConnexionBD(app, this));
        Button btnQuitter = new Button("Quitter");

        VBox vbox = new VBox(bienvenue, nomBD, login, motDePasse, btnConnexion, btnQuitter);

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
