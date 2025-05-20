package fr.saejava;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandeUnitTest
{
    @Test
    public void test()
    {
        Magasin m = new Magasin(0, null, null, null);
        assertEquals(m.getIdMag(),0);
    }

}
