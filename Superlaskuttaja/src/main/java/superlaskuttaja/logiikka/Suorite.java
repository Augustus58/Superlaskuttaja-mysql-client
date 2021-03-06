/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.logiikka;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Luokan ilmentymään voi tallettaa yhden suoritteen tiedot. Luokka tarjoaa
 * metodit tietojen oikeanlaisuuden tarkistamiseen.
 *
 * @author Augustus58
 */
public class Suorite {

    /**
     * Suoritteen asiakas.
     */
    private Asiakas asiakas;
    /**
     * Suoritteen kuvaus.
     */
    private String kuvaus;
    /**
     * Suoritteen pvm.
     */
    private GregorianCalendar pvm;
    /**
     * Suoritteen suorituksen määrä.
     */
    private Double maara;
    /**
     * Suoritteen määrän yksiköt.
     */
    private String maaranYksikot;
    /**
     * Yhtä yksikköä vastaava hinta.
     */
    private Double aHinta;
    /**
     * Suoritteen alv-prosentti. Riippuu suoritteen (esim. seinän maalaaminen)
     * muodostumishetkellä voimassa olevista verolaeista. Esim. 24.
     */
    private Integer alvProsentti;
    /**
     * Suoritteen alv yht.
     */
    private Double alv;
    /**
     * Suoritteen yhteishinta (alv mukana).
     */
    private Double yht;
    /**
     * Tieto siitä, että onko suorite laskutettu vai ei.
     */
    private Boolean onkoLaskutettu;
    /**
     * laskutettuTeksti riippuu tiedosta, että onko suorite laskutettu vai ei
     * (eli onko se jollain laskulla). Jos suorite on laskutettu, niin teksti on
     * "Laskulla (kys. laskun numero)". Jos ei ole laskutettu niin teksti on
     * "Ei".
     */
    private String laskutettuTeksti;
    /**
     * Suoritteeseen liittyvä lasku. Tämä on tiedossa vain, jos suorite on
     * laskutettu.
     */
    private Lasku lasku;
    /**
     * Pvm-formaatti, jota tarvitaan mm. suoritteen tietojen esittämiseen.
     */
    private final SimpleDateFormat pvmFormaatti;
    /**
     * Tarkistin, jota käytetään suoritteen tietojen oikeanlaisuuden
     * tarkistamiseen.
     */
    private final MerkkiJaMerkkijonoTarkistin tarkistin;

    public Suorite(Asiakas asiakas, String kuvaus, GregorianCalendar pvm, Double maara, String maaranYksikot, Double aHinta, Integer alvProsentti) {
        this.asiakas = asiakas;
        this.kuvaus = kuvaus;
        this.pvm = pvm;
        this.maara = maara;
        this.maaranYksikot = maaranYksikot;
        this.aHinta = aHinta;
        this.alvProsentti = alvProsentti;
        this.pvmFormaatti = new SimpleDateFormat("dd.MM.yyyy");
        this.onkoLaskutettu = false;
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
        this.laskutettuTeksti = "Ei";
        paivitaLaskutettuTeksti();
        laskeAlv();
        laskeYht();
    }
    
    public Suorite(Asiakas asiakas, String kuvaus, GregorianCalendar pvm, Double maara, String maaranYksikot, Double aHinta, Integer alvProsentti, Lasku lasku) {
        this.asiakas = asiakas;
        this.kuvaus = kuvaus;
        this.pvm = pvm;
        this.maara = maara;
        this.maaranYksikot = maaranYksikot;
        this.aHinta = aHinta;
        this.alvProsentti = alvProsentti;
        this.pvmFormaatti = new SimpleDateFormat("dd.MM.yyyy");
        this.lasku = lasku;
        this.onkoLaskutettu = true;
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
        paivitaLaskutettuTeksti();
        laskeAlv();
        laskeYht();
    }

    /**
     * Metodi antaa suoritteen tiedot taulukossa.
     * <p>
     * Tätä metodia tarvitaan erityisesti käyttöliittymän luokan
     * SuoritteetTaulukko ilmentymien muodostamiseen.
     *
     * @return Suoritteen tiedot taulukossa.
     */
    public Object[] suoritteenTiedotTaulukossa() {
        return (new Object[]{asiakas.getNimi(), kuvaus, pvm, maara, maaranYksikot, aHinta, alvProsentti, alv, yht, laskutettuTeksti});
    }

