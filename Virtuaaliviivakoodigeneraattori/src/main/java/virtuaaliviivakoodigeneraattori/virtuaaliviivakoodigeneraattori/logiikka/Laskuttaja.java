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
public class Laskuttaja {

    private final String nimi;
    private final String katuosoite;
    private final String postinumero;
    private final String kaupunki;
    private final String yrityksenNimi;
    private final String alvTunniste;
    private final Tilinumero tilinumero;
    private final String puhelinnumero;
    private final String sahkopostiOsoite;
    private Integer laskujaLahetetty;

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

}
