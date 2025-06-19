package fr.saejava.controlleur;

import java.time.LocalDate;
import java.util.Optional;

import fr.saejava.modele.Client;

import fr.saejava.modele.Commande;
import fr.saejava.modele.Magasin;
import fr.saejava.vue.App;
import fr.saejava.vue.VendeurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ControlleurCompteVendeur implements EventHandler<ActionEvent> {


    private App app;
    private VendeurVue vue;

    public ControlleurCompteVendeur(App app, VendeurVue vue) {
        this.app = app;
        this.vue = vue;
    }
    @Override
    public void handle(ActionEvent event) {

        Button btn = (Button) event.getSource();

        if (btn.getText().equals("X")) {
            int numCom = Integer.valueOf(btn.getId());
            try {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Voulez vous vraiment supprimer cette commande?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    app.getVendeur().retireCommande(numCom);
                    vue.majLesCommandes();
                    System.out.println("Suppression de la commande");
                } 
            } catch (Exception e) {
                e.printStackTrace();
            }
        } if (btn.getText().equals("Ajouter une commande")) {
            try {

                String nomMagasin = vue.getChoixMag();

                String nomUtilisateur = vue.getChoixUtil();

                if (nomMagasin == null || nomUtilisateur == null || nomMagasin.isEmpty() || nomUtilisateur.isEmpty()) {
                    app.alertChampsVides();
                    return;
                }

                char enligne ;
                    char livraison;

                    if (vue.getEnligneChoix() == "Oui")
                        enligne = 'O';
                    else 
                        enligne = 'N';

                    if (vue.getLivraisonChoix() == "a domicile")
                        livraison = 'C';
                    else
                        livraison = 'M';
                

                Magasin magasin = app.getVendeur().getMagasinBDparNom(nomMagasin);

                Client client = app.getVendeur().getClientParNom(nomUtilisateur);

                Commande commande = new Commande(0, LocalDate.now().toString() , enligne, livraison, magasin, client);

                app.setSceneAjouterCommandeVue(commande);




            } catch (Exception e) {
                app.alertErreur(e);
            }
        }   
    }
    
}
