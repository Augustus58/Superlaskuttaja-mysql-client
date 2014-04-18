/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.lisaa;

import java.awt.Component;
import superlaskuttaja.kayttoliittyma.IkkunaKuuntelija;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.logiikka.Lataaja;

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
        tiedotPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = (new Insets(0, 0, 5, 5));
        JLabel asiakasTeksti = new JLabel("Valitse asiakas:");
        tiedotPanel.add(asiakasTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = (new Insets(0, 5, 5, 0));
        String[] vaihtoehdotString = lataaja.getLadattuTietovarasto().asiakkaidenNimetArrayString();
        JComboBox asiakas = new JComboBox(vaihtoehdotString);
        asiakas.setSelectedIndex(0);
        asiakas.setEditable(false);
        LisaaLaskutIkkunaComboBoxKuuntelija comboBoxkuuntelija = new LisaaLaskutIkkunaComboBoxKuuntelija();
        asiakas.addActionListener(comboBoxkuuntelija);
        tiedotPanel.add(asiakas, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel suoritteetTeksti = new JLabel("Valitse asiakkaan laskuttamattomat suoritteet:");
        tiedotPanel.add(suoritteetTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = (new Insets(5, 5, 5, 0));
        LisaaLaskuIkkunaSuoritteetList suoritteetLista = new LisaaLaskuIkkunaSuoritteetList(comboBoxkuuntelija, lataaja);
        comboBoxkuuntelija.setSuoritteetList(suoritteetLista);
        JScrollPane suoritteetPane = new JScrollPane(suoritteetLista);
        suoritteetLista.paivitaListanSisalto();
        tiedotPanel.add(suoritteetPane, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel paivaysTeksti = new JLabel("Päiväys muodossa pp.kk.vvvv:");
        tiedotPanel.add(paivaysTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = (new Insets(5, 5, 5, 0));
        Date paivamaaraTanaan = new Date();
        JTextField paivaysKentta = new JTextField(pvmFormaatti.format(paivamaaraTanaan), 20);
        LisaaLaskutIkkunaPaivaysKenttaKuuntelija paivaysKenttaKuuntelija = new LisaaLaskutIkkunaPaivaysKenttaKuuntelija();
        paivaysKentta.getDocument().addDocumentListener(paivaysKenttaKuuntelija);
        paivaysKenttaKuuntelija.setPaivaysKentta(paivaysKentta);
        tiedotPanel.add(paivaysKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel maksuaikaTeksti = new JLabel("Maksuaika päivissä:");
        tiedotPanel.add(maksuaikaTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField maksuaikaKentta = new JTextField("14", 20);
        LisaaLaskutIkkunaMaksuAikaKenttaKuuntelija maksuAikaKenttaKuuntelija = new LisaaLaskutIkkunaMaksuAikaKenttaKuuntelija();
        maksuaikaKentta.getDocument().addDocumentListener(maksuAikaKenttaKuuntelija);
        maksuAikaKenttaKuuntelija.setPaivaysKentta(paivaysKentta);
        maksuAikaKenttaKuuntelija.setMaksuAikaKentta(maksuaikaKentta);
        paivaysKenttaKuuntelija.setMaksuAikaKentta(maksuaikaKentta);
        tiedotPanel.add(maksuaikaKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel erapaivaTeksti = new JLabel("Eräpäivä:");
        tiedotPanel.add(erapaivaTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = (new Insets(5, 5, 5, 0));
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, Integer.parseInt(maksuaikaKentta.getText()));
        JTextField erapaivaKentta = new JTextField(pvmFormaatti.format(c.getTime()), 20);
        paivaysKenttaKuuntelija.setErapaivaKentta(erapaivaKentta);
        maksuAikaKenttaKuuntelija.setErapaivaKentta(erapaivaKentta);
        erapaivaKentta.setEditable(false);
        tiedotPanel.add(erapaivaKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel viivastyskorkoTeksti = new JLabel("Viivästyskorko:");
        tiedotPanel.add(viivastyskorkoTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField viivastyskorkoKentta = new JTextField("8", 20);
        tiedotPanel.add(viivastyskorkoKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel maksuehtoTeksti = new JLabel("Maksuehto:");
        tiedotPanel.add(maksuehtoTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField maksuehtoKentta = new JTextField("14 päivää netto", 20);
        tiedotPanel.add(maksuehtoKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel lisatiedotTeksti = new JLabel("Lisätiedot:");
        tiedotPanel.add(lisatiedotTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField lisatiedotKentta = new JTextField("", 20);
        tiedotPanel.add(lisatiedotKentta, gbc);

        JButton lisaa = new JButton("Lisää");
        lisaa.setAlignmentX(Component.CENTER_ALIGNMENT);
        LisaaLaskuIkkunaLisaaKuuntelija kuuntelija = new LisaaLaskuIkkunaLisaaKuuntelija(comboBoxkuuntelija,
                suoritteetLista,
                paivaysKentta,
                maksuaikaKentta,
                erapaivaKentta,
                viivastyskorkoKentta,
                maksuehtoKentta,
                lisatiedotKentta,
                lataaja,
                taulukko,
                frame,
                lukko,
                pvmFormaatti);
        lisaa.addActionListener(kuuntelija);

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
