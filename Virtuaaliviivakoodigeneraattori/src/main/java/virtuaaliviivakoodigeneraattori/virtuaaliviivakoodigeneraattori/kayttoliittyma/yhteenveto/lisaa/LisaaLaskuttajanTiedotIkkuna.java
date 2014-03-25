/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.yhteenveto.lisaa;

import java.awt.Component;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.IkkunaKuuntelija;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.yhteenveto.LaskuttajaOsioJPanel;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LisaaLaskuttajanTiedotIkkuna implements Runnable {

    private JFrame frame;
    private final Lataaja lataaja;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel panel1;

    public LisaaLaskuttajanTiedotIkkuna(Lataaja lataaja, NappulaLukko lukko, LaskuttajaOsioJPanel panel) {
        this.lataaja = lataaja;
        this.lukko = lukko;
        this.panel1 = panel;
    }

    @Override
    public void run() {
        lukko.lukitse();

        frame = new JFrame("Lisää laskuttajan tiedot");
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

        JLabel nimiTeksti = new JLabel("Nimi:");
        JTextField nimiKentta = new JTextField("1");

        JLabel katuosoiteTeksti = new JLabel("Katuosoite:");
        JTextField katuosoiteKentta = new JTextField("1");

        JLabel postinumeroTeksti = new JLabel("Postinumero:");
        JTextField postinumeroKentta = new JTextField("1");

        JLabel kaupunkiTeksti = new JLabel("Kaupunki:");
        JTextField kaupunkiKentta = new JTextField("1");

        JLabel yritykseNimiTeksti = new JLabel("Yrityksen nimi:");
        JTextField yritykseNimiKentta = new JTextField("1");

        JLabel alvTunnisteTeksti = new JLabel("Alv-tunniste:");
        JTextField alvTunnisteKentta = new JTextField("1");

        JLabel tilinumeroTeksti = new JLabel("Tilinumero:");
        JTextField tilinumeroKentta = new JTextField("FI3816603001014664");

        JLabel tilinumeronPankkiTeksti = new JLabel("Tilinumeron pankki:");
        JTextField tilinumeronPankkiKentta = new JTextField("1");

        JLabel tilinumeronSwiftBicTeksti = new JLabel("Tilinumeron Swift/BIC:");
        JTextField tilinumeronSwiftBicKentta = new JTextField("1");

        JLabel puhelinnumeroTeksti = new JLabel("Puhelinnumero:");
        JTextField puhelinnumeroKentta = new JTextField("1");

        JLabel sahkopostiTeksti = new JLabel("Sähköposti:");
        JTextField sahkopostiKentta = new JTextField("asdasd@rfrf.com");

        JLabel laskujaLahetettyTeksti = new JLabel("Laskuja lähetetty yht:");
        JTextField laskujaLahetettyKentta = new JTextField("1");

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
