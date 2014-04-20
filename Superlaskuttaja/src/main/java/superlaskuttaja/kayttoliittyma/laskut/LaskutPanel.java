/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut;

import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.ComboBoxKuuntelija;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.laskut.lisaa.LaskutPanelLisaaLaskuKuuntelija;
import superlaskuttaja.kayttoliittyma.laskut.muokkaa.LaskutPanelMuokkaaLaskuaKuuntelija;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LaskutPanel extends JPanel {

    private final Lataaja lataaja;
    private final LaskutTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public LaskutPanel(Lataaja lataaja, NappulaLukko lukko) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.lataaja = lataaja;
        this.taulukko = new LaskutTaulukko(lataaja);
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

        String[] vaihtoehdotString = {"Asiakas", "Asiakasnumero", "Viite", "Laskun numero", "Summa", "Päiväys", "Eräpäivä", "Maksettu"};
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
        naytaKriteerinSisaltavatNappi.setToolTipText("Näyttää kriteeritekstin sisältävät laskut kaikkien laskujen joukosta");
        LaskutPanelNaytaKriteerinSisKuuntelija naytaKriteeriKuuntelija = new LaskutPanelNaytaKriteerinSisKuuntelija(lataaja, taulukko, comboBoxkuuntelija, kriteeriTekstikentta);
        naytaKriteerinSisaltavatNappi.addActionListener(naytaKriteeriKuuntelija);
        ylaosa.add(naytaKriteerinSisaltavatNappi);
        
        JButton naytaKaikkiNappi = new JButton("Näytä kaikki laskut");
        LaskutPanelNaytaKaikkiKuuntelija nautaKaikkiNappiKuuntelija = new LaskutPanelNaytaKaikkiKuuntelija(lataaja, taulukko);
        naytaKaikkiNappi.addActionListener(nautaKaikkiNappiKuuntelija);
        ylaosa.add(naytaKaikkiNappi);

        return ylaosa;

    }

    private JPanel keskiosa() {
        JPanel keskiosa = new JPanel();

        keskiosa.setPreferredSize(new Dimension(5000, 5000));
        keskiosa.setBorder(new EmptyBorder(20, 20, 20, 20));
        keskiosa.setLayout(new BoxLayout(keskiosa, BoxLayout.Y_AXIS));
        
        taulukko.muodostaLaskutTaulukko();
        taulukko.getSelectionModel().addListSelectionListener(kuuntelija);
        
        JScrollPane scrollPane = new JScrollPane(taulukko.getTaulukko()); 
        
        keskiosa.add(scrollPane);
        
        return keskiosa;

    }

    private JPanel alaosa() {
        JPanel alaosa = new JPanel(new GridLayout(1, 3));

        JButton lisaaLaskuNappi = new JButton("Lisää lasku");
        lisaaLaskuNappi.addActionListener(new LaskutPanelLisaaLaskuKuuntelija(lataaja, taulukko, lukko));
        alaosa.add(lisaaLaskuNappi);

        JButton muokkaaValittuaLaskuaNappi = new JButton("Muokkaa valittua laskua");
        muokkaaValittuaLaskuaNappi.addActionListener(new LaskutPanelMuokkaaLaskuaKuuntelija(lataaja, taulukko, kuuntelija, lukko));
        alaosa.add(muokkaaValittuaLaskuaNappi);
        
        JButton vieValittuLaskuPdfNappi = new JButton("Vie valittu lasku pdf");
//        vieValittuLaskuPdfNappi.addActionListener(new AsiakkaatPanelMuokkaaAsiakastaKuuntelija(lataaja, taulukko, kuuntelija, lukko));
        alaosa.add(vieValittuLaskuPdfNappi);

        JButton poistaValittuAsiakas = new JButton("Poista valittu lasku");
//        poistaValittuAsiakas.addActionListener(new AsiakkaatPanelPoistaAsiakasKuuntelija(lataaja, taulukko, kuuntelija, lukko));
        alaosa.add(poistaValittuAsiakas);

        return alaosa;

    }

}
