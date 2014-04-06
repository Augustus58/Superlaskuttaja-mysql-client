/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

/**
 * Luokan ilmentymään voi tallettaa laskuttajan tiedot. Luokka tarjoaa
 * metodit tietojen oikeanlaisuuden tarkistamiseen.
 *
 * @author Augustus58
 */
public class Laskuttaja {

    private String nimi;
    private String katuosoite;
    private String postinumero;
    private String kaupunki;
    private String yrityksenNimi;
    private String alvTunniste;
    private Tilinumero tilinumero;
    private String puhelinnumero;
    private String sahkopostiOsoite;
    private Integer laskujaLahetetty;
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

    public Boolean onkoNimiOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(nimi));
    }

    public Boolean onkoKatuosoiteOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(katuosoite));
    }

    public Boolean onkoPostinumeroOikeanlainen() {
        return (tarkistin.koostuukoMerkkijonoNumeroista(postinumero));
    }

    public Boolean onkoKaupunkiOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(kaupunki));
    }

    public Boolean onkoYrityksenNimiOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(yrityksenNimi));
    }

    public Boolean onkoAlvOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(alvTunniste));
    }

    public Boolean onkoTilinumeroOikeanlainen() {
        return (tilinumero.tarkistaTilinumero(tilinumero.getTilinumero()));
    }

    public Boolean onkoPankkiOikeanlainen() {
        return (tilinumero.onkoPankkiOikeanlainen());
    }

    public Boolean onkoSwiftBicOikeanlainen() {
        return (tilinumero.onkoSwiftBicOikeanlainen());
    }

    public Boolean onkoPuhelinnumeroOikeanlainen() {
        return (tarkistin.sisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista(puhelinnumero));
    }

    public Boolean onkoSahkopostiOikeanlainen() {
        return (tarkistin.onkoEmailOsoiteValidi(sahkopostiOsoite));
    }

    public Boolean onkoLaskujaLahetettyOikeanlainen() {
        if (laskujaLahetetty >= 0) {
            return true;
        }
        return false;
    }

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

    @Override
    public int hashCode() {
        return (nimi.hashCode()
                + katuosoite.hashCode()
                + postinumero.hashCode()
                + kaupunki.hashCode()
                + yrityksenNimi.hashCode()
                + alvTunniste.hashCode()
                + tilinumero.hashCode()
                + puhelinnumero.hashCode()
                + sahkopostiOsoite.hashCode()
                + laskujaLahetetty.hashCode());
    }
}
