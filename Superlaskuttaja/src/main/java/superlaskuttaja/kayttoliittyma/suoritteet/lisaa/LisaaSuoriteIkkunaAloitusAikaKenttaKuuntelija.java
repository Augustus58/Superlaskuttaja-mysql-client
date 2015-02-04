/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.lisaa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import superlaskuttaja.logiikka.MerkkiJaMerkkijonoTarkistin;

/**
 *
 * @author Augustus58
 */
public class LisaaSuoriteIkkunaAloitusAikaKenttaKuuntelija implements DocumentListener {

    JTextField aloitusAikaKentta;
    JTextField lopetusAikaKentta;
    JTextField maaraKentta;
    JRadioButton jrb1;
    JRadioButton jrb2;
    MerkkiJaMerkkijonoTarkistin tarkistin;
    private final DateFormat pvmFormaatti;

    public LisaaSuoriteIkkunaAloitusAikaKenttaKuuntelija() {
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
        if (tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnnValiNnPisteNn(aloitusAikaKentta.getText())) {
            if (tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValiNnPisteNnValidi(aloitusAikaKentta.getText())) {
                lopetusAikaKentta.setEditable(true);
                lopetusAikaKentta.setText(aloitusAikaKentta.getText().substring(0, 10) + " ");
                jrb1.setEnabled(true);
                jrb2.setEnabled(true);
            }
        } else {
            lopetusAikaKentta.setEditable(false);
            lopetusAikaKentta.setText(null);
            jrb1.setEnabled(false);
            jrb2.setEnabled(false);
        }
    }

    public void setAloitusAikaKentta(JTextField aloitusAikaKentta) {
        this.aloitusAikaKentta = aloitusAikaKentta;
    }

    public void setLopetusAikaKentta(JTextField lopetusAikaKentta) {
        this.lopetusAikaKentta = lopetusAikaKentta;
    }

    public void setMaaraKentta(JTextField maaraKentta) {
        this.maaraKentta = maaraKentta;
    }

    public void setJrb1(JRadioButton jrb1) {
        this.jrb1 = jrb1;
    }

    public void setJrb2(JRadioButton jrb2) {
        this.jrb2 = jrb2;
    }
}
