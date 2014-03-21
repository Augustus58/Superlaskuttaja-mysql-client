/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanel extends JPanel {
    
    Lataaja lataaja;
    
    public AsiakkaatPanel(Lataaja lataaja) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.lataaja = lataaja;
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

        JTextField kriteeriTekstikentta = new JTextField("Syötä hakuteksti tähän");
        ylaosa.add(kriteeriTekstikentta);

        JButton etsiNappi = new JButton("Etsi");
        ylaosa.add(etsiNappi);

        return ylaosa;

    }

    private JPanel keskiosa() {
        JPanel keskiosa = new JPanel();

        keskiosa.setPreferredSize(new Dimension(5000, 5000));

        String[] sarakkeidenNimet = {"Asiakkaan nimi", "Viite", "Summa", "Laskun numero"};
        Object[][] data = {
            {"Kathy", "555", "666", "234"},
            {"Kathy", "555", "666", "234"}
        };
        JTable taulukko = new JTable(data, sarakkeidenNimet);
        keskiosa.add(taulukko);

        return keskiosa;

    }

    private JPanel alaosa() {
        JPanel alaosa = new JPanel(new GridLayout(1, 3));

        JButton lisaaAsiakasNappi = new JButton("Lisää asiakas");
        lisaaAsiakasNappi.addActionListener(new AsiakkaatPanelLisaaAsiakasKuuntelija(lataaja));
        alaosa.add(lisaaAsiakasNappi);

        JButton muokkaaValittuaAsiakasta = new JButton("Muokkaa valittua asiasta");
        alaosa.add(muokkaaValittuaAsiakasta);

        JButton poistaValittuAsiakas = new JButton("Poista valittu asiakas");
        alaosa.add(poistaValittuAsiakas);

        return alaosa;

    }

}
