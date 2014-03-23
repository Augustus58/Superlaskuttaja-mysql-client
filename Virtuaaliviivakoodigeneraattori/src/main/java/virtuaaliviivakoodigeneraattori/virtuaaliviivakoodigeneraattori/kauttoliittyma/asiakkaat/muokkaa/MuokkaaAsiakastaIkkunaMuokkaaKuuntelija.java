/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.muokkaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.AsiakkaatTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.AsiakkaatTaulukkoValintaKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.NappulaLukko;
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
    private final AsiakkaatTaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public MuokkaaAsiakastaIkkunaMuokkaaKuuntelija(JTextField nimiKentta, JTextField katuosoiteKentta, JTextField postinumeroKentta, JTextField kaupunkiKentta, JTextField asiakasnumeroKentta, JTextField laskujaLahetettyKentta, Lataaja lataaja, AsiakkaatTaulukko taulukko, JFrame frame, AsiakkaatTaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
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
            Asiakas vanhaAsiakas = new Asiakas(taulukko.getValueString(kuuntelija.getArvoModel(), 4),
                    taulukko.getValueString(kuuntelija.getArvoModel(), 0),
                    taulukko.getValueString(kuuntelija.getArvoModel(), 1),
                    taulukko.getValueString(kuuntelija.getArvoModel(), 2),
                    taulukko.getValueString(kuuntelija.getArvoModel(), 3),
                    Integer.parseInt(taulukko.getValueString(kuuntelija.getArvoModel(), 5)));
            
            Asiakas asiakas = new Asiakas(asiakasnumeroKentta.getText(), nimiKentta.getText(), katuosoiteKentta.getText(), postinumeroKentta.getText(), kaupunkiKentta.getText(), Integer.parseInt(laskujaLahetettyKentta.getText()));
            
            if (!asiakas.onkoTiedotOikeanlaiset()) {
                throw new IllegalArgumentException("Jokin syöte on virheellinen.");
            }
            
            taulukko.getModel().insertRow(kuuntelija.getArvoModel(), asiakas.getAsiakkaanTiedotTaulukossa());
            taulukko.getModel().removeRow(kuuntelija.getArvoModel() + 1);
            
            lataaja.getLadattuTietovarasto().poistaAsiakas(vanhaAsiakas);
            lataaja.getLadattuTietovarasto().getAsiakkaat().add(asiakas);
            
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
