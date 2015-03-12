/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskuttaja.lisaaLaskuttaja;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import superlaskuttaja.kayttoliittyma.IkkunaKuuntelija;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.laskuttaja.LaskuttajaOsioJPanel;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LisaaLaskuttajanTiedotIkkuna implements Runnable {

    private JFrame frame;
    private final DataDeliver lataaja;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel panel1;

    public LisaaLaskuttajanTiedotIkkuna(DataDeliver lataaja, NappulaLukko lukko, LaskuttajaOsioJPanel panel) {
        this.lataaja = lataaja;
        this.lukko = lukko;
        this.panel1 = panel;
    }

    @Override
    public void run() {
        lukko.lukitse();

        frame = new JFrame("Lisää laskuttajan tiedot");
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

        JLabel nimiTeksti = new JLabel("Nimi:");
        JTextField nimiKentta = new JTextField();

        JLabel katuosoiteTeksti = new JLabel("Katuosoite:");
        JTextField katuosoiteKentta = new JTextField();

        JLabel postinumeroTeksti = new JLabel("Postinumero:");
        JTextField postinumeroKentta = new JTextField();

        JLabel kaupunkiTeksti = new JLabel("Kaupunki:");
        JTextField kaupunkiKentta = new JTextField();

        JLabel yritykseNimiTeksti = new JLabel("Yrityksen nimi:");
        JTextField yritykseNimiKentta = new JTextField();

        JLabel alvTunnisteTeksti = new JLabel("Alv-tunniste:");
        JTextField alvTunnisteKentta = new JTextField();

        JLabel tilinumeroTeksti = new JLabel("Tilinumero:");
        JTextField tilinumeroKentta = new JTextField();

        JLabel tilinumeronPankkiTeksti = new JLabel("Tilinumeron pankki:");
        JTextField tilinumeronPankkiKentta = new JTextField();

        JLabel tilinumeronSwiftBicTeksti = new JLabel("Tilinumeron Swift/BIC:");
        JTextField tilinumeronSwiftBicKentta = new JTextField();

        JLabel puhelinnumeroTeksti = new JLabel("Puhelinnumero:");
        JTextField puhelinnumeroKentta = new JTextField();

        JLabel sahkopostiTeksti = new JLabel("Sähköposti:");
        JTextField sahkopostiKentta = new JTextField();

        JLabel laskujaLahetettyTeksti = new JLabel("Laskuja lähetetty yht:");
        JTextField laskujaLahetettyKentta = new JTextField();

        JButton lisaa = new JButton("Lisää");
        lisaa.setAlignmentX(Component.CENTER_ALIGNMENT);
        LisaaLaskuttajanTiedotIkkunaLisaaKuuntelija kuuntelija = new LisaaLaskuttajanTiedotIkkunaLisaaKuuntelija(
                nimiKentta,
                katuosoiteKentta,
                postinumeroKentta,
                kaupunkiKentta,
                yritykseNimiKentta,
                alvTunnisteKentta,
                tilinumeroKentta,
                tilinumeronPankkiKentta,
                tilinumeronSwiftBicKentta,
                puhelinnumeroKentta,
                sahkopostiKentta,
                laskujaLahetettyKentta,
                lataaja,
                frame,
                lukko,
                panel1);
        lisaa.addActionListener(kuuntelija);
        
        tiedotPanel.add(nimiTeksti);
        tiedotPanel.add(nimiKentta);
        tiedotPanel.add(katuosoiteTeksti);
        tiedotPanel.add(katuosoiteKentta);
        tiedotPanel.add(postinumeroTeksti);
        tiedotPanel.add(postinumeroKentta);
        tiedotPanel.add(kaupunkiTeksti);
        tiedotPanel.add(kaupunkiKentta);
        tiedotPanel.add(yritykseNimiTeksti);
        tiedotPanel.add(yritykseNimiKentta);
        tiedotPanel.add(alvTunnisteTeksti);
        tiedotPanel.add(alvTunnisteKentta);
        tiedotPanel.add(tilinumeroTeksti);
        tiedotPanel.add(tilinumeroKentta);
        tiedotPanel.add(tilinumeronPankkiTeksti);
        tiedotPanel.add(tilinumeronPankkiKentta);
        tiedotPanel.add(tilinumeronSwiftBicTeksti);
        tiedotPanel.add(tilinumeronSwiftBicKentta);
        tiedotPanel.add(puhelinnumeroTeksti);
        tiedotPanel.add(puhelinnumeroKentta);
        tiedotPanel.add(sahkopostiTeksti);
        tiedotPanel.add(sahkopostiKentta);
        tiedotPanel.add(laskujaLahetettyTeksti);
        tiedotPanel.add(laskujaLahetettyKentta);

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