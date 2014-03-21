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
    private ArrayList<Laskuttaja> laskuttajat;

    public Tietovarasto() {
        this.asiakkaat = new ArrayList<>();
        this.laskut = new ArrayList<>();
        this.laskuttajat = new ArrayList<>();
    }

    public ArrayList<Asiakas> getAsiakkaat() {
        return asiakkaat;
    }

    public ArrayList<Lasku> getLaskut() {
        return laskut;
    }

    public ArrayList<Laskuttaja> getLaskuttajat() {
        return laskuttajat;
    }
    
}
