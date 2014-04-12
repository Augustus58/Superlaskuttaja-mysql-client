/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet;

import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.TaulukkoValintaKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

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
                lataaja.getLadattuTietovarasto().getSuoritteet().remove(kuuntelija.getPaivitettyArvo().intValue());
                taulukko.getModel().removeRow(kuuntelija.getPaivitettyArvo());
            } catch (Exception e) {
            SuoritteetPanelPoistaSuoritePoikkeusIkkuna poikkeusIkkuna = new SuoritteetPanelPoistaSuoritePoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
