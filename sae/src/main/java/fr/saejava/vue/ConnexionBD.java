package fr.saejava.vue;

import fr.saejava.controlleur.ControlleurConnexionBD;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConnexionBD {
    

    private Scene scene;

    TextField nomBD, login, motDePasse;

    public ConnexionBD(App app) {

        // Création des champs de saisie
        nomBD = new TextField();
        nomBD.setPromptText("Base de données");
        login = new TextField();
        login.setPromptText("Login");
        motDePasse = new TextField();
        motDePasse.setPromptText("Mot de passe");

        // Création des labels
        Text titre = new Text("Connectez-vous à la base de données");
        titre.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Text labelNomBD = new Text("Base de données :");
        Text labelLogin = new Text("Login :");
        Text labelMotDePasse = new Text("Mot de passe :");

        // Création des boutons
        Button btnConnexion = new Button("Se Connecter");
        btnConnexion.setStyle("-fx-background-color: #38b6ff; -fx-text-fill: white;");
        Button btnQuitter = new Button("Quitter");
        btnQuitter.setStyle("-fx-background-color: #38b6ff; -fx-text-fill: white;");

        // Mise en page des champs et labels
        VBox champs = new VBox(15,
            new VBox(10,
                new javafx.scene.layout.HBox(20, labelNomBD, nomBD),
                new javafx.scene.layout.HBox(20, labelLogin, login),
                new javafx.scene.layout.HBox(20, labelMotDePasse, motDePasse)
            ),
            new javafx.scene.layout.HBox(60, btnConnexion, btnQuitter)
        );
        champs.setStyle("-fx-alignment: center;");
        champs.setPrefWidth(500);

        // Centrer les éléments dans les HBox et VBox
        ((VBox)champs.getChildren().get(0)).setStyle("-fx-alignment: center;");
        ((javafx.scene.layout.HBox)champs.getChildren().get(1)).setStyle("-fx-alignment: center;");

        for (javafx.scene.Node node : ((VBox)champs.getChildren().get(0)).getChildren()) {
            if (node instanceof javafx.scene.layout.HBox) {
                ((javafx.scene.layout.HBox)node).setStyle("-fx-alignment: center;");
            }
        }

        BorderPane root = new BorderPane();
        root.setTop(titre);
        BorderPane.setAlignment(titre, javafx.geometry.Pos.CENTER);
        root.setCenter(champs);

        this.scene = new Scene(root, 600, 400);
    }

    public Scene getScene() {
        return scene;
    }


    public String getNomBD(){
        return nomBD.getText();
    }

     public String getLogin(){
        return login.getText();
    }

     public String getMotDePasse(){
        return motDePasse.getText();
    }

}
