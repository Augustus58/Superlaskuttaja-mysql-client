/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.lisaa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import superlaskuttaja.logiikka.MerkkiJaMerkkijonoTarkistin;

/**
 *
 * @author Augustus58
 */
public class LisaaLaskutIkkunaPaivaysKenttaKuuntelija implements DocumentListener {

    JTextField paivaysKentta;
    JTextField erapaivaKentta;
    JTextField maksuAikaKentta;
    MerkkiJaMerkkijonoTarkistin tarkistin;
    private final DateFormat pvmFormaatti;

    public LisaaLaskutIkkunaPaivaysKenttaKuuntelija() {
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
        this.pvmFormaatti = new SimpleDateFormat("dd.MM.yyyy");
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        paivita(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        paivita(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        paivita(e);
    }

    private void paivita(DocumentEvent e) {
        if (tarkistin.koostuukoMerkkijonoNumeroista(maksuAikaKentta.getText())) {
            if (tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn(paivaysKentta.getText())) {
                if (tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi(paivaysKentta.getText())) {
                    Calendar c = Calendar.getInstance();
                    Date paivays = null;
                    try {
                        paivays = pvmFormaatti.parse(paivaysKentta.getText());
                    } catch (ParseException ex) {
                        Logger.getLogger(LisaaLaskutIkkunaPaivaysKenttaKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    c.setTime(paivays);
                    c.add(Calendar.DATE, Integer.parseInt(maksuAikaKentta.getText()));
                    erapaivaKentta.setText(pvmFormaatti.format(c.getTime()));
                }
            }
        }
    }

    public void setPaivaysKentta(JTextField paivaysKentta) {
        this.paivaysKentta = paivaysKentta;
    }

    public void setErapaivaKentta(JTextField kentta) {
        this.erapaivaKentta = kentta;
    }

    public void setMaksuAikaKentta(JTextField maksuAikaKentta) {
        this.maksuAikaKentta = maksuAikaKentta;
    }
}
