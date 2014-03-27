///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.TableModelSolujenMuokkaaminenEstetty;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Asiakas;
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
    private Lataaja lataaja;

    public SuoritteetTaulukko(Lataaja lataaja) {
        this.lataaja = lataaja;
        this.taulukko = new JTable();
        this.model = new TableModelSolujenMuokkaaminenEstetty(new Object[][]{}, new Object[]{"Asiakas", "Kuvaus", "Päivämäärä", "Määrä", "Yksikkö", "à hinta", "Alv %", "Alv €", "Yhteensä", "Laskutettu"});            
        this.taulukko.setModel(model);
        this.selectionModel = this.taulukko.getSelectionModel();
        this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taulukko.setAutoCreateRowSorter(true);
        muodostaSuoritteetTaulukko();
    }

    public final void muodostaSuoritteetTaulukko() {
        if (!lataaja.getLadattuTietovarasto().getLaskut().isEmpty()) {
            for (int i = 0; i < lataaja.getLadattuTietovarasto().getSuoritteet().size(); i++) {
                model.addRow(lataaja.getLadattuTietovarasto().getSuoritteet().get(i).getSuoritteenTiedotTaulukossa());
            }
        }
    }
    
    public void addSuoritteetTaulukkoRivi(Suorite suorite) {
        model.addRow(suorite.getSuoritteenTiedotTaulukossa());
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
