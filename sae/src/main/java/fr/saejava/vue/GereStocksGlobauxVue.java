package fr.saejava.vue;

import fr.saejava.modele.Administrateur;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GereStocksGlobauxVue {
    Scene sceneGereBox;
    
    public GereStocksGlobauxVue(App app) {
        Text messageInfo = new Text("Gérer les stocks globaux");

        Text texteNomMagasin = new Text("Nom du magasin");
        BorderPane texteNomMagasinPane = new BorderPane(texteNomMagasin);
        BorderPane.setAlignment(texteNomMagasinPane, javafx.geometry.Pos.CENTER);
        texteNomMagasinPane.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        TextField nomMagasin = new TextField("Entrez le nom du magasin");
        BorderPane nomMagasinPane = new BorderPane(nomMagasin);
        BorderPane.setAlignment(nomMagasinPane, javafx.geometry.Pos.CENTER);
        nomMagasinPane.setStyle("-fx-font-size: 16px; -fx-padding: 0px; -fx-background-color: #ffffff; -fx-text-fill: black;");

        VBox nomMagasinBox = new VBox(0, texteNomMagasinPane, nomMagasinPane);

        Text texteLivre = new Text("Livre");
        BorderPane texteLivrePane = new BorderPane(texteLivre);
        BorderPane.setAlignment(texteLivrePane, javafx.geometry.Pos.CENTER);
        texteLivrePane.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        TextField livre = new TextField("Entrez l'ISBN du livre");
        BorderPane livrePane = new BorderPane(livre);
        BorderPane.setAlignment(livrePane, javafx.geometry.Pos.CENTER);
        livrePane.setStyle("-fx-font-size: 16px; -fx-padding: 0px; -fx-background-color: #ffffff; -fx-text-fill: black;");

        VBox livreBox = new VBox(0, texteLivrePane, livrePane);

        Text texteQTE = new Text("Quantité");
        BorderPane texteQTEPane = new BorderPane(texteLivre);
        BorderPane.setAlignment(texteQTEPane, javafx.geometry.Pos.CENTER);
        texteQTEPane.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        TextField qte = new TextField("Entrez le mot de passe du vendeur");
        BorderPane qtePane = new BorderPane(livre);
        BorderPane.setAlignment(qtePane, javafx.geometry.Pos.CENTER);
        qtePane.setStyle("-fx-font-size: 16px; -fx-padding: 0px; -fx-background-color: #ffffff; -fx-text-fill: black;");

        VBox qteBox = new VBox(0, texteQTEPane, qtePane);

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