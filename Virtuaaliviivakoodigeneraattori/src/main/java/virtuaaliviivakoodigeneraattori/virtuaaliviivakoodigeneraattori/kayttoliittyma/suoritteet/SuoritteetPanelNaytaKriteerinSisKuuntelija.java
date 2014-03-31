/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet;

import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.ComboBoxKuuntelija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.TableModelSolujenMuokkaaminenEstetty;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class SuoritteetPanelNaytaKriteerinSisKuuntelija implements ActionListener {

    private final Lataaja lataaja;
    private final SuoritteetTaulukko taulukko;
    private final ComboBoxKuuntelija kuuntelija;
    private final JTextField kriteeriTekstikentta;

    public SuoritteetPanelNaytaKriteerinSisKuuntelija(Lataaja lataaja, SuoritteetTaulukko taulukko, ComboBoxKuuntelija kuuntelija, JTextField kriteeriTekstikentta) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.kuuntelija = kuuntelija;
        this.kriteeriTekstikentta = kriteeriTekstikentta;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        RowFilter< TableModelSolujenMuokkaaminenEstetty, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(kriteeriTekstikentta.getText(), kuuntelija.getValinta());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        taulukko.getSorter().setRowFilter(rf);
    }
}
