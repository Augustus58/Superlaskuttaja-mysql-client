/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat;

import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.ComboBoxKuuntelija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.AsiakkaatTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Asiakas;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanelNaytaKriteerinSisKuuntelija implements ActionListener {
    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final ComboBoxKuuntelija kuuntelija;
    private final JTextField kriteeriTekstikentta;

    public AsiakkaatPanelNaytaKriteerinSisKuuntelija(Lataaja lataaja, AsiakkaatTaulukko taulukko, ComboBoxKuuntelija kuuntelija, JTextField kriteeriTekstikentta) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.kuuntelija = kuuntelija;
        this.kriteeriTekstikentta = kriteeriTekstikentta;
    }   
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        taulukko.reset();
        
        String kriteeri = kriteeriTekstikentta.getText();
        
        for(Asiakas asiakas : lataaja.getLadattuTietovarasto().getAsiakkaat()) {
            if(kuuntelija.getValinta() == 0) {
                if(asiakas.getNimi().contains(kriteeri)) {
                    taulukko.getModel().addRow(asiakas.getAsiakkaanTiedotTaulukossa());
                }
            }
            if(kuuntelija.getValinta() == 1) {
                if(asiakas.getKatuosoite().contains(kriteeri)) {
                    taulukko.getModel().addRow(asiakas.getAsiakkaanTiedotTaulukossa());
                }
            }
            if(kuuntelija.getValinta() == 2) {
                if(asiakas.getPostinumero().contains(kriteeri)) {
                    taulukko.getModel().addRow(asiakas.getAsiakkaanTiedotTaulukossa());
                }
            }
            if(kuuntelija.getValinta() == 3) {
                if(asiakas.getKaupunki().contains(kriteeri)) {
                    taulukko.getModel().addRow(asiakas.getAsiakkaanTiedotTaulukossa());
                }
            }
            if(kuuntelija.getValinta() == 4) {
                if(asiakas.getAsiakasnumero().contains(kriteeri)) {
                    taulukko.getModel().addRow(asiakas.getAsiakkaanTiedotTaulukossa());
                }
            }
            if(kuuntelija.getValinta() == 5) {
                if(asiakas.getLaskujaLahetetty().toString().contains(kriteeri)) {               
                    taulukko.getModel().addRow(asiakas.getAsiakkaanTiedotTaulukossa());
                }
            }
        }
    }
}
