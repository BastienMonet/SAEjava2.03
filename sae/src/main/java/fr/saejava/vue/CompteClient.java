package fr.saejava.vue;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CompteClient {

    private Scene scene;

    public CompteClient(App app){

        BorderPane root = new BorderPane();

        HBox hb = new HBox();

        VBox lesCommandes = new VBox();
        Text bienvenue = new Text("Bienvenue chère Client !"); 
        Button ajouteCommande = new Button("ajouter une commande");
        VBox vbgauche = new VBox(bienvenue, lesCommandes, ajouteCommande);

        Text onVousRecomande = new Text("on Vous Recommandes");
        VBox lesLivres = new VBox();
        VBox vbdroite = new VBox(onVousRecomande, lesLivres);

        Button deco = new Button("deconnexion");

        root.setLeft(vbgauche);
        root.setRight(vbdroite);
        root.setBottom(deco);

        Scene scene = new Scene(root);

        this.scene = scene;
    }
    

    public Scene getScene(){
        return scene;
    }

}
