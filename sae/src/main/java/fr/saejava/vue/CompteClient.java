package fr.saejava.vue;

import java.sql.SQLException;
import java.util.List;

import fr.saejava.modele.Client;
import fr.saejava.modele.Commande;
import fr.saejava.modele.Livre;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CompteClient {

    private Scene scene;

    private VBox lesCommandes;

    private VBox lesLivres;

    public CompteClient(App app, Client c) throws Exception {

        BorderPane root = new BorderPane();

        lesCommandes = new VBox();

        ScrollPane scrollPanecommande = new ScrollPane(lesCommandes);
        scrollPanecommande.setFitToWidth(true);

        majLesCommandes(c);
        Text bienvenue = new Text("Bienvenue chère " + c.getNomUtil() + " !"); 
        Button ajouteCommande = new Button("ajouter une commande");
        VBox vbgauche = new VBox(bienvenue, scrollPanecommande, ajouteCommande);

        Text onVousRecomande = new Text("on Vous Recommandes");
        
        lesLivres = new VBox();

        ScrollPane scrollPanelivre = new ScrollPane(lesLivres);
        scrollPanelivre.setFitToWidth(true);

        majRecomandation(c);

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


    public void majLesCommandes(Client c) throws Exception {
        lesCommandes.getChildren().clear();
        for (Commande commande : c.voirSesCommande()) {
            String res = commande.toString();
            Button btndetail = new Button("détails");
            Button btnsupprimer = new Button("X");
            btnsupprimer.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            HBox hb = new HBox(new Text(res), btndetail, btnsupprimer);
            lesCommandes.getChildren().add(hb);
        }
    }

    public void majRecomandation(Client c) throws Exception {
        lesLivres.getChildren().clear();
        for (Livre Livre : c.onVousRecommande()) {
            String res = Livre.toString();
            Button btndetail = new Button("détails");
            Button btnacheter = new Button("acheter");
            HBox hb = new HBox(new Text(res), btndetail, btnacheter);
            lesLivres.getChildren().add(hb);
        }
    }


    public Scene getScene(){
        return scene;
    }

}
