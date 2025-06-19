package fr.saejava.vue;

import fr.saejava.controlleur.ControlleurCompteClient;
import fr.saejava.controlleur.ControlleurCompteVendeur;
import fr.saejava.modele.Administrateur;
import fr.saejava.modele.Commande;
import fr.saejava.modele.Magasin;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    private ComboBox<String> choixUtil;
    private ComboBox<String> choixmag;
    private ComboBox<String> enligneChoix;
    private ComboBox<String> livraisonChoix;

    
    
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


        TextField rechercheUtil = new TextField();
        rechercheUtil.setPromptText("Rechercher un utilisateur");

        choixUtil = new ComboBox<>();

        choixmag = new ComboBox<>();

        try {
            for (Magasin m : app.getAdministrateur().voirToutLesMagasin()) {
            choixmag.getItems().add(m.getNomMag());
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des magasins : " + e.getMessage());
        }
        

        try {
            for (String nomprenom : app.getVendeur().voirToutLesNomClients()) {
                choixUtil.getItems().add(nomprenom);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
        }
        
        Text choixUtilText = new Text("Choisir un utilisateur :");

        Text choixMagText = new Text("Choisir un magasin :");

        Text enligne = new Text("en ligne ?");

        enligneChoix = new ComboBox<>();
        enligneChoix.getItems().addAll("Oui", "Non");
        enligneChoix.setValue("Oui");

        Text livraison = new Text("livraison ?");

        livraisonChoix = new ComboBox<>();
        livraisonChoix.getItems().addAll("en magasin", "a domicile");
        livraisonChoix.setValue("en magasin");



        HBox ajouteCommande = new HBox(choixUtilText, rechercheUtil, choixUtil, choixMagText , choixmag, enligne, enligneChoix, livraison, livraisonChoix);

        Button btnajoute = new Button("Ajouter une commande");

        btnajoute.setOnAction(new ControlleurCompteVendeur(app, this));

        VBox vueVendeurBox = new VBox(40, messageBVN, scroll, ajouteCommande, btnajoute ,deconnexion);
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

    public String getChoixUtil() {
        return choixUtil.getValue();
    }
    public String getChoixMag() {
        return choixmag.getValue();
    }
    public String getEnligneChoix() {
        return enligneChoix.getValue();
    }
    public String getLivraisonChoix() {
        return livraisonChoix.getValue();
    }


}