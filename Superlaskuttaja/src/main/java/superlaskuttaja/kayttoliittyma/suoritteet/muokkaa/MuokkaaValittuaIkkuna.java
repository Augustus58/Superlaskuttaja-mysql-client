/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.muokkaa;

import java.awt.Component;
import superlaskuttaja.kayttoliittyma.IkkunaKuuntelija;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import superlaskuttaja.kayttoliittyma.ComboBoxKuuntelija;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class MuokkaaValittuaIkkuna implements Runnable {

    private JFrame frame;
    private final Lataaja lataaja;
    private final SuoritteetTaulukko taulukko;
    private final NappulaLukko lukko;
    private final TaulukkoValintaKuuntelija kuuntelija;

    public MuokkaaValittuaIkkuna(Lataaja lataaja, SuoritteetTaulukko taulukko, NappulaLukko lukko, TaulukkoValintaKuuntelija kuuntelija) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.lukko = lukko;
        this.kuuntelija = kuuntelija;
    }

    @Override
    public void run() {
        lukko.lukitse();

        frame = new JFrame("Muokkaa suoritetta");
        frame.setLocation(130, 90);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        
        IkkunaKuuntelija ikkunaKuuntelija = new IkkunaKuuntelija(lukko);
        frame.addWindowListener(ikkunaKuuntelija);

        try {
            luoKomponentit(frame.getContentPane());
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            lukko.avaa();
            SuoritteetPanelMuokkaaValittuaPoikkeusIkkuna poikkeusIkkuna = new SuoritteetPanelMuokkaaValittuaPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }
    }

    private void luoKomponentit(Container container) {
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel tiedotPanel = new JPanel();
        GridLayout tiedotLayout = new GridLayout(0, 4);
        tiedotLayout.setVgap(10);
        tiedotLayout.setHgap(10);
        tiedotPanel.setLayout(tiedotLayout);

        JLabel vanhaAsiakasTeksti = new JLabel("Vanha asiakas:");
        JLabel vanhaAsiakasTeksti2 = new JLabel(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 0));
        JLabel asiakasTeksti = new JLabel("Uusi asiakas:");

        String[] vaihtoehdotString = lataaja.getLadattuTietovarasto().asiakkaidenNimetArrayString();
        JComboBox asiakasComboBox = new JComboBox(vaihtoehdotString);

        // Seuraavan toimiminen edellyttää, että taulukon malli ja tietovaraston suoritteet ovan samassa järjestyksessä.
        asiakasComboBox.setSelectedIndex(lataaja.getLadattuTietovarasto().getAsiakkaat().indexOf(lataaja.getLadattuTietovarasto().getSuoritteet().get(kuuntelija.getPaivitettyArvo()).getAsiakas()));
        asiakasComboBox.setEditable(false);
        ComboBoxKuuntelija comboBoxkuuntelija = new ComboBoxKuuntelija();
        asiakasComboBox.addActionListener(comboBoxkuuntelija);

        JLabel vanhaKuvausTeksti = new JLabel("Vanha kuvaus:");
        JLabel vanhaKuvausTeksti2 = new JLabel(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 1));
        JLabel kuvausTeksti = new JLabel("Uusi kuvaus:");
        JTextField kuvausKentta = new JTextField(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 1));

        JLabel vanhaPvmTeksti = new JLabel("Vanha päivämäärä:");
        JLabel vanhaPvmTeksti2 = new JLabel(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 2));
        JLabel pvmTeksti = new JLabel("Uusi päivämäärä muodossa pp.kk.vvvv:");
        JTextField pvmKentta = new JTextField(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 2));

        JLabel vanhaMaaraTeksti = new JLabel("Vanha määrä:");
        JLabel vanhaMaaraTeksti2 = new JLabel(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 3));
        JLabel maaraTeksti = new JLabel("Uusi määrä:");
        JTextField maaraKentta = new JTextField(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 3));

        JLabel vanhaMaaranYksikotTeksti = new JLabel("Vanhat yksiköt:");
        JLabel vanhaMaaranYksikotTeksti2 = new JLabel(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 4));
        JLabel maaranYksikotTeksti = new JLabel("Uudet yksiköt:");
        JTextField maaranYksikotKentta = new JTextField(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 4));

        JLabel vanhaAHintaTeksti = new JLabel("Vanha à hinta:");
        JLabel vanhaAHintaTeksti2 = new JLabel(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 5));
        JLabel aHintaTeksti = new JLabel("Uusi à hinta:");
        JTextField aHintaKentta = new JTextField(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 5));

        JLabel vanhaAlvProsTeksti = new JLabel("Vanha alv prosentti:");
        JLabel vanhaAlvProsTeksti2 = new JLabel(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 6));
        JLabel alvProsTeksti = new JLabel("Uusi alv prosentti muodossa nn:");
        JTextField alvProsKentta = new JTextField(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 6));

        JButton muokkaa = new JButton("Muokkaa");
        muokkaa.setAlignmentX(Component.CENTER_ALIGNMENT);
        MuokkaaValittuaIkkunaMuokkaaKuuntelija muokkaaKuuntelija = new MuokkaaValittuaIkkunaMuokkaaKuuntelija(kuvausKentta, pvmKentta, maaraKentta, maaranYksikotKentta, aHintaKentta, alvProsKentta, lataaja, taulukko, frame, lukko, comboBoxkuuntelija, kuuntelija);
        muokkaa.addActionListener(muokkaaKuuntelija);

        tiedotPanel.add(vanhaAsiakasTeksti);
        tiedotPanel.add(vanhaAsiakasTeksti2);
        tiedotPanel.add(asiakasTeksti);
        tiedotPanel.add(asiakasComboBox);

        tiedotPanel.add(vanhaKuvausTeksti);
        tiedotPanel.add(vanhaKuvausTeksti2);
        tiedotPanel.add(kuvausTeksti);
        tiedotPanel.add(kuvausKentta);

        tiedotPanel.add(vanhaPvmTeksti);
        tiedotPanel.add(vanhaPvmTeksti2);
        tiedotPanel.add(pvmTeksti);
        tiedotPanel.add(pvmKentta);

        tiedotPanel.add(vanhaMaaraTeksti);
        tiedotPanel.add(vanhaMaaraTeksti2);
        tiedotPanel.add(maaraTeksti);
        tiedotPanel.add(maaraKentta);

        tiedotPanel.add(vanhaMaaranYksikotTeksti);
        tiedotPanel.add(vanhaMaaranYksikotTeksti2);
        tiedotPanel.add(maaranYksikotTeksti);
        tiedotPanel.add(maaranYksikotKentta);

        tiedotPanel.add(vanhaAHintaTeksti);
        tiedotPanel.add(vanhaAHintaTeksti2);
        tiedotPanel.add(aHintaTeksti);
        tiedotPanel.add(aHintaKentta);

        tiedotPanel.add(vanhaAlvProsTeksti);
        tiedotPanel.add(vanhaAlvProsTeksti2);
        tiedotPanel.add(alvProsTeksti);
        tiedotPanel.add(alvProsKentta);

        panel.add(tiedotPanel);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(muokkaa);
        panel.add(Box.createRigidArea(new Dimension(600, 0)));

        container.add(panel);

    }

    public JFrame getFrame() {
        return frame;
    }

}
