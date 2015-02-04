/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LisaaLaskutIkkunaVastaanottajaComboBoxKuuntelija implements ActionListener {

    private Integer valinta;
    private LisaaLaskuIkkunaSuoritteetList list;
    private JComboBox vastaanottajaComboBox;
    private JComboBox tilaajaComboBox;
    private LisaaLaskuIkkunaTilaajaComboBoxKuuntelija tilaajaComboBoxkuuntelija;
    private final DataDeliver lataaja;
    private final DateFormat pvmFormaatti1;
    private final DateFormat pvmFormaatti3;
    private boolean paivitysKesken;

    public LisaaLaskutIkkunaVastaanottajaComboBoxKuuntelija(DataDeliver lataaja) {
        this.valinta = -5;
        this.lataaja = lataaja;
        this.pvmFormaatti1 = new SimpleDateFormat("dd.MM.yyyy");
        this.pvmFormaatti3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.paivitysKesken = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        valinta = (Integer) comboBox.getSelectedIndex();
        paivitaSuoritelista();
    }

    public void paivitaSuoritelista() {
        if (!paivitysKesken) {
            list.getModel().removeAllElements();
            String tilaaja = (String) tilaajaComboBox.getItemAt(tilaajaComboBoxkuuntelija.getValinta());
            int indeksi = 0;
            for (int i = tilaaja.length() - 1; i > -1; i--) {
                if (tilaaja.charAt(i) == ' ') {
                    indeksi = i + 1;
                    break;
                }
            }
            tilaaja = tilaaja.substring(indeksi);
            
            String vastaanottaja = (String) vastaanottajaComboBox.getItemAt(getValinta());
            indeksi = 0;
            for (int i = vastaanottaja.length() - 1; i > -1; i--) {
                if (vastaanottaja.charAt(i) == ' ') {
                    indeksi = i + 1;
                    break;
                }
            }
            vastaanottaja = vastaanottaja.substring(indeksi);
            
            ResultSet rs = lataaja.getDbc().executeQuery("select distinct alkuaika, kuvaus, suoritteenNumero\n"
                    + "from Suorite\n"
                    + "where tilaaja = " + tilaaja + "\n"
                    + "and vastaanottaja = " + vastaanottaja + "\n"
                    + "and lasku is null");
            
            ArrayList<String> al1 = new ArrayList<>();
            ArrayList<String> al2 = new ArrayList<>();
            try {
                java.sql.Timestamp alkuaika;
                while (rs.next()) {
                    alkuaika = rs.getTimestamp(1);
                    al1.add(pvmFormaatti1.format(pvmFormaatti3.parse(alkuaika.toString())) + " " + rs.getString(2));
                    al2.add(rs.getString(3));
                }
                String[] listaaVastSuoritteet = new String[al1.size()];
                for (int i = 0; i < al1.size(); i++) {
                    list.getModel().addElement(al1.get(i));
                    listaaVastSuoritteet[i] = al2.get(i);
                }
                list.setListaaVastSuoritteet(listaaVastSuoritteet);
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getSQLState());
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public Integer getValinta() {
        if (valinta == -5) {
            return 0;
        }
        return valinta;
    }

    public void setList(LisaaLaskuIkkunaSuoritteetList list) {
        this.list = list;
    }

    public void setVastaanottajaComboBox(JComboBox vastaanottajaComboBox) {
        this.vastaanottajaComboBox = vastaanottajaComboBox;
    }

    public void setTilaajaComboBox(JComboBox tilaajaComboBox) {
        this.tilaajaComboBox = tilaajaComboBox;
    }

    public void setTilaajaComboBoxkuuntelija(LisaaLaskuIkkunaTilaajaComboBoxKuuntelija tilaajaComboBoxkuuntelija) {
        this.tilaajaComboBoxkuuntelija = tilaajaComboBoxkuuntelija;
    }

    public void setPaivitysKesken(boolean paivitysKesken) {
        this.paivitysKesken = paivitysKesken;
    }

    public boolean isPaivitysKesken() {
        return paivitysKesken;
    }

}
