/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskuttaja;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.kayttoliittyma.laskuttaja.asetaTietokanta.LaskuttajaPanelAsetaTietokantaKuuntelija;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LaskuttajaPanel extends JPanel {

    private final DataDeliver lataaja;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel laskuttajaOsioJPanel;
    private final JTabbedPane tabbedPane;
    private final AsiakkaatTaulukko asiakkaatTaulukko;
    private final SuoritteetTaulukko suoritteetTaulukko;
    private final LaskutTaulukko laskutTaulukko;

    public LaskuttajaPanel(DataDeliver lataaja, NappulaLukko lukko, LaskuttajaOsioJPanel laskuttajaOsioJPanel, JTabbedPane tabbedPane, AsiakkaatTaulukko asiakkaatTaulukko, SuoritteetTaulukko suoritteetTaulukko, LaskutTaulukko laskutTaulukko) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.lataaja = lataaja;
        this.lukko = lukko;
        this.laskuttajaOsioJPanel = laskuttajaOsioJPanel;
        this.tabbedPane = tabbedPane;
        this.asiakkaatTaulukko = asiakkaatTaulukko;
        this.suoritteetTaulukko = suoritteetTaulukko;
        this.laskutTaulukko = laskutTaulukko;
        luoKomponentit();

    }

    private void luoKomponentit() {
        add(ylaosa());
        add(keskiosa());
        add(alaosa());

    }

    private JPanel ylaosa() {
        JPanel ylaosa = new JPanel(new GridLayout(1, 3));
        ylaosa.setMinimumSize(new Dimension(50, 35));

        return ylaosa;

    }

    private JPanel keskiosa() {
        JPanel keskiosa = new JPanel(new GridLayout(0, 1));
        keskiosa.setPreferredSize(new Dimension(5000, 5000));        

        laskuttajaOsioJPanel.paivitaSisalto();
        keskiosa.add(laskuttajaOsioJPanel);
        
        return keskiosa;

    }

    private JPanel alaosa() {
        
        JPanel alaosa = new JPanel();
        
        BoxLayout layout = new BoxLayout(alaosa, BoxLayout.Y_AXIS);
        alaosa.setLayout(layout);
        
        JButton asetaTietokantaNappi = new JButton("Aseta tietokanta");
        LaskuttajaPanelAsetaTietokantaKuuntelija kuuntelija = new LaskuttajaPanelAsetaTietokantaKuuntelija(lataaja, lukko, laskuttajaOsioJPanel, tabbedPane, asiakkaatTaulukko, suoritteetTaulukko, laskutTaulukko);
        asetaTietokantaNappi.addActionListener(kuuntelija);
        asetaTietokantaNappi.setAlignmentX(Component.CENTER_ALIGNMENT);
        asetaTietokantaNappi.setToolTipText("Asettaa tietokannan, johon laskuihin liittyvät tiedot tallennetaan.");
        alaosa.add(asetaTietokantaNappi);

        return alaosa;
    }
}
