package fr.saejava.vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class LivreVue {
    Scene sceneLivreVue;

    public LivreVue(App app) {
        Text livre = new Text("[nom du livre]");
        livre.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        Text titre = new Text("[titre]");
        Text nbPages = new Text("[nombre de pages]");
        Text datePubli = new Text("[date de publication]");
        Text prix = new Text("[prix]");
        VBox fin = new VBox(livre, titre, nbPages, datePubli, prix);
        fin.setSpacing(20);
        fin.setAlignment(Pos.CENTER);

        this.sceneLivreVue = new Scene(fin, 400, 300);
    }

    public Scene getScene(){
        return this.sceneLivreVue;
    }
}
