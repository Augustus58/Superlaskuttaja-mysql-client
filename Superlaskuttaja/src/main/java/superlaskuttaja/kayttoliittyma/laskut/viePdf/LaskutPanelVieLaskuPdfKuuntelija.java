/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.viePdf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LaskutPanelVieLaskuPdfKuuntelija implements ActionListener {

    private final DataDeliver lataaja;
    private final LaskutTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public LaskutPanelVieLaskuPdfKuuntelija(DataDeliver lataaja, LaskutTaulukko taulukko, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.kuuntelija = kuuntelija;
        this.lukko = lukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            try {
                kuuntelija.paivitaArvo();
                int laskunNumero = (Integer) taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 3);
                lataaja.getPdfe().vieLaskuPdf(Integer.toString(laskunNumero));
            } catch (Exception e) {
                LaskutPanelVieLaskuPdfPoikkeusIkkuna poikkeusIkkuna = new LaskutPanelVieLaskuPdfPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
