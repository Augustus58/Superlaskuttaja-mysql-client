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
public class LaskuttajaTest {

    Tilinumero tilinumero;
    Laskuttaja laskuttaja;

    public LaskuttajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tilinumero = new Tilinumero("FI3936363002092492", "Superpankki", "FISUFIHH");
        laskuttaja = new Laskuttaja("Erkki Laskuttaja", "Laskuttajankatu 17 A 7", "00549", "Laskuttajacity", "Superyhtiöt", "FI2343-34343", tilinumero, "035-5554444", "erkki.laskuttaja@superyhtiot.fi", 50345);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void onkoNimiOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoNimiOikeanlainen());
        laskuttaja.setNimi("ä");
        assertTrue(laskuttaja.onkoNimiOikeanlainen());
    }

    @Test
    public void onkoNimiOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setNimi("");
        assertFalse(laskuttaja.onkoNimiOikeanlainen());
        laskuttaja.setNimi(" ");
        assertFalse(laskuttaja.onkoNimiOikeanlainen());
        laskuttaja.setNimi("          ");
        assertFalse(laskuttaja.onkoNimiOikeanlainen());
    }

    @Test
    public void onkoKatuosoiteOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoKatuosoiteOikeanlainen());
        laskuttaja.setKatuosoite("ä");
        assertTrue(laskuttaja.onkoKatuosoiteOikeanlainen());
    }

    @Test
    public void onkoKatuosoiteOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setKatuosoite("");
        assertFalse(laskuttaja.onkoKatuosoiteOikeanlainen());
        laskuttaja.setKatuosoite(" ");
        assertFalse(laskuttaja.onkoKatuosoiteOikeanlainen());
        laskuttaja.setKatuosoite("          ");
        assertFalse(laskuttaja.onkoKatuosoiteOikeanlainen());
    }

    @Test
    public void onkoPostinumeroOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoPostinumeroOikeanlainen());
        laskuttaja.setPostinumero("0");
        assertTrue(laskuttaja.onkoPostinumeroOikeanlainen());
    }

    @Test
    public void onkoPostinumeroOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setPostinumero("");
        assertFalse(laskuttaja.onkoPostinumeroOikeanlainen());
        laskuttaja.setPostinumero(" ");
        assertFalse(laskuttaja.onkoPostinumeroOikeanlainen());
        laskuttaja.setPostinumero("          ");
        assertFalse(laskuttaja.onkoPostinumeroOikeanlainen());
        laskuttaja.setPostinumero("a");
        assertFalse(laskuttaja.onkoPostinumeroOikeanlainen());
        laskuttaja.setPostinumero("1231k3123");
        assertFalse(laskuttaja.onkoPostinumeroOikeanlainen());
        laskuttaja.setPostinumero("asd");
        assertFalse(laskuttaja.onkoPostinumeroOikeanlainen());
    }

    @Test
    public void onkoKaupunkiOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoKaupunkiOikeanlainen());
        laskuttaja.setKaupunki("ä");
        assertTrue(laskuttaja.onkoKaupunkiOikeanlainen());
    }

    @Test
    public void onkoKaupunkiOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setKaupunki("");
        assertFalse(laskuttaja.onkoKaupunkiOikeanlainen());
        laskuttaja.setKaupunki(" ");
        assertFalse(laskuttaja.onkoKaupunkiOikeanlainen());
        laskuttaja.setKaupunki("          ");
        assertFalse(laskuttaja.onkoKaupunkiOikeanlainen());
    }

    @Test
    public void onkoYrityksenNimiOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoYrityksenNimiOikeanlainen());
        laskuttaja.setYrityksenNimi("ä");
        assertTrue(laskuttaja.onkoYrityksenNimiOikeanlainen());
    }

    @Test
    public void onkoYrityksenNimiOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setYrityksenNimi("");
        assertFalse(laskuttaja.onkoYrityksenNimiOikeanlainen());
        laskuttaja.setYrityksenNimi(" ");
        assertFalse(laskuttaja.onkoYrityksenNimiOikeanlainen());
        laskuttaja.setYrityksenNimi("          ");
        assertFalse(laskuttaja.onkoYrityksenNimiOikeanlainen());
    }

    @Test
    public void onkoAlvOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoAlvOikeanlainen());
        laskuttaja.setAlv("ä");
        assertTrue(laskuttaja.onkoAlvOikeanlainen());
    }

    @Test
    public void onkoAlvOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setAlv("");
        assertFalse(laskuttaja.onkoAlvOikeanlainen());
        laskuttaja.setAlv(" ");
        assertFalse(laskuttaja.onkoAlvOikeanlainen());
        laskuttaja.setAlv("          ");
        assertFalse(laskuttaja.onkoAlvOikeanlainen());
    }

    @Test
    public void onkoTilinumeroOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoTilinumeroOikeanlainen());
    }

    @Test
    public void onkoTilinumeroOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setTilinumero(new Tilinumero("FI3936363002092493", "1", "2"));
        assertFalse(laskuttaja.onkoTilinumeroOikeanlainen());
        laskuttaja.setTilinumero(new Tilinumero("FI3936363002094592", "3", "4"));
        assertFalse(laskuttaja.onkoTilinumeroOikeanlainen());
        laskuttaja.setTilinumero(new Tilinumero("FI3936363342092492", "5", "6"));
        assertFalse(laskuttaja.onkoTilinumeroOikeanlainen());
    }

    @Test
    public void onkoPankkiOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoPankkiOikeanlainen());
        laskuttaja.setTilinumero(new Tilinumero("FI3936363002092492", "ä", "2"));
        assertTrue(laskuttaja.onkoPankkiOikeanlainen());
    }

    @Test
    public void onkoPankkiOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setTilinumero(new Tilinumero("FI3936363002092492", "", "2"));
        assertFalse(laskuttaja.onkoPankkiOikeanlainen());
        laskuttaja.setTilinumero(new Tilinumero("FI3936363002092492", " ", "2"));
        assertFalse(laskuttaja.onkoPankkiOikeanlainen());
        laskuttaja.setTilinumero(new Tilinumero("FI3936363002092492", "       ", "2"));
        assertFalse(laskuttaja.onkoPankkiOikeanlainen());
    }
    
    @Test
    public void onkoSwiftBicOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoSwiftBicOikeanlainen());
        laskuttaja.setTilinumero(new Tilinumero("FI3936363002092492", "1", "ä"));
        assertTrue(laskuttaja.onkoSwiftBicOikeanlainen());
    }

    @Test
    public void onkoSwiftBicOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setTilinumero(new Tilinumero("FI3936363002092492", "1", ""));
        assertFalse(laskuttaja.onkoSwiftBicOikeanlainen());
        laskuttaja.setTilinumero(new Tilinumero("FI3936363002092492", "1", " "));
        assertFalse(laskuttaja.onkoSwiftBicOikeanlainen());
        laskuttaja.setTilinumero(new Tilinumero("FI3936363002092492", "1", "    "));
        assertFalse(laskuttaja.onkoSwiftBicOikeanlainen());
    }

    @Test
    public void onkoPuhelinnumeroOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoPuhelinnumeroOikeanlainen());
        laskuttaja.setPuhelinnumero("050 34343-34343- - - -");
        assertTrue(laskuttaja.onkoPuhelinnumeroOikeanlainen());
    }

    @Test
    public void onkoPuhelinnumeroOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setPuhelinnumero("");
        assertFalse(laskuttaja.onkoPuhelinnumeroOikeanlainen());
        laskuttaja.setPuhelinnumero(" ");
        assertFalse(laskuttaja.onkoPuhelinnumeroOikeanlainen());
        laskuttaja.setPuhelinnumero("      ");
        assertFalse(laskuttaja.onkoPuhelinnumeroOikeanlainen());
        laskuttaja.setPuhelinnumero("0044-234r");
        assertFalse(laskuttaja.onkoPuhelinnumeroOikeanlainen());
        laskuttaja.setPuhelinnumero("223  434443r");
        assertFalse(laskuttaja.onkoPuhelinnumeroOikeanlainen());
        laskuttaja.setPuhelinnumero("asd");
        assertFalse(laskuttaja.onkoPuhelinnumeroOikeanlainen());
    }
    
    @Test
    public void onkoSahkopostiOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoSahkopostiOikeanlainen());
        laskuttaja.setSahkopostiOsoite("a@b.com");
        assertTrue(laskuttaja.onkoSahkopostiOikeanlainen());
    }

    @Test
    public void onkoSahkopostiOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setSahkopostiOsoite("");
        assertFalse(laskuttaja.onkoSahkopostiOikeanlainen());
        laskuttaja.setSahkopostiOsoite(" ");
        assertFalse(laskuttaja.onkoSahkopostiOikeanlainen());
        laskuttaja.setSahkopostiOsoite("      ");
        assertFalse(laskuttaja.onkoSahkopostiOikeanlainen());
        laskuttaja.setSahkopostiOsoite("!876€%&(@4534534.göh");
        assertFalse(laskuttaja.onkoSahkopostiOikeanlainen());
        laskuttaja.setSahkopostiOsoite("Seesam aukene!");
        assertFalse(laskuttaja.onkoSahkopostiOikeanlainen());
        laskuttaja.setSahkopostiOsoite("asd");
        assertFalse(laskuttaja.onkoSahkopostiOikeanlainen());
        laskuttaja.setSahkopostiOsoite(".");
        assertFalse(laskuttaja.onkoSahkopostiOikeanlainen());
        laskuttaja.setSahkopostiOsoite("€€€@a.de");
        assertFalse(laskuttaja.onkoSahkopostiOikeanlainen());
    }
    
   

    @Test
    public void onkoLaskujaLahetettyOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoLaskujaLahetettyOikeanlainen());
        laskuttaja.setLaskujaLahetetty(0);
        assertTrue(laskuttaja.onkoLaskujaLahetettyOikeanlainen());
        laskuttaja.setLaskujaLahetetty(1);
        assertTrue(laskuttaja.onkoLaskujaLahetettyOikeanlainen());
    }

    @Test
    public void onkoLaskujaLahetettyOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setLaskujaLahetetty(-1);
        assertFalse(laskuttaja.onkoLaskujaLahetettyOikeanlainen());
        laskuttaja.setLaskujaLahetetty(-5);
        assertFalse(laskuttaja.onkoLaskujaLahetettyOikeanlainen());
        laskuttaja.setLaskujaLahetetty(-5000);
        assertFalse(laskuttaja.onkoLaskujaLahetettyOikeanlainen());
    }

    @Test
    public void onkoTiedotOikeanlaisetToimiiOikeinOikeillaTiedoilla() {
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());
    }

    @Test
    public void onkoTiedotOikeanlaisetToimiiOikeinVaarillaTiedoilla() {
        laskuttaja.setNimi("");
        assertFalse(laskuttaja.onkoTiedotOikeanlaiset());
        laskuttaja.setNimi("Elmeri");
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());

        laskuttaja.setKatuosoite("");
        assertFalse(laskuttaja.onkoTiedotOikeanlaiset());
        laskuttaja.setKatuosoite("Katu 1 A 86");
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());

        laskuttaja.setPostinumero("r4");
        assertFalse(laskuttaja.onkoTiedotOikeanlaiset());
        laskuttaja.setPostinumero("00123");
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());

        laskuttaja.setKaupunki("");
        assertFalse(laskuttaja.onkoTiedotOikeanlaiset());
        laskuttaja.setKaupunki("Elmericity");
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());

        laskuttaja.setYrityksenNimi("");
        assertFalse(laskuttaja.onkoTiedotOikeanlaiset());
        laskuttaja.setYrityksenNimi("Superyhtiöt");
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());
        
        laskuttaja.setAlv("");
        assertFalse(laskuttaja.onkoTiedotOikeanlaiset());
        laskuttaja.setAlv("1");
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());
        
        Tilinumero tilinumero1 = new Tilinumero("FI3936363002052492", "Superpankki", "FISUFIHH");
        laskuttaja.setTilinumero(tilinumero1);
        assertFalse(laskuttaja.onkoTiedotOikeanlaiset());
        laskuttaja.setTilinumero(tilinumero);
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());
        
        Tilinumero tilinumero2 = new Tilinumero("FI3936363002092492", "", "FISUFIHH");
        laskuttaja.setTilinumero(tilinumero2);
        assertFalse(laskuttaja.onkoTiedotOikeanlaiset());
        laskuttaja.setTilinumero(tilinumero);
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());
        
        Tilinumero tilinumero3 = new Tilinumero("FI3936363002092492", "Superpankki", "");
        laskuttaja.setTilinumero(tilinumero3);
        assertFalse(laskuttaja.onkoTiedotOikeanlaiset());
        laskuttaja.setTilinumero(tilinumero);
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());
        
        laskuttaja.setPuhelinnumero("jdfhdjhfd");
        assertFalse(laskuttaja.onkoTiedotOikeanlaiset());
        laskuttaja.setPuhelinnumero("09874874368765");
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());
        
        laskuttaja.setSahkopostiOsoite("34f34f");
        assertFalse(laskuttaja.onkoTiedotOikeanlaiset());
        laskuttaja.setSahkopostiOsoite("a@b.fi");
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());
        
        laskuttaja.setLaskujaLahetetty(-300);
        assertFalse(laskuttaja.onkoTiedotOikeanlaiset());
        laskuttaja.setLaskujaLahetetty(300);
        assertTrue(laskuttaja.onkoTiedotOikeanlaiset());
    }
}
