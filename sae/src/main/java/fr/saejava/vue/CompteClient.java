package fr.saejava.vue;

import java.sql.SQLException;
import java.util.List;

import javax.sound.sampled.Control;

import fr.saejava.controlleur.ControlleurAcheterLivre;
import fr.saejava.controlleur.ControlleurCompteClient;
import fr.saejava.modele.Client;
import fr.saejava.modele.Commande;
import fr.saejava.modele.Livre;
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

public class CompteClient {

    private Scene scene;

    private VBox lesCommandes;

    private VBox lesLivres;

    private ComboBox<String> choixmag;
    private ComboBox<String> enligneChoix;
    private ComboBox<String> livraisonChoix;

    private Client c;

    private App app;


    public CompteClient(App app, Client c) throws Exception {

        this.c = app.getClient();
        this.app = app;

        BorderPane root = new BorderPane();

        lesCommandes = new VBox();

        ScrollPane scrollPanecommande = new ScrollPane(lesCommandes);
        scrollPanecommande.setFitToWidth(true);

        
        Text bienvenue = new Text("Bienvenue chère " + c.getNomUtil() + " !");
        
       
        choixmag = new ComboBox<>();

        for (Magasin m : c.voirToutLesMagasin()) {
            choixmag.getItems().add(m.getNomMag());
        }
        choixmag.setValue("Choisissez un magasin");

        Text enligne = new Text("En ligne?");

        enligneChoix = new ComboBox<>();
        enligneChoix.getItems().addAll("oui", "non");
        enligneChoix.setValue("oui");

        Text livraison = new Text("Livraison?");

        livraisonChoix = new ComboBox<>();
        livraisonChoix.getItems().addAll("en magasin", "a domicile");
        livraisonChoix.setValue("en magasin");
       
       
        HBox ajouteCommande = new HBox(choixmag, enligne, enligneChoix, livraison, livraisonChoix);

        TextField rechercheMag = new TextField();
        rechercheMag.setPromptText("Rechercher un magasin");

        rechercheMag.textProperty().addListener((observable, oldValue, newValue) -> {
            choixmag.getItems().clear();

            if (! newValue.equals("")){

                
                try {
                    for (Magasin mag : c.voirToutLesMagasin(newValue))
                    choixmag.getItems().add(mag.getNomMag());

                } catch (Exception e){
                    System.err.println(e.getMessage());
                }
            } else {
                
                try {
                    for (Magasin mag : c.voirToutLesMagasin())
                    choixmag.getItems().add(mag.getNomMag());

                } catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
            
            
        });

        Button btnajoute = new Button("ajouter une commande");

        btnajoute.setOnAction(new ControlleurCompteClient(app, this));
        
        VBox vbgauche = new VBox(20, bienvenue, scrollPanecommande, ajouteCommande, rechercheMag, btnajoute);

        Text onVousRecomande = new Text("On Vous Recommande");
        
        lesLivres = new VBox();

        ScrollPane scrollPanelivre = new ScrollPane(lesLivres);
        scrollPanelivre.setFitToWidth(true);

        majRecomandation();
        majLesCommandes();

        VBox vbdroite = new VBox(onVousRecomande, scrollPanelivre);

        Button deco = new Button("deconnexion");

        deco.setOnAction(e -> {
            app.setSceneConnexionUtil();
        });

        root.setLeft(vbgauche);
        root.setRight(vbdroite);
        root.setBottom(deco);

        Scene scene = new Scene(root, 750, 600);

        this.scene = scene;
    }


    public void majLesCommandes() throws Exception {
        lesCommandes.getChildren().clear();
        for (Commande commande : c.voirSesCommande()) {
            String res = commande.toString();
            Button btndetail = new Button("détails");
            btndetail.setId(String.valueOf(commande.getNumCom()));
            btndetail.setOnAction(event -> {
                app.setFenetreVoirCommande(commande);
            });

            Button btnsupprimer = new Button("X");
            btnsupprimer.setId(String.valueOf(commande.getNumCom()));
            btnsupprimer.setOnAction(new ControlleurCompteClient(app, this));
            btnsupprimer.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            HBox hb = new HBox(new Text(res), btndetail, btnsupprimer);
            lesCommandes.getChildren().add(hb);
        }
    }

    public void majRecomandation() throws Exception {
        lesLivres.getChildren().clear();
        for (Livre livre : c.onVousRecommande()) {
            String res = livre.toString();
            Button btndetail = new Button("détails");
            btndetail.setOnAction(event -> {
                app.setFenetreLivreVue(livre);
            });
            Button btnacheter = new Button("acheter");
            btnacheter.setId(livre.getTitre());
            btnacheter.setOnAction(new ControlleurAcheterLivre(app, c));
            HBox hb = new HBox(new Text(res), btndetail, btnacheter);
            lesLivres.getChildren().add(hb);
        }
    }


    public Scene getScene(){
        return scene;
    }


    public String getChoixmag() {
        return choixmag.getValue();
    }


    public String getEnligneChoix() {
        return enligneChoix.getValue();
    }


    public String getLivraisonChoix() {
        return livraisonChoix.getValue();
    }


}
