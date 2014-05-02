/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.lisaaValitusta;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
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
import superlaskuttaja.kayttoliittyma.IkkunaKuuntelija;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LisaaSuoriteValitustaIkkuna implements Runnable {

    private JFrame frame;
    private final Lataaja lataaja;
    private final SuoritteetTaulukko taulukko;
    private final NappulaLukko lukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final SimpleDateFormat dateFormat;

    public LisaaSuoriteValitustaIkkuna(Lataaja lataaja, SuoritteetTaulukko taulukko, NappulaLukko lukko, TaulukkoValintaKuuntelija kuuntelija) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.lukko = lukko;
        this.kuuntelija = kuuntelija;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }

    @Override
    public void run() {
        lukko.lukitse();

        frame = new JFrame("Lisää suorite");
        frame.setLocation(130, 90);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        IkkunaKuuntelija ikkunaKuuntelija = new IkkunaKuuntelija(lukko);
        frame.addWindowListener(ikkunaKuuntelija);

        try {
            luoKomponentit(frame.getContentPane());
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            lukko.avaa();
            SuoritteetPanelLisaaSuoriteValitustaPoikkeusIkkuna poikkeusIkkuna = new SuoritteetPanelLisaaSuoriteValitustaPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }
    }

    private void luoKomponentit(Container container) {
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel tiedotPanel = new JPanel();
        GridLayout tiedotLayout = new GridLayout(0, 2);
        tiedotLayout.setVgap(10);
        tiedotLayout.setHgap(10);
        tiedotPanel.setLayout(tiedotLayout);

        JLabel asiakasTeksti = new JLabel("Valitse asiakas:");

        String[] vaihtoehdotString = lataaja.getLadattuTietovarasto().asiakkaidenNimetArrayString();
        JComboBox asiakasComboBox = new JComboBox(vaihtoehdotString);

        // Seuraavan toimiminen edellyttää, että taulukon malli ja tietovaraston suoritteet ovan samassa järjestyksessä.
        asiakasComboBox.setSelectedIndex(lataaja.getLadattuTietovarasto().getAsiakkaat().indexOf(lataaja.getLadattuTietovarasto().getSuoritteet().get(kuuntelija.getPaivitettyArvo()).getAsiakas()));

        asiakasComboBox.setEditable(false);
        ComboBoxKuuntelija comboBoxkuuntelija = new ComboBoxKuuntelija();
        comboBoxkuuntelija.setValinta(lataaja.getLadattuTietovarasto().getAsiakkaat().indexOf(lataaja.getLadattuTietovarasto().getSuoritteet().get(kuuntelija.getPaivitettyArvo()).getAsiakas()));
        asiakasComboBox.addActionListener(comboBoxkuuntelija);

        JLabel kuvausTeksti = new JLabel("Kuvaus:");
        JTextField kuvausKentta = new JTextField(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 1));

        JLabel pvmTeksti = new JLabel("Päivämäärä muodossa pp.kk.vvvv:");
        JTextField pvmKentta = new JTextField(dateFormat.format(((GregorianCalendar)(taulukko.value(kuuntelija.getPaivitettyArvo(), 2))).getTime()));

        JLabel maaraTeksti = new JLabel("Määrä:");
        JTextField maaraKentta = new JTextField(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 3));

        JLabel maaranYksikotTeksti = new JLabel("Yksiköt:");
        JTextField maaranYksikotKentta = new JTextField(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 4));

        JLabel aHintaTeksti = new JLabel("à hinta:");
        JTextField aHintaKentta = new JTextField(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 5));

        JLabel alvProsTeksti = new JLabel("Alv prosentti muodossa nn:");
        JTextField alvProsKentta = new JTextField(taulukko.valueString(kuuntelija.getPaivitettyArvo(), 6));

        JButton lisaa = new JButton("Lisää");
        lisaa.setAlignmentX(Component.CENTER_ALIGNMENT);
        LisaaSuoriteValitustaIkkunaLisaaKuuntelija lisaaKuuntelija = new LisaaSuoriteValitustaIkkunaLisaaKuuntelija(comboBoxkuuntelija, kuvausKentta, pvmKentta, maaraKentta, maaranYksikotKentta, aHintaKentta, alvProsKentta, lataaja, taulukko, frame, lukko);
        lisaa.addActionListener(lisaaKuuntelija);

        tiedotPanel.add(asiakasTeksti);
        tiedotPanel.add(asiakasComboBox);
        tiedotPanel.add(kuvausTeksti);
        tiedotPanel.add(kuvausKentta);
        tiedotPanel.add(pvmTeksti);
        tiedotPanel.add(pvmKentta);
        tiedotPanel.add(maaraTeksti);
        tiedotPanel.add(maaraKentta);
        tiedotPanel.add(maaranYksikotTeksti);
        tiedotPanel.add(maaranYksikotKentta);
        tiedotPanel.add(aHintaTeksti);
        tiedotPanel.add(aHintaKentta);
        tiedotPanel.add(alvProsTeksti);
        tiedotPanel.add(alvProsKentta);

        panel.add(tiedotPanel);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(lisaa);
        panel.add(Box.createRigidArea(new Dimension(600, 0)));

        container.add(panel);
    }

    public JFrame getFrame() {
        return frame;
    }
}