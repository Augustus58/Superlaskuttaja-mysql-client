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
public class AsiakasTest {
    Asiakas asiakas;
    Asiakas asiakas1;
    Asiakas asiakas2;
    Asiakas asiakas3;
    
    public AsiakasTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // Luodaan asiakas, jonka tietojen pitäisi mennä läpi tarkistuksista.
        asiakas = new Asiakas("123", "Elmeri Asiakas", "Elmerinkatu 17 B 45", "00345", "Elmericity", 100000);
        asiakas1 = new Asiakas("123", "Elmeri Asiakas", "Elmerinkatu 17 B 45", "00345", "Elmericity", 100000);
        asiakas2 = new Asiakas("1234", "Elmeri Kantola", "Katu 75", "00347", "Nuorgam", 10000);
        asiakas3 = new Asiakas("1234", "Elmeri Kantola", "Katu 75", "00347", "Nuorgam", 10000);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void onkoNimiOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(asiakas.onkoNimiOikeanlainen());
        asiakas.setNimi("ä");
        assertTrue(asiakas.onkoNimiOikeanlainen());
    }
    
    @Test
    public void onkoNimiOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        asiakas.setNimi("");
        assertFalse(asiakas.onkoNimiOikeanlainen());
        asiakas.setNimi(" ");
        assertFalse(asiakas.onkoNimiOikeanlainen());
        asiakas.setNimi("          ");
        assertFalse(asiakas.onkoNimiOikeanlainen());
    }
    
    @Test
    public void onkoKaupunkiOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(asiakas.onkoKaupunkiOikeanlainen());
        asiakas.setKaupunki("ä");
        assertTrue(asiakas.onkoKaupunkiOikeanlainen());
    }
    
    @Test
    public void onkoKaupunkiOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        asiakas.setKaupunki("");
        assertFalse(asiakas.onkoKaupunkiOikeanlainen());
        asiakas.setKaupunki(" ");
        assertFalse(asiakas.onkoKaupunkiOikeanlainen());
        asiakas.setKaupunki("          ");
        assertFalse(asiakas.onkoKaupunkiOikeanlainen());
    }
    
    @Test
    public void onkoKatuosoiteOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(asiakas.onkoKatuosoiteOikeanlainen());
        asiakas.setKatuosoite("ä");
        assertTrue(asiakas.onkoKatuosoiteOikeanlainen());
    }
    
    @Test
    public void onkoKatuosoiteOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        asiakas.setKatuosoite("");
        assertFalse(asiakas.onkoKatuosoiteOikeanlainen());
        asiakas.setKatuosoite(" ");
        assertFalse(asiakas.onkoKatuosoiteOikeanlainen());
        asiakas.setKatuosoite("          ");
        assertFalse(asiakas.onkoKatuosoiteOikeanlainen());
    }
    
    @Test
    public void onkoAsiakasnumeroOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(asiakas.onkoAsiakasnumeroOikeanlainen());
        asiakas.setAsiakasnumero("0");
        assertTrue(asiakas.onkoAsiakasnumeroOikeanlainen());
        asiakas.setAsiakasnumero("007");
        assertTrue(asiakas.onkoAsiakasnumeroOikeanlainen());
    }
    
    @Test
    public void onkoAsiakasnumeroOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        asiakas.setAsiakasnumero("");
        assertFalse(asiakas.onkoAsiakasnumeroOikeanlainen());
        asiakas.setAsiakasnumero(" ");
        assertFalse(asiakas.onkoAsiakasnumeroOikeanlainen());
        asiakas.setAsiakasnumero("          ");
        assertFalse(asiakas.onkoAsiakasnumeroOikeanlainen());
        asiakas.setAsiakasnumero("ä");
        assertFalse(asiakas.onkoAsiakasnumeroOikeanlainen());
        asiakas.setAsiakasnumero(" 00");
        assertFalse(asiakas.onkoAsiakasnumeroOikeanlainen());
        asiakas.setAsiakasnumero("834768374jgi");
        assertFalse(asiakas.onkoAsiakasnumeroOikeanlainen());
        asiakas.setAsiakasnumero("12312 123");
        assertFalse(asiakas.onkoAsiakasnumeroOikeanlainen());
        asiakas.setAsiakasnumero("232-2322");
        assertFalse(asiakas.onkoAsiakasnumeroOikeanlainen());
    }
    
    @Test
    public void onkoPostinumeroOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(asiakas.onkoPostinumeroOikeanlainen());
        asiakas.setPostinumero("0");
        assertTrue(asiakas.onkoPostinumeroOikeanlainen());
    }
    
    @Test
    public void onkoPostinumeroOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        asiakas.setPostinumero("");
        assertFalse(asiakas.onkoPostinumeroOikeanlainen());
        asiakas.setPostinumero(" ");
        assertFalse(asiakas.onkoPostinumeroOikeanlainen());
        asiakas.setPostinumero("          ");
        assertFalse(asiakas.onkoPostinumeroOikeanlainen());
        asiakas.setPostinumero("a");
        assertFalse(asiakas.onkoPostinumeroOikeanlainen());
        asiakas.setPostinumero("1231k3123");
        assertFalse(asiakas.onkoPostinumeroOikeanlainen());
        asiakas.setPostinumero("asd");
        assertFalse(asiakas.onkoPostinumeroOikeanlainen());
    }
    
    @Test
    public void onkoLaskujaLahetettyOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(asiakas.onkoLaskujaLahetettyOikeanlainen());
        asiakas.setLaskujaLahetetty(0);
        assertTrue(asiakas.onkoLaskujaLahetettyOikeanlainen());
        asiakas.setLaskujaLahetetty(1);
        assertTrue(asiakas.onkoLaskujaLahetettyOikeanlainen());
    }
    
    @Test
    public void onkoLaskujaLahetettyOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        asiakas.setLaskujaLahetetty(-1);
        assertFalse(asiakas.onkoLaskujaLahetettyOikeanlainen());
        asiakas.setLaskujaLahetetty(-5);
        assertFalse(asiakas.onkoLaskujaLahetettyOikeanlainen());
        asiakas.setLaskujaLahetetty(-5000);
        assertFalse(asiakas.onkoLaskujaLahetettyOikeanlainen());
    }

    @Test
    public void onkoTiedotOikeanlaisetToimiiOikeinOikeillaTiedoilla() {
        assertTrue(asiakas.onkoTiedotOikeanlaiset());
    }
    
    @Test
    public void onkoTiedotOikeanlaisetToimiiOikeinVaarillaTiedoilla() {
        asiakas.setNimi("");
        assertFalse(asiakas.onkoTiedotOikeanlaiset());
        asiakas.setNimi("Elmeri");
        assertTrue(asiakas.onkoTiedotOikeanlaiset());
        
        asiakas.setKatuosoite("");
        assertFalse(asiakas.onkoTiedotOikeanlaiset());
        asiakas.setKatuosoite("Katu 1 A 86");
        assertTrue(asiakas.onkoTiedotOikeanlaiset());
        
        asiakas.setPostinumero("r4");
        assertFalse(asiakas.onkoTiedotOikeanlaiset());
        asiakas.setPostinumero("00123");
        assertTrue(asiakas.onkoTiedotOikeanlaiset());
        
        asiakas.setKaupunki("");
        assertFalse(asiakas.onkoTiedotOikeanlaiset());
        asiakas.setKaupunki("Elmericity");
        assertTrue(asiakas.onkoTiedotOikeanlaiset());
        
        asiakas.setLaskujaLahetetty(-300);
        assertFalse(asiakas.onkoTiedotOikeanlaiset());
        asiakas.setLaskujaLahetetty(300);
        assertTrue(asiakas.onkoTiedotOikeanlaiset());
        
        asiakas.setAsiakasnumero("f");
        assertFalse(asiakas.onkoTiedotOikeanlaiset());
        asiakas.setAsiakasnumero("1");
        assertTrue(asiakas.onkoTiedotOikeanlaiset());
    }
    
    @Test
    public void equalsToimiiOikein() {
        assertTrue(asiakas.equals(asiakas1));
        
        asiakas1.setNimi("Elmeri Iivola");
        assertFalse(asiakas.equals(asiakas1));
        asiakas1.setNimi(asiakas.getNimi());
        assertTrue(asiakas.equals(asiakas1));
        
        asiakas1.setKatuosoite("Helmerinkatu 7");
        assertFalse(asiakas.equals(asiakas1));
        asiakas1.setKatuosoite(asiakas.getKatuosoite());
        assertTrue(asiakas.equals(asiakas1));
        
        asiakas1.setPostinumero("00987");
        assertFalse(asiakas.equals(asiakas1));
        asiakas1.setPostinumero(asiakas.getPostinumero());
        assertTrue(asiakas.equals(asiakas1));
        
        asiakas1.setKaupunki("Nuorgam");
        assertFalse(asiakas.equals(asiakas1));
        asiakas1.setKaupunki(asiakas.getKaupunki());
        assertTrue(asiakas.equals(asiakas1));
        
        asiakas1.setLaskujaLahetetty(700);
        assertFalse(asiakas.equals(asiakas1));
        asiakas1.setLaskujaLahetetty(asiakas.getLaskujaLahetetty());
        assertTrue(asiakas.equals(asiakas1));
        
        asiakas1.setAsiakasnumero("1234");
        assertFalse(asiakas.equals(asiakas1));
        asiakas1.setAsiakasnumero(asiakas.getAsiakasnumero());
        assertTrue(asiakas.equals(asiakas1));
        
    }
    
    @Test
    public void hashCodeToimiiOikein() {
        int expected = asiakas.hashCode();
        assertEquals(expected, asiakas1.hashCode());
        int expected1 = asiakas2.hashCode();
        assertEquals(expected1, asiakas3.hashCode());
    }
}
