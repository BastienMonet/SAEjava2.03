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
        co.connecter(null, "DBmonet", "root", "4dameorc");


        Adiministrateur a = new Adiministrateur(co);
        
        Livre l1 = new Livre(0, null, 0, 0, 0);
        Livre l2 = new Livre(1, null, 0, 0, 0);
        Livre l3 = new Livre(2, null, 0, 0, 0);

        Client c1 = new Client(co);
        // a.retireLivreBD(1);
        // a.ajouteClientBD(new Client("a", "b", "c", "d", "e", "f", 0.0));

        c1.seConnecter("a", "b", "c");
        System.out.println(c1);

        // Magasin baxMagasin = new Magasin(0, "BAX livres", "BAXville", new HashMap<>());

        // boolean b = c1.seConnecter("rober", "lauran", "123446");

        // a.ajouteMagasinBD(baxMagasin);
        
        
        System.out.println("ça marche");
        
         
    }
}