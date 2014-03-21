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
public class Lataaja {
    
    private Tietovarasto ladattuTietovarasto;

    public Lataaja() {
        this.ladattuTietovarasto = new Tietovarasto();
    }

    public Tietovarasto getLadattuTietovarasto() {
        return ladattuTietovarasto;
    }
    
}
