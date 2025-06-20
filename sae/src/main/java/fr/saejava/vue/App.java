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


    /**
     * Constructeur de l'application
     */
    @Override
    public void init() throws Exception {
        this.connexionMySQL = new ConnexionMySQL();
        client = new Client(connexionMySQL);
        vendeur = new Vendeur(connexionMySQL);
        administrateur = new Administrateur(connexionMySQL);
    }

    /**
     * Méthode pour démarrer l'application
     * @param stage la scène principale de l'application
     * @throws Exception si une erreur se produit lors du démarrage
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        this.setSceneConnexionBD();

        stage.setTitle("SAE java");
        stage.show();

    }

    /**
     * Méthode pour définir la scène de connexion à la base de données
     */
    public void setStocksGlobauxVue() throws Exception {
        GereStocksGlobauxVue vue = new GereStocksGlobauxVue(this);
        this.stage.setScene(vue.getSceneStocksGlobaux());
    }

    /**
     * Méthode pour définir la scène d'administration
     */
    public void setSceneAdmin() {
        AdministrateurVue administrateurVue = new AdministrateurVue(this);
        this.stage.setScene(administrateurVue.getSceneAdmin());
    }

    /**
     * Méthode pour définir la scène de création d'un vendeur
     */
    public void setSceneCreerVendeur() {
        CreerVendeurVue vue = new CreerVendeurVue(this);
        this.stage.setScene(vue.getSceneCreationVendeur());
    }

    /**
     * Méthode pour définir la scène d'ajout d'une librairie
     */
    public void setAjouterLibrairie() {
        AjouteLibVue vue = new AjouteLibVue(this);
        this.stage.setScene(vue.getSceneAjouteLib());
    }

    /**
     * Méthode pour définir la scène de création d'un compte
     */
    public void setSceneCreerCompte(){
        CreerUnCompte vue = new CreerUnCompte(this);
        stage.setScene(vue.getScene());
    }

    /**
     * Méthode pour définir la scène de connexion à l'application
     */
    public void setSceneConnexionUtil(){
        ConnexionVue connexionVue = new ConnexionVue(this);
        this.stage.setScene(connexionVue.getScene());
    }

    /**
     * Méthode pour définir la scène de connexion à la base de données
     */
    public void setSceneConnexionBD(){
        ConnexionBD connexionBD = new ConnexionBD(this);
        this.stage.setScene(connexionBD.getScene());
    }

    /**
     * Méthode pour définir la scène de compte client
     * @throws Exception si une erreur se produit lors de la création de la scène
     */
    public void setSceneCompteClient() throws Exception{
        CompteClient compteClient = new CompteClient(this, client);
        this.stage.setScene(compteClient.getScene());
    }

    /**
     * Méthode pour définir la scène d'ajout d'une commande
     * @param commande la commande à ajouter
     * @throws Exception si une erreur se produit lors de la création de la scène
     */
    public void setSceneAjouterCommandeVue(Commande commande) throws Exception {
        AjouterCommandeVue AjoutercommandeVue = new AjouterCommandeVue(this, commande);
        this.stage.setScene(AjoutercommandeVue.getScene());
    }

    /**
     * Méthode pour définir la scène de visualisation d'une commande
     * @param commande la commande à visualiser
     */
    public void setFenetreLivreVue(Livre livre){
        FenetreLivreVue livreVue = new FenetreLivreVue(this, livre);
        livreVue.getFenetreLivreVue();
    }

    /**
     * Méthode pour définir la scène de visualisation d'une commande
     * @param commande la commande à visualiser
     */
    public void setFenetreVoirCommande(Commande commande) {
        VoirCommandeVue voirCommandeVue = new VoirCommandeVue(this, commande);
        voirCommandeVue.getFenetreVoirCommandeVue();
    }

    /**
     * Méthode pour définir la scène d'ajout d'un livre
     */
    public void setSceneAjouteLivre() {
        AjouteLivreVue ajouteLivreVue = new AjouteLivreVue(this);
        this.stage.setScene(ajouteLivreVue.getScene());
    }

    /**
     * Méthode pour définir la scène de modification d'une commande
     * @param commande la commande à modifier
     * @throws Exception si une erreur se produit lors de la création de la scène
     */
    public void setSceneModifieCommande(Commande commande) throws Exception {
        ModifierCommandeVue modifierCommandeVue = new ModifierCommandeVue(this, commande);
        this.stage.setScene(modifierCommandeVue.getScene());
    }

    /**
     * Méthode pour obtenir la connexion MySQL
     * @return la connexion MySQL
     */
    public ConnexionMySQL getConnexionMySQL() {
        return connexionMySQL;
    }

    /**
     * Méthode pour définir la connexion MySQL
     * @param connexionMySQL la connexion MySQL à définir
     */
    public void setConnexionMySQL(ConnexionMySQL connexionMySQL) {
        this.connexionMySQL = connexionMySQL;
        this.administrateur = new Administrateur(connexionMySQL);
    }

    /**
     * Méthode pour définir la scène du vendeur
     */
    public void setVueVendeur() {
        VendeurVue vendeurVue = new VendeurVue(this);
        this.stage.setScene(vendeurVue.getSceneVendeur());
    }

    /**
     * Méthode pour définir la scène d'ajout d'une commande en tant que vendeur
     * @param commande la commande à ajouter
     * @throws Exception si une erreur se produit lors de la création de la scène
     */
    public void setSceneAjouterCommandeVueVendeur(Commande commande) throws Exception {
        AjouterCommandeVueVendeur AjoutercommandeVueVendeur = new AjouterCommandeVueVendeur(this, commande);
        this.stage.setScene(AjoutercommandeVueVendeur.getScene());
    }

    /**
     * Méthode pour définir la scène de visualisation des statistiques
     */
    public void setStatistiqueVue() {
        StatistiqueVue statistiquesVue = new StatistiqueVue(this);
        this.stage.setScene(statistiquesVue.getScene());
    }

    /**
     * Méthode pour fermer l'application et la connexion MySQL
     */
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

    /**
     * Méthode pour obtenir le stage de l'application
     * @return le stage de l'application
     */
    public Client getClient() {
        return client;
    }

    /**
     * Méthode pour obtenir le vendeur de l'application
     * @return le vendeur de l'application
     */
    public Vendeur getVendeur() {
        return vendeur;
    }

    /**
     * Méthode pour obtenir l'administrateur de l'application
     * @return l'administrateur de l'application
     */
    public Administrateur getAdministrateur() {
        return administrateur;
    }

    /**
     * Méthode pour obtenir le stage de l'application
     * @return le stage de l'application
     */
    public void alertConnexionEchoue(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Identifiant ou mot de passe incorrect");
        alert.showAndWait();
    }

    /**
     * Méthode pour afficher une alerte de succès lors de la création d'un compte utilisateur
     */
    public void alertAjoutUtilisateur() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Compte créé");
        alert.setHeaderText("Votre compte a été créé avec succès !");
        alert.showAndWait();
    }

    /**
     * Méthode pour afficher une alerte d'erreur lorsque l'instance est déjà prise
     */
    public void alertInstanceDejaPrise() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Instance déjà prise");
        alert.setHeaderText("cette instance avec ces identifiants existe déjà.");
        alert.setContentText("Veuillez choisir un autre nom");
        alert.showAndWait();
    }

    /**
     * Méthode pour afficher une alerte lorsque les mots de passe ne correspondent pas
     */
    public void alertMdpDifferent() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Mot de passe différent");
        alert.setHeaderText("Les mots de passe ne correspondent pas.");
        alert.showAndWait();
    }

    /**
     * Méthode pour afficher une alerte lorsque des champs sont vides
     */
    public void alertChampsVides() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText("Veuillez remplir tous les champs.");
        alert.showAndWait();
    }

    /**
     * Méthode pour afficher une alerte d'erreur
     * @param e l'exception à afficher
     */
    public void alertErreur(Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur est survenue.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    /**
     * Méthode pour afficher une alerte de succès lors de l'ajout d'un livre
     */
    public void alertAjoutSucces() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout réussi");
        alert.setHeaderText("L'élément a été ajouté avec succès.");
        alert.showAndWait();
    }

    /**
     * Méthode pour afficher une alerte de succès lors de la modification d'un livre
     */
    public void alertQte() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Quantité invalide");
        alert.setHeaderText("La quantité saisie est invalide.");
        alert.setContentText("Veuillez saisir une quantité positive et inférieure ou égale à la quantité disponible.");
        alert.showAndWait();
    }

    /**
     * Méthode principale pour lancer l'application
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        App.launch(args);
    }
    
}
