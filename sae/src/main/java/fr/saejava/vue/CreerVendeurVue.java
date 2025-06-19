package fr.saejava.vue;

import javax.sound.sampled.Control;

import fr.saejava.controlleur.ControlleurCreerVendeur;
import fr.saejava.modele.Administrateur;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CreerVendeurVue {


    private Scene sceneCreationVendeur;

    private App app;

    private TextField prenomVendeur;

    private TextField nomVendeur;
    private TextField mdpVendeur;
    
    public CreerVendeurVue(App app) {

        this.app = app;


        Text messageInfo = new Text("Créez un compte vendeur");
        messageInfo.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        Text texteNomVendeur = new Text("Nom du vendeur");
        BorderPane textNomVendeurPane = new BorderPane(texteNomVendeur);
        BorderPane.setAlignment(textNomVendeurPane, javafx.geometry.Pos.CENTER);
        textNomVendeurPane.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #38b6ff; -fx-text-fill: black;");

        nomVendeur = new TextField();
        nomVendeur.setPromptText("Entrez le nom du vendeur");
        BorderPane nomVendeurPane = new BorderPane(nomVendeur);
        BorderPane.setAlignment(nomVendeurPane, javafx.geometry.Pos.CENTER);
        nomVendeurPane.setStyle("-fx-font-size: 16px; -fx-padding: 0px; -fx-background-color: #ffffff; -fx-text-fill: black;");

        VBox nomVendeurBox = new VBox(0, textNomVendeurPane, nomVendeurPane);

        Text textePrenomVendeur = new Text("Prénom du vendeur");
        BorderPane textPrenomVendeurPane = new BorderPane(textePrenomVendeur);
        BorderPane.setAlignment(textPrenomVendeurPane, javafx.geometry.Pos.CENTER);
        textPrenomVendeurPane.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #38b6ff; -fx-text-fill: black;");

        prenomVendeur = new TextField();
        prenomVendeur.setPromptText("Entrez le prénom du vendeur");
        BorderPane prenomVendeurPane = new BorderPane(prenomVendeur);
        BorderPane.setAlignment(prenomVendeurPane, javafx.geometry.Pos.CENTER);
        prenomVendeurPane.setStyle("-fx-font-size: 16px; -fx-padding: 0px; -fx-background-color: #ffffff; -fx-text-fill: black;");

        VBox prenomVendeurBox = new VBox(0, textPrenomVendeurPane, prenomVendeurPane);

        Text texteMDPVendeur = new Text("Mot de passe du vendeur");
        BorderPane textMDPVendeurPane = new BorderPane(texteMDPVendeur);
        BorderPane.setAlignment(textMDPVendeurPane, javafx.geometry.Pos.CENTER);
        textMDPVendeurPane.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #38b6ff; -fx-text-fill: black;");

        mdpVendeur = new PasswordField();
        mdpVendeur.setPromptText("Entrez le mot de passe du vendeur");
        BorderPane mdpVendeurPane = new BorderPane(mdpVendeur);
        BorderPane.setAlignment(mdpVendeurPane, javafx.geometry.Pos.CENTER);
        mdpVendeurPane.setStyle("-fx-font-size: 16px; -fx-padding: 0px; -fx-background-color: #ffffff; -fx-text-fill: black;");

        VBox mdpVendeurBox = new VBox(0, textMDPVendeurPane, mdpVendeurPane);

        Button creerVendeur = new Button("Créer le compte vendeur");
        creerVendeur.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #38b6ff; -fx-text-fill: black;");

        Button deconnexion = new Button("Annuler");
        deconnexion.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #38b6ff; -fx-text-fill: black;");

        deconnexion.setOnAction(event -> app.setSceneAdmin());
        creerVendeur.setOnAction(new ControlleurCreerVendeur(app ,this));


        HBox creerVendeurBox = new HBox(creerVendeur, deconnexion);
        creerVendeurBox.setAlignment(Pos.CENTER);
        creerVendeurBox.setSpacing(50);

        VBox vueCreationVendeur = new VBox(40, messageInfo, nomVendeurBox, prenomVendeurBox, mdpVendeurBox, creerVendeurBox);
        vueCreationVendeur.setStyle("-fx-padding: 20; -fx-alignment: center;");
        sceneCreationVendeur = new Scene(vueCreationVendeur, 1000, 1000);
    }

    public Scene getSceneCreationVendeur() {
        return sceneCreationVendeur;
    }

    public String getNomVendeur() {
        return nomVendeur.getText();
    }
    public String getPrenomVendeur() {
        return prenomVendeur.getText();
    }
    public String getMdpVendeur() {
        return mdpVendeur.getText();
    }
}