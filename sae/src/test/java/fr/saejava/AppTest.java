package fr.saejava;

import fr.saejava.modele.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class AppTest
{
    @Test
    public void test()
    {
        Magasin m = new Magasin(1, null, null);
        assertEquals(m.getIdMag(),1);
    }

}
