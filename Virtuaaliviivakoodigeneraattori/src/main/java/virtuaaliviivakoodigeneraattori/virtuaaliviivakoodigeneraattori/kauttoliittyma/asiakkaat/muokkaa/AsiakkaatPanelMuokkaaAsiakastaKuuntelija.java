/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.muokkaa;

import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.muokkaa.MuokkaaAsiakastaIkkuna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.AsiakkaatTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanelMuokkaaAsiakastaKuuntelija implements ActionListener {
    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;

    public AsiakkaatPanelMuokkaaAsiakastaKuuntelija(Lataaja lataaja, AsiakkaatTaulukko taulukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        MuokkaaAsiakastaIkkuna muokkaaAsiakasta = new MuokkaaAsiakastaIkkuna(lataaja, taulukko);
        SwingUtilities.invokeLater(muokkaaAsiakasta);
    }
}
