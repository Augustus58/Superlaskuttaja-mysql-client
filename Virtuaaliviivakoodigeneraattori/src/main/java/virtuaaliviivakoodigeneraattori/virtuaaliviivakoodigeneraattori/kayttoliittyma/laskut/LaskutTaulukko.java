///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.laskut;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.TableModelSolujenMuokkaaminenEstetty;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Asiakas;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LaskutTaulukko {

    private final JTable taulukko;
    private final TableModelSolujenMuokkaaminenEstetty model;
    private final ListSelectionModel selectionModel;
    private final Lataaja lataaja;

    public LaskutTaulukko(Lataaja lataaja) {
        this.lataaja = lataaja;
        this.taulukko = new JTable();
        this.model = new TableModelSolujenMuokkaaminenEstetty(new Object[][]{}, new Object[]{"Nimi", "Katuosoite", "Postinumero", "Kaupunki", "Asiakasnumero", "Laskuja lähetetty"});            
        this.taulukko.setModel(model);
        this.selectionModel = this.taulukko.getSelectionModel();
        this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taulukko.setAutoCreateRowSorter(true);
        muodostaLaskutTaulukko();
    }

    public final void muodostaLaskutTaulukko() {
        if (!lataaja.getLadattuTietovarasto().getLaskut().isEmpty()) {
            for (int i = 0; i < lataaja.getLadattuTietovarasto().getLaskut().size(); i++) {
                model.addRow(lataaja.getLadattuTietovarasto().getAsiakkaat().get(i).getAsiakkaanTiedotTaulukossa());
            }
        }
    }
    
    public void addAsiakkaatTaulukkoRivi(Asiakas asiakas) {
        model.addRow(asiakas.getAsiakkaanTiedotTaulukossa());
    }

    public JTable getTaulukko() {
        return taulukko;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public ListSelectionModel getSelectionModel() {
        return selectionModel;
    }

    public String getValueString(Integer x, Integer y) {
        return (getModel().getValueAt(x, y).toString());
    }

    public void reset() {
        model.setRowCount(0);
    }

}
