package superlaskuttaja.logiikka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import static java.util.Calendar.DAY_OF_MONTH;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class LaskuTest {

    Asiakas asiakas;
    Asiakas asiakas1;
    GregorianCalendar date;
    GregorianCalendar date1;
    Suorite suorite;
    Suorite suorite1;
    Suorite suorite2;

    Tilinumero tilinumero;
    Tilinumero tilinumero1;
    Laskuttaja laskuttaja;
    Laskuttaja laskuttaja1;
    Viite viite;
    Viite viite1;
    ArrayList<Suorite> suoritteetList;
    ArrayList<Suorite> suoritteetList1;
    LaskunSumma summa;
    LaskunSumma summa1;
    Pankkiviivakoodi pankkiviivakoodi;
    Pankkiviivakoodi pankkiviivakoodi1;
    Pankkiviivakoodi pankkiviivakoodi2;
    Lasku lasku;
    Lasku lasku1;
    Lasku lasku2;
    Lasku lasku3;

    public LaskuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        asiakas = new Asiakas("123", "Elmeri Asiakas", "Elmerinkatu 17 B 45", "00345", "Elmericity", 100000);
        asiakas1 = new Asiakas("123", "Elmeri Asiakas", "Elmerinkatu 17 B 45", "00345", "Elmericity", 100000);
        date = new GregorianCalendar(2014, 4 - 1, 2);
        date1 = new GregorianCalendar(2014, 4 - 1, 2);
        suorite = new Suorite(asiakas, "Kuvaus", date, 2.0, "h", 75.0, 24);
        suorite1 = new Suorite(asiakas1, "Kuvaus", date1, 2.0, "h", 75.0, 24);
        suorite2 = new Suorite(asiakas1, "Eri kuvaus", date1, 2.0, "h", 75.0, 24);

        tilinumero = new Tilinumero("FI7944052020036082", "Superpankki", "SUPFI");
        tilinumero1 = new Tilinumero("FI7944052020036082", "Superpankki", "SUPFI");
        laskuttaja = new Laskuttaja("Nimi", "Katuosoite", "00123", "Laskuttajacity", "Superyritys", "123456", tilinumero, "0101234123", "super@super.fi", 5000);
        laskuttaja1 = new Laskuttaja("Nimi", "Katuosoite", "00123", "Laskuttajacity", "Superyritys", "123456", tilinumero1, "0101234123", "super@super.fi", 5000);
        viite = new Viite("1234");
        viite1 = new Viite("1234");
        suoritteetList = new ArrayList<>();
        suoritteetList.add(suorite);
        suoritteetList1 = new ArrayList<>();
        suoritteetList1.add(suorite1);
        summa = new LaskunSumma(5, 5);
        summa1 = new LaskunSumma(5, 5);
        pankkiviivakoodi = new Pankkiviivakoodi(tilinumero, summa, viite, new GregorianCalendar(2014, 4 - 1, 16));
        pankkiviivakoodi1 = new Pankkiviivakoodi(tilinumero1, summa1, viite1, new GregorianCalendar(2014, 4 - 1, 16));
        pankkiviivakoodi2 = new Pankkiviivakoodi(tilinumero1, summa1, viite1, new GregorianCalendar(2014, 4 - 1, 15));
        lasku = new Lasku(laskuttaja, asiakas, new GregorianCalendar(2014, 4 - 1, 2), 5, new GregorianCalendar(2014, 4 - 1, 16), 50, viite, "Maksuehto", suoritteetList, "Lisätiedot", summa, pankkiviivakoodi);
        lasku1 = new Lasku(laskuttaja1, asiakas1, new GregorianCalendar(2014, 4 - 1, 2), 5, new GregorianCalendar(2014, 4 - 1, 16), 50, viite1, "Maksuehto", suoritteetList1, "Lisätiedot", summa1, pankkiviivakoodi1);
        lasku2 = new Lasku(laskuttaja, asiakas, new GregorianCalendar(2013, 4 - 3, 2), 5, new GregorianCalendar(2014, 4 - 1, 17), 5, viite, "Maksu", suoritteetList, "Lisätiedot", summa, pankkiviivakoodi);
        lasku3 = new Lasku(laskuttaja1, asiakas1, new GregorianCalendar(2013, 4 - 3, 2), 5, new GregorianCalendar(2014, 4 - 1, 17), 5, viite1, "Maksu", suoritteetList1, "Lisätiedot", summa1, pankkiviivakoodi1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsToimiiOikein() {
        assertTrue(lasku.equals(lasku1));
        
        lasku1.getLaskuttaja().setNimi("Toinen nimi");
        assertFalse(lasku.equals(lasku1));
        lasku1.getLaskuttaja().setNimi("Nimi");
        assertTrue(lasku.equals(lasku1));
        
        lasku1.getAsiakas().setNimi("Toinen nimi");
        assertFalse(lasku.equals(lasku1));
        lasku1.getAsiakas().setNimi("Elmeri Asiakas");
        assertTrue(lasku.equals(lasku1));
        
        lasku1.getPaivays().add(DAY_OF_MONTH, 3);
        assertFalse(lasku.equals(lasku1));
        lasku1.getPaivays().add(DAY_OF_MONTH, -3);
        assertTrue(lasku.equals(lasku1));
        
        lasku1.setViivastyskorko(90);
        assertFalse(lasku.equals(lasku1));
        lasku1.setViivastyskorko(50);
        assertTrue(lasku.equals(lasku1));
        
        lasku1.getViiteTarkisteella().asetaUusiViiteViitteestaIlmanTarkistetta("555");
        assertFalse(lasku.equals(lasku1));
        lasku1.getViiteTarkisteella().asetaUusiViiteViitteestaIlmanTarkistetta("1234");
        assertTrue(lasku.equals(lasku1));
        
        lasku1.setMaksuehto("Toinen ehto");
        assertFalse(lasku.equals(lasku1));
        lasku1.setMaksuehto("Maksuehto");
        assertTrue(lasku.equals(lasku1));
        
        lasku1.getSuoritteet().add(suorite2);
        assertFalse(lasku.equals(lasku1));
        lasku1.getSuoritteet().remove(suorite2);
        assertTrue(lasku.equals(lasku1));
        
        lasku1.setLisatiedot("Pöö");
        assertFalse(lasku.equals(lasku1));
        lasku1.setLisatiedot("Lisätiedot");
        assertTrue(lasku.equals(lasku1));
        
        lasku1.getSumma().setEurot(7);
        assertFalse(lasku.equals(lasku1));
        lasku1.getSumma().setEurot(5);
        assertTrue(lasku.equals(lasku1));
        
        lasku1.setPankkiviivakoodi(pankkiviivakoodi2);
        assertFalse(lasku.equals(lasku1));
        lasku1.setPankkiviivakoodi(pankkiviivakoodi1);
        assertTrue(lasku.equals(lasku1));
        
        lasku1.setOnkoMaksettu(true);
        assertFalse(lasku.equals(lasku1));
        lasku1.setOnkoMaksettu(false);
        assertTrue(lasku.equals(lasku1));
        
        lasku1.setOnkoMaksettu(true);
        lasku1.paivitaMaksettuTeksti();
        assertFalse(lasku.equals(lasku1));
        lasku1.setOnkoMaksettu(false);
        lasku1.paivitaMaksettuTeksti();
        assertTrue(lasku.equals(lasku1));
    }
    
    @Test
    public void hashCodeToimiiOikein() {
        int expected = lasku.hashCode();
        assertEquals(expected, lasku1.hashCode());
        int expected1 = lasku2.hashCode();
        assertEquals(expected1, lasku3.hashCode());
    }
}
