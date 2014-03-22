/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma;

import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.AsiakkaatPanel;
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
        
        JPanel yhteenvetoPanel = new YhteenvetoPanel();
        tabbedPane.addTab("Yhteenveto", null, yhteenvetoPanel,
                  "Näyttää yhteenvedon");
        
        JPanel laskutPanel = new LaskutPanel();
        tabbedPane.addTab("Laskut", null, laskutPanel,
                  "Näyttää laskut");
        
        JPanel asiakkaatPanel = new AsiakkaatPanel(lataaja);
        tabbedPane.addTab("Asiakkaat", null, asiakkaatPanel,
                  "Näyttää asiakkaat");
        
        JPanel laskuttajatPanel = new LaskuttajatPanel();
        tabbedPane.addTab("Laskuttajat", null, laskuttajatPanel,
                  "Näyttää laskuttajat");
        
        container.add(tabbedPane);
    }

    public JFrame getFrame() {
        return frame;
    }

}
