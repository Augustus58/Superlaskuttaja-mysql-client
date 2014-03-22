/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat;

import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.muokkaa.AsiakkaatPanelMuokkaaAsiakastaKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.lisaa.AsiakkaatPanelLisaaAsiakasKuuntelija;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanel extends JPanel {

    Lataaja lataaja;
    AsiakkaatTaulukko taulukko;

    public AsiakkaatPanel(Lataaja lataaja) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.lataaja = lataaja;
        this.taulukko = new AsiakkaatTaulukko(lataaja);
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

        String[] vaihtoehdotString = {"Asiakkaan nimi", "Asiakasnumero"};
        JComboBox kriteerit = new JComboBox(vaihtoehdotString);
        kriteerit.setSelectedIndex(0);
        ylaosa.add(kriteerit);

        JTextField kriteeriTekstikentta = new JTextField("Syötä kriteeriteksti tähän");
        ylaosa.add(kriteeriTekstikentta);

        JButton naytaKriteerinSisaltavatNappi = new JButton("Näytä kriteeritekstin sisältävät");
        ylaosa.add(naytaKriteerinSisaltavatNappi);
        
        JButton naytaKaikkiNappi = new JButton("Näytä kaikki");
        ylaosa.add(naytaKaikkiNappi);

        return ylaosa;

    }

    private JPanel keskiosa() {
        JPanel keskiosa = new JPanel();

        keskiosa.setPreferredSize(new Dimension(5000, 5000));
        keskiosa.setBorder(new EmptyBorder(20, 20, 20, 20));
        keskiosa.setLayout(new BoxLayout(keskiosa, BoxLayout.Y_AXIS));
        
        taulukko.muodostaAsiakkaatTaulukko();        
        JScrollPane scrollPane = new JScrollPane(taulukko.getTaulukko());        
        keskiosa.add(scrollPane);                
        
        return keskiosa;

    }

    private JPanel alaosa() {
        JPanel alaosa = new JPanel(new GridLayout(1, 3));

        JButton lisaaAsiakasNappi = new JButton("Lisää asiakas");
        lisaaAsiakasNappi.addActionListener(new AsiakkaatPanelLisaaAsiakasKuuntelija(lataaja, taulukko));
        alaosa.add(lisaaAsiakasNappi);

        JButton muokkaaValittuaAsiakastaNappi = new JButton("Muokkaa valittua asiasta");
        muokkaaValittuaAsiakastaNappi.addActionListener(new AsiakkaatPanelMuokkaaAsiakastaKuuntelija(lataaja, taulukko));
        alaosa.add(muokkaaValittuaAsiakastaNappi);

        JButton poistaValittuAsiakas = new JButton("Poista valittu asiakas");
        alaosa.add(poistaValittuAsiakas);

        return alaosa;

    }

}
