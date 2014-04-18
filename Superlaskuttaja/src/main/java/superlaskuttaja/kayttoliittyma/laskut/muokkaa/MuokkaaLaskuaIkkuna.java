/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.muokkaa;

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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class MuokkaaLaskuaIkkuna implements Runnable {

    private JFrame frame;
    private final Lataaja lataaja;
    private final LaskutTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;
    private final DateFormat pvmFormaatti;

    public MuokkaaLaskuaIkkuna(Lataaja lataaja, LaskutTaulukko taulukko, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.kuuntelija = kuuntelija;
        this.lukko = lukko;
        this.pvmFormaatti = new SimpleDateFormat("dd.MM.yyyy");
    }

    @Override
    public void run() {
        lukko.lukitse();

        frame = new JFrame("Muokkaa laskua");
        frame.setLocation(130, 90);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        IkkunaKuuntelija ikkunaKuuntelija = new IkkunaKuuntelija(lukko);
        frame.addWindowListener(ikkunaKuuntelija);

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
        JLabel asiakasTeksti = new JLabel("Valitse uusi asiakas:");
        tiedotPanel.add(asiakasTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = (new Insets(0, 5, 5, 0));
        String[] vaihtoehdotString = lataaja.getLadattuTietovarasto().asiakkaidenNimetArrayString();
        JComboBox asiakas = new JComboBox(vaihtoehdotString);
        asiakas.setSelectedIndex(lataaja.getLadattuTietovarasto().getAsiakkaat().indexOf(lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getAsiakas()));
        asiakas.setEditable(false);
        MuokkaaLaskuaIkkunaComboBoxKuuntelija comboBoxkuuntelija = new MuokkaaLaskuaIkkunaComboBoxKuuntelija();
        asiakas.addActionListener(comboBoxkuuntelija);
        tiedotPanel.add(asiakas, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel suoritteetTeksti = new JLabel("Valitse uuden asiakkaan laskutettavat suoritteet:");
        tiedotPanel.add(suoritteetTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = (new Insets(5, 5, 5, 0));
        MuokkaaLaskuaIkkunaSuoritteetList suoritteetLista = new MuokkaaLaskuaIkkunaSuoritteetList(comboBoxkuuntelija, kuuntelija, lataaja);
        comboBoxkuuntelija.setSuoritteetList(suoritteetLista);
        JScrollPane suoritteetPane = new JScrollPane(suoritteetLista);
        suoritteetLista.paivitaListanSisalto();
        tiedotPanel.add(suoritteetPane, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel paivaysTeksti = new JLabel("Uusi päiväys muodossa pp.kk.vvvv:");
        tiedotPanel.add(paivaysTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField paivaysKentta = new JTextField(pvmFormaatti.format(lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getPaivays().getTime()), 20);
        MuokkaaLaskuaIkkunaPaivaysKenttaKuuntelija paivaysKenttaKuuntelija = new MuokkaaLaskuaIkkunaPaivaysKenttaKuuntelija();
        paivaysKentta.getDocument().addDocumentListener(paivaysKenttaKuuntelija);
        paivaysKenttaKuuntelija.setPaivaysKentta(paivaysKentta);
        tiedotPanel.add(paivaysKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel maksuaikaTeksti = new JLabel("Uusi maksuaika päivissä:");
        tiedotPanel.add(maksuaikaTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField maksuaikaKentta = new JTextField("14", 20);
        MuokkaaLaskuaIkkunaMaksuAikaKenttaKuuntelija maksuAikaKenttaKuuntelija = new MuokkaaLaskuaIkkunaMaksuAikaKenttaKuuntelija();
        maksuaikaKentta.getDocument().addDocumentListener(maksuAikaKenttaKuuntelija);
        maksuAikaKenttaKuuntelija.setPaivaysKentta(paivaysKentta);
        maksuAikaKenttaKuuntelija.setMaksuAikaKentta(maksuaikaKentta);
        paivaysKenttaKuuntelija.setMaksuAikaKentta(maksuaikaKentta);
        tiedotPanel.add(maksuaikaKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel erapaivaTeksti = new JLabel("Uusi eräpäivä:");
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
        JLabel viivastyskorkoTeksti = new JLabel("Uusi viivästyskorko:");
        tiedotPanel.add(viivastyskorkoTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField viivastyskorkoKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getViivastyskorko().toString(), 20);
        tiedotPanel.add(viivastyskorkoKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel maksuehtoTeksti = new JLabel("Uusi maksuehto:");
        tiedotPanel.add(maksuehtoTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField maksuehtoKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getMaksuehto(), 20);
        tiedotPanel.add(maksuehtoKentta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = (new Insets(5, 0, 5, 5));
        JLabel lisatiedotTeksti = new JLabel("Uudet lisätiedot:");
        tiedotPanel.add(lisatiedotTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.insets = (new Insets(5, 5, 5, 0));
        JTextField lisatiedotKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getLisatiedot(), 20);
        tiedotPanel.add(lisatiedotKentta, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.insets = (new Insets(5, 0, 0, 5));
        JLabel onkoMaksettuTeksti = new JLabel("Onko lasku maksettu:");
        tiedotPanel.add(onkoMaksettuTeksti, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.insets = (new Insets(5, 5, 0, 0));
        JCheckBox onkoMaksettu = new JCheckBox();
        onkoMaksettu.setSelected(lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).isOnkoMaksettu());
        tiedotPanel.add(onkoMaksettu, gbc);

        JButton lisaa = new JButton("Muokkaa");
        lisaa.setAlignmentX(Component.CENTER_ALIGNMENT);
        MuokkaaLaskuaMuokkaaKuuntelija muokkaaKuuntelija = new MuokkaaLaskuaMuokkaaKuuntelija(comboBoxkuuntelija,
                suoritteetLista,
                paivaysKentta,
                maksuaikaKentta,
                erapaivaKentta,
                viivastyskorkoKentta,
                maksuehtoKentta,
                lisatiedotKentta,
                onkoMaksettu,
                lataaja,
                taulukko,
                frame,
                lukko,
                pvmFormaatti);
        lisaa.addActionListener(muokkaaKuuntelija);

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
