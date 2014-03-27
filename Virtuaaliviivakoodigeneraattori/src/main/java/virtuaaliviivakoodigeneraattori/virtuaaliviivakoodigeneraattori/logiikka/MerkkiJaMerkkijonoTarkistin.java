/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import org.apache.commons.validator.EmailValidator;

/**
 *
 * @author Augustus58
 */
// Tällä tarkistimella voidaan tarkistaa, onko Character-tyyppinen merkki numero tai iso kirjain A-Z.
public class MerkkiJaMerkkijonoTarkistin {

    private final String isotAakkosetAZ;
    private final String numerotValiviivaJaValilyonti;
    private EmailValidator validator;

    public MerkkiJaMerkkijonoTarkistin() {
        this.isotAakkosetAZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.numerotValiviivaJaValilyonti = "0123456789- ";
        this.validator = EmailValidator.getInstance();
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
    
    public Boolean onkoMerkkiNumeroValiviivaTaiValilyonti(Character merkki) {
        for (int i = 0; i < this.numerotValiviivaJaValilyonti.length(); i++) {
            if (merkki.equals(this.numerotValiviivaJaValilyonti.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    
    public Boolean SisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista(String merkkijono) {
        if (merkkijono.isEmpty()) {
            return false;
        }
        if (!sisaltaakoMerkkijonoVahintaanYhdenNumeron(merkkijono)) {
            return false;
        }
        for (int i = 0; i < merkkijono.length(); i++) {
            if (!onkoMerkkiNumeroValiviivaTaiValilyonti(merkkijono.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public Boolean sisaltaakoMerkkijonoVahintaanYhdenNumeron(String merkkijono) {
        for (int i = 0; i < merkkijono.length(); i++) {
            if (onkoMerkkiNumero(merkkijono.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    
    public Boolean voikoMerkkijononMuuttaaKokonaisluvuksi(String merkkijono) {
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
    
    public Boolean onkoEmailOsoiteValidi(String osoite) {
        return(validator.isValid(osoite));
    }

    public String getIsotAakkosetAZ() {
        return this.isotAakkosetAZ;
    }

}
