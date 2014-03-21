/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Augustus58
 */
public class LaskuttajatPanel extends JPanel {
    
    public LaskuttajatPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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

        JButton lisaaLaskuttajaNappi = new JButton("Lisää laskuttaja");
        alaosa.add(lisaaLaskuttajaNappi);

        JButton muokkaaValittuaLaskuttajaa = new JButton("Muokkaa valittua laskuttajaa");
        alaosa.add(muokkaaValittuaLaskuttajaa);

        JButton poistaValittuLaskuttajaNappi = new JButton("Poista valittu laskuttaja");
        alaosa.add(poistaValittuLaskuttajaNappi);

        return alaosa;

    }

}