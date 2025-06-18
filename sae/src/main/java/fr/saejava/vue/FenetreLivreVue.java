package fr.saejava.vue;

import fr.saejava.modele.Livre;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class FenetreLivreVue {
    Stage stage;

    public FenetreLivreVue(App app, Livre livre) {
        Stage stage = new Stage();
        this.stage= stage;

        

        Text titre = new Text("[titre]");
        titre.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        Text nbPages = new Text("[nombre de pages]");
        Text datePubli = new Text("[date de publication]");
        Text prix = new Text("[prix]");
        VBox fin = new VBox(titre, nbPages, datePubli, prix);
        fin.setSpacing(20);
        fin.setAlignment(Pos.CENTER);

        Scene root = new Scene(fin, 400, 300);
        stage.setScene(root);
    }

    public void getFenetreLivreVue (){
        stage.show();
    }

    public void close() {
        stage.close();
    }
}
