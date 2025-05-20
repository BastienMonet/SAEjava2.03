package fr.saejava;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestMagasin {
    @Test
    public void test() {
        Magasin m = new Magasin(1, "Librairie", "Paris", null);
        assertEquals(m.getIdMag(), 1);
        assertEquals(m.getNomMag(), "Librairie");
        assertEquals(m.getVilleMag(), "Paris");
    }

    @Test
    public void testAjouteLivre() {
        // Magasin m = new Magasin(1, "Librairie", "Paris", null);
        // Livre livre = new Livre(2070754928, "La Recherche du temps perdu", 421, "1913", 20, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        // m.ajouteLivre(livre, 5);
        // assertTrue(m.getLivres().containsKey(livre));
        // assertEquals(m.getLivres().get(livre), Integer.valueOf(5));
    }

    @Test
    public void testIsDispo() {
        // Magasin m = new Magasin(1, "Librairie", "Paris", null);
        // Livre livre = new Livre(2070754928, "La Recherche du temps perdu", 421, "1913", 20, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        // m.ajouteLivre(livre, 5);
        // assertTrue(m.isDispo(livre));
    }

    @Test
    public void testRetireLivre() {
        // Magasin m = new Magasin(1, "Librairie", "Paris");
        // Livre livre = new Livre(2070754928, "La Recherche du temps perdu", 421, "1913", 20, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        // m.ajouteLivre(livre, 5);
        // m.retireLivre(livre,0);
        // assertEquals(m.getLivres().get(livre), Integer.valueOf(4));
    }
    
}
