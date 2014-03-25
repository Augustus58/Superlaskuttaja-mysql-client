/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.yhteenveto;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class YhteenvetoPanel extends JPanel {

    private final Lataaja lataaja;
    private final NappulaLukko lukko;

    public YhteenvetoPanel(Lataaja lataaja) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.lataaja = lataaja;
        this.lukko = new NappulaLukko();
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

        LaskuttajaOsioJPanel frame = new LaskuttajaOsioJPanel(lataaja.getLadattuTietovarasto().isLaskuttajaLisatty(), lataaja, lukko);
        frame.paivitaSisalto();
        keskiosa.add(frame);
        
        return keskiosa;

    }

    private JPanel alaosa() {
        JPanel alaosa = new JPanel(new GridLayout(1, 0));
        
        JButton uusiNappi = new JButton("Uusi");
//        uusiNappi.setToolTipText("");
        alaosa.add(uusiNappi);
        
        JButton avaaNappi = new JButton("Avaa");
//        avaaNappi.setToolTipText("");
        alaosa.add(avaaNappi);
        
        JButton tallennaNappi = new JButton("Tallenna");
        tallennaNappi.setToolTipText("Tallenna laskuttaja, asiakkaat ja laskut tiedostoon");
        alaosa.add(tallennaNappi);

        JButton tallennaNimellaNappi = new JButton("Tallenna nimellä");
        tallennaNimellaNappi.setToolTipText("Tallenna laskuttaja, asiakkaat ja laskut tiedostoon");
        alaosa.add(tallennaNimellaNappi);

        return alaosa;

    }

}
