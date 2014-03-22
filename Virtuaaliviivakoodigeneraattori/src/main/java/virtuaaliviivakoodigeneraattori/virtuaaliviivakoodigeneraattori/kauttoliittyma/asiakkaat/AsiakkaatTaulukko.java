/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Asiakas;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatTaulukko {

    private final JTable taulukko;
    private final DefaultTableModel model;
    private final ListSelectionModel selectionModel;
    AsiakkaatTaulukkoValintaKuuntelija kuuntelija;
    private final Lataaja lataaja;

    public AsiakkaatTaulukko(Lataaja lataaja) {
        this.lataaja = lataaja;
        this.taulukko = new JTable();
        this.model = new DefaultTableModel(new Object[][]{}, new Object[]{"Nimi", "Katuosoite", "Postinumero", "Kaupunki", "Asiakasnumero", "Laskuja lähetetty"});
        this.taulukko.setModel(model);
        this.selectionModel = this.taulukko.getSelectionModel();
        this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.kuuntelija = new AsiakkaatTaulukkoValintaKuuntelija(this.taulukko);
        selectionModel.addListSelectionListener(kuuntelija);
        muodostaAsiakkaatTaulukko();
    }

    public final void muodostaAsiakkaatTaulukko() {
        if (!lataaja.getLadattuTietovarasto().getAsiakkaat().isEmpty()) {
            for (int i = 0; i < lataaja.getLadattuTietovarasto().getAsiakkaat().size(); i++) {
                model.addRow(lataaja.getLadattuTietovarasto().getAsiakkaat().get(i).getAsiakkaanTiedotTaulukossa());
            }
        }
    }

    public void addAsiakkaatTaulukkoRivi(Asiakas asiakas) {
        model.addRow(asiakas.getAsiakkaanTiedotTaulukossa());
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
    
    public Integer getKuuntelijaArvo() {
        return this.kuuntelija.getArvo();
    }
    
    public String getValueString(Integer x, Integer y) {
        return(getModel().getValueAt(x, y).toString());
    }

}
