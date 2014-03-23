/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Augustus58
 */
public class MerkkiTarkistinTest {

    MerkkiJaMerkkijonoTarkistin merkkitarkistin;
    Character merkki1;
    Character merkki2;
    Character merkki3;
    Character merkki4;
    Character merkki5;

    public MerkkiTarkistinTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        merkkitarkistin = new MerkkiJaMerkkijonoTarkistin();
        merkki1 = new Character('4');
        merkki2 = new Character('A');
        merkki3 = new Character('R');
        merkki4 = new Character('a');
        merkki5 = new Character('5');
    }

    @After
    public void tearDown() {
    }
    @Test
    public void onkoMerkkiNumeroToimiiOikein() {
        assertTrue(merkkitarkistin.onkoMerkkiNumero(merkki1));
        assertFalse(merkkitarkistin.onkoMerkkiNumero(merkki2));
    }

    @Test
    public void onkoMerkkiKirjainAZToimiiOikein() {
        assertTrue(merkkitarkistin.onkoMerkkiKirjainAZ(merkki3));
        assertFalse(merkkitarkistin.onkoMerkkiKirjainAZ(merkki4));
        assertFalse(merkkitarkistin.onkoMerkkiKirjainAZ(merkki5));
    }
}
