package fr.saejava.vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class StatistiqueVue {
    Scene sceneAjouteLib;

    public StatistiqueVue(App app) {
        Text titre = new Text("statistiques de la librairie: [nom de la librairie]");
        titre.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        Text CA = new Text("Chiffre d'affaires");
        HBox contCA = new HBox(CA);
        contCA.setMaxWidth(300);
        contCA.setStyle("-fx-background-color: #5ce1e6;");
        contCA.setAlignment(Pos.CENTER);
        TextField repCA = new TextField();
        repCA.setMaxWidth(300);
        VBox CAfin = new VBox(contCA, repCA);
        CAfin.setAlignment(Pos.CENTER);

        Button annuler = new Button("Annuler");

        VBox fin = new VBox(titre, CAfin, annuler);
        fin.setAlignment(Pos.CENTER);
        fin.setSpacing(20);

        this.sceneAjouteLib = new Scene(fin, 400, 300);
    }

    public Scene getSceneStatistique(){
        return this.sceneAjouteLib;
    }
}
