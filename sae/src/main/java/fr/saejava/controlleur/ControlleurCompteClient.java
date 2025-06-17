package fr.saejava.controlleur;

import java.time.LocalDate;
import java.util.Optional;

import fr.saejava.modele.Client;
import fr.saejava.modele.Commande;
import fr.saejava.vue.App;
import fr.saejava.vue.CompteClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ControlleurCompteClient implements EventHandler<ActionEvent> {

    private App app;
    private CompteClient compteClient;

    public ControlleurCompteClient(App app, CompteClient compteClient) {
        this.app = app;
        this.compteClient = compteClient;
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();

        if (btn.getText().equals("deconnexion")) {
            System.out.println("Déconnexion de l'utilisateur...");
            app.setSceneConnexionUtil();
        } else if (btn.getText().equals("ajouter une commande")){

            if (compteClient.getChoixmag().equals("Choisissez un magasin")){
                System.out.println(compteClient.getChoixmag());
                app.alertChampsVides();
            } else {
                try {
                    char enligne ;
                    char livraison;

                    if (compteClient.getEnligneChoix() == "oui")
                        enligne = 'O';
                    else 
                        enligne = 'N';

                    if (compteClient.getLivraisonChoix() == "a domicile")
                        livraison = 'C';
                    else
                        livraison = 'M';
                    Commande commande = new Commande(0, LocalDate.now().toString(), enligne, livraison, app.getClient().getMagasinBDparNom(compteClient.getChoixmag()), app.getClient());
                    app.setSceneAjouterCommandeVue(commande);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (btn.getText().equals("X")){
            int numCom = Integer.valueOf(btn.getId());
            try {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Voulez vous vraiment supprimer cette commande?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    app.getClient().retireSaCommande(app.getClient().getCommande(numCom));
                    compteClient.majLesCommandes();
                    System.out.println("Suppression de la commande");
                } 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
