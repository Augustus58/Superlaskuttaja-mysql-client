///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.TableModelSolujenMuokkaaminenEstetty;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Suorite;

/**
 *
 * @author Augustus58
 */
public class SuoritteetTaulukko {

    private JTable taulukko;
    private TableModelSolujenMuokkaaminenEstetty model;
    private ListSelectionModel selectionModel;
    private TableRowSorter<TableModelSolujenMuokkaaminenEstetty> sorter;
    private Lataaja lataaja;

    public SuoritteetTaulukko(Lataaja lataaja) {
        this.lataaja = lataaja;
        this.taulukko = new JTable();
        this.model = new TableModelSolujenMuokkaaminenEstetty(new Object[][]{}, new Object[]{"Asiakas", "Kuvaus", "Päivämäärä", "Määrä", "Yksikkö", "à hinta", "Alv %", "Alv €", "Yhteensä", "Laskutettu"});            
        this.sorter = new TableRowSorter<>(model);
        this.taulukko.setModel(model);
        this.taulukko.setRowSorter(sorter);
        this.selectionModel = this.taulukko.getSelectionModel();
        this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        muodostaSuoritteetTaulukko();
    }

    public final void muodostaSuoritteetTaulukko() {
        if (!lataaja.getLadattuTietovarasto().getSuoritteet().isEmpty()) {
            for (int i = 0; i < lataaja.getLadattuTietovarasto().getSuoritteet().size(); i++) {
                model.addRow(lataaja.getLadattuTietovarasto().getSuoritteet().get(i).suoritteenTiedotTaulukossa());
            }
        }
    }
    
    public void addSuoritteetTaulukkoRivi(Suorite suorite) {
        model.addRow(suorite.suoritteenTiedotTaulukossa());
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
