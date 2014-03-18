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

// Tällä tarkistimella voidaan tarkistaa, onko Character-tyyppinen merkki numero tai iso kirjain A-Z.
public class MerkkiTarkistin {
    private final String isotAakkosetAZ;
    
    public MerkkiTarkistin() {
        this.isotAakkosetAZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }
    
    public Boolean onkoMerkkiNumero(Character merkki) {
        if(Character.isDigit(merkki)) {
        return true;
    }
        return false;
    }
    
    public Boolean onkoMerkkiKirjainAZ(Character merkki) {
        for (int i = 0; i < this.isotAakkosetAZ.length(); i++) {
            if(merkki.equals(this.isotAakkosetAZ.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    
    public String getIsotAakkosetAZ() {
        return this.isotAakkosetAZ;
    }
        
}
