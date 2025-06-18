package fr.saejava.vue;

import fr.saejava.controlleur.ControlleurCompteClient;
import fr.saejava.controlleur.ControlleurCompteVendeur;
import fr.saejava.modele.Administrateur;
import fr.saejava.modele.Commande;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VendeurVue {
    Scene sceneVendeurBox;

    private VBox commandeBox;
    private App app;
    
    public VendeurVue(App app) {
        Text messageBVN = new Text("Bienvenue dans l'interface vendeur");


        VBox commandeBox = new VBox();


        ScrollPane scroll = new ScrollPane(commandeBox);

        this.commandeBox = commandeBox;
        this.app = app;

        Button deconnexion = new Button("Déconnexion");

        deconnexion.setOnAction(event -> {
            app.setSceneConnexionUtil();
        });
        deconnexion.setStyle("-fx-font-size: 10px; -fx-padding: 5px; -fx-background-color: #38b6ff; -fx-text-fill: black;");

        VBox vueVendeurBox = new VBox(40, messageBVN, scroll, deconnexion);
        vueVendeurBox.setStyle("-fx-padding: 20; -fx-alignment: center;");
        sceneVendeurBox = new Scene(vueVendeurBox, 1000, 1000);
        try {
            majLesCommandes();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Scene getSceneVendeur() {
        return sceneVendeurBox;
    }

     public void majLesCommandes() throws Exception {
        commandeBox.getChildren().clear();
        for (Commande commande : app.getVendeur().voirCommande()) {
            String res = commande.toString();
            Button btndetail = new Button("détails");
            btndetail.setId(String.valueOf(commande.getNumCom()));
            btndetail.setOnAction(event -> {
                app.setFenetreVoirCommande(commande);
            });

            Button btnsupprimer = new Button("X");
            btnsupprimer.setId(String.valueOf(commande.getNumCom()));
            btnsupprimer.setOnAction(new ControlleurCompteVendeur(app, this));
            btnsupprimer.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            HBox hb = new HBox(new Text(res), btndetail, btnsupprimer);
            commandeBox.getChildren().add(hb);
        }
    }


}