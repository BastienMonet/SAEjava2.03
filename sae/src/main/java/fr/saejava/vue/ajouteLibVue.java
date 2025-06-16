package fr.saejava.vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ajouteLibVue {
    Scene sceneAjouteLib;

    public ajouteLibVue(App app) {
        Text titre = new Text("Ajouter une librairie");
        titre.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        Text nom = new Text("Nom du magasin");
        HBox contNom = new HBox(nom);
        contNom.setMaxWidth(300);
        contNom.setStyle("-fx-background-color: #5ce1e6;");
        contNom.setAlignment(Pos.CENTER);
        TextField nomMagasin = new TextField();
        nomMagasin.setMaxWidth(300);
        nomMagasin.setPromptText("Entrez le nom du magasin");
        VBox nomMag = new VBox(contNom, nomMagasin);
        nomMag.setAlignment(Pos.CENTER);

        Text ville = new Text("Ville du magasin");
        HBox contVille = new HBox(ville);
        contVille.setMaxWidth(300);
        contVille.setStyle("-fx-background-color: #5ce1e6;");
        contVille.setAlignment(Pos.CENTER);
        TextField nomCity = new TextField();
        nomCity.setMaxWidth(300);
        nomCity.setPromptText("Entrez la ville du magasin");
        VBox nomVille = new VBox(contVille, nomCity);
        nomVille.setAlignment(Pos.CENTER);

        Button annuler = new Button("Annuler");

        VBox fin = new VBox(titre, nomMag, nomVille, annuler);
        fin.setAlignment(Pos.CENTER);
        fin.setSpacing(20);

        this.sceneAjouteLib = new Scene(fin, 400, 300);
    }

    public Scene getSceneAjouteLib(){
        return this.sceneAjouteLib;
    }
}
