package fr.saejava.vue;

import fr.saejava.modele.Administrateur;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VendeurVue {
    Scene sceneVendeurBox;
    
    public VendeurVue(App app) {
        Text messageBVN = new Text("Bienvenue dans l'interface vendeur");

        Text texteCommande = new Text("commande blabla");
        Button details = new Button("voir le détail");
        details.setStyle("-fx-font-size: 10px; -fx-padding: 5px; -fx-background-color: #a6a6a6; -fx-text-fill: black;");
        Button supprimer = new Button("X");
        supprimer.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-text-fill: black;");
        HBox commandeBox = new HBox(texteCommande, details, supprimer);
        BorderPane.setAlignment(commandeBox, javafx.geometry.Pos.CENTER);
        commandeBox.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        Button deconnexion = new Button("Déconnexion");

        deconnexion.setOnAction(event -> {
            app.setSceneConnexionUtil();
        });
        deconnexion.setStyle("-fx-font-size: 10px; -fx-padding: 5px; -fx-background-color: #38b6ff; -fx-text-fill: black;");

        VBox vueVendeurBox = new VBox(40, messageBVN, commandeBox, deconnexion);
        vueVendeurBox.setStyle("-fx-padding: 20; -fx-alignment: center;");
        sceneVendeurBox = new Scene(vueVendeurBox, 500, 500);
    }

    public Scene getSceneVendeur() {
        return sceneVendeurBox;
    }
}