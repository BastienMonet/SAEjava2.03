package fr.saejava.vue;

import fr.saejava.modele.Livre;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class FenetreLivreVue {
    Stage stage;

    public FenetreLivreVue(App app, Livre livre) {
        Stage stage = new Stage();
        this.stage= stage;

        

        Text titre = new Text("titre du livre : " + livre.getTitre());
        titre.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        Text nbPages = new Text("nombre de pages : " + String.valueOf(livre.getNbPages()));
        Text datePubli = new Text("année de publication : " +String.valueOf(livre.getDatePubli()));
        Text prix = new Text("prix du livre : " + String.valueOf(livre.getPrix()));

        Button quitter = new Button("quitter");
        quitter.setOnAction(event -> {
            this.close();
        });

        VBox fin = new VBox(titre, nbPages, datePubli, prix, quitter);
        fin.setSpacing(20);
        fin.setAlignment(Pos.CENTER);

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
