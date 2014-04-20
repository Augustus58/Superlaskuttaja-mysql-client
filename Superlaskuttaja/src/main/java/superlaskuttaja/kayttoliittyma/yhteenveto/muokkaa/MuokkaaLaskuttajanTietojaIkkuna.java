/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.yhteenveto.muokkaa;

import java.awt.Component;
import superlaskuttaja.kayttoliittyma.IkkunaKuuntelija;
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
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.yhteenveto.LaskuttajaOsioJPanel;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class MuokkaaLaskuttajanTietojaIkkuna implements Runnable {

    private JFrame frame;
    private final Lataaja lataaja;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel panel1;

    public MuokkaaLaskuttajanTietojaIkkuna(Lataaja lataaja, NappulaLukko lukko, LaskuttajaOsioJPanel panel) {
        this.lataaja = lataaja;
        this.lukko = lukko;
        this.panel1 = panel;
    }

    @Override
    public void run() {
        lukko.lukitse();

        frame = new JFrame("Muokkaa laskuttajan tietoja");
        frame.setLocation(130, 90);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        
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
        GridLayout tiedotLayout = new GridLayout(0, 4);
        tiedotLayout.setVgap(10);
        tiedotLayout.setHgap(10);
        tiedotPanel.setLayout(tiedotLayout);

        JLabel nimiTeksti1 = new JLabel("Vanha nimi:");
        JLabel nimiTeksti2 = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getNimi());
        JLabel nimiTeksti = new JLabel("Uusi nimi:");
        JTextField nimiKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskuttaja().getNimi());

        JLabel katuosoiteTeksti1 = new JLabel("Vanha katuosoite:");
        JLabel katuosoiteTeksti2 = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getKatuosoite());
        JLabel katuosoiteTeksti = new JLabel("Uusi katuosoite:");
        JTextField katuosoiteKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskuttaja().getKatuosoite());

        JLabel postinumeroTeksti1 = new JLabel("Vanha postinumero:");
        JLabel postinumeroTeksti2 = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getPostinumero());
        JLabel postinumeroTeksti = new JLabel("Uusi postinumero:");
        JTextField postinumeroKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskuttaja().getPostinumero());

        JLabel kaupunkiTeksti1 = new JLabel("Vanha kaupunki:");
        JLabel kaupunkiTeksti2 = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getKaupunki());
        JLabel kaupunkiTeksti = new JLabel("Uusi kaupunki");
        JTextField kaupunkiKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskuttaja().getKaupunki());

        JLabel yritykseNimiTeksti1 = new JLabel("Vanha yrityksen nimi:");
        JLabel yritykseNimiTeksti2 = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getYrityksenNimi());
        JLabel yritykseNimiTeksti = new JLabel("Uusi yrityksen nimi:");
        JTextField yritykseNimiKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskuttaja().getYrityksenNimi());

        JLabel alvTunnisteTeksti1 = new JLabel("Vanha alv-tunniste:");
        JLabel alvTunnisteTeksti2 = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getAlvTunniste());
        JLabel alvTunnisteTeksti = new JLabel("Uusi alv-tunniste:");
        JTextField alvTunnisteKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskuttaja().getAlvTunniste());

        JLabel tilinumeroTeksti1 = new JLabel("Vanha tilinumero:");
        JLabel tilinumeroTeksti2 = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getTilinumero().getTilinumero());
        JLabel tilinumeroTeksti = new JLabel("Uusi tilinumero:");
        JTextField tilinumeroKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskuttaja().getTilinumero().getTilinumero());

        JLabel tilinumeronPankkiTeksti1 = new JLabel("Vanha tilinumeron pankki:");
        JLabel tilinumeronPankkiTeksti2 = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getTilinumero().getPankki());
        JLabel tilinumeronPankkiTeksti = new JLabel("Uusi tilinumeron pankki:");
        JTextField tilinumeronPankkiKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskuttaja().getTilinumero().getPankki());

        JLabel tilinumeronSwiftBicTeksti1 = new JLabel("Vanha tilinumeron Swift/BIC:");
        JLabel tilinumeronSwiftBicTeksti2 = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getTilinumero().getSwiftBic());
        JLabel tilinumeronSwiftBicTeksti = new JLabel("Uusi tilinumeron Swift/BIC:");
        JTextField tilinumeronSwiftBicKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskuttaja().getTilinumero().getSwiftBic());

        JLabel puhelinnumeroTeksti1 = new JLabel("Vanha puhelinnumero:");
        JLabel puhelinnumeroTeksti2 = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getPuhelinnumero());
        JLabel puhelinnumeroTeksti = new JLabel("Uusi puhelinnumero:");
        JTextField puhelinnumeroKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskuttaja().getPostinumero());

        JLabel sahkopostiTeksti1 = new JLabel("Vanha sähköposti:");
        JLabel sahkopostiTeksti2 = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getSahkopostiOsoite());
        JLabel sahkopostiTeksti = new JLabel("Uusi sähköposti:");
        JTextField sahkopostiKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskuttaja().getSahkopostiOsoite());

        JLabel laskujaLahetettyTeksti1 = new JLabel("Vanha laskuja lähetetty yht:");
        JLabel laskujaLahetettyTeksti2 = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getLaskujaLahetetty().toString());
        JLabel laskujaLahetettyTeksti = new JLabel("Uusi laskuja lähetetty yht:");
        JTextField laskujaLahetettyKentta = new JTextField(lataaja.getLadattuTietovarasto().getLaskuttaja().getLaskujaLahetetty().toString());

        JButton muokkaa = new JButton("Muokkaa");
        muokkaa.setAlignmentX(Component.CENTER_ALIGNMENT);
        MuokkaaLaskuttajanTietojaIkkunaMuokkaaKuuntelija kuuntelija = new MuokkaaLaskuttajanTietojaIkkunaMuokkaaKuuntelija(
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
        muokkaa.addActionListener(kuuntelija);

        tiedotPanel.add(nimiTeksti1);
        tiedotPanel.add(nimiTeksti2);
        tiedotPanel.add(nimiTeksti);
        tiedotPanel.add(nimiKentta);
        tiedotPanel.add(katuosoiteTeksti1);
        tiedotPanel.add(katuosoiteTeksti2);
        tiedotPanel.add(katuosoiteTeksti);
        tiedotPanel.add(katuosoiteKentta);
        tiedotPanel.add(postinumeroTeksti1);
        tiedotPanel.add(postinumeroTeksti2);
        tiedotPanel.add(postinumeroTeksti);
        tiedotPanel.add(postinumeroKentta);
        tiedotPanel.add(kaupunkiTeksti1);
        tiedotPanel.add(kaupunkiTeksti2);
        tiedotPanel.add(kaupunkiTeksti);
        tiedotPanel.add(kaupunkiKentta);
        tiedotPanel.add(yritykseNimiTeksti1);
        tiedotPanel.add(yritykseNimiTeksti2);
        tiedotPanel.add(yritykseNimiTeksti);
        tiedotPanel.add(yritykseNimiKentta);
        tiedotPanel.add(alvTunnisteTeksti1);
        tiedotPanel.add(alvTunnisteTeksti2);
        tiedotPanel.add(alvTunnisteTeksti);
        tiedotPanel.add(alvTunnisteKentta);
        tiedotPanel.add(tilinumeroTeksti1);
        tiedotPanel.add(tilinumeroTeksti2);
        tiedotPanel.add(tilinumeroTeksti);
        tiedotPanel.add(tilinumeroKentta);
        tiedotPanel.add(tilinumeronPankkiTeksti1);
        tiedotPanel.add(tilinumeronPankkiTeksti2);
        tiedotPanel.add(tilinumeronPankkiTeksti);
        tiedotPanel.add(tilinumeronPankkiKentta);
        tiedotPanel.add(tilinumeronSwiftBicTeksti1);
        tiedotPanel.add(tilinumeronSwiftBicTeksti2);
        tiedotPanel.add(tilinumeronSwiftBicTeksti);
        tiedotPanel.add(tilinumeronSwiftBicKentta);
        tiedotPanel.add(puhelinnumeroTeksti1);
        tiedotPanel.add(puhelinnumeroTeksti2);
        tiedotPanel.add(puhelinnumeroTeksti);
        tiedotPanel.add(puhelinnumeroKentta);
        tiedotPanel.add(sahkopostiTeksti1);
        tiedotPanel.add(sahkopostiTeksti2);
        tiedotPanel.add(sahkopostiTeksti);
        tiedotPanel.add(sahkopostiKentta);
        tiedotPanel.add(laskujaLahetettyTeksti1);
        tiedotPanel.add(laskujaLahetettyTeksti2);
        tiedotPanel.add(laskujaLahetettyTeksti);
        tiedotPanel.add(laskujaLahetettyKentta);

        panel.add(tiedotPanel);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(muokkaa);
        panel.add(Box.createRigidArea(new Dimension(950, 0)));

        container.add(panel);

    }

    public JFrame getFrame() {
        return frame;
    }

}
