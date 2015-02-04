/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superlaskuttaja.logiikka;

/**
 * Tämä luokka on tarkoitettu ohjelman tietojen välittämiseen.
 * 
 * @author Augustus58
 */
public class DataDeliver {
    
    /**
     * Tietovarasto, jonka lataaja voi ladata käytettäväksi ohjelmassa.
     */
    private final DBConnection dbc;
    private final PdfExtractor pdfe;

    public DataDeliver() {
        this.dbc = new DBConnection();
        this.pdfe = new PdfExtractor(this);
    }

    public DBConnection getDbc() {
        return dbc;
    }

    public PdfExtractor getPdfe() {
        return pdfe;
    }
    
}
