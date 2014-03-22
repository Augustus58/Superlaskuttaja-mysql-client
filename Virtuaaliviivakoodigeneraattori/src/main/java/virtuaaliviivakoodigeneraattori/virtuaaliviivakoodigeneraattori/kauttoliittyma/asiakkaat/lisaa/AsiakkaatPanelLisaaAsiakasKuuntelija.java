/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanelLisaaAsiakasKuuntelija implements ActionListener {
    private Lataaja lataaja;

    public AsiakkaatPanelLisaaAsiakasKuuntelija(Lataaja lataaja) {
        this.lataaja = lataaja;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        LisaaAsiakas lisaaAsiakas = new LisaaAsiakas(lataaja);
        SwingUtilities.invokeLater(lisaaAsiakas);
    }
}
