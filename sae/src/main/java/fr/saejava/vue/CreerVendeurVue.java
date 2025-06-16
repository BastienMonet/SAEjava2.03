package fr.saejava.vue;

import fr.saejava.modele.Administrateur;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CreerVendeurVue {
    Scene sceneCreationVendeur;
    
    public CreerVendeurVue(App app) {
        Text messageInfo = new Text("Créez un compte vendeur");

        Text texteNomVendeur = new Text("Nom du vendeur");
        texteNomVendeur.setStyle("-fx-font-size: 16px; -fx-padding: 1px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        TextField nomVendeur = new TextField("Entrez le nom du vendeur");
        nomVendeur.setStyle("-fx-font-size: 16px; -fx-padding: 0px; -fx-background-color: #ffffff; -fx-text-fill: black;");

        VBox nomVendeurBox = new VBox(10, texteNomVendeur, nomVendeur);

        Text textePrenomVendeur = new Text("Prénom du vendeur");
        textePrenomVendeur.setStyle("-fx-font-size: 16px; -fx-padding: 1px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        TextField prenomVendeur = new TextField("Entrez le prénom du vendeur");
        prenomVendeur.setStyle("-fx-font-size: 16px; -fx-padding: 0px; -fx-background-color: #ffffff; -fx-text-fill: black;");

        VBox prenomVendeurBox = new VBox(10, textePrenomVendeur, prenomVendeur);

        Text texteMDPVendeur = new Text("Mot de passe du vendeur");
        texteMDPVendeur.setStyle("-fx-font-size: 16px; -fx-padding: 1px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        TextField mdpVendeur = new TextField("Entrez le mot de passe du vendeur");
        mdpVendeur.setStyle("-fx-font-size: 16px; -fx-padding: 0px; -fx-background-color: #ffffff; -fx-text-fill: black;");

        VBox mdpVendeurBox = new VBox(10, texteMDPVendeur, mdpVendeur);

        Button deconnexion = new Button("Annuler");
        deconnexion.setStyle("-fx-font-size: 10px; -fx-padding: 5px; -fx-background-color: #38b6ff; -fx-text-fill: black;");

        VBox vueCreationVendeur = new VBox(40, messageInfo, nomVendeurBox, prenomVendeurBox, mdpVendeurBox, deconnexion);
        vueCreationVendeur.setStyle("-fx-padding: 20; -fx-alignment: center;");
        sceneCreationVendeur = new Scene(vueCreationVendeur, 500, 500);
    }

    public Scene getSceneCreationVendeur() {
        return sceneCreationVendeur;
    }
}