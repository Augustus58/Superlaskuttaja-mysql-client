/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.asiakkaat.muokkaa;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import superlaskuttaja.kayttoliittyma.IkkunaKuuntelija;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class MuokkaaAsiakastaIkkuna implements Runnable {

    private JFrame frame;
    private final DataDeliver lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public MuokkaaAsiakastaIkkuna(DataDeliver lataaja, AsiakkaatTaulukko taulukko, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.kuuntelija = kuuntelija;
        this.lukko = lukko;
    }

    @Override
    public void run() {
        lukko.lukitse();

        frame = new JFrame("Muokkaa asiakasta");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame.setLocation(130, 90);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        IkkunaKuuntelija ikkinaKuuntelija = new IkkunaKuuntelija(lukko);
        frame.addWindowListener(ikkinaKuuntelija);

        try {
            luoKomponentit(frame.getContentPane());

            frame.pack();

            Dimension frameSize = frame.getSize();
            int x = (screenSize.width - frameSize.width) / 2;
            int y = (screenSize.height - frameSize.height) / 2;
            frame.setLocation(x, y);

            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            lukko.avaa();
            AsiakkaatPanelMuokkaaAsiakastaPoikkeusIkkuna poikkeusIkkuna = new AsiakkaatPanelMuokkaaAsiakastaPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }
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

        ResultSet rs = lataaja.getDbc().executeQuery("select distinct nimi, katuosoite, postinumero, kaupunki, email, asiakasnumero, laskujaLahetetty\n"
                + "from Asiakas\n"
                + "where asiakasnumero = " + taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 5).toString() + "\n"
                + "and versio = " + taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 7).toString() + "\n"
                + "");

        JTextField nimiKentta = null;
        JTextField katuosoiteKentta = null;
        JTextField postinumeroKentta = null;
        JTextField kaupunkiKentta = null;
        JTextField laskujaLahetettyKentta = null;
        JLabel nimiTeksti = null;
        JLabel nimiTekstiEntinen = null;
        JLabel nimiTekstiUusi = null;
        JLabel katuosoiteTeksti = null;
        JLabel katuosoiteTekstiEntinen = null;
        JLabel katuosoiteTekstiUusi = null;
        JLabel postinumeroTeksti = null;
        JLabel postinumeroTekstiEntinen = null;
        JLabel postinumeroTekstiUusi = null;
        JLabel kaupunkiTeksti = null;
        JLabel kaupunkiTekstiEntinen = null;
        JLabel kaupunkiTekstiUusi = null;
        JLabel laskujaLahetettyTeksti = null;
        JLabel laskujaLahetettyTekstiVanha = null;
        JLabel laskujaLahetettyTekstiUusi = null;
        JLabel emailTeksti = null;
        JLabel emailTekstiEntinen = null;
        JLabel emailTekstiUusi = null;
        JTextField emailKentta = null;

        try {
            rs.first();

            nimiTeksti = new JLabel("Entinen nimi: ");
            nimiTekstiEntinen = new JLabel(rs.getString(1));
            nimiTekstiUusi = new JLabel("Uusi nimi: ");
            nimiKentta = new JTextField(rs.getString(1));

            katuosoiteTeksti = new JLabel("Entinen katuosoite:");
            katuosoiteTekstiEntinen = new JLabel(rs.getString(2));
            katuosoiteTekstiUusi = new JLabel("Uusi katuosoite:");
            katuosoiteKentta = new JTextField(rs.getString(2));

            postinumeroTeksti = new JLabel("Entinen postinumero:");
            postinumeroTekstiEntinen = new JLabel(rs.getString(3));
            postinumeroTekstiUusi = new JLabel("Uusi postinumero:");
            postinumeroKentta = new JTextField(rs.getString(3));

            kaupunkiTeksti = new JLabel("Entinen kaupunki:");
            kaupunkiTekstiEntinen = new JLabel(rs.getString(4));
            kaupunkiTekstiUusi = new JLabel("Uusi kaupunki:");
            kaupunkiKentta = new JTextField(rs.getString(4));

            emailTeksti = new JLabel("Entinen sähköposti:");
            emailTekstiEntinen = new JLabel(rs.getString(5));
            emailTekstiUusi = new JLabel("Uusi sähköposti:");
            emailKentta = new JTextField(rs.getString(5));

            laskujaLahetettyTeksti = new JLabel("Laskuja lähetetty entinen:");
            laskujaLahetettyTekstiVanha = new JLabel(Integer.toString(rs.getInt(7)));
            laskujaLahetettyTekstiUusi = new JLabel("Laskuja lähetetty uusi:");
            laskujaLahetettyKentta = new JTextField(Integer.toString(rs.getInt(7)));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
        }

        JButton muokkaa = new JButton("Muokkaa");
        muokkaa.setAlignmentX(Component.CENTER_ALIGNMENT);
        MuokkaaAsiakastaIkkunaMuokkaaKuuntelija muokkaaAsiakastaKuuntelija = new MuokkaaAsiakastaIkkunaMuokkaaKuuntelija(nimiKentta, katuosoiteKentta, postinumeroKentta, kaupunkiKentta, emailKentta, laskujaLahetettyKentta, lataaja, taulukko, frame, kuuntelija, lukko);
        muokkaa.addActionListener(muokkaaAsiakastaKuuntelija);

        tiedotPanel.add(nimiTeksti);
        tiedotPanel.add(nimiTekstiEntinen);
        tiedotPanel.add(nimiTekstiUusi);
        tiedotPanel.add(nimiKentta);

        tiedotPanel.add(katuosoiteTeksti);
        tiedotPanel.add(katuosoiteTekstiEntinen);
        tiedotPanel.add(katuosoiteTekstiUusi);
        tiedotPanel.add(katuosoiteKentta);

        tiedotPanel.add(postinumeroTeksti);
        tiedotPanel.add(postinumeroTekstiEntinen);
        tiedotPanel.add(postinumeroTekstiUusi);
        tiedotPanel.add(postinumeroKentta);

        tiedotPanel.add(kaupunkiTeksti);
        tiedotPanel.add(kaupunkiTekstiEntinen);
        tiedotPanel.add(kaupunkiTekstiUusi);
        tiedotPanel.add(kaupunkiKentta);

        tiedotPanel.add(emailTeksti);
        tiedotPanel.add(emailTekstiEntinen);
        tiedotPanel.add(emailTekstiUusi);
        tiedotPanel.add(emailKentta);

        tiedotPanel.add(laskujaLahetettyTeksti);
        tiedotPanel.add(laskujaLahetettyTekstiVanha);
        tiedotPanel.add(laskujaLahetettyTekstiUusi);
        tiedotPanel.add(laskujaLahetettyKentta);

        panel.add(tiedotPanel);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(muokkaa);

        container.add(panel);

    }

    public JFrame getFrame() {
        return frame;
    }

}
