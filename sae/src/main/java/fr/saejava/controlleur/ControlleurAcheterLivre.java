package fr.saejava.controlleur;

import fr.saejava.vue.App;

import java.time.LocalDate;
import java.util.List;

import fr.saejava.modele.Client;
import fr.saejava.modele.Commande;
import fr.saejava.modele.CommandeUnit;
import fr.saejava.modele.Livre;
import fr.saejava.modele.Magasin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControlleurAcheterLivre implements EventHandler<ActionEvent> {

    private App app;
    private Client client;

    public ControlleurAcheterLivre(App app, Client client) {
        this.app = app;
        this.client = client;
    }

    @Override
    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        String livreId = button.getId();

        try {
            Livre livre = client.getLivreBDparTitre(livreId);

            List<Magasin> lstMag = client.voirToutLesMagasin();

            Magasin mag = this.retrouvePremierMagasin(lstMag, livre);

            Commande commande =  new Commande(0, LocalDate.now().toString(), 'N', 'C', mag, client);
            commande.ajouterCommandeUnit(new CommandeUnit(livre, 1));

            app.setSceneAjouterCommandeVue(commande);


        } catch (Exception e){
            app.alertErreur(e);
        }
    }



    public Magasin retrouvePremierMagasin(List<Magasin> lstMag, Livre livre){
        try {
            for (Magasin mag : lstMag){
                if (client.qteDansMagasin(livre, mag) != 0){
                    return mag;
                }
            }
            return lstMag.get(0);
        } catch (Exception e){
            app.alertErreur(e);
        }
        return null;
    }
    
}
