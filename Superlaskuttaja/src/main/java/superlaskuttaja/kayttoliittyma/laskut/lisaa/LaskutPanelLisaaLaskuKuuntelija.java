/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.kayttoliittyma.yhteenveto.LaskuttajaOsioJPanel;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LaskutPanelLisaaLaskuKuuntelija implements ActionListener {

    private final Lataaja lataaja;
    private final LaskutTaulukko taulukko;
    private final NappulaLukko lukko;
    private final SuoritteetTaulukko suoritteetTaulukko;
    private final AsiakkaatTaulukko asiakkaatTaulukko;
    private final LaskuttajaOsioJPanel laskuttajaOsioJPanel;

    public LaskutPanelLisaaLaskuKuuntelija(Lataaja lataaja, LaskutTaulukko taulukko, NappulaLukko lukko, SuoritteetTaulukko suoritteetTaulukko, AsiakkaatTaulukko asiakkaatTaulukko, LaskuttajaOsioJPanel laskuttajaOsioJPanel) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.lukko = lukko;
        this.suoritteetTaulukko = suoritteetTaulukko;
        this.asiakkaatTaulukko = asiakkaatTaulukko;
        this.laskuttajaOsioJPanel = laskuttajaOsioJPanel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            try {
                if (!lataaja.getLadattuTietovarasto().isLaskuttajaLisatty()) {
                    throw new NullPointerException("Laskuttajan tietoja ei ole lisätty.");
                }
                if (lataaja.getLadattuTietovarasto().getAsiakkaat().isEmpty()) {
                    throw new NullPointerException("Ei asiakkaita.");
                }
                if (lataaja.getLadattuTietovarasto().getSuoritteet().isEmpty()) {
                    throw new NullPointerException("Ei suoritteita.");
                }
                LisaaLaskuIkkuna lisaaSuorite = new LisaaLaskuIkkuna(lataaja, taulukko, lukko, suoritteetTaulukko, asiakkaatTaulukko, laskuttajaOsioJPanel);
                SwingUtilities.invokeLater(lisaaSuorite);
            } catch (Exception e) {
                LaskutPanelLisaaLaskuPoikkeusIkkuna poikkeusIkkuna = new LaskutPanelLisaaLaskuPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
