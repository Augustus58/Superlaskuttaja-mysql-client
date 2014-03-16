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
public class Viite {
    
    private final String viiteTarkisteella;
    
    public Viite(String viite) {
        this.viiteTarkisteella=muodostaTarkisteellinenViite(viite);
    }
    
    private String muodostaTarkisteellinenViite(String viite) {
        return (viite+laskeTarkiste(viite).toString());
}
    private Integer laskeTarkiste(String viite) {
        ArrayList<Integer> numerolista = new ArrayList<Integer>();
        for (int i = 1; i <= 7; i++) {
            numerolista.add(7);
            numerolista.add(3);
            numerolista.add(1);
        }
        Integer summa=0;
        for (int i = 1; i <= viite.length(); i++) {
            summa=summa+numerolista.get(i)*Integer.parseInt(viite.substring(i, i));
        }
        int ylosPyoristettyOsamaara;
        Integer tarkiste;
        ylosPyoristettyOsamaara=summa/10;
        tarkiste=(10*(ylosPyoristettyOsamaara+1)-summa);
        if(tarkiste==10) {
            return 0;
        }
        return(tarkiste);
    }
    
    public String getViiteTarkisteellaEtunollillaPituus20() {
        String etunollat="";
        while((etunollat+this.viiteTarkisteella).length()<20) {
            etunollat=etunollat+"0";
        }
        return (etunollat+this.viiteTarkisteella);
    }
    public String getViite() {
        return this.viiteTarkisteella;
    }
}
