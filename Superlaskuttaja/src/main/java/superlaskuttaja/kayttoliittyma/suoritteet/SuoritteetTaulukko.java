///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package superlaskuttaja.kayttoliittyma.suoritteet;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import superlaskuttaja.kayttoliittyma.GregorianCalendarRenderer;
import superlaskuttaja.kayttoliittyma.TableModelSolujenMuokkaaminenEstetty;
import superlaskuttaja.logiikka.DataDeliver;
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
    private final DataDeliver lataaja;

    public SuoritteetTaulukko(DataDeliver lataaja) {
        this.lataaja = lataaja;
        this.taulukko = new JTable();
        this.model = new TableModelSolujenMuokkaaminenEstetty(new Object[][]{}, new Object[]{"Tilaajan nimi", "Tilaajan asiakasnumero", "Kuvaus", "Päivämäärä", "Määrä", "Yksikkö", "Yhteensä", "Laskutettu", "Suoritteen numero"});
        this.sorter = new TableRowSorter<>(model);
        this.taulukko.setModel(model);
        this.taulukko.setRowSorter(sorter);
        this.selectionModel = this.taulukko.getSelectionModel();
        this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.dateRenderer = new GregorianCalendarRenderer();
//        this.taulukko.getColumnModel().getColumn(2).setCellRenderer(dateRenderer);
        TableColumn tc = this.taulukko.getColumn("Suoritteen numero");
        this.taulukko.removeColumn(tc);
        muodostaSuoritteetTaulukko();
    }

    public final void muodostaSuoritteetTaulukko() {
        if (lataaja.getDbc().isYhdistetty()) {
            ResultSet rs = lataaja.getDbc().executeQuery("select distinct nimi, tilaaja, kuvaus, alkuaika, maara, maaranYksikot,\n"
                    + "((1+0.01*alvProsentti)*aHintaVeroton*maara) as yht, !isnull(lasku) as onkoLaskutettu, suoritteenNumero\n"
                    + "from Suorite, Asiakas\n"
                    + "where tilaaja = asiakasnumero\n"
                    + "and tilaajanVersio = versio\n"
                    + "");
            
            try {
                java.sql.ResultSetMetaData rsmd = rs.getMetaData();
                int colNo = rsmd.getColumnCount();
                
                Object[] objects = new Object[colNo];
                
                while (rs.next()) {
                    for (int i = 0; i < colNo; i++) {
                        objects[i] = rs.getObject(i + 1);
                    }
                    model.addRow(objects);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getSQLState());
            }
        }
    }
    
    public void addSuoritteetTaulukkoRiveja(ResultSet rs) {
        try {
            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            int colNo = rsmd.getColumnCount();

            Object[] objects = new Object[colNo];

            while (rs.next()) {
                for (int i = 0; i < colNo; i++) {
                    objects[i] = rs.getObject(i + 1);
                }
                model.addRow(objects);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
        }
    }
    
    public void addSuoritteetTaulukkoRiviKohtaan(int kohta, ResultSet rs) {
        try {
            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            int colNo = rsmd.getColumnCount();

            Object[] objects = new Object[colNo];

            while (rs.next()) {
                for (int i = 0; i < colNo; i++) {
                    objects[i] = rs.getObject(i + 1);
                }
                model.insertRow(kohta, objects);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
        }
    }
    
    public void paivitaSuoritteenTiedot (String suoritteenNumero) {
        int indeksi = 0;
        for (int i = 0; i < taulukko.getModel().getRowCount(); i++) {
            if (valueString(i, 8).equals(suoritteenNumero)) {
                indeksi = i;
            }
        }
        ResultSet rs = lataaja.getDbc().executeQuery("select distinct nimi, tilaaja, kuvaus, alkuaika, maara, maaranYksikot,\n"
                + "((1+0.01*alvProsentti)*aHintaVeroton*maara) as yht, !isnull(lasku) as onkoLaskutettu, suoritteenNumero\n"
                + "from Suorite, Asiakas\n"
                + "where tilaaja = asiakasnumero\n"
                + "and tilaajanVersio = versio\n"
                + "and suoritteenNumero = " + suoritteenNumero + "\n"
                + "");
        addSuoritteetTaulukkoRiviKohtaan(indeksi, rs);
        model.removeRow(indeksi + 1);
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

    public String valueString(Integer x, Integer y) {
        return (getModel().getValueAt(x, y).toString());
    }

    public Object value(Integer x, Integer y) {
        return (getModel().getValueAt(x, y));
    }
}
