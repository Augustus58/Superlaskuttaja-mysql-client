/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskuttaja.asetaTietokanta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.kayttoliittyma.laskuttaja.LaskuttajaOsioJPanel;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LaskuttajaPanelAsetaTietokantaKuuntelija implements ActionListener {

    private final DataDeliver lataaja;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel panel;
    private final JTabbedPane tabbedPane;
    private final AsiakkaatTaulukko asiakkaatTaulukko;
    private final SuoritteetTaulukko suoritteetTaulukko;
    private final LaskutTaulukko laskutTaulukko;

    public LaskuttajaPanelAsetaTietokantaKuuntelija(DataDeliver lataaja, NappulaLukko lukko, LaskuttajaOsioJPanel panel, JTabbedPane tabbedPane, AsiakkaatTaulukko asiakkaatTaulukko, SuoritteetTaulukko suoritteetTaulukko, LaskutTaulukko laskutTaulukko) {
        this.lataaja = lataaja;
        this.lukko = lukko;
        this.panel = panel;
        this.tabbedPane = tabbedPane;
        this.asiakkaatTaulukko = asiakkaatTaulukko;
        this.suoritteetTaulukko = suoritteetTaulukko;
        this.laskutTaulukko = laskutTaulukko;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            AsetaTietokantaIkkuna AsetaTietokantaIkkuna = new AsetaTietokantaIkkuna(lataaja, lukko, panel, tabbedPane, asiakkaatTaulukko, suoritteetTaulukko, laskutTaulukko);
            SwingUtilities.invokeLater(AsetaTietokantaIkkuna);
        }
    }
}
