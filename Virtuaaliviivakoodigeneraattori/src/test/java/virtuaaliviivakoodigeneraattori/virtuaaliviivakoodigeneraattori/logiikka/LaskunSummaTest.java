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
    LaskunSumma summa1;
    LaskunSumma summa2;
    LaskunSumma summa3;
    
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
        summa1 = new LaskunSumma(0,0);
        summa2 = new LaskunSumma(5,53);
        summa3 = new LaskunSumma(5,53);
    }
    
    @After
    public void tearDown() {
    }

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
    public void eurotStringEtunollillaPituusKuusiToimiiOikein(){
        assertEquals("000000", summa.eurotStringEtunollillaPituusKuusi());
        LaskunSumma summa2 = new LaskunSumma(5,0);
        assertEquals("000005", summa2.eurotStringEtunollillaPituusKuusi());
        LaskunSumma summa3 = new LaskunSumma(55,0);
        assertEquals("000055", summa3.eurotStringEtunollillaPituusKuusi());
        LaskunSumma summa4 = new LaskunSumma(55555,0);
        assertEquals("055555", summa4.eurotStringEtunollillaPituusKuusi());
        LaskunSumma summa5 = new LaskunSumma(555555,0);
        assertEquals("555555", summa5.eurotStringEtunollillaPituusKuusi());
    }
    
    @Test
    public void sentitStringEtunollillaPituusKaksiToimiiOikein(){
        assertEquals("00", summa.sentitStringEtunollillaPituusKaksi());
        LaskunSumma summa2 = new LaskunSumma(0,5);
        assertEquals("05", summa2.sentitStringEtunollillaPituusKaksi());
        LaskunSumma summa3 = new LaskunSumma(0,55);
        assertEquals("55", summa3.sentitStringEtunollillaPituusKaksi());       
    }
    
    @Test
    public void muutaEurotToimiiOikein(){
        assertEquals("000000", summa.eurotStringEtunollillaPituusKuusi());
        summa.setEurot(5);
        assertEquals("000005", summa.eurotStringEtunollillaPituusKuusi());
    }
    
    @Test
    public void muutaSentitToimiiOikein(){
        assertEquals("00", summa.sentitStringEtunollillaPituusKaksi());
        summa.setSentit(5);
        assertEquals("05", summa.sentitStringEtunollillaPituusKaksi());
    }
    
    @Test
    public void toStringToimiiOikein() {
        assertEquals("0.0", summa.toString());
        summa.setEurot(5);
        summa.setSentit(5);
        assertEquals("5.5", summa.toString());
        summa.setEurot(55);
        summa.setSentit(55);
        assertEquals("55.55", summa.toString());
        summa.setEurot(9);
        summa.setSentit(99);
        assertEquals("9.99", summa.toString());
        summa.setEurot(9999);
        summa.setSentit(99);
        assertEquals("9999.99", summa.toString());
    }
    
    @Test
    public void equalsToimiiOikein() {
        assertTrue(summa.equals(summa1));
        
        summa.setEurot(7);
        assertFalse(summa.equals(summa1));
        summa.setEurot(0);
        assertTrue(summa.equals(summa1));
        
        summa.setSentit(7);
        assertFalse(summa.equals(summa1));
        summa.setSentit(0);
        assertTrue(summa.equals(summa1));
    }
    
    @Test
    public void hashCodeToimiiOikein() {
        int expected = summa.hashCode();
        assertEquals(expected, summa1.hashCode());
        int expected1 = summa2.hashCode();
        assertEquals(expected1, summa3.hashCode());
    }
}
