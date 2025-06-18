package fr.saejava.vue;

import fr.saejava.modele.Livre;
import fr.saejava.controlleur.ControlleurAjouteCommande;
import fr.saejava.modele.Commande;
import fr.saejava.modele.CommandeUnit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AjouterCommandeVue {
    
    private Scene scene;

    private App app;

    private Commande commande;


    private TextField qte;

    private ComboBox<String> cblivre;


    private VBox VBoxLivresDansMagasin;
    private VBox VboxCommande;

    public AjouterCommandeVue(App app, Commande commande) throws Exception {

        this.app = app;
        this.commande = commande;

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));

        Text title = new Text("Ajouter une commande");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        HBox titreCont = new HBox(title);
        titreCont.setAlignment(Pos.CENTER);
        titreCont.setPadding(new Insets(10, 0, 10, 0));
        
        Text dateCom = new Text("date de la commande : " + commande.getDateCom());
        dateCom.setStyle("-fx-font-size: 20;");
        Text enligne = new Text("en ligne : " + commande.enligne());
        enligne.setStyle("-fx-font-size: 20;");
        Text livraison = new Text("livraison : " + commande.getLivraison());
        livraison.setStyle("-fx-font-size: 20;");
        Text magasin = new Text("magasin : " + commande.getMagasin().getNomMag());
        magasin.setStyle("-fx-font-size: 20;");
        Text destinataire = new Text("destinataire : " + commande.getClient().getNomUtil());
        destinataire.setStyle("-fx-font-size: 20;");
        Text prixTotal = new Text("prix total : " + commande.prixTotCommande());
        prixTotal.setStyle("-fx-font-size: 20;");

        VBox vb = new VBox(dateCom, enligne, livraison, magasin, destinataire, prixTotal);
        vb.setSpacing(10);


        cblivre = new ComboBox();

        for (Livre livre : app.getClient().onVousRecommandeDansMagasin(commande.getMagasin())) {
            cblivre.getItems().add(livre.getTitre());
        }

        cblivre.setValue("Choisissez un livre");

        Text textQte = new Text("Quantité : ");

        qte = new TextField();

        Button valider = new Button("ajouter");

        TextField rechercheMag = new TextField();
        rechercheMag.setPromptText("Rechercher un magasin");


        GridPane gAjoute = new GridPane();
        gAjoute.add(rechercheMag, 2, 0);
        gAjoute.add(textQte, 0, 1);
        gAjoute.add(qte, 1, 1);
        gAjoute.add(cblivre, 2, 1);
        gAjoute.add(valider, 3, 1);
        gAjoute.setVgap(10);


        Button retour = new Button("retour");

        Button finaliser = new Button("finaliser la commande");

        retour.setOnAction(new ControlleurAjouteCommande(app, this));
        valider.setOnAction(new ControlleurAjouteCommande(app, this));
        finaliser.setOnAction(new ControlleurAjouteCommande(app, this));

        
        HBox menu = new HBox(retour, finaliser);
        menu.setSpacing(10);

        VboxCommande = new VBox();
        VBoxLivresDansMagasin = new VBox();

        ScrollPane scrollPaneCommande = new ScrollPane(VboxCommande);
        scrollPaneCommande.setMaxWidth(477);

        ScrollPane scrollPanelivreDansMagasin = new ScrollPane(VBoxLivresDansMagasin);
        scrollPanelivreDansMagasin.setMaxWidth(477);

        VBox vbcenter = new VBox(new Text("les livres dans le magasin"), scrollPanelivreDansMagasin, new Text("la commande actuelle") , scrollPaneCommande, gAjoute, menu);
        vbcenter.setSpacing(10);

        BorderPane.setMargin(vbcenter, new Insets(0, 0, 0, 50));

        root.setTop(titreCont);
        root.setLeft(vb);
        root.setCenter(vbcenter);

        try {
            majLivreDansMagasin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        
        this.scene = new Scene(root, 870, 400);
    }

    public Scene getScene() {
        return this.scene;
    }


    public void majLivreDansMagasin() throws Exception {
        VBoxLivresDansMagasin.getChildren().clear();
        for (Livre livre : this.app.getClient().onVousRecommandeDansMagasin(commande.getMagasin())) {
            String res = livre.toString();
            Button btndetail = new Button("détails");
            HBox hb = new HBox(new Text(res), new Text(app.getClient().qteDansMagasin(livre, commande.getMagasin()) + "en stock"), btndetail);
            VBoxLivresDansMagasin.getChildren().add(hb);
        }

    }

    public void majLivreDansCommande() {
        VboxCommande.getChildren().clear();
        for (CommandeUnit commandeUnit : commande.getListeCommandes()) {
            String res = commandeUnit.toString();
            VboxCommande.getChildren().add(new Text(res));

        }
    }

    public int getQte() {
        try {
            return Integer.parseInt(qte.getText());
        }
        catch (NumberFormatException e) {
            app.alertChampsVides();
            return 0;
        }
    }

    public String getCblivre() {
        return cblivre.getValue();
    }

    public Commande getCommande() {
        return commande;
    }

}
