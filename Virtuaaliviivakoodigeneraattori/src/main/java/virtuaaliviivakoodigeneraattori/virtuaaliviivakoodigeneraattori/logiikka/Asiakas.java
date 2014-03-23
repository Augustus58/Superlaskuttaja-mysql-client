/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

/**
 *
 * @author Augustus58
 */
public class Asiakas {

    private final String asiakasnumero;
    private final String nimi;
    private final String katuosoite;
    private final String postinumero;
    private final String kaupunki;
    private final Integer laskujaLahetetty;
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

    public Boolean onkoNimiOikeanlainen() {
        if (tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(nimi)) {
            return false;
        }
        return true;
    }

    public Boolean onkoKaupunkiOikeanlainen() {
        if (tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(kaupunki)) {
            return false;
        }
        return true;
    }

    public Boolean onkoKatuosoiteOikeanlainen() {
        if (tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(katuosoite)) {
            return false;
        }
        return true;
    }

    public Boolean onkoAsiakasnumeroOikeanlainen() {
        if (tarkistin.koostuukoMerkkijonoNumeroista(asiakasnumero)) {
            return true;
        }
        return false;
    }

    public Boolean onkoPostinumeroOikeanlainen() {
        if (tarkistin.koostuukoMerkkijonoNumeroista(postinumero)) {
            return true;
        }
        return false;
    }

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

    public Object[] getAsiakkaanTiedotTaulukossa() {
        return (new Object[]{nimi, katuosoite, postinumero, kaupunki, asiakasnumero, laskujaLahetetty});
    }

    @Override
    public boolean equals(Object olio) {
        if (olio == null) {
            return false;
        }
        if (getClass() != olio.getClass()) {
            return false;
        }
        Asiakas verrattava = (Asiakas) olio;
        if (!this.asiakasnumero.equals(verrattava.asiakasnumero)) {
            return false;
        }
        if (!this.katuosoite.equals(verrattava.katuosoite)) {
            return false;
        }
        if (!this.kaupunki.equals(verrattava.kaupunki)) {
            return false;
        }
        if (!this.laskujaLahetetty.equals(verrattava.laskujaLahetetty)) {
            return false;
        }
        if (!this.nimi.equals(verrattava.nimi)) {
            return false;
        }
        if (!this.postinumero.equals(verrattava.postinumero)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (laskujaLahetetty + asiakasnumero.hashCode() + nimi.hashCode() + katuosoite.hashCode());
    }
}
