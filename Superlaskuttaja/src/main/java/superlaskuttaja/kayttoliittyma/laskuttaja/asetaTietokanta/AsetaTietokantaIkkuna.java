/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskuttaja.asetaTietokanta;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import superlaskuttaja.kayttoliittyma.IkkunaKuuntelija;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.kayttoliittyma.laskuttaja.LaskuttajaOsioJPanel;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class AsetaTietokantaIkkuna implements Runnable {

    private JFrame frame;
    private final DataDeliver lataaja;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel panel1;
    private final JTabbedPane tabbedPane;
    private final AsiakkaatTaulukko asiakkaatTaulukko;
    private final SuoritteetTaulukko suoritteetTaulukko;
    private final LaskutTaulukko laskutTaulukko;

    public AsetaTietokantaIkkuna(DataDeliver lataaja, NappulaLukko lukko, LaskuttajaOsioJPanel panel1, JTabbedPane tabbedPane, AsiakkaatTaulukko asiakkaatTaulukko, SuoritteetTaulukko suoritteetTaulukko, LaskutTaulukko laskutTaulukko) {
        this.lataaja = lataaja;
        this.lukko = lukko;
        this.panel1 = panel1;
        this.tabbedPane = tabbedPane;
        this.asiakkaatTaulukko = asiakkaatTaulukko;
        this.suoritteetTaulukko = suoritteetTaulukko;
        this.laskutTaulukko = laskutTaulukko;
    }

    @Override
    public void run() {
        lukko.lukitse();

        frame = new JFrame("Aseta tietokanta");

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

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
        GridLayout tiedotLayout = new GridLayout(0, 2);
        tiedotLayout.setVgap(10);
        tiedotLayout.setHgap(10);
        tiedotPanel.setLayout(tiedotLayout);

        JLabel osoiteTeksti = new JLabel("Osoite muodossa mysql://localhost:3306 :");
        JTextField osoiteKentta = new JTextField(lataaja.getDbc().getAddr());

        JLabel kayttajatunnusTeksti = new JLabel("Käyttäjätunnus:");
        JTextField kayttajatunnusKentta = new JTextField(lataaja.getDbc().getUser());

        JLabel salasanaTeksti = new JLabel("Salasana:");
        JPasswordField salasanaKentta = new JPasswordField();

        JCheckBox muistaAsetus = new JCheckBox("Muista");

        JButton aseta = new JButton("Aseta");
        aseta.setAlignmentX(Component.CENTER_ALIGNMENT);
        AsetaTietokantaAsetaKuuntelija kuuntelija = new AsetaTietokantaAsetaKuuntelija(
                osoiteKentta,
                kayttajatunnusKentta,
                salasanaKentta,
                muistaAsetus,
                lataaja,
                frame,
                lukko,
                panel1,
                tabbedPane,
                asiakkaatTaulukko,
                suoritteetTaulukko,
                laskutTaulukko);
        aseta.addActionListener(kuuntelija);

        tiedotPanel.add(osoiteTeksti);
        tiedotPanel.add(osoiteKentta);
        tiedotPanel.add(kayttajatunnusTeksti);
        tiedotPanel.add(kayttajatunnusKentta);
        tiedotPanel.add(salasanaTeksti);
        tiedotPanel.add(salasanaKentta);

        panel.add(tiedotPanel);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(muistaAsetus);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(aseta);
        panel.add(Box.createRigidArea(new Dimension(600, 0)));

        container.add(panel);
    }

    public JFrame getFrame() {
        return frame;
    }
}
