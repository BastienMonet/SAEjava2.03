package fr.saejava.controlleur;

import fr.saejava.modele.Administrateur;
import fr.saejava.modele.Magasin;
import fr.saejava.vue.AjouteLibVue;
import fr.saejava.vue.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControlleurAjoutNouvelleLib implements EventHandler<ActionEvent> {

    private App app;
    private AjouteLibVue vue;

    private Administrateur administrateur;

    public ControlleurAjoutNouvelleLib(App app, AjouteLibVue vue) {
        this.app = app;
        this.vue = vue;
        this.administrateur = app.getAdministrateur();
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();

        if (btn.getText().equals("Ajouter")) {
            ajouterLibrairie();
        }
    }

    private void ajouterLibrairie() {
        String nomLib = vue.getNomMagasin();
        String adresseLib = vue.getNomCity();

        if (nomLib.isEmpty() || adresseLib.isEmpty()) {
            app.alertChampsVides();
        } else {
            try {

                if (administrateur.magasinEstDansBD(nomLib)) {
                    app.alertInstanceDejaPrise();
                    return;
                }
                app.getAdministrateur().ajouteMagasinBD(new Magasin(0, nomLib, adresseLib));
                app.setSceneAdmin();
                app.alertAjoutSucces();
            } catch (Exception e) {
                app.alertErreur(e);
                System.err.println(e.getMessage());
            }
        }
    }
    
}
