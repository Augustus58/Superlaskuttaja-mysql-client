///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package superlaskuttaja.kayttoliittyma.asiakkaat;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import superlaskuttaja.kayttoliittyma.TableModelSolujenMuokkaaminenEstetty;
import superlaskuttaja.logiikka.Asiakas;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatTaulukko {

    private final JTable taulukko;
    private final TableModelSolujenMuokkaaminenEstetty model;
    private final ListSelectionModel selectionModel;
    private final TableRowSorter<TableModelSolujenMuokkaaminenEstetty> sorter;
    private final DataDeliver lataaja;

    public AsiakkaatTaulukko(DataDeliver lataaja) {
        this.lataaja = lataaja;
        this.taulukko = new JTable();
        this.model = new TableModelSolujenMuokkaaminenEstetty(new Object[][]{}, new Object[]{"Nimi", "Katuosoite", "Postinumero", "Kaupunki", "Sähköposti", "Asiakasnumero", "Laskuja lähetetty", "Versio"});
        this.sorter = new TableRowSorter<>(model);
        this.taulukko.setModel(model);
        this.taulukko.setRowSorter(sorter);
        this.selectionModel = this.taulukko.getSelectionModel();
        this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableColumn tc = this.taulukko.getColumn("Versio");
        this.taulukko.removeColumn(tc);
        muodostaAsiakkaatTaulukko();
    }

    public final void muodostaAsiakkaatTaulukko() {
        if (lataaja.getDbc().isYhdistetty()) {
            ResultSet rs = lataaja.getDbc().executeQuery("select distinct nimi, katuosoite, postinumero, kaupunki, email, asiakasnumero, laskujaLahetetty, versio\n"
                    + "from Asiakas A\n"
                    + "where versio = (select max(versio) from Asiakas\n"
                    + "where asiakasnumero = A.asiakasnumero)\n"
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

    public void addAsiakkaatTaulukkoRiveja(ResultSet rs) {
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

    public void addAsiakkaatTaulukkoRiviKohtaan(int kohta, ResultSet rs) {
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

    public void paivitaAsiakkaanTiedot(String asiakasnumero) {
        int indeksi = 0;
        for (int i = 0; i < taulukko.getModel().getRowCount(); i++) {
            if (getValueString(i, 5).equals(asiakasnumero)) {
                indeksi = i;
            }
        }
        ResultSet rs = lataaja.getDbc().executeQuery("select distinct nimi, katuosoite, postinumero, kaupunki, email, asiakasnumero, laskujaLahetetty, versio\n"
                + "from Asiakas\n"
                + "where versio = (select max(versio) from Asiakas\n"
                + "where asiakasnumero = " + asiakasnumero + ")\n"
                + "and asiakasnumero = " + asiakasnumero + "\n"
                + "");
        addAsiakkaatTaulukkoRiviKohtaan(indeksi, rs);
        model.removeRow(indeksi + 1);
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
