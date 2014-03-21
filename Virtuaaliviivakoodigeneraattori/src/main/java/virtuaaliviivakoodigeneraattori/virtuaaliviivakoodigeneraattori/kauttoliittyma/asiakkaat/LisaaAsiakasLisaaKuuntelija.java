/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Asiakas;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LisaaAsiakasLisaaKuuntelija implements ActionListener {

    private final JTextField nimiKentta;
    private final JTextField katuosoiteKentta;
    private final JTextField postinumeroKentta;
    private final JTextField kaupunkiKentta;
    private final JTextField asiakasnumeroKentta;
    private final JTextField laskujaLahetettyKentta;
    private Lataaja lataaja;

    public LisaaAsiakasLisaaKuuntelija(JTextField nimiKentta, JTextField katuosoiteKentta, JTextField postinumeroKentta, JTextField kaupunkiKentta, JTextField asiakasnumeroKentta, JTextField laskujaLahetettyKentta, Lataaja lataaja) {
        this.nimiKentta = nimiKentta;
        this.katuosoiteKentta = katuosoiteKentta;
        this.postinumeroKentta = postinumeroKentta;
        this.asiakasnumeroKentta = asiakasnumeroKentta;
        this.laskujaLahetettyKentta = laskujaLahetettyKentta;
        this.kaupunkiKentta = kaupunkiKentta;
        this.lataaja = lataaja;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Asiakas asiakas = new Asiakas(asiakasnumeroKentta.getText(), nimiKentta.getText(), katuosoiteKentta.getText(), postinumeroKentta.getText(), kaupunkiKentta.getText(), Integer.parseInt(laskujaLahetettyKentta.getText()));
        lataaja.getLadattuTietovarasto().getAsiakkaat().add(asiakas);
        
    }
}
