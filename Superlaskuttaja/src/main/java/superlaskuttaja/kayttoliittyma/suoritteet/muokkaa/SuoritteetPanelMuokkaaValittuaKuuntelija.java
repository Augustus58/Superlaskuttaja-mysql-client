/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.muokkaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class SuoritteetPanelMuokkaaValittuaKuuntelija implements ActionListener {

    private final DataDeliver lataaja;
    private final SuoritteetTaulukko taulukko;
    private final NappulaLukko lukko;
    private final TaulukkoValintaKuuntelija kuuntelija;

    public SuoritteetPanelMuokkaaValittuaKuuntelija(DataDeliver lataaja, SuoritteetTaulukko taulukko, NappulaLukko lukko, TaulukkoValintaKuuntelija kuuntelija) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.lukko = lukko;
        this.kuuntelija = kuuntelija;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            try {
                kuuntelija.paivitaArvo();
                
                ResultSet rs = lataaja.getDbc().executeQuery("select distinct *\n"
                        + "from Suorite\n"
                        + "where lasku is not null\n"
                        + "and suoritteenNumero = " + taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 8).toString() + "\n"
                        + "");
                
                if (rs.first()) {
                    throw new IllegalStateException();
                }
                MuokkaaValittuaIkkuna muokkaaSuoritetta = new MuokkaaValittuaIkkuna(lataaja, taulukko, lukko, kuuntelija);
                SwingUtilities.invokeLater(muokkaaSuoritetta);
            } catch (IllegalStateException e) {
                SuoritteetPanelMuokkaaValittuaPoikkeusIkkunaSuoriteLaskulla poikkeusIkkuna = new SuoritteetPanelMuokkaaValittuaPoikkeusIkkunaSuoriteLaskulla();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            } catch (Exception e) {
                SuoritteetPanelMuokkaaValittuaPoikkeusIkkuna poikkeusIkkuna = new SuoritteetPanelMuokkaaValittuaPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
