/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.yhteenveto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 *
 * @author Augustus58
 */
public class LaskuttajaOsioJFrameMuokkaaLaskuttajanTietojaLaskuttajallaLaskujaPoikkeusIkkunaOkKuuntelija implements ActionListener {
    private final JDialog dialog;

    public LaskuttajaOsioJFrameMuokkaaLaskuttajanTietojaLaskuttajallaLaskujaPoikkeusIkkunaOkKuuntelija(JDialog dialog) {
        this.dialog = dialog;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        dialog.dispose();
        
    }
}
