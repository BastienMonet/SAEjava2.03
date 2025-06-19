package fr.saejava.controlleur;

import java.util.Optional;

import fr.saejava.modele.CommandeUnit;
import fr.saejava.vue.AjouterCommandeVue;
import fr.saejava.vue.App;
import fr.saejava.vue.ModifierCommandeVue;
import fr.saejava.vue.ModifierCommandeVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ControlleurModifierCommande implements EventHandler<ActionEvent> {

    private App app;
    private ModifierCommandeVue modifierCommandeVue;

    public ControlleurModifierCommande(App app, ModifierCommandeVue modifierCommandeVue) {
        this.app = app;
        this.modifierCommandeVue = modifierCommandeVue;
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
                int qte = modifierCommandeVue.getQte();
                String livreTitre = modifierCommandeVue.getCblivre();
            
                if (qte <= 0 || qte > app.getClient().qteDansMagasin(app.getClient().getLivreBDparTitre(livreTitre), modifierCommandeVue.getCommande().getMagasin())) {
                    app.alertQte();
                } else {
                    modifierCommandeVue.getCommande().ajouterCommandeUnit(new CommandeUnit(app.getClient().getLivreBDparTitre(livreTitre), qte));
                    modifierCommandeVue.majLivreDansCommande();
                }
            } catch (Exception e) {
                app.alertErreur(e);
                e.printStackTrace();
            }
        } else if(btn.getText().equals("modifier la commande")) {

            try {
                if ( ! modifierCommandeVue.getCommande().getListeCommandes().isEmpty()) {

                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Êtes-vous sûr de sauvegarder cette commande ?");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        app.getClient().updateSaCommande(modifierCommandeVue.getCommande());
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
