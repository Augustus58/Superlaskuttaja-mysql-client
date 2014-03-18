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
public class TilinumeroTest {
    
    String vaaraTilinumero1;
    String vaaraTilinumero2;
    String vaaraTilinumero3;
    String vaaraTilinumero4;
    String vaaraTilinumero5;
    String vaaraTilinumero6;
    String oikeaTilinumero1;
    String oikeaTilinumero2;
    Tilinumero tilinumero;
    
    public TilinumeroTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        vaaraTilinumero1 = "FI3816603001414664";
        vaaraTilinumero2 = "jdOJ3554JEIJI4444D";
        vaaraTilinumero3 = "q34crq3";
        vaaraTilinumero4 = "lkehmrfinhi9o3784yorxm983u4m9u87";
        vaaraTilinumero5 = "FI9780001505084521";
        vaaraTilinumero6 = "FI973";
        oikeaTilinumero1 = "FI3816603001014664";
        oikeaTilinumero2 = "FI9780001500084521";
        tilinumero = new Tilinumero("FI3816603001014664");
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
    public void tarkistaTilinumeroToimiiOikeinVaarallaTilinumerolla() {
        assertFalse(tilinumero.tarkistaTilinumero(vaaraTilinumero1));
        assertFalse(tilinumero.tarkistaTilinumero(vaaraTilinumero2));
        assertFalse(tilinumero.tarkistaTilinumero(vaaraTilinumero3));
        assertFalse(tilinumero.tarkistaTilinumero(vaaraTilinumero4));
        assertFalse(tilinumero.tarkistaTilinumero(vaaraTilinumero5));
        assertFalse(tilinumero.tarkistaTilinumero(vaaraTilinumero6));
    }
    
    @Test
    public void tarkistaTilinumeroToimiiOikeinOikeallaTilinumerolla() {
        assertTrue(tilinumero.tarkistaTilinumero(oikeaTilinumero1));
        assertTrue(tilinumero.tarkistaTilinumero(oikeaTilinumero2));   
    }
    
}
