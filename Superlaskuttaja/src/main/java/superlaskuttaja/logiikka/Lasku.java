/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.logiikka;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Luokan ilmentymään voi tallettaa yhden laskun tiedot. Luokka tarjoaa metodit
 * laskun tietojen oikeanlaisuuden tarkistamiseen.
 *
 * @author Augustus58
 */
public class Lasku {

    /**
     * Tieto tilinumerosta tulee tätä kautta.
     */
    private Laskuttaja laskuttaja;
    /**
     * Laskun asiakas.
     */
    private Asiakas asiakas;
    /**
     * Laskun päiväys.
     */
    private GregorianCalendar paivays;
    /**
     * Maksuaika päivissä.
     */
    private Integer maksuaika;
    /**
     * Tämä numerointi koskee kaikkia laskuja. Eli kaikkien asiakkaiden laskut
     * samalla juoksevalla numeroinnilla.
     */
    private Integer laskunNumero;
    /**
     * Laskun eräpäivä.
     */
    private GregorianCalendar erapaiva;
    /**
     * Laskun viivästyskorko. Esim. 8.
     */
    private Integer viivastyskorko;
    /**
     * Laskun viite tarkisteella.
     */
    private Viite viiteTarkisteella;
    /**
     * Laskun maksuehto. Esim. 14 päivää netto.
     */
    private String maksuehto;
    /**
     * Laskuun liittyvät suoritteet.
     */
    private ArrayList<Suorite> suoritteet;
    /**
     * Laskun lisätiedot.
     */
    private String lisatiedot;
    /**
     * Laskun summa. Käytetty omaa luokkaa (LaskunSumma), koska mm.
     * pankkiviivakoodin (=virtuaaliviivakoodi) standardit vaativat tietyt rajat
     * euroille ja senteille. 0<=eurot<=999999. 0<=sentit<=99.
     */
    private LaskunSumma summa;
    /**
     * Laskun tietojen pohjalta muodostettu pankkiviivakoodi.
     */
    private Pankkiviivakoodi pankkiviivakoodi;
    /**
     * Tieto siitä, että onko laskun asiakas maksanut kys. laskun.
     */
    private Boolean onkoMaksettu;
    /**
     * Maksettuteksti tulee olla "Ei", jos laskua ei ole maksettu ja "Kyllä" jos
     * on maksettu.
     */
    private String maksettuTeksti;
    /**
     * Tarkistin laskun tietojen oikeanlaisuuden varmistamiseen.
     */
    private final MerkkiJaMerkkijonoTarkistin tarkistin;

    public Lasku(Laskuttaja laskuttaja, Asiakas asiakas, GregorianCalendar paivays, Integer laskunNumero, GregorianCalendar erapaiva, Integer viivastyskorko, Viite viiteTarkisteella, String maksuehto, ArrayList<Suorite> suoritteet, String lisatiedot, LaskunSumma summa, Pankkiviivakoodi pankkiviivakoodi) {
        this.laskuttaja = laskuttaja;
        this.asiakas = asiakas;
        this.paivays = paivays;
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
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
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

    public GregorianCalendar getPaivays() {
        return paivays;
    }

    public GregorianCalendar getErapaiva() {
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

    /**
     * Metodi kertoo onko asiakas maksanut laskun.
     *
     * @return Tieto laskun maksamisesta.
     */
    public Boolean isOnkoMaksettu() {
        return onkoMaksettu;
    }

    public String getMaksettuTeksti() {
        return maksettuTeksti;
    }

    public Integer getMaksuaika() {
        return maksuaika;
    }

    /**
     * Metodi antaa laskun tiedot taulukossa.
     * <p>
     * Tätä metodia tarvitaan erityisesti käyttöliittymän luokan LaskutTaulukko
     * ilmentymien muodostamiseen.
     *
     * @return Laskun tiedot taulukossa.
     */
    public Object[] laskunTiedotTaulukossa() {
        return (new Object[]{asiakas.getNimi(), asiakas.getAsiakasnumero(), viiteTarkisteella, laskunNumero, summa, paivays, erapaiva, maksettuTeksti});
    }

    public void setLaskuttaja(Laskuttaja laskuttaja) {
        this.laskuttaja = laskuttaja;
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    public void setPaivays(GregorianCalendar paivays) {
        this.paivays = paivays;
    }

    public void setMaksuaika(Integer maksuaika) {
        this.maksuaika = maksuaika;
    }

    public void setLaskunNumero(Integer laskunNumero) {
        this.laskunNumero = laskunNumero;
    }

    public void setErapaiva(GregorianCalendar erapaiva) {
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

    /**
     * Metodilla voidaan asettaa tieto siitä, että onko kys. lasku maksettu.
     * <p>
     * Kun tämä metodi suoritetaan, niin samalla suoritetaan metodi
     * paivitaMaksettuTeksti.
     *
     * @param onkoMaksettu Haluttu totuusarvo asetettavaksi tiedoksi siitä, että
     * onko lasku maksettu.
     */
    public void setOnkoMaksettu(Boolean onkoMaksettu) {
        this.onkoMaksettu = onkoMaksettu;
        paivitaMaksettuTeksti();
    }

    /**
     * Metodi voidaan päivittää attribuutti maksettuTeksti.
     * <p>
     * Attribuutti maksettuTeksti päivitetään attribuuttiin onkoMaksettu
     * perustuen.
     */
    public void paivitaMaksettuTeksti() {
        if (onkoMaksettu) {
            maksettuTeksti = "Kyllä";
        } else {
            maksettuTeksti = "Ei";
        }
    }

    /**
     * Metodi kertoo onko laskun viivästyskorko oikeanlainen.
     *
     * @return Tieto viivästyskoron oikeanlaisuudesta.
     */
    public Boolean onkoViivastyskorkoOikeanlainen() {
        if (viivastyskorko < 0 || viivastyskorko > 100) {
            return false;
        }
        return true;
    }

    /**
     * Metodi kertoo onko laskun maksuehto oikeanlainen.
     *
     * @return Tieto maksuehdon oikeanlaisuudesta.
     */
    public Boolean onkoMaksuehtoOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(maksuehto));
    }

    /**
     * Metodi luokan ilmentymien samuuden selvittämiseen.
     *
     * @param olio Samuusverrattava olio.
     * @return Tieto verrattavan olion ja kutsujaolion samuudesta.
     */
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

    /**
     * Metodi jossa tehdään equals-metodin samuusvertailut.
     * <p>
     * Ennen tämän metodin käyttöä tulee varmistaa, että argumentti ei ole null
     * ja että argumentin luokka on Lasku.
     *
     * @param olio Samuusverrattava olio.
     * @return Tieto verrattavan olion ja kutsujaolion tietojen samuudesta.
     */
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

    /**
     * Luokan Lasku hashCode-metodi.
     * <p>
     * HashCode muodostetaan summaamalla attribuuttien laskuttaja, asiakas,
     * paivays, laskunNumero, erapaiva, viiteTarkisteella, lisatiedot, summa ja
     * pankkiviivakoodi hashCodet.
     *
     * @return Kokonaisluku.
     */
    @Override
    public int hashCode() {
        return (laskuttaja.hashCode()
                + asiakas.hashCode()
                + paivays.hashCode()
                + laskunNumero.hashCode()
                + erapaiva.hashCode()
                + viiteTarkisteella.hashCode()
                + lisatiedot.hashCode()
                + summa.hashCode()
                + pankkiviivakoodi.hashCode());
    }
}
