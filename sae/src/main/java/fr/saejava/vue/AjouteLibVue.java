package fr.saejava.vue;

import fr.saejava.controlleur.ControlleurAjoutNouvelleLib;
import fr.saejava.controlleur.ControlleurAjoutNouvelleLib;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AjouteLibVue {
    Scene sceneAjouteLib;

    TextField nomMagasin;

    TextField nomCity;

    /**
     * Constructeur de la vue pour ajouter une librairie
     * @param app l'application principale
     */
    public AjouteLibVue(App app) {
        Text titre = new Text("Ajouter une librairie");
        titre.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        Text nom = new Text("Nom du magasin");
        HBox contNom = new HBox(nom);
        contNom.setMaxWidth(300);
        contNom.setStyle("-fx-background-color: #38b6ff;");
        contNom.setAlignment(Pos.CENTER);
        nomMagasin = new TextField();
        nomMagasin.setMaxWidth(300);
        nomMagasin.setPromptText("Entrez le nom du magasin");
        VBox nomMag = new VBox(contNom, nomMagasin);
        nomMag.setAlignment(Pos.CENTER);

        Text ville = new Text("Ville du magasin");
        HBox contVille = new HBox(ville);
        contVille.setMaxWidth(300);
        contVille.setStyle("-fx-background-color: #38b6ff;");
        contVille.setAlignment(Pos.CENTER);
        nomCity = new TextField();
        nomCity.setMaxWidth(300);
        nomCity.setPromptText("Entrez la ville du magasin");
        VBox nomVille = new VBox(contVille, nomCity);
        nomVille.setAlignment(Pos.CENTER);


        Button ajoute = new Button("Ajouter"); 
        ajoute.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #38b6ff;");

        Button annuler = new Button("Annuler");
        annuler.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #38b6ff;");

        ajoute.setOnAction(new ControlleurAjoutNouvelleLib(app, this));
        annuler.setOnAction(event -> {
            app.setSceneAdmin();
        });

        HBox hbAjoute = new HBox(ajoute, annuler);
        hbAjoute.setAlignment(Pos.CENTER);
        hbAjoute.setSpacing(50);

        VBox fin = new VBox(titre, nomMag, nomVille, hbAjoute);
        fin.setAlignment(Pos.CENTER);
        fin.setSpacing(20);

        this.sceneAjouteLib = new Scene(fin, 1000, 1000);
    }

    /**
     * Retourne la scène pour ajouter une librairie
     * @return la scène pour ajouter une librairie
     */
    public Scene getSceneAjouteLib(){
        return this.sceneAjouteLib;
    }

    /**
     * Retourne le nom du magasin et de la ville saisis par l'utilisateur
     * @return le nom du magasin et de la ville
     */
    public String getNomMagasin() {
        return nomMagasin.getText();
    }

    /**
     * Retourne le nom de la ville saisi par l'utilisateur
     * @return le nom de la ville
     */
    public String getNomCity() {
        return nomCity.getText();
    }
}