    public Asiakas getAsiakas() {
        return asiakas;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public GregorianCalendar getPvm() {
        return pvm;
    }

    public Boolean getOnkoLaskutettu() {
        return onkoLaskutettu;
    }

    public SimpleDateFormat getPvmFormaatti() {
        return pvmFormaatti;
    }

    public Double getMaara() {
        return maara;
    }

    public String getMaaranYksikot() {
        return maaranYksikot;
    }

    public Double getaHinta() {
        return aHinta;
    }

    public Integer getAlvProsentti() {
        return alvProsentti;
    }

    public Double getAlv() {
        return alv;
    }

    public Double getYht() {
        return yht;
    }

    public String getLaskutettuTeksti() {
        return laskutettuTeksti;
    }

    /**
     * Metodi luokan ilmentymän attribuutin lasku palauttamiseen.
     * <p>
     * attribuutti lasku palautetaa vain, jos attribuutti onkoLaskutettu on
     * true. Muussa tapauksessa palautetaan null.
     *
     * @return Palauttaa attribuutin lasku.
     */
    public Lasku getLasku() {
        if (onkoLaskutettu) {
            return lasku;
        }
        return null;
    }

    @Override
    public String toString() {
        return (kuvaus + "  " + pvmFormaatti.format(pvm.getTime()) + "  " + maara + maaranYksikot);
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public void setPvm(GregorianCalendar pvm) {
        this.pvm = pvm;
    }

    public void setMaara(Double maara) {
        this.maara = maara;
    }

    public void setMaaranYksikot(String maaranYksikot) {
        this.maaranYksikot = maaranYksikot;
    }

    public void setaHinta(Double aHinta) {
        this.aHinta = aHinta;
    }

    public void setAlvProsentti(Integer alvProsentti) {
        this.alvProsentti = alvProsentti;
    }

    /**
     * Tällä metodilla voidaan asettaa suoritteeseen liittyvä lasku.
     * <p>
     * Samalla asetetetaan attribuutti onkoLaskutettu todeksi ja suoritetaan
     * metodi paivitaLaskutettuTeksti.
     *
     * @param lasku Lasku, joka halutaan asettaa suoritteeseen liittyväksi.
     */
    public void setLasku(Lasku lasku) {
        this.lasku = lasku;
        onkoLaskutettu = true;
        paivitaLaskutettuTeksti();
    }

    /**
     * Metodi laskee olion attribuutin alv attribuuttien alvProsentti, aHinta ja
     * maara perusteella.
     */
    private void laskeAlv() {
        this.alv = (alvProsentti / 100.0) * (aHinta * maara);
    }

    /**
     * Metodi laskee olion attribuutin yht attribuuttien aHinta, maara ja alv
     * perusteella.
     */
    private void laskeYht() {
        this.yht = (aHinta * maara) + alv;
    }

    /**
     * Metodilla voi päivittää olion attribuutin laskutettuTeksti.
     * <p>
     * Jos attribuutti onkoLaskutettu on true, niin teksti päivitetään muotoon
     * "Laskulla (laskun numero)". Muulloin teksti päivitetään muotoon "Ei".
     */
    public final void paivitaLaskutettuTeksti() {
        if (onkoLaskutettu) {
            laskutettuTeksti = "Laskulla " + lasku.getLaskunNumero().toString();
        } else {
            laskutettuTeksti = "Ei";
        }
    }

    /**
     * Metodilla voi poistaa tiedot suoritteeseen liittyvästä laskusta.
     */
    public void poistaLasku() {
        this.lasku = null;
        onkoLaskutettu = false;
        paivitaLaskutettuTeksti();
    }

    /**
     * Metodi kertoo onko suoritteen tiedot oikeanlaiset.
     *
     * @return Tieto tietojen oikeellisuudesta.
     */
    public boolean onkoTiedotOikeanlaisetPaitsiPvm() {
        if (onkoKuvausOikeanlainen()
                && onkoMaaraOikeanlainen()
                && onkoMaaranYksikotOikeanlainen()
                && onkoAHintaOikeanlainen()
                && onkoAlvProsenttiOikeanlainen()) {
            return true;
        }
        return false;
    }

    /**
     * Metodi kertoo onko suoritteen kuvaus oikeanlainen.
     *
     * @return Tieto kuvauksen oikeanlaisuudesta.
     */
    public boolean onkoKuvausOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(kuvaus));
    }

    /**
     * Metodi kertoo onko suoritteen attribuutti maara oikeanlainen.
     *
     * @return Tieto attribuutin maara oikeanlaisuudesta.
     */
    public boolean onkoMaaraOikeanlainen() {
        if (maara > 0) {
            return true;
        }
        return false;
    }

    /**
     * Metodi kertoo onko suoritteen määrän yksiköt oikeanlainen.
     *
     * @return Tieto määrän yksiköiden oikeanlaisuudesta.
     */
    public boolean onkoMaaranYksikotOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(maaranYksikot));
    }

    /**
     * Metodi kertoo onko suoritteen à hinta oikeanlainen.
     *
     * @return Tieto à hinnan oikeanlaisuudesta.
     */
    public boolean onkoAHintaOikeanlainen() {
        if (aHinta > 0) {
            return true;
        }
        return false;
    }

    /**
     * Metodi kertoo onko suoritteen alv-prosentti oikeanlainen.
     *
     * @return Tieto alv-prosentin oikeanlaisuudesta.
     */
    public boolean onkoAlvProsenttiOikeanlainen() {
        if (alvProsentti >= 0 && alvProsentti <= 100) {
            return true;
        }
        return false;
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
     * ja että argumentin luokka on Suorite.
     *
     * @param olio Samuusverrattava olio.
     * @return Tieto verrattavan olion ja kutsujaolion tietojen samuudesta.
     */
    private boolean teeEqualsVertailut(Object olio) {
        Suorite verrattava = (Suorite) olio;
        if (this.asiakas.equals(verrattava.asiakas)
                && this.kuvaus.equals(verrattava.kuvaus)
                && this.pvm.equals(verrattava.pvm)
                && this.maara.equals(verrattava.maara)
                && this.maaranYksikot.equals(verrattava.maaranYksikot)
                && this.aHinta.equals(verrattava.aHinta)
                && this.alvProsentti.equals(verrattava.alvProsentti)
                && this.alv.equals(verrattava.alv)
                && this.yht.equals(verrattava.yht)
                && this.onkoLaskutettu.equals(verrattava.onkoLaskutettu)
                && this.laskutettuTeksti.equals(verrattava.laskutettuTeksti)) {
            return true;
        }
        return false;
    }

    /**
     * Luokan Suorite hashCode-metodi.
     * <p>
     * HashCode muodostetaan summaamalla attribuuttien asiakas, kuvaus, pvm ja
     * maara hashCodet.
     *
     * @return Kokonaisluku.
     */
    @Override
    public int hashCode() {
        return (asiakas.hashCode()
                + kuvaus.hashCode()
                + pvm.hashCode()
                + maara.hashCode());
    }
}
