/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superlaskuttaja.kayttoliittyma.laskut.lisaa;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import superlaskuttaja.logiikka.Lataaja;
import superlaskuttaja.logiikka.Suorite;

/**
 *
 * @author Augustus58
 */
public class LisaaLaskuIkkunaSuoritteetList extends JList {

    DefaultListModel<Suorite> model;
    LisaaLaskutIkkunaComboBoxKuuntelija comboBoxKuuntelija;
    Lataaja lataaja;
    
    public LisaaLaskuIkkunaSuoritteetList(LisaaLaskutIkkunaComboBoxKuuntelija comboBoxKuuntelija, Lataaja lataaja) {
        super();
        this.comboBoxKuuntelija = comboBoxKuuntelija;
        this.lataaja = lataaja;
        this.model = new DefaultListModel<>();
        this.setModel(model);
    }
    
    public void paivitaListanSisalto() {
        model.removeAllElements();
        for (int i = 0; i < lataaja.getLadattuTietovarasto().asiakkaanLaskuttamattomatSuoritteetArrayList(lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxKuuntelija.getValinta())).size(); i++) {
            model.addElement(lataaja.getLadattuTietovarasto().asiakkaanLaskuttamattomatSuoritteetArrayList(lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxKuuntelija.getValinta())).get(i));
        }
    }

    @Override
    public DefaultListModel getModel() {
        return model;
    }

    public int[] valitutRivit() {
        return this.getSelectedIndices();
    }
}
