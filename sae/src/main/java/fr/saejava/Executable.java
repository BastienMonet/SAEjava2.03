package fr.saejava;

import java.sql.SQLException;
import java.util.HashMap;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;

public class Executable {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception{
        ConnexionMySQL co = new ConnexionMySQL();
        // co.connecter(null, "DBmonet", "monet", "monet");
        co.connecter(null, "DBmonet", "root", "4dameorc");


        Adiministrateur a = new Adiministrateur(co);
        
        Livre l1 = new Livre(1, null, 0, 0, 0);
        Livre l2 = new Livre(1, null, 0, 0, 0);
        Livre l3 = new Livre(1, null, 0, 0, 0);
        Client c1 = new Client(co);
        Magasin baxMagasin = new Magasin(1, "BAX livres", "BAXville", new HashMap<>());
        // a.retireLivreBD(1);
        // a.ajouteClientBD(new Client("a", "b", "c", "d", "e", "f", 0.0));
        // a.ajouteMagasinBD(baxMagasin);
        // a.ajouteLivreBD(l1);

        // c1.seConnecter("a", "b", "c");
        Commande com = new Commande(0, "14", 'O', 'C', baxMagasin);
        CommandeUnit comU = new CommandeUnit(l1, 2);
        
        // com.addCommandeUnit(comU);

        c1.seConnecter("a", "b", "c");

        // c1.ajouteCommandeBD(com);

        // a.ajouteLivreDansMagasin(baxMagasin, l3, 3);

        // a.retireLivreDansMagasin(baxMagasin, l3, 5);

        System.out.println(c1.onVousRecommande());


        System.out.println(c1);



        // boolean b = c1.seConnecter("rober", "lauran", "123446");

        
        
        System.out.println("ça marche");
        
         
    }
}