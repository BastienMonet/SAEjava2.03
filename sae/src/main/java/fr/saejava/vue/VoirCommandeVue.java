package fr.saejava.vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VoirCommandeVue {
    Scene sceneVoirCommandeVue;

    public VoirCommandeVue(App app) {
        Text titre = new Text("[la commande]");
        titre.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        HBox titreCont = new HBox(titre);
        titreCont.setAlignment(Pos.CENTER);

        Text date = new Text("[date]");
        date.setStyle("-fx-font-size: 20;");
        Text enLigne = new Text("[en ligne ?]");
        enLigne.setStyle("-fx-font-size: 20;");
        Text livraison = new Text("[livraison]");
        livraison.setStyle("-fx-font-size: 20;");
        Text mag = new Text("[le magasin]");
        mag.setStyle("-fx-font-size: 20;");
        Text destinataire = new Text("[le destinataire]");
        destinataire.setStyle("-fx-font-size: 20;");
        Text prixTotal = new Text("[le prix total]");
        prixTotal.setStyle("-fx-font-size: 20;");

        VBox infosCom = new VBox(date, enLigne, livraison, mag, destinataire, prixTotal);
        infosCom.setAlignment(Pos.CENTER);
        infosCom.setSpacing(10);

        // remplacer par une boucle pour afficher les livres
        HBox L1 = this.afficheLivre("Livre 1", 1, 50);
        HBox L2 = this.afficheLivre("Livre 2", 50, 1);
        HBox L3 = this.afficheLivre("Livre 3", 8, 70);
        VBox livres = new VBox(L1, L2, L3);
        livres.setAlignment(Pos.CENTER);
        livres.setSpacing(10);
        BorderPane.setMargin(livres, new Insets(0, 0, 0, 50));

        Button retour = new Button("retour");
        retour.setMinWidth(200);
        HBox retourCont = new HBox(retour);
        retourCont.setAlignment(Pos.CENTER_RIGHT);
        retourCont.setPadding(new Insets(50));

        BorderPane fin = new BorderPane();
        fin.setTop(titreCont);
        fin.setLeft(infosCom);
        fin.setCenter(livres);
        fin.setBottom(retourCont);

        this.sceneVoirCommandeVue = new Scene(fin, 650, 500);
    }

    private HBox afficheLivre(String titre, int qte, double prix){
        Text livre = new Text(titre);
        livre.setStyle("-fx-font-size: 20;");
        Button detail = new Button("voir le detail");
        HBox livreG = new HBox(livre, detail);
        livreG.setSpacing(20);

        HBox space = new HBox();
        space.setMinWidth(100);

        Text quantite = new Text("[qte]");
        quantite.setStyle("-fx-font-size: 20;");
        Text prixLivre = new Text("[prix]");
        prixLivre.setStyle("-fx-font-size: 20;");

        HBox livreQtePrix = new HBox(quantite, prixLivre);
        livreQtePrix.setSpacing(20);

        HBox fin = new HBox(livreG, space, livreQtePrix);
        fin.setAlignment(Pos.CENTER);

        return fin;
    }

    public Scene getSceneVoirCommandeVue() {
        return sceneVoirCommandeVue;
    }
}
