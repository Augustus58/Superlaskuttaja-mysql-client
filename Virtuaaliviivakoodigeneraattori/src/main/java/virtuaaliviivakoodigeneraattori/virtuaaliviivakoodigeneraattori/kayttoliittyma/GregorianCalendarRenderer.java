/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Augustus58
 */
public class GregorianCalendarRenderer extends DefaultTableCellRenderer {

    DateFormat formatter;

    public GregorianCalendarRenderer() {
        super();
        this.formatter = new SimpleDateFormat("dd.MM.yyyy");
    }

    @Override
    public void setValue(Object value) {
        GregorianCalendar calendar = (GregorianCalendar) value;
        setText((value == null) ? "" : formatter.format(calendar.getTime()));
    }
}
