/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Augustus58
 */
public class Lasku {

    private final Laskuttaja laskuttaja; //Tilinumero tätä kautta.
    private final Asiakas asiakas;

    private final Date paivays;
    private final Integer laskunNumero; //Tämä numerointi koskee kaikkia laskuja. Eli kaikkien asiakkaiden laskut samalla numeroinnilla.
    private final Date erapaiva;
    private final Integer viivastyskorko; //Esim 8.
    private final Viite viiteTarkisteella;
    private final String maksuehto;

    private final ArrayList<Suorite> suoritteet;
    private final String lisatiedot;
    private final LaskunSumma summa; //Käytetty omaa luokkaa (LaskunSumma), koska mm. pankkiviivakoodin (=virtuaaliviivakoodi) standardit vaativat tietyt rajat euroille ja senteille. 0<=eurot<=999999. 0<=sentit<=99.
    private final Pankkiviivakoodi pankkiviivakoodi;

    private Boolean onkoMaksettu;
    private String maksettuTeksti;

    public Lasku(Laskuttaja laskuttaja, Asiakas asiakas, Date lahetysPaiva, Integer laskunNumero, Date erapaiva, Integer viivastyskorko, Viite viiteTarkisteella, String maksuehto, ArrayList<Suorite> suoritteet, String lisatiedot, LaskunSumma summa, Pankkiviivakoodi pankkiviivakoodi) {
        this.laskuttaja = laskuttaja;
        this.asiakas = asiakas;
        this.paivays = lahetysPaiva;
        this.laskunNumero = laskunNumero;
        this.erapaiva = erapaiva;
        this.viivastyskorko = viivastyskorko;
        this.viiteTarkisteella = viiteTarkisteella;
        this.maksuehto = maksuehto;
        this.suoritteet = suoritteet;
        this.lisatiedot = lisatiedot;
        this.summa = summa;
        this.pankkiviivakoodi = pankkiviivakoodi;
        this.onkoMaksettu = false;
        this.maksettuTeksti = "Ei";
    }

    public Integer getLaskunNumero() {
        return laskunNumero;
    }

    public Object[] laskunTiedotTaulukossa() {
        return (new Object[]{asiakas.getNimi(), asiakas.getAsiakasnumero(), viiteTarkisteella, laskunNumero, summa, paivays, erapaiva, maksettuTeksti});
    }

    @Override
    public boolean equals(Object olio) {
        if (olio == null) {
            return false;
        }
        if (getClass() != olio.getClass()) {
            return false;
        }
        Lasku verrattava = (Lasku) olio;
        if (!this.laskuttaja.equals(verrattava.laskuttaja)) {
            return false;
        }
        if (!this.asiakas.equals(verrattava.asiakas)) {
            return false;
        }
        if (!this.paivays.equals(verrattava.paivays)) {
            return false;
        }
        if (!this.viivastyskorko.equals(verrattava.viivastyskorko)) {
            return false;
        }
        if (!this.viiteTarkisteella.equals(verrattava.viiteTarkisteella)) {
            return false;
        }
        if (!this.maksuehto.equals(verrattava.maksuehto)) {
            return false;
        }
        if (!this.suoritteet.equals(verrattava.suoritteet)) {
            return false;
        }
        if (!this.lisatiedot.equals(verrattava.lisatiedot)) {
            return false;
        }
        if (!this.summa.equals(verrattava.summa)) {
            return false;
        }
        if (!this.pankkiviivakoodi.equals(verrattava.pankkiviivakoodi)) {
            return false;
        }
        if (!this.onkoMaksettu.equals(verrattava.onkoMaksettu)) {
            return false;
        }
        if (!this.maksettuTeksti.equals(verrattava.maksettuTeksti)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (laskuttaja.hashCode() + asiakas.hashCode() + paivays.hashCode() + laskunNumero.hashCode() + erapaiva.hashCode() + viivastyskorko.hashCode() + viiteTarkisteella.hashCode() + maksuehto.hashCode() + suoritteet.hashCode() + lisatiedot.hashCode() + summa.hashCode() + pankkiviivakoodi.hashCode() + onkoMaksettu.hashCode() + maksettuTeksti.hashCode());
    }
}
