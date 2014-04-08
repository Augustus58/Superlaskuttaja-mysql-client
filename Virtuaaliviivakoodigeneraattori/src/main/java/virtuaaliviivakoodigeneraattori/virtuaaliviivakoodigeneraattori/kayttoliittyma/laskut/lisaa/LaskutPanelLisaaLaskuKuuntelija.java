/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.laskut.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.laskut.LaskutTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LaskutPanelLisaaLaskuKuuntelija implements ActionListener {

    private final Lataaja lataaja;
    private final LaskutTaulukko taulukko;
    private final NappulaLukko lukko;

    public LaskutPanelLisaaLaskuKuuntelija(Lataaja lataaja, LaskutTaulukko taulukko, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.lukko = lukko;
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
                LisaaLaskuIkkuna lisaaSuorite = new LisaaLaskuIkkuna(lataaja, taulukko, lukko);
                SwingUtilities.invokeLater(lisaaSuorite);
            } catch (Exception e) {
                LaskutPanelLisaaLaskuPoikkeusIkkuna poikkeusIkkuna = new LaskutPanelLisaaLaskuPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
