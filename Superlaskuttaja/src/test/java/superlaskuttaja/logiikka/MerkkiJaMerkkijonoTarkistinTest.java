/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.logiikka;

import superlaskuttaja.logiikka.MerkkiJaMerkkijonoTarkistin;
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
public class MerkkiJaMerkkijonoTarkistinTest {

    MerkkiJaMerkkijonoTarkistin tarkistin;
    Character merkki1;
    Character merkki2;
    Character merkki3;
    Character merkki4;
    Character merkki5;

    public MerkkiJaMerkkijonoTarkistinTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tarkistin = new MerkkiJaMerkkijonoTarkistin();
        merkki1 = new Character('4');
        merkki2 = new Character('A');
        merkki3 = new Character('R');
        merkki4 = new Character('a');
        merkki5 = new Character('5');
    }

    @After
    public void tearDown() {
    }

    @Test
    public void onkoMerkkiNumeroToimiiOikein() {
        assertTrue(tarkistin.onkoMerkkiNumero(merkki1));
        
        assertFalse(tarkistin.onkoMerkkiNumero(merkki2));
    }

    @Test
    public void onkoMerkkiKirjainAZToimiiOikein() {
        assertTrue(tarkistin.onkoMerkkiKirjainAZ(merkki3));
        
        assertFalse(tarkistin.onkoMerkkiKirjainAZ(merkki4));
        assertFalse(tarkistin.onkoMerkkiKirjainAZ(merkki5));
    }

    @Test
    public void koostuukoMerkkijonoNumeroistaToimiiOikein() {
        assertTrue(tarkistin.koostuukoMerkkijonoNumeroista("1"));
        assertTrue(tarkistin.koostuukoMerkkijonoNumeroista("1234567890"));
        
        assertFalse(tarkistin.koostuukoMerkkijonoNumeroista(""));
        assertFalse(tarkistin.koostuukoMerkkijonoNumeroista(" "));
        assertFalse(tarkistin.koostuukoMerkkijonoNumeroista("      "));
        assertFalse(tarkistin.koostuukoMerkkijonoNumeroista("123123a45535"));
        assertFalse(tarkistin.koostuukoMerkkijonoNumeroista("!"));
        assertFalse(tarkistin.koostuukoMerkkijonoNumeroista("a"));
    }

    @Test
    public void onkoMerkkiNumeroValiviivaTaiValilyontiToimiiOikein() {
        assertTrue(tarkistin.onkoMerkkiNumeroValiviivaTaiValilyonti('1'));
        assertTrue(tarkistin.onkoMerkkiNumeroValiviivaTaiValilyonti('-'));
        assertTrue(tarkistin.onkoMerkkiNumeroValiviivaTaiValilyonti(' '));
        
        assertFalse(tarkistin.onkoMerkkiNumeroValiviivaTaiValilyonti('a'));
        assertFalse(tarkistin.onkoMerkkiNumeroValiviivaTaiValilyonti('+'));
    }

    @Test
    public void SisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneistaToimiiOikein() {
        assertTrue(tarkistin.sisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista("1"));
        assertTrue(tarkistin.sisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista("11   ---2343 3  - 098766- -"));
        assertTrue(tarkistin.sisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista("1-2 4"));
        
        assertFalse(tarkistin.sisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista(" -"));
        assertFalse(tarkistin.sisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista(""));
        assertFalse(tarkistin.sisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista(" "));
        assertFalse(tarkistin.sisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista("       "));
        assertFalse(tarkistin.sisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista("1jenkfj"));
        assertFalse(tarkistin.sisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista("2- a878979"));
    }

    @Test
    public void sisaltaakoMerkkijonoVahintaanYhdenNumeronToimiiOikein() {
        assertTrue(tarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("tyui4"));
        assertTrue(tarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("q4werty"));
        assertTrue(tarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("1"));
        assertTrue(tarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("€!€!€!,.-;;:__::;;9"));
        assertTrue(tarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("5€!€!€!,.-;;:__::;;"));
        assertTrue(tarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("3453453"));
        
        assertFalse(tarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron(""));
        assertFalse(tarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron(" "));
        assertFalse(tarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("      "));
        assertFalse(tarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("weferg"));
        assertFalse(tarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("!!!åäö!"));
    }

    @Test
    public void voikoMerkkijononMuuttaaKokonaisluvuksiToimiiOikein() {
        assertTrue(tarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("0008"));
        assertTrue(tarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("123"));
        assertTrue(tarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("0030001020"));
        
        assertFalse(tarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("a0008"));
        assertFalse(tarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("12r3"));
        assertFalse(tarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("0030001020e"));
        assertFalse(tarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("00300 01020"));
    }

    @Test
    public void onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneistaToimiiOikein() {
        assertTrue(tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(""));
        assertTrue(tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(" "));
        assertTrue(tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista("      "));
        
        assertFalse(tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista("a"));
        assertFalse(tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista("."));
        assertFalse(tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista("         0"));
        assertFalse(tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista("å         "));
        assertFalse(tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista("     5    "));
    }

    @Test
    public void onkoEmailOsoiteValidiToimiiOikein() {
        assertTrue(tarkistin.onkoEmailOsoiteValidi("oijdwed@dwjkel.com"));
        assertTrue(tarkistin.onkoEmailOsoiteValidi("a@b.fi"));
        assertTrue(tarkistin.onkoEmailOsoiteValidi("ferferferf@ferferf.oijekrferf"));
        
        assertFalse(tarkistin.onkoEmailOsoiteValidi("ferferatferferf.oom"));
        assertFalse(tarkistin.onkoEmailOsoiteValidi("ferferferf@ferferf dot fi"));
    }

    @Test
    public void onkoMerkkijonoMuotoaNnPisteNnPisteNnnnToimiiOikein() {
        assertTrue(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("00.00.0000"));
        assertTrue(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("10.10.1000"));
        assertTrue(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("01.01.0001"));
        assertTrue(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("12.12.1212"));

        assertFalse(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("0.00.0000"));
        assertFalse(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("00.0.0000"));
        assertFalse(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("00.00.000"));
        assertFalse(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("00.00.a000"));
        assertFalse(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("00.0a.0000"));
        assertFalse(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("a0.00.0000"));
        assertFalse(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("00,00.0000"));
        assertFalse(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("00.00,0000"));
        assertFalse(tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn("00000000"));
    }
    
    @Test
    public void onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidiToimiiOikein() {
        assertTrue(tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi("01.01.2011"));
        assertTrue(tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi("10.10.2010"));
        assertTrue(tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi("02.04.2014"));
        assertTrue(tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi("01.04.2014"));
        
        assertFalse(tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi("00.01.2011"));
        assertFalse(tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi("32.01.2011"));
        assertFalse(tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi("01.00.2011"));
        assertFalse(tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi("01.13.2011"));
        assertFalse(tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi("30.02.2004"));
        assertFalse(tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi("29.02.2003"));
        assertFalse(tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi("29.02.2005"));
    }
}
