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
public class LaskunSummaTest {
    
    LaskunSumma summa;
    
    public LaskunSummaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        summa = new LaskunSumma(0,0);
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
    public void tarkistaEurotToimiiOikein(){
        assertFalse(summa.tarkistaEurot(-1));        
        assertTrue(summa.tarkistaEurot(0));
        assertTrue(summa.tarkistaEurot(5));
        assertTrue(summa.tarkistaEurot(999999));
        assertFalse(summa.tarkistaEurot(1000000));
    }
    
    @Test
    public void tarkistaSentitToimiiOikein(){
        assertFalse(summa.tarkistaSentit(-1));        
        assertTrue(summa.tarkistaSentit(0));
        assertTrue(summa.tarkistaSentit(5));
        assertTrue(summa.tarkistaSentit(99));
        assertFalse(summa.tarkistaSentit(100));
    }
    
    @Test
    public void getEurotStringEtunollillaPituusKuusiToimiiOikein(){
        assertEquals("000000", summa.getEurotStringEtunollillaPituusKuusi());
        LaskunSumma summa2 = new LaskunSumma(5,0);
        assertEquals("000005", summa2.getEurotStringEtunollillaPituusKuusi());
        LaskunSumma summa3 = new LaskunSumma(55,0);
        assertEquals("000055", summa3.getEurotStringEtunollillaPituusKuusi());
        LaskunSumma summa4 = new LaskunSumma(55555,0);
        assertEquals("055555", summa4.getEurotStringEtunollillaPituusKuusi());
        LaskunSumma summa5 = new LaskunSumma(555555,0);
        assertEquals("555555", summa5.getEurotStringEtunollillaPituusKuusi());
    }
    
    @Test
    public void getSentitStringEtunollillaPituusKaksiToimiiOikein(){
        assertEquals("00", summa.getSentitStringEtunollillaPituusKaksi());
        LaskunSumma summa2 = new LaskunSumma(0,5);
        assertEquals("05", summa2.getSentitStringEtunollillaPituusKaksi());
        LaskunSumma summa3 = new LaskunSumma(0,55);
        assertEquals("55", summa3.getSentitStringEtunollillaPituusKaksi());       
    }
    
    @Test
    public void muutaEurotToimiiOikein(){
        assertEquals("000000", summa.getEurotStringEtunollillaPituusKuusi());
        summa.muutaEurot(5);
        assertEquals("000005", summa.getEurotStringEtunollillaPituusKuusi());
    }
    
    @Test
    public void muutaSentitToimiiOikein(){
        assertEquals("00", summa.getSentitStringEtunollillaPituusKaksi());
        summa.muutaSentit(5);
        assertEquals("05", summa.getSentitStringEtunollillaPituusKaksi());
    }
    
}
