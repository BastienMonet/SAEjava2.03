package fr.saejava.controlleur;

import java.util.Optional;

import fr.saejava.modele.CommandeUnit;
import fr.saejava.vue.AjouterCommandeVueVendeur;
import fr.saejava.vue.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ControlleurAjouteCommandeVendeur implements EventHandler<ActionEvent> {

    private App app;
    private AjouterCommandeVueVendeur ajouterCommandeVueVendeur;

    public ControlleurAjouteCommandeVendeur(App app, AjouterCommandeVueVendeur ajouterCommandeVueVendeur) {
        this.app = app;
        this.ajouterCommandeVueVendeur = ajouterCommandeVueVendeur;
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();

        if (btn.getText().equals("retour")) {
            try {
                app.setVueVendeur();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (btn.getText().equals("ajouter")) {
            try{
                int qte = ajouterCommandeVueVendeur.getQte();
                String livreTitre = ajouterCommandeVueVendeur.getCblivre();
            
                if (qte <= 0 || qte > app.getClient().qteDansMagasin(app.getClient().getLivreBDparTitre(livreTitre), ajouterCommandeVueVendeur.getCommande().getMagasin())) {
                    app.alertQte();
                } else {
                    ajouterCommandeVueVendeur.getCommande().ajouterCommandeUnit(new CommandeUnit(app.getVendeur().getLivreBDparTitre(livreTitre), qte));
                    ajouterCommandeVueVendeur.majLivreDansCommande();
                }
            } catch (Exception e) {
                app.alertErreur(e);
                e.printStackTrace();
            }
        } else if(btn.getText().equals("finaliser la commande")) {

            try {
                if ( ! ajouterCommandeVueVendeur.getCommande().getListeCommandes().isEmpty()) {

                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Êtes-vous sûr de sauvegarder cette commande ?");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        app.getVendeur().ajouteUneCommandeBD(((ajouterCommandeVueVendeur.getCommande())));
                        app.setVueVendeur();
                    }


                    
                } else {
                    app.setVueVendeur();
                }
            
            } catch (Exception e) {
                app.alertErreur(e);
                e.printStackTrace();
            }
        } else if (btn.getText().equals("retirer")){
            ajouterCommandeVueVendeur.getCommande().removeCommandeUnit();
            ajouterCommandeVueVendeur.majLivreDansCommande();

        }
    }
    
}
