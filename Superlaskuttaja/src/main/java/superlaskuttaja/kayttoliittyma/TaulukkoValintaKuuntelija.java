/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Augustus58
 */
public class TaulukkoValintaKuuntelija implements ListSelectionListener {

    private Integer arvo;
    private Integer paivitettyArvo;
    private final JTable taulukko;

    public TaulukkoValintaKuuntelija(JTable taulukko) {
        this.taulukko = taulukko;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        arvo = taulukko.getSelectedRow();

    }

    public void paivitaArvo() {
        paivitettyArvo = taulukko.convertRowIndexToModel(arvo);
    }

    public Integer getPaivitettyArvo() {
        return (paivitettyArvo);
    }

}
