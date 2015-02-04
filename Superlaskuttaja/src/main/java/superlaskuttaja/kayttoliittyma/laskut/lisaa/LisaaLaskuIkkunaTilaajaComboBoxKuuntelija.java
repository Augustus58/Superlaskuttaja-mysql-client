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
import java.util.ArrayList;
import javax.swing.JComboBox;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LisaaLaskuIkkunaTilaajaComboBoxKuuntelija implements ActionListener {

    private final DataDeliver lataaja;
    private Integer valinta;
    private JComboBox vastaanottajaComboBox;
    private JComboBox tilaajaComboBox;
    private LisaaLaskutIkkunaVastaanottajaComboBoxKuuntelija vastaanottajaComboBoxKuuntelija;

    public LisaaLaskuIkkunaTilaajaComboBoxKuuntelija(DataDeliver lataaja) {
        this.lataaja = lataaja;
        this.valinta = -5;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        valinta = (Integer) comboBox.getSelectedIndex();
        paivitaVastaanottajaComboBox();
    }

    public void paivitaVastaanottajaComboBox() {
        vastaanottajaComboBoxKuuntelija.setPaivitysKesken(true);
        String tilaaja = (String) tilaajaComboBox.getItemAt(getValinta());
        int indeksi = 0;
        for (int i = tilaaja.length() - 1; i > -1; i--) {
            if (tilaaja.charAt(i) == ' ') {
                indeksi = i + 1;
                break;
            }
        }
        tilaaja = tilaaja.substring(indeksi);

        ResultSet rs = lataaja.getDbc().executeQuery("select distinct Asiakas.nimi, Asiakas.asiakasnumero\n"
                + "from Suorite, Asiakas\n"
                + "where Suorite.vastaanottaja = Asiakas.asiakasnumero\n"
                + "and Suorite.vastaanottajanVersio = Asiakas.versio\n"
                + "and Suorite.lasku is null\n"
                + "and Suorite.tilaaja = " + tilaaja
                + "");

        ArrayList<String> al = new ArrayList<>();

        try {
            while (rs.next()) {
                al.add(rs.getString(1) + " " + rs.getString(2));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
        }

        vastaanottajaComboBox.removeAllItems();

        for (String stri : al) {
            vastaanottajaComboBox.addItem(stri);
        }
        vastaanottajaComboBoxKuuntelija.setPaivitysKesken(false);
        vastaanottajaComboBoxKuuntelija.paivitaSuoritelista();
    }

    public Integer getValinta() {
        if (valinta == -5) {
            return 0;
        }
        return valinta;
    }

    public void setValinta(Integer valinta) {
        this.valinta = valinta;
    }

    public void setVastaanottajaComboBox(JComboBox vastaanottajaComboBox) {
        this.vastaanottajaComboBox = vastaanottajaComboBox;
    }

    public void setTilaajaComboBox(JComboBox tilaajaComboBox) {
        this.tilaajaComboBox = tilaajaComboBox;
    }

    public void setVastaanottajaComboBoxKuuntelija(LisaaLaskutIkkunaVastaanottajaComboBoxKuuntelija vastaanottajaComboBoxKuuntelija) {
        this.vastaanottajaComboBoxKuuntelija = vastaanottajaComboBoxKuuntelija;
    }

}
