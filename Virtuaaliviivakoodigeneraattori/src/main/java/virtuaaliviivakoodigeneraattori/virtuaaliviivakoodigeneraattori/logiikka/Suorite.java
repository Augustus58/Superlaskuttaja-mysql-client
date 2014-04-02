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
    private MerkkiJaMerkkijonoTarkistin tarkistin;

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
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
        paivitaLaskutettuTeksti();
        laskeAlv();
        laskeYht();
    }

    public Object[] suoritteenTiedotTaulukossa() {
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

    public Boolean getOnkoLaskutettu() {
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

    public Lasku getLasku() {
        if (onkoLaskutettu) {
            return lasku;
        }
        return null;
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public void setPvm(Date pvm) {
        this.pvm = pvm;
    }

    public void setMaara(Double maara) {
        this.maara = maara;
    }

    public void setMaaranYksikot(String maaranYksikot) {
        this.maaranYksikot = maaranYksikot;
    }

    public void setaHinta(Double aHinta) {
        this.aHinta = aHinta;
    }

    public void setAlvProsentti(Integer alvProsentti) {
        this.alvProsentti = alvProsentti;
    }
    
    public void setLasku(Lasku lasku) {
        this.lasku = lasku;
        onkoLaskutettu = true;
    }
    
    private void laskeAlv() {
        this.alv = (alvProsentti / 100.0) * (aHinta * maara);
    }

    private void laskeYht() {
        this.yht = (aHinta * maara) + alv;
    }

    public final void paivitaLaskutettuTeksti() {
        if (onkoLaskutettu) {
            laskutettuTeksti = "Laskulla " + lasku.getLaskunNumero().toString();
        } else {
            laskutettuTeksti = "Ei";
        }
    }

    public boolean onkoTiedotOikeanlaisetPaitsiPvm() {
        if (onkoKuvausOikeanlainen()
                && onkoMaaraOikeanlainen()
                && onkoMaaranYksikotOikeanlainen()
                && onkoAHintaOikeanlainen()
                && onkoAlvProsenttiOikeanlainen()){
            return true;
        }
        return false;
    }

    public boolean onkoKuvausOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(kuvaus));
    }

    public boolean onkoMaaraOikeanlainen() {
        if (maara > 0) {
            return true;
        }
        return false;
    }

    public boolean onkoMaaranYksikotOikeanlainen() {
        return (!tarkistin.onkoMerkkijonoTyhjaTaiKoostuukoSeValilyonneista(maaranYksikot));
    }

    public boolean onkoAHintaOikeanlainen() {
        if (aHinta > 0) {
            return true;
        }
        return false;
    }

    public boolean onkoAlvProsenttiOikeanlainen() {
        if (alvProsentti >= 0 && alvProsentti <= 100) {
            return true;
        }
        return false;
    }
}
