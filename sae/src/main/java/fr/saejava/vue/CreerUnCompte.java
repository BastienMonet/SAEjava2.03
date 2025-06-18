package fr.saejava.vue;

import fr.saejava.controlleur.ControlleurCreerCompte;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
        Text bienvenue = new Text("Création du compte");
        HBox bienvenueCont = new HBox(bienvenue);
        bienvenue.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        bienvenueCont.setAlignment(Pos.CENTER);

        Text nomT = new Text("nom:");
        Text prenomT = new Text("prénom:");
        Text addresseT = new Text("addresse:");
        Text codePostalT = new Text("code postal:");
        Text villeT = new Text("ville:");
        Text mdpT = new Text("mot de passe:");
        Text cmdpT = new Text("confirmation mot de passe:");
        nom = new TextField();
        nom.setMaxHeight(Double.MAX_VALUE);
        prenom = new TextField();
        prenom.setMaxHeight(Double.MAX_VALUE);
        addresse = new TextField();
        addresse.setMaxHeight(Double.MAX_VALUE);
        codePostal = new TextField();
        codePostal.setMaxHeight(Double.MAX_VALUE);
        ville = new TextField();
        ville.setMaxHeight(Double.MAX_VALUE);
        motDePasse = new PasswordField();
        motDePasse.setMaxHeight(Double.MAX_VALUE);
        confirmPwd = new PasswordField();
        confirmPwd.setMaxHeight(Double.MAX_VALUE);

        
        GridPane infos = new GridPane();
        infos.add(nomT,0, 0);
        infos.add(prenomT,0, 1);
        infos.add(addresseT,0, 2);
        infos.add(codePostalT,0, 3);
        infos.add(villeT,0, 4);
        infos.add(mdpT,0, 5);
        infos.add(cmdpT,0, 6);
        GridPane.setHalignment(nomT, HPos.RIGHT);
        GridPane.setHalignment(prenomT, HPos.RIGHT);
        GridPane.setHalignment(addresseT, HPos.RIGHT);
        GridPane.setHalignment(codePostalT, HPos.RIGHT);
        GridPane.setHalignment(villeT, HPos.RIGHT);
        GridPane.setHalignment(mdpT, HPos.RIGHT);
        GridPane.setHalignment(cmdpT, HPos.RIGHT);

        infos.add(nom,1, 0);
        infos.add(prenom,1, 1);
        infos.add(addresse,1, 2);
        infos.add(codePostal,1, 3);
        infos.add(ville,1, 4);
        infos.add(motDePasse,1, 5);
        infos.add(confirmPwd,1, 6);
        GridPane.setHgrow(nom, Priority.ALWAYS);
        GridPane.setHgrow(prenom, Priority.ALWAYS);
        GridPane.setHgrow(addresse, Priority.ALWAYS);
        GridPane.setHgrow(codePostal, Priority.ALWAYS);
        GridPane.setHgrow(ville, Priority.ALWAYS);
        GridPane.setHgrow(motDePasse, Priority.ALWAYS);
        GridPane.setHgrow(confirmPwd, Priority.ALWAYS);

        infos.setHgap(10);
        infos.setVgap(10);

        


        Button btncreerCompte = new Button("Créer un compte");
        btncreerCompte.setStyle("-fx-background-color: #38b6ff; -fx-text-fill: black;");
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.setStyle("-fx-background-color: #38b6ff; -fx-text-fill: black;");

        btnAnnuler.setOnAction(new ControlleurCreerCompte(app, this));
        btncreerCompte.setOnAction(new ControlleurCreerCompte(app, this));


        HBox hbbutton = new HBox(btncreerCompte, btnAnnuler);
        hbbutton.setAlignment(Pos.CENTER);
        hbbutton.setSpacing(20);

        
        BorderPane root = new BorderPane();
        root.setTop(bienvenueCont);
        root.setCenter(infos);
        infos.setPadding(new Insets(20, 20, 20, 20));
        root.setBottom(hbbutton);
        root.setPadding(new Insets(0, 5, 20, 5));

        Scene scene = new Scene(root, 500, 380);

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
