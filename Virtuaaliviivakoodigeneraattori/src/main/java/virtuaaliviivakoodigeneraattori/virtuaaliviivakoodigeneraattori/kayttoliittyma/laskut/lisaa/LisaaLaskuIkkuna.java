/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.laskut.lisaa;

import java.awt.Component;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.IkkunaKuuntelija;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.laskut.LaskutTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LisaaLaskuIkkuna implements Runnable {

    private JFrame frame;
    private final Lataaja lataaja;
    private final LaskutTaulukko taulukko;
    private final NappulaLukko lukko;
    private final DateFormat pvmFormaatti;

    public LisaaLaskuIkkuna(Lataaja lataaja, LaskutTaulukko taulukko, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.lukko = lukko;
        this.pvmFormaatti = new SimpleDateFormat("dd.MM.yyyy");
    }

    @Override
    public void run() {
        lukko.lukitse();

        frame = new JFrame("Lisää lasku");
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
        LisaaLaskutIkkunaComboBoxKuuntelija comboBoxkuuntelija = new LisaaLaskutIkkunaComboBoxKuuntelija();
        asiakas.addActionListener(comboBoxkuuntelija);

        JLabel suoritteetTeksti = new JLabel("Valitse asiakkaan laskuttamattomat suoritteet:");
        LisaaLaskuIkkunaSuoritteetList suoritteetLista = new LisaaLaskuIkkunaSuoritteetList(comboBoxkuuntelija, lataaja);
        comboBoxkuuntelija.setSuoritteetList(suoritteetLista);
        JScrollPane suoritteetPane = new JScrollPane(suoritteetLista);
        suoritteetLista.paivitaListanSisalto();

        JLabel paivaysTeksti = new JLabel("Päiväys muodossa pp.kk.vvvv:");
        Date paivamaaraTanaan = new Date();
        JTextField paivaysKentta = new JTextField(pvmFormaatti.format(paivamaaraTanaan));

        JLabel maksuaikaTeksti = new JLabel("Maksuaika päivissä:");
        JTextField maksuaikaKentta = new JTextField("14");

        JLabel erapaivaTeksti = new JLabel("Eräpäivä:");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, Integer.parseInt(maksuaikaKentta.getText()));
        JTextField erapaivaKentta = new JTextField(pvmFormaatti.format(c.getTime()));
//
//        erapaivaKentta.setEditable(false);

        JButton lisaa = new JButton("Lisää");
        lisaa.setAlignmentX(Component.CENTER_ALIGNMENT);
//        LisaaLaskuIkkunaLisaaKuuntelija kuuntelija = new LisaaLaskuIkkunaLisaaKuuntelija(comboBoxkuuntelija, kuvausKentta, pvmKentta, maaraKentta, maaranYksikotKentta, aHintaKentta, alvProsKentta, lataaja, taulukko, frame, lukko);
//        lisaa.addActionListener(kuuntelija);

        tiedotPanel.add(asiakasTeksti);
        tiedotPanel.add(asiakas);
        tiedotPanel.add(suoritteetTeksti);
        tiedotPanel.add(suoritteetPane);
        tiedotPanel.add(paivaysTeksti);
        tiedotPanel.add(paivaysKentta);
        tiedotPanel.add(maksuaikaTeksti);
        tiedotPanel.add(maksuaikaKentta);
        tiedotPanel.add(erapaivaTeksti);
        tiedotPanel.add(erapaivaKentta);
        
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
