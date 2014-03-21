/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LisaaAsiakas implements Runnable {

    private JFrame frame;
    private Lataaja lataaja;

    public LisaaAsiakas(Lataaja lataaja) {
        this.lataaja = lataaja;
    }

    @Override
    public void run() {
        frame = new JFrame("Lisää asiakas");
        frame.setPreferredSize(new Dimension(900, 600));

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(0, 2));
        
        JLabel nimiTeksti = new JLabel("Nimi:");       
        JTextField nimiKentta = new JTextField();      
        
        JLabel katuosoiteTeksti = new JLabel("Katuosoite:");       
        JTextField katuosoiteKentta = new JTextField();        
        
        JLabel postinumeroTeksti = new JLabel("Postinumero:");       
        JTextField postinumeroKentta = new JTextField();        
        
        JLabel kaupunkiTeksti = new JLabel("Kaupunki:");       
        JTextField kaupunkiKentta = new JTextField();        
        
        JLabel asiakasnumeroTeksti = new JLabel("Asiakasnumero:");     
        JTextField asiakasnumeroKentta = new JTextField();        
        
        JLabel laskujaLahetettyTeksti = new JLabel("Laskuja lähetetty:");
        JTextField laskujaLahetettyKentta = new JTextField();
                
        JButton lisaa = new JButton("Lisää");
        LisaaAsiakasLisaaKuuntelija kuuntelija = new LisaaAsiakasLisaaKuuntelija(nimiKentta, katuosoiteKentta, postinumeroKentta, kaupunkiKentta, asiakasnumeroKentta, laskujaLahetettyKentta, lataaja);
        lisaa.addActionListener(kuuntelija);
        
        container.add(nimiTeksti);
        container.add(nimiKentta);
        container.add(katuosoiteTeksti);
        container.add(katuosoiteKentta);
        container.add(postinumeroTeksti);
        container.add(postinumeroKentta);
        container.add(kaupunkiTeksti);
        container.add(kaupunkiKentta);
        container.add(asiakasnumeroTeksti);
        container.add(asiakasnumeroKentta);
        container.add(laskujaLahetettyTeksti);
        container.add(laskujaLahetettyKentta);
        container.add(lisaa);
        
    }

    public JFrame getFrame() {
        return frame;
    }

}
