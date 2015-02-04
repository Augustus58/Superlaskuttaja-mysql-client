/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskuttaja.asetaTietokanta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.kayttoliittyma.laskuttaja.LaskuttajaOsioJPanel;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class AsetaTietokantaAsetaKuuntelija implements ActionListener {

    private final JTextField osoiteKentta;
    private final JTextField kayttajatunnusKentta;
    private final JPasswordField salasanaKentta;
    private final JCheckBox muistaAsetus;
    private final DataDeliver lataaja;
    private final JFrame frame;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel panel;
    private final JTabbedPane tabbedPane;
    private final AsiakkaatTaulukko asiakkaatTaulukko;
    private final SuoritteetTaulukko suoritteetTaulukko;
    private final LaskutTaulukko laskutTaulukko;

    public AsetaTietokantaAsetaKuuntelija(JTextField osoiteKentta, JTextField kayttajatunnusKentta, JPasswordField salasanaKentta, JCheckBox muistaAsetus, DataDeliver lataaja, JFrame frame, NappulaLukko lukko, LaskuttajaOsioJPanel panel, JTabbedPane tabbedPane, AsiakkaatTaulukko asiakkaatTaulukko, SuoritteetTaulukko suoritteetTaulukko, LaskutTaulukko laskutTaulukko) {
        this.osoiteKentta = osoiteKentta;
        this.kayttajatunnusKentta = kayttajatunnusKentta;
        this.salasanaKentta = salasanaKentta;
        this.muistaAsetus = muistaAsetus;
        this.lataaja = lataaja;
        this.frame = frame;
        this.lukko = lukko;
        this.panel = panel;
        this.tabbedPane = tabbedPane;
        this.asiakkaatTaulukko = asiakkaatTaulukko;
        this.suoritteetTaulukko = suoritteetTaulukko;
        this.laskutTaulukko = laskutTaulukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            lataaja.getDbc().setAddr(osoiteKentta.getText());
            lataaja.getDbc().setUser(kayttajatunnusKentta.getText());
            lataaja.getDbc().setPass(new String(salasanaKentta.getPassword()));
            if (!lataaja.getDbc().connect()) {
                throw new IllegalArgumentException("Jokin syöte on virheellinen tai tietokantaan ei saada yhteyttä");
            } else {
                if (muistaAsetus.isSelected()) {
                    lataaja.getDbc().saveConnData();
                }
                ResultSet rs = lataaja.getDbc().executeQuery("select count(yrityksenNimi) as lkm\n"
                        + "from Laskuttaja\n"
                        + "");
                try {
                    rs.first();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getSQLState());
                }
                try {
                    if (rs.getInt("lkm") > 0) {
                        panel.setLaskuttajaOlemassa(true);
                    }
                } catch (SQLException sQLException) {
                    System.out.println(sQLException.getMessage());
                    System.out.println(sQLException.getSQLState());
                }
                panel.paivitaSisalto();
                tabbedPane.setEnabledAt(1, true);
                tabbedPane.setEnabledAt(2, true);
                tabbedPane.setEnabledAt(3, true);
                asiakkaatTaulukko.muodostaAsiakkaatTaulukko();
                suoritteetTaulukko.muodostaSuoritteetTaulukko();
                laskutTaulukko.muodostaLaskutTaulukko();
                suljeIkkuna();
            }
        } catch (Exception e) {
            AsetaTietokantaAsetaPoikkeusIkkuna poikkeusIkkuna = new AsetaTietokantaAsetaPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }
    }

    private void suljeIkkuna() {
        lukko.avaa();
        frame.dispose();
    }
}
