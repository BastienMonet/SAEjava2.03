package fr.saejava.vue;

import fr.saejava.controlleur.ControlleurGererStock;
import fr.saejava.modele.Administrateur;
import fr.saejava.modele.Livre;
import fr.saejava.modele.Magasin;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
        messageInfo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Text texteNomMagasin = new Text("Nom du magasin:");
        texteNomMagasin.setStyle("-fx-font-size: 20px;");
        GridPane.setHalignment(texteNomMagasin, HPos.RIGHT);

        TextField rechercheMag = new TextField();

        rechercheMag.textProperty().addListener((observable, oldValue, newValue) -> {
            nomMagasin.getItems().clear();
            try {

                if (newValue.isEmpty()) {
                    for (Magasin mag : administrateur.voirToutLesMagasin()) {
                        nomMagasin.getItems().add(mag.getNomMag());
                    }
                    return;
                }
                for (Magasin mag : administrateur.voirToutLesMagasin(newValue)) {
                    nomMagasin.getItems().add(mag.getNomMag());
               
            }
            } catch (Exception e) {
                System.err.println("Erreur lors de la récupération des magasins : " + e.getMessage());
            }
            
        });
        rechercheMag.setPromptText("Rechercher un magasin");

        nomMagasin = new ComboBox<>();
        nomMagasin.setMinWidth(163);

        for (Magasin mag : administrateur.voirToutLesMagasin()) {
            nomMagasin.getItems().add(mag.getNomMag());
        }


        Text texteLivre = new Text("Livre:");
        texteLivre.setStyle("-fx-font-size: 20px;");
        GridPane.setHalignment(texteLivre, HPos.RIGHT);


        TextField rechercheLivre = new TextField();
        rechercheLivre.setPromptText("Rechercher un livre");

        rechercheLivre.textProperty().addListener((observable, oldValue, newValue) -> {
            nomLivre.getItems().clear();
            try {

                if (newValue.isEmpty()) {
                    for (Livre livre : administrateur.voirToutLesLivres()) {
                        nomLivre.getItems().add(livre.getTitre());
                    }
                    return;
                }
                for (Livre livre : administrateur.voirToutLesLivres(newValue)) {
                    nomLivre.getItems().add(livre.getTitre());
                }
            } catch (Exception e) {
                System.err.println("Erreur lors de la récupération des livres : " + e.getMessage());
            }
        });

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


        Text textQTE = new Text("Quantité");
        textQTE.setStyle("-fx-font-size: 20px;");
        HBox textQTEBox = new HBox(textQTE);
        qte = new TextField();

        textQTE.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        textQTEBox.setStyle("-fx-background-color: #38b6ff; -fx-alignment: center;");

        texteQTElivreDansMagasin = new Text("il y a ? fois le livre dans ce magasin");
        texteQTElivreDansMagasin.setStyle("-fx-font-size: 20px;");


        GridPane infos = new GridPane();
        infos.add(texteNomMagasin, 0, 0);
        infos.add(rechercheMag, 1, 0);
        infos.add(nomMagasin, 2, 0);
        infos.add(texteLivre, 0, 1);
        infos.add(rechercheLivre, 1, 1);
        infos.add(nomLivre, 2, 1);
        infos.add(texteQTElivreDansMagasin, 0, 2);
        GridPane.setColumnSpan(texteQTElivreDansMagasin, 3);
        infos.add(textQTEBox, 0, 3);
        GridPane.setColumnSpan(textQTEBox, 3);
        infos.add(qte, 0, 4);
        GridPane.setColumnSpan(qte, 3);

        for (Node node : infos.getChildren()){
            if (node instanceof Text) {
                if (!(((Text) node).getText().equals(texteNomMagasin.getText()) || ((Text) node).getText().equals(texteLivre.getText()))){
                    GridPane.setHalignment(node, HPos.CENTER);
                    GridPane.setValignment(node, VPos.CENTER);
                }
            }
            else{
                GridPane.setHalignment(node, HPos.CENTER);
                GridPane.setValignment(node, VPos.CENTER);
            }
        }

        infos.setAlignment(Pos.CENTER);
        infos.setHgap(10);
        infos.setVgap(10);


        Button ajouter = new Button("Ajouter");
        ajouter.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #38b6ff; -fx-text-fill: black;");

        Button retirer = new Button("Retirer");
        retirer.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #38b6ff; -fx-text-fill: black;");

        Button retour = new Button("Annuler");
        retour.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #38b6ff; -fx-text-fill: black;");


        ajouter.setOnAction(new ControlleurGererStock(app, this));
        retirer.setOnAction(new ControlleurGererStock(app, this));
        retour.setOnAction(event -> {
            app.setSceneAdmin();
        });

        HBox selection = new HBox(ajouter, retirer, retour);
        selection.setAlignment(Pos.CENTER);
        selection.setSpacing(50);

        VBox vueGereBox = new VBox(40, messageInfo, infos, selection);
        vueGereBox.setStyle("-fx-padding: 20; -fx-alignment: center;");
        sceneGereBox = new Scene(vueGereBox, 1000, 1000);
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
                texteQTElivreDansMagasin.setText("il y a ? fois le livre dans ce magasin");
            } else {
                int qteLivre = administrateur.qteDansMagasin(livre, mag);
                texteQTElivreDansMagasin.setText("il y a : " + qteLivre + " fois le livre dans ce magasin");
            }
            
        } catch (Exception e) {
            texteQTElivreDansMagasin.setText("il y a ? fois le livre dans ce magasin");
            System.err.println(e.getMessage());
        }
        
    }
}