/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import java.math.BigInteger;

/**
 *
 * @author Augustus58
 */

// Osaan tämän luokan metodeista löytyy toiminnan perustelut osoitteesta
// http://www.fkl.fi/teemasivut/sepa/tekninen_dokumentaatio/Dokumentit/IBAN_ja_BIC_maksuliikenteessa.pdf
public class Tilinumero {

    private final String tilinumero;
    private final String pankki;
    private final String swiftBic;
    private final MerkkiJaMerkkijonoTarkistin tarkistin;

    public Tilinumero(String tilinumero, String pankki, String swiftBic) {
        this.tilinumero = tilinumero;
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
        this.pankki = pankki;
        this.swiftBic = swiftBic;
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
            if (this.tarkistin.onkoMerkkiKirjainAZ(tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun.charAt(i))) {
                muunnettuMerkkijono = muunnettuMerkkijono + muunnaKirjainKokonaisluvuksi(tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun.charAt(i)).toString();
            } else {
                muunnettuMerkkijono = muunnettuMerkkijono + tilinumeroMaakoodiJaTarkisteSiirrettyLoppuun.charAt(i);
            }
        }
        if (tarkistin.voikoMerkkijononMuuttaaKokonaisluvuksi(muunnettuMerkkijono)) {
            BigInteger kokonaisluku = new BigInteger(muunnettuMerkkijono);
            return (kokonaisluku);
        }
        BigInteger nolla = new BigInteger("0");
        return nolla;

    }

    private Integer muunnaKirjainKokonaisluvuksi(Character kirjain) {
        int k = 0;
        for (int i = 0; i < this.tarkistin.getIsotAakkosetAZ().length(); i++) {
            if (this.tarkistin.getIsotAakkosetAZ().substring(i, i+1).equals(kirjain.toString())) {
                k = i + 10;
            }
        }
        return k;
    }

    public Boolean onkoPankkiOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(pankki));
    }
    
    public Boolean onkoSwiftBicOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(swiftBic));
    }
    
    public String getTilinumero() {
        return this.tilinumero;
    }
    
    public String getTilinumeroIlmanMaatunnusta() {
        return this.tilinumero.substring(2);
    }

    public String getPankki() {
        return pankki;
    }

    public String getSwiftBic() {
        return swiftBic;
    }
    
}
