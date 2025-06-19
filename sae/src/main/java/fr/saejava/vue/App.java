package fr.saejava.vue;

import fr.saejava.modele.Administrateur;
import fr.saejava.modele.Client;
import fr.saejava.modele.Commande;
import fr.saejava.modele.ConnexionMySQL;
import fr.saejava.modele.Livre;
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
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        this.setSceneConnexionBD();

        stage.setTitle("SAE java");
        stage.show();

    }

    public void setStocksGlobauxVue() throws Exception {
        GereStocksGlobauxVue vue = new GereStocksGlobauxVue(this);
        this.stage.setScene(vue.getSceneStocksGlobaux());
    }

    public void setSceneAdmin() {
        AdministrateurVue administrateurVue = new AdministrateurVue(this);
        this.stage.setScene(administrateurVue.getSceneAdmin());
    }

    public void setSceneCreerVendeur() {
        CreerVendeurVue vue = new CreerVendeurVue(this);
        this.stage.setScene(vue.getSceneCreationVendeur());
    }

    public void setAjouterLibrairie() {
        AjouteLibVue vue = new AjouteLibVue(this);
        this.stage.setScene(vue.getSceneAjouteLib());
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

    public void setSceneAjouterCommandeVue(Commande commande) throws Exception {
        AjouterCommandeVue AjoutercommandeVue = new AjouterCommandeVue(this, commande);
        this.stage.setScene(AjoutercommandeVue.getScene());
    }

    public void setFenetreLivreVue(Livre livre){
        FenetreLivreVue livreVue = new FenetreLivreVue(this, livre);
        livreVue.getFenetreLivreVue();
    }

    public void setFenetreVoirCommande(Commande commande) {
        VoirCommandeVue voirCommandeVue = new VoirCommandeVue(this, commande);
        voirCommandeVue.getFenetreVoirCommandeVue();
    }

    public void setSceneAjouteLivre() {
        AjouteLivreVue ajouteLivreVue = new AjouteLivreVue(this);
        this.stage.setScene(ajouteLivreVue.getScene());
    }

    public void setSceneModifieCommande(Commande commande) throws Exception {
        ModifierCommandeVue modifierCommandeVue = new ModifierCommandeVue(this, commande);
        this.stage.setScene(modifierCommandeVue.getScene());
    }

    public ConnexionMySQL getConnexionMySQL() {
        return connexionMySQL;
    }

    public void setConnexionMySQL(ConnexionMySQL connexionMySQL) {
        this.connexionMySQL = connexionMySQL;
        this.administrateur = new Administrateur(connexionMySQL);
    }

    public void setVueVendeur() {
        VendeurVue vendeurVue = new VendeurVue(this);
        this.stage.setScene(vendeurVue.getSceneVendeur());
    }

    public void setSceneAjouterCommandeVueVendeur(Commande commande) throws Exception {
        AjouterCommandeVueVendeur AjoutercommandeVueVendeur = new AjouterCommandeVueVendeur(this, commande);
        this.stage.setScene(AjoutercommandeVueVendeur.getScene());
    }

    public void setStatistiqueVue() {
        StatistiqueVue statistiquesVue = new StatistiqueVue(this);
        this.stage.setScene(statistiquesVue.getScene());
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
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Identifiant ou mot de passe incorrect");
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
        alert.setContentText("Veuillez choisir un autre nom d'utilisateur ou prénom.");
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

    public void alertErreur(Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur est survenue.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    public void alertAjoutSucces() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout réussi");
        alert.setHeaderText("L'élément a été ajouté avec succès.");
        alert.showAndWait();
    }

    public void alertQte() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Quantité invalide");
        alert.setHeaderText("La quantité saisie est invalide.");
        alert.setContentText("Veuillez saisir une quantité positive et inférieure ou égale à la quantité disponible.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        App.launch(args);
    }
    
}
