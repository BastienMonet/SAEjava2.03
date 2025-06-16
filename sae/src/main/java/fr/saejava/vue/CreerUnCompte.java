package fr.saejava.vue;

import fr.saejava.controlleur.ControlleurCreerCompte;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CreerUnCompte {


    private Scene scene; 

    private TextField nom;
    private TextField prenom;
    private TextField addresse;
    private TextField codePostal;
    private TextField ville;
    private PasswordField motDePasse;
    private PasswordField confirmPwd;

    public CreerUnCompte(App app){
        nom = new TextField();
        prenom = new TextField();
        addresse = new TextField();
        codePostal = new TextField();
        ville = new TextField();
        motDePasse = new PasswordField();
        confirmPwd = new PasswordField();

        BorderPane root = new BorderPane();
        HBox hb1 = new HBox(new Text("Nom :"), nom);
        HBox hb2 = new HBox(new Text("Prenom :"), prenom);
        HBox hb3 = new HBox(new Text("addresse :"), addresse);
        HBox hb4 = new HBox(new Text("Code Postal :"), codePostal);
        HBox hb5 = new HBox(new Text("Ville de résidence:"), ville);
        HBox hb6 = new HBox(new Text("Mot de passe :"), motDePasse);
        HBox hb7 = new HBox(new Text("Confirmation du mot de passe :"), confirmPwd);

        VBox vb = new VBox(hb1, hb2, hb3, hb4, hb5, hb6, hb7);

        Text bienvenue = new Text("Création du compte");


        Button btncreerCompte = new Button("Créer un compte");
        Button btnAnnuler = new Button("Annuler");

        btnAnnuler.setOnAction(new ControlleurCreerCompte(app, this));
        btncreerCompte.setOnAction(new ControlleurCreerCompte(app, this));


        HBox hbbutton = new HBox(btncreerCompte, btnAnnuler);

        

        root.setTop(bienvenue);
        root.setCenter(vb);
        root.setBottom(hbbutton);

        Scene scene = new Scene(root);

        this.scene = scene;
    }

    public String getNom() {
        return nom.getText();
    }
    public String getPrenom() {
        return prenom.getText();
    }
    public String getAddresse() {
        return addresse.getText();
    }
    public String getCodePostal() {
        return codePostal.getText();
    }
    public String getVille() {
        return ville.getText();
    }
    public String getMotDePasse() {
        return motDePasse.getText();
    }
    public String getConfirmPwd() {
        return confirmPwd.getText();
    }

    public Scene getScene(){
        return scene;
    }
    
}
