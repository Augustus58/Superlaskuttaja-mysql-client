/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.maksettu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LaskutPanelMVMaksettuKuuntelija implements ActionListener {

    private final DataDeliver lataaja;
    private final LaskutTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;
    private final SuoritteetTaulukko suoritteetTaulukko;

    public LaskutPanelMVMaksettuKuuntelija(DataDeliver lataaja, LaskutTaulukko taulukko, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko, SuoritteetTaulukko suoritteetTaulukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.kuuntelija = kuuntelija;
        this.lukko = lukko;
        this.suoritteetTaulukko = suoritteetTaulukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            try {
                kuuntelija.paivitaArvo();

                ResultSet rs = lataaja.getDbc().executeQuery("select onkoMaksettu\n"
                        + "from Lasku\n"
                        + "where laskunNumero = " + taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 3) + "\n"
                        + "");

                rs.first();

                if (rs.getBoolean(1)) {
                    lataaja.getDbc().executeUpdate("update Lasku\n"
                            + "set onkoMaksettu = b'0'\n"
                            + "where laskunNumero = " + taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 3) + "\n"
                            + "");
                } else {
                    lataaja.getDbc().executeUpdate("update Lasku\n"
                            + "set onkoMaksettu = b'1'\n"
                            + "where laskunNumero = " + taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 3) + "\n"
                            + "");
                }

                rs = lataaja.getDbc().executeQuery("select distinct Asiakas.nimi, Asiakas.asiakasnumero, Pankkiviivakoodi.viiteTarkisteella,\n"
                        + "Lasku.laskunNumero, Pankkiviivakoodi.laskunSumma, Lasku.paivays, Pankkiviivakoodi.erapaiva, Lasku.onkoMaksettu\n"
                        + "from Lasku, Suorite, Asiakas, Pankkiviivakoodi\n"
                        + "where Lasku.laskunNumero = Suorite.lasku\n"
                        + "and Lasku.pankkiviivakoodi = Pankkiviivakoodi.pankkiviivakoodi\n"
                        + "and Suorite.tilaaja = Asiakas.asiakasnumero\n"
                        + "and Suorite.tilaajanVersio = Asiakas.versio\n"
                        + "and laskunNumero = " + taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 3) + "\n"
                        + "limit 1\n"
                        + "");
                
                taulukko.addLaskutTaulukkoRiviKohtaan(kuuntelija.getPaivitettyArvo(), rs);
                taulukko.getModel().removeRow(kuuntelija.getPaivitettyArvo() + 1);

            } catch (Exception e) {
                LaskutPanelMVMaksettuPoikkeusIkkuna poikkeusIkkuna = new LaskutPanelMVMaksettuPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
