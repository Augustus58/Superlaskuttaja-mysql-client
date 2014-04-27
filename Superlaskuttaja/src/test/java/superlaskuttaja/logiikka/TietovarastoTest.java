/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.logiikka;

import java.util.ArrayList;
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
public class TietovarastoTest {

    Asiakas asiakas;
    Asiakas asiakas1;
    Asiakas asiakas2;
    Asiakas asiakas3;

    GregorianCalendar pvm;
    GregorianCalendar pvm1;
    GregorianCalendar pvm2;

    Suorite suorite;
    Suorite suorite1;
    Suorite suorite2;

    Tietovarasto tietovarasto;
    String[][] verrattavaTaulukko;

    Tilinumero tilinumero;
    Laskuttaja laskuttaja;
    
    GregorianCalendar pvmLasku;
    GregorianCalendar pvmLasku1;
    Viite viite;
    ArrayList<Suorite> suoritteet;
    LaskunSumma summa;
    Pankkiviivakoodi pankkiviivakoodi;
    Lasku lasku;

    public TietovarastoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        asiakas = new Asiakas("123", "Elmeri Asiakas", "Elmerinkatu 17 B 45", "00345", "Toijala", 10);
        asiakas1 = new Asiakas("2123", "Elmeri Viitala", "Elmerinkatu 45", "00365", "Elmericity", 1000);
        asiakas2 = new Asiakas("12534", "Elmeri Kantola", "Katu 75", "00447", "Nuorgam", 10000);
        asiakas3 = new Asiakas("1234", "Elmeri Eskola", "Katu 12", "00347", "Outokumpu", 12000);

        pvm = new GregorianCalendar(2014, 3, 12);
        pvm1 = new GregorianCalendar(2014, 3, 14);
        pvm2 = new GregorianCalendar(2014, 3, 16);

        suorite = new Suorite(asiakas, "1", pvm, 1.0, "h", 100.0, 24);
        suorite1 = new Suorite(asiakas, "2", pvm1, 1.0, "h", 120.0, 24);
        suorite2 = new Suorite(asiakas, "3", pvm2, 1.0, "h", 100.0, 24);

        tietovarasto = new Tietovarasto();
        tietovarasto.getAsiakkaat().add(asiakas);
        tietovarasto.getAsiakkaat().add(asiakas1);
        tietovarasto.getAsiakkaat().add(asiakas2);
        tietovarasto.getAsiakkaat().add(asiakas3);

        tietovarasto.getSuoritteet().add(suorite);
        tietovarasto.getSuoritteet().add(suorite1);
        tietovarasto.getSuoritteet().add(suorite2);

        verrattavaTaulukko = new String[][]{
            {"Elmeri Asiakas", "Elmerinkatu 17 B 45", "00345", "Toijala", "10", "123"},
            {"Elmeri Viitala", "Elmerinkatu 45", "00365", "Elmericity", "1000", "2123"},
            {"Elmeri Kantola", "Katu 75", "00447", "Nuorgam", "10000", "12534"},
            {"Elmeri Eskola", "Katu 12", "00347", "Outokumpu", "12000", "1234"}};

        tilinumero = new Tilinumero("FI3936363002092492", "Superpankki", "FISUFIHH");
        laskuttaja = new Laskuttaja("Erkki Laskuttaja", "Laskuttajankatu 17 A 7", "00549", "Laskuttajacity", "Superyhtiöt", "FI2343-34343", tilinumero, "035-5554444", "erkki.laskuttaja@superyhtiot.fi", 50345);
        
        pvmLasku = new GregorianCalendar(2014, 5, 5);
        pvmLasku1 = new GregorianCalendar(2014, 5, 15);
        
        viite = new Viite("12345");
        
        suoritteet = new ArrayList();
        suoritteet.add(suorite);
        suoritteet.add(suorite2);
        
        summa = new LaskunSumma(248, 0);
        
        pankkiviivakoodi = new Pankkiviivakoodi(tilinumero, summa, viite, pvm);
        
        lasku = new Lasku(laskuttaja, asiakas, pvmLasku, 30, pvmLasku1, 8, viite, "Ehto", suoritteet, "Lisatiedot", summa, pankkiviivakoodi);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void asiakkaatArrayStringToimiiOikein() {
        String[][] taulukko = tietovarasto.asiakkaatArrayString();
        for (int i = 0; i < tietovarasto.getAsiakkaat().size(); i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(verrattavaTaulukko[i][j], taulukko[i][j]);
            }
        }
    }

    @Test
    public void asiakkaidenNimetArrayStringToimiiOikein() {
        String[] lista = tietovarasto.asiakkaidenNimetArrayString();
        for (int i = 0; i < tietovarasto.getAsiakkaat().size(); i++) {
            assertEquals(tietovarasto.getAsiakkaat().get(i).getNimi(), lista[i]);
        }
    }

    @Test
    public void setLaskuttajaToimiiOikein() {
        assertFalse(tietovarasto.isLaskuttajaLisatty());
        tietovarasto.setLaskuttaja(laskuttaja);
        assertTrue(tietovarasto.isLaskuttajaLisatty());
        assertEquals(laskuttaja, tietovarasto.getLaskuttaja());
    }

    @Test
    public void poistaAsiakasToimiiOikein() {
        assertTrue(tietovarasto.getAsiakkaat().contains(asiakas));
        assertTrue(tietovarasto.getAsiakkaat().contains(asiakas1));
        assertTrue(tietovarasto.getAsiakkaat().contains(asiakas2));
        assertTrue(tietovarasto.getAsiakkaat().contains(asiakas3));
        assertEquals(4, tietovarasto.getAsiakkaat().size());
        tietovarasto.poistaAsiakas(asiakas);
        assertFalse(tietovarasto.getAsiakkaat().contains(asiakas));
        assertTrue(tietovarasto.getAsiakkaat().contains(asiakas1));
        assertTrue(tietovarasto.getAsiakkaat().contains(asiakas2));
        assertTrue(tietovarasto.getAsiakkaat().contains(asiakas3));
        assertEquals(3, tietovarasto.getAsiakkaat().size());
    }

    @Test
    public void asiakkaanLaskuttamattomatSuoritteetArrayListToimiiOikein() {
        ArrayList<Suorite> lista = new ArrayList();
        lista.add(suorite);
        lista.add(suorite1);
        lista.add(suorite2);
        assertEquals(lista, tietovarasto.asiakkaanLaskuttamattomatSuoritteetArrayList(asiakas));
        
        suorite.setLasku(lasku);
        suorite2.setLasku(lasku);
        ArrayList<Suorite> lista1 = new ArrayList();
        lista1.add(suorite1);
        assertEquals(lista1, tietovarasto.asiakkaanLaskuttamattomatSuoritteetArrayList(asiakas));
    }

    @Test
    public void asiakkaanLaskuttamattomatSuoritteetJaHalututSuoritteetArrayListToimiiOikein() {
        suorite1.setLasku(lasku);
        ArrayList<Suorite> lista = new ArrayList();
        lista.add(suorite);
        lista.add(suorite2);
        ArrayList<Suorite> lista1 = new ArrayList();
        lista1.add(suorite2);
        assertEquals(lista, tietovarasto.asiakkaanLaskuttamattomatSuoritteetJaHalututSuoritteetArrayList(asiakas, lista1));
    }
}
