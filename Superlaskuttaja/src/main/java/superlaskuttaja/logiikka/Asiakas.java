/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.logiikka;

/**
 * Luokan ilmentymään voi tallettaa yhden asiakkaan tiedot. Luokka tarjoaa
 * metodit tietojen oikeanlaisuuden tarkistamiseen.
 *
 * @author Augustus58
 */
public class Asiakas {

    /**
     * Numero asiakkaan yksilöimiseen. Asiakasnumeron tulee olla vähintään kaksi
     * merkkiä pitkä ja siinä ei saa olla etunollia, koska sitä voidaan käyttää
     * viitteiden muodostamiseen.
     */
    private String asiakasnumero;
    /**
     * Asiakkaan nimi.
     */
    private String nimi;
    /**
     * Asiakkaan katuosoite.
     */
    private String katuosoite;
    /**
     * Asiakkaan postinumero. Esim. 00180.
     */
    private String postinumero;
    /**
     * Asiakkaan kaupunki. Esim. Helsinki.
     */
    private String kaupunki;
    /**
     * Kokonaisluku, joka kertyy kys. asiakkaalle lähetettyjen laskun
     * lukumäärän. Tätä tietoa tarvitaan mm. laskujen viitteiden muodostamiseen.
     */
    private Integer laskujaLahetetty;
    /**
     * Tarkistimella voidaan tarkistaa, että asiakkaalle tulee oikeanlaiset tiedot.
     */
    private final MerkkiJaMerkkijonoTarkistin tarkistin;

    public Asiakas(String asiakasnumero, String nimi, String katuosoite, String postinumero, String kaupunki, Integer laskujaLahetetty) {
        this.asiakasnumero = asiakasnumero;
        this.nimi = nimi;
        this.katuosoite = katuosoite;
        this.postinumero = postinumero;
        this.laskujaLahetetty = laskujaLahetetty;
        this.kaupunki = kaupunki;
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
    }

    /**
     * Metodi kertoo onko asiakkaan tiedot oikeanlaiset.
     *
     * @return Tieto tietojen oikeellisuudesta.
     */
    public Boolean onkoTiedotOikeanlaiset() {
        if (onkoNimiOikeanlainen()
                && onkoKaupunkiOikeanlainen()
                && onkoKatuosoiteOikeanlainen()
                && onkoAsiakasnumeroOikeanlainen()
                && onkoPostinumeroOikeanlainen()
                && onkoLaskujaLahetettyOikeanlainen()) {
            return true;
        }
        return false;
    }

    /**
     * Metodi kertoo onko asiakkaan nimi oikeanlainen.
     *
     * @return Tieto nimen oikeanlaisuudesta.
     */
    public Boolean onkoNimiOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(nimi));
    }

    /**
     * Metodi kertoo onko asiakkaan kaupunki oikeanlainen.
     *
     * @return Tieto kaupungin oikeanlaisuudesta.
     */
    public Boolean onkoKaupunkiOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(kaupunki));
    }

    /**
     * Metodi kertoo onko asiakkaan katuosoite oikeanlainen.
     *
     * @return Tieto katuosoitteen oikeanlaisuudesta.
     */
    public Boolean onkoKatuosoiteOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(katuosoite));
    }

    /**
     * Metodi kertoo onko asiakkaan asiakasnumero oikeanlainen.
     * <p>
     * Asiakasnumeron tulee olla vähintään kaksi merkkiä pitkä ja siinä ei saa
     * olla etunollia, koska sitä voidaan käyttää viitteiden muodostamiseen.
     *
     * @return Tieto asiakasnumeron oikeanlaisuudesta.
     */
    public Boolean onkoAsiakasnumeroOikeanlainen() {
        if (!tarkistin.koostuukoMerkkijonoNumeroista(asiakasnumero)) {
            return false;
        }
        if (asiakasnumero.length() < 2) {
            return false;
        }
        if (tarkistin.onkoMerkkijononEnsimmainenMerkkiNolla(asiakasnumero)) {
            return false;
        }
        return true;
    }

    /**
     * Metodi kertoo onko asiakkaan postinumero oikeanlainen.
     *
     * @return Tieto postinumeron oikeanlaisuudesta.
     */
    public Boolean onkoPostinumeroOikeanlainen() {
        return (tarkistin.koostuukoMerkkijonoNumeroista(postinumero));
    }

    /**
     * Metodi kertoo onko asiakkaan lähetettyjen laskujen lkm oikeanlainen.
     *
     * @return Tieto lähetettyjen laskujen lkm:n oikeanlaisuudesta.
     */
    public Boolean onkoLaskujaLahetettyOikeanlainen() {
        if (laskujaLahetetty >= 0) {
            return true;
        }
        return false;
    }

    public String getAsiakasnumero() {
        return asiakasnumero;
    }

    public String getNimi() {
        return nimi;
    }

    public String getKaupunki() {
        return kaupunki;
    }

    public Integer getLaskujaLahetetty() {
        return laskujaLahetetty;
    }

    /**
     * Metodi antaa uuden lähettyjen laskujen määrän.
     *
     * @return (laskujaLahetetty + 1).
     */
    public Integer annaLaskujaLahetettyPlusYksi() {
        return (laskujaLahetetty + 1);
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public String getPostinumero() {
        return postinumero;
    }

    /**
     * Metodin suorittaminen kasvattaa attribuutin laskukujaLahetetty arvoa
     * yhdellä.
     *
     */
    public void kasvataLahetettyjenLaskujenMaaraaYhdella() {
        laskujaLahetetty++;
    }

    /**
     * Metodi antaa asiakkaan tiedot taulukossa.
     * <p>
     * Tätä metodia tarvitaan erityisesti käyttöliittymän luokan
     * AsiakkaatTaulukko ilmentymien muodostamiseen.
     *
     * @return Asiakkaan tiedot taulukossa.
     */
    public Object[] asiakkaanTiedotTaulukossa() {
        return (new Object[]{nimi, katuosoite, postinumero, kaupunki, asiakasnumero, laskujaLahetetty});
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
     * ja että argumentin luokka on Asiakas.
     *
     * @param olio Samuusverrattava olio.
     * @return Tieto verrattavan olion ja kutsujaolion tietojen samuudesta.
     */
    private boolean teeEqualsVertailut(Object olio) {
        Asiakas verrattava = (Asiakas) olio;

        if (this.asiakasnumero.equals(verrattava.asiakasnumero)
                && this.katuosoite.equals(verrattava.katuosoite)
                && this.kaupunki.equals(verrattava.kaupunki)
                && this.laskujaLahetetty.equals(verrattava.laskujaLahetetty)
                && this.nimi.equals(verrattava.nimi)
                && this.postinumero.equals(verrattava.postinumero)) {
            return true;
        }

        return false;
    }

    /**
     * Luokan Asiakas hashCode-metodi.
     * <p>
     * HashCode muodostetaan summaamalla attribuuttien laskujaLahetetty,
     * asiakasnumero, nimi ja katuosoite hashCodet.
     *
     * @return Kokonaisluku.
     */
    @Override
    public int hashCode() {
        return (laskujaLahetetty + asiakasnumero.hashCode() + nimi.hashCode() + katuosoite.hashCode());
    }

    public void setAsiakasnumero(String asiakasnumero) {
        this.asiakasnumero = asiakasnumero;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }

    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }

    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }

    public void setLaskujaLahetetty(Integer laskujaLahetetty) {
        this.laskujaLahetetty = laskujaLahetetty;
    }

}
