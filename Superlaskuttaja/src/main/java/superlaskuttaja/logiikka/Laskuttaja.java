/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.logiikka;

/**
 * Luokan ilmentymään voi tallettaa laskuttajan tiedot. Luokka tarjoaa metodit
 * tietojen oikeanlaisuuden tarkistamiseen.
 *
 * @author Augustus58
 */
public class Laskuttaja {

    /**
     * Laskuttajan nimi.
     */
    private String nimi;
    /**
     * Laskuttajan katuosoite.
     */
    private String katuosoite;
    /**
     * Laskuttajan postinumero.
     */
    private String postinumero;
    /**
     * Laskuttajan kaupunki.
     */
    private String kaupunki;
    /**
     * Laskuttajan yrityksen nimi.
     */
    private String yrityksenNimi;
    /**
     * Laskuttajan yrityksen alv-tunniste.
     */
    private String alvTunniste;
    /**
     * Laskuttajan tilinumero.
     */
    private Tilinumero tilinumero;
    /**
     * Laskuttajan puhelinnumero.
     */
    private String puhelinnumero;
    /**
     * Laskuttajan sähköpostiosoite.
     */
    private String sahkopostiOsoite;
    /**
     * Tieto laskuttajan lähetettyjen laskujen määrästä.
     */
    private Integer laskujaLahetetty;
    /**
     * Tarkistin, jolla voidaan tarkistaa laskuttajan tietojen oikeanlaisuus.
     */
    private final MerkkiJaMerkkijonoTarkistin tarkistin;

    public Laskuttaja(String nimi, String katuosoite, String postinumero, String kaupunki, String yrityksenNimi, String alvTunniste, Tilinumero tilinumero, String puhelinnumero, String sahkopostiOsoite, Integer laskujaLahetetty) {
        this.nimi = nimi;
        this.katuosoite = katuosoite;
        this.postinumero = postinumero;
        this.kaupunki = kaupunki;
        this.yrityksenNimi = yrityksenNimi;
        this.alvTunniste = alvTunniste;
        this.tilinumero = tilinumero;
        this.puhelinnumero = puhelinnumero;
        this.sahkopostiOsoite = sahkopostiOsoite;
        this.laskujaLahetetty = laskujaLahetetty;
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
    }
    
