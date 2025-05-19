package fr.saejava;
import fr.saejava.Livre;

import static org.junit.Assert.assertTrue;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;


import org.junit.Test;

public class TestCommande {
    @Test
    public void testAjout() {
        

        CommandeUnit commandeUnit = new CommandeUnit(new Livre(123456789, "Test Book", 100, "2023-01-01", 20, null, null, null), 2);
        Commande commande = new Commande(1, "2023-01-01", true, 'L', "2023-01-02");

        commande.ajouterCommandeUnit(commandeUnit);
        assertEquals(1, commande.getListeCommandes().size());
    }

}
