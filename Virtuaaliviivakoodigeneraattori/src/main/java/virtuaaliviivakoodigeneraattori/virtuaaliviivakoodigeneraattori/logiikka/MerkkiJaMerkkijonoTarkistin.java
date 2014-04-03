/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import org.apache.commons.validator.GenericValidator;

/**
 *
 * @author Augustus58
 */
public class MerkkiJaMerkkijonoTarkistin {

    private final String isotAakkosetAZ;
    private final String numerotValiviivaJaValilyonti;

    public MerkkiJaMerkkijonoTarkistin() {
        this.isotAakkosetAZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.numerotValiviivaJaValilyonti = "0123456789- ";
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

    public Boolean sisaltaakoMerkkijNumeroitaJaKoostuukoMerkkijNumeroistaValiviivoistaTaiValilyonneista(String merkkijono) {
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
                if (!merkkijono.substring(i, i + 1).equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean onkoEmailOsoiteValidi(String osoite) {
        return (GenericValidator.isEmail(osoite));
    }

    public Boolean onkoMerkkijonoMuotoaNnPisteNnPisteNnnn(String merkkijono) {
        if (merkkijono.length() != 10) {
            return false;
        }
        if (koostuukoMerkkijonoNumeroista(merkkijono.substring(0, 2))
                && merkkijono.substring(2, 3).equals(".")
                && koostuukoMerkkijonoNumeroista(merkkijono.substring(3, 5))
                && merkkijono.substring(5, 6).equals(".")
                && koostuukoMerkkijonoNumeroista(merkkijono.substring(6, 10))) {
            return true;
        }
        return false;
    }

    public Boolean onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi(String merkkijono) {
        return (GenericValidator.isDate(merkkijono, "dd.MM.yyyy", true));
    }

    public String getIsotAakkosetAZ() {
        return this.isotAakkosetAZ;
    }

}
