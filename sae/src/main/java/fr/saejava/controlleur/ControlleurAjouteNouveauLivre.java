package fr.saejava.controlleur;

import fr.saejava.modele.Administrateur;
import fr.saejava.modele.Livre;
import fr.saejava.vue.AjouteLivreVue;
import fr.saejava.vue.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControlleurAjouteNouveauLivre implements EventHandler<ActionEvent> {

    private App app;
    private AjouteLivreVue vue;
    private Administrateur administrateur;

    public ControlleurAjouteNouveauLivre(App app, AjouteLivreVue vue) {
        this.app = app;
        this.vue = vue;
        this.administrateur = app.getAdministrateur();
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();

        if (btn.getText().equals("Ajouter")) {
            ajouterLivre();
        }
    }

    private void ajouterLivre() {
        String titre = vue.getTitreLivre();
        String nbpage = vue.getNbPages();
        String datepub = vue.getDatePublic();
        String prix = vue.getPrix();

        if (titre.isEmpty() || nbpage.isEmpty() || datepub.isEmpty() || prix.isEmpty()) {
            app.alertChampsVides();
        } else {
            try {
                if (administrateur.livreEstDansBD(titre)) {
                    app.alertInstanceDejaPrise();
                    return;
                }
                administrateur.ajouteLivreBD(new Livre(0, titre, Integer.parseInt(nbpage), Integer.parseInt(datepub), Double.parseDouble(prix), 0));
                app.setSceneAdmin();
                app.alertAjoutSucces();
            } catch (Exception e) {
                app.alertErreur(e);
                System.err.println(e.getMessage());
            }
        }
    }
    
}
