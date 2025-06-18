package fr.saejava.vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.saejava.modele.Livre;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class FenetreLivreVue {
    Stage stage;

    public FenetreLivreVue(App app, Livre livre) {
        Stage stage = new Stage();
        this.stage= stage;

        Random random = new Random();
        int pg = random.nextInt(6);

        List<String> listePageGarde = new ArrayList<>();
        listePageGarde.add("page_garde.png");
        listePageGarde.add("page_garde2.png");
        listePageGarde.add("page_garde3.png");
        listePageGarde.add("page_garde4.png");
        listePageGarde.add("page_garde5.png");

        Image backImage = new Image("file:sae/img/" + listePageGarde.get(pg));

        BackgroundImage backgroundImage = new BackgroundImage(
            backImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(100, 100, true, true, true, false)
        );
        

        Text titre = new Text("titre du livre : " + livre.getTitre());
        titre.setStyle("-fx-font-size: 35; -fx-font-weight: bold;");
        Text nbPages = new Text("nombre de pages : " + String.valueOf(livre.getNbPages()));
        nbPages.setStyle("-fx-font-size: 15;");
        Text datePubli = new Text("année de publication : " +String.valueOf(livre.getDatePubli()));
        datePubli.setStyle("-fx-font-size: 15;");
        Text prix = new Text("prix du livre : " + String.valueOf(livre.getPrix()));
        prix.setStyle("-fx-font-size: 15;");

        Button quitter = new Button("quitter");
        quitter.setOnAction(event -> {
            this.close();
        });

        VBox fin = new VBox(titre, nbPages, datePubli, prix, quitter);
        fin.setSpacing(20);
        fin.setAlignment(Pos.CENTER);
        fin.setBackground(new Background(backgroundImage));

        Scene root = new Scene(fin, 1000, 1000);
        stage.setScene(root);
    }

    public void getFenetreLivreVue (){
        stage.show();
    }

    public void close() {
        stage.close();
    }
}
