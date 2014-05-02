/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.muokkaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.kayttoliittyma.yhteenveto.LaskuttajaOsioJPanel;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LaskutPanelMuokkaaLaskuaKuuntelija implements ActionListener {

    private final Lataaja lataaja;
    private final LaskutTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;
    private final SuoritteetTaulukko suoritteetTaulukko;
    private final AsiakkaatTaulukko asiakkaatTaulukko;
    private final LaskuttajaOsioJPanel laskuttajaOsioJPanel;

    public LaskutPanelMuokkaaLaskuaKuuntelija(Lataaja lataaja, LaskutTaulukko taulukko, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko, SuoritteetTaulukko suoritteetTaulukko, AsiakkaatTaulukko asiakkaatTaulukko, LaskuttajaOsioJPanel laskuttajaOsioJPanel) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.kuuntelija = kuuntelija;
        this.lukko = lukko;
        this.suoritteetTaulukko = suoritteetTaulukko;
        this.asiakkaatTaulukko = asiakkaatTaulukko;
        this.laskuttajaOsioJPanel = laskuttajaOsioJPanel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            try {
                kuuntelija.paivitaArvo();
                if (lataaja.getLadattuTietovarasto().getLaskut().isEmpty()) {
                    throw new NullPointerException("Ei laskuja.");
                }
                MuokkaaLaskuaIkkuna lisaaSuorite = new MuokkaaLaskuaIkkuna(lataaja, taulukko, kuuntelija, lukko, suoritteetTaulukko, asiakkaatTaulukko, laskuttajaOsioJPanel);
                SwingUtilities.invokeLater(lisaaSuorite);
            } catch (Exception e) {
                LaskutPanelMuokkaaLaskuaPoikkeusIkkuna poikkeusIkkuna = new LaskutPanelMuokkaaLaskuaPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
