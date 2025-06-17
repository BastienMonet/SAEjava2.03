package fr.saejava.controlleur;

import fr.saejava.modele.Administrateur;
import fr.saejava.modele.Magasin;
import fr.saejava.modele.Livre;
import fr.saejava.vue.App;
import fr.saejava.vue.GereStocksGlobauxVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControlleurGererStock implements EventHandler<ActionEvent> {

    private App app;
    private GereStocksGlobauxVue vue;
    private Administrateur administrateur;

    public ControlleurGererStock(App app, GereStocksGlobauxVue vue) {
        this.app = app;
        this.vue = vue;
        this.administrateur = app.getAdministrateur();
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();

        if (btn.getText().equals("Ajouter")) {
            try {
                Magasin magasin = administrateur.getMagasinBDparNom(vue.getNomMagasin());
                Livre livre = administrateur.getLivreBDparTitre(vue.getNomLivre());
                int qte = Integer.valueOf(vue.getQte());
                if (magasin == null || livre == null || qte <= 0) {
                    app.alertChampsVides();
                } else {
                    administrateur.ajouteLivreDansMagasin(magasin, livre, qte);
                    app.alertAjoutSucces();
                    app.setSceneAdmin();
                }
            } catch (Exception e) {
                app.alertErreur();
                e.printStackTrace();
            }
        } else if (btn.getText().equals("Retirer")) {
            try {
                Magasin magasin = administrateur.getMagasinBDparNom(vue.getNomMagasin());
                Livre livre = administrateur.getLivreBDparTitre(vue.getNomLivre());
                int qte = Integer.valueOf(vue.getQte());
                if (magasin == null || livre == null || qte <= 0) {
                    app.alertChampsVides();
                } else {
                    administrateur.retireLivreDansMagasin(magasin, livre, qte);
                    app.alertAjoutSucces();
                    app.setSceneAdmin();
                }
            } catch (Exception e) {
                app.alertErreur();
                e.printStackTrace();
            }
            
    }
    }
    
}
