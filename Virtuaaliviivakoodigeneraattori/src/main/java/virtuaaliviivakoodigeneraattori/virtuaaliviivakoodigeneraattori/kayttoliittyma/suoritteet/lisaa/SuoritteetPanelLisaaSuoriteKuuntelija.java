/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class SuoritteetPanelLisaaSuoriteKuuntelija implements ActionListener {

    private final Lataaja lataaja;
    private final SuoritteetTaulukko taulukko;
    private final NappulaLukko lukko;

    public SuoritteetPanelLisaaSuoriteKuuntelija(Lataaja lataaja, SuoritteetTaulukko taulukko, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.lukko = lukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            try {
                if (lataaja.getLadattuTietovarasto().getAsiakkaat().isEmpty()) {
                    throw new NullPointerException("Ei asiakkaita.");
                }
                LisaaSuoriteIkkuna lisaaSuorite = new LisaaSuoriteIkkuna(lataaja, taulukko, lukko);
                SwingUtilities.invokeLater(lisaaSuorite);
            } catch (Exception e) {
                SuoritteetPanelLisaaSuoritePoikkeusIkkuna poikkeusIkkuna = new SuoritteetPanelLisaaSuoritePoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
