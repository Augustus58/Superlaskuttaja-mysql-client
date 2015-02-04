/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.poista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class SuoritteetPanelPoistaSuoriteKuuntelija implements ActionListener {

    private final DataDeliver lataaja;
    private final SuoritteetTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public SuoritteetPanelPoistaSuoriteKuuntelija(DataDeliver lataaja, SuoritteetTaulukko taulukko, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.kuuntelija = kuuntelija;
        this.lukko = lukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            try {
                kuuntelija.paivitaArvo();
                ResultSet rs = lataaja.getDbc().executeQuery("select distinct *\n"
                        + "from Suorite\n"
                        + "where lasku is not null\n"
                        + "and suoritteenNumero = " + taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 8).toString() + "\n"
                        + "");

                if (rs.first()) {
                    throw new IllegalStateException();
                }
                String valitunSuoritteenNumero = taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 8).toString();
                lataaja.getDbc().executeUpdate("delete from Suorite\n"
                        + "where suoritteenNumero = " + valitunSuoritteenNumero + "\n"
                        + "");
                taulukko.getModel().removeRow(kuuntelija.getPaivitettyArvo());
            } catch (IllegalStateException e) {
                SuoritteetPanelPoistaSuoriteSuoriteOnLaskullaPoikkeusIkkuna poikkeusIkkuna = new SuoritteetPanelPoistaSuoriteSuoriteOnLaskullaPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            } catch (Exception e) {
                SuoritteetPanelPoistaSuoritePoikkeusIkkuna poikkeusIkkuna = new SuoritteetPanelPoistaSuoritePoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
