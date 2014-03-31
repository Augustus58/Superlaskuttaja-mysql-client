/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet;

import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet.lisaa.SuoritteetPanelLisaaSuoriteKuuntelija;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.ComboBoxKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.TaulukkoValintaKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet.lisaaValitusta.SuoritteetPanelLisaaSuoriteValitustaKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet.muokkaa.SuoritteetPanelMuokkaaValittuaKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class SuoritteetPanel extends JPanel {

    private final Lataaja lataaja;
    private final SuoritteetTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public SuoritteetPanel(Lataaja lataaja, NappulaLukko lukko) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.lataaja = lataaja;
        this.taulukko = new SuoritteetTaulukko(lataaja);
        this.kuuntelija = new TaulukkoValintaKuuntelija(taulukko.getTaulukko());
        this.lukko = lukko;
        luoKomponentit();

    }

    private void luoKomponentit() {
        add(ylaosa());
        add(keskiosa());
        add(alaosa());

    }

    private JPanel ylaosa() {
        JPanel ylaosa = new JPanel(new GridLayout(1, 3));
        ylaosa.setMinimumSize(new Dimension(50, 32));

        String[] vaihtoehdotString = {"Asiakkaan nimi", "Kuvaus", "Päivämäärä", "Määrä", "Yksikkö", "à hinta", "Alv %", "Alv €", "Yhteensä", "Laskutettu"};
        JComboBox kriteerit = new JComboBox(vaihtoehdotString);
        kriteerit.setSelectedIndex(0);
        kriteerit.setEditable(false);
        kriteerit.setToolTipText("Valitse kriteeri");      
        ComboBoxKuuntelija comboBoxkuuntelija = new ComboBoxKuuntelija();
        kriteerit.addActionListener(comboBoxkuuntelija);
        ylaosa.add(kriteerit);

        JTextField kriteeriTekstikentta = new JTextField();
        kriteeriTekstikentta.setToolTipText("Syötä kriteeriteksti tähän");        
        ylaosa.add(kriteeriTekstikentta);

        JButton naytaKriteerinSisaltavatNappi = new JButton("Näytä kriteeritekstin sisältävät");
        naytaKriteerinSisaltavatNappi.setToolTipText("Näyttää kriteeritekstin sisältävät suoritteet kaikkien suoritteiden joukosta");
        SuoritteetPanelNaytaKriteerinSisKuuntelija naytaKriteeriKuuntelija = new SuoritteetPanelNaytaKriteerinSisKuuntelija(lataaja, taulukko, comboBoxkuuntelija, kriteeriTekstikentta);
        naytaKriteerinSisaltavatNappi.addActionListener(naytaKriteeriKuuntelija);
        ylaosa.add(naytaKriteerinSisaltavatNappi);
        
        JButton naytaKaikkiNappi = new JButton("Näytä kaikki suoritteet");
        SuoritteetPanelNaytaKaikkiKuuntelija nautaKaikkiNappiKuuntelija = new SuoritteetPanelNaytaKaikkiKuuntelija(lataaja, taulukko);
        naytaKaikkiNappi.addActionListener(nautaKaikkiNappiKuuntelija);
        ylaosa.add(naytaKaikkiNappi);

        return ylaosa;

    }

    private JPanel keskiosa() {
        JPanel keskiosa = new JPanel();

        keskiosa.setPreferredSize(new Dimension(5000, 5000));
        keskiosa.setBorder(new EmptyBorder(20, 20, 20, 20));
        keskiosa.setLayout(new BoxLayout(keskiosa, BoxLayout.Y_AXIS));
        
        // Pidetään suoritetaulukko ja suoritteet tietovarastossa samassa järjestyksessä.
        // Samaa jätjestystä tarvitaan mm. luokassa LisaaSuoriteValitustaIkkuna.
        
        taulukko.muodostaSuoritteetTaulukko();         
        taulukko.getSelectionModel().addListSelectionListener(kuuntelija);
        
        JScrollPane scrollPane = new JScrollPane(taulukko.getTaulukko()); 
        
        keskiosa.add(scrollPane);
        
        return keskiosa;

    }

    private JPanel alaosa() {
        JPanel alaosa = new JPanel(new GridLayout(1, 3));

        JButton lisaaSuoriteNappi = new JButton("Lisää suorite");
        lisaaSuoriteNappi.addActionListener(new SuoritteetPanelLisaaSuoriteKuuntelija(lataaja, taulukko, lukko));
        alaosa.add(lisaaSuoriteNappi);
        
        JButton lisaaSuoriteKayttaenValittuaPohjanaNappi = new JButton("Lisää suorite valitusta");
        lisaaSuoriteKayttaenValittuaPohjanaNappi.setToolTipText("Lisää uusi suorite käyttäen valittua suoritetta pohjana");
        lisaaSuoriteKayttaenValittuaPohjanaNappi.addActionListener(new SuoritteetPanelLisaaSuoriteValitustaKuuntelija(lataaja, taulukko, lukko, kuuntelija));
        alaosa.add(lisaaSuoriteKayttaenValittuaPohjanaNappi);
        
        JButton muokkaaValittuaSuoritettaNappi = new JButton("Muokkaa valittua suoritetta");
        muokkaaValittuaSuoritettaNappi.addActionListener(new SuoritteetPanelMuokkaaValittuaKuuntelija(lataaja, taulukko, lukko, kuuntelija));
        alaosa.add(muokkaaValittuaSuoritettaNappi);

        JButton poistaValittuSuorite = new JButton("Poista valittu suorite");
        poistaValittuSuorite.addActionListener(new SuoritteetPanelPoistaSuoriteKuuntelija(lataaja, taulukko, kuuntelija, lukko));
        alaosa.add(poistaValittuSuorite);

        return alaosa;

    }

}
