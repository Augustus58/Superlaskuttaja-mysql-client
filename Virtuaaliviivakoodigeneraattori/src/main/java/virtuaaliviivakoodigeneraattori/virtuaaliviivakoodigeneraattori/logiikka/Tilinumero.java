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
public class Tilinumero {
    
    private final String tilinumero;
    
    public Tilinumero(String tilinumero) {
        this.tilinumero=tilinumero;
    }
    public String getTilinumeroIlmanMaatunnusta() {
        return this.tilinumero.substring(2);
    }
    public String getTilinumero() {
        return this.tilinumero;
    }
}
