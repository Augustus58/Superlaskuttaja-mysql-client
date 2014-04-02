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
// Tämän luokan tarkistusmetodien toiminnan perustelut löytyy osoitteesta
// http://www.fkl.fi/teemasivut/sepa/tekninen_dokumentaatio/Dokumentit/Pankkiviivakoodi-opas.pdf
public class LaskunSumma {

    private Integer eurot;
    private Integer sentit;

    public LaskunSumma(Integer eurot, Integer sentit) {
        this.eurot = eurot;
        this.sentit = sentit;
    }

    public String eurotStringEtunollillaPituusKuusi() {
        String etunollat = "";
        while ((etunollat + this.eurot.toString()).length() < 6) {
            etunollat = etunollat + "0";
        }
        return (etunollat + this.eurot.toString());
    }

    public String sentitStringEtunollillaPituusKaksi() {
        String etunollat = "";
        while ((etunollat + this.sentit.toString()).length() < 2) {
            etunollat = etunollat + "0";
        }
        return (etunollat + this.sentit.toString());
    }

    //Seuraavassa tarkistetaan, että euromäärä täyttää pankkiviivakoodistandardin.
    public Boolean tarkistaEurot(Integer eurot) {
        if (eurot >= 0 && eurot <= 999999) {
            return true;
        }
        return false;
    }

    //Seuraavassa tarkistetaan, että senttimäärä täyttää pankkiviivakoodistandardin.
    public Boolean tarkistaSentit(Integer sentit) {
        if (sentit >= 0 && sentit <= 99) {
            return true;
        }
        return false;
    }

    public void setEurot(Integer eurot) {
        this.eurot = eurot;
    }

    public void setSentit(Integer sentit) {
        this.sentit = sentit;
    }

    @Override
    public String toString() {
        return (eurot.toString() + "." + sentit.toString());
    }

    @Override
    public boolean equals(Object olio) {
        if (olio == null) {
            return false;
        }
        if (getClass() != olio.getClass()) {
            return false;
        }
        LaskunSumma verrattava = (LaskunSumma) olio;
        if (!this.eurot.equals(verrattava.eurot)) {
            return false;
        }
        if (!this.sentit.equals(verrattava.sentit)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (eurot.hashCode() + sentit.hashCode());
    }
}
