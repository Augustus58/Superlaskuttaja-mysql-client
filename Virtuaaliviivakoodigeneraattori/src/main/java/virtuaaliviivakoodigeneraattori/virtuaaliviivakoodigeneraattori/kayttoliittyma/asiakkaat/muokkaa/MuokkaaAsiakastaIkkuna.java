/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.asiakkaat.muokkaa;

import java.awt.Component;
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
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.IkkunaKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.TaulukkoValintaKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class MuokkaaAsiakastaIkkuna implements Runnable {

    private JFrame frame;
    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public MuokkaaAsiakastaIkkuna(Lataaja lataaja, AsiakkaatTaulukko taulukko, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.kuuntelija = kuuntelija;
        this.lukko = lukko;
    }    

    @Override
    public void run() {
        lukko.lukitse();
        
        frame = new JFrame("Muokkaa asiakasta");
        frame.setLocation(130, 90);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        IkkunaKuuntelija ikkinaKuuntelija = new IkkunaKuuntelija(lukko);
        frame.addWindowListener(ikkinaKuuntelija);

        try {
            luoKomponentit(frame.getContentPane());
            
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
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
        
        JLabel nimiTeksti = new JLabel("Entinen nimi: ");
        JLabel nimiTekstiEntinen = new JLabel(taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 0).toString());
        JLabel nimiTekstiUusi = new JLabel("Uusi nimi: ");
        JTextField nimiKentta = new JTextField(taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 0).toString());
//        nimiKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel katuosoiteTeksti = new JLabel("Entinen katuosoite:");
        JLabel katuosoiteTekstiEntinen = new JLabel(taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 1).toString());
        JLabel katuosoiteTekstiUusi = new JLabel("Uusi katuosoite:");
        JTextField katuosoiteKentta = new JTextField(taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 1).toString());
//        nimiKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel postinumeroTeksti = new JLabel("Entinen postinumero:");
        JLabel postinumeroTekstiEntinen = new JLabel(taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 2).toString());
        JLabel postinumeroTekstiUusi = new JLabel("Uusi postinumero:");
        JTextField postinumeroKentta = new JTextField(taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 2).toString());
//        postinumeroKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel kaupunkiTeksti = new JLabel("Entinen kaupunki:"); 
        JLabel kaupunkiTekstiEntinen = new JLabel(taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 3).toString());
        JLabel kaupunkiTekstiUusi = new JLabel("Uusi kaupunki:");
        JTextField kaupunkiKentta = new JTextField(taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 3).toString());
//        kaupunkiKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel asiakasnumeroTeksti = new JLabel("Entinen asiakasnumero:");
        JLabel asiakasnumeroTekstiVanha = new JLabel(taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 4).toString());
        JLabel asiakasnumeroTekstiUusi = new JLabel("Uusi asiakasnumero:");
        JTextField asiakasnumeroKentta = new JTextField(taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 4).toString());
//        asiakasnumeroKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel laskujaLahetettyTeksti = new JLabel("Laskuja lähetetty entinen:");
        JLabel laskujaLahetettyTekstiVanha = new JLabel(taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 5).toString());
        JLabel laskujaLahetettyTekstiUusi = new JLabel("Laskuja lähetetty uusi:");
        JTextField laskujaLahetettyKentta = new JTextField(taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 5).toString());
//        laskujaLahetettyKentta.setPreferredSize(new Dimension(300, 0));
           
        JButton muokkaa = new JButton("Muokkaa");
        muokkaa.setAlignmentX( Component.CENTER_ALIGNMENT );
        MuokkaaAsiakastaIkkunaMuokkaaKuuntelija muokkaaAsiakastaKuuntelija = new MuokkaaAsiakastaIkkunaMuokkaaKuuntelija(nimiKentta, katuosoiteKentta, postinumeroKentta, kaupunkiKentta, asiakasnumeroKentta, laskujaLahetettyKentta, lataaja, taulukko, frame, kuuntelija, lukko);
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
        
        tiedotPanel.add(asiakasnumeroTeksti);
        tiedotPanel.add(asiakasnumeroTekstiVanha);
        tiedotPanel.add(asiakasnumeroTekstiUusi);
        tiedotPanel.add(asiakasnumeroKentta);
        
        tiedotPanel.add(laskujaLahetettyTeksti);
        tiedotPanel.add(laskujaLahetettyTekstiVanha);
        tiedotPanel.add(laskujaLahetettyTekstiUusi);
        tiedotPanel.add(laskujaLahetettyKentta);
        
        panel.add(tiedotPanel);
        panel.add(Box.createRigidArea(new Dimension(10,10)));
        panel.add(muokkaa);
        
        container.add(panel);
        
    }

    public JFrame getFrame() {
        return frame;
    }

}
