/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.muokkaa;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.AsiakkaatTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class MuokkaaAsiakastaIkkuna implements Runnable {

    private JFrame frame;
    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;

    public MuokkaaAsiakastaIkkuna(Lataaja lataaja, AsiakkaatTaulukko taulukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
    }

    @Override
    public void run() {
        frame = new JFrame("Muokkaa asiakasta");
        frame.setLocation(130, 90);
        
        frame.setResizable(false);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        JPanel panel = new JPanel();
        
        GridLayout layout = new GridLayout(0, 4);
        layout.setVgap(10);
        layout.setHgap(10);
        
        panel.setLayout(layout);
        
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel nimiTeksti = new JLabel("Entinen nimi: ");
        JLabel nimiTekstiEntinen = new JLabel(taulukko.getModel().getValueAt(taulukko.getKuuntelijaArvo(), 0).toString());
        JLabel nimiTekstiUusi = new JLabel("Uusi nimi: ");
        JTextField nimiKentta = new JTextField(taulukko.getModel().getValueAt(taulukko.getKuuntelijaArvo(), 0).toString());
//        nimiKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel katuosoiteTeksti = new JLabel("Entinen katuosoite:");
        JLabel katuosoiteTekstiEntinen = new JLabel(taulukko.getModel().getValueAt(taulukko.getKuuntelijaArvo(), 1).toString());
        JLabel katuosoiteTekstiUusi = new JLabel("Uusi katuosoite:");
        JTextField katuosoiteKentta = new JTextField(taulukko.getModel().getValueAt(taulukko.getKuuntelijaArvo(), 1).toString());
//        nimiKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel postinumeroTeksti = new JLabel("Entinen postinumero:");
        JLabel postinumeroTekstiEntinen = new JLabel(taulukko.getModel().getValueAt(taulukko.getKuuntelijaArvo(), 2).toString());
        JLabel postinumeroTekstiUusi = new JLabel("Uusi postinumero:");
        JTextField postinumeroKentta = new JTextField(taulukko.getModel().getValueAt(taulukko.getKuuntelijaArvo(), 2).toString());
//        postinumeroKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel kaupunkiTeksti = new JLabel("Entinen kaupunki:"); 
        JLabel kaupunkiTekstiEntinen = new JLabel(taulukko.getModel().getValueAt(taulukko.getKuuntelijaArvo(), 3).toString());
        JLabel kaupunkiTekstiUusi = new JLabel("Uusi kaupunki:");
        JTextField kaupunkiKentta = new JTextField(taulukko.getModel().getValueAt(taulukko.getKuuntelijaArvo(), 3).toString());
//        kaupunkiKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel asiakasnumeroTeksti = new JLabel("Entinen asiakasnumero:");
        JLabel asiakasnumeroTekstiVanha = new JLabel(taulukko.getModel().getValueAt(taulukko.getKuuntelijaArvo(), 4).toString());
        JLabel asiakasnumeroTekstiUusi = new JLabel("Uusi asiakasnumero:");
        JTextField asiakasnumeroKentta = new JTextField(taulukko.getModel().getValueAt(taulukko.getKuuntelijaArvo(), 4).toString());
//        asiakasnumeroKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel laskujaLahetettyTeksti = new JLabel("Laskuja lähetetty entinen:");
        JLabel laskujaLahetettyTekstiVanha = new JLabel(taulukko.getModel().getValueAt(taulukko.getKuuntelijaArvo(), 5).toString());
        JLabel laskujaLahetettyTekstiUusi = new JLabel("Laskuja lähetetty uusi:");
        JTextField laskujaLahetettyKentta = new JTextField(taulukko.getModel().getValueAt(taulukko.getKuuntelijaArvo(), 5).toString());
//        laskujaLahetettyKentta.setPreferredSize(new Dimension(300, 0));
           
        JButton lisaa = new JButton("Muokkaa");
        MuokkaaAsiakastaIkkunaMuokkaaKuuntelija kuuntelija = new MuokkaaAsiakastaIkkunaMuokkaaKuuntelija(nimiKentta, katuosoiteKentta, postinumeroKentta, kaupunkiKentta, asiakasnumeroKentta, laskujaLahetettyKentta, lataaja, taulukko, frame);
        lisaa.addActionListener(kuuntelija);
        
        panel.add(nimiTeksti);
        panel.add(nimiTekstiEntinen);
        panel.add(nimiTekstiUusi);
        panel.add(nimiKentta);
        
        panel.add(katuosoiteTeksti);
        panel.add(katuosoiteTekstiEntinen);
        panel.add(katuosoiteTekstiUusi);
        panel.add(katuosoiteKentta);
        
        panel.add(postinumeroTeksti);
        panel.add(postinumeroTekstiEntinen);
        panel.add(postinumeroTekstiUusi);
        panel.add(postinumeroKentta);
        
        panel.add(kaupunkiTeksti);
        panel.add(kaupunkiTekstiEntinen);
        panel.add(kaupunkiTekstiUusi);
        panel.add(kaupunkiKentta);
        
        panel.add(asiakasnumeroTeksti);
        panel.add(asiakasnumeroTekstiVanha);
        panel.add(asiakasnumeroTekstiUusi);
        panel.add(asiakasnumeroKentta);
        
        panel.add(laskujaLahetettyTeksti);
        panel.add(laskujaLahetettyTekstiVanha);
        panel.add(laskujaLahetettyTekstiUusi);
        panel.add(laskujaLahetettyKentta);
        
        panel.add(Box.createRigidArea(new Dimension(20,0)));
        panel.add(lisaa);
        
        container.add(panel);
        
    }

    public JFrame getFrame() {
        return frame;
    }

}
