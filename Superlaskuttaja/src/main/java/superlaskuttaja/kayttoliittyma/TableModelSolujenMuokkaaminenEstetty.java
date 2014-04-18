/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superlaskuttaja.kayttoliittyma;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Augustus58
 */
public class TableModelSolujenMuokkaaminenEstetty extends DefaultTableModel{

    public TableModelSolujenMuokkaaminenEstetty(Object[][] object, Object[] object0) {
        super(object, object0);
    }
    
    @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
}
