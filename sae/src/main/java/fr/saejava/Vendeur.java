package fr.saejava;

import java.sql.*;

public class Vendeur extends Utilisateur {
    
    public Vendeur(ConnexionMySQL laConnexion){
        super(laConnexion);
    }


    public Vendeur(String nomUtil, String prenomUtil, String pwd){
        super(nomUtil, prenomUtil, pwd);
    }

    @Override
    public boolean seConnecter(String nom, String prenom, String pwd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seConnecter'");
    }


    




}
