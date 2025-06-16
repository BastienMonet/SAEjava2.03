package fr.saejava.vue;

import fr.saejava.modele.Administrateur;
import fr.saejava.modele.Client;
import fr.saejava.modele.ConnexionMySQL;
import fr.saejava.modele.Vendeur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
   
    private Stage stage;

    private ConnexionMySQL connexionMySQL;

    private Client client;
    private Vendeur vendeur;
    private Administrateur administrateur;


    @Override
    public void init() throws Exception {
        this.connexionMySQL = new ConnexionMySQL();
        client = new Client(connexionMySQL);
        vendeur = new Vendeur(connexionMySQL);
        administrateur = new Administrateur(connexionMySQL);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.stage = primaryStage;

        this.setSceneConnexionBD();

        stage.setTitle("SAE java");
        stage.show();

    }

    public void setSceneCreerCompte(){
        CreerUnCompte vue = new CreerUnCompte(this);
        stage.setScene(vue.getScene());
    }

    public void setSceneConnexionUtil(){
        ConnexionVue connexionVue = new ConnexionVue(this);
        this.stage.setScene(connexionVue.getScene());
    }

    public void setSceneConnexionBD(){
        ConnexionBD connexionBD = new ConnexionBD(this);
        this.stage.setScene(connexionBD.getScene());
    }

    public void setSceneCompteClient() throws Exception{
        CompteClient compteClient = new CompteClient(this, client);
        this.stage.setScene(compteClient.getScene());
    }

    public ConnexionMySQL getConnexionMySQL() {
        return connexionMySQL;
    }

    public void setConnexionMySQL(ConnexionMySQL connexionMySQL) {
        this.connexionMySQL = connexionMySQL;
        this.administrateur = new Administrateur(connexionMySQL);
    }

    public void close() {
        if (this.connexionMySQL != null) {
            try {
                this.connexionMySQL.close();
                System.out.println("Déconnexion réussie");
            } catch (Exception e) {
                System.out.println("Erreur lors de la déconnexion : " + e.getMessage());
            }
            
        }
        stage.close();
    }

    public Client getClient() {
        return client;
    }

    public Vendeur getVendeur() {
        return vendeur;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void alertConnexionEchoue(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("erreur de connexion");
        alert.setHeaderText("identifiant ou mot de passe incorrecte");
        alert.showAndWait();
    }

    public void alertAjoutUtilisateur() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Compte créé");
        alert.setHeaderText("Votre compte a été créé avec succès !");
        alert.showAndWait();
    }

    public void alertCompteDejaPris() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Compte déjà pris");
        alert.setHeaderText("Un compte avec ces identifiants existe déjà.");
        alert.setContentText("Veuillez choisir un autre nom d'utilisateur ou prenom.");
        alert.showAndWait();
    }

    public void alertMdpDifferent() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Mot de passe différent");
        alert.setHeaderText("Les mots de passe ne correspondent pas.");
        alert.showAndWait();
    }

    public void alertChampsVides() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText("Veuillez remplir tous les champs.");
        alert.showAndWait();
    }



    public static void main(String[] args) {
        App.launch(args);
    }
    
}
