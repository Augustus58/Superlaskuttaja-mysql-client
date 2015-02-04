/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskuttaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.laskuttaja.muokkaaLaskuttajaa.MuokkaaLaskuttajanTietojaIkkuna;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LaskuttajaOsioJFrameMuokkaaLaskuttajanTietojaKuuntelija implements ActionListener {

    private final DataDeliver lataaja;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel panel;

    public LaskuttajaOsioJFrameMuokkaaLaskuttajanTietojaKuuntelija(DataDeliver lataaja, NappulaLukko lukko, LaskuttajaOsioJPanel panel) {
        this.lataaja = lataaja;
        this.lukko = lukko;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            try {
                MuokkaaLaskuttajanTietojaIkkuna muokkaaTietoja = new MuokkaaLaskuttajanTietojaIkkuna(lataaja, lukko, panel);
                SwingUtilities.invokeLater(muokkaaTietoja);
            } catch (IllegalStateException e) {
                LaskuttajaOsioJFrameMuokkaaLaskuttajanTietojaLaskuttajallaLaskujaPoikkeusIkkuna poikkeusIkkuna = new LaskuttajaOsioJFrameMuokkaaLaskuttajanTietojaLaskuttajallaLaskujaPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
