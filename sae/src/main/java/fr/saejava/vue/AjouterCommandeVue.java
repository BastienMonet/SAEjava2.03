package fr.saejava.vue;

import fr.saejava.modele.Livre;
import fr.saejava.controlleur.ControlleurAjouteCommande;
import fr.saejava.modele.Commande;
import fr.saejava.modele.CommandeUnit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
        
        Text dateCom = new Text("date de la commande : " + commande.getDateCom());
        Text enligne = new Text("en ligne : " + commande.enligne());
        Text livraison = new Text("livraison : " + commande.getLivraison());
        Text magasin = new Text("magasin : " + commande.getMagasin().getNomMag());
        Text destinataire = new Text("destinataire : " + commande.getClient().getNomUtil());
        Text prixTotal = new Text("prix total : " + commande.prixTotCommande());

        VBox vb = new VBox(dateCom, enligne, livraison, magasin, destinataire, prixTotal);


        cblivre = new ComboBox();

        for (Livre livre : app.getClient().onVousRecommandeDansMagasin(commande.getMagasin())) {
            cblivre.getItems().add(livre.getTitre());
        }

        cblivre.setValue("Choisissez un livre");

        Text textQte = new Text("Quantité : ");

        qte = new TextField();

        Button valider = new Button("ajouter");


        HBox hbAjoute = new HBox(textQte, qte, cblivre, valider);


        Button retour = new Button("retour");

        retour.setOnAction(new ControlleurAjouteCommande(app, this));
        valider.setOnAction(new ControlleurAjouteCommande(app, this));

        Button finaliser = new Button("finaliser la commande");

        HBox menu = new HBox(retour, finaliser);

        VboxCommande = new VBox();
        VBoxLivresDansMagasin = new VBox();

        ScrollPane scrollPaneCommande = new ScrollPane(VboxCommande);

        ScrollPane scrollPanelivreDansMagasin = new ScrollPane(VBoxLivresDansMagasin);

        VBox vbcenter = new VBox(new Text("les livres dans le magasin"), scrollPanelivreDansMagasin, new Text("la commande acctuel") , scrollPaneCommande, hbAjoute, menu);


        root.setLeft(vb);
        root.setCenter(vbcenter);

        try {
            majLivreDansMagasin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        
        this.scene = new Scene(root, 800, 400);
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
