package fr.saejava;

import java.sql.SQLException;
import java.util.HashMap;

public class ExecutableTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception{
        ConnexionMySQL co = new ConnexionMySQL();
        // co.connecter(null, "DBmonet", "monet", "monet");
        co.connecter(null, "DBmonet", "root", "4dameorc");

        Adiministrateur a = new Adiministrateur(co);

        // Livre l1 = new Livre(1, "les dents de la mer", 169, 1990, 19.99);
        // Livre l2 = new Livre(2, "le magicien", 142, 2003, 15.00);
        // Livre l3 = new Livre(3, "fuir", 546, 2016, 45.05);

        // a.ajouteClientBD(new Client("a", "b", "c", null, null, null, 0.00));
        // Magasin baxMagasin = new Magasin(1, "babar", "BAXville", new HashMap<>());
        // Magasin centreMagasin = new Magasin(2, "centre librairie", "centre", new HashMap<>());
        // // a.retireLivreBD(1);
        // a.ajouteClientBD(new Client("a", "b", "c", "d", "e", "f", 0.0));
        // a.ajouteMagasinBD(baxMagasin);
        // a.ajouteMagasinBD(centreMagasin);
        // a.ajouteLivreBD(l1);
        // a.ajouteLivreBD(l2);
        // a.ajouteLivreBD(l3);
        // a.ajouteLivreBD(new Livre(5, "le grand bleu", 324, 1243, 87.00));
        // a.ajouteLivreDansMagasin(baxMagasin, l1, 5);
        // a.ajouteLivreDansMagasin(baxMagasin, l2, 5);
        // a.ajouteLivreDansMagasin(a.getMagasinBDparId("centre librairie"), a.getLivreBDparTitre("fuir"), 3);
        // a.ajouteLivreDansMagasin(baxMagasin, l3, 5);


        Vendeur v = new Vendeur(co);
        // Commande com = new Commande(0, "14", 'O', 'C', babar);
        // CommandeUnit comU = new CommandeUnit(l1, 2);
        
        // com.addCommandeUnit(comU);

        System.out.println(v.voirCommande());


        // c1.ajouteCommandeBD(com);

        // List<Commande> lstcom = c1.voirSesCommande();
        // System.out.println(lstcom);


        // System.out.println(c1.qteParMagasin(a.getLivreBDparTitre("fuir")));


        // System.out.println();

    }
}
