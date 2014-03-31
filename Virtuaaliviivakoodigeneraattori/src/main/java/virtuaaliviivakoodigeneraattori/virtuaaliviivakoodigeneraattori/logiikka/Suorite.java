/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Augustus58
 */
public class Suorite {

    private Asiakas asiakas;
    private String kuvaus;
    private Date pvm;
    private Double maara;
    private String maaranYksikot;
    private Double aHinta;
    private Integer alvProsentti; // Esim 24.
    private Double alv;
    private Double yht;
    private Boolean onkoLaskutettu;
    private String laskutettuTeksti;
    private Lasku lasku;
    private SimpleDateFormat pvmFormaatti;

    public Suorite(Asiakas asiakas, String kuvaus, Date pvm, Double maara, String maaranYksikot, Double aHinta, Integer alvProsentti) {
        this.asiakas = asiakas;
        this.kuvaus = kuvaus;
        this.pvm = pvm;
        this.maara = maara;
        this.maaranYksikot = maaranYksikot;
        this.aHinta = aHinta;
        this.alvProsentti = alvProsentti;
        this.pvmFormaatti = new SimpleDateFormat("dd.MM.yyyy");
        this.onkoLaskutettu = false;
        paivitaLaskutettuTeksti();
        laskeAlv();
        laskeYht();
    }
    
    public Object[] getSuoritteenTiedotTaulukossa() {
        return (new Object[]{asiakas.getNimi(), kuvaus, pvmFormaatti.format(pvm), maara, maaranYksikot, aHinta, alvProsentti, alv, yht, laskutettuTeksti});
    }

    public Asiakas getAsiakas() {
        return asiakas;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public Date getPvm() {
        return pvm;
    }

    public Boolean isOnkoLaskutettu() {
        return onkoLaskutettu;
    }

    public SimpleDateFormat getPvmFormaatti() {
        return pvmFormaatti;
    }
    
    public Double getMaara() {
        return maara;
    }

    public String getMaaranYksikot() {
        return maaranYksikot;
    }

    public Double getaHinta() {
        return aHinta;
    }

    public Integer getAlvProsentti() {
        return alvProsentti;
    }

    public Double getAlv() {
        return alv;
    }

    public Double getYht() {
        return yht;
    }

    public String getLaskutettuTeksti() {
        return laskutettuTeksti;
    }
    
    private void laskeAlv() {
        this.alv = (alvProsentti / 100.0) * aHinta;
    }
    
    private void laskeYht() {
        this.yht = aHinta + alv;
    }
    
    public final void paivitaLaskutettuTeksti() {
        if (onkoLaskutettu) {
            laskutettuTeksti = "Laskulla " + lasku.getLaskunNumero().toString();
        } else {
            laskutettuTeksti = "Ei";
        }
    }

    public boolean onkoTiedotOikeanlaiset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
