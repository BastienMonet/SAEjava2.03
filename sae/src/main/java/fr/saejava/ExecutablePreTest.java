package fr.saejava;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;


public class ExecutablePreTest {


    public static void main(String[] args) throws Exception{
        System.out.println("entrer votre nom de base");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nomb = reader.readLine(); 
        System.out.println("entrer le nom du login");
        String noml = reader.readLine(); 
        System.out.println("entrer le mot de passe");
        String pwd = reader.readLine(); 
        try {
           
            ConnexionMySQL co = new ConnexionMySQL();
            // co.connecter(null, "DBmonet", "monet", "monet");
            co.connecter(null, nomb, noml, pwd);

            Administrateur a = new Administrateur(co);

            Client c = new Client(co);

            c.seConnecter("a", "b", "c");
            
            a.ajouteAdminBD(new Administrateur("a", "b", "c"));

            a.ajouteClientBD(new Client("d", "e", "f", "quelque part", "012345", null, 0.00));
            
            boolean res = a.seConnecter("john", "vincent", "1234");

            Livre l1 = new Livre(1, "les dents de la mer", 169, 1990, 19.99, 0);
            Livre l2 = new Livre(2, "le magicien", 142, 2003, 15.00, 0);
            Livre l3 = new Livre(3, "fuir", 546, 2016, 45.05, 0);

            
            Magasin m1 = new Magasin(1, "m1", "BAXville");
            Magasin m2 = new Magasin(2, "m2", "centre");
            // a.retireLivreBD(1);
            a.ajouteClientBD(new Client("h", "i", "j", "d", "e", "f", 0.0));
            a.ajouteMagasinBD(m1);
            a.ajouteMagasinBD(m2);
            a.ajouteLivreBD(l1);
            a.ajouteLivreBD(l2);
            a.ajouteLivreBD(l3);
            a.ajouteLivreBD(new Livre(5, "le grand bleu", 324, 1243, 87.00, 0));
            a.ajouteLivreDansMagasin(m1, l1, 5);
            a.ajouteLivreDansMagasin(m1, l2, 5);
            a.ajouteLivreDansMagasin(a.getMagasinBDparNom("m2"), a.getLivreBDparTitre("fuir"), 3);
            a.ajouteLivreDansMagasin(m1, l3, 5);

            co.close();
        } catch (Exception e){
            System.out.println("problème de connection avec la base, assurer vous bien\n d'avoir le bon driver jdbc:mysql://servinfo-maria:3306/ et changer le dans ConnexionMySQL.java");
        }

        

        

        

    }
}
