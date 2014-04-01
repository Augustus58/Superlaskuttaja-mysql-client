/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet.lisaaValitusta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.TaulukkoValintaKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class SuoritteetPanelLisaaSuoriteValitustaKuuntelija implements ActionListener {

    private final Lataaja lataaja;
    private final SuoritteetTaulukko taulukko;
    private final NappulaLukko lukko;
    private final TaulukkoValintaKuuntelija kuuntelija;

    public SuoritteetPanelLisaaSuoriteValitustaKuuntelija(Lataaja lataaja, SuoritteetTaulukko taulukko, NappulaLukko lukko, TaulukkoValintaKuuntelija kuuntelija) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.lukko = lukko;
        this.kuuntelija = kuuntelija;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (!lukko.onkoLukkoPaalla()) {
                kuuntelija.paivitaArvo();
                LisaaSuoriteValitustaIkkuna lisaaSuorite = new LisaaSuoriteValitustaIkkuna(lataaja, taulukko, lukko, kuuntelija);
                SwingUtilities.invokeLater(lisaaSuorite);
            }
        } catch (Exception e) {
            SuoritteetPanelLisaaSuoriteValitustaPoikkeusIkkuna poikkeusIkkuna = new SuoritteetPanelLisaaSuoriteValitustaPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }
    }
}
