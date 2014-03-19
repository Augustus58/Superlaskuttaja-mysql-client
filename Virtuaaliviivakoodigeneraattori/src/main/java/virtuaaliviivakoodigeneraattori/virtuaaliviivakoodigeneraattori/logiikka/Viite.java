/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import java.util.ArrayList;

/**
 *
 * @author Augustus58
 */

// Tämän luokan metodien toiminnan perustelut löytyy osoitteesta
// http://www.fkl.fi/teemasivut/sepa/tekninen_dokumentaatio/Dokumentit/kotimaisen_viitteen_rakenneohje.pdf
public class Viite {

    private final String viiteTarkisteella;
    private final MerkkiTarkistin merkkitarkistin;
// Konstruktorille tulee antaa viite ilman tarkistetta.
    public Viite(String viite) {
        this.viiteTarkisteella = muodostaTarkisteellinenViite(viite);
        this.merkkitarkistin = new MerkkiTarkistin();
    }

    public Boolean onkoViiteKelvollinen(String viiteIlmanTarkistetta) {
        if (viiteIlmanTarkistetta.length() < 3 || viiteIlmanTarkistetta.length() > 19) {
            return false;
        }
        for (int i = 0; i < viiteIlmanTarkistetta.length(); i++) {
            if (!this.merkkitarkistin.onkoMerkkiNumero(viiteIlmanTarkistetta.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String muodostaTarkisteellinenViite(String viite) {
        return (viite + laskeTarkiste(viite).toString());
    }

    // Tähän tulee tehdä tarkisteita huonojen syötteiden varalle.
    private Integer laskeTarkiste(String viite) {
        Integer summa = laskeTarkisteenSumma(viite);
        int alasPyoristettyOsamaara;
        Integer tarkiste;
        alasPyoristettyOsamaara = summa / 10;
        tarkiste = (10 * (alasPyoristettyOsamaara + 1) - summa);
        if (tarkiste == 10) {
            return 0;
        }
        return (tarkiste);
    }

    private Integer laskeTarkisteenSumma(String viite) {
        Integer summa = 0;
        ArrayList<Integer> numerolista = luoPainoarvonumerolista();
        for (int i = 1; i <= viite.length(); i++) {
            summa = summa + numerolista.get(i - 1) * Integer.parseInt(viite.substring(viite.length() - i, viite.length() - i + 1));
        }
        return summa;
    }

    private ArrayList<Integer> luoPainoarvonumerolista() {
        ArrayList<Integer> numerolista = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            numerolista.add(7);
            numerolista.add(3);
            numerolista.add(1);
        }
        return numerolista;
    }

    public String getViiteTarkisteellaEtunollillaPituus20() {
        String etunollat = "";
        while ((etunollat + this.viiteTarkisteella).length() < 20) {
            etunollat = etunollat + "0";
        }
        return (etunollat + this.viiteTarkisteella);
    }

    public String getViite() {
        return this.viiteTarkisteella;
    }
}
