/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma;

import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet.SuoritteetPanel;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.yhteenveto.YhteenvetoPanel;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.laskut.LaskutPanel;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.asiakkaat.AsiakkaatPanel;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

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
        frame.setPreferredSize(new Dimension(1000, 700));
        frame.setLocation(100, 60);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        Lataaja lataaja = new Lataaja();

        luoKomponentit(frame.getContentPane(), lataaja);

        frame.pack();
        frame.setVisible(true);
        
    }

    private void luoKomponentit(Container container, Lataaja lataaja) {
        JTabbedPane tabbedPane = new JTabbedPane();
        
        JPanel yhteenvetoPanel = new YhteenvetoPanel(lataaja);
        tabbedPane.addTab("Yhteenveto", null, yhteenvetoPanel, null);

        JPanel asiakkaatPanel = new AsiakkaatPanel(lataaja);
        tabbedPane.addTab("Asiakkaat", null, asiakkaatPanel, null);
        
        JPanel suoritteetPanel = new SuoritteetPanel(lataaja);
        tabbedPane.addTab("Suoritteet", null, suoritteetPanel, null);
        
        JPanel laskutPanel = new LaskutPanel(lataaja);
        tabbedPane.addTab("Laskut", null, laskutPanel, null);
        
        container.add(tabbedPane);
    }

    public JFrame getFrame() {
        return frame;
    }

}
