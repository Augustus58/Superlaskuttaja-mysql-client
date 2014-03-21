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
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Augustus58
 */
public class LaskutPanel extends JPanel {
    
    public LaskutPanel() {
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
        ylaosa.setMinimumSize(new Dimension(50, 32));
        
        String[] vaihtoehdotString = {"Asiakkaan nimi", "Viite", "Laskun numero"};
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
        JPanel alaosa = new JPanel(new GridLayout(1, 4));
        
        JButton lisaaLaskuNappi = new JButton("Lisää lasku");
        alaosa.add(lisaaLaskuNappi);
        
        JButton muokkaaValittuaLaskuaNappi = new JButton("Muokkaa valittua laskua");
        alaosa.add(muokkaaValittuaLaskuaNappi);
        
        JButton vieValittuLaskuPdfNappi = new JButton("Vie valittu lasku pdf");
        alaosa.add(vieValittuLaskuPdfNappi);
        
        JButton poistaValittuLaskuNappi = new JButton("Poista valittu lasku");
        alaosa.add(poistaValittuLaskuNappi);
        
        return alaosa;
        
    }
    
}
