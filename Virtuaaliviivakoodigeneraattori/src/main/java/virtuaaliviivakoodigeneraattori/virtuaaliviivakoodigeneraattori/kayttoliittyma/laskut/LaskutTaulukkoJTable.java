/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.laskut;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Augustus58
 */
public class LaskutTaulukkoJTable extends JTable{

    public LaskutTaulukkoJTable() {
    }

    public LaskutTaulukkoJTable(TableModel dm) {
        super(dm);
    }

    public LaskutTaulukkoJTable(TableModel dm, TableColumnModel cm) {
        super(dm, cm);
    }

    public LaskutTaulukkoJTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
        super(dm, cm, sm);
    }

    public LaskutTaulukkoJTable(int numRows, int numColumns) {
        super(numRows, numColumns);
    }

    public LaskutTaulukkoJTable(Vector rowData, Vector columnNames) {
        super(rowData, columnNames);
    }

    public LaskutTaulukkoJTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column == 5 || column == 6) {
            return GregorianCalendar.class;
        }
        return super.getColumnClass(column);
    }
}
