package fr.saejava.controlleur;

import fr.saejava.modele.CommandeUnit;
import fr.saejava.vue.AjouterCommandeVue;
import fr.saejava.vue.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControlleurAjouteCommande implements EventHandler<ActionEvent> {

    private App app;
    private AjouterCommandeVue ajouterCommandeVue;

    public ControlleurAjouteCommande(App app, AjouterCommandeVue ajouterCommandeVue) {
        this.app = app;
        this.ajouterCommandeVue = ajouterCommandeVue;
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();

        if (btn.getText().equals("retour")) {
            try {
                app.setSceneCompteClient();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (btn.getText().equals("ajouter")) {
            try{
                int qte = ajouterCommandeVue.getQte();
                String livreTitre = ajouterCommandeVue.getCblivre();
            
                if (qte <= 0 || qte > app.getClient().qteDansMagasin(app.getClient().getLivreBDparTitre(livreTitre), ajouterCommandeVue.getCommande().getMagasin())) {
                    app.alertErreur();
                } else {
                    ajouterCommandeVue.getCommande().ajouterCommandeUnit(new CommandeUnit(app.getClient().getLivreBDparTitre(livreTitre), qte));
                    ajouterCommandeVue.majLivreDansCommande();
                }
            } catch (Exception e) {
                app.alertErreur();
                e.printStackTrace();
            }
        }
    }
    
}
