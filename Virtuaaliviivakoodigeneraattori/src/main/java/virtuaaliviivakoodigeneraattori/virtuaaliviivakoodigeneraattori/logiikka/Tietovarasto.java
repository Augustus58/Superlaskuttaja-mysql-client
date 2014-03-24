/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import java.util.ArrayList;

/**
 *
 * @author Augustus58
 */
//Tämän luokan ilmentymässä voi "säilyttää" ohjelmassa esiintyviä olioita, kuten esim. Asiakas -tai Lasku luokkien ilmentymiä.
public class Tietovarasto {

    private ArrayList<Asiakas> asiakkaat;
    private ArrayList<Lasku> laskut;
    private Laskuttaja laskuttaja;
    private Boolean laskuttajaLisatty;

    public Tietovarasto() {
        this.asiakkaat = new ArrayList<>();
        this.laskut = new ArrayList<>();     
        this.laskuttajaLisatty = false;
    }

    public String[][] getAsiakkaatArrayString() {
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
    
    public ArrayList<Asiakas> getAsiakkaat() {
        return asiakkaat;
    }

    public ArrayList<Lasku> getLaskut() {
        return laskut;
    }

    public void setLaskuttaja(Laskuttaja laskuttaja) {
        this.laskuttaja = laskuttaja;
        laskuttajaLisatty = true;
    }

    public Laskuttaja getLaskuttaja() {
        return laskuttaja;
    }

    public Boolean isLaskuttajaLisatty() {
        return laskuttajaLisatty;
    }
    
    public void poistaAsiakas(Asiakas asiakas) {
        if (asiakkaat.contains(asiakas)) {
            asiakkaat.remove(asiakas);
        }
    }
}
