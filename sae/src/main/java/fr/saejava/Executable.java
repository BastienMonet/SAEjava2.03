package fr.saejava;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.checkerframework.checker.units.qual.C;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;

public class Executable {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception{
        ConnexionMySQL co = new ConnexionMySQL();
        // co.connecter(null, "DBmonet", "monet", "monet");
        co.connecter(null, "DBmonet", "root", "4dameorc");


        Adiministrateur a = new Adiministrateur(co);

        Livre l1 = new Livre(3, "les dents de la mer", 169, 1990, 19.99);
        Livre l2 = new Livre(2, "le magicien", 142, 2003, 15.00);
        Livre l3 = new Livre(1, "fuir", 546, 2016, 45.05);

        // a.ajouteClientBD(new Client("a", "b", "c", null, null, null, 0.00));
        Magasin baxMagasin = new Magasin(1, "BAX livres", "BAXville", new HashMap<>());
        Magasin centreMagasin = new Magasin(2, "centre librairie", "centre", new HashMap<>());
        // // a.retireLivreBD(1);
        // // a.ajouteClientBD(new Client("a", "b", "c", "d", "e", "f", 0.0));
        // a.ajouteMagasinBD(baxMagasin);
        // a.ajouteMagasinBD(centreMagasin);
        // a.ajouteLivreBD(l1);
        // a.ajouteLivreBD(l2);
        // a.ajouteLivreBD(l3);
        // a.ajouteLivreBD(new Livre(5, "le grand bleu", 324, 1243, 87.00));
        // a.ajouteLivreDansMagasin(baxMagasin, l1, 5);
        // a.ajouteLivreDansMagasin(baxMagasin, l2, 5);
        // a.ajouteLivreDansMagasin(centreMagasin, l3, 5);
        // a.ajouteLivreDansMagasin(baxMagasin, l3, 5);

















        boolean fini = false;
        boolean finiClient = false;
        while (!fini){
            System.out.println("bonjour et bienvenue, selectionner l'action de votre choix");
            System.out.println("1 - Se connecter en tant que client");
            System.out.println("2 - Se connecter en tant qu'admin");
            System.out.println("3 - quitter");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            switch (input){
                case ("1") :
                    System.out.println("entrer votre nom d'utilisateur");
                    BufferedReader r2 = new BufferedReader(new InputStreamReader(System.in));
                    String username = r2.readLine();
                    System.out.println("entrer votre prenom");
                    BufferedReader r3 = new BufferedReader(new InputStreamReader(System.in));
                    String lastname = r3.readLine();
                    System.out.println("entrer votre mot de passe");
                    BufferedReader r4 = new BufferedReader(new InputStreamReader(System.in));
                    String pwd = r4.readLine();
                    try{
                        Client c = new Client(co);
                        boolean seco = c.seConnecter(username, lastname, pwd);
                        if (seco == true){
                            finiClient = false;
                            System.out.println("connection reussi");
                            while (! finiClient){
                            System.out.println();
                            System.out.println("que souhaiter vous faire");
                            System.out.println("1 - consulter le catalogue");
                            System.out.println("2 - créer une commande");
                            System.out.println("3 - consulter ses commande");
                            System.out.println("4 - quitter");
                            BufferedReader r5 = new BufferedReader(new InputStreamReader(System.in));
                            String res5 = r5.readLine();
                            switch (res5) {
                                case ("1") :
                                    Set<Livre> lstLivre = c.onVousRecommande();
                                    for (Livre l : lstLivre){
                                        System.out.println(l);
                                    }
                                    break;
                                case ("3") :
                                    List<Commande> lstCommande = c.voirSesCommande();
                                    for (Commande c : lstCommande){
                                        System.out.println(c);
                                    }
                                    break;

                                case ("4") :
                                    finiClient = false;
                                    break;

                            }
                            }
                        } else {
                            System.out.println("echec de la connection");
                        }

                    } catch (SQLException e) {
                        System.out.println("la base de donnée a rencontrer un problème");

                    }
                    break;
                case ("2") :
                    System.out.println("WIP");
                case ("3") :
                    fini = true;

            }
                
        }
        
        

        // // c1.seConnecter("a", "b", "c");
        // Commande com = new Commande(0, "14", 'O', 'C', baxMagasin);
        // CommandeUnit comU = new CommandeUnit(l1, 2);
        
        // com.addCommandeUnit(comU);

        // c1.seConnecter("a", "b", "c");

        // c1.ajouteCommandeBD(com);


        // System.out.println(c1.onVousRecommande());


        // System.out.println(c1);



        // boolean b = c1.seConnecter("rober", "lauran", "123446");

        
        
        System.out.println("ça marche");
        
         
    }
}