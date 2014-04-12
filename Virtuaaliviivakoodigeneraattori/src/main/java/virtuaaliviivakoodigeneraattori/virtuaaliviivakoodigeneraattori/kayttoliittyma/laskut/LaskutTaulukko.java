///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.laskut;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.GregorianCalendarRenderer;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.TableModelSolujenMuokkaaminenEstetty;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lasku;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LaskutTaulukko {

    private LaskutTaulukkoJTable taulukko;
    private TableModelSolujenMuokkaaminenEstetty model;
    private ListSelectionModel selectionModel;
    private TableRowSorter<TableModelSolujenMuokkaaminenEstetty> sorter;
    private GregorianCalendarRenderer dateRenderer;
    private Lataaja lataaja;

    public LaskutTaulukko(Lataaja lataaja) {
        this.lataaja = lataaja;
        this.taulukko = new LaskutTaulukkoJTable();
        this.model = new TableModelSolujenMuokkaaminenEstetty(new Object[][]{}, new Object[]{"Asiakas", "Asiakasnumero", "Viite", "Laskun numero", "Summa", "Päiväys", "Eräpäivä", "Maksettu"});           
        this.sorter = new TableRowSorter<>(model);
        this.taulukko.setModel(model);
        this.taulukko.setRowSorter(sorter);
        this.selectionModel = this.taulukko.getSelectionModel();
        this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.dateRenderer = new GregorianCalendarRenderer();
        this.taulukko.getColumnModel().getColumn(5).setCellRenderer(dateRenderer);
        this.taulukko.getColumnModel().getColumn(6).setCellRenderer(dateRenderer);
        muodostaLaskutTaulukko();
    }

    public final void muodostaLaskutTaulukko() {
        if (!lataaja.getLadattuTietovarasto().getLaskut().isEmpty()) {
            for (int i = 0; i < lataaja.getLadattuTietovarasto().getLaskut().size(); i++) {
                model.addRow(lataaja.getLadattuTietovarasto().getLaskut().get(i).laskunTiedotTaulukossa());
            }
        }
    }
    
    public void lisaaLaskutTaulukkoRivi(Lasku lasku) {
        model.addRow(lasku.laskunTiedotTaulukossa());
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
