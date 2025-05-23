package fr.saejava;

import java.sql.SQLException;
import java.util.HashMap;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;

public class Executable {
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        ConnexionMySQL co = new ConnexionMySQL();
        // co.connecter(null, "DBmonet", "monet", "monet");
        co.connecter(null, "DBfranchet", "franchet", "franchet");


        Adiministrateur a = new Adiministrateur(0, null, null, null, null, co);
        
        Livre l1 = new Livre(0, null, 0, 0, 0);
        Livre l2 = new Livre(1, null, 0, 0, 0);
        Livre l3 = new Livre(2, null, 0, 0, 0);

        Client c1 = new Client(1, "rober", "lauran", "123446", null, null, null, null, co);
        // a.retireLivreBD(1);
        // a.ajouteClientBD(c1);

        Magasin baxMagasin = new Magasin(0, "BAX livres", "BAXville", new HashMap<>());

        // boolean b = c1.seConnecter("rober", "lauran", "123446");

        a.ajouteMagasinBD(baxMagasin);
        
        
        System.out.println("ça marche");
        
         
    }
}