/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.lisaa;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import superlaskuttaja.kayttoliittyma.ComboBoxKuuntelija;
import superlaskuttaja.kayttoliittyma.IkkunaKuuntelija;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LisaaSuoriteIkkuna implements Runnable {

    private JFrame frame;
    private final DataDeliver lataaja;
    private final SuoritteetTaulukko taulukko;
    private final NappulaLukko lukko;
    private final DateFormat pvmFormaatti;

    public LisaaSuoriteIkkuna(DataDeliver lataaja, SuoritteetTaulukko taulukko, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.lukko = lukko;
        this.pvmFormaatti = new SimpleDateFormat("dd.MM.yyyy");
    }

    @Override
    public void run() {
        lukko.lukitse();

        frame = new JFrame("Lisää suorite");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame.setLocation(130, 90);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        IkkunaKuuntelija kuuntelija = new IkkunaKuuntelija(lukko);
        frame.addWindowListener(kuuntelija);

        luoKomponentit(frame.getContentPane());
        frame.pack();

        Dimension frameSize = frame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x, y);

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
        
        ResultSet rs = lataaja.getDbc().executeQuery("select distinct nimi, asiakasnumero\n"
                + "from Asiakas A\n"
                + "where versio = (select max(versio)\n"
                + "from Asiakas where asiakasnumero = A.asiakasnumero)\n"
                + "");

        ArrayList<String> al = new ArrayList<>();

        try {

            while (rs.next()) {
                al.add(rs.getString(1) + " " + rs.getString(2));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
        }

        String[] strings = new String[al.size()];

        for (int i = 0; i < al.size(); i++) {
            strings[i] = al.get(i);
        }
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = (new Insets(0, 0, 5, 5));
        gbc.weightx = 0.30;
        JLabel asiakasTeksti = new JLabel("Tilaaja:");
        tiedotPanel.add(asiakasTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = (new Insets(0, 5, 5, 0));
        gbc.weightx = 0.5;
        JComboBox comboBoxTilaaja = new JComboBox(strings);
        comboBoxTilaaja.setSelectedIndex(0);
        comboBoxTilaaja.setEditable(false);
        TilaajaComboBoxKuuntelija comboBoxKuuntelijaTilaaja = new TilaajaComboBoxKuuntelija();
        comboBoxTilaaja.addActionListener(comboBoxKuuntelijaTilaaja);
        tiedotPanel.add(comboBoxTilaaja, gbc);
        gbc.weightx = 0.0;
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = (new Insets(0, 0, 5, 5));
        JLabel vastaanottajaTeksti = new JLabel("Vastaanottaja:");
        tiedotPanel.add(vastaanottajaTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = (new Insets(0, 5, 5, 0));
        JComboBox comboBoxVastaanottaja = new JComboBox(strings);
        comboBoxKuuntelijaTilaaja.setComboBoxVastaanottaja(comboBoxVastaanottaja);
        comboBoxVastaanottaja.setSelectedIndex(0);
        comboBoxVastaanottaja.setEditable(false);
        ComboBoxKuuntelija comboBoxKuuntelijaVastaanottaja = new ComboBoxKuuntelija();
        comboBoxVastaanottaja.addActionListener(comboBoxKuuntelijaVastaanottaja);
        tiedotPanel.add(comboBoxVastaanottaja, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel kuvausTeksti = new JLabel("Kuvaus:");
        tiedotPanel.add(kuvausTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField kuvausKentta = new JTextField();
        tiedotPanel.add(kuvausKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel aloitusAikaTeksti = new JLabel("Aloitusaika:");
        tiedotPanel.add(aloitusAikaTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = (new Insets(5, 5, 5, 0));
        Date paivamaaraTanaan = new Date();
        JTextField aloitusAikaKentta = new JTextField(pvmFormaatti.format(paivamaaraTanaan));
        LisaaSuoriteIkkunaAloitusAikaKenttaKuuntelija k1 = new LisaaSuoriteIkkunaAloitusAikaKenttaKuuntelija();
        aloitusAikaKentta.getDocument().addDocumentListener(k1);
        k1.setAloitusAikaKentta(aloitusAikaKentta);
        tiedotPanel.add(aloitusAikaKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel lopetusAikaTeksti = new JLabel("Lopetusaika:");
        tiedotPanel.add(lopetusAikaTeksti, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = (new Insets(5, 5, 5, 5));
        gbc.weightx = 0.20;
        gbc.anchor = GridBagConstraints.EAST;
        JRadioButton jrb1 = new JRadioButton();
        jrb1.setSelected(true);
        jrb1.setEnabled(false);
        k1.setJrb1(jrb1);
        tiedotPanel.add(jrb1, gbc);
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField lopetusAikaKentta = new JTextField();
        lopetusAikaKentta.setEditable(false);
        LisaaSuoriteIkkunaLopetusAikaKenttaKuuntelija k2 = new LisaaSuoriteIkkunaLopetusAikaKenttaKuuntelija();
        lopetusAikaKentta.getDocument().addDocumentListener(k2);
        k2.setLopetusAikaKentta(lopetusAikaKentta);
        k2.setAloitusAikaKentta(aloitusAikaKentta);
        k2.setJrb1(jrb1);
        k1.setLopetusAikaKentta(lopetusAikaKentta);
        tiedotPanel.add(lopetusAikaKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel maaraTeksti = new JLabel("Määrä:");
        tiedotPanel.add(maaraTeksti, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = (new Insets(5, 5, 5, 5));
        gbc.anchor = GridBagConstraints.EAST;
        JRadioButton jrb2 = new JRadioButton();
        jrb2.setEnabled(false);
        k1.setJrb2(jrb2);
        k2.setJrb2(jrb2);
        tiedotPanel.add(jrb2, gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField maaraKentta = new JTextField();
        LisaaSuoriteIkkunaMaaraKenttaKuuntelija k3 = new LisaaSuoriteIkkunaMaaraKenttaKuuntelija();
        maaraKentta.getDocument().addDocumentListener(k3);
        k3.setAloitusAikaKentta(aloitusAikaKentta);
        k3.setLopetusAikaKentta(lopetusAikaKentta);
        k3.setMaaraKentta(maaraKentta);
        k3.setJrb1(jrb1);
        k3.setJrb2(jrb2);
        k2.setMaaraKentta(maaraKentta);
        k1.setMaaraKentta(maaraKentta);
        tiedotPanel.add(maaraKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel maaranYksikotTeksti = new JLabel("Yksiköt:");
        tiedotPanel.add(maaranYksikotTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField maaranYksikotKentta = new JTextField("h");
        k2.setMaaranYksikotKentta(maaranYksikotKentta);
        k3.setMaaranYksikotKentta(maaranYksikotKentta);
        tiedotPanel.add(maaranYksikotKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel aHintaVerollinenTeksti = new JLabel("à hinta verollinen:");
        tiedotPanel.add(aHintaVerollinenTeksti, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.insets = (new Insets(5, 5, 5, 5));
        gbc.anchor = GridBagConstraints.EAST;
        JRadioButton jrb3 = new JRadioButton();
        jrb3.setSelected(true);
        tiedotPanel.add(jrb3, gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField aHintaVerollinenKentta = new JTextField();
        LisaaSuoriteIkkunaVerollinenAHintaKenttaKuuntelija k4 = new LisaaSuoriteIkkunaVerollinenAHintaKenttaKuuntelija();
        aHintaVerollinenKentta.getDocument().addDocumentListener(k4);
        k4.setJrb3(jrb3);
        k4.setaHintaVerollinenKentta(aHintaVerollinenKentta);
        tiedotPanel.add(aHintaVerollinenKentta, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel aHintaTeksti = new JLabel("à hinta veroton:");
        tiedotPanel.add(aHintaTeksti, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.insets = (new Insets(5, 5, 5, 5));
        gbc.anchor = GridBagConstraints.EAST;
        JRadioButton jrb4 = new JRadioButton();
        k4.setJrb4(jrb4);
        tiedotPanel.add(jrb4, gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField aHintaVerotonKentta = new JTextField();
        LisaaSuoriteIkkunaVerotonAHintaKenttaKuuntelija k5 = new LisaaSuoriteIkkunaVerotonAHintaKenttaKuuntelija();
        aHintaVerotonKentta.getDocument().addDocumentListener(k5);
        k5.setaHintaVerollinenKentta(aHintaVerollinenKentta);
        k5.setaHintaVerotonKentta(aHintaVerotonKentta);
        k5.setJrb3(jrb3);
        k5.setJrb4(jrb4);
        k4.setaHintaVerotonKentta(aHintaVerotonKentta);
        tiedotPanel.add(aHintaVerotonKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel alvProsTeksti = new JLabel("Alv prosentti:");
        tiedotPanel.add(alvProsTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField alvProsKentta = new JTextField("24");
        k4.setAlvProsKentta(alvProsKentta);
        k5.setAlvProsKentta(alvProsKentta);
        tiedotPanel.add(alvProsKentta, gbc);
        
        JButton lisaa = new JButton("Lisää");
        lisaa.setAlignmentX(Component.CENTER_ALIGNMENT);
        LisaaSuoriteIkkunaLisaaKuuntelija kuuntelija = new LisaaSuoriteIkkunaLisaaKuuntelija(comboBoxTilaaja, comboBoxVastaanottaja, comboBoxKuuntelijaTilaaja, comboBoxKuuntelijaVastaanottaja, kuvausKentta, aloitusAikaKentta, lopetusAikaKentta, maaraKentta, maaranYksikotKentta, aHintaVerotonKentta, alvProsKentta, lataaja, taulukko, frame, lukko);
        lisaa.addActionListener(kuuntelija);

        ButtonGroup group1 = new ButtonGroup();
        group1.add(jrb1);
        group1.add(jrb2);
        
        ButtonGroup group2 = new ButtonGroup();
        group2.add(jrb3);
        group2.add(jrb4);

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