/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.poista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class SuoritteetPanelPoistaSuoriteKuuntelija implements ActionListener {

    private final Lataaja lataaja;
    private final SuoritteetTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public SuoritteetPanelPoistaSuoriteKuuntelija(Lataaja lataaja, SuoritteetTaulukko taulukko, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
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
                for (int i = 0; i < lataaja.getLadattuTietovarasto().getLaskut().size(); i++) {
                    if (lataaja.getLadattuTietovarasto().getLaskut().get(i).equals(lataaja.getLadattuTietovarasto().getSuoritteet().get(kuuntelija.getPaivitettyArvo()).getLasku())) {
                        throw new IllegalStateException();
                    }
                }
                lataaja.getLadattuTietovarasto().getSuoritteet().remove(kuuntelija.getPaivitettyArvo().intValue());
                taulukko.getModel().removeRow(kuuntelija.getPaivitettyArvo());
            } catch (IllegalStateException e) {
                SuoritteetPanelPoistaSuoriteSuoriteOnLaskullaPoikkeusIkkuna poikkeusIkkuna = new SuoritteetPanelPoistaSuoriteSuoriteOnLaskullaPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            } catch (Exception e) {
                SuoritteetPanelPoistaSuoritePoikkeusIkkuna poikkeusIkkuna = new SuoritteetPanelPoistaSuoritePoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
