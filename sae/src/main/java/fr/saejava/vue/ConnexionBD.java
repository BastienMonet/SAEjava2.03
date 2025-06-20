package fr.saejava.vue;

import fr.saejava.controlleur.ControlleurConnexionBD;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

public class ConnexionBD {
    

    private Scene scene;

    TextField nomBD, login;

    PasswordField motDePasse;

    /**
     * Constructeur de la vue pour la connexion à la base de données
     * @param app l'application principale
     */
    public ConnexionBD(App app) {


        Text nom = new Text("Nom BD:");
        Text log = new Text("Login:");
        Text mdp = new Text("Mot de passe:");

        // Création des champs de saisie

        nomBD = new TextField();
        nomBD.setPromptText("Base de données");
        login = new TextField();
        login.setPromptText("Login");
        motDePasse = new PasswordField();
        motDePasse.setPromptText("Mot de passe");

        // Création des labels
        Text titre = new Text("Connectez-vous à la base de données");
        titre.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        titre.setTextAlignment(TextAlignment.RIGHT);
        Text labelNomBD = new Text("Base de données :");
        labelNomBD.setStyle("-fx-font-size: 15px;");
        GridPane.setHalignment(labelNomBD, HPos.RIGHT);
        Text labelLogin = new Text("Login :");
        labelLogin.setStyle("-fx-font-size: 15px;");
        GridPane.setHalignment(labelLogin, HPos.RIGHT);
        Text labelMotDePasse = new Text("Mot de passe :");
        labelMotDePasse.setStyle("-fx-font-size: 15px;");
        GridPane.setHalignment(labelMotDePasse, HPos.RIGHT);

        // Création des boutons
        Button btnConnexion = new Button("Se Connecter");
        btnConnexion.setStyle("-fx-background-color: #38b6ff; -fx-text-fill: black;");

        Button btnQuitter = new Button("Quitter");
        btnQuitter.setStyle("-fx-background-color: #38b6ff; -fx-text-fill: black;");

        // Mise en page des champs et labels
        GridPane champs = new GridPane();
        champs.add(labelNomBD, 0, 0);
        champs.add(nomBD, 1, 0);
        champs.add(labelLogin, 0, 1);
        champs.add(login, 1, 1);
        champs.add(labelMotDePasse, 0, 2);
        champs.add(motDePasse, 1, 2);
        champs.setHgap(10);
        champs.setVgap(10);
        champs.setAlignment(Pos.CENTER);

        // Mise en page des boutons
        btnConnexion.setOnAction(new ControlleurConnexionBD(app, this));
        btnQuitter.setOnAction(new ControlleurConnexionBD(app, this));
        HBox btnHb = new HBox(btnConnexion, btnQuitter);
        btnHb.setAlignment(Pos.CENTER);
        btnHb.setSpacing(20);

        // Mise en page du formulaire
        VBox centre = new VBox(champs, btnHb);
        centre.setAlignment(Pos.CENTER);
        centre.setSpacing(20);

        // Création de la scène
        BorderPane root = new BorderPane();
        root.setTop(titre);
        BorderPane.setAlignment(titre, Pos.CENTER);
        root.setCenter(centre);


        this.scene = new Scene(root, 1000, 1000);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    /**
     * Retourne la scène de connexion à la base de données
     * @return la scène de connexion
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Retourne le nom de la base de données, le login et le mot de passe saisis par l'utilisateur
     * @return un tableau contenant le nom de la base de données, le login et le mot de passe
     */
    public String getNomBD(){
        return nomBD.getText();
    }
    
    /**
     * Retourne le login saisi par l'utilisateur
     * @return le login
     */
    public String getLogin(){
        return login.getText();
    }

    /**
     * Retourne le mot de passe saisi par l'utilisateur
     * @return
     */
     public String getMotDePasse(){
        return motDePasse.getText();
    }

}
