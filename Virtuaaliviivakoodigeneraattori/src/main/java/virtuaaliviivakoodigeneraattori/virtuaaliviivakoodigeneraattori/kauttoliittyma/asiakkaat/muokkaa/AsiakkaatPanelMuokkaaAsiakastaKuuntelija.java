/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.muokkaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.AsiakkaatTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.AsiakkaatTaulukkoValintaKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanelMuokkaaAsiakastaKuuntelija implements ActionListener {

    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final AsiakkaatTaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public AsiakkaatPanelMuokkaaAsiakastaKuuntelija(Lataaja lataaja, AsiakkaatTaulukko taulukko, AsiakkaatTaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
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
                MuokkaaAsiakastaIkkuna muokkaaAsiakasta = new MuokkaaAsiakastaIkkuna(lataaja, taulukko, kuuntelija, lukko);
                SwingUtilities.invokeLater(muokkaaAsiakasta);
            } catch (Exception e) {
                AsiakkaatPanelMuokkaaAsiakastaPoikkeusIkkuna poikkeusIkkuna = new AsiakkaatPanelMuokkaaAsiakastaPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }

    }
}
