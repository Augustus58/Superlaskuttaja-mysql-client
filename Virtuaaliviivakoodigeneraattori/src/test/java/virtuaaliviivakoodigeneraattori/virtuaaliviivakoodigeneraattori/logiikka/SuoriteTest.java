/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import java.util.ArrayList;
import java.util.Date;
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
public class SuoriteTest {

    Asiakas asiakas;
    Date date;
    Suorite suorite;

    Tilinumero tilinumero;
    Laskuttaja laskuttaja;
    Viite viite;
    ArrayList<Suorite> suoritteetList;
    LaskunSumma summa;
    Pankkiviivakoodi pankkiviivakoodi;
    Lasku lasku;

    public SuoriteTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //Asetetaan suorite-oliolle sellaiset tiedot, joiden pitäisi olla oikeanlaiset.
        asiakas = new Asiakas("123", "Elmeri Asiakas", "Elmerinkatu 17 B 45", "00345", "Elmericity", 100000);
        date = new Date(2014 - 1900, 4 - 1, 2);
        suorite = new Suorite(asiakas, "Kuvaus", date, 2.0, "h", 75.0, 24);

        tilinumero = new Tilinumero("FI7944052020036082", "Superpankki", "SUPFI");
        laskuttaja = new Laskuttaja("Nimi", "Katuosoite", "00123", "Laskuttajacity", "Superyritys", "123456", tilinumero, "0101234123", "super@super.fi", 5000);
        viite = new Viite("1234");
        suoritteetList = new ArrayList<>();
        suoritteetList.add(suorite);
        summa = new LaskunSumma(5, 5);
        pankkiviivakoodi = new Pankkiviivakoodi(tilinumero, summa, viite, new Date(2014 - 1900, 4 - 1, 16));
        lasku = new Lasku(laskuttaja, asiakas, new Date(2014 - 1900, 4 - 1, 2), 5, new Date(2014 - 1900, 4 - 1, 16), 50, viite, "Maksuehto", suoritteetList, "Lisätiedot", summa, pankkiviivakoodi);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriToimiiOikein() {
        //Tässä testataan metodit laskeAlv ja laskeYht.
        Suorite suorite2 = new Suorite(asiakas, "Kuvaus", date, 2.0, "h", 75.0, 24);
        assertEquals(new Double(36.0), suorite2.getAlv(), 0.00001);
        assertEquals(new Double(186.0), suorite2.getYht(), 0.00001);

        Suorite suorite3 = new Suorite(asiakas, "Kuvaus", date, 3.0, "h", 15.0, 12);
        assertEquals(new Double(5.4), suorite3.getAlv(), 0.00001);
        assertEquals(new Double(50.4), suorite3.getYht(), 0.00001);

        Suorite suorite4 = new Suorite(asiakas, "Kuvaus", date, 4.0, "h", 30.0, 25);
        assertEquals(new Double(30.0), suorite4.getAlv(), 0.00001);
        assertEquals(new Double(150.0), suorite4.getYht(), 0.00001);
    }

    @Test
    public void setLaskuToimiiOikein() {
        suorite.setLasku(lasku);
        assertEquals(lasku, suorite.getLasku());
        assertTrue(suorite.getOnkoLaskutettu());
    }

    @Test
    public void paivitaLaskutettuTekstiToimiiOikein() {
        assertEquals("Ei", suorite.getLaskutettuTeksti());
        suorite.setLasku(lasku);
        suorite.paivitaLaskutettuTeksti();
        assertEquals("Laskulla 5", suorite.getLaskutettuTeksti());
    }

