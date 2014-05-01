/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.poista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LaskutPanelPoistaLaskuKuuntelija implements ActionListener {

    private final Lataaja lataaja;
    private final LaskutTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public LaskutPanelPoistaLaskuKuuntelija(Lataaja lataaja, LaskutTaulukko taulukko, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
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
                
                for (int i = 0; i < lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getSuoritteet().size(); i++) {
                    lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getSuoritteet().get(i).poistaLasku();
                }
                
                lataaja.getLadattuTietovarasto().getLaskut().remove(kuuntelija.getPaivitettyArvo().intValue());
                taulukko.getModel().removeRow(kuuntelija.getPaivitettyArvo().intValue());
            } catch (Exception e) {
                LaskutPanelPoistaLaskuPoikkeusIkkuna poikkeusIkkuna = new LaskutPanelPoistaLaskuPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
