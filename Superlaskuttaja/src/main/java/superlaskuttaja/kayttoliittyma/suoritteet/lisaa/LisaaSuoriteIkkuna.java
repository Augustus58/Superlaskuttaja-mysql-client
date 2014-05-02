/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.lisaa;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import superlaskuttaja.kayttoliittyma.ComboBoxKuuntelija;
import superlaskuttaja.kayttoliittyma.IkkunaKuuntelija;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LisaaSuoriteIkkuna implements Runnable {

    private JFrame frame;
    private final Lataaja lataaja;
    private final SuoritteetTaulukko taulukko;
    private final NappulaLukko lukko;
    private final DateFormat pvmFormaatti;

    public LisaaSuoriteIkkuna(Lataaja lataaja, SuoritteetTaulukko taulukko, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.lukko = lukko;
        this.pvmFormaatti = new SimpleDateFormat("dd.MM.yyyy");
    }

    @Override
    public void run() {
        lukko.lukitse();

        frame = new JFrame("Lisää suorite");
        frame.setLocation(130, 90);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        IkkunaKuuntelija kuuntelija = new IkkunaKuuntelija(lukko);
        frame.addWindowListener(kuuntelija);

        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
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
        JComboBox asiakas = new JComboBox(vaihtoehdotString);
        asiakas.setSelectedIndex(0);
        asiakas.setEditable(false);
        ComboBoxKuuntelija comboBoxkuuntelija = new ComboBoxKuuntelija();
        asiakas.addActionListener(comboBoxkuuntelija);

        JLabel kuvausTeksti = new JLabel("Kuvaus:");
        JTextField kuvausKentta = new JTextField();

        JLabel pvmTeksti = new JLabel("Päivämäärä muodossa pp.kk.vvvv:");
        Date paivamaaraTanaan = new Date();
        JTextField pvmKentta = new JTextField(pvmFormaatti.format(paivamaaraTanaan));

        JLabel maaraTeksti = new JLabel("Määrä:");
        JTextField maaraKentta = new JTextField();

        JLabel maaranYksikotTeksti = new JLabel("Yksiköt:");
        JTextField maaranYksikotKentta = new JTextField("h");

        JLabel aHintaTeksti = new JLabel("à hinta:");
        JTextField aHintaKentta = new JTextField();

        JLabel alvProsTeksti = new JLabel("Alv prosentti muodossa nn:");
        JTextField alvProsKentta = new JTextField("24");

        JButton lisaa = new JButton("Lisää");
        lisaa.setAlignmentX(Component.CENTER_ALIGNMENT);
        LisaaSuoriteIkkunaLisaaKuuntelija kuuntelija = new LisaaSuoriteIkkunaLisaaKuuntelija(comboBoxkuuntelija, kuvausKentta, pvmKentta, maaraKentta, maaranYksikotKentta, aHintaKentta, alvProsKentta, lataaja, taulukko, frame, lukko);
        lisaa.addActionListener(kuuntelija);

        tiedotPanel.add(asiakasTeksti);
        tiedotPanel.add(asiakas);
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
