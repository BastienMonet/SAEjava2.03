package fr.saejava.controlleur;

import fr.saejava.vue.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControlleurCompteClient implements EventHandler<ActionEvent> {

    private App app;

    public ControlleurCompteClient(App app) {
        this.app = app;
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();

        if (btn.getText().equals("deconnexion")) {
            System.out.println("Déconnexion de l'utilisateur...");
            app.setSceneConnexionUtil();
        }
    }
    
}
