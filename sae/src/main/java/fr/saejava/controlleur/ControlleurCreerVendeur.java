package fr.saejava.controlleur;

import fr.saejava.exception.CompteDejaPrisException;
import fr.saejava.modele.Administrateur;
import fr.saejava.modele.Vendeur;
import fr.saejava.vue.App;
import fr.saejava.vue.CreerVendeurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControlleurCreerVendeur implements EventHandler<ActionEvent> {

    private CreerVendeurVue creerVendeurVue;

    private App app;

    private Administrateur administrateur;

    public ControlleurCreerVendeur(App app, CreerVendeurVue creerVendeurVue) {
        this.creerVendeurVue = creerVendeurVue;
        this.app = app;
        this.administrateur = app.getAdministrateur();
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();

        if (btn.getText().equals("Créer le compte vendeur")) {
            creerCompteVendeur();
        }
    }


    public void creerCompteVendeur() {
        String nom = creerVendeurVue.getNomVendeur();
        String prenom = creerVendeurVue.getPrenomVendeur();
        String motDePasse = creerVendeurVue.getMdpVendeur();

        if (nom.isEmpty() || prenom.isEmpty() || motDePasse.isEmpty()) {
            app.alertChampsVides();
        } else {
            try {
                administrateur.ajouteVendeurBD(new Vendeur(nom, prenom, motDePasse));
                app.setSceneAdmin();
                app.alertAjoutUtilisateur();
            } catch (CompteDejaPrisException e) {
                app.alertCompteDejaPris();
            } catch (Exception e) {
                app.alertErreur(e);
                System.err.println(e.getMessage());
            }
        }
    }
    
}
