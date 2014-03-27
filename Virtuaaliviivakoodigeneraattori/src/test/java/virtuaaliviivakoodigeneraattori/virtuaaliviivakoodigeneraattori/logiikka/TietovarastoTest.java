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
public class TietovarastoTest {

    Asiakas asiakas;
    Asiakas asiakas1;
    Asiakas asiakas2;
    Asiakas asiakas3;

    Tietovarasto tietovarasto;
    String[][] verrattavaTaulukko;

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

        tietovarasto = new Tietovarasto();
        tietovarasto.getAsiakkaat().add(asiakas);
        tietovarasto.getAsiakkaat().add(asiakas1);
        tietovarasto.getAsiakkaat().add(asiakas2);
        tietovarasto.getAsiakkaat().add(asiakas3);

        verrattavaTaulukko = new String[][]{
            {"Elmeri Asiakas", "Elmerinkatu 17 B 45", "00345", "Toijala", "10", "123"},
            {"Elmeri Viitala", "Elmerinkatu 45", "00365", "Elmericity", "1000", "2123"},
            {"Elmeri Kantola", "Katu 75", "00447", "Nuorgam", "10000", "12534"},
            {"Elmeri Eskola", "Katu 12", "00347", "Outokumpu", "12000", "1234"}};
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAsiakkaatArrayStringToimiiOikein() {
        String[][] taulukko = tietovarasto.getAsiakkaatArrayString();
        for (int i = 0; i < tietovarasto.getAsiakkaat().size(); i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(verrattavaTaulukko[i][j], taulukko[i][j]);
            }
        }
    }
}
