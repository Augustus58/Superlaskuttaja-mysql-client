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
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.AsiakkaatTaulukko;
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

    public MuokkaaAsiakastaIkkunaMuokkaaKuuntelija(JTextField nimiKentta, JTextField katuosoiteKentta, JTextField postinumeroKentta, JTextField kaupunkiKentta, JTextField asiakasnumeroKentta, JTextField laskujaLahetettyKentta, Lataaja lataaja, AsiakkaatTaulukko taulukko, JFrame frame) {
        this.nimiKentta = nimiKentta;
        this.katuosoiteKentta = katuosoiteKentta;
        this.postinumeroKentta = postinumeroKentta;
        this.asiakasnumeroKentta = asiakasnumeroKentta;
        this.laskujaLahetettyKentta = laskujaLahetettyKentta;
        this.kaupunkiKentta = kaupunkiKentta;
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {  
        Asiakas vanhaAsiakas = new Asiakas(taulukko.getValueString(taulukko.getKuuntelijaArvo(), 4),
                taulukko.getValueString(taulukko.getKuuntelijaArvo(), 0),
                taulukko.getValueString(taulukko.getKuuntelijaArvo(), 1),
                taulukko.getValueString(taulukko.getKuuntelijaArvo(), 2),
                taulukko.getValueString(taulukko.getKuuntelijaArvo(), 3),
                Integer.parseInt(taulukko.getValueString(taulukko.getKuuntelijaArvo(), 5)));
        
        Asiakas asiakas = new Asiakas(asiakasnumeroKentta.getText(), nimiKentta.getText(), katuosoiteKentta.getText(), postinumeroKentta.getText(), kaupunkiKentta.getText(), Integer.parseInt(laskujaLahetettyKentta.getText()));
        
        taulukko.getModel().removeRow(taulukko.getKuuntelijaArvo());
        taulukko.getModel().addRow(asiakas.getAsiakkaanTiedotTaulukossa());
        
        lataaja.getLadattuTietovarasto().poistaAsiakas(vanhaAsiakas);
        lataaja.getLadattuTietovarasto().getAsiakkaat().add(asiakas);
        
        suljeIkkuna();
        
    }
    
    private void suljeIkkuna() {
        frame.dispose();
    }
}
