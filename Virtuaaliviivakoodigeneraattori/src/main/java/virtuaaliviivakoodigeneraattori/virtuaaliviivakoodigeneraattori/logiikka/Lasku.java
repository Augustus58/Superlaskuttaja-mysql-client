/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Augustus58
 */
public class Lasku {

    private final Laskuttaja laskuttaja; //Tilinumero tätä kautta.
    private final Asiakas asiakas;  
    
    private final Date lahetysPaiva;
    private final Integer laskunNumero; //Tämä numerointi koskee kaikkia laskuja. Eli kaikkien asiakkaiden laskut samalla numeroinnilla.
    private final Date erapaiva;
    private final Integer viivastyskorko;
    private final Viite viiteTarkisteella;                
    private final String maksuehto;          
    
    private final ArrayList<Suorite> suoritteet;
    private final String lisatiedot;
    private final LaskunSumma summa; //Käytetty omaa luokkaa (LaskunSumma), koska mm. pankkiviivakoodin (=virtuaaliviivakoodi) standardit vaativat tietyt rajat euroille ja senteille. 0<=eurot<=999999. 0<=sentit<=99.
    private final Pankkiviivakoodi pankkiviivakoodi;

    public Lasku(Laskuttaja laskuttaja, Asiakas asiakas, Date lahetysPaiva, Integer laskunNumero, Date erapaiva, Integer viivastyskorko, Viite viiteTarkisteella, String maksuehto, ArrayList<Suorite> suoritteet, String lisatiedot, LaskunSumma summa, Pankkiviivakoodi pankkiviivakoodi) {
        this.laskuttaja = laskuttaja;
        this.asiakas = asiakas;
        this.lahetysPaiva = lahetysPaiva;
        this.laskunNumero = laskunNumero;
        this.erapaiva = erapaiva;
        this.viivastyskorko = viivastyskorko;
        this.viiteTarkisteella = viiteTarkisteella;
        this.maksuehto = maksuehto;
        this.suoritteet = suoritteet;
        this.lisatiedot = lisatiedot;
        this.summa = summa;
        this.pankkiviivakoodi = pankkiviivakoodi;
    }

    public Integer getLaskunNumero() {
        return laskunNumero;
    }
}
