package fr.saejava;
import fr.saejava.Livre;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;


import org.junit.Test;

public class TestCommande {

    @Test
    public void testAjout() {
        

        CommandeUnit commandeUnit = new CommandeUnit(new Livre(123456789, "Test Book", 100, 1, 20), 2);
        Commande commande = new Commande(1, "2023-01-01", 'a', 'L', null);

        commande.ajouterCommandeUnit(commandeUnit);
        assertEquals(1, commande.getListeCommandes().size());
    }


    @Test
    public void TestCommander() throws IllegalArgumentException, Exception{
        Map<Livre, Integer> lesLivre = new HashMap<>();

        Livre l1 = new Livre(1, "a", 0, 1, 0);
        Livre l2 = new Livre(2, "b", 0, 1, 0);
        Livre l3 = new Livre(3, "c", 0, 1, 0);

        lesLivre.put(l1, 4);
        lesLivre.put(l2, 2);
        lesLivre.put(l3, 0);

        Magasin mag = new Magasin(1, "mag1", null, lesLivre);

        CommandeUnit comU1 = new CommandeUnit(l1, 2);
        CommandeUnit comU2 = new CommandeUnit(l2, 3);

        Commande com = new Commande(0, null, 'a', 'a', mag);
        com.addCommandeUnit(comU1);

        Commande com2 = new Commande(0, null, 'a', 'a', mag);
        com2.addCommandeUnit(comU2);


        com.commander();
        // com2.commander(); -> L'exception fonctionne

        int res = lesLivre.get(l1);

        assertEquals(2, res);

        com.renvoyer();

        int res2 = lesLivre.get(l1);

        assertEquals(4, res2);
        
        Set<Livre> set = lesLivre.keySet();
        System.out.println(mag);
    }

}
