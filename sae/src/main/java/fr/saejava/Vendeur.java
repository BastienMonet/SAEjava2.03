package fr.saejava;

import java.sql.*;

public class Vendeur extends Utilisateur {
    
    public Vendeur(int idUtil, String nomUtil, String prenomUtil, String pwd, Catalogue cat, ConnexionMySQL laConnexion){
        super(idUtil, nomUtil, prenomUtil, pwd, cat, laConnexion);
    }

    @Override
    public boolean seConnecter(String nom, String prenom, String pwd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seConnecter'");
    }




}
