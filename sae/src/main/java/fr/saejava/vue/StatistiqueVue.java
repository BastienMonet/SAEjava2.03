package fr.saejava.vue;

import java.util.ArrayList;
import java.util.Map;

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

    /**
     * Constructeur de la vue pour afficher les statistiques de la librairie
     * @param app l'application principale
     */
    public StatistiqueVue(App app) {
        Text titre = new Text("statistiques de la librairie: [nom de la librairie]");
        titre.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");


        Button annuler = new Button("Retour");
        annuler.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-background-color: #38b6ff;");

        annuler.setOnAction(event -> {
            app.setSceneAdmin();
        });

        PieChart pieChart = new PieChart();

        try {
            ArrayList<Map.Entry<String,Integer>> donnees = app.getAdministrateur().CAparMagasin();


            
            pieChart.setTitle("Chiffre d'affaires de la librairie par magasin");
            for (Map.Entry<String,Integer> entree: donnees){
            pieChart.getData().add(new PieChart.Data(entree.getKey(),entree.getValue()));
        }
        } catch (Exception e){
            System.err.println("une erreur c'est produite dans la création du graph");
            System.err.println(e.getMessage());
        }

        
        VBox fin = new VBox(titre, pieChart, annuler);
        fin.setAlignment(Pos.CENTER);
        fin.setSpacing(20);

        this.sceneAjouteLib = new Scene(fin, 1000, 1000);

    }
        
    /**
     * Retourne la scène de la vue des statistiques
     * @return la scène de la vue des statistiques
     */
    public Scene getScene(){
        return this.sceneAjouteLib;
    }
}
