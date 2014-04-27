/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superlaskuttaja.logiikka;

/**
 * Tämä luokka on tarkoitettu ohjelman tietojen lataamiseen ja tietojen välittämiseen
 * Tietovarasto-oliolta.
 * 
 * @author Augustus58
 */
public class Lataaja {
    
    /**
     * Tietovarasto, jonka lataaja voi ladata käytettäväksi ohjelmassa.
     */
    private final Tietovarasto ladattuTietovarasto;

    public Lataaja() {
        this.ladattuTietovarasto = new Tietovarasto();
    }

    public Tietovarasto getLadattuTietovarasto() {
        return ladattuTietovarasto;
    }
    
}
