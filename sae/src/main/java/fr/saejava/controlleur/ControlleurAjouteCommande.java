package fr.saejava.controlleur;

import java.util.Optional;

import fr.saejava.modele.CommandeUnit;
import fr.saejava.vue.AjouterCommandeVue;
import fr.saejava.vue.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

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
                    app.alertQte();
                } else {
                    ajouterCommandeVue.getCommande().ajouterCommandeUnit(new CommandeUnit(app.getClient().getLivreBDparTitre(livreTitre), qte));
                    ajouterCommandeVue.majLivreDansCommande();
                }
            } catch (Exception e) {
                app.alertErreur(e);
                e.printStackTrace();
            }
        } else if(btn.getText().equals("finaliser la commande")) {

            try {
                if ( ! ajouterCommandeVue.getCommande().getListeCommandes().isEmpty()) {

                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Êtes-vous sûr de sauvegarder cette commande ?");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        app.getClient().ajouteCommandeBD((ajouterCommandeVue.getCommande()));
                        app.setSceneCompteClient();
                    }


                    
                } else {
                    app.setSceneCompteClient();
                }
            
            } catch (Exception e) {
                app.alertErreur(e);
                e.printStackTrace();
            }
        }
    }
    
}
