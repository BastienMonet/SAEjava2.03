package fr.saejava.vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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

        PieChart CAfin = new PieChart();
        CAfin.setTitle("Chiffre d'affaires de la librairie");

        Button annuler = new Button("Annuler");

        VBox fin = new VBox(titre, CAfin, annuler);
        fin.setAlignment(Pos.CENTER);
        fin.setSpacing(20);

        this.sceneAjouteLib = new Scene(fin, 400, 300);
    }

    public Scene getScene(){
        return this.sceneAjouteLib;
    }
}
