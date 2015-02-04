/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.lisaa;

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
public class LisaaSuoriteIkkunaLopetusAikaKenttaKuuntelija implements DocumentListener {
    
    JTextField aloitusAikaKentta;
    JTextField lopetusAikaKentta;
    JTextField maaraKentta;
    JTextField maaranYksikotKentta;
    JRadioButton jrb1;
    JRadioButton jrb2;
    MerkkiJaMerkkijonoTarkistin tarkistin;
    private final DateFormat pvmFormaatti;

    public LisaaSuoriteIkkunaLopetusAikaKenttaKuuntelija() {
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
        if (jrb1.isSelected()) {
            if (tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnnValiNnPisteNn(lopetusAikaKentta.getText())) {
                if (tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValiNnPisteNnValidi(lopetusAikaKentta.getText())) {
                    if (maaranYksikotKentta.getText().equals("h")) {
                        Date aloitus = null;
                        Date lopetus = null;
                        try {
                            aloitus = pvmFormaatti.parse(aloitusAikaKentta.getText());
                            lopetus = pvmFormaatti.parse(lopetusAikaKentta.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        if (aloitus.after(lopetus)) {
                        } else {
                            long diff = lopetus.getTime() - aloitus.getTime();
                            double tunneissa = diff / (1.0 * 60 * 60 * 1000);
                            maaraKentta.setText(Double.toString(tunneissa));
                        }
                    } else if (maaranYksikotKentta.getText().equals("min")) {
                        Date aloitus = null;
                        Date lopetus = null;
                        try {
                            aloitus = pvmFormaatti.parse(aloitusAikaKentta.getText());
                            lopetus = pvmFormaatti.parse(lopetusAikaKentta.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        if (aloitus.after(lopetus)) {
                        } else {
                            long diff = lopetus.getTime() - aloitus.getTime();
                            double minuuteissa = diff / (1.0 * 60 * 1000);
                            maaraKentta.setText(Double.toString(minuuteissa));
                        }
                    } else {
                    }
                }
            } else {
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
