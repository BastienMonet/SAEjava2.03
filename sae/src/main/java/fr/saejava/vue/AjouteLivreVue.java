package fr.saejava.vue;

import fr.saejava.controlleur.ControlleurAjoutNouvelleLib;
import fr.saejava.controlleur.ControlleurAjouteNouveauLivre;
import fr.saejava.controlleur.ControlleurAjoutNouvelleLib;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AjouteLivreVue {
    Scene sceneAjouteLivre;

    TextField titreLivre, nbpages, datePublic, prix; 

    public AjouteLivreVue(App app) {
        Text titre = new Text("Ajouter un livre");
        titre.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        Text nom = new Text("Titre du du livre");
        HBox contNom = new HBox(nom);
        contNom.setMaxWidth(300);
        contNom.setStyle("-fx-background-color: #38b6ff;");
        contNom.setAlignment(Pos.CENTER);
        titreLivre = new TextField();
        titreLivre.setMaxWidth(300);
        titreLivre.setPromptText("Entrez le titre du livre");
        VBox vb1 = new VBox(contNom, titreLivre);
        vb1.setAlignment(Pos.CENTER);

        Text nbpage = new Text("nombre de pages");
        HBox contnbpage = new HBox(nbpage);
        contnbpage.setMaxWidth(300);
        contnbpage.setStyle("-fx-background-color: #38b6ff;");
        contnbpage.setAlignment(Pos.CENTER);
        nbpages = new TextField();
        nbpages.setMaxWidth(300);
        nbpages.setPromptText("Entrez le nombre de page");
        VBox vb2 = new VBox(contnbpage, nbpages);
        vb2.setAlignment(Pos.CENTER);

        Text daText = new Text("Année de publication");
        HBox contdatepub = new HBox(daText);
        contdatepub.setMaxWidth(300);
        contdatepub.setStyle("-fx-background-color: #38b6ff;");
        contdatepub.setAlignment(Pos.CENTER);
        datePublic = new TextField();
        datePublic.setMaxWidth(300);
        datePublic.setPromptText("Entrez l'année de publication");
        VBox vb3 = new VBox(contdatepub, datePublic);
        vb3.setAlignment(Pos.CENTER);

        Text prixText = new Text("Prix du livre");
        HBox contprix = new HBox(prixText);
        contprix.setMaxWidth(300);
        contprix.setStyle("-fx-background-color: #38b6ff;");
        contprix.setAlignment(Pos.CENTER);
        prix = new TextField();
        prix.setMaxWidth(300);
        prix.setPromptText("Entrez le prix du livre");
        VBox vb4 = new VBox(contprix, prix);
        vb4.setAlignment(Pos.CENTER);


        Button ajoute = new Button("Ajouter"); 

        Button annuler = new Button("Annuler");


        ajoute.setOnAction(new ControlleurAjouteNouveauLivre(app, this));
        annuler.setOnAction(event -> {
            app.setSceneAdmin();
        });

        HBox hbAjoute = new HBox(ajoute, annuler);

        VBox fin = new VBox(titre, vb1, vb2, vb3, vb4, hbAjoute);
        fin.setAlignment(Pos.CENTER);
        fin.setSpacing(20);

        this.sceneAjouteLivre = new Scene(fin, 1000, 1000);
    }

    public Scene getScene(){
        return this.sceneAjouteLivre;
    }


    public String getTitreLivre() {
        return titreLivre.getText();
    }
    public String getNbPages() {
        return nbpages.getText();
    }
    public String getDatePublic() {
        return datePublic.getText();
    }
    public String getPrix() {
        return prix.getText();
    }
}
