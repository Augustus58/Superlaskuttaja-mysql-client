/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

/**
 * Luokan ilmentymään voi tallettaa yhden asiakkaan tiedot. Luokka tarjoaa
 * metodit tietojen oikeanlaisuuden tarkistamiseen.
 *
 * @author Augustus58
 */
public class Asiakas {

    private String asiakasnumero;
    private String nimi;
    private String katuosoite;
    private String postinumero;
    private String kaupunki;
    private Integer laskujaLahetetty;
    private MerkkiJaMerkkijonoTarkistin tarkistin;

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
     *
     * @return Tieto asiakasnumeron oikeanlaisuudesta.
     */
    public Boolean onkoAsiakasnumeroOikeanlainen() {
        return (tarkistin.koostuukoMerkkijonoNumeroista(asiakasnumero));
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

    public String getKatuosoite() {
        return katuosoite;
    }

    public String getPostinumero() {
        return postinumero;
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
