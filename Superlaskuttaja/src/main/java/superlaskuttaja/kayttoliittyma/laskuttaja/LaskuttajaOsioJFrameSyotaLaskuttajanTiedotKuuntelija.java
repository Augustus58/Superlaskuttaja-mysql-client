/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskuttaja;

import superlaskuttaja.kayttoliittyma.laskuttaja.lisaaLaskuttaja.LisaaLaskuttajanTiedotIkkuna;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LaskuttajaOsioJFrameSyotaLaskuttajanTiedotKuuntelija implements ActionListener {

    private final DataDeliver lataaja;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel panel;

    public LaskuttajaOsioJFrameSyotaLaskuttajanTiedotKuuntelija(DataDeliver lataaja, NappulaLukko lukko, LaskuttajaOsioJPanel panel) {
        this.lataaja = lataaja;
        this.lukko = lukko;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            LisaaLaskuttajanTiedotIkkuna syotaTiedot = new LisaaLaskuttajanTiedotIkkuna(lataaja, lukko, panel);
            SwingUtilities.invokeLater(syotaTiedot);
        }
    }

}
