package fr.saejava.vue;

import fr.saejava.modele.Commande;
import fr.saejava.modele.CommandeUnit;
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
import javafx.stage.Stage;

public class VoirCommandeVue {
    private Stage stage;

    private VBox lesDetail;

    private Commande commande;

    public VoirCommandeVue(App app, Commande commande) {

        Stage stage = new Stage();
        this.stage = stage;
        this.commande = commande;


        Text titre = new Text("la commande");
        titre.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        HBox titreCont = new HBox(titre);
        titreCont.setAlignment(Pos.CENTER);

        Text date = new Text(commande.getDateCom());
        date.setStyle("-fx-font-size: 20;");
        String enligne = "";

        if (commande.enligne() == 'O') {
            enligne = "commandé en ligne";
        } else {
            enligne = "commandé sur place";
        }

        String livraison = "";
        if (commande.getLivraison() == 'C') {
            livraison = "livré à " + commande.getClient().getAdresseUtil() + " " + commande.getClient().getVilleUtil();
        } else {
            livraison = "livré au magasin ";
        }
        Text enLigne = new Text(enligne);
        enLigne.setStyle("-fx-font-size: 20;");
        Text Textlivraison = new Text(livraison);
        Textlivraison.setStyle("-fx-font-size: 20;");
        Text mag = new Text(commande.getMagasin().getNomMag());
        mag.setStyle("-fx-font-size: 20;");
        Text destinataire = new Text(commande.getClient().getNomUtil() + " " + commande.getClient().getPrenomUtil());
        destinataire.setStyle("-fx-font-size: 20;");
        Text prixTotal = new Text(commande.prixTotCommande() + " €");
        prixTotal.setStyle("-fx-font-size: 20;");

        VBox infosCom = new VBox(date, enLigne, Textlivraison, mag, destinataire, prixTotal);
        infosCom.setAlignment(Pos.CENTER);
        infosCom.setSpacing(10);

        lesDetail = new VBox();

        this.livreDansCommande();

        // // remplacer par une boucle pour afficher les livres
        // HBox L1 = this.afficheLivre("Livre 1", 1, 50);
        // HBox L2 = this.afficheLivre("Livre 2", 50, 1);
        // HBox L3 = this.afficheLivre("Livre 3", 8, 70);
        // VBox livres = new VBox(L1, L2, L3);
        // livres.setAlignment(Pos.CENTER);
        // livres.setSpacing(10);
        // BorderPane.setMargin(livres, new Insets(0, 0, 0, 50));

        Button retour = new Button("quitter");

        retour.setOnAction(event -> {
            this.closeFenetreVoirCommandeVue();
        });
        retour.setMinWidth(200);
        HBox retourCont = new HBox(retour);
        retourCont.setAlignment(Pos.CENTER_RIGHT);
        retourCont.setPadding(new Insets(50));

        BorderPane fin = new BorderPane();
        fin.setTop(titreCont);
        fin.setLeft(infosCom);
        fin.setRight(lesDetail);
        fin.setBottom(retourCont);

        Scene root = new Scene(fin, 850, 700);

        stage.setScene(root);
        stage.setHeight(1000);
        stage.setWidth(1000);
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



    public void livreDansCommande() {
        lesDetail.getChildren().clear();
        for (CommandeUnit commandeUnit : commande.getListeCommandes()) {
            String res = commandeUnit.toString();
            Button detail = new Button("détails");
            HBox hb = new HBox(new Text(res), detail);
            lesDetail.getChildren().add(hb);

        }
    }


    public void getFenetreVoirCommandeVue() {
        this.stage.show();
    }

    public void closeFenetreVoirCommandeVue() {
        this.stage.close();
    }
}
