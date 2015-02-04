/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.asiakkaat.lisaa;

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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import superlaskuttaja.kayttoliittyma.IkkunaKuuntelija;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LisaaAsiakasIkkuna implements Runnable {

    private JFrame frame;
    private final DataDeliver lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final NappulaLukko lukko;

    public LisaaAsiakasIkkuna(DataDeliver lataaja, AsiakkaatTaulukko taulukko, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.lukko = lukko;
    }

    @Override
    public void run() {
        lukko.lukitse();

        frame = new JFrame("Lisää asiakas");
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

        JLabel emailTeksti = new JLabel("Sähköposti:");
        JTextField emailKentta = new JTextField();

        ResultSet rs = lataaja.getDbc().executeQuery("select max(asiakasnumero) from Asiakas");
        String anro = "";
        try {
            rs.first();
            if (rs.getInt(1) == 0) {
                anro = "1000";
            } else {
                anro = Integer.toString(rs.getInt(1) + 1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
        }

        JLabel asiakasnumeroTeksti = new JLabel("Asiakasnumero (ei etunollia ja pit. väh. kaksi):");
        JTextField asiakasnumeroKentta = new JTextField(anro);

        JLabel laskujaLahetettyTeksti = new JLabel("Laskuja lähetetty:");
        JTextField laskujaLahetettyKentta = new JTextField();

        JButton lisaa = new JButton("Lisää");
        lisaa.setAlignmentX(Component.CENTER_ALIGNMENT);
        LisaaAsiakasIkkunaLisaaKuuntelija kuuntelija = new LisaaAsiakasIkkunaLisaaKuuntelija(nimiKentta, katuosoiteKentta, postinumeroKentta, kaupunkiKentta, emailKentta, asiakasnumeroKentta, laskujaLahetettyKentta, lataaja, taulukko, frame, lukko);
        lisaa.addActionListener(kuuntelija);

        tiedotPanel.add(nimiTeksti);
        tiedotPanel.add(nimiKentta);
        tiedotPanel.add(katuosoiteTeksti);
        tiedotPanel.add(katuosoiteKentta);
        tiedotPanel.add(postinumeroTeksti);
        tiedotPanel.add(postinumeroKentta);
        tiedotPanel.add(kaupunkiTeksti);
        tiedotPanel.add(kaupunkiKentta);
        tiedotPanel.add(emailTeksti);
        tiedotPanel.add(emailKentta);
        tiedotPanel.add(asiakasnumeroTeksti);
        tiedotPanel.add(asiakasnumeroKentta);
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
