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
    private final MerkkiTarkistin merkkitarkistin;

    public Tilinumero(String tilinumero) {
        this.tilinumero = tilinumero;
        this.merkkitarkistin = new MerkkiTarkistin();
    }

    public Boolean tarkistaTilinumero(String tilinumero) {
        BigInteger yksi = new BigInteger("1");
        BigInteger yhdeksankymmentaseitseman = new BigInteger("97");
        if (tilinumero.length() <= 4 || tilinumero.length() > 18) {
            return false;
        }

        if (muunnaTilinumeroMaakoodiJaTarkisteSiirrettyLoppuunKokonaisluvuksi(siirraMaakodiJaTarkisteLoppuun(tilinumero)).mod(yhdeksankymmentaseitseman).equals(yksi)) {
            return true;
        }

        return false;

    }

    private String siirraMaakodiJaTarkisteLoppuun(String tilinumero) {
        return (tilinumero.substring(5 - 1) + tilinumero.substring(0, 4));
    }
    
    private BigInteger muunnaTilinumeroMaakoodiJaTarkisteSiirrettyLoppuunKokonaisluvuksi(String tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun) {
        String muunnettuMerkkijono = "";
        for (int i = 0; i < tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun.length(); i++) {
            if (this.merkkitarkistin.onkoMerkkiKirjainAZ(tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun.charAt(i))) {
                muunnettuMerkkijono = muunnettuMerkkijono + muunnaKirjainKokonaisluvuksi(tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun.charAt(i)).toString();
            } else {
                muunnettuMerkkijono = muunnettuMerkkijono + tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun.charAt(i);
            }
        }
        if (voikoMerkkijononMuuttaaKokonaisluvuksi(muunnettuMerkkijono)) {
            BigInteger kokonaisluku = new BigInteger(muunnettuMerkkijono);
            return (kokonaisluku);
        }
        BigInteger nolla = new BigInteger("0");
        return nolla;

    }

    private Boolean voikoMerkkijononMuuttaaKokonaisluvuksi(String merkkijono) {
        for (int i = 0; i < merkkijono.length(); i++) {
            if (!this.merkkitarkistin.onkoMerkkiNumero(merkkijono.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private Integer muunnaKirjainKokonaisluvuksi(Character kirjain) {
        int k = 0;
        for (int i = 0; i < this.merkkitarkistin.getIsotAakkosetAZ().length(); i++) {
            if (this.merkkitarkistin.getIsotAakkosetAZ().substring(i, i+1).equals(kirjain.toString())) {
                k = i + 10;
            }
        }
        return k;
    }

    public String getTilinumeroIlmanMaatunnusta() {
        return this.tilinumero.substring(2);
    }

    public String getTilinumero() {
        return this.tilinumero;
    }
}
