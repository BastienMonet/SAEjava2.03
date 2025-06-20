package fr.saejava.vue;
import fr.saejava.controlleur.ControlleurConnexionUtil;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Toggle;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ToggleGroup;

public class ConnexionVue {
    Scene sceneConnexion;

    private TextField nomText;
    private TextField prenomText;
    private PasswordField mdpText;
    private ToggleGroup groupe;

    Text connexionText;

    /**
     * Constructeur de la vue pour la connexion des utilisateurs
     * @param app l'application principale
     */
    public ConnexionVue(App app) {
        Text messBienvenue = new Text("Salutation !\nVeuillez vous connecter.");
        messBienvenue.setTextAlignment(TextAlignment.CENTER);
        messBienvenue.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        Text nom = new Text("Nom:");
        Text prenom = new Text("Prénom:");
        Text mdp = new Text("Mot de passe:");
        nomText = new TextField("");
        prenomText = new TextField();
        mdpText = new PasswordField();
        GridPane infoConnextion = new GridPane();
        infoConnextion.add(nom, 0, 0);
        infoConnextion.add(nomText, 1, 0);
        infoConnextion.add(prenom, 0, 1);
        infoConnextion.add(prenomText, 1, 1);
        infoConnextion.add(mdp, 0, 2);
        infoConnextion.add(mdpText, 1, 2);
        infoConnextion.setAlignment(Pos.CENTER);
        infoConnextion.setHgap(10);
        infoConnextion.setVgap(10);

        RadioButton radioCli = new RadioButton("client");
        RadioButton radiovend = new RadioButton("vendeur");
        RadioButton radioAdmin = new RadioButton("administrateur");
        radioCli.setSelected(true);
        groupe = new ToggleGroup();
        radioCli.setToggleGroup(groupe);
        radiovend.setToggleGroup(groupe);
        radioAdmin.setToggleGroup(groupe);
        HBox radio = new HBox(radioCli, radiovend, radioAdmin);
        radio.setAlignment(Pos.CENTER);
        radio.setSpacing(10);

        connexionText = new Text("");

        Button connexion = new Button("Connexion");
        connexion.setStyle("-fx-background-color: #38b6ff; -fx-text-fill: black;");
        connexion.setOnAction(new ControlleurConnexionUtil(app, this));
        connexion.setMinWidth(80);

        Button quitter = new Button("Quitter");
        quitter.setStyle("-fx-background-color: #38b6ff; -fx-text-fill: black;");
        quitter.setOnAction(new ControlleurConnexionUtil(app, this));
        quitter.setMinWidth(80);

        HBox boutonsAction = new HBox(connexion, quitter);
        boutonsAction.setAlignment(Pos.CENTER);
        boutonsAction.setSpacing(100);

        Button inscription = new Button("Pas de compte ?");
        inscription.setStyle("-fx-background-color: #38b6ff; -fx-text-fill: black;");
        inscription.setOnAction(new ControlleurConnexionUtil(app, this));
        inscription.setAlignment(Pos.CENTER);



        VBox fin = new VBox(messBienvenue, infoConnextion, radio, connexionText, boutonsAction, inscription);
        fin.setAlignment(Pos.CENTER);
        fin.setSpacing(30);

        this.sceneConnexion = new Scene(fin, 1000, 1000);
    }

    /**
     * Méthodes pour récupérer les informations saisies par l'utilisateur
     * @return les informations saisies
     */
    public String getNom() {
        return nomText.getText();
    }

    /**
     * Retourne le prénom saisi par l'utilisateur
     * @return le prénom
     */
    public String getPrenom() {
        return prenomText.getText();
    }

    /**
     * Retourne le mot de passe saisi par l'utilisateur
     * @return le mot de passe
     */
    public String getMdp() {
        return mdpText.getText();
    }

    /**
     * Retourne le type d'utilisateur sélectionné
     * @return le type d'utilisateur (client, vendeur, administrateur)
     */
    public Toggle getGroupe() {
        return groupe.getSelectedToggle();
    }

    /**
     * Retourne la scène de connexion
     * @return la scène de connexion
     */
    public Scene getScene() {
        return sceneConnexion;
    }

    /**
     * Retourne le texte de connexion
     * @return le texte de connexion
     */
    public Text getConnexionText() {
        return connexionText;
    }
}
