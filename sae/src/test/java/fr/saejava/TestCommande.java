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
        

        CommandeUnit commandeUnit = new CommandeUnit(new Livre(123456789, "Test Book", 100, 1, 20, 0), 2);
        Commande commande = new Commande(1, "2023-01-01", 'a', 'L', null);

        commande.ajouterCommandeUnit(commandeUnit);
        assertEquals(1, commande.getListeCommandes().size());
    }

}
