/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.asiakkaat.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Asiakas;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LisaaAsiakasIkkunaLisaaKuuntelija implements ActionListener {

    private final JTextField nimiKentta;
    private final JTextField katuosoiteKentta;
    private final JTextField postinumeroKentta;
    private final JTextField kaupunkiKentta;
    private final JTextField asiakasnumeroKentta;
    private final JTextField laskujaLahetettyKentta;
    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final JFrame frame;
    private final NappulaLukko lukko;

    public LisaaAsiakasIkkunaLisaaKuuntelija(JTextField nimiKentta, JTextField katuosoiteKentta, JTextField postinumeroKentta, JTextField kaupunkiKentta, JTextField asiakasnumeroKentta, JTextField laskujaLahetettyKentta, Lataaja lataaja, AsiakkaatTaulukko taulukko, JFrame frame, NappulaLukko lukko) {
        this.nimiKentta = nimiKentta;
        this.katuosoiteKentta = katuosoiteKentta;
        this.postinumeroKentta = postinumeroKentta;
        this.asiakasnumeroKentta = asiakasnumeroKentta;
        this.laskujaLahetettyKentta = laskujaLahetettyKentta;
        this.kaupunkiKentta = kaupunkiKentta;
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.frame = frame;
        this.lukko = lukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Asiakas asiakas = new Asiakas(asiakasnumeroKentta.getText(), nimiKentta.getText(), katuosoiteKentta.getText(), postinumeroKentta.getText(), kaupunkiKentta.getText(), Integer.parseInt(laskujaLahetettyKentta.getText()));
            
            if (!asiakas.onkoTiedotOikeanlaiset()) {
                throw new IllegalArgumentException("Jokin syöte on virheellinen.");
            }
                                             
            lataaja.getLadattuTietovarasto().getAsiakkaat().add(asiakas);
            taulukko.addAsiakkaatTaulukkoRivi(asiakas);
            suljeIkkuna();
        } catch (Exception e) {
            LisaaAsiakasIkkunaLisaaKuuntelijaPoikkeusIkkuna poikkeusIkkuna = new LisaaAsiakasIkkunaLisaaKuuntelijaPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }
        
    }
    
    private void suljeIkkuna() {
        frame.dispose();
        lukko.avaa();
    }
}
