/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Augustus58
 */
public class Tilinumero {

    private final String tilinumero;

    public Tilinumero(String tilinumero) {
        this.tilinumero = tilinumero;
    }

    public Boolean tarkistaTilinumero(String tilinumero) {
        BigInteger yksi = new BigInteger("1");
        BigInteger yhdeksankymmentaseitseman = new BigInteger("97");
        if (tilinumero.length() == 0 || tilinumero.length() > 18) {
            return false;
        }
        
        if(muunnaTilinumeroMaakoodiJaTarkisteSiirrettyLoppuunKokonaisluvuksi(siirraMaakodiJaTarkisteLoppuun(tilinumero)).mod(yhdeksankymmentaseitseman).equals(yksi)) {
            return true;
        }
        
        return false;
        
    }
    
    private String siirraMaakodiJaTarkisteLoppuun(String tilinumero) {
        return(tilinumero.substring(5-1)+tilinumero.substring(0, 4));
    }
    
    private BigInteger muunnaTilinumeroMaakoodiJaTarkisteSiirrettyLoppuunKokonaisluvuksi(String tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun) {
        String muunnettuMerkkijono = "";
        for (int i = 0; i < tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun.length(); i++) {
            if(onkoMerkkiIsoAakkonen(tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun.charAt(i))) {
                muunnettuMerkkijono = muunnettuMerkkijono + muunnaKirjainKokonaisluvuksi(tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun.charAt(i)).toString();
            }
            else {
                muunnettuMerkkijono = muunnettuMerkkijono + tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun.charAt(i);
            }
        }
        if(voikoMerkkijononMuuttaaKokonaisluvuksi(muunnettuMerkkijono)) {
            BigInteger kokonaisluku = new BigInteger(muunnettuMerkkijono);
            return(kokonaisluku);
        }
        BigInteger nolla = new BigInteger("0");
        return nolla;
        
    }
    
    private Boolean voikoMerkkijononMuuttaaKokonaisluvuksi(String merkkijono) {
        for (int i = 0; i < merkkijono.length(); i++) {
            if(!onkoMerkkiNumero(merkkijono.charAt(i))) {
                return false;
                } 
            }
        return true;
    }
    
    private Boolean onkoMerkkiIsoAakkonen (Character merkki) {
        ArrayList<String> aakkoslista = muodostaAakkoslista();
        for (int i = 0; i < aakkoslista.size(); i++) {
            if(aakkoslista.get(i).equals(merkki.toString()))
                return true;
        }
        return false;
    }
    
    private Boolean onkoMerkkiNumero (Character merkki) {
        ArrayList<String> numerolista = muodostaNumerolista();
        for (int i = 0; i < numerolista.size(); i++) {
            if(numerolista.get(i).equals(merkki.toString()))
                return true;
        }
        return false;
    }
    
    private Integer muunnaKirjainKokonaisluvuksi(Character kirjain) {
        ArrayList<String> aakkoslista = muodostaAakkoslista();
        int k = 0;
        for (int i = 0; i < aakkoslista.size(); i++) {
            if(aakkoslista.get(i).equals(kirjain.toString())) {
                k = i + 10;
            }
        }
        return k;
    }
    
    private ArrayList<String> muodostaNumerolista() {
        ArrayList<String> numerolista = new ArrayList<>();
        numerolista.add("0");
        numerolista.add("1");
        numerolista.add("2");
        numerolista.add("3");
        numerolista.add("4");
        numerolista.add("5");
        numerolista.add("6");
        numerolista.add("7");
        numerolista.add("8");
        numerolista.add("9");
        return numerolista;
    }
    
    private ArrayList<String> muodostaAakkoslista() {
        ArrayList<String> aakkoslista = new ArrayList<>();
        aakkoslista.add("A");        
        aakkoslista.add("B");
        aakkoslista.add("C");
        aakkoslista.add("D");
        aakkoslista.add("E");
        aakkoslista.add("F");
        aakkoslista.add("G");
        aakkoslista.add("H");
        aakkoslista.add("I");
        aakkoslista.add("J");
        aakkoslista.add("K");
        aakkoslista.add("L");
        aakkoslista.add("M");
        aakkoslista.add("N");
        aakkoslista.add("O");
        aakkoslista.add("P");
        aakkoslista.add("Q");
        aakkoslista.add("R");
        aakkoslista.add("S");
        aakkoslista.add("T");
        aakkoslista.add("U");
        aakkoslista.add("V");
        aakkoslista.add("W");
        aakkoslista.add("X");
        aakkoslista.add("Y");
        aakkoslista.add("Z");
        return aakkoslista;
    }

    public String getTilinumeroIlmanMaatunnusta() {
        return this.tilinumero.substring(2);
    }

    public String getTilinumero() {
        return this.tilinumero;
    }
}
