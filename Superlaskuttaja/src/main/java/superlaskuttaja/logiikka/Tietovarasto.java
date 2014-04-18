/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.logiikka;

import java.util.ArrayList;

/**
 * Tämän luokan ilmentymässä voi "säilyttää" ohjelmassa esiintyvien luokkien
 * Laskuttaja, Asiakas, Suorite ja Lasku -luokkien ilmentymiä.
 *
 * @author Augustus58
 */
public class Tietovarasto {

    private Laskuttaja laskuttaja;
    private final ArrayList<Asiakas> asiakkaat;
    private final ArrayList<Suorite> suoritteet;
    private final ArrayList<Lasku> laskut;
    private Boolean laskuttajaLisatty;

    public Tietovarasto() {
        this.asiakkaat = new ArrayList<>();
        this.suoritteet = new ArrayList<>();
        this.laskut = new ArrayList<>();
        this.laskuttajaLisatty = false;
    }

    /**
     * Metodi antaa asiakkaiden tiedot taulukossa.
     * <p>
     * Tätä metodia tarvitaan erityisesti ohjelman tietojen tallentamiseen
     * tiedostoon.
     *
     * @return Asiakkaiden tiedot taulukossa, yhden asiakkaan tiedot yhdellä
     * rivillä jne.
     */
    public String[][] asiakkaatArrayString() {
        String[][] taulukko = new String[asiakkaat.size()][6];
        for (int i = 0; i < asiakkaat.size(); i++) {
            taulukko[i][0] = asiakkaat.get(i).getNimi();
            taulukko[i][1] = asiakkaat.get(i).getKatuosoite();
            taulukko[i][2] = asiakkaat.get(i).getPostinumero();
            taulukko[i][3] = asiakkaat.get(i).getKaupunki();
            taulukko[i][4] = asiakkaat.get(i).getLaskujaLahetetty().toString();
            taulukko[i][5] = asiakkaat.get(i).getAsiakasnumero();
        }
        return taulukko;
    }

    /**
     * Metodi antaa asiakkaiden nimet taulukossa.
     * <p>
     * Tätä metodia tarvitaan erityisesti suoritteiden käsittelyyn.
     *
     * @return Asiakkaiden nimet taulukossa.
     */
    public String[] asiakkaidenNimetArrayString() {
        String[] taulukko = new String[asiakkaat.size()];
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = asiakkaat.get(i).getNimi();
        }
        return taulukko;
    }

    public ArrayList<Asiakas> getAsiakkaat() {
        return asiakkaat;
    }

    public ArrayList<Lasku> getLaskut() {
        return laskut;
    }

    public Laskuttaja getLaskuttaja() {
        return laskuttaja;
    }

    public ArrayList<Suorite> getSuoritteet() {
        return suoritteet;
    }

    /**
     * Metodi antaa argumentin laskuttamattomat suoritteet.
     * <p>
     * Tätä metodia tarvitaan erityisesti käyttöliittymän "lisää lasku" ja
     * "muokkaa valittua laskua" osioissa.
     *
     * @param asiakas Asiakas, jonka laskuttamattomat suoritteet halutaan.
     * @return Argumenttina annetun asiakkaan laskuttamattomat suoritteet
     * ArrayList:nä.
     */
    public ArrayList<Suorite> asiakkaanLaskuttamattomatSuoritteetArrayList(Asiakas asiakas) {
        ArrayList<Suorite> laskuttamattomatSuoritteet = new ArrayList<>();
        for (int i = 0; i < this.getSuoritteet().size(); i++) {
            if (this.getSuoritteet().get(i).getAsiakas().equals(asiakas)) {
                if (!this.getSuoritteet().get(i).getOnkoLaskutettu()) {
                    laskuttamattomatSuoritteet.add(this.getSuoritteet().get(i));
                }
            }
        }
        return laskuttamattomatSuoritteet;
    }

    /**
     * Metodi antaa argumenttina annetun asiakkaan laskuttamattomat suoritteet
     * ja lisäksi argumenttina annetut suoritteet.
     * <p>
     * Tätä metodia tarvitaan erityisesti käyttöliittymän "muokkaa valittua
     * laskua" osiossa.
     *
     * @param asiakas Asiakas, jonka laskuttamattomat suoritteet halutaan.
     * @param suoritteet Suoritteet, jotka halutaan laskuttamattomien lisäksi.
     * @return Argumenttina annetun asiakkaan laskuttamattomat suoritteet ja
     * halutut suoritteet samassa järjestyksessä, kuin ne on lisätty.
     * ArrayList:nä.
     */
    public ArrayList<Suorite> asiakkaanLaskuttamattomatSuoritteetJaHalututSuoritteetArrayList(Asiakas asiakas, ArrayList<Suorite> suoritteet) {
        ArrayList<Suorite> palautettavatSuoritteet = new ArrayList<>();
        for (int i = 0; i < this.getSuoritteet().size(); i++) {
            if (this.getSuoritteet().get(i).getAsiakas().equals(asiakas)) {
                if (!this.getSuoritteet().get(i).getOnkoLaskutettu() || suoritteet.contains(this.getSuoritteet().get(i))) {
                    palautettavatSuoritteet.add(this.getSuoritteet().get(i));
                }
            }
        }
        return palautettavatSuoritteet;
    }

    public void setLaskuttaja(Laskuttaja laskuttaja) {
        this.laskuttaja = laskuttaja;
        laskuttajaLisatty = true;
    }

    /**
     * Metodi kertoo onko laskuttajan tiedot syötetty.
     * <p>
     * Tätä metodia tarvitaan, kun halutaan selvittää voiko esim. laskun
     * muodostaa.
     *
     * @return Tieto onko laskuttajan tiedot syötetty.
     */
    public Boolean isLaskuttajaLisatty() {
        return laskuttajaLisatty;
    }

    /**
     * Metodilla voi poistaa asiakkaan asiakkaista.
     *
     * @param asiakas Poistettava asiakas.
     */
    public void poistaAsiakas(Asiakas asiakas) {
        if (asiakkaat.contains(asiakas)) {
            asiakkaat.remove(asiakas);
        }
    }
}
