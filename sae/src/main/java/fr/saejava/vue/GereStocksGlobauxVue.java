package fr.saejava.vue;

import fr.saejava.controlleur.ControlleurGererStock;
import fr.saejava.modele.Administrateur;
import fr.saejava.modele.Livre;
import fr.saejava.modele.Magasin;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GereStocksGlobauxVue {
    private Scene sceneGereBox;
    private Administrateur administrateur;
    private ComboBox<String> nomMagasin;
    private ComboBox<String> nomLivre;
    private TextField qte;
    private Text texteQTElivreDansMagasin;
    
    public GereStocksGlobauxVue(App app) throws Exception {

        administrateur = app.getAdministrateur();

        Text messageInfo = new Text("Gérer les stocks globaux");

        Text texteNomMagasin = new Text("Nom du magasin");
        BorderPane texteNomMagasinPane = new BorderPane(texteNomMagasin);
        BorderPane.setAlignment(texteNomMagasinPane, javafx.geometry.Pos.CENTER);
        texteNomMagasinPane.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        nomMagasin = new ComboBox<>();

        for (Magasin mag : administrateur.voirToutLesMagasin()) {
            nomMagasin.getItems().add(mag.getNomMag());
        }

        VBox nomMagasinBox = new VBox(0, texteNomMagasinPane, nomMagasin);

        Text texteLivre = new Text("Livre");
        BorderPane texteLivrePane = new BorderPane(texteLivre);
        BorderPane.setAlignment(texteLivrePane, javafx.geometry.Pos.CENTER);
        texteLivrePane.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        nomLivre = new ComboBox<>();

        for (Livre livre : administrateur.voirToutLesLivres()) {
            nomLivre.getItems().add(livre.getTitre());
        }

        nomMagasin.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateTexteQteLivreDansMagasin();
           
        });

        nomLivre.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateTexteQteLivreDansMagasin();
        });

        VBox livreBox = new VBox(0, texteLivrePane, nomLivre);

        Text texteQTE = new Text("Quantité");
        BorderPane texteQTEPane = new BorderPane(texteLivre);
        BorderPane.setAlignment(texteQTEPane, javafx.geometry.Pos.CENTER);
        texteQTEPane.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        texteQTElivreDansMagasin = new Text("il y a ? fois le livre dans ce magasin");

        qte = new TextField();

        VBox qteBox = new VBox(0, texteQTE, texteQTEPane, qte);

        Button ajouter = new Button("Ajouter");
        ajouter.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        Button retirer = new Button("Retirer");
        retirer.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #5ce1e6; -fx-text-fill: black;");

        Button retour = new Button("Annuler");
        retour.setStyle("-fx-font-size: 10px; -fx-padding: 5px; -fx-background-color: #38b6ff; -fx-text-fill: black;");


        ajouter.setOnAction(new ControlleurGererStock(app, this));
        retirer.setOnAction(new ControlleurGererStock(app, this));
        retour.setOnAction(event -> {
            app.setSceneAdmin();
        });

        HBox selection = new HBox(ajouter, retirer, retour);

        VBox vueGereBox = new VBox(40, messageInfo, nomMagasinBox, livreBox, texteQTElivreDansMagasin ,qteBox, selection);
        vueGereBox.setStyle("-fx-padding: 20; -fx-alignment: center;");
        sceneGereBox = new Scene(vueGereBox, 500, 500);
    }

    public Scene getSceneStocksGlobaux() {
        return sceneGereBox;
    }

    public String getNomMagasin() {
        return nomMagasin.getValue();
    }
    public String getNomLivre() {
        return nomLivre.getValue();
    }
    public String getQte() {
        return qte.getText();
    }

    public void updateTexteQteLivreDansMagasin() {
        String nomMag = getNomMagasin();
        String nomLiv = getNomLivre();
        try {
            Magasin mag = administrateur.getMagasinBDparNom(nomMag);
            Livre livre = administrateur.getLivreBDparTitre(nomLiv);

            if (nomMag == null || nomLiv == null) {
                texteQTElivreDansMagasin.setText("il y a : fois le livre dans ce magasin");
            } else {
                int qteLivre = administrateur.qteDansMagasin(livre, mag);
                texteQTElivreDansMagasin.setText("il y a : " + qteLivre + " fois le livre dans ce magasin");
            }
            
        } catch (Exception e) {
            texteQTElivreDansMagasin.setText("il y a ? fois le livre dans ce magasin");
            e.printStackTrace();
        }
        
    }
}