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
public class MerkkiJaMerkkijonoTarkistin {

    private final String isotAakkosetAZ;

    public MerkkiJaMerkkijonoTarkistin() {
        this.isotAakkosetAZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    public Boolean onkoMerkkiNumero(Character merkki) {
        if (Character.isDigit(merkki)) {
            return true;
        }
        return false;
    }

    public Boolean onkoMerkkiKirjainAZ(Character merkki) {
        for (int i = 0; i < this.isotAakkosetAZ.length(); i++) {
            if (merkki.equals(this.isotAakkosetAZ.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public Boolean koostuukoMerkkijonoNumeroista(String merkkijono) {
        if (merkkijono.isEmpty()) {
            return false;
        }
        for (int i = 0; i < merkkijono.length(); i++) {
            if (!onkoMerkkiNumero(merkkijono.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public Boolean onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(String merkkijono) {
        if (merkkijono.isEmpty()) {
            return true;
        }
        if (!merkkijono.isEmpty()) {
            for (int i = 0; i < merkkijono.length(); i++) {
                if (!merkkijono.substring(i, i+1).equals(" ")) {
                    return false;
                }
            }
            
        }
        return true;
    }

    public String getIsotAakkosetAZ() {
        return this.isotAakkosetAZ;
    }

}
