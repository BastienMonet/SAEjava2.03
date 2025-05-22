package fr.saejava;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;

public class Executable {
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        ConnexionMySQL co = new ConnexionMySQL();
        // co.connecter(null, "DBmonet", "monet", "monet");
        co.connecter(null, "DBmonet", "root", "4dameorc");


        Adiministrateur a = new Adiministrateur(0, null, null, null, null, co);
        
        Livre l1 = new Livre(0, null, 0, 0, 0);
        Livre l2 = new Livre(1, null, 0, 0, 0);
        Livre l3 = new Livre(2, null, 0, 0, 0);
        a.ajouteLivreBD(l1);
        
        
        System.out.println("ça marche");
        
         
    }
}