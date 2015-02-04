/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LaskutPanelNaytaKaikkiKuuntelija implements ActionListener {

    private final DataDeliver lataaja;
    private final LaskutTaulukko taulukko;

    public LaskutPanelNaytaKaikkiKuuntelija(DataDeliver lataaja, LaskutTaulukko taulukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        taulukko.getSorter().setRowFilter(null);
    }
}
