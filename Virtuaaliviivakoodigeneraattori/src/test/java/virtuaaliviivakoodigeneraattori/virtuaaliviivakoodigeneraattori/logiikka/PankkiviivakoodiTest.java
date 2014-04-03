/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

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
public class PankkiviivakoodiTest {

    Tilinumero tilinumero;
    LaskunSumma laskunSumma;
    Viite viite;
    Date erapaiva;
    Pankkiviivakoodi pankkiviivakoodi;

    Tilinumero tilinumero1;
    LaskunSumma laskunSumma1;
    Viite viite1;
    Date erapaiva1;
    Pankkiviivakoodi pankkiviivakoodi1;

    Tilinumero tilinumero2;
    LaskunSumma laskunSumma2;
    Viite viite2;
    Date erapaiva2;
    Pankkiviivakoodi pankkiviivakoodi2;

    Tilinumero tilinumero3;
    LaskunSumma laskunSumma3;
    Viite viite3;
    Date erapaiva3;
    Pankkiviivakoodi pankkiviivakoodi3;

    Tilinumero tilinumero4;
    LaskunSumma laskunSumma4;
    Viite viite4;
    Date erapaiva4;
    Pankkiviivakoodi pankkiviivakoodi4;

    public PankkiviivakoodiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tilinumero = new Tilinumero("FI7944052020036082", "", "");
        laskunSumma = new LaskunSumma(4883, 15);
        viite = new Viite("86851625961989"); // Ilman tarkistetta.
        erapaiva = new Date(2010, 6 - 1, 12);
        pankkiviivakoodi = new Pankkiviivakoodi(tilinumero, laskunSumma, viite, erapaiva);

        tilinumero1 = new Tilinumero("FI7944052020036082", "", "");
        laskunSumma1 = new LaskunSumma(4883, 15);
        viite1 = new Viite("86851625961989"); // Ilman tarkistetta.
        erapaiva1 = new Date(2010, 6 - 1, 12);
        pankkiviivakoodi1 = new Pankkiviivakoodi(tilinumero, laskunSumma, viite, erapaiva);

        tilinumero2 = new Tilinumero("FI5810171000000122", "", "");
        laskunSumma2 = new LaskunSumma(482, 99);
        viite2 = new Viite("55958224329467"); // Ilman tarkistetta.
        erapaiva2 = new Date(2012, 1 - 1, 31);
        pankkiviivakoodi2 = new Pankkiviivakoodi(tilinumero2, laskunSumma2, viite2, erapaiva2);

        tilinumero3 = new Tilinumero("FI0250004640001302", "", "");
        laskunSumma3 = new LaskunSumma(693, 80);
        viite3 = new Viite("6987567208343536"); // Ilman tarkistetta.
        erapaiva3 = new Date(2011, 7 - 1, 24);
        pankkiviivakoodi3 = new Pankkiviivakoodi(tilinumero3, laskunSumma3, viite3, erapaiva3);

        tilinumero4 = new Tilinumero("FI0250004640001302", "", "");
        laskunSumma4 = new LaskunSumma(693, 80);
        viite4 = new Viite("6987567208343536"); // Ilman tarkistetta.
        erapaiva4 = new Date(2011, 7 - 1, 24);
        pankkiviivakoodi4 = new Pankkiviivakoodi(tilinumero3, laskunSumma3, viite3, erapaiva3);
    }

    @After
    public void tearDown() {
    }

    // Testien lähteenä käytetty materiaalista http://www.fkl.fi/teemasivut/sepa/tekninen_dokumentaatio/Dokumentit/Pankkiviivakoodi-opas.pdf
    // löytyviä version 4 testilaskuja.
    @Test
    public void muodostaPankkiviivakoodiToimiiOikein() {
        assertEquals("47944052020036082004883150000000086851625961989710061240", pankkiviivakoodi.pankkiviivakoodiIlmanAloitustaJaLopetusta());
        assertEquals("45810171000000122000482990000000055958224329467112013155", pankkiviivakoodi2.pankkiviivakoodiIlmanAloitustaJaLopetusta());
        assertEquals("40250004640001302000693800000006987567208343536411072414", pankkiviivakoodi3.pankkiviivakoodiIlmanAloitustaJaLopetusta());
    }

    @Test
    public void getStringToimiiOikein() {
        assertEquals("10547944052020036082004883150000000086851625961989710061240106", pankkiviivakoodi.getPankkiviivakoodi());
        assertEquals("10545810171000000122000482990000000055958224329467112013155106", pankkiviivakoodi2.getPankkiviivakoodi());
        assertEquals("10540250004640001302000693800000006987567208343536411072414106", pankkiviivakoodi3.getPankkiviivakoodi());
    }

    @Test
    public void equalsToimiiOikein() {
        assertTrue(pankkiviivakoodi.equals(pankkiviivakoodi1));
        assertTrue(pankkiviivakoodi3.equals(pankkiviivakoodi4));
        assertFalse(pankkiviivakoodi1.equals(pankkiviivakoodi2));
        assertFalse(pankkiviivakoodi2.equals(pankkiviivakoodi3));
    }

    @Test
    public void hashCodeToimiiOikein() {
        int expected = pankkiviivakoodi.hashCode();
        assertEquals(expected, pankkiviivakoodi1.hashCode());
        int expected1 = pankkiviivakoodi3.hashCode();
        assertEquals(expected1, pankkiviivakoodi4.hashCode());
    }
}
