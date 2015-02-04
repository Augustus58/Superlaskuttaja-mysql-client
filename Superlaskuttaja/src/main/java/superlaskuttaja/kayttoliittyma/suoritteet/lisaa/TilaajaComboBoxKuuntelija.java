/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author Augustus58
 */
public class TilaajaComboBoxKuuntelija implements ActionListener {

    private Integer valinta;
    private JComboBox comboBoxVastaanottaja;

    public TilaajaComboBoxKuuntelija() {
        this.valinta = -5;
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox comboBox = (JComboBox)e.getSource();
        valinta = (Integer)comboBox.getSelectedIndex();
        comboBoxVastaanottaja.setSelectedIndex(valinta);
    }

    public Integer getValinta() {
        if(valinta == -5) {
            return 0;
        }
        return valinta;
    }

    public void setValinta(Integer valinta) {
        this.valinta = valinta;
    }

    public void setComboBoxVastaanottaja(JComboBox comboBoxVastaanottaja) {
        this.comboBoxVastaanottaja = comboBoxVastaanottaja;
    }

}
