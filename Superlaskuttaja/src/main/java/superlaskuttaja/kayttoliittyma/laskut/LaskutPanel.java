/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import superlaskuttaja.kayttoliittyma.ComboBoxKuuntelija;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.laskut.lisaa.LaskutPanelLisaaLaskuKuuntelija;
import superlaskuttaja.kayttoliittyma.laskut.maksettu.LaskutPanelMVMaksettuKuuntelija;
import superlaskuttaja.kayttoliittyma.laskut.poista.LaskutPanelPoistaLaskuKuuntelija;
import superlaskuttaja.kayttoliittyma.laskut.viePdf.LaskutPanelVieLaskuPdfKuuntelija;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.kayttoliittyma.laskuttaja.LaskuttajaOsioJPanel;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LaskutPanel extends JPanel {

    private final DataDeliver lataaja;
    private final LaskutTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;
    private final SuoritteetTaulukko suoritteetTaulukko;
    private final AsiakkaatTaulukko asiakkaatTaulukko;
    private final LaskuttajaOsioJPanel laskuttajaOsioJPanel;

    public LaskutPanel(DataDeliver lataaja, NappulaLukko lukko, LaskutTaulukko laskutTaulukko, SuoritteetTaulukko suoritteetTaulukko, AsiakkaatTaulukko asiakkaatTaulukko, LaskuttajaOsioJPanel laskuttajaOsioJPanel) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.lataaja = lataaja;
        this.taulukko = laskutTaulukko;
        this.kuuntelija = new TaulukkoValintaKuuntelija(taulukko.getTaulukko());
        this.lukko = lukko;
        this.suoritteetTaulukko = suoritteetTaulukko;
        this.asiakkaatTaulukko = asiakkaatTaulukko;
        this.laskuttajaOsioJPanel = laskuttajaOsioJPanel;
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

//        taulukko.muodostaLaskutTaulukko();
        taulukko.getSelectionModel().addListSelectionListener(kuuntelija);

        JScrollPane scrollPane = new JScrollPane(taulukko.getTaulukko());

        keskiosa.add(scrollPane);

        return keskiosa;
    }

    private JPanel alaosa() {
        JPanel alaosa = new JPanel(new GridLayout(1, 3));

        JButton lisaaLaskuNappi = new JButton("Lisää lasku");
        lisaaLaskuNappi.addActionListener(new LaskutPanelLisaaLaskuKuuntelija(lataaja, taulukko, lukko, suoritteetTaulukko, asiakkaatTaulukko, laskuttajaOsioJPanel));
        alaosa.add(lisaaLaskuNappi);

        JButton muutaValitunMaksettuNappi = new JButton("Muuta valitun maksettu");
        muutaValitunMaksettuNappi.addActionListener(new LaskutPanelMVMaksettuKuuntelija(lataaja, taulukko, kuuntelija, lukko, suoritteetTaulukko));
        alaosa.add(muutaValitunMaksettuNappi);

        JButton vieValittuLaskuPdfNappi = new JButton("Vie valittu lasku pdf");
        vieValittuLaskuPdfNappi.addActionListener(new LaskutPanelVieLaskuPdfKuuntelija(lataaja, taulukko, kuuntelija, lukko));
        alaosa.add(vieValittuLaskuPdfNappi);

        JButton poistaValittuLasku = new JButton("Poista valittu lasku");
        poistaValittuLasku.addActionListener(new LaskutPanelPoistaLaskuKuuntelija(lataaja, taulukko, kuuntelija, lukko, suoritteetTaulukko));
        alaosa.add(poistaValittuLasku);

        return alaosa;
    }
}
