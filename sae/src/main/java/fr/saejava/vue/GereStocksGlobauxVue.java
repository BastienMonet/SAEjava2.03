package fr.saejava.vue;

import fr.saejava.modele.Administrateur;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GereStocksGlobauxVue {
    Scene sceneGereBox;
    
    public GereStocksGlobauxVue(App app) {
        Text messageInfo = new Text("Gérer les stocks globaux");

        Text texteNomMagasin = new Text("Nom du magasin");
        texteNomMagasin.setStyle("-fx-font-size: 16px; -fx-padding: 1px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        TextField nomMagasin = new TextField("Entrez le nom du magasin");
        nomMagasin.setStyle("-fx-font-size: 16px; -fx-padding: 0px; -fx-background-color: #ffffff; -fx-text-fill: black;");

        VBox nomMagasinBox = new VBox(10, texteNomMagasin, nomMagasin);

        Text texteLivre = new Text("Livre");
        texteLivre.setStyle("-fx-font-size: 16px; -fx-padding: 1px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        TextField livre = new TextField("Entrez l'ISBN du livre");
        livre.setStyle("-fx-font-size: 16px; -fx-padding: 0px; -fx-background-color: #ffffff; -fx-text-fill: black;");

        VBox livreBox = new VBox(10, texteLivre, livre);

        Text texteQTE = new Text("Quantité");
        texteQTE.setStyle("-fx-font-size: 16px; -fx-padding: 1px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        TextField qte = new TextField("Entrez le mot de passe du vendeur");
        qte.setStyle("-fx-font-size: 16px; -fx-padding: 0px; -fx-background-color: #ffffff; -fx-text-fill: black;");

        VBox qteBox = new VBox(10, texteQTE, qte);

        Button deconnexion = new Button("Annuler");
        deconnexion.setStyle("-fx-font-size: 10px; -fx-padding: 5px; -fx-background-color: #38b6ff; -fx-text-fill: black;");

        VBox vueGereBox = new VBox(40, messageInfo, nomMagasinBox, livreBox, qteBox, deconnexion);
        vueGereBox.setStyle("-fx-padding: 20; -fx-alignment: center;");
        sceneGereBox = new Scene(vueGereBox, 500, 500);
    }

    public Scene getSceneStocksGlobaux() {
        return sceneGereBox;
    }
}