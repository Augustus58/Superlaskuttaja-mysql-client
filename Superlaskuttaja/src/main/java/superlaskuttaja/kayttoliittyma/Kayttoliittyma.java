/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma;

import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetPanel;
import superlaskuttaja.kayttoliittyma.laskuttaja.LaskuttajaPanel;
import superlaskuttaja.kayttoliittyma.laskut.LaskutPanel;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatPanel;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.kayttoliittyma.laskuttaja.LaskuttajaOsioJPanel;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;

    public Kayttoliittyma() {
    }

    @Override
    public void run() {
        frame = new JFrame("Superlaskuttaja");
        frame.setPreferredSize(new Dimension(1200, 750));

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        DataDeliver lataaja = new DataDeliver();

        NappulaLukko lukko = new NappulaLukko();

        boolean laskuttajaLisatty = false;

        if (lataaja.getDbc().isYhdistetty()) {
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
                    laskuttajaLisatty = true;
                }
            } catch (SQLException sQLException) {
                System.out.println(sQLException.getMessage());
                System.out.println(sQLException.getSQLState());
            }
        }

        LaskuttajaOsioJPanel laskuttajaOsioJPanel = new LaskuttajaOsioJPanel(laskuttajaLisatty, lataaja, lukko);

        AsiakkaatTaulukko asiakkaatTaulukko = new AsiakkaatTaulukko(lataaja);
        SuoritteetTaulukko suoritteetTaulukko = new SuoritteetTaulukko(lataaja);
        LaskutTaulukko laskutTaulukko = new LaskutTaulukko(lataaja);

        luoKomponentit(frame.getContentPane(), lataaja, lukko, laskutTaulukko, suoritteetTaulukko, asiakkaatTaulukko, laskuttajaOsioJPanel);

        frame.pack();

        Dimension frameSize = frame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x, y);

        frame.setVisible(true);

    }

    private void luoKomponentit(Container container, DataDeliver lataaja, NappulaLukko lukko, LaskutTaulukko laskutTaulukko, SuoritteetTaulukko suoritteetTaulukko, AsiakkaatTaulukko asiakkaatTaulukko, LaskuttajaOsioJPanel laskuttajaOsioJPanel) {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel LaskuttajaPanel = new LaskuttajaPanel(lataaja, lukko, laskuttajaOsioJPanel, tabbedPane, asiakkaatTaulukko, suoritteetTaulukko, laskutTaulukko);
        tabbedPane.addTab("Laskuttaja", null, LaskuttajaPanel, null);

        JPanel asiakkaatPanel = new AsiakkaatPanel(lataaja, lukko, asiakkaatTaulukko);
        tabbedPane.addTab("Asiakkaat", null, asiakkaatPanel, null);

        JPanel suoritteetPanel = new SuoritteetPanel(lataaja, lukko, suoritteetTaulukko);
        tabbedPane.addTab("Suoritteet", null, suoritteetPanel, null);

        JPanel laskutPanel = new LaskutPanel(lataaja, lukko, laskutTaulukko, suoritteetTaulukko, asiakkaatTaulukko, laskuttajaOsioJPanel);
        tabbedPane.addTab("Laskut", null, laskutPanel, null);

        if (!lataaja.getDbc().isYhdistetty()) {
            tabbedPane.setEnabledAt(1, false);
            tabbedPane.setEnabledAt(2, false);
            tabbedPane.setEnabledAt(3, false);
        }
        container.add(tabbedPane);
    }

    public JFrame getFrame() {
        return frame;
    }
}
