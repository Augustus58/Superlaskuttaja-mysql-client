/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superlaskuttaja.kayttoliittyma.laskut.lisaa;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import superlaskuttaja.logiikka.DataDeliver;
import superlaskuttaja.logiikka.Suorite;

/**
 *
 * @author Augustus58
 */
public class LisaaLaskuIkkunaSuoritteetList extends JList { 

    DefaultListModel<Suorite> model;
    String[] listaaVastSuoritteet;
    
    public LisaaLaskuIkkunaSuoritteetList() {
        super();
        this.model = new DefaultListModel<>();
        this.setModel(model);
    }
    
    @Override
    public DefaultListModel getModel() {
        return model;
    }

    public int[] valitutRivit() {
        return this.getSelectedIndices();
    }

    public void setListaaVastSuoritteet(String[] listaaVastSuoritteet) {
        this.listaaVastSuoritteet = listaaVastSuoritteet;
    }

    public String[] getListaaVastSuoritteet() {
        return listaaVastSuoritteet;
    }
    
}
