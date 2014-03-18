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
    Viite viite2;

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
        viite2 = new Viite("86851625961989");
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void muodostaTarkisteellinenViiteToimiiOikein() {
        assertEquals(viite.muodostaTarkisteellinenViite("12345"), "123453");
        assertEquals(viite.muodostaTarkisteellinenViite("123"), "1232");
        assertEquals(viite.muodostaTarkisteellinenViite("1234567891011121314"), "12345678910111213142");
    }
    
    @Test
    public void getViiteTarkisteellaEtunollillaPituus20ToimiiOikein() {
        assertEquals(viite.getViiteTarkisteellaEtunollillaPituus20(), "00000000000000123453");
        assertEquals(viite2.getViiteTarkisteellaEtunollillaPituus20(), "00000868516259619897");
    }

}
