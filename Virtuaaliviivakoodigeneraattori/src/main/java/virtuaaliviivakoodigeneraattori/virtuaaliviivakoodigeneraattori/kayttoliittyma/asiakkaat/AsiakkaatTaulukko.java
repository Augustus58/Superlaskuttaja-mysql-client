///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.asiakkaat;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.TableModelSolujenMuokkaaminenEstetty;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Asiakas;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatTaulukko {

    private final JTable taulukko;
    private final TableModelSolujenMuokkaaminenEstetty model;
    private final ListSelectionModel selectionModel;
    private TableRowSorter<TableModelSolujenMuokkaaminenEstetty> sorter;
    private final Lataaja lataaja;

    public AsiakkaatTaulukko(Lataaja lataaja) {
        this.lataaja = lataaja;
        this.taulukko = new JTable();
        this.model = new TableModelSolujenMuokkaaminenEstetty(new Object[][]{}, new Object[]{"Nimi", "Katuosoite", "Postinumero", "Kaupunki", "Asiakasnumero", "Laskuja lähetetty"});            
        this.sorter = new TableRowSorter<>(model);
        this.taulukko.setModel(model);
        this.taulukko.setRowSorter(sorter);
        this.selectionModel = this.taulukko.getSelectionModel();
        this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        muodostaAsiakkaatTaulukko();
    }

    public final void muodostaAsiakkaatTaulukko() {
        if (!lataaja.getLadattuTietovarasto().getAsiakkaat().isEmpty()) {
            for (int i = 0; i < lataaja.getLadattuTietovarasto().getAsiakkaat().size(); i++) {
                model.addRow(lataaja.getLadattuTietovarasto().getAsiakkaat().get(i).asiakkaanTiedotTaulukossa());
            }
        }
    }
    
    public void addAsiakkaatTaulukkoRivi(Asiakas asiakas) {
        model.addRow(asiakas.asiakkaanTiedotTaulukossa());
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

    public TableRowSorter<TableModelSolujenMuokkaaminenEstetty> getSorter() {
        return sorter;
    }
    
    public String getValueString(Integer x, Integer y) {
        return (getModel().getValueAt(x, y).toString());
    }
}
