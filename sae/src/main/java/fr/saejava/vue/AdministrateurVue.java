package fr.saejava.vue;

import fr.saejava.modele.Administrateur;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AdministrateurVue {
    Scene sceneAdmin;
    
    public AdministrateurVue(App app) {
        Text messageBVN = new Text("Bienvenue Administrateur");

        Button creerVendeur = new Button("Créer un compte vendeur");
        creerVendeur.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        Button ajouteLibrairie = new Button("Ajouter une nouvelle librairie");
        ajouteLibrairie.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        Button ajouteLivre = new Button("Ajouter un nouveau livre");
        ajouteLivre.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        Button gereStocks = new Button("Gérer les stocks globaux");
        gereStocks.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        Button voirlesStat = new Button("voir les statistique de la librairie");
        voirlesStat.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");


        Button deconnexion = new Button("Déconnexion");
        deconnexion.setStyle("-fx-font-size: 10px; -fx-padding: 5px; -fx-background-color: #38b6ff; -fx-text-fill: black;");

        creerVendeur.setOnAction(event -> app.setSceneCreerVendeur());
        ajouteLibrairie.setOnAction(event -> app.setAjouterLibrairie());
        deconnexion.setOnAction(event -> app.setSceneConnexionUtil());
        ajouteLivre.setOnAction(event -> app.setSceneAjouteLivre());
        gereStocks.setOnAction(event -> {
            try {
            app.setStocksGlobauxVue();
            } catch (Exception e) {
            e.printStackTrace();
            }
        });

        voirlesStat.setOnAction(event -> app.setStatistiqueVue());


        


        VBox vueAdmin = new VBox(40, messageBVN, creerVendeur, ajouteLibrairie, ajouteLivre , gereStocks, voirlesStat, deconnexion);
        vueAdmin.setStyle("-fx-padding: 20; -fx-alignment: center;");
        sceneAdmin = new Scene(vueAdmin, 1000, 1000);
    }

    public Scene getSceneAdmin() {
        return sceneAdmin;
    }
}
