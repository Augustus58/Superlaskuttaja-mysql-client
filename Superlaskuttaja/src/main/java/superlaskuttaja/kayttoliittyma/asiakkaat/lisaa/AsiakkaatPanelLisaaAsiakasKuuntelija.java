/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.asiakkaat.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanelLisaaAsiakasKuuntelija implements ActionListener {
    private final DataDeliver lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final NappulaLukko lukko;

    public AsiakkaatPanelLisaaAsiakasKuuntelija(DataDeliver lataaja, AsiakkaatTaulukko taulukko, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.lukko = lukko;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            LisaaAsiakasIkkuna lisaaAsiakas = new LisaaAsiakasIkkuna(lataaja, taulukko, lukko);
            SwingUtilities.invokeLater(lisaaAsiakas);
        }
    }
}
