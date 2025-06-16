package fr.saejava.controlleur;

import java.sql.SQLException;

import fr.saejava.exception.CompteDejaPrisException;
import fr.saejava.modele.Client;
import fr.saejava.vue.App;
import fr.saejava.vue.CreerUnCompte;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControlleurCreerCompte implements EventHandler<ActionEvent> {

    private App app;
    private CreerUnCompte creerCompteVue;

    public ControlleurCreerCompte(App app, CreerUnCompte creerCompte) {
        this.app = app;
        this.creerCompteVue = creerCompte;
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();

        if (btn.getText().equals("Créer un compte")) {
            creerCompte();
        } else if (btn.getText().equals("Annuler")) {
            app.setSceneConnexionUtil();
        }
    }

    public void creerCompte() {
        String nom = creerCompteVue.getNom();
        String prenom = creerCompteVue.getPrenom();
        String motDePasse = creerCompteVue.getMotDePasse();
        String addresse = creerCompteVue.getAddresse();
        String codePostal = creerCompteVue.getCodePostal();
        String ville = creerCompteVue.getVille();

        if (nom.isEmpty() || prenom.isEmpty() || motDePasse.isEmpty() || addresse.isEmpty() || codePostal.isEmpty() || ville.isEmpty()) {
            app.alertChampsVides();
        } else if (!motDePasse.equals(creerCompteVue.getConfirmPwd())) {
            app.alertMdpDifferent();
        } else {
        try {
            Client c = new Client(nom, prenom, motDePasse, addresse, codePostal, ville, 0.00);
            app.getAdministrateur().ajouteClientBD(c);
            app.setSceneConnexionUtil();
            app.alertAjoutUtilisateur();
        } catch (SQLException e) {
            System.out.println("Erreur de la base de donnée : " + e.getMessage());
        } catch (CompteDejaPrisException e) {
            System.out.println("un compte porte dejà ces identifiant : " + e.getMessage());
            app.alertCompteDejaPris();
        } catch (Exception e) {
            System.out.println("Erreur inconnue : " + e.getMessage());
        }
        }



        
        }
    
}
