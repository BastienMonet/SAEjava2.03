package fr.saejava.controlleur;

import fr.saejava.modele.Magasin;
import fr.saejava.vue.AjouteLibVue;
import fr.saejava.vue.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControlleurAjoutNouvelleLib implements EventHandler<ActionEvent> {

    private App app;
    private AjouteLibVue vue;

    public ControlleurAjoutNouvelleLib(App app, AjouteLibVue vue) {
        this.app = app;
        this.vue = vue;
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
                app.getAdministrateur().ajouteMagasinBD(new Magasin(0, nomLib, adresseLib));
                app.setSceneAdmin();
                app.alertAjoutSucces();
            } catch (Exception e) {
                app.alertErreur();
                System.err.println(e.getMessage());
            }
        }
    }
    
}
