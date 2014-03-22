/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.lisaa;

import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.lisaa.LisaaAsiakasIkkunaLisaaKuuntelija;
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
public class LisaaAsiakasIkkuna implements Runnable {

    private JFrame frame;
    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;

    public LisaaAsiakasIkkuna(Lataaja lataaja, AsiakkaatTaulukko taulukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
    }

    @Override
    public void run() {
        frame = new JFrame("Lisää asiakas");
        frame.setLocation(130, 90);
        
        frame.setResizable(false);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        JPanel panel = new JPanel();
        
        GridLayout layout = new GridLayout(0, 2);
        layout.setVgap(10);
        layout.setHgap(10);
        
        panel.setLayout(layout);
        
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel nimiTeksti = new JLabel("Nimi:");
        JTextField nimiKentta = new JTextField();
        nimiKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel katuosoiteTeksti = new JLabel("Katuosoite:");       
        JTextField katuosoiteKentta = new JTextField();
        nimiKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel postinumeroTeksti = new JLabel("Postinumero:");       
        JTextField postinumeroKentta = new JTextField();
        postinumeroKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel kaupunkiTeksti = new JLabel("Kaupunki:");       
        JTextField kaupunkiKentta = new JTextField();
        kaupunkiKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel asiakasnumeroTeksti = new JLabel("Asiakasnumero:");     
        JTextField asiakasnumeroKentta = new JTextField();
        asiakasnumeroKentta.setPreferredSize(new Dimension(300, 0));
        
        JLabel laskujaLahetettyTeksti = new JLabel("Laskuja lähetetty:");
        JTextField laskujaLahetettyKentta = new JTextField();
        laskujaLahetettyKentta.setPreferredSize(new Dimension(300, 0));
           
        JButton lisaa = new JButton("Lisää");
        LisaaAsiakasIkkunaLisaaKuuntelija kuuntelija = new LisaaAsiakasIkkunaLisaaKuuntelija(nimiKentta, katuosoiteKentta, postinumeroKentta, kaupunkiKentta, asiakasnumeroKentta, laskujaLahetettyKentta, lataaja, taulukko, frame);
        lisaa.addActionListener(kuuntelija);
        
        panel.add(nimiTeksti);
        panel.add(nimiKentta);
        panel.add(katuosoiteTeksti);
        panel.add(katuosoiteKentta);
        panel.add(postinumeroTeksti);
        panel.add(postinumeroKentta);
        panel.add(kaupunkiTeksti);
        panel.add(kaupunkiKentta);
        panel.add(asiakasnumeroTeksti);
        panel.add(asiakasnumeroKentta);
        panel.add(laskujaLahetettyTeksti);
        panel.add(laskujaLahetettyKentta);
        panel.add(Box.createRigidArea(new Dimension(20,0)));
        panel.add(lisaa);
        
        container.add(panel);
        
    }

    public JFrame getFrame() {
        return frame;
    }

}
