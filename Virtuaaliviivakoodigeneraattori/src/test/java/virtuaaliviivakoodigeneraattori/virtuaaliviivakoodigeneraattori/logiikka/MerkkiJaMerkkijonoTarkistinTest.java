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
public class MerkkiJaMerkkijonoTarkistinTest {

    MerkkiJaMerkkijonoTarkistin merkkitarkistin;
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
        merkkitarkistin = new MerkkiJaMerkkijonoTarkistin();
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
        assertTrue(merkkitarkistin.onkoMerkkiNumero(merkki1));
        assertFalse(merkkitarkistin.onkoMerkkiNumero(merkki2));
    }

    @Test
    public void onkoMerkkiKirjainAZToimiiOikein() {
        assertTrue(merkkitarkistin.onkoMerkkiKirjainAZ(merkki3));
        assertFalse(merkkitarkistin.onkoMerkkiKirjainAZ(merkki4));
        assertFalse(merkkitarkistin.onkoMerkkiKirjainAZ(merkki5));
    }

    @Test
    public void koostuukoMerkkijonoNumeroistaToimiiOikein() {
        assertTrue(merkkitarkistin.koostuukoMerkkijonoNumeroista("1"));
        assertTrue(merkkitarkistin.koostuukoMerkkijonoNumeroista("1234567890"));
        assertFalse(merkkitarkistin.koostuukoMerkkijonoNumeroista(""));
        assertFalse(merkkitarkistin.koostuukoMerkkijonoNumeroista(" "));
        assertFalse(merkkitarkistin.koostuukoMerkkijonoNumeroista("      "));
        assertFalse(merkkitarkistin.koostuukoMerkkijonoNumeroista("123123a45535"));
        assertFalse(merkkitarkistin.koostuukoMerkkijonoNumeroista("!"));
        assertFalse(merkkitarkistin.koostuukoMerkkijonoNumeroista("a"));
    }

    @Test
    public void onkoMerkkiNumeroValiviivaTaiValilyontiToimiiOikein() {
        assertTrue(merkkitarkistin.onkoMerkkiNumeroValiviivaTaiValilyonti('1'));
        assertTrue(merkkitarkistin.onkoMerkkiNumeroValiviivaTaiValilyonti('-'));
        assertTrue(merkkitarkistin.onkoMerkkiNumeroValiviivaTaiValilyonti(' '));
        assertFalse(merkkitarkistin.onkoMerkkiNumeroValiviivaTaiValilyonti('a'));
        assertFalse(merkkitarkistin.onkoMerkkiNumeroValiviivaTaiValilyonti('+'));
    }

    @Test
    public void SisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneistaToimiiOikein() {
        assertTrue(merkkitarkistin.SisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista("1"));
        assertTrue(merkkitarkistin.SisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista("11   ---2343 3  - 098766- -"));
        assertTrue(merkkitarkistin.SisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista("1-2 4"));
        assertFalse(merkkitarkistin.SisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista(" -"));
        assertFalse(merkkitarkistin.SisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista(""));
        assertFalse(merkkitarkistin.SisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista(" "));
        assertFalse(merkkitarkistin.SisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista("       "));
        assertFalse(merkkitarkistin.SisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista("1jenkfj"));
        assertFalse(merkkitarkistin.SisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista("2- a878979"));
    }

    @Test
    public void sisaltaakoMerkkijonoVahintaanYhdenNumeronToimiiOikein() {
        assertTrue(merkkitarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("tyui4"));
        assertTrue(merkkitarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("q4werty"));
        assertTrue(merkkitarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("1"));
        assertTrue(merkkitarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("€!€!€!,.-;;:__::;;9"));
        assertTrue(merkkitarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("5€!€!€!,.-;;:__::;;"));
        assertTrue(merkkitarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("3453453"));
        assertFalse(merkkitarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron(""));
        assertFalse(merkkitarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron(" "));
        assertFalse(merkkitarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("      "));
        assertFalse(merkkitarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("weferg"));
        assertFalse(merkkitarkistin.sisaltaakoMerkkijonoVahintaanYhdenNumeron("!!!åäö!"));
    }
    
    @Test
    public void voikoMerkkijononMuuttaaKokonaisluvuksiToimiiOikein() {
        assertTrue(merkkitarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("0008"));
        assertTrue(merkkitarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("123"));
        assertTrue(merkkitarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("0030001020"));
        assertFalse(merkkitarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("a0008"));
        assertFalse(merkkitarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("12r3"));
        assertFalse(merkkitarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("0030001020e"));
        assertFalse(merkkitarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi("00300 01020"));
    }

    @Test
    public void onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneistaToimiiOikein() {
        assertTrue(merkkitarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(""));
        assertTrue(merkkitarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(" "));
        assertTrue(merkkitarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista("      "));
        assertFalse(merkkitarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista("a"));
        assertFalse(merkkitarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista("."));
        assertFalse(merkkitarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista("         0"));
        assertFalse(merkkitarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista("å         "));
        assertFalse(merkkitarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista("     5    "));
    }

    @Test
    public void onkoEmailOsoiteValidiToimiiOikein() {
        assertTrue(merkkitarkistin.onkoEmailOsoiteValidi("oijdwed@dwjkel.com"));
        assertTrue(merkkitarkistin.onkoEmailOsoiteValidi("a@b.fi"));
        assertTrue(merkkitarkistin.onkoEmailOsoiteValidi("ferferferf@ferferf.oijekrferf"));
        assertFalse(merkkitarkistin.onkoEmailOsoiteValidi("ferferatferferf.oom"));
        assertFalse(merkkitarkistin.onkoEmailOsoiteValidi("ferferferf@ferferf dot fi"));
    }
}
