/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import java.util.Date;

/**
 *
 * @author Augustus58
 */
public class Lasku {

    private final Tilinumero tilinumero;
    private final Viite viiteTarkisteella;
    private final LaskunSumma summa; //Käytetty omaa luokkaa (LaskunSumma), koska mm. pankkiviivakoodin (=virtuaaliviivakoodi) standardit vaativat tietyt rajat euroille ja senteille. 0<=eurot<=999999. 0<=sentit<=99.
    private final Date erapaiva;
    private final Pankkiviivakoodi pankkiviivakoodi;

    public Lasku(Tilinumero tilinumero, Viite viiteTarkisteella, LaskunSumma summa, Date erapaiva, Pankkiviivakoodi pankkiviivakoodi) {
        this.tilinumero = tilinumero;
        this.viiteTarkisteella = viiteTarkisteella;
        this.summa = summa;
        this.erapaiva = erapaiva;
        this.pankkiviivakoodi = pankkiviivakoodi;
    }
}
