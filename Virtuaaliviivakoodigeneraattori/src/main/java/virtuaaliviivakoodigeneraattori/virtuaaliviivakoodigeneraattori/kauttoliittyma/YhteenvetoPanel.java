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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Augustus58
 */
public class YhteenvetoPanel extends JPanel {

    public YhteenvetoPanel() {
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
        JPanel keskiosa = new JPanel(new GridLayout(0, 1));
        keskiosa.setPreferredSize(new Dimension(5000, 5000));

        JLabel laskujenLkmTeksti = new JLabel("Laskujen lkm");
        keskiosa.add(laskujenLkmTeksti);
        JLabel asiakkaidenLkmTeksti = new JLabel("Asiakkaiden lkm");
        keskiosa.add(asiakkaidenLkmTeksti);

        return keskiosa;

    }

    private JPanel alaosa() {
        JPanel alaosa = new JPanel(new GridLayout(1, 2));

        JButton tallennaNappi = new JButton("Tallenna");
        alaosa.add(tallennaNappi);

        JButton tallennaNimellaNappi = new JButton("Tallenna nimellä");
        alaosa.add(tallennaNimellaNappi);

        return alaosa;

    }

}
