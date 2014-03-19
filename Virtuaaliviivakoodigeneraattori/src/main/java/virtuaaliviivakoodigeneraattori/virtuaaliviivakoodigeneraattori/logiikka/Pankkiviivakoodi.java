/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Augustus58
 */

// Tämän luokan metodien toiminnan perustelut löytyy osoitteesta
// http://www.fkl.fi/teemasivut/sepa/tekninen_dokumentaatio/Dokumentit/Pankkiviivakoodi-opas.pdf
public class Pankkiviivakoodi {

    private final String pankkiviivakoodi;
    private final SimpleDateFormat paivamaaraFormaatti;

    public Pankkiviivakoodi(Tilinumero tilinumero, LaskunSumma laskunSumma, Viite viite, Date erapaiva) {

        this.paivamaaraFormaatti = new SimpleDateFormat("yyMMdd");
        this.pankkiviivakoodi = muodostaPankkiviivakoodi(muodostaPankkiviivakoodiIlmanTarkistettaJaLopetusta(tilinumero, laskunSumma, viite, erapaiva));

    }

    private String muodostaPankkiviivakoodiIlmanTarkistettaJaLopetusta(Tilinumero tilinumero, LaskunSumma laskunSumma, Viite viite, Date erapaiva) {
        return ("105"
                + "4"
                + tilinumero.getTilinumeroIlmanMaatunnusta()
                + laskunSumma.getEurotStringEtunollillaPituusKuusi()
                + laskunSumma.getSentitStringEtunollillaPituusKaksi()
                + "000"
                + viite.getViiteTarkisteellaEtunollillaPituus20()
                + paivamaaraFormaatti.format(erapaiva));
    }

    private String muodostaPankkiviivakoodi(String pankkiviivakoodiIlmanTarkistettaJaLopetusta) {
        return (pankkiviivakoodiIlmanTarkistettaJaLopetusta
                + laskeTarkiste(pankkiviivakoodiIlmanTarkistettaJaLopetusta).toString()
                + "106");
    }

    private Integer laskeTarkiste(String pankkiviivakoodiIlmanTarkistettaJaLopetusta) {
        Integer summa = 0;
        summa = summa + 1 * Integer.parseInt(pankkiviivakoodiIlmanTarkistettaJaLopetusta.substring(0, 3));
        for (int i = 1; i <= 27; i++) {
            summa = summa + i * Integer.parseInt(pankkiviivakoodiIlmanTarkistettaJaLopetusta.substring(2 * i + 1, 2 * i + 3));

        }
        return (summa % 103);

    }
    
    public String getString() {
        return this.pankkiviivakoodi;
    }
    
    public String getStringIlmanAloitustaJaLopetusta() {
        return this.pankkiviivakoodi.substring(3, this.pankkiviivakoodi.length()-3);
    }
}