    @Test
    public void onkoKuvausOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(suorite.onkoKuvausOikeanlainen());
        suorite.setKuvaus("ä");
        assertTrue(suorite.onkoKuvausOikeanlainen());
    }

    @Test
    public void onkoKuvausOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        suorite.setKuvaus("");
        assertFalse(suorite.onkoKuvausOikeanlainen());
        suorite.setKuvaus(" ");
        assertFalse(suorite.onkoKuvausOikeanlainen());
        suorite.setKuvaus("          ");
        assertFalse(suorite.onkoKuvausOikeanlainen());
    }
    
    @Test
    public void onkoMaaraOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(suorite.onkoMaaraOikeanlainen());
        suorite.setMaara(70.0);
        assertTrue(suorite.onkoMaaraOikeanlainen());
    }

    @Test
    public void onkoMaaraOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        suorite.setMaara(0.0);
        assertFalse(suorite.onkoMaaraOikeanlainen());
        suorite.setMaara(-0.5);
        assertFalse(suorite.onkoMaaraOikeanlainen());
        suorite.setMaara(-1.0);
        assertFalse(suorite.onkoMaaraOikeanlainen());
    }
    
    @Test
    public void onkoMaaranYksikotOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(suorite.onkoMaaranYksikotOikeanlainen());
        suorite.setKuvaus("ä");
        assertTrue(suorite.onkoMaaranYksikotOikeanlainen());
    }

    @Test
    public void onkoMaaranYksikotOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        suorite.setMaaranYksikot("");
        assertFalse(suorite.onkoMaaranYksikotOikeanlainen());
        suorite.setMaaranYksikot(" ");
        assertFalse(suorite.onkoMaaranYksikotOikeanlainen());
        suorite.setMaaranYksikot("          ");
        assertFalse(suorite.onkoMaaranYksikotOikeanlainen());
    }
    
    @Test
    public void onkoAHintaOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(suorite.onkoAHintaOikeanlainen());
        suorite.setaHinta(70.0);
        assertTrue(suorite.onkoAHintaOikeanlainen());
    }

    @Test
    public void onkoAHintaOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        suorite.setaHinta(0.0);
        assertFalse(suorite.onkoAHintaOikeanlainen());
        suorite.setaHinta(-0.5);
        assertFalse(suorite.onkoAHintaOikeanlainen());
        suorite.setaHinta(-1.0);
        assertFalse(suorite.onkoAHintaOikeanlainen());
    }
    
    @Test
    public void onkoAlvProsenttiOikeanlainenToimiiOikeinOikeillaTiedoilla() {
        assertTrue(suorite.onkoAlvProsenttiOikeanlainen());
        suorite.setAlvProsentti(0);
        assertTrue(suorite.onkoAlvProsenttiOikeanlainen());
        suorite.setAlvProsentti(50);
        assertTrue(suorite.onkoAlvProsenttiOikeanlainen());
        suorite.setAlvProsentti(100);
        assertTrue(suorite.onkoAlvProsenttiOikeanlainen());
    }

    @Test
    public void onkoAlvProsenttiOikeanlainenToimiiOikeinVaarillaTiedoilla() {
        suorite.setAlvProsentti(-5);
        assertFalse(suorite.onkoAlvProsenttiOikeanlainen());
        suorite.setAlvProsentti(-1);
        assertFalse(suorite.onkoAlvProsenttiOikeanlainen());
        suorite.setAlvProsentti(-101);
        assertFalse(suorite.onkoAlvProsenttiOikeanlainen());
        suorite.setAlvProsentti(-105);
        assertFalse(suorite.onkoAlvProsenttiOikeanlainen());
    }
    
    @Test
    public void onkoTiedotOikeanlaisetPaitsiPvmToimiiOikeinOikeillaTiedoilla() {
        assertTrue(suorite.onkoTiedotOikeanlaisetPaitsiPvm());
    }
    
    @Test
    public void onkoTiedotOikeanlaisetPaitsiPvmToimiiOikeinVaarillaTiedoilla() {
        suorite.setKuvaus("");
        assertFalse(suorite.onkoTiedotOikeanlaisetPaitsiPvm());
        suorite.setKuvaus("Kuvaus");
        assertTrue(suorite.onkoTiedotOikeanlaisetPaitsiPvm());
        
        suorite.setMaara(-50.4);
        assertFalse(suorite.onkoTiedotOikeanlaisetPaitsiPvm());
        suorite.setMaara(50.0);
        assertTrue(suorite.onkoTiedotOikeanlaisetPaitsiPvm());
        
        suorite.setMaaranYksikot("");
        assertFalse(suorite.onkoTiedotOikeanlaisetPaitsiPvm());
        suorite.setMaaranYksikot("h");
        assertTrue(suorite.onkoTiedotOikeanlaisetPaitsiPvm());
        
        suorite.setaHinta(0.0);
        assertFalse(suorite.onkoTiedotOikeanlaisetPaitsiPvm());
        suorite.setaHinta(300.23);
        assertTrue(suorite.onkoTiedotOikeanlaisetPaitsiPvm());
        
        suorite.setAlvProsentti(1000);
        assertFalse(suorite.onkoTiedotOikeanlaisetPaitsiPvm());
        suorite.setAlvProsentti(5);
        assertTrue(suorite.onkoTiedotOikeanlaisetPaitsiPvm());
    }
}
