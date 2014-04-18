/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.asiakkaat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanelNaytaKaikkiKuuntelija implements ActionListener {

    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;

    public AsiakkaatPanelNaytaKaikkiKuuntelija(Lataaja lataaja, AsiakkaatTaulukko taulukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        taulukko.getSorter().setRowFilter(null);
    }
}