    /**
     * Metodi kertoo onko laskuttajan nimi oikeanlainen.
     *
     * @return Tieto nimen oikeanlaisuudesta.
     */
    public Boolean onkoNimiOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(nimi));
    }

    /**
     * Metodi kertoo onko laskuttajan katuosoite oikeanlainen.
     *
     * @return Tieto katuosoitteen oikeanlaisuudesta.
     */
    public Boolean onkoKatuosoiteOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(katuosoite));
    }

    /**
     * Metodi kertoo onko laskuttajan postinumero oikeanlainen.
     *
     * @return Tieto postinumeron oikeanlaisuudesta.
     */
    public Boolean onkoPostinumeroOikeanlainen() {
        return (tarkistin.koostuukoMerkkijonoNumeroista(postinumero));
    }

    /**
     * Metodi kertoo onko laskuttajan kaupunki oikeanlainen.
     *
     * @return Tieto kaupungin oikeanlaisuudesta.
     */
    public Boolean onkoKaupunkiOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(kaupunki));
    }

    /**
     * Metodi kertoo onko laskuttajan yrityksen nimi oikeanlainen.
     *
     * @return Tieto yrityksen nimen oikeanlaisuudesta.
     */
    public Boolean onkoYrityksenNimiOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(yrityksenNimi));
    }

    /**
     * Metodi kertoo onko laskuttajan alv-tunniste oikeanlainen.
     *
     * @return Tieto alv-tunnisteen oikeanlaisuudesta.
     */
    public Boolean onkoAlvOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(alvTunniste));
    }

    /**
     * Metodi kertoo onko laskuttajan tilinumero oikeanlainen.
     *
     * @return Tieto tilinumeron oikeanlaisuudesta.
     */
    public Boolean onkoTilinumeroOikeanlainen() {
        return (tilinumero.tarkistaTilinumero(tilinumero.getTilinumero()));
    }

    /**
     * Metodi kertoo onko laskuttajan pankin nimi oikeanlainen.
     *
     * @return Tieto pankin nimen oikeanlaisuudesta.
     */
    public Boolean onkoPankkiOikeanlainen() {
        return (tilinumero.onkoPankkiOikeanlainen());
    }

    /**
     * Metodi kertoo onko laskuttajan pankin "SWIFT BIC" oikeanlainen.
     *
     * @return Tieto pankin "SWIFT BIC"-koodin oikeanlaisuudesta.
     */
    public Boolean onkoSwiftBicOikeanlainen() {
        return (tilinumero.onkoSwiftBicOikeanlainen());
    }

    /**
     * Metodi kertoo onko laskuttajan puhelinnumero oikeanlainen.
     *
     * @return Tieto puhelinnumeron oikeanlaisuudesta.
     */
    public Boolean onkoPuhelinnumeroOikeanlainen() {
        return (tarkistin.sisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista(puhelinnumero));
    }

    /**
     * Metodi kertoo onko laskuttajan sähköpostiosoite oikeanlainen.
     *
     * @return Tieto sähköpostiosoitteen oikeanlaisuudesta.
     */
    public Boolean onkoSahkopostiOikeanlainen() {
        return (tarkistin.onkoEmailOsoiteValidi(sahkopostiOsoite));
    }

    /**
     * Metodi kertoo onko laskuttajan sähköpostiosoite oikeanlainen.
     *
     * @return Tieto sähköpostiosoitteen oikeanlaisuudesta.
     */
    public Boolean onkoLaskujaLahetettyOikeanlainen() {
        if (laskujaLahetetty >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Metodi kertoo onko laskuttajan tiedot oikeanlaiset.
     *
     * @return Tieto tietojen oikeellisuudesta.
     */
    public boolean onkoTiedotOikeanlaiset() {
        if (onkoNimiOikeanlainen()
                && onkoKatuosoiteOikeanlainen()
                && onkoPostinumeroOikeanlainen()
                && onkoKaupunkiOikeanlainen()
                && onkoYrityksenNimiOikeanlainen()
                && onkoAlvOikeanlainen()
                && onkoTilinumeroOikeanlainen()
                && onkoPankkiOikeanlainen()
                && onkoSwiftBicOikeanlainen()
                && onkoPuhelinnumeroOikeanlainen()
                && onkoSahkopostiOikeanlainen()
                && onkoLaskujaLahetettyOikeanlainen()) {
            return true;
        }
        return false;
    }

    public String getNimi() {
        return nimi;
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public String getKaupunki() {
        return kaupunki;
    }

    public String getYrityksenNimi() {
        return yrityksenNimi;
    }

    public String getAlvTunniste() {
        return alvTunniste;
    }

    public Tilinumero getTilinumero() {
        return tilinumero;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public String getSahkopostiOsoite() {
        return sahkopostiOsoite;
    }

    public Integer getLaskujaLahetetty() {
        return laskujaLahetetty;
    }

    /**
     * Metodi antaa uuden laskun numeron.
     *
     * @return Laskun numero uuteen laskuun. Muodostetaan lisäämällä yksi
     * attribuuttiin laskujaLahetetty.
     */
    public Integer annaUusiLaskunNumero() {
        return (laskujaLahetetty + 1);
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

    public void setYrityksenNimi(String yrityksenNimi) {
        this.yrityksenNimi = yrityksenNimi;
    }

    public void setAlv(String alvTunniste) {
        this.alvTunniste = alvTunniste;
    }

    public void setTilinumero(Tilinumero tilinumero) {
        this.tilinumero = tilinumero;
    }

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    public void setSahkopostiOsoite(String sahkopostiOsoite) {
        this.sahkopostiOsoite = sahkopostiOsoite;
    }

    public void setLaskujaLahetetty(Integer laskujaLahetetty) {
        this.laskujaLahetetty = laskujaLahetetty;
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
     * ja että argumentin luokka on Laskuttaja.
     *
     * @param olio Samuusverrattava olio.
     * @return Tieto verrattavan olion ja kutsujaolion tietojen samuudesta.
     */
    private boolean teeEqualsVertailut(Object olio) {
        Laskuttaja verrattava = (Laskuttaja) olio;
        if (this.nimi.equals(verrattava.nimi)
                && this.katuosoite.equals(verrattava.katuosoite)
                && this.postinumero.equals(verrattava.postinumero)
                && this.kaupunki.equals(verrattava.kaupunki)
                && this.yrityksenNimi.equals(verrattava.yrityksenNimi)
                && this.alvTunniste.equals(verrattava.alvTunniste)
                && this.tilinumero.equals(verrattava.tilinumero)
                && this.puhelinnumero.equals(verrattava.puhelinnumero)
                && this.sahkopostiOsoite.equals(verrattava.sahkopostiOsoite)
                && this.laskujaLahetetty.equals(verrattava.laskujaLahetetty)) {
            return true;
        }
        return false;
    }

    /**
     * Luokan Laskuttaja hashCode-metodi.
     * <p>
     * HashCode muodostetaan summaamalla attribuuttien nimi, katuosoite,
     * postinumero, kaupunki ja yrityksenNimi hashCodet.
     *
     * @return Kokonaisluku.
     */
    @Override
    public int hashCode() {
        return (nimi.hashCode()
                + katuosoite.hashCode()
                + postinumero.hashCode()
                + kaupunki.hashCode()
                + yrityksenNimi.hashCode());
    }
}
