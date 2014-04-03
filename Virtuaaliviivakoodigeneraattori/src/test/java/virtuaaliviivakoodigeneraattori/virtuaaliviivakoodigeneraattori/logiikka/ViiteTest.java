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
public class ViiteTest {

    Viite viite;
    Viite viite1;
    Viite viite2;
    Viite viite3;

    public ViiteTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        viite = new Viite("12345");
        viite1 = new Viite("12345");
        viite2 = new Viite("86851625961989");
        viite3 = new Viite("86851625961989");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void muodostaTarkisteellinenViiteToimiiOikein() {
        assertEquals(viite.muodostaTarkisteellinenViite("12345"), "123453");
        assertEquals(viite.muodostaTarkisteellinenViite("123"), "1232");
        assertEquals(viite.muodostaTarkisteellinenViite("1234567891011121314"), "12345678910111213142");
    }

    @Test
    public void getViiteTarkisteellaEtunollillaPituus20ToimiiOikein() {
        assertEquals(viite.viiteTarkisteellaEtunollillaPituus20(), "00000000000000123453");
        assertEquals(viite2.viiteTarkisteellaEtunollillaPituus20(), "00000868516259619897");
    }

    @Test
    public void onkoViiteKelvollinenToimiiOikein() {
        assertTrue(viite.onkoViiteKelvollinen("123"));
        assertTrue(viite.onkoViiteKelvollinen("123456"));
        assertTrue(viite.onkoViiteKelvollinen("1231231231231231231"));
        
        assertFalse(viite.onkoViiteKelvollinen("1"));
        assertFalse(viite.onkoViiteKelvollinen("12"));
        assertFalse(viite.onkoViiteKelvollinen("12312312312312312312"));
        assertFalse(viite.onkoViiteKelvollinen("123123123123123123124"));
        
        assertFalse(viite.onkoViiteKelvollinen("12a"));
        assertFalse(viite.onkoViiteKelvollinen("31t234"));
        assertFalse(viite.onkoViiteKelvollinen("1231231231231231-31"));
    }
    
    @Test
    public void equalsToimiiOikein() {
        assertTrue(viite.equals(viite1));
        
        viite.asetaUusiViiteViitteestaIlmanTarkistetta("56756756");
        assertFalse(viite.equals(viite1));
        viite.asetaUusiViiteViitteestaIlmanTarkistetta("12345");
        assertTrue(viite.equals(viite1));
    }
    
    @Test
    public void hashCodeToimiiOikein() {
        int expected = viite.hashCode();
        assertEquals(expected, viite1.hashCode());
        int expected1 = viite2.hashCode();
        assertEquals(expected1, viite3.hashCode());
    }
}
