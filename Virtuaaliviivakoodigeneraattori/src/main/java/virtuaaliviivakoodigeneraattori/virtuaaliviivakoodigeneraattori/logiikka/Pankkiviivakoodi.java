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
 *
 * Luokka tarjoaa tarvittavat metodit pankkiviivakoodien muodostamiseen ja
 * niiden oikeellisuuden tarkistamiseen. Tämän luokan metodien toiminnan
 * perustelut löytyvät osoitteesta
 * http://www.fkl.fi/teemasivut/sepa/tekninen_dokumentaatio/Dokumentit/Pankkiviivakoodi-opas.pdf
 */
public class Pankkiviivakoodi {

    private final String pankkiviivakoodi;
    private final SimpleDateFormat paivamaaraFormaatti;

    public Pankkiviivakoodi(Tilinumero tilinumero, LaskunSumma laskunSumma, Viite viite, Date erapaiva) {

        this.paivamaaraFormaatti = new SimpleDateFormat("yyMMdd");
        this.pankkiviivakoodi = muodostaPankkiviivakoodi(muodostaPankkiviivakoodiIlmanTarkistettaJaLopetusta(tilinumero, laskunSumma, viite, erapaiva));

    }

    /**
     * Metodi muodostaa pankkiviivakoodin ilman tarkistetta ja lopetusta.
     *
     * @param tilinumero Oikeaksi todettu tilinumero.
     * @param laskunSumma Laskun summa.
     * @param viite Oikeaksi todettu viite.
     * @param erapaiva Laskun eräpäivä.
     *
     * @return pankkiviivakoodi ilman aloitusta ja lopetusta.
     */
    private String muodostaPankkiviivakoodiIlmanTarkistettaJaLopetusta(Tilinumero tilinumero, LaskunSumma laskunSumma, Viite viite, Date erapaiva) {
        return ("105"
                + "4"
                + tilinumero.tilinumeroIlmanMaatunnusta()
                + laskunSumma.eurotStringEtunollillaPituusKuusi()
                + laskunSumma.sentitStringEtunollillaPituusKaksi()
                + "000"
                + viite.viiteTarkisteellaEtunollillaPituus20()
                + paivamaaraFormaatti.format(erapaiva));
    }

    /**
     * Metodi muodostaa pankkiviivakoodin aloituksella ja lopetuksella.
     *
     * @param pankkiviivakoodiIlmanTarkistettaJaLopetusta Pankkiviivakoodi ilman
     * aloitusta ja lopetusta.
     *
     * @return Pankkiviivakoodi aloituksella ja lopetuksella.
     */
    private String muodostaPankkiviivakoodi(String pankkiviivakoodiIlmanTarkistettaJaLopetusta) {
        return (pankkiviivakoodiIlmanTarkistettaJaLopetusta
                + laskeTarkiste(pankkiviivakoodiIlmanTarkistettaJaLopetusta).toString()
                + "106");
    }

    /**
     * Metodi laskee tarkisteen pankkiviivakoodiin.
     *
     * @param pankkiviivakoodiIlmanTarkistettaJaLopetusta Pankkiviivakoodi ilman
     * aloitusta ja lopetusta.
     *
     * @return Tarkistenumero
     */
    private Integer laskeTarkiste(String pankkiviivakoodiIlmanTarkistettaJaLopetusta) {
        Integer summa = 0;
        summa = summa + 1 * Integer.parseInt(pankkiviivakoodiIlmanTarkistettaJaLopetusta.substring(0, 3));
        for (int i = 1; i <= 27; i++) {
            summa = summa + i * Integer.parseInt(pankkiviivakoodiIlmanTarkistettaJaLopetusta.substring(2 * i + 1, 2 * i + 3));

        }
        return (summa % 103);

    }

    public String getPankkiviivakoodi() {
        return this.pankkiviivakoodi;
    }

    /**
     * Metodi antaa jo muodostetun pankkiviivakoodin ilman aloitusta ja
     * lopetusta.
     *
     * @return pankkiviivakoodi ilman aloitusta lopetusta.
     */
    public String pankkiviivakoodiIlmanAloitustaJaLopetusta() {
        return this.pankkiviivakoodi.substring(3, this.pankkiviivakoodi.length() - 3);
    }

    /**
     * Metodi luokan ilmentymien samuuden selvittämiseen.
     *
     * @param olio Samuusverrattava olio.
     * @return Tieto verrattavan olion ja kutsujaolion samuudesta.
     */
    @Override
    public boolean equals(Object olio) {
        if (olio == null) {
            return false;
        }
        if (getClass() != olio.getClass()) {
            return false;
        }
        return (teeEqualsVertailut(olio));
    }
    
    /**
     * Metodi jossa tehdään equals-metodin samuusvertailut.
     * <p>
     * Ennen tämän metodin käyttöä tulee varmistaa, että argumentti ei ole null
     * ja että argumentin luokka on Pankkiviivakoodi.
     *
     * @param olio Samuusverrattava olio.
     * @return Tieto verrattavan olion ja kutsujaolion tietojen samuudesta.
     */
    private boolean teeEqualsVertailut(Object olio) {
        Pankkiviivakoodi verrattava = (Pankkiviivakoodi) olio;
        if (this.pankkiviivakoodi.equals(verrattava.pankkiviivakoodi)) {
            return true;
        }
        return false;
    }

    /**
     * Luokan Pankkiviivakoodi hashCode-metodi.
     * <p>
     * HashCode muodostetaan attribuutin pankkiviivakoodi hashCodesta.
     *
     * @return Kokonaisluku.
     */
    @Override
    public int hashCode() {
        return (pankkiviivakoodi.hashCode());
    }
}
