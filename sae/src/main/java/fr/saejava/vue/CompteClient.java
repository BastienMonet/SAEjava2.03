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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CompteClient {

    private Scene scene;

    private VBox lesCommandes;

    private GridPane lesLivres;

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

        Text commandText = new Text("Vos commandes en cours...");
        commandText.setStyle("-fx-font-size: 20;");

        ScrollPane scrollPanecommande = new ScrollPane(lesCommandes);
        scrollPanecommande.setFitToWidth(true);

        
        Text bienvenue = new Text("Bienvenue cher(e) " + c.getNomUtil() + " !");
        bienvenue.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        HBox bienvenueBox = new HBox(bienvenue);
        bienvenueBox.setPadding(new Insets(10, 0, 20, 0));
        bienvenueBox.setAlignment(Pos.CENTER);
        
       
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

        HBox ajouteCommande = new HBox(rechercheMag, choixmag, enligne, enligneChoix, livraison, livraisonChoix);

        Button btnajoute = new Button("ajouter une commande");
        btnajoute.setStyle("-fx-font-size: 16px; -fx-background-color: #38b6ff;");

        btnajoute.setOnAction(new ControlleurCompteClient(app, this));
        
        VBox vbcenter = new VBox(20, commandText, scrollPanecommande, ajouteCommande, btnajoute);
        BorderPane.setMargin(vbcenter, new Insets(0, 20, 0, 0));


        Text onVousRecomande = new Text("On vous recommande");
        onVousRecomande.setStyle("-fx-font-size: 20;");
        
        lesLivres = new GridPane();

        ScrollPane scrollPanelivre = new ScrollPane(lesLivres);
        scrollPanelivre.setFitToWidth(true);

        majRecomandation();
        majLesCommandes();

        VBox vbright = new VBox(20, onVousRecomande, scrollPanelivre);

        Button deco = new Button("deconnexion");
        deco.setStyle("-fx-font-size: 16px; -fx-background-color: #38b6ff;");

        deco.setOnAction(e -> {
            app.setSceneConnexionUtil();
        });

        root.setTop(bienvenueBox);
        root.setCenter(vbcenter);
        root.setRight(vbright);
        root.setBottom(deco);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 1200, 1000);

        this.scene = scene;
    }


    /**
     * Met à jour la liste des commandes du client
     * @throws Exception si une erreur survient lors de la récupération des commandes
     */
    public void majLesCommandes() throws Exception {
        lesCommandes.getChildren().clear();
        GridPane grid = new GridPane();
        int i = 0;
        for (Commande commande : c.voirSesCommande()) {
            Text res = new Text(commande.toString());
            res.setStyle("-fx-font-size: 15;");
            Button btndetail = new Button("détails");
            btndetail.setStyle("-fx-font-size: 16px; -fx-background-color: #38b6ff;");
            btndetail.setId(String.valueOf(commande.getNumCom()));
            btndetail.setOnAction(event -> {
                app.setFenetreVoirCommande(commande);
            });

            HBox space = new HBox();
            space.setMinWidth(20);
            Button btnsupprimer = new Button("X");
            Button btnmodifie = new Button("modifier");
            btnmodifie.setStyle("-fx-font-size: 16px; -fx-background-color: #38b6ff;");
            btnmodifie.setId(String.valueOf(commande.getNumCom()));
            btnmodifie.setOnAction(event -> {
                try {
                    app.setSceneModifieCommande(commande);
                } catch (Exception e){
                    System.err.println(e.getMessage());
                }
                
            });
            btnmodifie.setId(String.valueOf(commande.getNumCom()));
            btnsupprimer.setId(String.valueOf(commande.getNumCom()));
            btnsupprimer.setOnAction(new ControlleurCompteClient(app, this));
            btnsupprimer.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            grid.add(res, 0, i);
            grid.add(space, 1, i);
            grid.add(btndetail, 2, i);
            grid.add(btnmodifie, 3, i);
            grid.add(btnsupprimer, 4, i);
            i++;
        }
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        lesCommandes.getChildren().add(grid);
    }

    /**
     * Met à jour la liste des livres recommandés pour le client
     * @throws Exception si une erreur survient lors de la récupération des livres
     */
    public void majRecomandation() throws Exception {
        lesLivres.getChildren().clear();

        GridPane grid = new GridPane();
        int i = 0;
        for (Livre livre : c.onVousRecommande()) {
            Text res = new Text(livre.toString());
            res.setStyle("-fx-font-size: 15;");

            Button btndetail = new Button("détails");
            btndetail.setOnAction(event -> {
                app.setFenetreLivreVue(livre);
            });
            btndetail.setStyle("-fx-font-size: 16px; -fx-background-color: #38b6ff;");
            Button btnacheter = new Button("acheter");
            btnacheter.setStyle("-fx-font-size: 16px; -fx-background-color: #38b6ff;");
            

            btnacheter.setId(livre.getTitre());

            btnacheter.setOnAction(new ControlleurAcheterLivre(app, c));

            grid.add(res, 0, i);
            grid.add(btndetail, 1, i);
            grid.add(btnacheter, 2, i);
            i++;
        }
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        lesLivres.getChildren().add(grid);
    }

    /**
     * Retourne la scène du compte client
     * @return la scène du compte client
     */
    public Scene getScene(){
        return scene;
    }

    /**
     * Retourne le client associé à cette vue
     * @return le client
     */
    public String getChoixmag() {
        return choixmag.getValue();
    }

    /**
     * Retourne le choix d'achat en ligne
     * @return le choix d'achat en ligne
     */
    public String getEnligneChoix() {
        return enligneChoix.getValue();
    }

    /**
     * Retourne le choix de livraison
     * @return le choix de livraison
     */
    public String getLivraisonChoix() {
        return livraisonChoix.getValue();
    }


}
