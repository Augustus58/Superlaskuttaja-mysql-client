/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.asiakkaat.muokkaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.logiikka.Asiakas;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class MuokkaaAsiakastaIkkunaMuokkaaKuuntelija implements ActionListener {

    private final JTextField nimiKentta;
    private final JTextField katuosoiteKentta;
    private final JTextField postinumeroKentta;
    private final JTextField kaupunkiKentta;
    private final JTextField emailKentta;
    private final JTextField laskujaLahetettyKentta;
    private final DataDeliver lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final JFrame frame;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public MuokkaaAsiakastaIkkunaMuokkaaKuuntelija(JTextField nimiKentta, JTextField katuosoiteKentta, JTextField postinumeroKentta, JTextField kaupunkiKentta, JTextField emailKentta, JTextField laskujaLahetettyKentta, DataDeliver lataaja, AsiakkaatTaulukko taulukko, JFrame frame, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
        this.nimiKentta = nimiKentta;
        this.katuosoiteKentta = katuosoiteKentta;
        this.postinumeroKentta = postinumeroKentta;
        this.kaupunkiKentta = kaupunkiKentta;
        this.emailKentta = emailKentta;
        this.laskujaLahetettyKentta = laskujaLahetettyKentta;
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.frame = frame;
        this.kuuntelija = kuuntelija;
        this.lukko = lukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            String alkupAsiakasnumero = taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 5).toString();
            Asiakas asiakas = new Asiakas(alkupAsiakasnumero, nimiKentta.getText(), katuosoiteKentta.getText(), postinumeroKentta.getText(), kaupunkiKentta.getText(), Integer.parseInt(laskujaLahetettyKentta.getText()), emailKentta.getText());

            if (!asiakas.onkoTiedotOikeanlaiset()) {
                throw new IllegalArgumentException("Jokin syöte on virheellinen.");
            }
            
            ResultSet rs = lataaja.getDbc().executeQuery("select max(versio) from Asiakas\n"
                    + "where asiakasnumero = " + alkupAsiakasnumero + "\n"
                    + "");

            try {
                rs.first();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getSQLState());
            }

            int uudenVersio = rs.getInt(1) + 1;

            lataaja.getDbc().executeUpdate("INSERT INTO Asiakas () VALUES ('" + alkupAsiakasnumero + "',\n"
                    + uudenVersio + ",\n"
                    + "'" + nimiKentta.getText() + "',\n"
                    + "'" + katuosoiteKentta.getText() + "',\n"
                    + "'" + postinumeroKentta.getText() + "',\n"
                    + "'" + kaupunkiKentta.getText() + "',\n"
                    + "'" + laskujaLahetettyKentta.getText() + "',\n"
                    + "'" + emailKentta.getText() + "'\n"
                    + ")");

            rs = lataaja.getDbc().executeQuery("select distinct nimi, katuosoite, postinumero, kaupunki, email, asiakasnumero, laskujaLahetetty, versio\n"
                    + "from Asiakas\n"
                    + "where versio = " + uudenVersio + "\n"
                    + "and asiakasnumero = " + alkupAsiakasnumero + "\n"
                    + "");

            taulukko.addAsiakkaatTaulukkoRiviKohtaan(kuuntelija.getPaivitettyArvo(), rs);
            taulukko.getModel().removeRow(kuuntelija.getPaivitettyArvo() + 1);

            suljeIkkuna();
        } catch (Exception e) {
//            e.printStackTrace();
            MuokkaaAsiakastaIkkunaMuokkaaKuuntelijaPoikkeusIkkuna poikkeusIkkuna = new MuokkaaAsiakastaIkkunaMuokkaaKuuntelijaPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }

    }

    private void suljeIkkuna() {
        frame.dispose();
        lukko.avaa();
    }
}
