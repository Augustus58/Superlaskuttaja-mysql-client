/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.asiakkaat;

import superlaskuttaja.kayttoliittyma.asiakkaat.poista.AsiakkaatPanelPoistaAsiakasKuuntelija;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.ComboBoxKuuntelija;
import superlaskuttaja.kayttoliittyma.asiakkaat.muokkaa.AsiakkaatPanelMuokkaaAsiakastaKuuntelija;
import superlaskuttaja.kayttoliittyma.asiakkaat.lisaa.AsiakkaatPanelLisaaAsiakasKuuntelija;
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
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanel extends JPanel {

    private final DataDeliver lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public AsiakkaatPanel(DataDeliver lataaja, NappulaLukko lukko, AsiakkaatTaulukko asiakkaatTaulukko) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.lataaja = lataaja;
        this.taulukko = asiakkaatTaulukko;
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

        String[] vaihtoehdotString = {"Nimi", "Katuosoite", "Postinumero", "Kaupunki", "Sähköposti", "Asiakasnumero", "Laskuja lähetetty"};
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
        naytaKriteerinSisaltavatNappi.setToolTipText("Näyttää kriteeritekstin sisältävät asiakkaat kaikkien asiakkaiden joukosta");
        AsiakkaatPanelNaytaKriteerinSisKuuntelija naytaKriteeriKuuntelija = new AsiakkaatPanelNaytaKriteerinSisKuuntelija(lataaja, taulukko, comboBoxkuuntelija, kriteeriTekstikentta);
        naytaKriteerinSisaltavatNappi.addActionListener(naytaKriteeriKuuntelija);
        ylaosa.add(naytaKriteerinSisaltavatNappi);
        
        JButton naytaKaikkiNappi = new JButton("Näytä kaikki asiakkaat");
        AsiakkaatPanelNaytaKaikkiKuuntelija nautaKaikkiNappiKuuntelija = new AsiakkaatPanelNaytaKaikkiKuuntelija(lataaja, taulukko);
        naytaKaikkiNappi.addActionListener(nautaKaikkiNappiKuuntelija);
        ylaosa.add(naytaKaikkiNappi);

        return ylaosa;

    }

    private JPanel keskiosa() {
        JPanel keskiosa = new JPanel();

        keskiosa.setPreferredSize(new Dimension(5000, 5000));
        keskiosa.setBorder(new EmptyBorder(20, 20, 20, 20));
        keskiosa.setLayout(new BoxLayout(keskiosa, BoxLayout.Y_AXIS));
        
        taulukko.getSelectionModel().addListSelectionListener(kuuntelija);
        
        JScrollPane scrollPane = new JScrollPane(taulukko.getTaulukko()); 
        
        keskiosa.add(scrollPane);
        
        return keskiosa;

    }

    private JPanel alaosa() {
        JPanel alaosa = new JPanel(new GridLayout(1, 3));

        JButton lisaaAsiakasNappi = new JButton("Lisää asiakas");
        lisaaAsiakasNappi.addActionListener(new AsiakkaatPanelLisaaAsiakasKuuntelija(lataaja, taulukko, lukko));
        alaosa.add(lisaaAsiakasNappi);

        JButton muokkaaValittuaAsiakastaNappi = new JButton("Muokkaa valittua asiakasta");
        muokkaaValittuaAsiakastaNappi.addActionListener(new AsiakkaatPanelMuokkaaAsiakastaKuuntelija(lataaja, taulukko, kuuntelija, lukko));
        alaosa.add(muokkaaValittuaAsiakastaNappi);

        JButton poistaValittuAsiakas = new JButton("Poista valittu asiakas");
        poistaValittuAsiakas.addActionListener(new AsiakkaatPanelPoistaAsiakasKuuntelija(lataaja, taulukko, kuuntelija, lukko));
        alaosa.add(poistaValittuAsiakas);

        return alaosa;

    }

}
