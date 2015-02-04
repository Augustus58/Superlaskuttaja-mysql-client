/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.muokkaa;

import superlaskuttaja.kayttoliittyma.suoritteet.lisaaValitusta.*;
import superlaskuttaja.kayttoliittyma.suoritteet.lisaa.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import superlaskuttaja.logiikka.MerkkiJaMerkkijonoTarkistin;

/**
 *
 * @author Augustus58
 */
public class MuokkaaValittuaIkkunaMaaraKenttaKuuntelija implements DocumentListener {

    JTextField aloitusAikaKentta;
    JTextField lopetusAikaKentta;
    JTextField maaraKentta;
    JTextField maaranYksikotKentta;
    JRadioButton jrb1;
    JRadioButton jrb2;
    MerkkiJaMerkkijonoTarkistin tarkistin;
    private final DateFormat pvmFormaatti;

    public MuokkaaValittuaIkkunaMaaraKenttaKuuntelija() {
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
        this.pvmFormaatti = new SimpleDateFormat("dd.MM.yyyy HH.mm");
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
        if (jrb2.isSelected()) {
            try {
                double maara = Double.parseDouble(maaraKentta.getText());
                if (tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnnValiNnPisteNn(aloitusAikaKentta.getText())
                        && tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValiNnPisteNnValidi(aloitusAikaKentta.getText())) {
                    Date aloitus = null;
                    long aloitusMs = 0;
                    try {
                        aloitus = pvmFormaatti.parse(aloitusAikaKentta.getText());
                        aloitusMs = aloitus.getTime();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    long lisattavatMs;
                    long lopetusMs;
                    Date lopetus;
                    if (maaranYksikotKentta.getText().equals("h")) {
                        lisattavatMs = (long) (maara * 60 * 60 * 1000);
                        lopetusMs = aloitusMs + lisattavatMs;
                        lopetus = new Date(lopetusMs);
                        lopetusAikaKentta.setText(pvmFormaatti.format(lopetus));
                    } else if (maaranYksikotKentta.getText().equals("min")) {
                        lisattavatMs = (long) (maara * 60 * 1000);
                        lopetusMs = aloitusMs + lisattavatMs;
                        lopetus = new Date(lopetusMs);
                        lopetusAikaKentta.setText(pvmFormaatti.format(lopetus));
                    } else {
                    }
                }
            } catch (NumberFormatException | NullPointerException ex) {
            }
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

    public void setMaaranYksikotKentta(JTextField maaranYksikotKentta) {
        this.maaranYksikotKentta = maaranYksikotKentta;
    }

    public void setJrb1(JRadioButton jrb1) {
        this.jrb1 = jrb1;
    }

    public void setJrb2(JRadioButton jrb2) {
        this.jrb2 = jrb2;
    }
}
