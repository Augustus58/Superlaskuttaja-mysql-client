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

    private Laskuttaja laskuttaja; //Tilinumero tätä kautta.
    private Asiakas asiakas;

    private Date paivays;
    private Integer laskunNumero; //Tämä numerointi koskee kaikkia laskuja. Eli kaikkien asiakkaiden laskut samalla numeroinnilla.
    private Date erapaiva;
    private Integer viivastyskorko; //Esim 8.
    private Viite viiteTarkisteella;
    private String maksuehto;

    private ArrayList<Suorite> suoritteet;
    private String lisatiedot;
    private LaskunSumma summa; //Käytetty omaa luokkaa (LaskunSumma), koska mm. pankkiviivakoodin (=virtuaaliviivakoodi) standardit vaativat tietyt rajat euroille ja senteille. 0<=eurot<=999999. 0<=sentit<=99.
    private Pankkiviivakoodi pankkiviivakoodi;

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

    public Laskuttaja getLaskuttaja() {
        return laskuttaja;
    }

    public Asiakas getAsiakas() {
        return asiakas;
    }

    public Date getPaivays() {
        return paivays;
    }

    public Date getErapaiva() {
        return erapaiva;
    }

    public Integer getViivastyskorko() {
        return viivastyskorko;
    }

    public Viite getViiteTarkisteella() {
        return viiteTarkisteella;
    }

    public String getMaksuehto() {
        return maksuehto;
    }

    public ArrayList<Suorite> getSuoritteet() {
        return suoritteet;
    }

    public String getLisatiedot() {
        return lisatiedot;
    }

    public LaskunSumma getSumma() {
        return summa;
    }

    public Pankkiviivakoodi getPankkiviivakoodi() {
        return pankkiviivakoodi;
    }

    public Boolean isOnkoMaksettu() {
        return onkoMaksettu;
    }

    public String getMaksettuTeksti() {
        return maksettuTeksti;
    }
    
    public Object[] laskunTiedotTaulukossa() {
        return (new Object[]{asiakas.getNimi(), asiakas.getAsiakasnumero(), viiteTarkisteella, laskunNumero, summa, paivays, erapaiva, maksettuTeksti});
    }

    public void setLaskuttaja(Laskuttaja laskuttaja) {
        this.laskuttaja = laskuttaja;
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    public void setPaivays(Date paivays) {
        this.paivays = paivays;
    }

    public void setLaskunNumero(Integer laskunNumero) {
        this.laskunNumero = laskunNumero;
    }

    public void setErapaiva(Date erapaiva) {
        this.erapaiva = erapaiva;
    }

    public void setViivastyskorko(Integer viivastyskorko) {
        this.viivastyskorko = viivastyskorko;
    }

    public void setViiteTarkisteella(Viite viiteTarkisteella) {
        this.viiteTarkisteella = viiteTarkisteella;
    }

    public void setMaksuehto(String maksuehto) {
        this.maksuehto = maksuehto;
    }

    public void setSuoritteet(ArrayList<Suorite> suoritteet) {
        this.suoritteet = suoritteet;
    }

    public void setLisatiedot(String lisatiedot) {
        this.lisatiedot = lisatiedot;
    }

    public void setSumma(LaskunSumma summa) {
        this.summa = summa;
    }

    public void setPankkiviivakoodi(Pankkiviivakoodi pankkiviivakoodi) {
        this.pankkiviivakoodi = pankkiviivakoodi;
    }

    public void setOnkoMaksettu(Boolean onkoMaksettu) {
        this.onkoMaksettu = onkoMaksettu;
    }

    public void paivitaMaksettuTeksti() {
        if (onkoMaksettu) {
            maksettuTeksti = "Kyllä";
        } else {maksettuTeksti = "Ei";}
    }
    
    @Override
    public boolean equals(Object olio) {
        if (olio == null) {
            return false;
        }
        if (getClass() != olio.getClass()) {
            return false;
        }
        return (teeEqualsVertailut(olio));
    }

    private boolean teeEqualsVertailut(Object olio) {
        Lasku verrattava = (Lasku) olio;
        if (this.laskuttaja.equals(verrattava.laskuttaja)
                && this.asiakas.equals(verrattava.asiakas)
                && this.paivays.equals(verrattava.paivays)
                && this.viivastyskorko.equals(verrattava.viivastyskorko)
                && this.viiteTarkisteella.equals(verrattava.viiteTarkisteella)
                && this.maksuehto.equals(verrattava.maksuehto)
                && this.suoritteet.equals(verrattava.suoritteet)
                && this.lisatiedot.equals(verrattava.lisatiedot)
                && this.summa.equals(verrattava.summa)
                && this.pankkiviivakoodi.equals(verrattava.pankkiviivakoodi)
                && this.onkoMaksettu.equals(verrattava.onkoMaksettu)
                && this.maksettuTeksti.equals(verrattava.maksettuTeksti)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (laskuttaja.hashCode() + asiakas.hashCode() + paivays.hashCode() + laskunNumero.hashCode() + erapaiva.hashCode() + viivastyskorko.hashCode() + viiteTarkisteella.hashCode() + maksuehto.hashCode() + suoritteet.hashCode() + lisatiedot.hashCode() + summa.hashCode() + pankkiviivakoodi.hashCode() + onkoMaksettu.hashCode() + maksettuTeksti.hashCode());
    }
}
