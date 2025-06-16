package fr.saejava.controlleur;

import java.sql.SQLException;

import fr.saejava.modele.ConnexionMySQL;
import fr.saejava.vue.App;
import fr.saejava.vue.ConnexionBD;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControlleurConnexionBD implements EventHandler<ActionEvent> {

    private App app;

    private ConnexionBD connexionBD;

    public ControlleurConnexionBD(App app, ConnexionBD connexionBD) {
        this.app = app;
        this.connexionBD = connexionBD;
    }


    @Override
    public void handle(ActionEvent event){
        Button btn = (Button) event.getSource();

        if (btn.getText().equals("Quitter")) {
            System.out.println("Fermeture de l'application...");
            app.close();
        } else if (btn.getText().equals("Se connecter")) {
            handleConnexion();
        }

    }



    public void handleConnexion() {

        System.out.println("Connexion à la base de données...");
        ConnexionMySQL co = app.getConnexionMySQL();
        String nomBD = connexionBD.getNomBD();
        String login = connexionBD.getLogin();
        String motDePasse = connexionBD.getMotDePasse();

        try {
            co.connecter(null, nomBD, login, motDePasse);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
            return;
        }
        

        if (co.isConnecte()){
            app.setConnexionMySQL(co);
            app.setSceneConnexionUtil();
            System.out.println("Connexion réussie");
        } else {
            System.out.println("Connexion échouée");
        }

    }



}




