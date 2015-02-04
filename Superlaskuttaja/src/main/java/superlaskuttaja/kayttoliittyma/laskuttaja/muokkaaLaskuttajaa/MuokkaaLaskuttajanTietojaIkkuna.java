/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskuttaja.muokkaaLaskuttajaa;

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
import superlaskuttaja.kayttoliittyma.laskuttaja.LaskuttajaOsioJPanel;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class MuokkaaLaskuttajanTietojaIkkuna implements Runnable {
    
    private JFrame frame;
    private final DataDeliver lataaja;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel panel1;
    
    public MuokkaaLaskuttajanTietojaIkkuna(DataDeliver lataaja, NappulaLukko lukko, LaskuttajaOsioJPanel panel) {
        this.lataaja = lataaja;
        this.lukko = lukko;
        this.panel1 = panel;
    }
    
    @Override
    public void run() {
        lukko.lukitse();
        
        frame = new JFrame("Muokkaa laskuttajan tietoja");
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
        GridLayout tiedotLayout = new GridLayout(0, 4);
        tiedotLayout.setVgap(10);
        tiedotLayout.setHgap(10);
        tiedotPanel.setLayout(tiedotLayout);
        
        ResultSet st = lataaja.getDbc().executeQuery("select distinct nimi, katuosoite, postinumero, kaupunki, yrityksenNimi, alvTunniste, tilinumero, tilinumeronPankki, tilinumeronSwiftBic, puhelinnumero, sahkopostiOsoite, laskujaLahetetty\n"
                + "from Laskuttaja\n"
                + "where versio = (select max(versio) from Laskuttaja)\n"
                + "");
        
        try {
            st.first();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
        }
        
        JTextField nimiKentta = null;
        JTextField katuosoiteKentta = null;
        JTextField postinumeroKentta = null;
        JTextField kaupunkiKentta = null;
        JTextField yritykseNimiKentta = null;
        JTextField alvTunnisteKentta = null;
        JTextField tilinumeroKentta = null;
        JTextField tilinumeronPankkiKentta = null;
        JTextField tilinumeronSwiftBicKentta = null;
        JTextField puhelinnumeroKentta = null;
        JTextField sahkopostiKentta = null;
        JTextField laskujaLahetettyKentta = null;
        JLabel nimiTeksti1 = null;
        JLabel nimiTeksti2 = null;
        JLabel nimiTeksti = null;
        JLabel katuosoiteTeksti1 = null;
        JLabel katuosoiteTeksti2 = null;
        JLabel katuosoiteTeksti = null;
        JLabel postinumeroTeksti1 = null;
        JLabel postinumeroTeksti2 = null;
        JLabel postinumeroTeksti = null;
        JLabel kaupunkiTeksti1 = null;
        JLabel kaupunkiTeksti2 = null;
        JLabel kaupunkiTeksti = null;
        JLabel yritykseNimiTeksti1 = null;
        JLabel yritykseNimiTeksti2 = null;
        JLabel yritykseNimiTeksti = null;
        JLabel alvTunnisteTeksti1 = null;
        JLabel alvTunnisteTeksti2 = null;
        JLabel alvTunnisteTeksti = null;
        JLabel tilinumeroTeksti1 = null;
        JLabel tilinumeroTeksti2 = null;
        JLabel tilinumeroTeksti = null;
        JLabel tilinumeronPankkiTeksti1 = null;
        JLabel tilinumeronPankkiTeksti2 = null;
        JLabel tilinumeronPankkiTeksti = null;
        JLabel tilinumeronSwiftBicTeksti1 = null;
        JLabel tilinumeronSwiftBicTeksti2 = null;
        JLabel tilinumeronSwiftBicTeksti = null;
        JLabel puhelinnumeroTeksti1 = null;
        JLabel puhelinnumeroTeksti2 = null;
        JLabel puhelinnumeroTeksti = null;
        JLabel sahkopostiTeksti1 = null;
        JLabel sahkopostiTeksti2 = null;
        JLabel sahkopostiTeksti = null;
        JLabel laskujaLahetettyTeksti1 = null;
        JLabel laskujaLahetettyTeksti2 = null;
        JLabel laskujaLahetettyTeksti = null;
        
        try {
            nimiTeksti1 = new JLabel("Vanha nimi:");
            nimiTeksti2 = new JLabel(st.getString(1));
            nimiTeksti = new JLabel("Uusi nimi:");
            nimiKentta = new JTextField(st.getString(1));
            
            katuosoiteTeksti1 = new JLabel("Vanha katuosoite:");
            katuosoiteTeksti2 = new JLabel(st.getString(2));
            katuosoiteTeksti = new JLabel("Uusi katuosoite:");
            katuosoiteKentta = new JTextField(st.getString(2));
            
            postinumeroTeksti1 = new JLabel("Vanha postinumero:");
            postinumeroTeksti2 = new JLabel(st.getString(3));
            postinumeroTeksti = new JLabel("Uusi postinumero:");
            postinumeroKentta = new JTextField(st.getString(3));
            
            kaupunkiTeksti1 = new JLabel("Vanha kaupunki:");
            kaupunkiTeksti2 = new JLabel(st.getString(4));
            kaupunkiTeksti = new JLabel("Uusi kaupunki");
            kaupunkiKentta = new JTextField(st.getString(4));
            
            yritykseNimiTeksti1 = new JLabel("Vanha yrityksen nimi:");
            yritykseNimiTeksti2 = new JLabel(st.getString(5));
            yritykseNimiTeksti = new JLabel("Uusi yrityksen nimi:");
            yritykseNimiKentta = new JTextField(st.getString(5));
            
            alvTunnisteTeksti1 = new JLabel("Vanha alv-tunniste:");
            alvTunnisteTeksti2 = new JLabel(st.getString(6));
            alvTunnisteTeksti = new JLabel("Uusi alv-tunniste:");
            alvTunnisteKentta = new JTextField(st.getString(6));
            
            tilinumeroTeksti1 = new JLabel("Vanha tilinumero:");
            tilinumeroTeksti2 = new JLabel(st.getString(7));
            tilinumeroTeksti = new JLabel("Uusi tilinumero:");
            tilinumeroKentta = new JTextField(st.getString(7));
            
            tilinumeronPankkiTeksti1 = new JLabel("Vanha tilinumeron pankki:");
            tilinumeronPankkiTeksti2 = new JLabel(st.getString(8));
            tilinumeronPankkiTeksti = new JLabel("Uusi tilinumeron pankki:");
            tilinumeronPankkiKentta = new JTextField(st.getString(8));
            
            tilinumeronSwiftBicTeksti1 = new JLabel("Vanha tilinumeron Swift/BIC:");
            tilinumeronSwiftBicTeksti2 = new JLabel(st.getString(9));
            tilinumeronSwiftBicTeksti = new JLabel("Uusi tilinumeron Swift/BIC:");
            tilinumeronSwiftBicKentta = new JTextField(st.getString(9));
            
            puhelinnumeroTeksti1 = new JLabel("Vanha puhelinnumero:");
            puhelinnumeroTeksti2 = new JLabel(st.getString(10));
            puhelinnumeroTeksti = new JLabel("Uusi puhelinnumero:");
            puhelinnumeroKentta = new JTextField(st.getString(10));
            
            sahkopostiTeksti1 = new JLabel("Vanha sähköposti:");
            sahkopostiTeksti2 = new JLabel(st.getString(11));
            sahkopostiTeksti = new JLabel("Uusi sähköposti:");
            sahkopostiKentta = new JTextField(st.getString(11));
            
            laskujaLahetettyTeksti1 = new JLabel("Vanha laskuja lähetetty yht:");
            laskujaLahetettyTeksti2 = new JLabel(Integer.toString(st.getInt(12)));
            laskujaLahetettyTeksti = new JLabel("Uusi laskuja lähetetty yht:");
            laskujaLahetettyKentta = new JTextField(Integer.toString(st.getInt(12)));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
        }
        
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
