/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.asiakkaat.muokkaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.TaulukkoValintaKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Asiakas;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class MuokkaaAsiakastaIkkunaMuokkaaKuuntelija implements ActionListener {

    private final JTextField nimiKentta;
    private final JTextField katuosoiteKentta;
    private final JTextField postinumeroKentta;
    private final JTextField kaupunkiKentta;
    private final JTextField asiakasnumeroKentta;
    private final JTextField laskujaLahetettyKentta;
    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final JFrame frame;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public MuokkaaAsiakastaIkkunaMuokkaaKuuntelija(JTextField nimiKentta, JTextField katuosoiteKentta, JTextField postinumeroKentta, JTextField kaupunkiKentta, JTextField asiakasnumeroKentta, JTextField laskujaLahetettyKentta, Lataaja lataaja, AsiakkaatTaulukko taulukko, JFrame frame, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
        this.nimiKentta = nimiKentta;
        this.katuosoiteKentta = katuosoiteKentta;
        this.postinumeroKentta = postinumeroKentta;
        this.kaupunkiKentta = kaupunkiKentta;
        this.asiakasnumeroKentta = asiakasnumeroKentta;
        this.laskujaLahetettyKentta = laskujaLahetettyKentta;
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.frame = frame;
        this.kuuntelija = kuuntelija;
        this.lukko = lukko;
    }    

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Asiakas vanhaAsiakas = new Asiakas(taulukko.getValueString(kuuntelija.getPaivitettyArvo(), 4),
                    taulukko.getValueString(kuuntelija.getPaivitettyArvo(), 0),
                    taulukko.getValueString(kuuntelija.getPaivitettyArvo(), 1),
                    taulukko.getValueString(kuuntelija.getPaivitettyArvo(), 2),
                    taulukko.getValueString(kuuntelija.getPaivitettyArvo(), 3),
                    Integer.parseInt(taulukko.getValueString(kuuntelija.getPaivitettyArvo(), 5)));
            
            Asiakas asiakas = new Asiakas(asiakasnumeroKentta.getText(), nimiKentta.getText(), katuosoiteKentta.getText(), postinumeroKentta.getText(), kaupunkiKentta.getText(), Integer.parseInt(laskujaLahetettyKentta.getText()));
            
            if (!asiakas.onkoTiedotOikeanlaiset()) {
                throw new IllegalArgumentException("Jokin syöte on virheellinen.");
            }

            lataaja.getLadattuTietovarasto().getAsiakkaat().remove(kuuntelija.getPaivitettyArvo().intValue());
            lataaja.getLadattuTietovarasto().getAsiakkaat().add(kuuntelija.getPaivitettyArvo(), asiakas);
            taulukko.getModel().insertRow(kuuntelija.getPaivitettyArvo(), asiakas.asiakkaanTiedotTaulukossa());
            taulukko.getModel().removeRow(kuuntelija.getPaivitettyArvo() + 1);
            suljeIkkuna();
        } catch (Exception e) {
            MuokkaaAsiakastaIkkunaMuokkaaKuuntelijaPoikkeusIkkuna poikkeusIkkuna = new MuokkaaAsiakastaIkkunaMuokkaaKuuntelijaPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }
        
    }
    
    private void suljeIkkuna() {
        frame.dispose();
        lukko.avaa();
    }
}
