package fr.saejava.controlleur;

import org.checkerframework.checker.units.qual.s;

import fr.saejava.vue.App;
import fr.saejava.vue.ConnexionVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class ControlleurConnexionUtil implements EventHandler<ActionEvent> {
    

    private App app;

    private ConnexionVue connexionVue;

    public ControlleurConnexionUtil(App app, ConnexionVue connexionVue) {
        this.app = app;
        this.connexionVue = connexionVue;

    }


    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();

        if (btn.getText().equals("Quitter")) {
            System.out.println("Fermeture de l'application...");
            app.close();
        } else if (btn.getText().equals("Connexion")) {
           seConnecter();
        } else if (btn.getText().equals("Pas de compte ?")){
            app.setSceneCreerCompte();
        }
    }



    public void seConnecter() {
        System.out.println("Connexion de l'utilisateur...");

        String nom = connexionVue.getNom();
        String prenom = connexionVue.getPrenom();
        String motDePasse = connexionVue.getMdp();

        RadioButton radioSelectionne = (RadioButton) connexionVue.getGroupe();

            try {
                boolean estco = false;

                if (radioSelectionne.getText().equals("client")){
                    estco = app.getClient().seConnecter(nom, prenom, motDePasse);

                    if (estco){
                        app.setSceneCompteClient();
                    } else {
                        app.alertConnexionEchoue();
                    }
                
                } else if (radioSelectionne.getText().equals("vendeur")){
                    estco = app.getVendeur().seConnecter(nom, prenom, motDePasse);

                } else if (radioSelectionne.getText().equals("administrateur")){
                    estco = app.getAdministrateur().seConnecter(nom, prenom, motDePasse);
                    
                }

                if (estco){
                        System.out.println("connexion réussi");

                    }
            } catch (Exception exception){
                System.err.println(exception.getMessage());
            }

    }
}
