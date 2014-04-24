///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package superlaskuttaja.kayttoliittyma.suoritteet;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import superlaskuttaja.kayttoliittyma.GregorianCalendarRenderer;
import superlaskuttaja.kayttoliittyma.TableModelSolujenMuokkaaminenEstetty;
import superlaskuttaja.logiikka.Lataaja;
import superlaskuttaja.logiikka.Suorite;

/**
 *
 * @author Augustus58
 */
public class SuoritteetTaulukko {

    private final JTable taulukko;
    private final TableModelSolujenMuokkaaminenEstetty model;
    private final ListSelectionModel selectionModel;
    private final TableRowSorter<TableModelSolujenMuokkaaminenEstetty> sorter;
    private final GregorianCalendarRenderer dateRenderer;
    private final Lataaja lataaja;

    public SuoritteetTaulukko(Lataaja lataaja) {
        this.lataaja = lataaja;
        this.taulukko = new JTable();
        this.model = new TableModelSolujenMuokkaaminenEstetty(new Object[][]{}, new Object[]{"Asiakas", "Kuvaus", "Päivämäärä", "Määrä", "Yksikkö", "à hinta", "Alv %", "Alv €", "Yhteensä", "Laskutettu"});            
        this.sorter = new TableRowSorter<>(model);
        this.taulukko.setModel(model);
        this.taulukko.setRowSorter(sorter);
        this.selectionModel = this.taulukko.getSelectionModel();
        this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.dateRenderer = new GregorianCalendarRenderer();
        this.taulukko.getColumnModel().getColumn(2).setCellRenderer(dateRenderer);
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
